package com.hc.sys.core.manage.dao;
import com.hc.sys.common.dao.BaseDao;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.core.manage.entity.RoleMenu;
import com.hc.sys.core.manage.model.RoleMenuModel;

import java.util.List;

/**
 * @Description: 角色菜单
 * @Author: fangyong
 * @CreateDate: 2018/10/16 15:16
 * @Version: 1.0.0.0
 */
public interface RoleMenuDao extends BaseDao<RoleMenu> {

	/**
	 * 删除角色菜单关联表信息（物理删除）
	 * 
	 * @param roleId 角色ID
	 */
	void deleteByRoleId(long roleId);

	/**
	 * 列表
	 * @param model
	 * @return
	 */
	PageDataList<RoleMenu> list(RoleMenuModel model);
	/**
	 * 列表
	 * @param model
	 * @return
	 */
	List<RoleMenu> getMenuListByRoleId(RoleMenuModel model);
}