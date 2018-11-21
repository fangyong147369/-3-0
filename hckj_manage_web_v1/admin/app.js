'use strict';
const env = process.argv[2];
require('../env/env-' + env);
const app = require('koa')();
const logger = require('koa-logger');//日志打印
const koa_static=require('koa-static');//静态文件
const router = require('koa-router')(); //路由
const onerror = require('koa-onerror');
const render = require('koa-ejs');
const path = require('path');
const fs = require('fs');
//const session = require('koa-session');//基于cookie的session
const session = require('koa-generic-session');//基于缓存的session
const redisStore = require('koa-redis');
global.config=require('./config');
app.keys = ['hckj'];//设置签名Cookie密钥
app.use(session({
        secret: 'adMiN_AH^%^SSFWPDG32A2DKJS(*PDSA',
        key: 'hckj_session',
        proxy: 'true',
        cookie: {
            domain: 'localhost',
            path: '/',
            httpOnly: false,
            maxAge:1000, //24 * 60 * 60 * 1000one day in ms,
            rewrite: false,
            signed: false,
            overwrite: false
        },
        store: redisStore({//将session数据同步到redis中：
            host:com.env.redis.host,
            port:com.env.redis.port,
            prefix: "/",
            ttl:3,   //60 * 60 * 24 * 30session的有效期为30天(秒)
        })
    }));
app.use(router.routes());
app .use(router.allowedMethods());
require('../tools/util');
console.log(__dirname);
console.log(path.dirname(__dirname));
app.use(koa_static('public'));

if ('dev' === env) {
    app.use(logger());
}
onerror(app);

global._static_url =com.env.cdn_url;
app.use(function *(next){
    var start = new Date;
    yield next;
    var ms = new Date - start;
    this.set('X-Response-Time', ms + 'ms');
});

app.use(function *(next){
    var start = new Date;
    yield next;
    var ms = new Date - start;
    console.log('%s %s - %s', this.method, this.url, ms);
});

app.use(function *(next){
    try {
        if(this.status=="404"){
            if(this.method==="POST"){
                this.body={message:'此接口不存在！ ',code:-1};
            }else{
                yield this.render('404');
            }
        }
        else{
            yield next;
        }
    } catch (err) {
        this.status = err.status || 500;
        this.body = err.message;
    }
})

fs.readdirSync('./routes').forEach(function(file){
    if(file.indexOf(".js")>-1)
        require('./routes/'+file.replace(/^(.+)\.js$/,"$1"))(router);
})

render(app, {
    layout: '__layout',
    root: path.join(__dirname, 'views'),
    viewExt: 'ejs',
    cache: ('dev' !== env),
    debug: ('dev' === env)
});
app.on('error', function(err){
    try {
    }catch (e) {
        console.log('e',e.toString());
    }
});
app.listen(4700,function(){
	console.log("端口号："+4700+" -启动成功")
});