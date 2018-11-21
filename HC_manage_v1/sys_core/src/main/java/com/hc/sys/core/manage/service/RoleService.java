package com.hc.sys.core.manage.service;
import com.hc.sys.core.manage.entity.Menu;
import com.hc.sys.core.manage.entity.Role;
import com.hc.sys.core.manage.model.RoleModel;
import com.hc.sys.common.form.Result;

import java.util.List;

/**
 * @Description: 角色
 * @Author: fangyong
 * @CreateDate: 2018/10/18 10:46
 * @Version: 1.0.0.0
 */
public interface RoleService{

	/**
 	 * 列表
 	 * @param model
 	 * @return
 	 */
	public Result list(RoleModel model);

	/**
 	 * 添加
 	 * @param model
 	 * @return
 	 */
	public Result add(RoleModel model);

	/**
 	 * 修改
 	 * @param model
 	 * @return
 	 */
	public Result update(RoleModel model);

	/**
 	 * 获取
 	 * @param model
 	 * @return
 	 */
	public Result getById(RoleModel model);
	/**
	 * 通过条件查询Role
	 * @param model
	 * @return
	 */
	Role getRoleBySearchName(RoleModel model);
	/**
	 * 通过角色ID查询所有菜单
	 * @param model
	 * @return
	 */
	Object getListMuneByRoleId(RoleModel model);
}