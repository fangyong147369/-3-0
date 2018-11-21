/**
 * @author zzhu
 * 系统配置业务
 */
'use strict';
const co = require('co');
const midx = require('./midx');

/**
 * 管理员登录
 * @param name 帐号
 * @param password 密码
 * @returns {*}
 */
exports.findOperator = function* (data) {
    if (!data.userName) {
        return {message: '帐号不能为空.', code: -1};
    }
    if (!data.password) {
        return {message: '密码不能为空.', code: -1};
    }
    return co(function* () {
        try {
            let result = yield midx('/manage/operator/signIn', data);
            if(result!=null&&result.code==="ECONNREFUSED"){
                return {err:"服务器连接失败！",address:"192.168.2.84:"+result.port,code:2,mes:"服务器地址错误或服务器已关闭！"};
            }
            return result;
        } catch (error) {
            return {code: -1, message: error};
        }
    })
}

/**
 * 根据id获取管理员信息
 * @param id
 */
exports.getOperatorById = function* (id) {
    return co(function* () {
        try {
            let result = yield midx('/manage/operator/getById', {id: id});
            return (result && result.data && result.data.id) ? result.data : null;
        } catch (error) {
            console.log(error);
            return null;
        }
    })
}

/**
 * 统计在线用户
 * @param model
 * @return
 */
exports.selectOperatorSessionID = function* () {
    return co(function* () {
        try {
            let result = yield midx('/manage/operator/selectOperatorSessionID');
            return result;
        } catch (error) {
            console.log(error);
            return null;
        }
    })
}

/**
 * 删除SessionID
 * @param model
 * @return
 */
exports.deleteSessionID = function* (id) {
    return co(function* () {
        try {
            let result = yield midx('/manage/operator/deleteSessionID',{id:id});
            return result;
        } catch (error) {
            console.log(error);
            return null;
        }
    })
}
/**
 * 获取管理员列表
 * @param pageNo
 * @param pageSize
 * @returns {*|Promise}
 */
exports.getOperatorList = function* (model) {
    return co(function* () {
        try {
            let result = yield midx('/manage/operator/list', model);
            return {
                rows: (result && result.data && result.data.list && result.data.list instanceof Array ? result.data.list : []),
                total: (result && result.data && result.data.page && result.data.page.total && !isNaN(result.data.page.total) ? result.data.page.total : 0)
            };
        } catch (error) {
            console.log(error);
            return {list: [], total: 0, code: -1, message: error};
        }
    })
}

/**
 * 管理员保存
 * @param params
 * @returns {*}
 */
exports.saveOperator = function* (params) {
    return co(function* () {
        try {
            let result = null;
            if (params.id == 0) {
                let userName = yield midx('/manage/operator/getOperatorBySearchName', {searchName: params.userName});
                if (userName.code==1) {
                    return {code: -1, message: "此用户名已被占用！"};
                }
                result = yield midx('/manage/operator/add', params);
            } else {
                let model = yield midx('/manage/operator/getById', {id: params.id});
                if (model.code==-1&&model.data==null) {
                    return {code: -1, message: '用户信息错误.'};
                }
                if (model!=null&&model.code==1&&model.data!=null) {
                    if (model.data.userName!=params.userName) {
                        let userName = yield midx('/manage/operator/getOperatorBySearchName', {searchName: params.userName});
                        if (userName != null) {
                            return {code: -1, message: "此用户名已被占用！"};
                        }
                    }
                }
                result = yield midx('/manage/operator/update', params);
            }
            return result;
        } catch (error) {
            console.log(error);
            return {code: -1, message: error};
        }
    })
}


/**
 * 角色列表
 */
exports.getRoleList = function* (model) {
    return co(function* () {
        try {
            let result = yield midx('/manage/role/list', model);
            return {
                rows: (result && result.data && result.data.list && result.data.list instanceof Array ? result.data.list : []),
                total: (result && result.data && result.data.page && result.data.page.total && !isNaN(result.data.page.total) ? result.data.page.total : 0)
            };
        } catch (error) {
            console.log(error);
            return {list: [], total: 0, code: -1, message: error};
        }
    })
}

/**
 * 根据id获取角色
 * @param id
 * @returns {*|Promise}
 */
exports.getRoleById = function* (id) {
    return co(function* () {
        try {
            let result = yield midx('/manage/role/getById', {id: id});
            return (result && result.data && result.data.id) ? result.data : null;
        } catch (error) {
            console.log(error);
            return null;
        }
    })
}

/**
 * 有效角色
 * @returns {*|Promise}
 */
exports.getEffectiveRoleList = function* () {
    return co(function* () {
        try {
            let result = yield midx('/manage/role/list', {pageNo: 1, pageSize: 100, status: 1});
            return (result && result.data && result.data.list && result.data.list instanceof Array) ? result.data.list : [];
        } catch (error) {
            console.log(error);
            return {list: [], error: error};
        }
    })
}

/**
 * 角色编辑
 * @param params object对象
 * @returns {*}
 */
