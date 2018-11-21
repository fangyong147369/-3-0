package com.hc.sys.core.manage.model;
import com.hc.sys.core.manage.entity.Operator;
import org.springframework.beans.BeanUtils;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.common.model.jpa.Page;
import com.hc.sys.common.util.date.DateUtil;
import com.hc.sys.common.util.encrypt.MD5;
import com.hc.sys.common.util.validate.StringUtil;
/**
 * @Description:管理员
 * @Author: fangyong
 * @CreateDate: 2018/10/17 20:46
 * @Version: 1.0.0.0
 */
public class OperatorModel extends Operator {
	/** 序列号 **/
	private static final long serialVersionUID = 1L;

	/** 当前页码 **/
	private int pageNo;
	/** 每页数据条数 **/
	private int pageSize = Page.ROWS;
	/** 条件查询 **/
	private String searchName;
	/** 角色Model **/
	private RoleModel roleModel;
	/** 角色roleId **/
	private long roleId;
	/** 密码操作方式（1.登录 2.修改） **/
	private int pwdWay;
	/**登录方式方式（1.p登录 2.pc登录,3.moblie） **/
	private int signInWay;
	/**
	 * 实体转换model
	 */
	public static OperatorModel instance(Operator operator) {
		if(operator==null||operator.getId()<0){
			return null;
		}
		OperatorModel operatorModel = new OperatorModel();
		BeanUtils.copyProperties(operator, operatorModel);
		return operatorModel;
	}

	/**
	 * model转换实体
	 */
	public Operator prototype() {
		Operator operator = new Operator();
		BeanUtils.copyProperties(this, operator);
		return operator;
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
	 * 获取 条件查询
	 *
	 * @return searchName 条件查询
	 */
	public String getSearchName() {
		return this.searchName;
	}

	/**
	 * 设置 条件查询
	 *
	 * @param searchName 条件查询
	 */
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	/**
	 * 获取 角色Model
	 *
	 * @return roleModel 角色Model
	 */
	public RoleModel getRoleModel() {
		return this.roleModel;
	}

	/**
	 * 设置 角色Model
	 *
	 * @param roleModel 角色Model
	 */
	public void setRoleModel(RoleModel roleModel) {
		this.roleModel = roleModel;
	}

	/**
	 * 获取 角色roleId
	 *
	 * @return roleId 角色roleId
	 */
	public long getRoleId() {
		return this.roleId;
	}

	/**
	 * 设置 角色roleId
	 *
	 * @param roleId 角色roleId
	 */
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	/**
	 * 获取 密码操作方式（1.登录 2.修改）
	 *
	 * @return pwdWay 密码操作方式（1.登录 2.修改）
	 */
	public int getPwdWay() {
		return this.pwdWay;
	}

	/**
	 * 设置 密码操作方式（1.登录 2.修改）
	 *
	 * @param pwdWay 密码操作方式（1.登录 2.修改）
	 */
	public void setPwdWay(int pwdWay) {
		this.pwdWay = pwdWay;
	}

	/**
	 * 获取 登录方式方式（1.p登录 2.pc登录3.moblie）
	 *
	 * @return signInWay 登录方式方式（1.p登录 2.pc登录3.moblie）
	 */
	public int getSignInWay() {
		return this.signInWay;
	}

	/**
	 * 设置 登录方式方式（1.p登录 2.pc登录3.moblie）
	 *
	 * @param signInWay 登录方式方式（1.p登录 2.pc登录3.moblie）
	 */
	public void setSignInWay(int signInWay) {
		this.signInWay = signInWay;
	}
}