import { Plugin } from "vite";
import playwright, { devices } from 'playwright';

export default function pluginPlayWright(): Plugin {
  return {
    name: 'plugin-playwright',
    enforce: 'pre',
    // vite特有钩子
    config(config) {
      console.log('config', config);
      console.log('process1:', process);
      return {}
    },
    configResolved(resolvedCofnig) {
      // console.log('configResolved', resolvedCofnig);
      console.log('process2: ', process);
    },
    options(options) {
      console.log(options);
      return {}
    },
    transformIndexHtml(html) {
      return html.replace(
        /<title>(.*?)<\/title>/,
        `<title>Title replaced!</title>`
      )
    }
  };
}
