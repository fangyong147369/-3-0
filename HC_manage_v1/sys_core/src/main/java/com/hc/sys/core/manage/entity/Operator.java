package com.hc.sys.core.manage.entity;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hc.sys.common.entity.LongPKEntity;
/**
 * 管理员
 /**
 * @Description: 菜单
 * @Author: fangyong
 * @CreateDate: 2018/10/15 10:46
 * @Version: 1.0.0.0
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name ="operator")
public class Operator extends LongPKEntity {

	/** 序列号 **/
	private static final long serialVersionUID = 1L;
	/** 用户名 **/
	private String userName;
	/** sessionID **/
	private String sessionID;
	/** 登录密码 **/
	private String password;
	/** 手机 **/
	private String mobile;
	/** 邮箱 **/
	private String email;
	/** 状态：1-正常, -1-禁用 **/
	private int status;
	/** 角色主键 **/
	@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private Role role;
	/** 添加时间 **/
	private Date addTime;
	/** 最后登录IP **/
	private String loginIp;
	/** 最后登录时间 **/
	private Date loginTime;
	/** 添加者 **/
	private String addManager;
	/** 修改时间 **/
	private Date updateTime;
	/** 备注 **/
	private String remark;
	/**
	 * 获取 用户名
	 *
	 * @return userName 用户名
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * 设置 用户名
	 *
	 * @param userName 用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 获取 登录密码
	 *
	 * @return password 登录密码
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * 设置 登录密码
	 *
	 * @param password 登录密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取 手机
	 *
	 * @return mobile 手机
	 */
	public String getMobile() {
		return this.mobile;
	}

	/**
	 * 设置 手机
	 *
	 * @param mobile 手机
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获取 邮箱
	 *
	 * @return email 邮箱
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * 设置 邮箱
	 *
	 * @param email 邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}



	/**
	 * 获取 角色主键
	 *
	 * @return role 角色主键
	 */
	public Role getRole() {
		return this.role;
	}

	/**
	 * 设置 角色主键
	 *
	 * @param role 角色主键
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * 获取 添加时间
	 *
	 * @return addTime 添加时间
	 */
	public Date getAddTime() {
		return this.addTime;
	}

	/**
	 * 设置 添加时间
	 *
	 * @param addTime 添加时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	/**
	 * 获取 最后登录IP
	 *
	 * @return loginIp 最后登录IP
	 */
	public String getLoginIp() {
		return this.loginIp;
	}

	/**
	 * 设置 最后登录IP
	 *
	 * @param loginIp 最后登录IP
	 */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	/**
	 * 获取 最后登录时间
	 *
	 * @return loginTime 最后登录时间
	 */
	public Date getLoginTime() {
		return this.loginTime;
	}

	/**
	 * 设置 最后登录时间
	 *
	 * @param loginTime 最后登录时间
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	/**
	 * 获取 添加者
	 *
	 * @return addManager 添加者
	 */
	public String getAddManager() {
		return this.addManager;
	}

	/**
	 * 设置 添加者
	 *
	 * @param addManager 添加者
	 */
	public void setAddManager(String addManager) {
		this.addManager = addManager;
	}

	/**
	 * 获取 修改时间
	 *
	 * @return updateTime 修改时间
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 设置 修改时间
	 *
	 * @param updateTime 修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取 备注
	 *
	 * @return remark 备注
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 设置 备注
	 *
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取 状态：1-正常 -1-禁用
	 *
	 * @return status 状态：1-正常 -1-禁用
	 */
	public int getStatus() {
		return this.status;
	}

	/**
	 * 设置 状态：1-正常 -1-禁用
	 *
	 * @param status 状态：1-正常 -1-禁用
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 获取 sessionID
	 *
	 * @return sessionID sessionID
	 */
	public String getSessionID() {
		return this.sessionID;
	}

	/**
	 * 设置 sessionID
	 *
	 * @param sessionID sessionID
	 */
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

}
