package com.hc.sys.core.manage.service.impl;

import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.manage.dao.MenuDao;
import com.hc.sys.core.manage.entity.Menu;
import com.hc.sys.core.manage.model.MenuModel;
import com.hc.sys.core.manage.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 菜单
 * @Author: fangyong
 * @CreateDate: 2018/10/15 19:41
 * @Version: 1.0.0.0
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuDao menuDao;

    /**
     * 列表
     *
     * @param model
     * @return
     */
    @Override
    public Result list(MenuModel model) {
        PageDataList<Menu> pageDataList = menuDao.list(model);
        PageDataList<MenuModel> pageDataList_ = new PageDataList<MenuModel>();
        pageDataList_.setPage(pageDataList.getPage());
        List<MenuModel> list = new ArrayList<MenuModel>();
        if (pageDataList != null && pageDataList.getList().size() > 0) {
            for (Menu menu : pageDataList.getList()) {
                MenuModel model_ = MenuModel.instance(menu);
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
    @Transactional
    public Result add(MenuModel model) {
        addCheckParams(model);
        Menu menu = model.prototype();
        menuDao.save(menu);
        return Result.success().setData(menu);
    }

    /**
     * 修改
     *
     * @param model
     * @return
     */
    @Override
    @Transactional
    public Result update(MenuModel model) {
        updateCheckParams(model);
        setUpdateParams(model);
        menuDao.update(model.prototype());
        return Result.success();
    }

    /**
     * 获取
     *
     * @param model
     * @return
     */
    @Override
    public Result getById(MenuModel model) {
        if (model.getId() <= 0) {
            return Result.error("参数错误！");
        }
        Menu menu = menuDao.find(model.getId());
        if (menu == null) {
            return Result.error("此菜单不存在！");
        }
        return Result.success().setData(menu);
    }
/**
 * @description 批量保存菜单
 * @author: fangyong
 * @date 2018/11/21 9:30
 */
    @Override
    @Transactional
    public Result saveMenuList() {
        return menuDao.saveMenuList();
    }

    /**
     * 添加参数检验
     *
     * @param model
     * @return
     */
    protected void addCheckParams(MenuModel model) {
        checkParams(model);
        if (model.getParentId() < 0) {
            model.setParentId(0);
        }
        model.setType(0);
    }

    /**
     * 修改参数检验
     *
     * @param model
     * @return
     */
    protected void checkParams(MenuModel model) {
        if (StringUtil.isBlank(model.getName())) {
            throw new BusinessException("菜单名称不能为空！");
        }
        if (StringUtil.isBlank(model.getHref())) {
            throw new BusinessException("菜单url不能为空！");
        }
    }

    /**
     * 修改参数检验
     *
     * @param model
     * @return
     */
    protected void updateCheckParams(MenuModel model) {
        if (model.getId() <= 0) {
            throw new BusinessException("菜单ID错误！");
        }
        Menu menu = menuDao.find(model.getId());
        if (StringUtil.isBlank(menu)) {
            throw new BusinessException("此菜单不存在！");
        }
        checkParams(model);
    }

    /**
     * 修改参数设置
     *
     * @param model
     * @return
     */
    protected void setUpdateParams(MenuModel model) {
        model.setName(model.getName());
        model.setParentId(model.getParentId());
        model.setHref(model.getHref());
        model.setSort(model.getSort());
        model.setRemark(model.getRemark());
        model.setStatus(model.getStatus());
        model.setType(0);
    }
}