'use strict';
const operator = require('../controllers/operator');
const session = require('../filters/session-filter');
const koaBody = require('koa-body')();
const ccap = require('../tool/ccap');
module.exports = function (router) {
    router.get('/',koaBody,session.auth,operator.index);
    router.post('/',koaBody,session.auth,operator.globaSerch);//全局搜索
    router.get('/signIn',koaBody,operator.signIn);
    router.get('/getImgCode',koaBody,ccap.getImageCode);
    router.post('/checkImageCode',koaBody,ccap.checkImageCode);//使用验证码的
    router.post('/signIn',koaBody,operator.findOperator);
    router.get('/signOut',koaBody,operator.signOut);
    router.get('/welcome',koaBody,session.auth,operator.welcome);
    router.get('/test',koaBody,operator.test);
};