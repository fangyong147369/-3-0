package com.hc.sys.core.dye.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hc.sys.common.entity.LongPKEntity;
import com.hc.sys.core.manage.entity.Operator;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description: 染料类型
 * @Author: fangyong
 * @CreateDate: 2018/10/19 14:58
 * @Version: 1.0.0.0
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name ="dye_type")
public class DyeType extends LongPKEntity {
    /** 序列号 **/
    private static final long serialVersionUID = 1L;
    /**染料类型名称 **/
    private String name;
    /**别名**/
    private String alias;
    /**父类型**/
    private int parentType;
    /**描述 **/
    private String description;

    /**
     * 获取 染料类型名称
     *
     * @return name 染料类型名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置 染料类型名称
     *
     * @param name 染料类型名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 别名
     *
     * @return alias 别名
     */
    public String getAlias() {
        return this.alias;
    }

    /**
     * 设置 别名
     *
     * @param alias 别名
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * 获取 描述
     *
     * @return description 描述
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * 设置 描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取 父类型
     *
     * @return parentType 父类型
     */
    public int getParentType() {
        return this.parentType;
    }

    /**
     * 设置 父类型
     *
     * @param parentType 父类型
     */
    public void setParentType(int parentType) {
        this.parentType = parentType;
    }
}
