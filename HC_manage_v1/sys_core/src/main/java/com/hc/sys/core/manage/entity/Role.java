package com.hc.sys.core.manage.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.hc.sys.common.entity.LongPKEntity;

/**
 * @Description: 角色
 * @Author: fangyong
 * @CreateDate: 2018/10/17 14:46
 * @Version: 1.0.0.0
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = "role")
public class Role extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;
    /**
     * 角色名
     **/
    private String name;
    /**
     * 状态：0-未启用；1-启用
     **/
    private int status;
    /**
     * 备注
     **/
    private String remark;
    /**
     * 获取权限
     **/
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private List<RoleMenu> roleMenus;


    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", state=" + status +
                ", remark='" + remark + '\'' +
                ", roleMenus=" + roleMenus +
                '}';
    }

    /**
     * 获取 角色名
     *
     * @return name 角色名
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置 角色名
     *
     * @param name 角色名
     */
    public void setName(String name) {
        this.name = name;
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
     * 获取 获取权限
     *
     * @return roleMenus 获取权限
     */
    public List<RoleMenu> getRoleMenus() {
        return this.roleMenus;
    }

    /**
     * 设置 获取权限
     *
     * @param roleMenus 获取权限
     */
    public void setRoleMenus(List<RoleMenu> roleMenus) {
        this.roleMenus = roleMenus;
    }

    /**
     * 获取 状态：0-未启用；1-启用
     *
     * @return status 状态：0-未启用；1-启用
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * 设置 状态：0-未启用；1-启用
     *
     * @param status 状态：0-未启用；1-启用
     */
    public void setStatus(int status) {
        this.status = status;
    }
}
