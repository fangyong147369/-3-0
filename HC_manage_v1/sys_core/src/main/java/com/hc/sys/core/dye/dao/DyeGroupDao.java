package com.hc.sys.core.dye.dao;
import com.hc.sys.common.dao.BaseDao;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.core.dye.entity.DyeGroup;
import com.hc.sys.core.dye.model.DyeGroupModel;
import java.util.List;
/**
 * @Description:  染料组
 * @Author: fangyong
 * @CreateDate: 2018/10/19 11:16
 * @Version: 1.0.0.0
 */
public interface DyeGroupDao extends BaseDao<DyeGroup> {
    /**
     * 分页列表
     * @param model
     * @return
     */
    PageDataList<DyeGroup> list(DyeGroupModel model);

}
