package com.hc.sys.common.util.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * log4j工具类
 * @author fangyong
 * @version 1.0.0.0
 * @since 2017年6月29日
 */
public class LogUtil {

	private static Logger log = LoggerFactory.getLogger("HCKJ-MS");
	private LogUtil() {
	}

	/**
	 * 调试
	 * @param msg
	 */
	public static void debug(String msg) {
		log.debug(msg);
	}

	/**
	 * 信息
	 * @param msg
	 */
	public static void info(String msg) {
		log.info(msg);
	}

	/**
	 * 警告
	 * @param msg
	 */
	public static void warn(String msg) {
		log.warn(msg);
	}

	/**
	 * 错误
	 * @param msg
	 */
	public static void error(String msg) {
		log.error(msg);
	}
	
	/**
	 * 错误
	 * @param msg
	 */
	public static void error(String msg,Throwable t) {
		log.error(msg, t);
	}

}
