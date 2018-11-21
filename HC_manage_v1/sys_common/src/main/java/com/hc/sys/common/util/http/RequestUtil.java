package com.hc.sys.common.util.http;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * RequestUtil
 * @author zp
 * @version 2.0.0.0
 * @since 2017年11月13日
 */
public class RequestUtil {
	
	private static String WebappsDir = null;
	
	/**
	 * SpringMvc下获取request
	 * 
	 */
	public static HttpServletRequest getRequest() {
		try {
			return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * SpringMvc下获取session
	 * 
	 */
	public static HttpSession getSession() {
		return getRequest().getSession();
	}
	
	/**
	 * SpringMvc下获取requestServer
	 * 
	 * @return	request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
	 */
	public static String getRequestServer(){
		HttpServletRequest request = RequestUtil.getRequest();
		String requestServer = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
		return requestServer + request.getContextPath();
	}
	
	/**
	 * SpringMvc下获取Server
	 * 
	 * @return	request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
	 */
	public static String getServer(){
		HttpServletRequest request = RequestUtil.getRequest();
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
	}
	
	/**
	 * 获取webapps路径
	 * @param request
	 */
	public static String getWebappsDir(HttpServletRequest request) {
		String currAppDir = request.getRealPath("/").replace("\\", "/");
		currAppDir = currAppDir.substring(0, currAppDir.length() - 1);
		return currAppDir.substring(0, currAppDir.lastIndexOf("/"));
	}
	
	/**
	 * 获取webapps路径
	 */
	public static String getWebappsDir() {
		if(WebappsDir != null){
			return WebappsDir;
		}
		return getWebappsDir(getRequest());
	}
	
	public static void setWebappsDir(ServletContext servletContext){
		String currAppDir = servletContext.getRealPath("/").replace("\\", "/");
		currAppDir = currAppDir.substring(0, currAppDir.length() - 1);
		WebappsDir = currAppDir.substring(0, currAppDir.lastIndexOf("/"));
	}
	
	/**
	 * 获取客户端IP地址（可能有多个，如：192.168.1.2,192.168.1.10）
	 * @param request
	 */
	public static String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if("0:0:0:0:0:0:0:1".equals(ip) || "127.0.0.1".equals(ip)){
			try {
				ip = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				ip = "127.0.0.1";
			}
		}
		return ip;
	}
	
	/**
	 * 获取客户端IP地址（可能有多个，如：192.168.1.2,192.168.1.10）
	 */
	public static String getClientIp(){
		return getClientIP(getRequest());
	}
}
