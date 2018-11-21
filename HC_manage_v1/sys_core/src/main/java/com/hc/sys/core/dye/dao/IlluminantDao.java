package com.hc.sys.core.dye.dao;
import com.hc.sys.common.dao.BaseDao;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.core.dye.entity.Illuminant;
import com.hc.sys.core.dye.model.IlluminantModel;
/**
 * @Description: 光源表
 * @Author: fangyong
 * @CreateDate: 2018/10/18 14:28
 * @Version: 1.0.0.0
 */
public interface IlluminantDao extends BaseDao<Illuminant> {

	/**
	 * 列表
	 * @param model
	 * @return
	 */
	PageDataList<Illuminant> list(IlluminantModel model);
	
}