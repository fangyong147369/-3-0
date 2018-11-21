package com.hc.sys.core.manage.dao;
import com.hc.sys.common.dao.BaseDao;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.core.manage.entity.Menu;
import com.hc.sys.core.manage.entity.Role;
import com.hc.sys.core.manage.model.RoleModel;

import java.util.List;

/**
 * @Description: 角色
 * @Author: fangyong
 * @CreateDate: 2018/10/15 19:16
 * @Version: 1.0.0.0
 */
public interface RoleDao extends BaseDao<Role> {

	/**
	 * 列表
	 * @param model
	 * @return
	 */
	PageDataList<Role> list(RoleModel model);
	 /**
	  * 通过条件查询Role
	  * @param model
	  * @return
	  */
	 Role getRoleBySearchName(RoleModel model);
}