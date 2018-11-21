package com.hc.sys.core.dye.model;
import com.hc.sys.common.model.jpa.Page;
import com.hc.sys.core.dye.entity.Illuminantwhitexyz;
import org.springframework.beans.BeanUtils;
/**
 * @Description: 描述
 * @Author: fangyong
 * @CreateDate: 2018/11/5 10:26
 * @Version: 1.0.0.0
 */
public class IlluminantwhitexyzModel extends Illuminantwhitexyz {
    /** 序列号 **/
    private static final int serialVersionUID = 1;
    /** 当前页码 **/
    private int pageNo;
    /** 每页数据条数 **/
    private int pageSize = Page.ROWS;
    /** 条件查询 **/
    private String searchName;

    /**
     * 实体转换model
     */
    public static IlluminantwhitexyzModel instance(Illuminantwhitexyz illuminantwhitexyz) {
        IlluminantwhitexyzModel illuminantwhitexyzModel = new IlluminantwhitexyzModel();
        BeanUtils.copyProperties(illuminantwhitexyz, illuminantwhitexyzModel);
        return illuminantwhitexyzModel;
    }

    /**
     * model转换实体
     */
    public Illuminantwhitexyz prototype() {
        Illuminantwhitexyz illuminantwhitexyz = new Illuminantwhitexyz();
        BeanUtils.copyProperties(this, illuminantwhitexyz);
        return illuminantwhitexyz;
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
