package com.hc.sys.core.manage.service.impl;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import com.hc.sys.common.form.Result;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.core.manage.dao.RoleMenuDao;
import com.hc.sys.core.manage.entity.RoleMenu;
import com.hc.sys.core.manage.model.MenuModel;
import com.hc.sys.core.manage.model.RoleMenuModel;
import com.hc.sys.core.manage.model.RoleModel;
import com.hc.sys.core.manage.service.RoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 角色-菜单
 * @Author: fangyong
 * @CreateDate: 2018/10/18 14:26
 * @Version: 1.0.0.0
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

	@Resource
	private RoleMenuDao roleMenuDao;
	/**
 	 * 列表
 	 * @param model
 	 * @return
 	 */
	@Override
	public Result list(RoleMenuModel model){
		PageDataList<RoleMenu> pageDataList = roleMenuDao.list(model);
		PageDataList<RoleMenuModel> pageDataList_ = new PageDataList<RoleMenuModel>();
		pageDataList_.setPage(pageDataList.getPage());
		List<RoleMenuModel> list = new ArrayList<RoleMenuModel>();
		if(pageDataList != null && pageDataList.getList().size() > 0){
			for (RoleMenu menu : pageDataList.getList()) {
				RoleMenuModel model_ = RoleMenuModel.instance(menu);
				model_.setMenuModel(MenuModel.instance(menu.getMenu()));
				model_.setRoleModel(RoleModel.instance(menu.getRole()));
				list.add(model_);
			}
		}
		pageDataList_.setList(list);
		return Result.success().setData(pageDataList_);
	}

	/**
 	 * 添加
 	 * @param model
 	 * @return
 	 */
	@Override
	@Transactional
	public Result add(RoleMenuModel model){

		return null;
	}

	/**
 	 * 修改
 	 * @param model
 	 * @return
 	 */
	@Override
	@Transactional
	public Result update(RoleMenuModel model){

		return null;
	}

	/**
 	 * 获取
 	 * @param model
 	 * @return
 	 */
	@Override
	public Result getById(RoleMenuModel model){

		return null;
	}

	@Override
	public List<RoleMenu> getMenuListByRoleId(RoleMenuModel model) {
		List<RoleMenu> list=new ArrayList<RoleMenu>();
		List<RoleMenu> rm=roleMenuDao.getMenuListByRoleId(model);
		for (RoleMenu menu : rm) {
			RoleMenuModel model_ = RoleMenuModel.instance(menu);
			model_.setMenuModel(MenuModel.instance(menu.getMenu()));
			model.setRoleModel(RoleModel.instance(menu.getRole()));
			list.add(model_);
		}
		return list;
	}

}