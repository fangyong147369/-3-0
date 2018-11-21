package com.hc.sys.core.material.dao;

import com.hc.sys.common.dao.BaseDao;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.core.material.entity.Formula;
import com.hc.sys.core.material.model.FormulaModel;

import java.util.List;

/**
 * @Description: 配方
 * @Author: fangyong
 * @CreateDate: 2018/10/19 18:43
 * @Version: 1.0.0.0
 */
public interface FormulaDao extends BaseDao<Formula> {
    /**
     * 分页列表
     * @param model
     * @return
     */
    PageDataList<Formula> list(FormulaModel model);
    /**
     * 无分页列表
     * @param
     * @return
     */
    List<Formula> findList();
}
