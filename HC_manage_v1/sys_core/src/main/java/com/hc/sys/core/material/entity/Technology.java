package com.hc.sys.core.material.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hc.sys.common.entity.LongPKEntity;
import com.hc.sys.core.dye.entity.DyeingTank;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.util.Date;

/**
 * @Description: 工艺
 * @Author: fangyong
 * @CreateDate: 2018/10/19 17:15
 * @Version: 1.0.0.0
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name ="technology")
public class Technology extends LongPKEntity {
    /**
     * 序列号
     **/
    private static final long serialVersionUID = 1L;
    /**名称 **/
    private String name;
    /**关联染厂车间对象**/
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "factory_id")
    private Factory factory;
    /**浴比**/
    private float liquorRatio;
    /**升温速率(度/每分钟) 默认为0**/
    private float heatingRate;
    /**升温时长**/
    private int heatingTime;
    /**保温时长(分钟)**/
    private int insulationTime;
    /**主泵转速**/
    private float mainPumpSpeed;
    /**降温速率(度/每分钟)**/
    private float coolDownRate;
    /**到达的温度**/
    private float temperature;
    /**关联染缸对象**/
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "dyeing_tank_id")
    private DyeingTank dyeingTank;
    /**添加时间**/
    private Date addTime;

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
     * 获取 浴比
     *
     * @return liquorRatio 浴比
     */
    public float getLiquorRatio() {
        return this.liquorRatio;
    }

    /**
     * 设置 浴比
     *
     * @param liquorRatio 浴比
     */
    public void setLiquorRatio(float liquorRatio) {
        this.liquorRatio = liquorRatio;
    }

    /**
     * 获取 升温速率(度每分钟) 默认为0
     *
     * @return heatingRate 升温速率(度每分钟) 默认为0
     */
    public float getHeatingRate() {
        return this.heatingRate;
    }

    /**
     * 设置 升温速率(度每分钟) 默认为0
     *
     * @param heatingRate 升温速率(度每分钟) 默认为0
     */
    public void setHeatingRate(float heatingRate) {
        this.heatingRate = heatingRate;
    }




    /**
     * 获取 主泵转速
     *
     * @return mainPumpSpeed 主泵转速
     */
    public float getMainPumpSpeed() {
        return this.mainPumpSpeed;
    }

    /**
     * 设置 主泵转速
     *
     * @param mainPumpSpeed 主泵转速
     */
    public void setMainPumpSpeed(float mainPumpSpeed) {
        this.mainPumpSpeed = mainPumpSpeed;
    }

    /**
     * 获取 关联染缸对象
     *
     * @return dyeingTank 关联染缸对象
     */
    public DyeingTank getDyeingTank() {
        return this.dyeingTank;
    }

    /**
     * 设置 关联染缸对象
     *
     * @param dyeingTank 关联染缸对象
     */
    public void setDyeingTank(DyeingTank dyeingTank) {
        this.dyeingTank = dyeingTank;
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
     * 获取 升温时长
     *
     * @return heatingTime 升温时长
     */
    public int getHeatingTime() {
        return this.heatingTime;
    }

    /**
     * 设置 升温时长
     *
     * @param heatingTime 升温时长
     */
    public void setHeatingTime(int heatingTime) {
        this.heatingTime = heatingTime;
    }

    /**
     * 获取 关联染厂车间对象
     *
     * @return factory 关联染厂车间对象
     */
    public Factory getFactory() {
        return this.factory;
    }

    /**
     * 设置 关联染厂车间对象
     *
     * @param factory 关联染厂车间对象
     */
    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    /**
     * 获取 降温速率(度每分钟)
     *
     * @return coolDownRate 降温速率(度每分钟)
     */
    public float getCoolDownRate() {
        return this.coolDownRate;
    }

    /**
     * 设置 降温速率(度每分钟)
     *
     * @param coolDownRate 降温速率(度每分钟)
     */
    public void setCoolDownRate(float coolDownRate) {
        this.coolDownRate = coolDownRate;
    }

    /**
     * 获取 到达的温度
     *
     * @return temperature 到达的温度
     */
    public float getTemperature() {
        return this.temperature;
    }

    /**
     * 设置 到达的温度
     *
     * @param temperature 到达的温度
     */
    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }


    /**
     * 获取 保温时长(分钟)
     *
     * @return insulationTime 保温时长(分钟)
     */
    public int getInsulationTime() {
        return this.insulationTime;
    }

    /**
     * 设置 保温时长(分钟)
     *
     * @param insulationTime 保温时长(分钟)
     */
    public void setInsulationTime(int insulationTime) {
        this.insulationTime = insulationTime;
    }
}
