package com.hc.sys.core.manage.service;
import com.hc.sys.core.manage.entity.RoleMenu;
import com.hc.sys.core.manage.model.RoleMenuModel;
import com.hc.sys.core.manage.model.RoleMenuModel;
import com.hc.sys.common.form.Result;

import java.util.List;

/**
 * 角色-菜单
 * @Author: fangyong
 * @CreateDate: 2018/10/18 11:26
 * @Version: 1.0.0.0
 */
public interface RoleMenuService{

	/**
 	 * 列表
 	 * @param model
 	 * @return
 	 */
	public Result list(RoleMenuModel model);

	/**
 	 * 添加
 	 * @param model
 	 * @return
 	 */
	public Result add(RoleMenuModel model);

	/**
 	 * 修改
 	 * @param model
 	 * @return
 	 */
	public Result update(RoleMenuModel model);

	/**
 	 * 获取
 	 * @param model
 	 * @return
 	 */
	public Result getById(RoleMenuModel model);
	/**
	 * 列表
	 * @param model
	 * @return
	 */
	List<RoleMenu> getMenuListByRoleId(RoleMenuModel model);
}