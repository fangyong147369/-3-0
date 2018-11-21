package com.hc.sys.core.manage.model;
import org.springframework.beans.BeanUtils;
import com.hc.sys.common.model.jpa.Page;
import com.hc.sys.core.manage.entity.Role;
/**
 * @Description: 角色
 * @Author: fangyong
 * @CreateDate: 2018/10/18 9:26
 * @Version: 1.0.0.0
 */
public class RoleModel extends Role {
	/** 序列号 **/
	private static final long serialVersionUID = 1L;

	/** 当前页码 **/
	private int pageNo;
	/** 每页数据条数 **/
	private int pageSize = Page.ROWS;
	/** Model **/
	private String searchName;
	
	/** 角色菜单 **/
	private String menuIdStr;
	/**
	 * 实体转换model
	 */
	public static RoleModel instance(Role role) {
		RoleModel roleModel = new RoleModel();
		BeanUtils.copyProperties(role, roleModel);
		return roleModel;
	}

	/**
	 * model转换实体
	 */
	public Role prototype() {
		Role role = new Role();
		BeanUtils.copyProperties(this, role);
		return role;
	}
	
	/**
	 * 设置管理员角色修改基本参数
	 * @param role
	 */
	public void setUpdateParam(Role role) {
		role.setName(this.getName());
		role.setRemark(this.getRemark());
		role.setStatus(this.getStatus());
	}


	/**
	 * 获取 当前页码
	 *
	 * @return pageNo 当前页码
	 */
	public int getPageNo() {
		return this.pageNo;
	}

	/**
	 * 设置 当前页码
	 *
	 * @param pageNo 当前页码
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * 获取 每页数据条数
	 *
	 * @return pageSize 每页数据条数
	 */
	public int getPageSize() {
		return this.pageSize;
	}

	/**
	 * 设置 每页数据条数
	 *
	 * @param pageSize 每页数据条数
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取 Model
	 *
	 * @return searchName Model
	 */
	public String getSearchName() {
		return this.searchName;
	}

	/**
	 * 设置 Model
	 *
	 * @param searchName Model
	 */
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	/**
	 * 获取 角色菜单
	 *
	 * @return menuIdStr 角色菜单
	 */
	public String getMenuIdStr() {
		return this.menuIdStr;
	}

	/**
	 * 设置 角色菜单
	 *
	 * @param menuIdStr 角色菜单
	 */
	public void setMenuIdStr(String menuIdStr) {
		this.menuIdStr = menuIdStr;
	}
}