package com.hc.sys.core.dye.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hc.sys.common.entity.LongPKEntity;
import com.hc.sys.core.manage.entity.Operator;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.util.Date;
/**
 * @Description: 染料
 * @Author: fangyong
 * @CreateDate: 2018/10/19 10:28
 * @Version: 1.0.0.0
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name ="dye")
public class Dye extends LongPKEntity {
    /** 序列号 **/
    private static final long serialVersionUID = 1L;
    /**燃料名称**/
    private String name;
    /****/
    private String alias;
    /**价格**/
    private double price;
    /****/
    private int exterior;
    /** 关联管理员对象*/
    @JsonBackReference
    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "operator_id")
    private Operator operator;
    /**染料云端id，如果没有同步过，那么是0 **/
    private int cloudId;
    /**状态 0：未上传云端，1：已经上传，2：云端验证通过，3：拒绝使用的数据**/
    private int status;
    /**染料力份**/
    private float strength;
    /**关联染料类型对象**/
    @JsonBackReference
    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "dye_type_id")
    private DyeType dyeType;

    /** 关联颜色对象*/
    @JsonBackReference
    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "color_id")
    private DyeColor dyeColor;
    /** 关联染料厂商对象*/
    @JsonBackReference
    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "manufacturer_id")
    private DyeManufacturer dyeManufacturer;
    /**描述**/
    private String description;
    /**添加时间**/
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
     *
     * @param operator 关联管理员对象
     */
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

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
     * 获取 染料力份
     *
     * @return strength 染料力份
     */
    public float getStrength() {
        return this.strength;
    }

    /**
     * 设置 染料力份
     *
     * @param strength 染料力份
     */
    public void setStrength(float strength) {
        this.strength = strength;
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
     * 获取 关联染料类型对象
     *
     * @return dyeType 关联染料类型对象
     */
    public DyeType getDyeType() {
        return this.dyeType;
    }

    /**
     * 设置 关联染料类型对象
     *
     * @param dyeType 关联染料类型对象
     */
    public void setDyeType(DyeType dyeType) {
        this.dyeType = dyeType;
    }



    /**
     * 获取 关联染料厂商对象
     *
     * @return dyeManufacturer 关联染料厂商对象
     */
    public DyeManufacturer getDyeManufacturer() {
        return this.dyeManufacturer;
    }

    /**
     * 设置 关联染料厂商对象
     *
     * @param dyeManufacturer 关联染料厂商对象
     */
    public void setDyeManufacturer(DyeManufacturer dyeManufacturer) {
        this.dyeManufacturer = dyeManufacturer;
    }

    /**
     * 获取 燃料名称
     *
     * @return name 燃料名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置 燃料名称
     *
     * @param name 燃料名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     *
     * @return alias
     */
    public String getAlias() {
        return this.alias;
    }

    /**
     * 设置
     *
     * @param alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * 获取 价格
     *
     * @return price 价格
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * 设置 价格
     *
     * @param price 价格
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * 获取
     *
     * @return exterior
     */
    public int getExterior() {
        return this.exterior;
    }

    /**
     * 设置
     *
     * @param exterior
     */
    public void setExterior(int exterior) {
        this.exterior = exterior;
    }


    /**
     * 获取 关联颜色对象
     *
     * @return dyeColor 关联颜色对象
     */
    public DyeColor getDyeColor() {
        return this.dyeColor;
    }

    /**
     * 设置 关联颜色对象
     *
     * @param dyeColor 关联颜色对象
     */
    public void setDyeColor(DyeColor dyeColor) {
        this.dyeColor = dyeColor;
    }
}
