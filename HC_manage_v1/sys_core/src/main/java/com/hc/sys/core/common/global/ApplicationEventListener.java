package com.hc.sys.core.common.global;
import com.hc.sys.common.util.log.LogUtil;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;
/**
 * 初始化配置
 * 
 * @author fy
 * @version 2.0.0.0
 * @since 2017年11月6日
 */
@Component
public class ApplicationEventListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
//        // 在这里可以监听到Spring Boot的生命周期
//        if (event instanceof ApplicationEnvironmentPreparedEvent) {
//            LogUtil.info("初始化环境变量");
//        }
//        else if (event instanceof ApplicationPreparedEvent) {
//            LogUtil.info("初始化完成");
//        }
//        else if (event instanceof ContextRefreshedEvent) {
//            LogUtil.info("应用刷新");
//        }
//        else if (event instanceof ApplicationReadyEvent) {
//            LogUtil.info("应用已启动完成");
//        }
//        else if (event instanceof ContextStartedEvent) {
//            LogUtil.info("应用启动，需要在代码动态添加监听器才可捕获");
//        }
//        else if (event instanceof ContextStoppedEvent) {
//            LogUtil.info("应用停止");
//        }
//        else if (event instanceof ContextClosedEvent) {
//            LogUtil.info(" 应用关闭");
//        }
//        else {LogUtil.info(" 其它-----");}
    }
}
