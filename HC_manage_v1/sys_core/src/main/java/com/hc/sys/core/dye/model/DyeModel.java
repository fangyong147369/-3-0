package com.hc.sys.core.dye.model;

import com.hc.sys.common.model.jpa.Page;
import com.hc.sys.core.dye.entity.Colorant;
import com.hc.sys.core.dye.entity.Dye;
import com.hc.sys.core.manage.model.OperatorModel;
import org.springframework.beans.BeanUtils;

/**
 * @Description: 染料
 * @Author: fangyong
 * @CreateDate: 2018/10/19 11:14
 * @Version: 1.0.0.0
 */
public class DyeModel extends Dye{
    /** 序列号 **/
    private static final long serialVersionUID = 1L;

    /** 当前页码 **/
    private int pageNo;
    /** 每页数据条数 **/
    private int pageSize = Page.ROWS;
    /** 条件查询 **/
    private String searchName;
    /** OperatorModel **/
    private OperatorModel operatorModel;
    /** dyeTypeModel **/
    private DyeTypeModel dyeTypeModel;
    /** dyeManufacturerModel **/
    private DyeManufacturerModel dyeManufacturerModel;
    /** dyeColorModel **/
    private DyeColorModel dyeColorModel;
    /** dyeManufacturerId **/
    private long dyeManufacturerId;
    /** operatoId **/
    private long operatoId;
    /** dyeTypeId **/
    private long dyeTypeId;
    /** dyeColorId **/
    private long dyeColorId;
    /**
     * 实体转换model
     */
    public static DyeModel instance( Dye dye) {
        if(dye==null||dye.getId()<0){
            return null;
        }
        DyeModel dyeModel = new DyeModel();
        BeanUtils.copyProperties(dye, dyeModel);
        return dyeModel;
    }

    /**
     * model转换实体
     */
    public Dye prototype() {
        Dye dye = new Dye();
        BeanUtils.copyProperties(this, dye);
        return dye;
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
     * 获取 OperatorModel
     * @return operatorModel OperatorModel
     */
    public OperatorModel getOperatorModel() {
        return this.operatorModel;
    }

    /**
     * 设置 OperatorModel
     * @param operatorModel OperatorModel
     */
    public void setOperatorModel(OperatorModel operatorModel) {
        this.operatorModel = operatorModel;
    }

    /**
     * 获取 operatoId
     *
     * @return operatoId operatoId
     */
    public long getOperatoId() {
        return this.operatoId;
    }

    /**
     * 设置 operatoId
     *
     * @param operatoId operatoId
     */
    public void setOperatoId(long operatoId) {
        this.operatoId = operatoId;
    }

    /**
     * 获取 dyeTypeId
     *
     * @return dyeTypeId dyeTypeId
     */
    public long getDyeTypeId() {
        return this.dyeTypeId;
    }

    /**
     * 设置 dyeTypeId
     *
     * @param dyeTypeId dyeTypeId
     */
    public void setDyeTypeId(long dyeTypeId) {
        this.dyeTypeId = dyeTypeId;
    }

    /**
     * 获取 dyeTypeModel
     *
     * @return dyeTypeModel dyeTypeModel
     */
    public DyeTypeModel getDyeTypeModel() {
        return this.dyeTypeModel;
    }

    /**
     * 设置 dyeTypeModel
     *
     * @param dyeTypeModel dyeTypeModel
     */
    public void setDyeTypeModel(DyeTypeModel dyeTypeModel) {
        this.dyeTypeModel = dyeTypeModel;
    }

    /**
     * 获取 dyeManufacturerModel
     *
     * @return dyeManufacturerModel dyeManufacturerModel
     */
    public DyeManufacturerModel getDyeManufacturerModel() {
        return this.dyeManufacturerModel;
    }

    /**
     * 设置 dyeManufacturerModel
     *
     * @param dyeManufacturerModel dyeManufacturerModel
     */
    public void setDyeManufacturerModel(DyeManufacturerModel dyeManufacturerModel) {
        this.dyeManufacturerModel = dyeManufacturerModel;
    }

    /**
     * 获取 dyeManufacturerId
     *
     * @return dyeManufacturerId dyeManufacturerId
     */
    public long getDyeManufacturerId() {
        return this.dyeManufacturerId;
    }

    /**
     * 设置 dyeManufacturerId
     *
     * @param dyeManufacturerId dyeManufacturerId
     */
    public void setDyeManufacturerId(long dyeManufacturerId) {
        this.dyeManufacturerId = dyeManufacturerId;
    }


    /**
     * 获取 dyeColorModel
     *
     * @return dyeColorModel dyeColorModel
     */
    public DyeColorModel getDyeColorModel() {
        return this.dyeColorModel;
    }

    /**
     * 设置 dyeColorModel
     *
     * @param dyeColorModel dyeColorModel
     */
    public void setDyeColorModel(DyeColorModel dyeColorModel) {
        this.dyeColorModel = dyeColorModel;
    }

    /**
     * 获取 dyeColorId
     *
     * @return dyeColorId dyeColorId
     */
    public long getDyeColorId() {
        return this.dyeColorId;
    }

    /**
     * 设置 dyeColorId
     *
     * @param dyeColorId dyeColorId
     */
    public void setDyeColorId(long dyeColorId) {
        this.dyeColorId = dyeColorId;
    }
}
