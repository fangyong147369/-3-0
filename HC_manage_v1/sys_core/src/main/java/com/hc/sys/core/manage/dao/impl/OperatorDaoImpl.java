package com.hc.sys.core.manage.dao.impl;

import com.hc.sys.common.form.Result;
import com.hc.sys.core.manage.dao.OperatorDao;
import com.hc.sys.common.model.jpa.OrderFilter;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.common.model.jpa.QueryParam;
import com.hc.sys.common.model.jpa.SearchFilter;
import com.hc.sys.common.util.validate.StringUtil;
import com.hc.sys.core.manage.entity.Operator;
import com.hc.sys.core.manage.model.OperatorModel;
import org.hibernate.LockMode;
import org.springframework.stereotype.Repository;
import com.hc.sys.common.dao.jpa.BaseDaoImpl;
import com.hc.sys.core.manage.dao.OperatorDao;

import javax.persistence.LockModeType;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:管理员
 * @Author: fangyong
 * @CreateDate: 2018/10/17 19:16
 * @Version: 1.0.0.0
 */
@Repository
public class OperatorDaoImpl extends BaseDaoImpl<Operator> implements OperatorDao {

    /**
     * 列表
     */
    @Override
    public PageDataList<Operator> list(OperatorModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            param.addParam("userName", SearchFilter.Operators.LIKE, model.getSearchName().trim());
        }
        if (model.getStatus() != 0) {
            param.addParam("status", model.getStatus());
        }
        param.addOrder(OrderFilter.OrderType.ASC, "id");
        param.addPage(model.getPageNo(), model.getPageSize());
        return super.findPageList(param);
    }

    /**
     * 通过条件查询Operator
     *
     * @param model
     * @return
     */
    @Override
    public Operator getOperatorBySearchName(OperatorModel model) {
        QueryParam param = QueryParam.getInstance();
        if (StringUtil.isNotBlank(model.getSearchName())) {
            SearchFilter name = new SearchFilter("email", SearchFilter.Operators.EQ, model.getSearchName().trim());
            SearchFilter userName = new SearchFilter("userName", SearchFilter.Operators.EQ, model.getSearchName().trim());
            param.addOrFilter(name, userName);
        }
        return super.findByCriteriaForUnique(param);
    }

    /**
     * @description 在线用户管理
     * @author: fangyong
     * @date 2018/11/15 10:56
     */
    @Override
    public Object selectOperatorSessionID() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Operator> operatorList = new ArrayList<Operator>();
        String sqlOperator = "select * from operator where sessionID is not null and sessionID!=\"\"";
        Query queryOperator = em.createNativeQuery(sqlOperator, Operator.class);
        if (queryOperator.getResultList() != null && queryOperator.getResultList().size() > 0) {
            for (Object object : queryOperator.getResultList()) {
                Operator operator = (Operator) object;
                try {
                    operatorList.add(operator);
                } catch (ClassCastException e) {
                    return Result.error(e.getMessage());
                }
            }
        }
        map.put("countSession", operatorList.size());
        map.put("operatorList", operatorList);
        return map;
    }

    /**
     * @description 查询SessionID是否为空
     * @author: fangyong
     * @date 2018/11/15 11:23
     */
    @Override
    public Object selectOperatorSessionIDIsNull(OperatorModel model) {
        if (StringUtil.isBlank(model.getId())||model.getId()<=0) {
            return Result.error("参数错误----请检查id参数是否正确！");
        }
        String sqlOperator = "select sessionID from operator where id= :id";
        Query queryOperator = em.createNativeQuery(sqlOperator);
        queryOperator.setParameter("id", model.getId());
        if (StringUtil.isBlank(queryOperator.getSingleResult())) {
            return Result.success();
        } else {
            return Result.error();
        }

    }

    /**
     * @description 删除SessionID
     * @author: fangyong
     * @date 2018/11/15 10:56
     */
    @Override
    public int deleteSessionID(OperatorModel model) {
        String sql = "update operator set sessionID =null where id= :id";
        Query query = em.createNativeQuery(sql);
        query.setParameter("id", model.getId());
        int n = query.executeUpdate();
        return n;
    }

}