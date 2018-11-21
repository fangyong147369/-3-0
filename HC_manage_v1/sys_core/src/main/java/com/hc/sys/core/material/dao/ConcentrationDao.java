package com.hc.sys.core.material.dao;

import com.hc.sys.common.dao.BaseDao;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.core.material.entity.Concentration;
import com.hc.sys.core.material.model.ConcentrationModel;
import java.util.List;

/**
 * @Description: 浓度
 * @Author: fangyong
 * @CreateDate: 2018/10/19 18:46
 * @Version: 1.0.0.0
 */
public interface ConcentrationDao extends BaseDao<Concentration> {
    /**
     * 分页列表
     * @param model
     * @return
     */
    PageDataList<Concentration> list(ConcentrationModel model);
    /**
     * 无分页列表
     * @param
     * @return
     */
    List<Concentration> findList();
}
