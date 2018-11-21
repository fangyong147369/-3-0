package com.hc.sys.core.material.dao.impl;

import com.hc.sys.common.dao.jpa.BaseDaoImpl;
import com.hc.sys.common.model.jpa.OrderFilter;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.model.jpa.QueryParam;
import com.hc.sys.common.model.jpa.SearchFilter;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.material.dao.FormulaDao;
import com.hc.sys.core.material.dao.MaterialProofingDao;
import com.hc.sys.core.material.entity.Formula;
import com.hc.sys.core.material.entity.MaterialProofing;
import com.hc.sys.core.material.model.FormulaModel;
import com.hc.sys.core.material.model.MaterialProofingModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 材质打样
 * @Author: fangyong
 * @CreateDate: 2018/11/12 9:25
 * @Version: 1.0.0.0
 */
@Repository
public class MaterialProofingDaoImpl extends BaseDaoImpl<MaterialProofing> implements MaterialProofingDao {
    /**
     * 分页列表
     * @param
     * @return
     */
    @Override
    public PageDataList<MaterialProofing> list(MaterialProofingModel model) {
        QueryParam param=QueryParam.getInstance();
        if(StringUtil.isNotBlank(model.getSearchName())){
            SearchFilter orFilter2 = new SearchFilter("id", SearchFilter.Operators.LIKE, model.getSearchName().trim());
            param.addOrFilter(orFilter2);
        }
        param.addOrder(OrderFilter.OrderType.ASC, "id");
        param.addPage(0, 100000000);
        return super.findPageList(param);
    }
    /**
     * 无分页列表
     * @param
     * @return
     */
    @Override
    public List<MaterialProofing> findList() {
        return super.findAll();
    }
}
