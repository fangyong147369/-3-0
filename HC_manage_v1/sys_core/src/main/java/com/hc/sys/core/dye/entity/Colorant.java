package com.hc.sys.core.dye.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hc.sys.common.entity.LongPKEntity;
import javax.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.Id;

import java.util.Date;
/**
 * @Description: 配方颜色表
 * @Author: fangyong
 * @CreateDate: 2018/10/18 13:29
 * @Version: 1.0.0.0
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name ="colorant")
public class Colorant extends LongPKEntity {
    /** 序列号 **/
    private static final long serialVersionUID = 1L;
    /** 关联光源Id*/
    private int illuminantId;
    /** 测量模式  0：SCI，1：SCE；默认是SCI模式*/
    private int specularMode;
    /** 关联角度Id*/
    private float angle;
    /**RGB颜色 **/
    private int rgb;
    /**目标颜色亮度（L） **/
    private float l;
    /**目标颜色绿红（a） **/
    private float a;
    /**目标颜色蓝黄（b） **/
    private float b;
    /**反射率 **/
    private float reflection400;
    /**反射率 **/
    private float reflection410;
    /**反射率 **/
    private float reflection420;
    /**反射率 **/
    private float reflection430;
    /**反射率 **/
    private float reflection440;
    /**反射率 **/
    private float reflection450;
    /**反射率 **/
    private float reflection460;
    /**反射率 **/
    private float reflection470;
    /**反射率 **/
    private float reflection480;
    /**反射率 **/
    private float reflection490;
    /**反射率 **/
    private float reflection500;
    /**反射率 **/
    private float reflection510;
    /**反射率 **/
    private float reflection520;
    /**反射率 **/
    private float reflection530;
    /**反射率 **/
    private float reflection540;
    /**反射率 **/
    private float reflection550;
    /**反射率 **/
    private float reflection560;
    /**反射率 **/
    private float reflection570;
    /**反射率 **/
    private float reflection580;
    /**反射率 **/
    private float reflection590;
    /**反射率 **/
    private float reflection600;
    /**反射率 **/
    private float reflection610;
    /**反射率 **/
    private float reflection620;
    /**反射率 **/
    private float reflection630;
    /**反射率 **/
    private float reflection640;
    /**反射率 **/
    private float reflection650;
    /**反射率 **/
    private float reflection660;
    /**反射率 **/
    private float reflection670;
    /**反射率 **/
    private float reflection680;
    /**反射率 **/
    private float reflection690;
    /**反射率 **/
    private float reflection700;
    /**如果存在材质图片，记录文件路径 **/
    private String imagePath;
    /**添加时间 **/
    private Date addTime;

