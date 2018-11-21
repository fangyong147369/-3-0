package com.hc.sys.core.common.web;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
/**
 * @Description:
 * @Author: fangyong
 * @CreateDate: 2018/10/23 10:35
 * @Version: 1.0.0.0
 */
public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    }
}