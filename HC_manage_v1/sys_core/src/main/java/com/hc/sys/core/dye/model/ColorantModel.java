package com.hc.sys.core.dye.model;

import com.hc.sys.common.model.jpa.Page;
import com.hc.sys.core.dye.entity.Colorant;
import org.springframework.beans.BeanUtils;

/**
 * @Description: 配方颜色
 * @Author: fangyong
 * @CreateDate: 2018/10/18 13:54
 * @Version: 1.0.0.0
 */
public class ColorantModel  extends Colorant {
    /** 序列号 **/
    private static final long serialVersionUID = 1L;

    /** 当前页码 **/
    private int pageNo;
    /** 每页数据条数 **/
    private int pageSize = Page.ROWS;
    /** 条件查询 **/
    private String searchName;
    /** IlluminantModel **/
    private IlluminantModel illuminantModel;
    /**
     * 实体转换model
     */
    public static ColorantModel instance(Colorant colorant) {
        if(colorant==null||colorant.getId()<0){
            return null;
        }
        ColorantModel colorantModel = new ColorantModel();
        BeanUtils.copyProperties(colorant, colorantModel);
        return colorantModel;
    }

    /**
     * model转换实体
     */
    public Colorant prototype() {
        Colorant colorant = new Colorant();
        BeanUtils.copyProperties(this, colorant);
        return colorant;
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
     * 获取 IlluminantModel
     *
     * @return illuminantModel IlluminantModel
     */
    public IlluminantModel getIlluminantModel() {
        return this.illuminantModel;
    }

    /**
     * 设置 IlluminantModel
     *
     * @param illuminantModel IlluminantModel
     */
    public void setIlluminantModel(IlluminantModel illuminantModel) {
        this.illuminantModel = illuminantModel;
    }


}
