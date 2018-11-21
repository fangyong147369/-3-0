'use strict';
const biz_operator = require('../../base/system');
var UUID = require('uuid');
/**
 * 登录
 */
exports.signIn = function* () {
    yield this.render('/main/signIn', {req: this});
}

/**
 * 注销
 */
exports.signOut = function* () {
    let id = this.request.query.id;
    for (var i = 0; i < com.env.countNum.length; +i++) {
        if (com.env.countNum[i].id == id) {
            com.env.countNum.splice(i, 1);
        }
    }
    if (id > 0) {
        yield biz_operator.deleteSessionID(id);
    }
    yield this.render('/main/signIn', {req: this});
}

/**
 * 首页
 */
exports.index = function* () {
    let menuList = [];
    let roleId = (this.session && this.session.user && this.session.user.roleModel) ? this.session.user.roleModel.id : 0;
    if (roleId > 0) {
        menuList = yield biz_operator.getMenuListByRoleId(roleId);
    }
    yield this.render('/main/index', {req: this, menuList: menuList});
}
/**
 * 登录
 */
exports.globaSerch = function* () {
    let body = this.request.body;
    this.body = {message: "···接口陆续开放中·····", "返回": "<a href='http://localhost:4700/'>返回<a/>"};
}

/**
 * welcome
 */
exports.welcome = function* () {
    let menu = yield biz_operator.getParentMenuList(1);//统计系统当前在线管理员
    let operatorOnLine = yield biz_operator.selectOperatorSessionID();
    yield this.render('/main/welcome', {req: this, operator: operatorOnLine});
}

/**
 * 登录
 */
exports.findOperator = function* () {
    let userName = this.request.body.username;
    let password = this.request.body.password;
    let body = yield biz_operator.findOperator({
        sessionID: UUID.v1(),
        signInWay: 2,
        userName: userName,
        password: password
    });
    if (body!=null&&body.code == 1 && body.data) {
        this.session.user = body.data;
        com.env.countNum.push(new User(body.data.id, body.data.sessionID));
    }
    this.body = body;
}

function User(id, uuid) {
    this.id = id;
    this.uuid = uuid;
}

/**
 * 登录
 */
exports.test = function* () {
    let body = yield biz_operator.test(1);
    yield this.render('/main/test', {req: this, body: body});
}
