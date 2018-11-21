'use strict';

/**
 * 授权登陆
 * @param req
 * @param res
 * @param next
 */
exports.auth= function*(next) {
    if(this.session.user){
        yield* next;
    }else{
        this.redirect('/signIn');
    }
};