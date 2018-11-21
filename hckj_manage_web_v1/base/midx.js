'use strict';
const request = require('request');

/**
 * midx请求
 */
module.exports = function (url, postdata) {
    postdata =postdata||{};
    console.error(new Date().Format("yyyy-MM-dd hh:mm:ss")+"       "+com.env.core_path + url);
    console.error(postdata);
    return new Promise(( resolve, reject ) => {
        request.post(com.env.core_path+url,(err, _res, body)  =>{
            console.log(body);
            if (err) {
                resolve( err );
            }
            else {
                if (body){
                    try{
                        body = JSON.parse(body);
                    }catch(e){
                        body=null;
                    }
                }
                resolve(body ? body : null);
            }
        }).form(postdata);
    })
}