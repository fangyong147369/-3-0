package com.hc.sys.common.entity;
import javax.persistence.Column;
import java.io.Serializable;
/**
 * @Description: 角度和光源主键
 * @Author: fangyong
 * @CreateDate: 2018/11/6 13:11
 * @Version: 1.0.0.0
 */
public class PrimaryKeyAngleAndIlluminant implements Serializable {
    private static final int serialVersionUID = 1;
    @Column(length = 8, nullable = false)
    private int id;
    @Column(length =8, nullable = false)
    private float angle;

    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public float getAngle() {
        return angle;
    }
    public void setAngle(float angle) {
        this.angle = angle;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
