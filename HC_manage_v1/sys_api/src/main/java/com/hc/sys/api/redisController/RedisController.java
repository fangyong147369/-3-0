package com.hc.sys.api.redisController;

import com.hc.sys.api.config.Init;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.core.manage.service.MenuService;
import com.hc.sys.core.material.service.FormulaByLabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description:菜单
 * @Author: fangyong
 * @CreateDate: 2018/10/18 10:25
 * @Version: 1.0.0.0
 */
@RestController
@RequestMapping("/jtt")
public class RedisController {
    @Resource
    FormulaByLabService formulaByLabService;
    @Resource
    MenuService menuService;
    @Autowired
    private Init init;
    int n = 100;
    static int m = 0;

    /**
     * @description 并发接口测试
     * @author: fangyong
     * @date 2018/11/17 9:34
     */
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public synchronized Object test() throws BusinessException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                n--;
                if (n == 99) {
                    System.out.println(this.getClass());
                    System.out.println("恭喜您中奖了");

                } else {
                    System.out.println("您与大奖插肩而过，继续加油呦！");
                }
            }


        }).start();
        return null;
    }



    /**
     * @description all
     * @author: fangyong
     * @date 2018/11/17 9:34
     */
    @RequestMapping(value = "/all", method = RequestMethod.POST)
    @ResponseBody
    public Object all() throws BusinessException {
        return formulaByLabService.all();
    }

    /**
     * @description 同步锁测试
     * @author: fangyong
     * @date 2018/11/17 9:34
     */
    @RequestMapping(value = "/init", method = RequestMethod.POST)
    @ResponseBody
    public Object init() throws BusinessException {
        // init.showInitData();
        return init.showInitmodelList();
    }

    /**
     * @description 批量保存菜单
     * @author: fangyong
     * @date 2018/11/17 9:34
     */
    @RequestMapping(value = "/saveMenuList", method = RequestMethod.POST)
    @ResponseBody
    public Object saveMenuList() throws BusinessException {
        return menuService.saveMenuList();
    }
}