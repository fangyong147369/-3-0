package com.hc.sys.common.form;

import com.hc.sys.common.util.BeanUtilCommon;
import com.hc.sys.common.util.encrypt.AES256Util;
import com.hc.sys.common.util.json.JsonUtil;
import com.hc.sys.common.util.BeanUtilCommon;
import com.hc.sys.common.util.encrypt.AES256Util;
import com.hc.sys.common.util.json.JsonUtil;
import com.hc.sys.common.util.validate.StringUtil;
import org.springframework.beans.factory.annotation.Value;

/**
 * 请求返回结果
 * @author zp
 * @version 1.0.0.0
 * @update-author 2018年3月29日
 */
public class Result {
	/** 执行成功 **/
	public static final int SUCCESS = 1;
	public static final String SUCCESS_MSG = "操作成功";
	/** 执行失败 **/
	public static final int FAILURE = -1;
	public static final String ERROR_MSG = "操作失败";

	/** 执行结果 1: 成功 -1: 失败 其它为自定义的错误代码 **/
	private int code;
	/** 返回对象数据是否被加密 **/
	private boolean secure = false;
	/** 提示信息 **/
	private String message;
	/** 返回对象 **/
	private Object data;
	/** 构造函数 **/
	public Result() {
	}

	/**
	 * 实例化执行成功的AjaxResult对象
	 */
	public static Result success() {
		return Result.success(SUCCESS_MSG);
	}

	/**
	 * 实例化执行成功的AjaxResult对象
	 */
	public static Result success(String message) {
		return new Result(true, message);
	}

	/**
	 * 实例化执行失败的AjaxResult对象
	 */
	public static Result error() {
		return Result.error(ERROR_MSG);
	}

	/**
	 * 实例化执行失败的AjaxResult对象
	 */
	public static Result error(String message) {
		return new Result(false, message);
	}

	public Result(boolean result, String message) {
		this.setResult(result);
		this.message = message;
	}

	public Result(boolean result, String message, Object data) {
		this.setResult(result);
		this.message = message;
		this.data = data;
	}
	public Result(boolean result, String message,boolean secure, Object data) {
		this.setResult(result);
		this.message = message;
		this.data = data;
		this.secure = secure;
	}

	/**
	 * 是否加密
	 * @param secure
	 */
	public Result(boolean secure) {
		this.secure = secure;
	}

	/**
	 * 设置执行结果
	 * 
	 * @param
	 *
	 */
	public void setResult(boolean result) {
		if (result)
			this.code = SUCCESS;
		else
			this.code = FAILURE;
	}

	/** 获取【执行结果1:成功-1:失败其它为自定义的错误代码】 **/
	public int getCode() {
		return code;
	}

	/** 设置【执行结果1:成功-1:失败其它为自定义的错误代码】 **/
	public void setCode(int code) {
		this.code = code;
	}

	/** 获取【返回对象数据是否被加密】 **/
	public boolean isSecure() {
		return secure;
	}

	/** 设置【返回对象数据是否被加密】 **/
	public void setSecure(boolean secure) {
		this.secure = secure;
	}

	/** 获取【提示信息】 **/
	public String getMessage() {
		return message;
	}

	/** 设置【提示信息】 **/
	public void setMessage(String message) {
		this.message = message;
	}

	/** 获取【返回对象】 **/
	public Object getData() {
		// TODO 对AjaxResult返回的data数据进行AES256加密
		AES256Util aes = BeanUtilCommon.getBean(AES256Util.class);
		if (secure && data != null) {
			if (data instanceof String) {
				String jsonData = aes.encrypt(data.toString());
				return jsonData;
			} else {
				String jsonData = JsonUtil.formatToStr(data);
				jsonData = aes.encrypt(jsonData);
				return jsonData;
			}
		}
		return data == null ? "":data;
	}

	/** 设置【返回对象】 **/
	public Result setData(Object data) {
		this.data = data;
		return this;
	}

}
