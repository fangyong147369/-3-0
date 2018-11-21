package com.hc.sys.core.manage.service;
import com.hc.sys.common.form.Result;
import com.hc.sys.core.manage.model.MenuModel;

/**
 * @Description: 菜单
 * @Author: fangyong
 * @CreateDate: 2018/10/15 19:26
 * @Version: 1.0.0.0
 */
public interface MenuService {

	/**
 	 * 列表
 	 * @param model
 	 * @return
 	 */
	public Result list(MenuModel model);

	/**
 	 * 添加
 	 * @param model
 	 * @return
 	 */
	public Result add(MenuModel model);

	/**
 	 * 修改
 	 * @param model
 	 * @return
 	 */
	public Result update(MenuModel model);

	/**
 	 * 获取
 	 * @param model
 	 * @return
 	 */
	public Result getById(MenuModel model);
	/**
	 * @description 批量保存菜单
	 * @author: fangyong
	 * @date 2018/11/21 9:19
	 */
	Result saveMenuList();
}