package com.hc.sys.core.material.dao;

import com.hc.sys.common.dao.BaseDao;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.core.material.entity.Formula;
import com.hc.sys.core.material.entity.MaterialProofing;
import com.hc.sys.core.material.model.FormulaModel;
import com.hc.sys.core.material.model.MaterialProofingModel;

import java.util.List;

/**
 * @Description: 材质打样
 * @Author: fangyong
 * @CreateDate: 2018/11/12 9:25
 * @Version: 1.0.0.0
 */
public interface MaterialProofingDao extends BaseDao<MaterialProofing> {
    /**
     * 分页列表
     * @param model
     * @return
     */
    PageDataList<MaterialProofing> list(MaterialProofingModel model);
    /**
     * 无分页列表
     * @param
     * @return
     */
    List<MaterialProofing> findList();
}
