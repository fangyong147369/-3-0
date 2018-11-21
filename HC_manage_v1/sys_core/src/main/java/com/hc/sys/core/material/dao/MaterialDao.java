package com.hc.sys.core.material.dao;

import com.hc.sys.common.dao.BaseDao;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.core.dye.entity.Dye;
import com.hc.sys.core.dye.model.DyeModel;
import com.hc.sys.core.material.entity.Material;
import com.hc.sys.core.material.model.MaterialModel;

import java.util.List;

/**
 * @Description: 材质
 * @Author: fangyong
 * @CreateDate: 2018/10/19 10:39
 * @Version: 1.0.0.0
 */
public interface MaterialDao extends BaseDao<Material>{
    /**
     * 分页列表
     * @param model
     * @return
     */
    PageDataList<Material> list(MaterialModel model);
    /**
     * 无分页列表
     * @param
     * @return
     */
    List<Material> findList();

}
