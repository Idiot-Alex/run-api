package com.hotstrip.runapi.proxy;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpProxyServer {
    int port;

    public HttpProxyServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        new HttpProxyServer(8443).run();
    }

    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(
                                    new LoggingHandler(LogLevel.DEBUG),
                                    new HttpProxyClientHandler());
                        }
                    })
                    .bind(port).sync().channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    /**
     * 代理客户端去请求目标主机
     */
    private static class HttpProxyClientHandler extends ChannelInboundHandlerAdapter {

        /*代理服务端channel*/
        private Channel clientChannel;
        /*目标主机channel*/
        private Channel remoteChannel;
        /*解析真实客户端的header*/
        private HttpProxyClientHeader header = new HttpProxyClientHeader();


        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            clientChannel = ctx.channel();
        }

        /**
         * 注意在真实客户端请求一个页面的时候，此方法不止调用一次，
         * 这是TCP底层决定的（拆包/粘包）
         */
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            if (header.isComplete()) {
               /*如果在真实客户端的本次请求中已经解析过header了，
               说明代理客户端已经在目标主机建立了连接，直接将真实客户端的数据写给目标主机*/
                remoteChannel.writeAndFlush(msg);// just forward
                return;
            }

            ByteBuf in = (ByteBuf) msg;
            header.digest(in);/*解析目标主机信息*/

            if (!header.isComplete()) {
                /*如果解析过一次header之后未完成解析，直接返回，释放buf*/
                in.release();
                return;
            }

            // disable AutoRead until remote connection is ready
            clientChannel.config().setAutoRead(false);

            if (header.isHttps()) { // if https, respond 200 to create tunnel
                clientChannel.writeAndFlush(Unpooled.wrappedBuffer("HTTP/1.1 200 Connection Established\r\n\r\n".getBytes()));
            }
            /*
              下面为真实客户端第一次来到的时候，代理客户端向目标客户端发起连接
             */
            Bootstrap b = new Bootstrap();
            b.group(clientChannel.eventLoop()) // use the same EventLoop
                    .channel(clientChannel.getClass())
                    .handler(new HttpProxyRemoteHandler(clientChannel));
            ChannelFuture f = b.connect(header.getHost(), header.getPort());
            remoteChannel = f.channel();
            f.addListener((ChannelFutureListener) future -> {
                if (future.isSuccess()) {
                    // connection is ready, enable AutoRead
                    clientChannel.config().setAutoRead(true);
                    // forward header and remaining bytes
                    if (!header.isHttps()) {
                        //in读取一次缓冲区就没有了，header.byteBuf里面存了一份
                        remoteChannel.writeAndFlush(header.getByteBuf());
                    }
                } else {
                    in.release();
                    clientChannel.close();
                }
            });
        }


        @Override
        public void channelInactive(ChannelHandlerContext ctx) {
            flushAndClose(remoteChannel);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
            e.printStackTrace();
            flushAndClose(clientChannel);
        }

        private void flushAndClose(Channel ch) {
            if (ch != null && ch.isActive()) {
                ch.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
            }
        }
    }


    /**
     * 代理客户端请求目标主机处理器
     */
    private static class HttpProxyRemoteHandler extends ChannelInboundHandlerAdapter {

        private Channel clientChannel;
        private Channel remoteChannel;

        public HttpProxyRemoteHandler(Channel clientChannel) {
            this.clientChannel = clientChannel;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            this.remoteChannel = ctx.channel();
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            clientChannel.writeAndFlush(msg); // just forward
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) {
            flushAndClose(clientChannel);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
            e.printStackTrace();
            flushAndClose(remoteChannel);
        }

        private void flushAndClose(Channel ch) {
            if (ch != null && ch.isActive()) {
                ch.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
            }
        }
    }


    /**
     * 真实主机的请求头信息
     */
    private static class HttpProxyClientHeader {
        private String method;//请求类型
        private String host;//目标主机
        private int port;//目标主机端口
        private boolean https;//是否是https
        private boolean complete;//是否完成解析
        private ByteBuf byteBuf = Unpooled.buffer();


        private StringBuilder lineBuf = new StringBuilder();


        public String getMethod() {
            return method;
        }

        public String getHost() {
            return host;
        }

        public int getPort() {
            return port;
        }

        public boolean isHttps() {
            return https;
        }

        public boolean isComplete() {
            return complete;
        }

        public ByteBuf getByteBuf() {
            return byteBuf;
        }

        /**
         * 解析header信息，建立连接
         HTTP 请求头如下
         GET <a href="http://www.baidu.com/">www.baidu.com</a> HTTP/1.1
         Host: www.baidu.com
         User-Agent: curl/7.69.1
         Accept: *//*
        Proxy-Connection:Keep-Alive

        HTTPS请求头如下
        CONNECT www.baidu.com:443 HTTP/1.1
        Host: www.baidu.com:443
        User-Agent: curl/7.69.1
        Proxy-Connection: Keep-Alive

        * @param in
        */
        public void digest(ByteBuf in) {
            while (in.isReadable()) {
                if (complete) {
                    throw new IllegalStateException("already complete");
                }
                String line = readLine(in);
                log.info(line);
                if (line == null) {
                    return;
                }
                if (method == null) {
                    method = line.split(" ")[0]; // the first word is http method name
                    https = method.equalsIgnoreCase("CONNECT"); // method CONNECT means https
                }
                if (line.startsWith("Host: ")) {
                    String[] arr = line.split(":");
                    host = arr[1].trim();
                    if (arr.length == 3) {
                        port = Integer.parseInt(arr[2]);
                    } else if (https) {
                        port = 443; // https
                    } else {
                        port = 80; // http
                    }
                }
                if (line.isEmpty()) {
                    if (host == null || port == 0) {
                        throw new IllegalStateException("cannot find header 'Host'");
                    }
                    byteBuf = byteBuf.asReadOnly();
                    complete = true;
                    break;
                }
            }
            log.info(String.valueOf(this));
            log.info("--------------------------------------------------------------------------------");
        }

        private String readLine(ByteBuf in) {
            while (in.isReadable()) {
                byte b = in.readByte();
                byteBuf.writeByte(b);
                lineBuf.append((char) b);
                int len = lineBuf.length();
                if (len >= 2 && lineBuf.substring(len - 2).equals("\r\n")) {
                    String line = lineBuf.substring(0, len - 2);
                    lineBuf.delete(0, len);
                    return line;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return "HttpProxyClientHeader{" +
                    "method='" + method + '\'' +
                    ", host='" + host + '\'' +
                    ", port=" + port +
                    ", https=" + https +
                    ", complete=" + complete +
                    '}';
        }
    }

    public static String convertByteBufToString(ByteBuf buf) {
        String str;
        if (buf.hasArray()) { // 处理堆缓冲区
            str = new String(buf.array(), buf.arrayOffset() + buf.readerIndex(), buf.readableBytes());
        } else { // 处理直接缓冲区以及复合缓冲区
            byte[] bytes = new byte[buf.readableBytes()];
            buf.getBytes(buf.readerIndex(), bytes);
            str = new String(bytes, 0, buf.readableBytes());
        }
        return str;
    }
}
