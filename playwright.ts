// example.js
import playwright from 'playwright'

(async () => {
  const browser = await playwright.chromium.launch({
    channel: 'msedge',
  });
  const context = await browser.newContext();
  const page = await context.newPage();
  await page.goto('https://www.baidu.com');
  await page.screenshot({ path: 'example.png' });

  const pageContent = await page.content();
  console.log(pageContent)
  console.log('---------------')
  const request = await page.on('request')

  await browser.close();
})();
