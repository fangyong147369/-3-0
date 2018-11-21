package com.hc.sys.core.material.dao;

import com.hc.sys.common.dao.BaseDao;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.core.material.entity.Factory;
import com.hc.sys.core.material.entity.Technology;
import com.hc.sys.core.material.model.FactoryModel;

import java.util.List;

/**
 * @Description: 染厂车间
 * @Author: fangyong
 * @CreateDate: 2018/11/3 13:59
 * @Version: 1.0.0.0
 */
public interface FactoryDao extends BaseDao<Factory> {
    /**
     * 分页列表
     * @param model
     * @return
     */
    PageDataList<Factory> list(FactoryModel model);
}
