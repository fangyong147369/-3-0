package com.hc.sys.core.material.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hc.sys.common.entity.LongPKEntity;
import com.hc.sys.core.dye.entity.Colorant;
import com.hc.sys.core.dye.entity.Dye;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
/**
 * @Description: 材质打样
 * @Author: fangyong
 * @CreateDate: 2018/11/5 11:01
 * @Version: 1.0.0.0
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name ="material_proofing")
public class MaterialProofing extends LongPKEntity {
    /** 序列号 **/
    private static final long serialVersionUID = 1L;
    /**状态 0：未上传云端，1：已经上传，2：云端验证通过，3：拒绝使用的数据**/
    private int status;
    /**浓度**/
    private float conc;

    public MaterialProofing() {
    }

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
    @JoinColumn(name = "dye_id")
    private Dye dye;

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
     * 获取 浓度
     *
     * @return conc 浓度
     */
    public float getConc() {
        return this.conc;
    }

    /**
     * 设置 浓度
     *
     * @param conc 浓度
     */
    public void setConc(float conc) {
        this.conc = conc;
    }

    /**
     * 获取 关联材质对象
     *
     * @return material 关联材质对象
     */
    public Material getMaterial() {
        return this.material;
    }

    /**
     * 设置 关联材质对象
     *
     * @param material 关联材质对象
     */
    public void setMaterial(Material material) {
        this.material = material;
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
     * 获取 关联染料组对象
     *
     * @return dye 关联染料组对象
     */
    public Dye getDye() {
        return this.dye;
    }

    /**
     * 设置 关联染料组对象
     *
     * @param dye 关联染料组对象
     */
    public void setDye(Dye dye) {
        this.dye = dye;
    }
}
