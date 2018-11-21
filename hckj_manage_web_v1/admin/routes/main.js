'use strict';
const operator = require('../controllers/operator');
const session = require('../filters/session-filter');
const koaBody = require('koa-body')();
const ccap = require('../tool/ccap');
module.exports = function (router) {
   // router.get('/',koaBody,operator.index);
    router.post('/',koaBody,operator.globaSerch);//全局搜索
    router.get('/signIn',koaBody,operator.findOperator);
    router.get('/signOut',koaBody,operator.signOut);
    router.get('/welcome',koaBody,session.auth,operator.welcome);
    router.get('/test',koaBody,operator.test);
};