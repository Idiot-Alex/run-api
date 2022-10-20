import { createApp } from 'vue'
import './style.css'
import 'uno.css'
import App from './App.vue'


createApp(App).mount('#app')

console.log(process)
const currentNodeVersion = process.versions.node;
const semver = currentNodeVersion.split('.');
console.log(semver)

import playwright from 'playwright'
(async () => {
  const browser = await playwright.chromium.launch({
    channel: 'msedge',
  });
  const context = await browser.newContext();
  const page = await context.newPage();
  await page.goto('http://www.baidu.com');
  await page.screenshot({ path: 'example1.png' });

  await browser.close();
})();
