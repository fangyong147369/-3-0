package com.hc.sys.core.dye.dao;

import com.hc.sys.common.dao.BaseDao;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.core.dye.entity.Dye;
import com.hc.sys.core.dye.entity.DyeType;
import com.hc.sys.core.dye.model.DyeModel;
import com.hc.sys.core.dye.model.DyeTypeModel;

import java.util.List;

/**
 * @Description: 染料类型
 * @Author: fangyong
 * @CreateDate: 2018/10/19 11:16
 * @Version: 1.0.0.0
 */
public interface DyeTypeDao extends BaseDao<DyeType> {
    /**
     * 分页列表
     * @param model
     * @return
     */
    PageDataList<DyeType> list(DyeTypeModel model);
}
