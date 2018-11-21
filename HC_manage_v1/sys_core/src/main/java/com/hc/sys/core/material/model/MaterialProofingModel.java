package com.hc.sys.core.material.model;

import com.hc.sys.common.model.jpa.Page;
import com.hc.sys.core.dye.model.ColorantModel;
import com.hc.sys.core.dye.model.DyeGroupModel;
import com.hc.sys.core.dye.model.DyeModel;
import com.hc.sys.core.manage.model.OperatorModel;
import com.hc.sys.core.material.entity.Formula;
import com.hc.sys.core.material.entity.MaterialProofing;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @Description: 材质打样
 * @Author: fangyong
 * @CreateDate: 2018/10/19 17:41
 * @Version: 1.0.0.0
 */
public class MaterialProofingModel extends MaterialProofing {
    /** 序列号 **/
    private static final long serialVersionUID = 1L;

    /** 当前页码 **/
    private int pageNo;
    /** 每页数据条数 **/
    private int pageSize = Page.ROWS;
    /** 条件查询 **/
    private String searchName;
    /** materialModel **/
    private MaterialModel materialModel;
    /** colorantModel **/
    private ColorantModel colorantModel;
    /** dyeGroupModel **/
    private DyeModel dyeModel;
    /** materialModel **/
    private long materialId;
    /** colorantModel **/
    private long colorantId;
    /** dyeId **/
    private long dyeId;

    /**
     * 实体转换model
     */
    public static MaterialProofingModel instance(MaterialProofing materialProofing) {
        if(materialProofing==null||materialProofing.getId()<0){
            return null;
        }
        MaterialProofingModel materialProofingModel = new MaterialProofingModel();
        BeanUtils.copyProperties(materialProofing, materialProofingModel);
        return materialProofingModel;
    }

    /**
     * model转换实体
     */
    public MaterialProofing prototype() {
        MaterialProofing materialProofing= new MaterialProofing();
        BeanUtils.copyProperties(this, materialProofing);
        return materialProofing;
    }


    /**
     * 获取 当前页码
     *
     * @return pageNo 当前页码
     */
    public int getPageNo() {
        return this.pageNo;
    }

    /**
     * 设置 当前页码
     *
     * @param pageNo 当前页码
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * 获取 每页数据条数
     *
     * @return pageSize 每页数据条数
     */
    public int getPageSize() {
        return this.pageSize;
    }

    /**
     * 设置 每页数据条数
     *
     * @param pageSize 每页数据条数
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取 条件查询
     *
     * @return searchName 条件查询
     */
    public String getSearchName() {
        return this.searchName;
    }

    /**
     * 设置 条件查询
     *
     * @param searchName 条件查询
     */
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }


    /**
     * 获取 materialModel
     *
     * @return materialModel materialModel
     */
    public MaterialModel getMaterialModel() {
        return this.materialModel;
    }

    /**
     * 设置 materialModel
     *
     * @param materialModel materialModel
     */
    public void setMaterialModel(MaterialModel materialModel) {
        this.materialModel = materialModel;
    }

    /**
     * 获取 colorantModel
     *
     * @return colorantModel colorantModel
     */
    public ColorantModel getColorantModel() {
        return this.colorantModel;
    }

    /**
     * 设置 colorantModel
     *
     * @param colorantModel colorantModel
     */
    public void setColorantModel(ColorantModel colorantModel) {
        this.colorantModel = colorantModel;
    }

    /**
     * 获取 materialModel
     *
     * @return materialId materialModel
     */
    public long getMaterialId() {
        return this.materialId;
    }

    /**
     * 设置 materialModel
     *
     * @param materialId materialModel
     */
    public void setMaterialId(long materialId) {
        this.materialId = materialId;
    }

    /**
     * 获取 colorantModel
     *
     * @return colorantId colorantModel
     */
    public long getColorantId() {
        return this.colorantId;
    }

    /**
     * 设置 colorantModel
     *
     * @param colorantId colorantModel
     */
    public void setColorantId(long colorantId) {
        this.colorantId = colorantId;
    }

    /**
     * 获取 dyeGroupModel
     *
     * @return dyeModel dyeGroupModel
     */
    public DyeModel getDyeModel() {
        return this.dyeModel;
    }

    /**
     * 设置 dyeGroupModel
     *
     * @param dyeModel dyeGroupModel
     */
    public void setDyeModel(DyeModel dyeModel) {
        this.dyeModel = dyeModel;
    }

    /**
     * 获取 dyeId
     *
     * @return dyeId dyeId
     */
    public long getDyeId() {
        return this.dyeId;
    }

    /**
     * 设置 dyeId
     *
     * @param dyeId dyeId
     */
    public void setDyeId(long dyeId) {
        this.dyeId = dyeId;
    }

}
