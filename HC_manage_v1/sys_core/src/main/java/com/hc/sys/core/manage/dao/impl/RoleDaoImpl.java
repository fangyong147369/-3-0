package com.hc.sys.core.manage.dao.impl;
import com.hc.sys.common.util.BeanUtilCommon;
import com.hc.sys.core.manage.dao.MenuDao;
import com.hc.sys.core.manage.dao.RoleDao;
import com.hc.sys.core.manage.dao.RoleMenuDao;
import com.hc.sys.core.manage.entity.Menu;
import com.hc.sys.core.manage.entity.RoleMenu;
import com.hc.sys.core.manage.model.RoleModel;
import org.springframework.stereotype.Repository;

import com.hc.sys.common.dao.jpa.BaseDaoImpl;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.model.jpa.QueryParam;
import com.hc.sys.common.model.jpa.SearchFilter;
import com.hc.sys.common.model.jpa.OrderFilter.OrderType;
import com.hc.sys.common.model.jpa.SearchFilter.Operators;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.manage.entity.Role;
import com.hc.sys.core.manage.model.RoleModel;
import com.hc.sys.core.manage.dao.RoleDao;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:角色
 * @Author: fangyong
 * @CreateDate: 2018/10/17 20:16
 * @Version: 1.0.0.0
 */
@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	
	/**
	 * 列表
	 * @param model
	 * @return
	 */
	@Override
	public PageDataList<Role> list(RoleModel model) {
		QueryParam param = QueryParam.getInstance();
		if(StringUtil.isNotBlank(model.getSearchName())){
			param.addParam("name", Operators.LIKE, model.getSearchName().trim());
		}
		if(model.getStatus()!=0){
			param.addParam("status", model.getStatus());
		}
		param.addOrder(OrderType.ASC, "id");
		param.addPage(model.getPageNo(), model.getPageSize());
		return super.findPageList(param);
	}
	 /**
	  * 通过条件查询Role
	  * @param model
	  * @return
	  */
	 @Override
	 public Role getRoleBySearchName(RoleModel model) {
		 Role role=null;
		 if(StringUtil.isNotBlank(model.getSearchName())){
			 role=super.findObjByProperty("name", model.getSearchName().trim());
		 }
		 return role;
	 }


}