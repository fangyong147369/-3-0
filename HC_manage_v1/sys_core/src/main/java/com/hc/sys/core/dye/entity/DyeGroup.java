package com.hc.sys.core.dye.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @Description: 染料组
 * @Author: fangyong
 * @CreateDate: 2018/10/19 11:38
 * @Version: 1.0.0.0
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name ="dye_group")
public class DyeGroup extends LongPKEntity {
    /** 序列号 **/
    private static final long serialVersionUID = 1L;
    /**名称 **/
    private String name;
    /**描述**/
    private String description;
    /**添加时间**/
    private Date addTime;
    /**dyeid1 **/
    private long dyeid1;
    /**dyeid2 **/
    private long dyeid2;
    /**dyeid3 **/
    private long dyeid3;
    /**dyeid4 **/
    private long dyeid4;
    /**dyeid5 **/
    private long dyeid5;
    /**dyeid6 **/
    private long dyeid6;
    /**dyeid7 **/
    private long dyeid7;
    /**dyeid8 **/
    private long dyeid8;
    /**dyeid9 **/
    private long dyeid9;
    /**dyeid10 **/
    private long dyeid10;

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
     * 获取 dyeid1
     *
     * @return dyeid1 dyeid1
     */
    public long getDyeid1() {
        return this.dyeid1;
    }

    /**
     * 设置 dyeid1
     *
     * @param dyeid1 dyeid1
     */
    public void setDyeid1(long dyeid1) {
        this.dyeid1 = dyeid1;
    }

    /**
     * 获取 dyeid2
     *
     * @return dyeid2 dyeid2
     */
    public long getDyeid2() {
        return this.dyeid2;
    }

    /**
     * 设置 dyeid2
     *
     * @param dyeid2 dyeid2
     */
    public void setDyeid2(long dyeid2) {
        this.dyeid2 = dyeid2;
    }

    /**
     * 获取 dyeid3
     *
     * @return dyeid3 dyeid3
     */
    public long getDyeid3() {
        return this.dyeid3;
    }

    /**
     * 设置 dyeid3
     *
     * @param dyeid3 dyeid3
     */
    public void setDyeid3(long dyeid3) {
        this.dyeid3 = dyeid3;
    }

    /**
     * 获取 dyeid4
     *
     * @return dyeid4 dyeid4
     */
    public long getDyeid4() {
        return this.dyeid4;
    }

    /**
     * 设置 dyeid4
     *
     * @param dyeid4 dyeid4
     */
    public void setDyeid4(long dyeid4) {
        this.dyeid4 = dyeid4;
    }

    /**
     * 获取 dyeid5
     *
     * @return dyeid5 dyeid5
     */
    public long getDyeid5() {
        return this.dyeid5;
    }

    /**
     * 设置 dyeid5
     *
     * @param dyeid5 dyeid5
     */
    public void setDyeid5(long dyeid5) {
        this.dyeid5 = dyeid5;
    }

    /**
     * 获取 dyeid6
     *
     * @return dyeid6 dyeid6
     */
    public long getDyeid6() {
        return this.dyeid6;
    }

    /**
     * 设置 dyeid6
     *
     * @param dyeid6 dyeid6
     */
    public void setDyeid6(long dyeid6) {
        this.dyeid6 = dyeid6;
    }

    /**
     * 获取 dyeid7
     *
     * @return dyeid7 dyeid7
     */
    public long getDyeid7() {
        return this.dyeid7;
    }

    /**
     * 设置 dyeid7
     *
     * @param dyeid7 dyeid7
     */
    public void setDyeid7(long dyeid7) {
        this.dyeid7 = dyeid7;
    }

    /**
     * 获取 dyeid8
     *
     * @return dyeid8 dyeid8
     */
    public long getDyeid8() {
        return this.dyeid8;
    }

    /**
     * 设置 dyeid8
     *
     * @param dyeid8 dyeid8
     */
    public void setDyeid8(long dyeid8) {
        this.dyeid8 = dyeid8;
    }

    /**
     * 获取 dyeid9
     *
     * @return dyeid9 dyeid9
     */
    public long getDyeid9() {
        return this.dyeid9;
    }

    /**
     * 设置 dyeid9
     *
     * @param dyeid9 dyeid9
     */
    public void setDyeid9(long dyeid9) {
        this.dyeid9 = dyeid9;
    }

    /**
     * 获取 dyeid10
     *
     * @return dyeid10 dyeid10
     */
    public long getDyeid10() {
        return this.dyeid10;
    }

    /**
     * 设置 dyeid10
     *
     * @param dyeid10 dyeid10
     */
    public void setDyeid10(long dyeid10) {
        this.dyeid10 = dyeid10;
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
}
