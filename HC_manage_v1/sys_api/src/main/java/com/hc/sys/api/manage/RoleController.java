package com.hc.sys.api.manage;
import javax.annotation.Resource;

import com.hc.sys.common.util.http.RequestUtil;
import org.springframework.web.bind.annotation.*;

import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.core.common.web.BaseController;
import com.hc.sys.core.manage.model.RoleModel;
import com.hc.sys.core.manage.service.RoleService;
/**
 *
 * @Description: 角色
 * @Author: fangyong
 * @CreateDate: 2018/10/17 20:35
 * @Version: 1.0.0.0
 */
@RestController
@RequestMapping("/manage/role")
public class RoleController extends BaseController<RoleModel> {

	@Resource
	RoleService roleService;

	/**
 	 * 列表
 	 * @param model
 	 * @return
 	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(RoleModel model) throws BusinessException {
		return roleService.list(model);
	}

	/**
 	 * 添加
 	 * @param model
 	 * @return
 	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(RoleModel model) throws BusinessException {
		return roleService.add(model);
	}
	/**
	 * 通过条件查询Role
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getRoleBySearchName", method = RequestMethod.POST)
	@ResponseBody
	public Object getRoleBySearchName(RoleModel model) throws BusinessException {
		return roleService.getRoleBySearchName(model);
	}
	/**
 	 * 修改
 	 * @param model
 	 * @return
 	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(RoleModel model) throws BusinessException {
		return roleService.update(model);
	}

	/**
 	 * 获取
 	 * @param model
 	 * @return
 	 */
	@RequestMapping(value = "/getById", method = RequestMethod.POST)
	@ResponseBody
	public Object getById(RoleModel model) throws BusinessException {
		return roleService.getById(model);
	}

	/**
	 * 通过角色ID查询所有菜单
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getListMuneByRoleId", method = RequestMethod.POST)
	@ResponseBody
	public Object getListMuneByRoleId(RoleModel model) throws BusinessException {
		return roleService.getListMuneByRoleId(model);
	}
}