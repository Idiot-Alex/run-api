import playwright, { devices } from 'playwright';

(async () => {
  // Setup
  const browser = await playwright.chromium.launch();
  const context = await browser.newContext(devices['iPhone 11']);
  const page = await context.newPage();

  // The actual interesting bit
  await context.route('**.jpg', route => route.abort());
  await page.goto('https://www.baidu.com/');

  console.log(await page.title());

  // Teardown
  await context.close();
  await browser.close();
})()
