package com.hc.sys.common.exception;
/**
 * 异常处理
 * @author fangyong
 * @version 1.0.0.0
 * @since 2017年6月28日
 */
public class NoRollBackException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public static final int TYPE_JSON = 1;
	public static final int TYPE_CLOSE = 2;
	protected String url;
	protected int type;
	protected String buttonName;

	public NoRollBackException(String msg, RuntimeException ex) {
		super(msg, ex);
	}

	public NoRollBackException() {
		super();
	}

	public NoRollBackException(String message) {
		super(message);
	}

	public NoRollBackException(String message, String url) {
		super(message);
		this.url = url;
	}

	public NoRollBackException(String message, String url,String buttonName) {
		super(message);
		this.url = url;
		this.buttonName = buttonName;
	}
	
	public NoRollBackException(String message, int type) {
		super(message);
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
