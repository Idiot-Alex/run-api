ah.proxy({
    onRequest: (config, handler) => {
        if (config.url === 'https://aa/') {
            handler.resolve({
                config: config,
                status: 200,
                headers: {'content-type': 'text/text'},
                response: 'hi world aa'
            })
        } else {
            handler.next(config);
        }
    },
    onError: (err, handler) => {
        if (err.config.url === 'https://bb/') {
            handler.resolve({
                config: err.config,
                status: 200,
                headers: {'content-type': 'text/text'},
                response: 'hi world bb'
            })
        } else {
            handler.next(err)
        }
    },
    onResponse: (response, handler) => {
        if (response.config.url === location.href) {
            handler.reject({
                config: response.config,
                type: 'error'
            })
        } else {
            handler.next(response)
        }
    }
})

function test(url) {
    var events = ['load', 'loadend', 'timeout', 'error', 'readystatechange', 'abort']

    var xhr = new XMLHttpRequest();
    events.forEach(function (e) {
        xhr['on' + e] = function (event) {
            console.log('on' + e, xhr.readyState, event)
        }
        xhr.addEventListener(e, function (event) {
            console.log(e, xhr.readyState, event)
        })
    });
    xhr.addEventListener('load', function (event) {
        console.log('response', xhr.response)
        alert(xhr.response)
    })
    //setTimeout(()=>xhr.abort(),100)
    xhr.open('get', url);
    xhr.send();

}

function testJquery(url) {
    $.get(url).done(function (d) {
        console.log(d)
        alert(d)
    }).fail(function (e) {
        console.log('hi world', e)
    })
}