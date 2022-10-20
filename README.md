# run-api
a api doc generated with run application

## How
1. 输入你的项目地址，点击按钮
2. 在返回的 ui 界面上完成你的交互逻辑
3. 你需要的 doc 文档就会自动生成了


## 两个想法
1. 使用 playwright js 启动一个无头的浏览器，访问输入的网址并且渲染到页面，同时拦截每一个 http 请求
2. 使用 vite 的 ssr 启动一个 palywright 无头浏览器作为中间服务，拦截每一个 http 请求（需要研究下是否可行）


## 需要实现的功能
[x] 启动一个 palywright 无头浏览器
[ ] 使用 palywright 访问网址并返回结果  
