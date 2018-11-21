package com.hc.sys.core.material.dao.impl;

import com.hc.sys.common.dao.jpa.BaseDaoImpl;
import com.hc.sys.common.model.jpa.OrderFilter;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.model.jpa.QueryParam;
import com.hc.sys.common.model.jpa.SearchFilter;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.common.web.BaseController;
import com.hc.sys.core.material.dao.FactoryDao;
import com.hc.sys.core.material.entity.Factory;
import com.hc.sys.core.material.model.FactoryModel;
import org.springframework.stereotype.Repository;

/**
 * @Description: 染厂车间
 * @Author: fangyong
 * @CreateDate: 2018/11/3 14:01
 * @Version: 1.0.0.0
 */
@Repository

public class FactoryDaoImpl  extends BaseDaoImpl<Factory> implements FactoryDao {

    @Override
    public PageDataList<Factory> list(FactoryModel model) {
        QueryParam param=QueryParam.getInstance();
        if(StringUtil.isNotBlank(model.getSearchName())){
            SearchFilter orFilter2 = new SearchFilter("name", SearchFilter.Operators.LIKE, model.getSearchName().trim());
            param.addOrFilter(orFilter2);
        }
        param.addOrder(OrderFilter.OrderType.ASC, "id");
        param.addPage(0, 100000000);
        return super.findPageList(param);
    }
}
