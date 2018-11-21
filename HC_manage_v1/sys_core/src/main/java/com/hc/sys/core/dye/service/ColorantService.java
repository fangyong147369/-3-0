package com.hc.sys.core.dye.service;
import com.hc.sys.common.form.Result;
import com.hc.sys.core.dye.model.ColorantModel;
import com.hc.sys.core.manage.model.MenuModel;

import java.util.List;

/**
 * @Description: 颜色
 * @Author: fangyong
 * @CreateDate: 2018/10/18 14:36
 * @Version: 1.0.0.0
 */
public interface ColorantService {

	/**
 	 * 列表
 	 * @param model
 	 * @return
 	 */
	public Result list(ColorantModel model);
	/**
	 * 无分页列表
	 * @param
	 * @return
	 */
	public List<ColorantModel> findAll();
	/**
 	 * 添加
 	 * @param model
 	 * @return
 	 */
	public Result add(ColorantModel model);

	/**
 	 * 修改
 	 * @param model
 	 * @return
 	 */
	public Result update(ColorantModel model);

	/**
 	 * 获取
 	 * @param model
 	 * @return
 	 */
	public Result getById(ColorantModel model);

}