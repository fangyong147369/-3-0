package com.hc.sys.api.manage;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.core.common.web.BaseController;
import com.hc.sys.core.manage.model.RoleMenuModel;
import com.hc.sys.core.manage.service.RoleMenuService;
/**
 *
 * @Description: 角色-菜单
 * @Author: fangyong
 * @CreateDate: 2018/10/17 20:45
 * @Version: 1.0.0.0
 */
@RestController
@RequestMapping("/manage/rolemenu")
public class RoleMenuController extends BaseController<RoleMenuModel> {

	@Resource
	RoleMenuService roleMenuService;

	/**
 	 * 列表
 	 * @param model
 	 * @return
 	 */
	@RequestMapping(value = "/getMenuListByRoleId", method = RequestMethod.POST)
	@ResponseBody
	public Object getMenuListByRoleId(RoleMenuModel model) throws BusinessException {
		return roleMenuService.getMenuListByRoleId(model);
	}


	/**
	 * 列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(RoleMenuModel model) throws BusinessException {
		return roleMenuService.list(model);
	}
	/**
 	 * 添加
 	 * @param model
 	 * @return
 	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(RoleMenuModel model) throws BusinessException {
		return roleMenuService.add(model);
	}

	/**
 	 * 修改
 	 * @param model
 	 * @return
 	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(RoleMenuModel model) throws BusinessException {
		return roleMenuService.update(model);
	}

	/**
 	 * 获取
 	 * @param model
 	 * @return
 	 */
	@RequestMapping(value = "/getById", method = RequestMethod.POST)
	@ResponseBody
	public Object getById(RoleMenuModel model) throws BusinessException {
		return roleMenuService.getById(model);
	}
}