package com.hc.sys.core.dye.dao;
import com.hc.sys.common.dao.BaseDao;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.core.dye.entity.DyeColor;
import com.hc.sys.core.dye.model.DyeColorModel;
/**
 * @Description: 染料颜色
 * @Author: fangyong
 * @CreateDate: 2018/11/5 12:09
 * @Version: 1.0.0.0
 */
public interface DyeColorDao extends BaseDao<DyeColor> {
    /**
     * 分页列表
     * @param model
     * @return
     */
    PageDataList<DyeColor> list(DyeColorModel model);
}
