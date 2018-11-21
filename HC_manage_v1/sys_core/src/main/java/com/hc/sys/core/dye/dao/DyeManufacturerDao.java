package com.hc.sys.core.dye.dao;

import com.hc.sys.common.dao.BaseDao;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.core.dye.entity.DyeManufacturer;
import com.hc.sys.core.dye.model.DyeManufacturerModel;

import java.util.List;

/**
 * @Description:  染料厂商
 * @Author: fangyong
 * @CreateDate: 2018/10/19 11:11
 * @Version: 1.0.0.0
 */
public interface DyeManufacturerDao extends BaseDao<DyeManufacturer>{
    /**
     * 列表
     * @param model
     * @return
     */
    PageDataList<DyeManufacturer> list(DyeManufacturerModel model);
}
