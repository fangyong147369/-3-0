Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

var regExp= {
    amount: {reg: /^\d+$/, msg: '请输入正确的金额'},
    withdraw_amount: {reg: /^\d{1,30}(\.\d{1,2})?$/, msg: '请输入正确的金额'},
    bank: {reg: /\w+/, msg: '请选择银行'},
    cardno: {reg: /\w+/, msg: '请输入银行卡号'},
    validcode: {reg: /\w+/, msg: '请输入验证码'},
    mobile: {reg: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/, msg: '请输入11位手机号'},
    nick: {reg: /.+/, msg: '请输入昵称'},
    tradepwd: {reg: /.{6,}/, msg: '输入至少6位交易密码'},
    tradepwd1: {reg: /.{6,}/, reg1: 'tradepwd', msg: '两次交易密码输入不一致'},
    password: {reg: /.{6,}/, msg: '请输入至少6位密码'},
    pwd2: {reg: /.{6,}/, reg1: 'pwd', msg: '两次密码输入不一致'},
    realname: {reg: /^([\u4e00-\u9fa5]+|([a-z]+\s?)+)$/, msg: '请输入您的真实姓名'},
    idcard: {reg: /\d{17}\w/, msg: '请输入您的身份证号'},
    imagecode: {reg: /\w+/, msg: '请输入验证码'},
    email: {reg: /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/, msg: '请输入正确的邮箱地址'}
}


$.fn.serializeJson = function () {
    var serializeObj = {};
    var array = this.serializeArray();
    var str = this.serialize();
    $(array).each(function () {
        if (serializeObj[this.name]) {
            if ($.isArray(serializeObj[this.name])) {
                serializeObj[this.name].push(this.value);
            } else {
                serializeObj[this.name] = [serializeObj[this.name], this.value];
            }
        } else {
            serializeObj[this.name] = this.value;
        }
    });
    return serializeObj;
};


jQuery.fn.extend({
    checkForm2: function () {
        var valid = true, res = {}, name;
        $.each(this.find("input,select"), function (i, o) {
            var _check = $(o).attr("_check");
            var _allownull = $(o).attr("_allownull");
            var cond = true;
            if (_allownull == "true" && $.trim(o.value).length == 0) {

            }
            else {
                if ((_check == "amount" || _check == "withdraw_amount") && $(this).attr("_max")) {
                    var max = parseFloat($(this).attr("_max"));
                    cond = (parseFloat(this.value) <= max);
                }
                if (_check && cclc.checks[_check]) {
                    if (!$.trim(o.value).match(zckj.checks[_check].reg) || !cond) {
                        $(o).nextAll('p.tips:first').addClass('err').html(zckj.checks[_check].msg);
                        valid = false;
                    }
                    if (zckj.checks[_check].reg1 && $.trim(o.value) != $("input[name='" + zckj.checks[_check].reg1 + "']").val()) {
                        $(o).nextAll('p.tips:first').addClass('err').html(zckj.checks[_check].msg);
                        valid = false;
                    }
                }


                if (valid) {
                    $(o).nextAll('p.tips:first').removeClass('err').html('');
                }
            }
            name = $(o).attr("name");
            if (name) {
                switch (o.type) {
                    case "text":
                    case "hidden":
                    case "select-one":
                    case "password":
                        res[name] = $.trim(o.value);
                        break;
                    case "checkbox":
                        if (o.checked) {
                            res[name] = res[name] || [];
                            res[name].push(o.value);
                        }
                        break;
                    case "radio":
                        if (o.checked) {
                            res[name] = o.value;
                        }
                        break;
                    default:
                        break;

                }
            }
        })
        return valid == false ? valid : res;
    }
});