package com.hc.sys.core.manage.service;
import com.hc.sys.core.manage.entity.Operator;
import com.hc.sys.core.manage.model.OperatorModel;
import com.hc.sys.common.form.Result;
 /**
 * 管理员
 * @Author: fangyong
 * @CreateDate: 2018/10/18 11:46
 * @Version: 1.0.0.0
 */
public interface OperatorService{

	/**
 	 * 列表
 	 * @param model
 	 * @return
 	 */
	public Result list(OperatorModel model);

	/**
 	 * 添加
 	 * @param model
 	 * @return
 	 */
	public Result add(OperatorModel model);

	/**
 	 * 修改
 	 * @param model
 	 * @return
 	 */
	public Result update(OperatorModel model);

	/**
 	 * 获取
 	 * @param model
 	 * @return
 	 */
	public Result getById(OperatorModel model);

	/**
	 * 登录
	 * @param model
	 * @return
	 */
	public Result signIn(OperatorModel model);
	 /**
	  * 登录ByNetty
	  * @param model
	  * @return
	  */
	 public Result signInByNetty(OperatorModel model);
	 /**
	  * 删除SessionID
	  * @param model
	  * @return
	  */
	 public Object deleteSessionID(OperatorModel model);
	 /**
	  * 统计在线用户
	  * @param model
	  * @return
	  */
	 public Object selectOperatorSessionID(OperatorModel model);
	 /**
	  * 通过条件查询Operator
	  * @param model
	  * @return
	  */
	 Result getOperatorBySearchName(OperatorModel model);
	 /**
	  * 查询SessionID是否为空
	  * @param
	  * @return
	  */
	 public Object selectOperatorSessionIDIsNull(OperatorModel model);

}