package com.hc.sys.api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * @Description: 主程序入口
 * @Author: fangyong
 * @CreateDate: 2018/10/17 20:02
 * @Version: 1.0.0.0
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableAutoConfiguration
@ImportResource("classpath:applicationConfig.xml")
public class SpringbootApplicatin extends SpringBootServletInitializer {
    public static void main(String[] args) {
        new SpringApplication(SpringbootApplicatin.class).run(args);

    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(new Class[]{SpringbootApplicatin.class});
    }

}
