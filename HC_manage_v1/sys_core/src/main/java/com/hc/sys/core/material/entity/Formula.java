package com.hc.sys.core.material.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hc.sys.common.entity.LongPKEntity;
import com.hc.sys.core.dye.entity.Colorant;
import com.hc.sys.core.dye.entity.DyeGroup;
import com.hc.sys.core.manage.entity.Operator;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @Description: 配方
 * @Author: fangyong
 * @CreateDate: 2018/10/19 11:31
 * @Version: 1.0.0.0
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name = "formula",uniqueConstraints = {@UniqueConstraint(columnNames="name")})
public class Formula extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;
    /**
     * 关联管理员对象
     */
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "operator_id")
    private Operator operator;
    /**
     * 状态 0：未上传云端，1：已经上传，2：云端验证通过，3：拒绝使用的数据
     **/
    private int status;
    /**
     * 配方名称
     **/
    private String name;
    /**
     * 色样编号
     **/
    private String sampleName;
    /**
     * 配方类型 0：实验室配方，1：车间配方
     **/
    private int type;
    /**
     * 关联材质对象
     */
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "material_id")
    private Material material;
    /**
     * 关联颜色对象
     */
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "colorant_id")
    private Colorant colorant;
    /**
     * 关联染料组对象
     */
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "dye_group_id")
    private DyeGroup dyeGroup;
   /**
     * 关联工艺对象
     */
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "technology_id")
    private Technology technology;

    /**
     * 获取浓度
     **/
    @OneToMany(mappedBy = "formula", fetch = FetchType.EAGER)
    private List<Concentration> concentration;
    /**
     * 添加时间
     **/
    private Date addTime;

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

     * @param operator 关联管理员对象
     */
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    /**
     * 获取 状态 0：未上传云端，1：已经上传，2：云端验证通过，3：拒绝使用的数据
     * @return status 状态 0：未上传云端，1：已经上传，2：云端验证通过，3：拒绝使用的数据
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * 设置 状态 0：未上传云端，1：已经上传，2：云端验证通过，3：拒绝使用的数据
     * @param status 状态 0：未上传云端，1：已经上传，2：云端验证通过，3：拒绝使用的数据
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * 获取 名称
     * @return name 名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置 名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 配方类型 0：实验室配方，1：车间配方
     * @return type 配方类型 0：实验室配方，1：车间配方
     */
    public int getType() {
        return this.type;
    }

    /**
     * 设置 配方类型 0：实验室配方，1：车间配方
     * @param type 配方类型 0：实验室配方，1：车间配方
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取 关联材质对象
     * @return material 关联材质对象
     */
    public Material getMaterial() {
        return this.material;
    }

    /**
     * 设置 关联材质对象
     * @param material 关联材质对象
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * 获取 关联颜色对象
     * @return colorant 关联颜色对象
     */
    public Colorant getColorant() {
        return this.colorant;
    }

    /**
     * 设置 关联颜色对象
     * @param colorant 关联颜色对象
     */
    public void setColorant(Colorant colorant) {
        this.colorant = colorant;
    }

    /**
     * 获取 关联染料组对象
     * @return dyeGroup 关联染料组对象
     */
    public DyeGroup getDyeGroup() {
        return this.dyeGroup;
    }

    /**
     * 设置 关联染料组对象
     * @param dyeGroup 关联染料组对象
     */
    public void setDyeGroup(DyeGroup dyeGroup) {
        this.dyeGroup = dyeGroup;
    }

    /**
     * 获取 关联工艺对象
     * @return technology 关联工艺对象
     */
    public Technology getTechnology() {
        return this.technology;
    }

    /**
     * 设置 关联工艺对象
     * @param technology 关联工艺对象
     */
    public void setTechnology(Technology technology) {
        this.technology = technology;
    }


    /**
     * 获取 添加时间
     * @return addTime 添加时间
     */
    public Date getAddTime() {
        return this.addTime;
    }

    /**
     * 设置 添加时间
     * @param addTime 添加时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取 色样编号
     *
     * @return sampleName 色样编号
     */
    public String getSampleName() {
        return this.sampleName;
    }

    /**
     * 设置 色样编号
     *
     * @param sampleName 色样编号
     */
    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }


    /**
     * 获取 获取浓度
     *
     * @return concentration 获取浓度
     */
    public List<Concentration> getConcentration() {
        return this.concentration;
    }

    /**
     * 设置 获取浓度
     *
     * @param concentration 获取浓度
     */
    public void setConcentration(List<Concentration> concentration) {
        this.concentration = concentration;
    }

}
