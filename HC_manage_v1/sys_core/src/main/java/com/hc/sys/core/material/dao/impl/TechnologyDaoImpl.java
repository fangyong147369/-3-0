package com.hc.sys.core.material.dao.impl;

import com.hc.sys.common.dao.jpa.BaseDaoImpl;
import com.hc.sys.common.model.jpa.OrderFilter;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.model.jpa.QueryParam;
import com.hc.sys.common.model.jpa.SearchFilter;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.material.dao.TechnologyDao;
import com.hc.sys.core.material.entity.Technology;
import com.hc.sys.core.material.model.TechnologyModel;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @Description:工艺
 * @Author: fangyong
 * @CreateDate: 2018/10/19 19:14
 * @Version: 1.0.0.0
 */
@Repository
public class TechnologyDaoImpl extends BaseDaoImpl<Technology> implements TechnologyDao {
    /**
     * 分页列表
     * @param
     * @return
     */
    @Override
    public PageDataList<Technology> list(TechnologyModel model) {
        QueryParam param=QueryParam.getInstance();
        if(StringUtil.isNotBlank(model.getSearchName())){
            SearchFilter orFilter2 = new SearchFilter("id", SearchFilter.Operators.LIKE, model.getSearchName().trim());
            param.addOrFilter(orFilter2);
        }
        param.addOrder(OrderFilter.OrderType.ASC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }
    /**
     * 无分页列表
     * @param
     * @return
     */
    @Override
    public List<Technology> findAll() {
        return super.findAll();
    }
}
