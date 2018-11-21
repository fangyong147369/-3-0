package com.hc.sys.core.material.model;
import com.hc.sys.common.model.jpa.Page;
import com.hc.sys.core.dye.model.ColorantModel;
import com.hc.sys.core.manage.model.OperatorModel;
import com.hc.sys.core.material.entity.Material;
import org.springframework.beans.BeanUtils;

/**
 * @Description: 材质
 * @Author: fangyong
 * @CreateDate: 2018/10/19 10:41
 * @Version: 1.0.0.0
 */
public class MaterialModel extends Material {
    /** 序列号 **/
    private static final long serialVersionUID = 1L;

    /** 当前页码 **/
    private int pageNo;
    /** 每页数据条数 **/
    private int pageSize = Page.ROWS;
    /** 条件查询 **/
    private String searchName;
    /** operatorModel **/
    private OperatorModel operatorModel;
    /** colorantModel **/
    private ColorantModel colorantModel;
    /** materialTypeModel **/
    private MaterialTypeModel materialTypeModel;
    /** operatorId **/
    private long operatorId;
    /** colorantId **/
    private long colorantId;
    /** materialTypeId **/
    private long materialTypeId;
    /**
     * 实体转换model
     */
    public static MaterialModel instance(Material material) {
        if(material==null||material.getId()<0){
            return null;
        }
        MaterialModel materialModel = new MaterialModel();
        BeanUtils.copyProperties(material, materialModel);
        return materialModel;
    }

    /**
     * model转换实体
     */
    public Material prototype() {
        Material material= new Material();
        BeanUtils.copyProperties(this, material);
        return material;
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
     * 获取 materialTypeModel
     *
     * @return materialTypeModel materialTypeModel
     */
    public MaterialTypeModel getMaterialTypeModel() {
        return this.materialTypeModel;
    }

    /**
     * 设置 materialTypeModel
     *
     * @param materialTypeModel materialTypeModel
     */
    public void setMaterialTypeModel(MaterialTypeModel materialTypeModel) {
        this.materialTypeModel = materialTypeModel;
    }

    /**
     * 获取 operatorId
     *
     * @return operatorId operatorId
     */
    public long getOperatorId() {
        return this.operatorId;
    }

    /**
     * 设置 operatorId
     *
     * @param operatorId operatorId
     */
    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * 获取 colorantId
     *
     * @return colorantId colorantId
     */
    public long getColorantId() {
        return this.colorantId;
    }

    /**
     * 设置 colorantId
     *
     * @param colorantId colorantId
     */
    public void setColorantId(long colorantId) {
        this.colorantId = colorantId;
    }

    /**
     * 获取 materialTypeId
     *
     * @return materialTypeId materialTypeId
     */
    public long getMaterialTypeId() {
        return this.materialTypeId;
    }

    /**
     * 设置 materialTypeId
     *
     * @param materialTypeId materialTypeId
     */
    public void setMaterialTypeId(long materialTypeId) {
        this.materialTypeId = materialTypeId;
    }

    /**
     * 获取 operatorModel
     *
     * @return operatorModel operatorModel
     */
    public OperatorModel getOperatorModel() {
        return this.operatorModel;
    }

    /**
     * 设置 operatorModel
     *
     * @param operatorModel operatorModel
     */
    public void setOperatorModel(OperatorModel operatorModel) {
        this.operatorModel = operatorModel;
    }
}
