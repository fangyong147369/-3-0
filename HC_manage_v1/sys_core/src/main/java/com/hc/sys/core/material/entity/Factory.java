package com.hc.sys.core.material.entity;

import com.hc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Description: 染厂车间
 * @Author: fangyong
 * @CreateDate: 2018/11/3 13:51
 * @Version: 1.0.0.0
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = "factory")
public class Factory extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;

    /**
     *染厂名称
     **/
    private String name;
    /**
     * 染厂车间
     **/
    private String workshop;

    /**
     * 获取 染厂名称
     *
     * @return name 染厂名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置 染厂名称
     *
     * @param name 染厂名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 染厂车间
     *
     * @return workshop 染厂车间
     */
    public String getWorkshop() {
        return this.workshop;
    }

    /**
     * 设置 染厂车间
     *
     * @param workshop 染厂车间
     */
    public void setWorkshop(String workshop) {
        this.workshop = workshop;
    }

}
