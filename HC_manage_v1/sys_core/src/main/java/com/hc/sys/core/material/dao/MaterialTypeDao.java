package com.hc.sys.core.material.dao;

import com.hc.sys.common.dao.BaseDao;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.core.material.entity.MaterialType;
import com.hc.sys.core.material.model.MaterialTypeModel;

import java.util.List;

/**
 * @Description: 材质类型
 * @Author: fangyong
 * @CreateDate: 2018/10/19 10:39
 * @Version: 1.0.0.0
 */
public interface MaterialTypeDao extends BaseDao<MaterialType> {
    /**
     * 分页列表
     * @param model
     * @return
     */
    PageDataList<MaterialType> list(MaterialTypeModel model);
    /**
     * 无分页列表
     * @param
     * @return
     */
    List<MaterialType> findList();
}
