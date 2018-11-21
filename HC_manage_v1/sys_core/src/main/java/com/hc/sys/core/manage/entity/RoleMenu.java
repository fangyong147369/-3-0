package com.hc.sys.core.manage.entity;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hc.sys.common.entity.LongPKEntity;
 /**
 * @Description: 角色菜单
 * @Author: fangyong
 * @CreateDate: 2018/10/16 13:46
 * @Version: 1.0.0.0
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = "role_menu")
public class RoleMenu extends LongPKEntity {
	/** 序列号 **/
	private static final long serialVersionUID = 1L;
	 /**
	  * 角色主键
	  */
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private Role role;

	/**
	 * 菜单主键
	 */
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id")
	private Menu menu;




	 /**
	  * 获取 菜单主键
	  *
	  * @return menu 菜单主键
	  */
	 public Menu getMenu() {
		 return this.menu;
	 }

	 /**
	  * 设置 菜单主键
	  *
	  * @param menu 菜单主键
	  */
	 public void setMenu(Menu menu) {
		 this.menu = menu;
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
 }
