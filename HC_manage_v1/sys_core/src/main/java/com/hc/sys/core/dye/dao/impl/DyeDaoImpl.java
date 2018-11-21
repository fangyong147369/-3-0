package com.hc.sys.core.dye.dao.impl;

import com.hc.sys.common.dao.jpa.BaseDaoImpl;
import com.hc.sys.common.model.jpa.OrderFilter;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.model.jpa.QueryParam;
import com.hc.sys.common.model.jpa.SearchFilter;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.dye.dao.DyeDao;
import com.hc.sys.core.dye.entity.Dye;
import com.hc.sys.core.dye.model.DyeModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 染料
 * @Author: fangyong
 * @CreateDate: 2018/10/19 11:16
 * @Version: 1.0.0.0
 */
@Repository
public class DyeDaoImpl extends BaseDaoImpl<Dye> implements DyeDao {
    /**
     * 列表
     * @param model
     * @return
     */
    @Override
    public PageDataList<Dye> list(DyeModel model) {
        QueryParam param = QueryParam.getInstance();
        if(StringUtil.isNotBlank(model.getSearchName())){
            SearchFilter orFilter2 = new SearchFilter("name", SearchFilter.Operators.LIKE, model.getSearchName().trim());
            param.addOrFilter(orFilter2);
        }
        param.addOrder(OrderFilter.OrderType.ASC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }
}
