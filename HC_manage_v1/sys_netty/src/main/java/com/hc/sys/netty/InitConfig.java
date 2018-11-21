package com.hc.sys.netty;

import com.hc.sys.common.util.log.LogUtil;
import com.hc.sys.netty.nettyService.FormulaInputService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Description: 描述
 * @Author: fangyong
 * @CreateDate: 2018/10/18 18:37
 * @Version: 1.0.0.0
 */
@Component
public class InitConfig implements CommandLineRunner {
   @Value("${netty.port}")
    private int port =0;

    @Override
    public void run(String... var1) throws Exception {
        if (port <= 0) {
            LogUtil.error("------若要使用netty服务！请先配置正确的服务端口-------");
        } else {
            FormulaInputService formulaInputService = FormulaInputService.getInstance();
            formulaInputService.setM_nettyPort(port);
            formulaInputService.create();
        }
    }
}
