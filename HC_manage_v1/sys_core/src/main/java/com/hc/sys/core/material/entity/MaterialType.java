package com.hc.sys.core.material.entity;
import com.hc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
/**
 * @Description: 材质类型
 * @Author: fangyong
 * @CreateDate: 2018/10/19 17:26
 * @Version: 1.0.0.0
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name ="material_type")
public class MaterialType extends LongPKEntity{
    /** 序列号 **/
    private static final long serialVersionUID = 1L;
    /**材质名称**/
    private String name;
    /**描述**/
    private String description;
    /**父Id**/
    private int parentId;

    /**
     * 获取 材质名称
     *
     * @return name 材质名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置 材质名称
     *
     * @param name 材质名称
     */
    public void setName(String name) {
        this.name = name;
    }




    /**
     * 获取 父Id
     *
     * @return parentId 父Id
     */
    public int getParentId() {
        return this.parentId;
    }

    /**
     * 设置 父Id
     *
     * @param parentId 父Id
     */
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取 描述
     *
     * @return description 描述
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * 设置 描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
