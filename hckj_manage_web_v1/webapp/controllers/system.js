'use strict';
const system=require('../../base/system.js');
/**
 * 管理员列表
 */
exports.operator=function*(){
    yield this.render('/system/operator',{req:this});
}

/**
 * 管理员编辑
 */
exports.editOperator=function*(){
    let model=null;
    let id=this.request.query.id||'';
    let roleList=yield system.getEffectiveRoleList();
    if(id>0 && !isNaN(id)){
        model=yield system.getOperatorById(id);
    }
    yield this.render('/system/editOperator',{req:this,model:model,roleList:roleList});
}

/**
 * 管理员保存
 */
exports.saveOperator=function*(){
    let model= this.request.body.model||'';
    let result=yield system.saveOperator(model);
    this.body=result;
}

/**
 * 获取管理员列表
 */
exports.getOperatorList=function*(){
    let model= this.request.body.model;
    let body=yield system.getOperatorList(model);
    this.body=body;
}

/**
 * 菜单
 */
exports.menu=function*(){
    yield this.render('/system/menu',{req:this});
}

/**
 * 菜单编辑
 */
exports.editMenu=function*(){
    let model=null;
    let id=this.request.query.id||'';
    let parentMenu=yield system.getParentMenuList();
    if(id && !isNaN(id)){
        model=yield system.getMenuById(id);
    }
    yield this.render('/system/editMenu',{req:this,parentmodel:parentMenu,model:model});
}

/**
 * 菜单列表
 */
exports.getMenuList=function *(){
    let model= this.request.body.model;
    let body=yield system.getMenuList(model);
    this.body=body;
}

/**
 * 菜单保存
 */
exports.saveMenu=function*(){
    let model= this.request.body.model||'';
    let result=yield system.saveMenu(model);
    this.body=result;
}


/**
 * 根据角色Id和菜单状态获取菜单列表
 */
exports.getMenuListByRoleId=function *(){
    let roleMenu=null;
    let id=this.request.query.id||0;
    if(id>0) {
        roleMenu = yield system.getMenuListByRoleId(id);
    }
    this.body=roleMenu;
}
/**
 * 角色编辑
 */
exports.editRole=function*(){
    let role=null;
    let id=this.request.query.id||'';
    let roleMenu=[];
    roleMenu.push(0);
    if(id && !isNaN(id)){
        role=yield system.getRoleById(id);
        if(role && role.roleMenus &&　role.roleMenus instanceof  Array){
            for(let i=0;i<role.roleMenus.length;i++){
                roleMenu.push(role.roleMenus[i].menuModel.id)
            }
        }
    }
    let body= yield system.getParentMenuList(0);
    console.log()
    let menu={};
    if(body && body instanceof Array){
        for(let i=0;i<body.length;i++){
            menu[body[i].id]={id:body[i].id,pid:body[i].parentId,title:body[i].name};
        }
    }
    yield this.render('/system/editRole',{req:this,model:role,roleMenu:roleMenu.join(','),menu:encodeURIComponent(JSON.stringify(menu))});
}
/**
 * 角色列表
 */
exports.role=function*(){
    yield this.render('/system/role',{req:this});
}

/**
 * 角色列表
 */
exports.getRoleList=function *(){
    let model= this.request.body.model;
    let body=yield system.getRoleList(model);
    this.body=body;
}

/**
 * 角色保存
 */
exports.saveRole=function*(){
    let model= this.request.body.model||'';
   let result=yield system.saveRole(model);
    this.body=result;
}
