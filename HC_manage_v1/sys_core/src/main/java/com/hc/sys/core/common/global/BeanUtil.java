package com.hc.sys.core.common.global;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 工具类-spring bean
 * 
 * @author fy
 * @version 1.0.0.0
 * @since 2017年7月21日
 */
@Component
public class BeanUtil implements ApplicationContextAware {
	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext context)throws BeansException {
		BeanUtil.context = context;
//		System.out.println("---------------------------------------------------------------------");
//
//		System.out.println("========ApplicationContext配置成功,在普通类可以通过调用BeanUtil.getAppContext()获取applicationContext对象,applicationContext=========");
//
//		System.out.println("---------------------------------------------------------------------");
	}

	/**
	 * 获取ApplicationContext对象
	 */
	public static ApplicationContext getContext() {
		return context;
	}
	
	/**
	 * 获取实例,根据实例名称
	 * @param beanName	实例名称
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
		try {
			return (T) context.getBean(beanName);
		} catch (BeansException e) {
			return null;
		}
	}
	
	/**
	 * 获取实例,根据实例类Class
	 * @param clazz	实例类Class
	 */
	public static <T> T getBean(Class<T> clazz) {
		try {
			return (T) context.getBean(clazz);
		} catch (BeansException e) {
			return null;
		}
	}

	/**
	 * 判断容器中指定实例是否存在
	 * @param request	
	 * @param beanName	实例名称
	 */
	public static boolean containsBean(HttpServletRequest request, String beanName) {
		WebApplicationContext wct = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession().getServletContext());
		return wct.containsBean(beanName);
	}
	
	/**
	 * 判断容器中指定实例是否存在
	 * @param beanName	实例名称
	 */
	public static boolean containsBean(String beanName) {
		if(context == null)throw new RuntimeException("ApplicationContext对象未实例化.");
		return context.containsBean(beanName);
	}
}
