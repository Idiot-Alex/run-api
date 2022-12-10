import axios from "axios";
// import { proxy } from "ajax-hook";

describe('测试函数', function() {
    test('正常入参', async function() {
        // proxy({
        //     //请求发起前进入
        //     onRequest: (config, handler) => {
        //         console.log(config.url)
        //         handler.next(config);
        //     },
        //     //请求发生错误时进入，比如超时；注意，不包括http状态码错误，如404仍然会认为请求成功
        //     onError: (err, handler) => {
        //         console.log(err.type)
        //         handler.next(err)
        //     },
        //     //请求成功后进入
        //     onResponse: (response, handler) => {
        //         console.log(response.response)
        //         handler.next(response)
        //     }
        // })
        expect(true)
    })
})

describe('sum function test', () => {
    // 这里 test 和 it 没有明显区别，it 是指: it should xxx, test 是指 test xxx
    test('sum(1, 2) === 3', async () => {
        await axios.get('https://bird.ioliu.cn/v1').then(res => {
            console.log(JSON.stringify(res.data))
        })

        expect(1+2).toBe(3);
    });
})