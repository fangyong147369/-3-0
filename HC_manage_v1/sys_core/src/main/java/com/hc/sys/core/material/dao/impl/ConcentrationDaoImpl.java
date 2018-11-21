package com.hc.sys.core.material.dao.impl;
import com.hc.sys.common.dao.jpa.BaseDaoImpl;
import com.hc.sys.common.model.jpa.OrderFilter;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.model.jpa.QueryParam;
import com.hc.sys.common.model.jpa.SearchFilter;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.material.dao.ConcentrationDao;
import com.hc.sys.core.material.entity.Concentration;
import com.hc.sys.core.material.model.ConcentrationModel;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @Description: 浓度
 * @Author: fangyong
 * @CreateDate: 2018/10/19 18:52
 * @Version: 1.0.0.0
 */
@Repository
public class ConcentrationDaoImpl extends BaseDaoImpl<Concentration> implements ConcentrationDao{
    @Override
    public PageDataList<Concentration> list(ConcentrationModel model) {
        QueryParam param=QueryParam.getInstance();
        if(StringUtil.isNotBlank(model.getSearchName())){
            SearchFilter orFilter2 = new SearchFilter("id", SearchFilter.Operators.LIKE, model.getSearchName().trim());
            param.addOrFilter(orFilter2);
        }
        if(model.getFormulaId()>0){
            param.addParam("formula.id",model.getFormulaId());
        }
        param.addOrder(OrderFilter.OrderType.ASC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    @Override
    public List<Concentration> findList() {
        return super.findAll();
    }
}
