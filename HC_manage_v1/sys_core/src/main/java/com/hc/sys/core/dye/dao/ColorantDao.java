package com.hc.sys.core.dye.dao;
import com.hc.sys.common.dao.BaseDao;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.core.dye.entity.Colorant;
import com.hc.sys.core.dye.model.ColorantModel;

import java.util.List;

/**
 * @Description: 颜色表
 * @Author: fangyong
 * @CreateDate: 2018/10/18 14:25
 * @Version: 1.0.0.0
 */
public interface ColorantDao extends BaseDao<Colorant> {

	/**
	 * 列表
	 * @param model
	 * @return
	 */
	PageDataList<Colorant> list(ColorantModel model);
}