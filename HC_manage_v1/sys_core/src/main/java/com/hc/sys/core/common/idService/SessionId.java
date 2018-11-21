package com.hc.sys.core.common.idService;

/**
 * sessionId key
 */
public interface SessionId {



    /** cookie userCookie过期串 */
    String COOKIE_SESSIONID = "HUIMI_SESSIONID";
    /** cookie userCookie过期串 */
    String COOKIE_USERID = "HUIMI_USERID";
    String COOKIE_UUID = "user-uuid";

    /** 用户登陆后各种短信的前缀*/
    String MEMBER_MSG = "HUIMI:MEMBER:MSG:";
    /** 用户注册短信 */
    String MEMBER_REG_MSG = "REG:";
    /** 用户忘记密码短信 */
    String RESET_PWD = "RESET_PWD:";
    /** 用户修改支付密码短信 */
    String RESET_PAY_PWD = "RESET_PAY_PWD:";
    /** 修改手机号短信,验证手机号 */
    String RESET_PHONE = "RESET_PHONE:";
    /** 修改手机号 新手机号发送短信 */
    String RESET_PHONE_NEW = "RESET_PHONENEW:";
    /** 短信登录 */
    String MSG_LOGIN = "MSG_LOGIN:";
    /** 绑定银行卡 */
    String BINDING_CARD = "BANDING_CARD:";


    /** 登录用户 */
    String MEMBER_USER="USER";
    /** 图片验证码 */
    String CAPTCHA = "captcha";
}
