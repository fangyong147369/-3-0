package com.hc.sys.core.material.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hc.sys.common.entity.LongPKEntity;
import com.hc.sys.core.dye.entity.Colorant;
import com.hc.sys.core.manage.entity.Operator;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description: 材质
 * @Author: fangyong
 * @CreateDate: 2018/10/19 10:37
 * @Version: 1.0.0.0
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name ="material")
public class Material extends LongPKEntity{
    /** 序列号 **/
    private static final long serialVersionUID = 1L;
    /**染料云端id，如果没有同步过，那么是0 **/
    private int cloudId;
    /**状态 0：未上传云端，1：已经上传，2：云端验证通过，3：拒绝使用的数据**/
    private int status;
    /** 关联管理员对象*/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "operator_id")
    private Operator operator;
    /** 关联颜色对象*/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "colorant_id")
    private Colorant colorant;
    /** 关联材质类型对象*/
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "material_type_id")
    private MaterialType materialType;
    /**材质名称**/
    private String name;
    /**材质别名**/
    private String alias;
    /**描述**/
    private String description;
    /**是否浸染（1是，2否）默认1**/
    private int dipDyeing;
    /**克重，单位克**/
    private float weight;
    /**浴比**/
    private float liquorRatio;
    /**添加时间**/
    private Date addTime;

    /**
     * 获取 染料云端id，如果没有同步过，那么是0
     *
     * @return cloudId 染料云端id，如果没有同步过，那么是0
     */
    public int getCloudId() {
        return this.cloudId;
    }

    /**
     * 设置 染料云端id，如果没有同步过，那么是0
     *
     * @param cloudId 染料云端id，如果没有同步过，那么是0
     */
    public void setCloudId(int cloudId) {
        this.cloudId = cloudId;
    }

    /**
     * 获取 状态 0：未上传云端，1：已经上传，2：云端验证通过，3：拒绝使用的数据
     *
     * @return status 状态 0：未上传云端，1：已经上传，2：云端验证通过，3：拒绝使用的数据
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * 设置 状态 0：未上传云端，1：已经上传，2：云端验证通过，3：拒绝使用的数据
     *
     * @param status 状态 0：未上传云端，1：已经上传，2：云端验证通过，3：拒绝使用的数据
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * 获取 关联管理员对象
     *
     * @return operator 关联管理员对象
     */
    public Operator getOperator() {
        return this.operator;
    }

    /**
     * 设置 关联管理员对象
     *
     * @param operator 关联管理员对象
     */
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    /**
     * 获取 关联颜色对象
     *
     * @return colorant 关联颜色对象
     */
    public Colorant getColorant() {
        return this.colorant;
    }

    /**
     * 设置 关联颜色对象
     *
     * @param colorant 关联颜色对象
     */
    public void setColorant(Colorant colorant) {
        this.colorant = colorant;
    }

    /**
     * 获取 关联材质类型对象
     *
     * @return materialType 关联材质类型对象
     */
    public MaterialType getMaterialType() {
        return this.materialType;
    }

    /**
     * 设置 关联材质类型对象
     *
     * @param materialType 关联材质类型对象
     */
    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    /**
     * 获取 材质名称
     *
     * @return name 材质名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置 材质名称
     *
     * @param name 材质名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 材质别名
     *
     * @return alias 材质别名
     */
    public String getAlias() {
        return this.alias;
    }

    /**
     * 设置 材质别名
     *
     * @param alias 材质别名
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
     * 获取 克重，单位克
     *
     * @return weight 克重，单位克
     */
    public float getWeight() {
        return this.weight;
    }

    /**
     * 设置 克重，单位克
     *
     * @param weight 克重，单位克
     */
    public void setWeight(float weight) {
        this.weight = weight;
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
     * 获取 是否浸染（1是，2否）默认1
     *
     * @return dipDyeing 是否浸染（1是，2否）默认1
     */
    public int getDipDyeing() {
        return this.dipDyeing;
    }

    /**
     * 设置 是否浸染（1是，2否）默认1
     *
     * @param dipDyeing 是否浸染（1是，2否）默认1
     */
    public void setDipDyeing(int dipDyeing) {
        this.dipDyeing = dipDyeing;
    }

    /**
     * 获取 浴比
     *
     * @return liquorRatio 浴比
     */
    public float getLiquorRatio() {
        return this.liquorRatio;
    }

    /**
     * 设置 浴比
     *
     * @param liquorRatio 浴比
     */
    public void setLiquorRatio(float liquorRatio) {
        this.liquorRatio = liquorRatio;
    }

}
