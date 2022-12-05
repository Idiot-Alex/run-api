import {proxy, unProxy} from "ajax-hook";

function init(server) {
    console.log("init......")
    proxy({
        //请求发起前进入
        onRequest: (config, handler) => {
            console.log(config.url)
            handler.next(config);
        },
        //请求发生错误时进入，比如超时；注意，不包括http状态码错误，如404仍然会认为请求成功
        onError: (err, handler) => {
            console.log(err.type)
            handler.next(err)
        },
        //请求成功后进入
        onResponse: (response, handler) => {
            console.log(response.response)
            handler.next(response)
        }
    })
}

function destroy() {
    console.log("destroy......")
    unProxy();
}

const runapi = {
    init: init(),
    destroy: destroy(),
}
export default runapi;