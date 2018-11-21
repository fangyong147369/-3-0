package com.hc.sys.common.entity;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * @Description: 角度和光源主键
 * @Author: fangyong
 * @CreateDate: 2018/11/6 13:11
 * @Version: 1.0.0.0
 */
public class PrimaryKeyAndFormulaID implements Serializable {
    private static final int serialVersionUID = 1;
    @Column(length = 8, nullable = false, name = "key_id")
    private int keyId;
    @Column(length = 8, nullable = false, name = "formula_id")
    private int formulaId;

    public int getKeyId() {
        return keyId;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    public int getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(int formulaId) {
        this.formulaId = formulaId;
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
