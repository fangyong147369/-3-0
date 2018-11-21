const cache = require('./cache');
const svgCaptcha = require('svg-captcha');
exports.getImageCode = function*(){
    let captcha = svgCaptcha.create({
        width: 105,
        height: 47,
        noise:0,
        background: "#f4f3f2",//干扰线条数
        fontSize: 48
    });
    this.response.type='svg';
    this.response.status=200;
    this.session.sessionID=this.sessionId;
    yield cache.set(this.sessionId,captcha.text,1000*60*2);
    this.body=captcha.data;
}

exports.checkImageCode =function*(){
    let cacheCode= yield cache.get(this.session.sessionID);
    let code = this.request.body.imgCode;
    console.log("cacheCode:"+cacheCode+"---code-"+code)
    if (cacheCode && cacheCode.toLowerCase() == code.toLowerCase()){
        this.body={status:200 ,code:1};
    }else {
        this.body={status:500 ,message: '图片验证码输入不正确',code:-1};
    }
}
