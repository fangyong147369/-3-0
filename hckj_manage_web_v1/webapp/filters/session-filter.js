'use strict';

/**
 * 授权登陆
 * @param req
 * @param res
 * @param next
 */
exports.auth= function*(next) {
    if(com.env.countNum.length>0){
      for(var i=0;i<com.env.countNum.length;+i++){
          if(com.env.countNum[i].uuid){
              yield* next;
          }else{
            return  this.response.redirect('/signIn');
          }
      }
    }
};