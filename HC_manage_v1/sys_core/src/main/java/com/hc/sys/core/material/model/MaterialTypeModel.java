package com.hc.sys.core.material.model;
import com.hc.sys.common.model.jpa.Page;
import com.hc.sys.core.dye.model.IlluminantModel;
import com.hc.sys.core.material.entity.MaterialType;
import org.springframework.beans.BeanUtils;

/**
 * @Description:  材质类型
 * @Author: fangyong
 * @CreateDate: 2018/10/19 10:41
 * @Version: 1.0.0.0
 */
public class MaterialTypeModel extends MaterialType {
    /** 序列号 **/
    private static final long serialVersionUID = 1L;

    /** 当前页码 **/
    private int pageNo;
    /** 每页数据条数 **/
    private int pageSize = Page.ROWS;
    /** 条件查询 **/
    private String searchName;

    /**
     * 实体转换model
     */
    public static MaterialTypeModel instance(MaterialType materialType) {
        if(materialType==null||materialType.getId()<0){
            return null;
        }
        MaterialTypeModel materialTypeModel = new MaterialTypeModel();
        BeanUtils.copyProperties(materialType, materialTypeModel);
        return materialTypeModel;
    }

    /**
     * model转换实体
     */
    public MaterialType prototype() {
        MaterialType materialType= new MaterialType();
        BeanUtils.copyProperties(this, materialType);
        return materialType;
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

}
