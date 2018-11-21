package com.hc.sys.core.material.dao.impl;
import com.hc.sys.common.dao.jpa.BaseDaoImpl;
import com.hc.sys.common.model.jpa.OrderFilter;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.model.jpa.QueryParam;
import com.hc.sys.common.model.jpa.SearchFilter;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.material.dao.FormulaDao;
import com.hc.sys.core.material.entity.Formula;
import com.hc.sys.core.material.model.FormulaModel;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * @Description: 配方
 * @Author: fangyong
 * @CreateDate: 2018/10/19 19:10
 * @Version: 1.0.0.0
 */
@Repository
public class FormulaDaoImpl extends BaseDaoImpl<Formula> implements FormulaDao {
    /**
     * 分页列表
     * @param
     * @return
     */
    @Override
    public PageDataList<Formula> list(FormulaModel model) {
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
    public List<Formula> findList() {
        return super.findAll();
    }
    /**
     * 批量插入
     * @param
     * @return
     */
    public List<Formula> saveList() {
     List<Formula> formulaList=super.findAll();
        super.save(formulaList);
        return super.findAll();
    }
}
