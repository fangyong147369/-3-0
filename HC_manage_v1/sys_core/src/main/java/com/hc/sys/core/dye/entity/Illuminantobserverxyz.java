package com.hc.sys.core.dye.entity;
import com.hc.sys.common.entity.PrimaryKeyAngleAndIlluminant;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.io.Serializable;
/**
 * @Description: 描述
 * @Author: fangyong
 * @CreateDate: 2018/11/2 17:16
 * @Version: 1.0.0.0
 */
@Entity
@Table(name ="illuminantobserverxyz")
@IdClass(PrimaryKeyAngleAndIlluminant.class)
@DynamicUpdate(true)
@DynamicInsert(true)
public class Illuminantobserverxyz  implements Serializable{
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
    public float getX() {
        return X;
    }

    public void setX(float x) {
        X = x;
    }

    public float getY() {
        return Y;
    }

    public void setY(float y) {
        Y = y;
    }

    public float getZ() {
        return Z;
    }

    public void setZ(float z) {
        Z = z;
    }
    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
