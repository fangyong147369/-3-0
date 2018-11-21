package com.hc.sys.core.material.entity;

import com.hc.sys.common.entity.PrimaryKeyAndFormulaID;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description: 配方
 * @Author: fangyong
 * @CreateDate: 2018/10/19 11:31
 * @Version: 1.0.0.0
 */
@Entity
@IdClass(PrimaryKeyAndFormulaID.class)
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = "formula_by_lab")
public class FormulaByLab implements Serializable {
    @Id
    private int keyId;
    @Id
    private int formulaId;
    /**
     * 层数
     **/
    private int layer;
    /**
     * 配方类型
     **/
    private int formulaType;
    /**
     * 关联材质Id
     */
    private int materialId;
    /**
     * 关联染料组Id
     */
    private int dyeGroupId;
    /**
     * 目标颜色亮度（L）
     **/
    private float l;
    /**
     * 目标颜色绿红（a）
     **/
    private float a;
    /**
     * 目标颜色蓝黄（b）
     **/
    private float b;

    public int getKeyId() {
        return keyId;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    public int getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(int formulaId) {
        this.formulaId = formulaId;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public int getFormulaType() {
        return formulaType;
    }

    public void setFormulaType(int formulaType) {
        this.formulaType = formulaType;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public int getDyeGroupId() {
        return dyeGroupId;
    }

    public void setDyeGroupId(int dyeGroupId) {
        this.dyeGroupId = dyeGroupId;
    }

    public float getL() {
        return l;
    }

    public void setL(float l) {
        this.l = l;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }
}
