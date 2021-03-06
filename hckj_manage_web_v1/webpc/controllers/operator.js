'use strict';
const biz_operator=require('../../base/system');
/**
 * 登录
 */
exports.signIn=function*(){
    yield this.render('/main/signIn',{req:this});
}

/**
 * 注销
 */
exports.signOut=function*(){
    // delete this.session.user;
           this.session.user=null;
    yield this.render('/main/signIn',{req:this});
}

/**
 * 首页
 */
exports.index=function*(){
    let menuList=[];
    let roleId=(this.session&&this.session.user&&this.session.user.roleModel)?this.session.user.roleModel.id:0;
   if(roleId>0){
       menuList=yield biz_operator.getMenuListByRoleId(roleId);
   }
    yield this.render('/main/index',{req:this,menuList:menuList});
}
/**
 * 登录
 */
exports.globaSerch=function*(){
    let body=this.request.body;
    this.body={message:"···接口陆续开放中·····","返回":"<a href='http://localhost:4700/'>返回<a/>"};
}
/**
 * welcome
 */
exports.welcome=function*(){
    let menu=yield biz_operator.getParentMenuList(1);
    yield this.render('/main/welcome',{req:this});
}

/**
 * 登录
 */
exports.findOperator=function*(){
    let userName=this.request.body.username;
    let password=this.request.body.password;
    let body=yield biz_operator.findOperator({userName:userName,password:password});
    if(body.code==1&&body.data){
        body.sessionId=this.sessionId;
        this.session.user=body.data;
    }
    this.body=body;
}
/**
 * 登录
 */
exports.test=function*(){
    let body=yield biz_operator.test(1);
    yield this.render('/main/test',{req:this,body:body});
}
