'use strict';

/**
 * 授权登陆
 * @param req
 * @param res
 * @param next
 */
exports.auth= function*(next) {
    // if(this.session.user){
    //     yield* next;
    // }else{
    //     this.body={mes:"请先登录!"}
    // }
    yield* next;
};