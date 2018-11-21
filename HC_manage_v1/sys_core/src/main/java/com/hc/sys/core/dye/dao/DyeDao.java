package com.hc.sys.core.dye.dao;

import com.hc.sys.common.dao.BaseDao;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.core.dye.entity.Dye;
import com.hc.sys.core.dye.model.DyeModel;

import java.util.List;

/**
 * @Description: 染料
 * @Author: fangyong
 * @CreateDate: 2018/10/19 11:16
 * @Version: 1.0.0.0
 */
public interface DyeDao extends BaseDao<Dye> {
    /**
     * 分页列表
     * @param model
     * @return
     */
    PageDataList<Dye> list(DyeModel model);
}
