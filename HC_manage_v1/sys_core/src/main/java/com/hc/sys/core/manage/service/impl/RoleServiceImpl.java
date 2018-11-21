package com.hc.sys.core.manage.service.impl;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import com.hc.sys.common.model.jpa.QueryParam;
import com.hc.sys.core.manage.dao.MenuDao;
import com.hc.sys.core.manage.dao.RoleDao;
import com.hc.sys.core.manage.dao.RoleMenuDao;
import com.hc.sys.core.manage.entity.Menu;
import com.hc.sys.core.manage.model.MenuModel;
import com.hc.sys.core.manage.model.RoleModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.manage.entity.Role;
import com.hc.sys.core.manage.entity.RoleMenu;
import com.hc.sys.core.manage.model.RoleMenuModel;
import com.hc.sys.core.manage.service.RoleService;
/**
 * @Description: 角色
 * @Author: fangyong
 * @CreateDate: 2018/10/18 15:14
 * @Version: 1.0.0.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;
    @Resource
    private RoleMenuDao roleMenuDao;
    @Resource
    private MenuDao menuDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public Result list(RoleModel model) {
        PageDataList<Role> pageDataList = roleDao.list(model);
        PageDataList<RoleModel> pageDataList_ = new PageDataList<RoleModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<RoleModel> list = new ArrayList<RoleModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (Role role : pageDataList.getList()) {
                RoleModel model_ = RoleModel.instance(role);
                list.add(model_);
            }
        }
        pageDataList_.setList(list);
        return Result.success().setData(pageDataList_);
    }

    /**
     * 添加
     *
     * @param model
     * @return
     */
    @Override
    public Result add(RoleModel model) {
        String menuIdStr = model.getMenuIdStr();
        if (StringUtil.isBlank(menuIdStr)) {
            return Result.error("请选择角色对应的菜单！");
        }
        String[] menuIdArr = menuIdStr.split(",");
        Role role = model.prototype();
        Role r = roleDao.save(role);

        for (int i = 0; i < menuIdArr.length; i++) {
            long menuId = Long.parseLong(menuIdArr[i]);
            Menu menu = menuDao.find(menuId);
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenu(menu);
            roleMenu.setRole(r);
            roleMenuDao.save(roleMenu);
        }
        roleDao.clear();
        return Result.success().setData(role);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    public Result update(RoleModel model) {
        String menuIdStr = model.getMenuIdStr();
        if (StringUtil.isBlank(menuIdStr)) {
            return Result.error("请选择角色对应的菜单！");
        }
        String[] menuIdArr = menuIdStr.split(",");
        Role role = roleDao.find(model.getId());
        model.setUpdateParam(role);// 设置基本参数
        Role r = roleDao.update(role);// 更新
        roleMenuDao.deleteByRoleId(role.getId());// 删除原菜单
        // 保存新菜单
        for (int i = 0; i < menuIdArr.length; i++) {
            long menuId = Long.parseLong(menuIdArr[i]);
            Menu menu = menuDao.find(menuId);
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenu(menu);
            roleMenu.setRole(r);
            roleMenuDao.save(roleMenu);
        }
        return Result.success().setData(model.prototype());
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(RoleModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        Role role = roleDao.find(model.getId());
        List<RoleMenu> list = new ArrayList<RoleMenu>();
        for (RoleMenu menu : role.getRoleMenus()) {
            RoleMenuModel model_ = RoleMenuModel.instance(menu);
            model_.setMenuModel(MenuModel.instance(menu.getMenu()));
            list.add(model_);
        }
        //Hibernate.initialize(list);
        if (role == null) {
            return Result.error("此角色不存在！");
        }
        role.setRoleMenus(list);
        return Result.success().setData(role);
    }

    /**
     * 通过条件查询Role
     *
     * @param model
     * @return
     */
    @Override
    public Role getRoleBySearchName(RoleModel model) {
        if (!StringUtil.isNotBlank(model.getSearchName())) {
            Result.error().setMessage("参数不能为空！");
        }
        return roleDao.getRoleBySearchName(model);
    }

    /**
     * 通过角色ID查询所有菜单
     *
     * @param model
     * @return
     */
    @Override
    public Object getListMuneByRoleId(RoleModel model) {
        List menuList = new ArrayList();
        if (model.getId() <= 0) {
            Result.error().setMessage("角色Id参数错误！");
        }
        QueryParam queryParam=QueryParam.getInstance();
        queryParam.addParam("role.id", model.getId());
        queryParam.addParam("role.status", model.getStatus());
        List<RoleMenu> roleMenus = roleMenuDao.findByCriteria(queryParam);
        if (!roleMenus.isEmpty()) {
            for (RoleMenu roleMenu : roleMenus) {
                menuList.add(MenuModel.instance(roleMenu.getMenu()).prototype());
            }
        }
        return menuList;
    }

}