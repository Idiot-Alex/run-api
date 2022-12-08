
describe('测试getResult函数', function() {
    test('正常入参', function() {
        expect(true)
    })
})

describe('sum function test', () => {
    // 这里 test 和 it 没有明显区别，it 是指: it should xxx, test 是指 test xxx
    test('sum(1, 2) === 3', () => {
        expect(1+2).toBe(3);
    });
})