exports.saveRole = function* (params) {
    if (!params || typeof params != 'object') {
        return {code: -1, message: '参数错误.'}
    }
    if (!params || !params.name || !((/^[\u4e00-\u9fa5]+([\.|\·][\u4e00-\u9fa5]+)*$/).test(params.name))) {
        return {code: -1, message: '角色名称不正确.'};
    }
    return co(function* () {
        try {
            let result = null;
            if (params.id == 0) {
                let roleName = yield midx('/manage/role/getRoleBySearchName', {searchName: params.name});
                if (roleName != null) {
                    return {code: -1, message: "此角色名已被占用！"};
                }
                result = yield midx('/manage/role/add', params);
                if (result && result.data && result.data.id) {
                    return {code: 1, message: "操作成功"};
                } else {
                    return {code: -1, message: result.message};
                }
            } else {
                let model = yield midx('/manage/role/getById', {id: params.id});
                console.log(model.data.name==params.name)
                if (!model || !model.data ||model.code!=1) {
                    return {code: -1, message: '角色信息错误.'};
                }
                    if (model != null && model.code == 1 && model.data != null) {
                        if (model.data.name != params.name) {
                            let roleName = yield midx('/manage/role/getRoleBySearchName', {searchName: params.name});
                            if (roleName != null) {
                                return {code: -1, message: "此角色名已被占用！"};
                            }
                        }
                    result = yield midx('/manage/role/update', params);
                }
                return result;
            }
        } catch (error) {
            console.log(error);
            return {code: -1, message: error};
        }
    })
}
/**
 * 获取菜单列表
 * @param name
 * @param password
 * @returns {*}
 */
exports.getMenuList = function* (model) {
    return co(function* () {
        try {
            let result = yield midx('/manage/menu/list', model);
            return {
                rows: (result && result.data && result.data.list && result.data.list instanceof Array ? result.data.list : []),
                total: (result && result.data && result.data.page && result.data.page.total && !isNaN(result.data.page.total) ? result.data.page.total : 0)
            };
        } catch (error) {
            console.log(error);
            return {list: [], total: 0, code: -1, message: error};
        }
    })
}

/**
 * 父菜单
 */
exports.getParentMenuList = function* (status) {
    status = status;
    return co(function* () {
        try {
            let result = yield midx('/manage/menu/list', {pageNo: 1, pageSize: 1000, status: status});
            return (result && result.data && result.data.list && result.data.list instanceof Array) ? result.data.list : [];
        } catch (error) {
            console.log(error);
            return {list: [], message: error};
        }
    })
}

/**
 * 菜单编辑
 * @param params object对象
 * @returns {*}
 */
exports.saveMenu = function* (params) {
    if (!params || !params.name || !((/^[\u4e00-\u9fa5]+([\.|\·][\u4e00-\u9fa5]+)*$/).test(params.name))) {
        return {code: -1, message: '菜单名称不正确.'};
    }
    if (params.parentId != 0 && !params.href) {
        return {code: -1, message: '菜单链接不能为空.'};
    }
    return co(function* () {
        try {
            let result = null;
            if (params.id==0) {
                result = yield midx('/manage/menu/add', params);
            }
            else {
                let model = yield midx('/manage/menu/getById', {id: params.id});
                if (!model || !model.data || !model.data.id) {
                    return {code: -1, message: '菜单信息错误.'};
                }
                if (params.parentId == model.data.id) {
                    return {code: -1, message: '父菜单不能选自身.'};
                }
                let modelList = [];
                if (model.data.parentId === 0 && params.parentId != 0) {
                    modelList = yield midx('/manage/menu/list', {parentId: id});
                    if (modelList && modelList.data && modelList.data.list && modelList.data.list instanceof Array && modelList.data.list.length > 0) {
                        return {code: -1, message: '该菜单有子菜单不能变更菜单级别.'};
                    }
                }
                result = yield midx('/manage/menu/update', params);
            }
            return result;
        } catch (error) {
            console.log(error);
            return {message: error, code: -1};
        }
    })
}

/**
 * 根据id获取菜单
 * @param id
 * @returns {*|Promise}
 */
exports.getMenuById = function* (id) {
    return co(function* () {
        try {
            let result = yield midx('/manage/menu/getById', {id: id});
            return (result && result.data && result.data.id) ? result.data : null;
        } catch (error) {
            console.log(error);
            return {message: error, code: -1};
        }
    })
}
/**
 * 根据角色id获取菜单列表
 * @param id
 * @returns {*|Promise}
 */
exports.getMenuListByRoleId = function* (roleId) {
    if (roleId <= 0) {
        return {code: -1, message: "角色Id错误！"};
    }
    return co(function* () {
        try {
            let result = yield midx('/manage/role/getListMuneByRoleId', {id: roleId, status: 1});
            return result;
        } catch (error) {
            console.log(error);
            return {message: error, code: -1};
        }
    })
}


/**
 * 根据id获取管理员信息
 * @param id
 */
exports.test = function* (id) {
    return co(function* () {
        try {
            let result = yield midx('/manage/role/getListMuneByRoleId', {id: id});
            return result;
        } catch (error) {
            console.log(error);
            return {message: error, code: -1};
        }
    })
}