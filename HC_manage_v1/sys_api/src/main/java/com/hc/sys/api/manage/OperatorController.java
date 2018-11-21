package com.hc.sys.api.manage;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import com.hc.sys.common.exception.BusinessException;
import com.hc.sys.core.common.web.BaseController;
import com.hc.sys.core.manage.model.OperatorModel;
import com.hc.sys.core.manage.service.OperatorService;
 /**
 * @Description: 管理员
 * @Author: fangyong
 * @CreateDate: 2018/10/18 11:25
 * @Version: 1.0.0.0
 */
@RestController
@RequestMapping("/manage/operator")
public class OperatorController extends BaseController<OperatorModel> {
	@Resource
	OperatorService operatorService;
	/**
 	 * 列表
 	 * @param model
 	 * @return
 	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(OperatorModel model) throws BusinessException {
		return operatorService.list(model);
	}

	/**
 	 * 添加
 	 * @param model
 	 * @return
 	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object add(OperatorModel model) throws BusinessException {
		return operatorService.add(model);
	}
	 /**
	  * 通过条件查询Operator
	  * @param model
	  * @return
	  */
	 @RequestMapping(value = "/getOperatorBySearchName", method = RequestMethod.POST)
	 @ResponseBody
	 public Object getOperatorBySearchName(OperatorModel model) throws BusinessException {
		 return operatorService.getOperatorBySearchName(model);
	 }
	/**
 	 * 修改
 	 * @param model
 	 * @return
 	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Object update(OperatorModel model) throws BusinessException {
		return operatorService.update(model);
	}

	/**
 	 * 获取
 	 * @param model
 	 * @return
 	 */
	@RequestMapping(value = "/getById", method = RequestMethod.POST)
	@ResponseBody
	public Object getById(OperatorModel model) throws BusinessException {
		return operatorService.getById(model);
	}

	/**
	 * 登录
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/signIn",method = RequestMethod.POST)
	@ResponseBody
	public Object signIn(OperatorModel model) throws BusinessException {
		return operatorService.signIn(model);
	}

	 /**
	  * 删除SessionID
	  * @param model
	  * @return
	  */
	 @RequestMapping(value = "/deleteSessionID",method = RequestMethod.POST)
	 @ResponseBody
	 public Object deleteSessionID(OperatorModel model) throws BusinessException {
		 return operatorService.deleteSessionID(model);
	 }
	 /**
	  * 统计在线用户
	  * @param model
	  * @return
	  */
	 @RequestMapping(value = "/selectOperatorSessionID",method = RequestMethod.POST)
	 @ResponseBody
	 public Object selectOperatorSessionID(OperatorModel model) throws BusinessException {
		 return operatorService.selectOperatorSessionID(model);
	 }
	 /**
	  * @description 查询SessionID是否为空
	  * @author: fangyong
	  * @date 2018/11/15 11:23
	  */
	 @RequestMapping(value = "/selectOperatorSessionIDIsNull",method = RequestMethod.POST)
	 @ResponseBody
	 public Object selectOperatorSessionIDIsNull(OperatorModel model) throws BusinessException {
		 return operatorService.selectOperatorSessionIDIsNull(model);
	 }
}