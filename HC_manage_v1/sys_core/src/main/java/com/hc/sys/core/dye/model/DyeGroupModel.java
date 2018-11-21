package com.hc.sys.core.dye.model;
import com.hc.sys.common.model.jpa.Page;
import com.hc.sys.core.dye.entity.DyeGroup;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @Description: 染料组
 * @Author: fangyong
 * @CreateDate: 2018/10/19 11:14
 * @Version: 1.0.0.0
 */
public class DyeGroupModel extends DyeGroup{
    /** 序列号 **/
    private static final long serialVersionUID = 1L;

    /** 当前页码 **/
    private int pageNo;
    /** 每页数据条数 **/
    private int pageSize = Page.ROWS;
    /** 条件查询 **/
    private String searchName;
    /** dyeIds **/
    private String dyeIds;
    /**
     * 实体转换model
     */
    public static DyeGroupModel instance(DyeGroup dyeGroup) {
        if(dyeGroup==null||dyeGroup.getId()<0){
            return null;
        }
        DyeGroupModel dyeGroupModel = new DyeGroupModel();
        BeanUtils.copyProperties(dyeGroup, dyeGroupModel);
        return dyeGroupModel;
    }

    /**
     * model转换实体
     */
    public DyeGroup prototype() {
        DyeGroup dyeGroup = new DyeGroup();
        BeanUtils.copyProperties(this, dyeGroup);
        return dyeGroup;
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
     * 获取 dyeIds
     *
     * @return dyeIds dyeIds
     */
    public String getDyeIds() {
        return this.dyeIds;
    }

    /**
     * 设置 dyeIds
     *
     * @param dyeIds dyeIds
     */
    public void setDyeIds(String dyeIds) {
        this.dyeIds = dyeIds;
    }
}
