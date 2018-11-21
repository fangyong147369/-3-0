package com.hc.sys.core.dye.entity;
import com.hc.sys.common.entity.PrimaryKeyIdAndWaveLengthIdPkEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.io.Serializable;
/**
 * @Description: 描述
 * @Author: fangyong
 * @CreateDate: 2018/11/2 17:20
 * @Version: 1.0.0.0
 */
@Entity
@Table(name ="illuminantrelativeenergydistribution")
@IdClass(PrimaryKeyIdAndWaveLengthIdPkEntity.class)
@DynamicUpdate(true)
@DynamicInsert(true)
public class Illuminantrelativeenergydistribution implements Serializable{
    @Id
    private int id;
    @Id
    private int waveLength;
    /**value **/
    private float value;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getWaveLength() {
        return waveLength;
    }

    public void setWaveLength(int waveLength) {
        this.waveLength = waveLength;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
