package com.hc.sys.core.manage.model;
import org.springframework.beans.BeanUtils;
import com.hc.sys.common.model.jpa.Page;
import com.hc.sys.core.manage.entity.RoleMenu;

/**
 * @Description: 角色菜单
 * @Author: fangyong
 * @CreateDate: 2018/10/16 13:46
 * @Version: 1.0.0.0
 */
public class RoleMenuModel extends RoleMenu {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     * 当前页码
     **/
    private int pageNo;
    /**
     * 每页数据条数
     **/
    private int pageSize = Page.ROWS;
    /**
     * 条件查询
     **/
    private String searchName;
    /**
     * 角色model
     **/
    private RoleModel roleModel;
    /**
     * 菜单model
     **/
    private MenuModel menuModel;
    /** 状态-1-未启用；1-启用 **/
    private int status;
    /** 角色Id **/
    private long roleId;
    /**
     * 实体转换model
     */
    public static RoleMenuModel instance(RoleMenu roleMenu) {
        RoleMenuModel roleMenuModel = new RoleMenuModel();
        BeanUtils.copyProperties(roleMenu, roleMenuModel);
        return roleMenuModel;
    }

    /**
     * model转换实体
     */
    public RoleMenu prototype() {
        RoleMenu roleMenu = new RoleMenu();
        BeanUtils.copyProperties(this, roleMenu);
        return roleMenu;
    }

    /**
     * 获取【当前页码】
     **/
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 设置【当前页码】
     **/
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 获取【每页数据条数】
     **/
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置【每页数据条数】
     **/
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取【searchName】
     **/
    public String getSearchName() {
        return searchName;
    }

    /**
     * 设置【searchName】
     **/
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }


    /**
     * 获取 角色model
     *
     * @return roleModel 角色model
     */
    public RoleModel getRoleModel() {
        return this.roleModel;
    }

    /**
     * 设置 角色model
     *
     * @param roleModel 角色model
     */
    public void setRoleModel(RoleModel roleModel) {
        this.roleModel = roleModel;
    }

    /**
     * 获取 菜单model
     *
     * @return menuModel 菜单model
     */
    public MenuModel getMenuModel() {
        return this.menuModel;
    }

    /**
     * 设置 菜单model
     *
     * @param menuModel 菜单model
     */
    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }


    /**
     * 获取 角色Id
     *
     * @return roleId 角色Id
     */
    public long getRoleId() {
        return this.roleId;
    }

    /**
     * 设置 角色Id
     *
     * @param roleId 角色Id
     */
    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取 状态0-未启用；1-启用
     *
     * @return status 状态0-未启用；1-启用
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * 设置 状态0-未启用；1-启用
     *
     * @param status 状态0-未启用；1-启用
     */
    public void setStatus(int status) {
        this.status = status;
    }
}