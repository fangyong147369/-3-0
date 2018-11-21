package com.hc.sys.core.dye.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.util.Date;

/**
 * @Description: 染缸
 * @Author: fangyong
 * @CreateDate: 2018/10/19 17:28
 * @Version: 1.0.0.0
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name ="dyeing_tank")
public class DyeingTank extends LongPKEntity {
    /** 序列号 **/
    private static final long serialVersionUID = 1L;
    /**染缸名称**/
    private String name;
    /**染缸型号**/
    private String model;
    /**关联染缸厂商对象**/
    @JsonBackReference
    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "manufacturer_tank_id")
    private DyeingTankManufacturer dyeingTankManufacturer;
    /**添加时间**/
    private Date addTime;


    /**
     * 获取 染缸名称
     *
     * @return name 染缸名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置 染缸名称
     *
     * @param name 染缸名称
     */
    public void setName(String name) {
        this.name = name;
    }



    /**
     * 获取 添加时间
     *
     * @return addTime 添加时间
     */
    public Date getAddTime() {
        return this.addTime;
    }

    /**
     * 设置 添加时间
     *
     * @param addTime 添加时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取 关联染缸厂商对象
     *
     * @return dyeingTankManufacturer 关联染缸厂商对象
     */
    public DyeingTankManufacturer getDyeingTankManufacturer() {
        return this.dyeingTankManufacturer;
    }

    /**
     * 设置 关联染缸厂商对象
     *
     * @param dyeingTankManufacturer 关联染缸厂商对象
     */
    public void setDyeingTankManufacturer(DyeingTankManufacturer dyeingTankManufacturer) {
        this.dyeingTankManufacturer = dyeingTankManufacturer;
    }

    /**
     * 获取 染缸型号
     *
     * @return model 染缸型号
     */
    public String getModel() {
        return this.model;
    }

    /**
     * 设置 染缸型号
     *
     * @param model 染缸型号
     */
    public void setModel(String model) {
        this.model = model;
    }

}
