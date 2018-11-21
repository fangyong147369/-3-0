package com.hc.sys.core.dye.entity;

import com.hc.sys.common.entity.PrimaryKeyAngleAndIlluminant;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description: 描述
 * @Author: fangyong
 * @CreateDate: 2018/11/7 11:42
 * @Version: 1.0.0.0
 */
@Entity
@Table(name ="illuminantwhitexyz")
@IdClass(PrimaryKeyAngleAndIlluminant.class)
@DynamicUpdate(true)
@DynamicInsert(true)
public class Illuminantwhitexyz implements Serializable {
    @Id
    private int id;
    @Id
    private float angle;
    /**X **/
    private float X;
    /**Y **/
    private float Y;
    /**Z **/
    private float Z;
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

    /**
     * 获取 X
     *
     * @return X X
     */
    public float getX() {
        return this.X;
    }

    /**
     * 设置 X
     *
     * @param X X
     */
    public void setX(float X) {
        this.X = X;
    }

    /**
     * 获取 Y
     *
     * @return Y Y
     */
    public float getY() {
        return this.Y;
    }

    /**
     * 设置 Y
     *
     * @param Y Y
     */
    public void setY(float Y) {
        this.Y = Y;
    }

    /**
     * 获取 Z
     *
     * @return Z Z
     */
    public float getZ() {
        return this.Z;
    }

    /**
     * 设置 Z
     *
     * @param Z Z
     */
    public void setZ(float Z) {
        this.Z = Z;
    }
}
