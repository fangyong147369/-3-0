package com.hc.sys.core.dye.model;

import com.hc.sys.common.model.jpa.Page;
import com.hc.sys.core.dye.entity.DyeingTank;
import org.springframework.beans.BeanUtils;
/**
 * @Description: 染缸
 * @Author: fangyong
 * @CreateDate: 2018/10/19 11:14
 * @Version: 1.0.0.0
 */
public class DyeingTankModel extends DyeingTank {
    /** 序列号 **/
    private static final long serialVersionUID = 1L;

    /** 当前页码 **/
    private int pageNo;
    /** 每页数据条数 **/
    private int pageSize = Page.ROWS;
    /** 条件查询 **/
    private String searchName;
    /** DyeManufacturerModel **/
    private DyeManufacturerModel dyeManufacturerModel;
    /** dyeingTankManufacturerId **/
    private long dyeingTankManufacturerId;
    /**
     * 实体转换model
     */
    public static DyeingTankModel instance(DyeingTank dyeingTank) {
        if(dyeingTank==null||dyeingTank.getId()<0){
            return null;
        }
        DyeingTankModel dyeingTankModel = new DyeingTankModel();
        BeanUtils.copyProperties(dyeingTank, dyeingTankModel);
        return dyeingTankModel;
    }

    /**
     * model转换实体
     */
    public DyeingTank prototype() {
        DyeingTank dyeingTank = new DyeingTank();
        BeanUtils.copyProperties(this, dyeingTank);
        return dyeingTank;
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
     * 获取 DyeManufacturerModel
     *
     * @return dyeManufacturerModel DyeManufacturerModel
     */
    public DyeManufacturerModel getDyeManufacturerModel() {
        return this.dyeManufacturerModel;
    }

    /**
     * 设置 DyeManufacturerModel
     *
     * @param dyeManufacturerModel DyeManufacturerModel
     */
    public void setDyeManufacturerModel(DyeManufacturerModel dyeManufacturerModel) {
        this.dyeManufacturerModel = dyeManufacturerModel;
    }


    /**
     * 获取 dyeingTankManufacturerId
     *
     * @return dyeingTankManufacturerId dyeingTankManufacturerId
     */
    public long getDyeingTankManufacturerId() {
        return this.dyeingTankManufacturerId;
    }

    /**
     * 设置 dyeingTankManufacturerId
     *
     * @param dyeingTankManufacturerId dyeingTankManufacturerId
     */
    public void setDyeingTankManufacturerId(long dyeingTankManufacturerId) {
        this.dyeingTankManufacturerId = dyeingTankManufacturerId;
    }
}
