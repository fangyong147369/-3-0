package com.hc.sys.common.entity;
import javax.persistence.Column;
import java.io.Serializable;

/**
 * @Description: 主键(WaveLengthIdPkEntity)
 * @Author: fangyong
 * @CreateDate: 2018/11/2 17:47
 * @Version: 1.0.0.0
 */
public class PrimaryKeyIdAndWaveLengthIdPkEntity implements Serializable {
    private static final int serialVersionUID = 1;
    @Column(length =8,nullable = false)
    private int id;
    @Column(length =8,nullable = false)
    private int waveLength;
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
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}