package com.hc.sys.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hc.sys.common.util.json.JsonUtil;
import com.hc.sys.common.util.json.JsonUtil;

/**
 * Web服务器响应输出数据工具类
 * @author fangyong
 * @version 1.0.0.0
 */

public class WebUtils {
    public static final String REQUEST_AJAX_HEADER = "X-Requested-With";
    public static final String XML_HTTP_REQUEST = "XMLHttpRequest";
    public static final String ISO_8859_1 = "ISO-8859-1";
    public static final String UTF_8 = "UTF-8";

    /**
     * 解码
     * @param v	待解码字符串
     * @param e	编码
     * @return	解码后字符串
     */
    public static String decode(String v, String e) {
        String fValue = "";
        try {
            fValue = java.net.URLDecoder.decode(v, e);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
            throw new RuntimeException(e1);
        }
        return fValue;
    }

    /**
     * 编码转换
     * @param str	字符串
     * @param oldEncode 原编码
     * @param newEncode 现编码
     * @return	编码后字符串
     */
    public static String charSet(String str, String oldEncode, String newEncode) {
        String s = "";
        try {
            s = new String(str.getBytes(newEncode), oldEncode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return s;
    }
    /**
     * 将字符串从ISO_8859_1编码成UTF_8
     * @param str	字符串
     * @return	编码后字符串
     */
    public static String charSet(String str) {
       return charSet(str, ISO_8859_1, UTF_8);
    }
    /**
     * 判断是否是Ajax请求
     * @param request	请求
     * @return	true or false
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return request.getHeader(REQUEST_AJAX_HEADER) != null && request.getHeader(REQUEST_AJAX_HEADER).equalsIgnoreCase(XML_HTTP_REQUEST);
    }
    /**
     * 输出字符串
     * 	response.setContentType("text/html; charset=UTF-8");
	 * @param s	要输出的数据
	 */
	public static void out(HttpServletResponse response, String s) {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(s);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
            if (out != null) {
            	out.flush();
            	out.close();
            }
        }
	}
    /**
     * 输出字符串
     * 	response.setContentType("application/json;charset=UTF-8");
     * @param s	字符串
     */
    public static void response(HttpServletResponse response, String s) {
		response.setContentType("application/json;charset=UTF-8");
    	response.setCharacterEncoding(UTF_8);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }
    /**
     * 输出json对象
     * @param o	json对象
     */
    public static void responseJson(HttpServletResponse response, Object o) {
        String s = JsonUtil.formatToStr(o);
        response(response, s);
    }
    /**
     * 字符串编码后输出
     * @param str		字符串
     * @param oldEncode	原编码
     * @param newEncode 现编码
     */
    public static void response(HttpServletResponse response, String str, String oldEncode, String newEncode) {
        response(response, charSet(str, oldEncode, newEncode));
    }
    /**
     * 字符串UTF-8编码后输出
     * @param str		字符串
     */
    public static void responseDf(HttpServletResponse response, String str) {
        response(response, charSet(str, ISO_8859_1, UTF_8));
    }
    /**
     * json对象编码后输出
     * @param o	json对象
     * @param oldEncode	原编码
     * @param newEncode 现编码
     */
    public static void responseJson(HttpServletResponse response, Object o, String oldEncode, String newEncode) {
        String s = JsonUtil.formatToStr(o);
        response(response, s, oldEncode, newEncode);
    }
    /**
     * json对象UTF-8编码后输出
     * @param o	json对象
     */
    public static void responseJsonDf(HttpServletResponse response, Object o) {
        String s = JsonUtil.formatToStr(o);
        responseDf(response, s);
    }
}
