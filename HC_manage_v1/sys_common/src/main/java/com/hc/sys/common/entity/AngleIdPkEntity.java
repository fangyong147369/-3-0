package com.hc.sys.common.entity;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @Description: 描述
 * @Author: fangyong
 * @CreateDate: 2018/11/2 17:47
 * @Version: 1.0.0.0
 */
@Embeddable
public class AngleIdPkEntity implements Serializable {
    private int id;
    private float angle;

    public AngleIdPkEntity() {
    }
    public AngleIdPkEntity(int id, float angle) {
        this.id = id;
        this.angle = angle;
    }

    @Override
    public boolean equals(Object o) {
        return o!=null&&this.getClass()==o.getClass()&&this.toString().equals(o.toString());
    }

    @Override
    public int hashCode() {

        return Objects.hash(this.getAngle(), this.getId());
    }
    /**
     * 获取 @Column(length=20name="id")
     *
     * @return id @Column(length=20name="id")
     */
    @Transient
    public int getId() {
        return this.id;
    }

    /**
     * 设置 @Column(length=20name="id")
     *
     * @param id @Column(length=20name="id")
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取 @Column(length=20name="angle")
     *
     * @return angle @Column(length=20name="angle")
     */

    public float getAngle() {
        return this.angle;
    }

    /**
     * 设置 @Column(length=20name="angle")
     *
     * @param angle @Column(length=20name="angle")
     */
    public void setAngle(float angle) {
        this.angle = angle;
    }
}