package com.hc.sys.core.material.model;
import com.hc.sys.common.model.jpa.Page;
import com.hc.sys.core.dye.model.DyeingTankModel;
import com.hc.sys.core.material.entity.Technology;
import org.springframework.beans.BeanUtils;
/**
 * @Description: 工艺
 * @Author: fangyong
 * @CreateDate: 2018/10/19 10:49
 * @Version: 1.0.0.0
 */
public class TechnologyModel extends Technology {
    /** 序列号 **/
    private static final long serialVersionUID = 1L;

    /** 当前页码 **/
    private int pageNo;
    /** 每页数据条数 **/
    private int pageSize = Page.ROWS;
    /** 条件查询 **/
    private String searchName;
    /** dyeingTankModel **/
    private DyeingTankModel dyeingTankModel;
    /** factoryModel **/
    private FactoryModel factoryModel;
    /** dyeingTankId **/
    private long dyeingTankId;
    /** factoryId **/
    private long factoryId;

    /**
     * 实体转换model
     */
    public static TechnologyModel instance(Technology technology) {
        if(technology==null||technology.getId()<0){
            return null;
        }
        TechnologyModel technologyModel = new TechnologyModel();
        BeanUtils.copyProperties(technology, technologyModel);
        return technologyModel;
    }

    /**
     * model转换实体
     */
    public Technology prototype() {
        Technology technology= new Technology();
        BeanUtils.copyProperties(this, technology);
        return technology;
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
     * 获取 dyeingTankModel
     *
     * @return dyeingTankModel dyeingTankModel
     */
    public DyeingTankModel getDyeingTankModel() {
        return this.dyeingTankModel;
    }

    /**
     * 设置 dyeingTankModel
     *
     * @param dyeingTankModel dyeingTankModel
     */
    public void setDyeingTankModel(DyeingTankModel dyeingTankModel) {
        this.dyeingTankModel = dyeingTankModel;
    }

    /**
     * 获取 dyeingTankId
     *
     * @return dyeingTankId dyeingTankId
     */
    public long getDyeingTankId() {
        return this.dyeingTankId;
    }

    /**
     * 设置 dyeingTankId
     *
     * @param dyeingTankId dyeingTankId
     */
    public void setDyeingTankId(long dyeingTankId) {
        this.dyeingTankId = dyeingTankId;
    }

    /**
     * 获取 factoryId
     *
     * @return factoryId factoryId
     */
    public long getFactoryId() {
        return this.factoryId;
    }

    /**
     * 设置 factoryId
     *
     * @param factoryId factoryId
     */
    public void setFactoryId(long factoryId) {
        this.factoryId = factoryId;
    }

    /**
     * 获取 factoryModel
     *
     * @return factoryModel factoryModel
     */
    public FactoryModel getFactoryModel() {
        return this.factoryModel;
    }

    /**
     * 设置 factoryModel
     *
     * @param factoryModel factoryModel
     */
    public void setFactoryModel(FactoryModel factoryModel) {
        this.factoryModel = factoryModel;
    }
}
