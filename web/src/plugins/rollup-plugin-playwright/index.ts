import { Plugin } from "vite";
import playwright, { devices } from 'playwright';

export default function pluginPlayWright(): Plugin {
  return {
    name: 'plugin-playwright',
    enforce: 'pre',
    load(id, options?) {
      console.log(`id: ${id}`);
      console.log(`options: ${JSON.stringify(options)}`);
    },
   /**
     * 转换 html 输出的钩子函数
     * @param {string} html 打包生成的转换前的 html
     * @param {import('vite').IndexHtmlTransformContext} ctx 当前 bundle，chunk 等输出信息
     * @returns {import('vite').IndexHtmlTransformResult} 转换后的 html 字符串
     */
    async transformIndexHtml(html, ctx)  {
      console.log(ctx);
      return html;
    },
    transform(code, id, options?) {
      console.log(`code: ${code}, id: ${id}`);
    },
  };
}
