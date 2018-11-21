package com.hc.sys.core.material.dao.impl;

import com.hc.sys.common.dao.jpa.BaseDaoImpl;
import com.hc.sys.common.model.jpa.OrderFilter;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.model.jpa.QueryParam;
import com.hc.sys.common.model.jpa.SearchFilter;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.material.dao.MaterialTypeDao;
import com.hc.sys.core.material.entity.MaterialType;
import com.hc.sys.core.material.model.MaterialTypeModel;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @Description: 材质类型
 * @Author: fangyong
 * @CreateDate: 2018/10/19 18:49
 * @Version: 1.0.0.0
 */
@Repository
public class MaterialTypeDaoImpl extends BaseDaoImpl<MaterialType> implements MaterialTypeDao {
    /**
     * 分页列表
     * @param
     * @return
     */
    @Override
    public PageDataList<MaterialType> list(MaterialTypeModel model) {
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
    public List<MaterialType> findList() {
        return super.findAll();
    }
}
