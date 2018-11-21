package com.hc.sys.core.dye.dao.impl;

import com.hc.sys.common.dao.jpa.BaseDaoImpl;
import com.hc.sys.common.model.jpa.OrderFilter;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.model.jpa.QueryParam;
import com.hc.sys.common.model.jpa.SearchFilter;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.dye.dao.IlluminantobserverxyzDao;
import com.hc.sys.core.dye.entity.Illuminantobserverxyz;
import com.hc.sys.core.dye.model.IlluminantobserverxyzModel;
import org.springframework.stereotype.Repository;

/**
 * @Description: 描述
 * @Author: fangyong
 * @CreateDate: 2018/11/6 15:12
 * @Version: 1.0.0.0
 */
@Repository
public class IlluminantobserverxyzDaoImpl extends BaseDaoImpl<Illuminantobserverxyz> implements IlluminantobserverxyzDao {
    /**
     * 列表
     * @param model
     * @return
     */
    @Override
    public PageDataList<Illuminantobserverxyz> list(IlluminantobserverxyzModel model) {
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
