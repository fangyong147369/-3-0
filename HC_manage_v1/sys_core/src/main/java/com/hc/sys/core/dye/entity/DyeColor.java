package com.hc.sys.core.dye.entity;

import com.hc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Description: 染料颜色
 * @Author: fangyong
 * @CreateDate: 2018/10/19 11:38
 * @Version: 1.0.0.0
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name ="dye_color")
public class DyeColor extends LongPKEntity {
    /** 序列号 **/
    private static final long serialVersionUID = 1L;
    /**名称 **/
    private String name;
    /**别名**/
    private String alias;
    /**添加时间**/
    private Date addTime;
    /**rgb **/
    private int rgb;

    /**
     * 获取 名称
     *
     * @return name 名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置 名称
     *
     * @param name 名称
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
     * 获取 添加时间
     *
     * @return addTime 添加时间
     */
    public Date getAddTime() {
        return this.addTime;
    }

    /**
     * 设置 添加时间
     *
     * @param addTime 添加时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取 rgb
     *
     * @return rgb rgb
     */
    public int getRgb() {
        return this.rgb;
    }

    /**
     * 设置 rgb
     *
     * @param rgb rgb
     */
    public void setRgb(int rgb) {
        this.rgb = rgb;
    }
}
