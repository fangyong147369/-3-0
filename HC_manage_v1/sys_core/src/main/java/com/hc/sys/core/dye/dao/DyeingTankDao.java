package com.hc.sys.core.dye.dao;

import com.hc.sys.common.dao.BaseDao;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.core.dye.entity.DyeingTank;
import com.hc.sys.core.dye.model.DyeingTankModel;

import java.util.List;

/**
 * @Description:  染缸
 * @Author: fangyong
 * @CreateDate: 2018/10/19 20:51
 * @Version: 1.0.0.0
 */
public interface DyeingTankDao extends BaseDao<DyeingTank> {
    /**
     * 分页列表
     * @param model
     * @return
     */
    PageDataList<DyeingTank> list(DyeingTankModel model);
}
