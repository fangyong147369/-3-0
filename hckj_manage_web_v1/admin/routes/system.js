'use strict';
const system = require('../controllers/system');
const session = require('../filters/session-filter');
const koaBody = require('koa-body')();

module.exports = function (router) {
    router.get('/system/operator',koaBody,session.auth,system.operator);
    router.get('/system/editOperator',koaBody,session.auth,system.editOperator);
    router.post('/system/saveOperator',koaBody,session.auth,system.saveOperator);
    router.post('/system/getOperatorList',koaBody,session.auth,system.getOperatorList);
    router.post('/system/getMenuListByRoleId',koaBody,session.auth,system.getMenuListByRoleId);

    router.get('/system/role',koaBody,session.auth,system.role);
    router.post('/system/getRoleList',koaBody,session.auth,system.getRoleList);
    router.get('/system/editRole',koaBody,session.auth,system.editRole);
    router.post('/system/saveRole',koaBody,session.auth,system.saveRole);

    router.get('/system/menu',koaBody,session.auth,system.menu);
    router.post('/system/getMenuList',koaBody,session.auth,system.getMenuList);
    router.get('/system/editMenu',koaBody,session.auth,system.editMenu);
    router.post('/system/saveMenu',koaBody,session.auth,system.saveMenu);

};