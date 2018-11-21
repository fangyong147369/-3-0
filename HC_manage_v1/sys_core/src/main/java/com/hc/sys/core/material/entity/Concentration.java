package com.hc.sys.core.material.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hc.sys.common.entity.LongPKEntity;
import com.hc.sys.core.dye.entity.Colorant;
import com.hc.sys.core.dye.entity.Dye;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @Description: 浓度
 * @Author: fangyong
 * @CreateDate: 2018/10/19 11:41
 * @Version: 1.0.0.0
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name ="concentration")
public class Concentration extends LongPKEntity {
    /** 序列号 **/
    private static final long serialVersionUID = 1L;

    /** 关联Dye对象*/
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "dye_id")
    private Dye dye;
    /**0表示浓度100% 默认为0**/
    private float concentration;
    /**
     * 关联配方对象
     */
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "formula_id")
    private Formula formula;
    /**
     * 获取 关联Dye对象
     *
     * @return dye 关联Dye对象
     */
    public Dye getDye() {
        return this.dye;
    }

    /**
     * 设置 关联Dye对象
     *
     * @param dye 关联Dye对象
     */
    public void setDye(Dye dye) {
        this.dye = dye;
    }

    /**
     * 获取 0表示浓度100% 默认为0
     *
     * @return concentration 0表示浓度100% 默认为0
     */
    public float getConcentration() {
        return this.concentration;
    }

    /**
     * 设置 0表示浓度100% 默认为0
     *
     * @param concentration 0表示浓度100% 默认为0
     */
    public void setConcentration(float concentration) {
        this.concentration = concentration;
    }

    /**
     * 获取 关联配方对象
     *
     * @return formula 关联配方对象
     */
    public Formula getFormula() {
        return this.formula;
    }

    /**
     * 设置 关联配方对象
     *
     * @param formula 关联配方对象
     */
    public void setFormula(Formula formula) {
        this.formula = formula;
    }
}
