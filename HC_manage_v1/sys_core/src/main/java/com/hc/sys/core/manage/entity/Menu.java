package com.hc.sys.core.manage.entity;

import com.hc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
* @Description: 菜单
* @Author: fangyong
* @CreateDate: 2018/10/15 16:46
* @Version: 1.0.0.0
*/
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name ="menu", uniqueConstraints = {@UniqueConstraint(columnNames="name")})
public class Menu extends LongPKEntity {

   /** 序列号 **/
   private static final long serialVersionUID = 1L;
   /** 菜单名称 **/
   private String name;
   /** 父级ID **/
   private long parentId;
   /** 链接地址 **/
   private String href;
   /** 类型 **/
   private int type;
   /** 状态0-未启用；1-启用 **/
   private int status;
   /** 排序 **/
   private int sort;
   /** 备注 **/
   private String remark;

   /**
    * 构造方法
    */
   public Menu() {
       super();
   }

   /**
    * 构造方法
    * @param id 主键
    */
   public Menu(long id) {
       super();
       this.setId(id);
   }


    /**
     * 获取 菜单名称
     *
     * @return name 菜单名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置 菜单名称
     *
     * @param name 菜单名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 父级ID
     *
     * @return parentId 父级ID
     */
    public long getParentId() {
        return this.parentId;
    }

    /**
     * 设置 父级ID
     *
     * @param parentId 父级ID
     */
    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取 链接地址
     *
     * @return href 链接地址
     */
    public String getHref() {
        return this.href;
    }

    /**
     * 设置 链接地址
     *
     * @param href 链接地址
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * 获取 类型
     *
     * @return type 类型
     */
    public int getType() {
        return this.type;
    }

    /**
     * 设置 类型
     *
     * @param type 类型
     */
    public void setType(int type) {
        this.type = type;
    }



    /**
     * 获取 排序
     *
     * @return sort 排序
     */
    public int getSort() {
        return this.sort;
    }

    /**
     * 设置 排序
     *
     * @param sort 排序
     */
    public void setSort(int sort) {
        this.sort = sort;
    }

    /**
     * 获取 备注
     *
     * @return remark 备注
     */
    public String getRemark() {
        return this.remark;
    }

    /**
     * 设置 备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 状态0-未启用；1-启用
     *
     * @return status 状态0-未启用；1-启用
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * 设置 状态0-未启用；1-启用
     *
     * @param status 状态0-未启用；1-启用
     */
    public void setStatus(int status) {
        this.status = status;
    }
}
