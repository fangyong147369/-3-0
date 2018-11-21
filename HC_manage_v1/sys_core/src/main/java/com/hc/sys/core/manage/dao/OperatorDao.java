package com.hc.sys.core.manage.dao;
import com.hc.sys.common.dao.BaseDao;
import com.hc.sys.common.model.jpa.PageDataList;
import com.hc.sys.core.manage.entity.Operator;
import com.hc.sys.core.manage.model.OperatorModel;
/**
 * @Description: 管理员
 * @Author: fangyong
 * @CreateDate: 2018/10/16 18:16
 * @Version: 1.0.0.0
 */
public interface OperatorDao extends BaseDao<Operator> {
    /**
     * 列表
     * @param model
     * @return
     */
    PageDataList<Operator> list(OperatorModel model);
    /**
     * 通过条件查询Operator
     * @param model
     * @return
     */
    Operator getOperatorBySearchName(OperatorModel model);
    /**
     * 统计在线用户
     * @param
     * @return
     */
    public Object selectOperatorSessionID();
    /**
     * 查询SessionID是否为空
     * @param
     * @return
     */
    public Object selectOperatorSessionIDIsNull(OperatorModel model);
    /**
     * 删除SessionID
     * @param model
     * @return
     */
    public int  deleteSessionID(OperatorModel model);
    /**
     * @description 查询SessionID是否为空
     * @author: fangyong
     * @date 2018/11/15 11:23
     */
}