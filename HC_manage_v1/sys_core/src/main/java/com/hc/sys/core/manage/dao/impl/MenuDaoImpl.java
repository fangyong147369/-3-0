package com.hc.sys.core.manage.dao.impl;

import com.hc.sys.common.dao.jpa.BaseDaoImpl;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.OrderFilter.OrderType;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.model.jpa.QueryParam;
import com.hc.sys.common.model.jpa.SearchFilter;
import com.hc.sys.common.model.jpa.SearchFilter.Operators;
import com.hc.sys.common.util.log.LogUtil;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.manage.dao.MenuDao;
import com.hc.sys.core.manage.entity.Menu;
import com.hc.sys.core.manage.model.MenuModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
* @Description: 菜单
* @Author: fangyong
* @CreateDate: 2018/10/18 17:15
* @Version: 1.0.0.0
*/
@Repository
public class MenuDaoImpl extends BaseDaoImpl<Menu> implements MenuDao {

   /**
    * 列表
    * @param model
    * @return
    */
   @Override
   public PageDataList<Menu> list(MenuModel model) {
       QueryParam param = QueryParam.getInstance();
       if(StringUtil.isNotBlank(model.getSearchName())){
           param.addParam("name",Operators.LIKE, model.getSearchName().trim());
       }
       if(model.getStatus()!=0){
           param.addParam("status", model.getStatus());
       }
       param.addOrder(OrderType.ASC, "id");
       param.addPage(model.getPageNo(), model.getPageSize());
       return super.findPageList(param);
   }
/**
 * @description 批量保存菜单
 * @author: fangyong
 * @date 2018/11/21 9:19
 */
    @Override
    public Result saveMenuList() {
        List<Menu> menuList = new ArrayList<Menu>();
        List<Menu> menus = super.findAll();
        for (Menu menu : menus) {
            Menu m = new Menu();
            m.setName(menu.getName());
            m.setHref(menu.getHref());
            m.setParentId(menu.getParentId());
            m.setRemark(menu.getRemark());
            m.setStatus(menu.getStatus());
            menuList.add(m);
        }
        try {
            super.save(menuList);
          return   Result.success();
        } catch (Exception e) {
            LogUtil.error(e.getMessage());
            return   Result.error(e.getMessage());
        }

    }

}