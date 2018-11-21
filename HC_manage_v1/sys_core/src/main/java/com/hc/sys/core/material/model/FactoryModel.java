package com.hc.sys.core.material.model;

import com.hc.sys.common.model.jpa.Page;
import com.hc.sys.core.material.entity.Factory;
import com.hc.sys.core.material.entity.Formula;
import org.springframework.beans.BeanUtils;

/**
 * @Description: 染厂车间
 * @Author: fangyong
 * @CreateDate: 2018/11/3 13:54
 * @Version: 1.0.0.0
 */
public class FactoryModel extends Factory {
    /** 序列号 **/
    private static final long serialVersionUID = 1L;

    /** 当前页码 **/
    private int pageNo;
    /** 每页数据条数 **/
    private int pageSize = Page.ROWS;
    /** 条件查询 **/
    private String searchName;

    /**
     * 实体转换model
     */
    public static FactoryModel instance(Factory factory) {
        if(factory==null||factory.getId()<0){
            return null;
        }
        FactoryModel formulaModel = new FactoryModel();
        BeanUtils.copyProperties(factory, formulaModel);
        return formulaModel;
    }

    /**
     * model转换实体
     */
    public Factory prototype() {
        Factory factory= new Factory();
        BeanUtils.copyProperties(this, factory);
        return factory;
    }


    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }
}
