package com.hc.sys.core.manage.dao.impl;
import javax.persistence.Query;

import com.hc.sys.common.dao.jpa.BaseDaoImpl;
import com.hc.sys.common.model.jpa.OrderFilter;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.model.jpa.QueryParam;
import com.hc.sys.common.model.jpa.SearchFilter;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.manage.dao.RoleMenuDao;
import com.hc.sys.core.manage.entity.RoleMenu;
import com.hc.sys.core.manage.model.RoleMenuModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:角色-菜单
 * @Author: fangyong
 * @CreateDate: 2018/10/17 19:36
 * @Version: 1.0.0.0
 */
@Repository
public class RoleMenuDaoImpl extends BaseDaoImpl<RoleMenu> implements RoleMenuDao {

	/**
	 * 删除角色菜单关联表信息（物理删除）
	 * @param roleId 角色ID
	 */
	@Override
	public void deleteByRoleId(long roleId) {
		Query query = em.createNativeQuery("delete from role_menu where role_id=?1").setParameter(1, roleId);
		query.executeUpdate();
	}

	/**
	 * 列表
	 * @param model
	 * @return
	 */
	@Override
	public PageDataList<RoleMenu> list(RoleMenuModel model) {
		QueryParam param = QueryParam.getInstance();
		if(StringUtil.isNotBlank(model.getSearchName())){
			SearchFilter orFilter2 = new SearchFilter("name", SearchFilter.Operators.LIKE, model.getSearchName().trim());
			param.addOrFilter(orFilter2);
		}
		param.addOrder(OrderFilter.OrderType.ASC, "id");
		param.addPage(model.getPageNo(), model.getPageSize());
		return super.findPageList(param);
	}


	@Override
	public List<RoleMenu> getMenuListByRoleId(RoleMenuModel model) {
		QueryParam param = QueryParam.getInstance();
		if (model.getRoleId()>0) {
			param.addParam("role.id", model.getRoleId());
		}
		if (model.getStatus()!= 0) {
			param.addParam("menu.status", model.getStatus());
		}
		return this.findByCriteria(param);
	}
}