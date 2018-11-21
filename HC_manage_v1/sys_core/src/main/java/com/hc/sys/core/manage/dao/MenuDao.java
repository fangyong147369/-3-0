package com.hc.sys.core.manage.dao;
import com.hc.sys.common.dao.BaseDao;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.core.manage.entity.Menu;
import com.hc.sys.core.manage.model.MenuModel;

/**
 * @Description: 菜单
 * @Author: fangyong
 * @CreateDate: 2018/10/18 17:10
 * @Version: 1.0.0.0
 */
public interface MenuDao extends BaseDao<Menu> {

	/**
	 * 列表
	 * @param model
	 * @return
	 */
	PageDataList<Menu> list(MenuModel model);
	/**
	 * @description 批量保存菜单
	 * @author: fangyong
	 * @date 2018/11/21 9:19
	 */
	Result saveMenuList();
}