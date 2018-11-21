package com.hc.sys.core.common.web;
import org.springframework.web.bind.annotation.ModelAttribute;
/**
 * Controller基类，所有Controller都必须继承该类
 *  
 * @author fy
 * @version 1.0.0.0
 * @since 2018年7月6日
 */
public abstract class BaseController<T>{
	
	@ModelAttribute
	public void init(T model){
	}
}