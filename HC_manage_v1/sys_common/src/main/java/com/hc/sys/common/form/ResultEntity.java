package com.hc.sys.common.form;
/**
 * 通用返回结果对象
 */
public class ResultEntity<T> {
    public static final int SUCCESS = 200;
    public static final int LOADING = 300;
    public static final int FAIL = 400;
    //未注册
    public static final int NO_REGISTER = 2001;
    //手机未认证
    public static final int NO_MOBILE_AUTH = 201;
    //邮箱未认证
    public static final int NO_EMAIL_AUTH = 202;
    //实名未认证
    public static final int NO_REALNAME_AUTH = 203;
    //谷歌验证器未绑定
    public static final int NO_GOOGLE_BIND = 204;

    private int code;
    private String message = "";
    private String url = "";
    private T data;
    private String uuid;

    public static ResultEntity success() {
        ResultEntity restResult = new ResultEntity();
        restResult.code = SUCCESS;
        restResult.message = "操作成功";
        return restResult;
    }

    public static ResultEntity success(String msg) {
        ResultEntity restResult = new ResultEntity();
        restResult.code = ResultEntity.SUCCESS;
        restResult.message = msg;
        return restResult;
    }

    public static ResultEntity success(String msg, Object data) {
        ResultEntity restResult = new ResultEntity();
        restResult.code = ResultEntity.SUCCESS;
        restResult.message = msg;
        restResult.data = data;
        return restResult;
    }

    public static ResultEntity fail() {
        ResultEntity restResult = new ResultEntity();
        restResult.code = FAIL;
        restResult.message = "操作失败";
        return restResult;
    }

    public static ResultEntity fail(String msg) {
        ResultEntity restResult = new ResultEntity();
        restResult.code = FAIL;
        restResult.message = msg;
        return restResult;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
