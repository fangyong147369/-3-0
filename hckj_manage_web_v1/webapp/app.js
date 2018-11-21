'use strict';
const env = process.argv[2];
require('../env/env-' + env);
const app = require('koa')();
const logger = require('koa-logger');//日志打印
const koa_static = require('koa-static');//静态文件
const router = require('koa-router')(); //路由
const onerror = require('koa-onerror');
const render = require('koa-ejs');
const path = require('path');
const fs = require('fs');
const session = require('koa-generic-session');//基于缓存的session
const redisStore = require('koa-redis');
global.config = require('./config');
app.keys = ['hckj'];//设置签名Cookie密钥
app.use(session({
    secret: 'adMiN_AH^%^SSFWPDG32A2DKJS(*PDSA',
    key: 'hckj_session',
    proxy: 'true',
    cookie: {
        domain: 'localhost',
        path: '/',
        httpOnly: false,
        maxAge: 24 * 60 * 60 * 1000, //one day in ms,
        rewrite: false,
        signed: false,
        overwrite: false
    },
    store: redisStore({//将session数据同步到redis中：
        host: com.env.redis.host,
        port: com.env.redis.port,
        prefix: "/",
        ttl: 60 * 60 * 24 * 30,   //session的有效期为30天(秒)
    })
}));
app.use(router.routes());
app.use(router.allowedMethods());
require('../tools/util');
console.log(__dirname);
console.log(path.dirname(__dirname));
app.use(koa_static('public'));

if ('dev' === env) {
    app.use(logger());
}
onerror(app);

global._static_url = com.env.cdn_url;
// app.use(function* (next) {
//     var start = new Date;
//     yield next;
//     var ms = new Date - start;
//     this.set('X-Response-Time', ms + 'ms');
// });

// app.use(function* (next) {
//     var start = new Date;
//     yield next;
//     var ms = new Date - start;
//     console.log('%s %s - %s', this.method, this.url, ms);
// });

app.use(function* (next) {

    try {
        if (this.status == "404") {
            console.log('此接口不存在！ ' + JSON.stringify(this))
            if (this.method === "POST") {
                this.body = {message: '此接口不存在！ ', code: -1};
            } else {
             return   yield this.render('404');
            }
        }
        else {
            yield next;
        }
    } catch (err) {
        this.status = err.status || 500;
        yield this.render('500');
    }
})

fs.readdirSync('./routes').forEach(function (file) {
    if (file.indexOf(".js") > -1)
        require('./routes/' + file.replace(/^(.+)\.js$/, "$1"))(router);
})

render(app, {
    layout: '__layout',
    root: path.join(__dirname, 'views'),
    viewExt: 'ejs',
    cache: ('dev' !== env),
    debug: ('dev' === env)
});

/**
 * 错误捕捉中间件
 */
app.use(function* (next) {
    this
    try {
        this.error = (code, message) => {
            console.log('threw error');
            if (typeof code === 'string') {
                message = code;
                code = 500;
            }

            this.throw(code || 500, message || '服务器错误');
        };

        yield next();
    } catch (e) {
        let status = e.status || 500;
        let message = e.message || '服务器错误';
        this.body = {status, message};

        // 手动释放 error 事件
        this.app.emit('error', e, ctx);
    }
});
app.on('error', function (err) {
    console.log('server error', err);
});
app.listen(7000, function () {
    console.log("端口号：" + 7000 + " -启动成功")
});