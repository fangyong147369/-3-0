package com.hc.sys.core.dye.service;
import com.hc.sys.common.form.Result;
import com.hc.sys.core.dye.entity.Illuminant;
import com.hc.sys.core.dye.model.DyeModel;
import com.hc.sys.core.dye.model.IlluminantModel;

import java.util.List;

/**
 * @Description: 光源
 * @Author: fangyong
 * @CreateDate: 2018/10/18 14:56
 * @Version: 1.0.0.0
 */
public interface IlluminantService {
	/**
	 * 无分页列表
	 * @param
	 * @return
	 */
	public List<Illuminant> findAll();
	/**
 	 * 添加
 	 * @param model
 	 * @return
 	 */
	public Result add(IlluminantModel model);

	/**
 	 * 修改
 	 * @param model
 	 * @return
 	 */
	public Result update(IlluminantModel model);

	/**
 	 * 获取
 	 * @param model
 	 * @return
 	 */
	public Result getByIdAndAngle(IlluminantModel model);

}