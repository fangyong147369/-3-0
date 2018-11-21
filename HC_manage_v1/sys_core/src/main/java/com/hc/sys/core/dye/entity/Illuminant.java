package com.hc.sys.core.dye.entity;
import com.hc.sys.common.entity.PrimaryKeyAngleAndIlluminant;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.io.Serializable;
/**
 * @Description: 光源表
 * @Author: fangyong
 * @CreateDate: 2018/10/18 13:40
 * @Version: 1.0.0.0
 */
@Entity
@Table(name ="illuminant")
@IdClass(PrimaryKeyAngleAndIlluminant.class)
@DynamicUpdate(true)
@DynamicInsert(true)
public class Illuminant implements Serializable {
    @Id
    private int id;
    @Id
    private float angle;
    /**描述 **/
    private String description;
    /**名称 **/
    private String name;

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
    /**
     * 获取 名称
     *
     * @return name 名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置 名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 @Id
     *
     * @return id @Id
     */
    public int getId() {
        return this.id;
    }

    /**
     * 设置 @Id
     *
     * @param id @Id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取 @Id
     *
     * @return angle @Id
     */
    public float getAngle() {
        return this.angle;
    }

    /**
     * 设置 @Id
     *
     * @param angle @Id
     */
    public void setAngle(float angle) {
        this.angle = angle;
    }
}