//    /**
//     * 获取 关联光源对象
//     *
//     * @return illuminant 关联光源对象
//     */
//    public Illuminant getIlluminant() {
//        return this.illuminant;
//    }
//
//    /**
//     * 设置 关联光源对象
//     *
//     * @param illuminant 关联光源对象
//     */
//    public void setIlluminant(Illuminant illuminant) {
//        this.illuminant = illuminant;
//    }

    /**
     * 获取 RGB颜色
     *
     * @return rgb RGB颜色
     */
    public int getRgb() {
        return this.rgb;
    }

    /**
     * 设置 RGB颜色
     *
     * @param rgb RGB颜色
     */
    public void setRgb(int rgb) {
        this.rgb = rgb;
    }

    /**
     * 获取 目标颜色亮度（L）
     *
     * @return l 目标颜色亮度（L）
     */
    public float getL() {
        return this.l;
    }

    /**
     * 设置 目标颜色亮度（L）
     *
     * @param l 目标颜色亮度（L）
     */
    public void setL(float l) {
        this.l = l;
    }

    /**
     * 获取 目标颜色绿红（a）
     *
     * @return a 目标颜色绿红（a）
     */
    public float getA() {
        return this.a;
    }

    /**
     * 设置 目标颜色绿红（a）
     *
     * @param a 目标颜色绿红（a）
     */
    public void setA(float a) {
        this.a = a;
    }

    /**
     * 获取 目标颜色蓝黄（b）
     *
     * @return b 目标颜色蓝黄（b）
     */
    public float getB() {
        return this.b;
    }

    /**
     * 设置 目标颜色蓝黄（b）
     *
     * @param b 目标颜色蓝黄（b）
     */
    public void setB(float b) {
        this.b = b;
    }

    /**
     * 获取 反射率
     *
     * @return reflection400 反射率
     */
    public float getReflection400() {
        return this.reflection400;
    }

    /**
     * 设置 反射率
     *
     * @param reflection400 反射率
     */
    public void setReflection400(float reflection400) {
        this.reflection400 = reflection400;
    }

    /**
     * 获取 反射率
     *
     * @return reflection410 反射率
     */
    public float getReflection410() {
        return this.reflection410;
    }

    /**
     * 设置 反射率
     *
     * @param reflection410 反射率
     */
    public void setReflection410(float reflection410) {
        this.reflection410 = reflection410;
    }

    /**
     * 获取 反射率
     *
     * @return reflection420 反射率
     */
    public float getReflection420() {
        return this.reflection420;
    }

    /**
     * 设置 反射率
     *
     * @param reflection420 反射率
     */
    public void setReflection420(float reflection420) {
        this.reflection420 = reflection420;
    }

    /**
     * 获取 反射率
     *
     * @return reflection430 反射率
     */
    public float getReflection430() {
        return this.reflection430;
    }

    /**
     * 设置 反射率
     *
     * @param reflection430 反射率
     */
    public void setReflection430(float reflection430) {
        this.reflection430 = reflection430;
    }

    /**
     * 获取 反射率
     *
     * @return reflection440 反射率
     */
    public float getReflection440() {
        return this.reflection440;
    }

    /**
     * 设置 反射率
     *
     * @param reflection440 反射率
     */
    public void setReflection440(float reflection440) {
        this.reflection440 = reflection440;
    }

    /**
     * 获取 反射率
     *
     * @return reflection450 反射率
     */
    public float getReflection450() {
        return this.reflection450;
    }

    /**
     * 设置 反射率
     *
     * @param reflection450 反射率
     */
    public void setReflection450(float reflection450) {
        this.reflection450 = reflection450;
    }

    /**
     * 获取 反射率
     *
     * @return reflection460 反射率
     */
    public float getReflection460() {
        return this.reflection460;
    }

    /**
     * 设置 反射率
     *
     * @param reflection460 反射率
     */
    public void setReflection460(float reflection460) {
        this.reflection460 = reflection460;
    }

    /**
     * 获取 反射率
     *
     * @return reflection470 反射率
     */
    public float getReflection470() {
        return this.reflection470;
    }

    /**
     * 设置 反射率
     *
     * @param reflection470 反射率
     */
    public void setReflection470(float reflection470) {
        this.reflection470 = reflection470;
    }

    /**
     * 获取 反射率
     *
     * @return reflection480 反射率
     */
    public float getReflection480() {
        return this.reflection480;
    }

    /**
     * 设置 反射率
     *
     * @param reflection480 反射率
     */
    public void setReflection480(float reflection480) {
        this.reflection480 = reflection480;
    }

    /**
     * 获取 反射率
     *
     * @return reflection490 反射率
     */
    public float getReflection490() {
        return this.reflection490;
    }

    /**
     * 设置 反射率
     *
     * @param reflection490 反射率
     */
    public void setReflection490(float reflection490) {
        this.reflection490 = reflection490;
    }

    /**
     * 获取 反射率
     *
     * @return reflection500 反射率
     */
    public float getReflection500() {
        return this.reflection500;
    }

    /**
     * 设置 反射率
     *
     * @param reflection500 反射率
     */
    public void setReflection500(float reflection500) {
        this.reflection500 = reflection500;
    }

    /**
     * 获取 反射率
     *
     * @return reflection510 反射率
     */
    public float getReflection510() {
        return this.reflection510;
    }

    /**
     * 设置 反射率
     *
     * @param reflection510 反射率
     */
    public void setReflection510(float reflection510) {
        this.reflection510 = reflection510;
    }

    /**
     * 获取 反射率
     *
     * @return reflection520 反射率
     */
    public float getReflection520() {
        return this.reflection520;
    }

    /**
     * 设置 反射率
     *
     * @param reflection520 反射率
     */
    public void setReflection520(float reflection520) {
        this.reflection520 = reflection520;
    }

    /**
     * 获取 反射率
     *
     * @return reflection530 反射率
     */
    public float getReflection530() {
        return this.reflection530;
    }

    /**
     * 设置 反射率
     *
     * @param reflection530 反射率
     */
    public void setReflection530(float reflection530) {
        this.reflection530 = reflection530;
    }

    /**
     * 获取 反射率
     *
     * @return reflection540 反射率
     */
    public float getReflection540() {
        return this.reflection540;
    }

    /**
     * 设置 反射率
     *
     * @param reflection540 反射率
     */
    public void setReflection540(float reflection540) {
        this.reflection540 = reflection540;
    }

    /**
     * 获取 反射率
     *
     * @return reflection550 反射率
     */
    public float getReflection550() {
        return this.reflection550;
    }

    /**
     * 设置 反射率
     *
     * @param reflection550 反射率
     */
    public void setReflection550(float reflection550) {
        this.reflection550 = reflection550;
    }

    /**
     * 获取 反射率
     *
     * @return reflection560 反射率
     */
    public float getReflection560() {
        return this.reflection560;
    }

    /**
     * 设置 反射率
     *
     * @param reflection560 反射率
     */
    public void setReflection560(float reflection560) {
        this.reflection560 = reflection560;
    }

    /**
     * 获取 反射率
     *
     * @return reflection570 反射率
     */
    public float getReflection570() {
        return this.reflection570;
    }

    /**
     * 设置 反射率
     *
     * @param reflection570 反射率
     */
    public void setReflection570(float reflection570) {
        this.reflection570 = reflection570;
    }

    /**
     * 获取 反射率
     *
     * @return reflection580 反射率
     */
    public float getReflection580() {
        return this.reflection580;
    }

    /**
     * 设置 反射率
     *
     * @param reflection580 反射率
     */
    public void setReflection580(float reflection580) {
        this.reflection580 = reflection580;
    }

    /**
     * 获取 反射率
     *
     * @return reflection590 反射率
     */
    public float getReflection590() {
        return this.reflection590;
    }

    /**
     * 设置 反射率
     *
     * @param reflection590 反射率
     */
    public void setReflection590(float reflection590) {
        this.reflection590 = reflection590;
    }

    /**
     * 获取 反射率
     *
     * @return reflection600 反射率
     */
    public float getReflection600() {
        return this.reflection600;
    }

    /**
     * 设置 反射率
     *
     * @param reflection600 反射率
     */
    public void setReflection600(float reflection600) {
        this.reflection600 = reflection600;
    }

    /**
     * 获取 反射率
     *
     * @return reflection610 反射率
     */
    public float getReflection610() {
        return this.reflection610;
    }

    /**
     * 设置 反射率
     *
     * @param reflection610 反射率
     */
    public void setReflection610(float reflection610) {
        this.reflection610 = reflection610;
    }

    /**
     * 获取 反射率
     *
     * @return reflection620 反射率
     */
    public float getReflection620() {
        return this.reflection620;
    }

    /**
     * 设置 反射率
     *
     * @param reflection620 反射率
     */
    public void setReflection620(float reflection620) {
        this.reflection620 = reflection620;
    }

    /**
     * 获取 反射率
     *
     * @return reflection630 反射率
     */
    public float getReflection630() {
        return this.reflection630;
    }

    /**
     * 设置 反射率
     *
     * @param reflection630 反射率
     */
    public void setReflection630(float reflection630) {
        this.reflection630 = reflection630;
    }

    /**
     * 获取 反射率
     *
     * @return reflection640 反射率
     */
    public float getReflection640() {
        return this.reflection640;
    }

    /**
     * 设置 反射率
     *
     * @param reflection640 反射率
     */
    public void setReflection640(float reflection640) {
        this.reflection640 = reflection640;
    }

    /**
     * 获取 反射率
     *
     * @return reflection650 反射率
     */
    public float getReflection650() {
        return this.reflection650;
    }

    /**
     * 设置 反射率
     *
     * @param reflection650 反射率
     */
    public void setReflection650(float reflection650) {
        this.reflection650 = reflection650;
    }

    /**
     * 获取 反射率
     *
     * @return reflection660 反射率
     */
    public float getReflection660() {
        return this.reflection660;
    }

    /**
     * 设置 反射率
     *
     * @param reflection660 反射率
     */
    public void setReflection660(float reflection660) {
        this.reflection660 = reflection660;
    }

    /**
     * 获取 反射率
     *
     * @return reflection670 反射率
     */
    public float getReflection670() {
        return this.reflection670;
    }

    /**
     * 设置 反射率
     *
     * @param reflection670 反射率
     */
    public void setReflection670(float reflection670) {
        this.reflection670 = reflection670;
    }

    /**
     * 获取 反射率
     *
     * @return reflection680 反射率
     */
    public float getReflection680() {
        return this.reflection680;
    }

    /**
     * 设置 反射率
     *
     * @param reflection680 反射率
     */
    public void setReflection680(float reflection680) {
        this.reflection680 = reflection680;
    }

    /**
     * 获取 反射率
     *
     * @return reflection690 反射率
     */
    public float getReflection690() {
        return this.reflection690;
    }

    /**
     * 设置 反射率
     *
     * @param reflection690 反射率
     */
    public void setReflection690(float reflection690) {
        this.reflection690 = reflection690;
    }

    /**
     * 获取 反射率
     *
     * @return reflection700 反射率
     */
    public float getReflection700() {
        return this.reflection700;
    }

    /**
     * 设置 反射率
     *
     * @param reflection700 反射率
     */
    public void setReflection700(float reflection700) {
        this.reflection700 = reflection700;
    }

    /**
     * 获取 如果存在材质图片，记录文件路径
     *
     * @return imagePath 如果存在材质图片，记录文件路径
     */
    public String getImagePath() {
        return this.imagePath;
    }

    /**
     * 设置 如果存在材质图片，记录文件路径
     *
     * @param imagePath 如果存在材质图片，记录文件路径
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
     * 获取 关联光源Id
     *
     * @return illuminantId 关联光源Id
     */
    public int getIlluminantId() {
        return this.illuminantId;
    }

    /**
     * 设置 关联光源Id
     *
     * @param illuminantId 关联光源Id
     */
    public void setIlluminantId(int illuminantId) {
        this.illuminantId = illuminantId;
    }

    /**
     * 获取 @Id    @Column(name="angle")
     *
     * @return angle @Id    @Column(name="angle")
     */
    public float getAngle() {
        return this.angle;
    }

    /**
     * 设置 @Id    @Column(name="angle")
     *
     * @param angle @Id    @Column(name="angle")
     */
    public void setAngle(float angle) {
        this.angle = angle;
    }

    /**
     * 获取 测量模式  0：SCI，1：SCE；默认是SCI模式
     *
     * @return specularMode 测量模式  0：SCI，1：SCE；默认是SCI模式
     */
    public int getSpecularMode() {
        return this.specularMode;
    }

    /**
     * 设置 测量模式  0：SCI，1：SCE；默认是SCI模式
     *
     * @param specularMode 测量模式  0：SCI，1：SCE；默认是SCI模式
     */
    public void setSpecularMode(int specularMode) {
        this.specularMode = specularMode;
    }
}
