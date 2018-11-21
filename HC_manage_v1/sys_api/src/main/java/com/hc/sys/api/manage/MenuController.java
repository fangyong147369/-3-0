package com.hc.sys.api.manage;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.core.common.web.BaseController;
import com.hc.sys.core.manage.model.MenuModel;
import com.hc.sys.core.manage.service.MenuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
* @Description:菜单
* @Author: fangyong
* @CreateDate: 2018/10/18 10:25
* @Version: 1.0.0.0
*/
@RestController
@RequestMapping("/manage/menu")
public class MenuController extends BaseController<MenuModel> {

   @Resource
   MenuService menuService;
   /**
     * 列表
     * @param model
     * @return
     */
   @RequestMapping(value = "/list", method = RequestMethod.POST)
   @ResponseBody
   public Object  list(MenuModel model) throws BusinessException {
      return menuService.list(model);
   }

   /**
     * 添加
     * @param model
     * @return
     */
   @RequestMapping(value = "/add", method = RequestMethod.POST)
   @ResponseBody
   public Object add(MenuModel model) throws BusinessException {
       return menuService.add(model);
   }

   /**
     * 修改
     * @param model
     * @return
     */
   @RequestMapping(value = "/update", method = RequestMethod.POST)
   @ResponseBody
   public Object update(MenuModel model) throws BusinessException {
       return menuService.update(model);
   }

   /**
     * 获取
     * @param model
     * @return
     */
   @RequestMapping(value = "/getById", method = RequestMethod.POST)
   @ResponseBody
   public Object getById(MenuModel model) throws BusinessException {
       return menuService.getById(model);
   }

}