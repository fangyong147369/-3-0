package com.hc.sys.core.dye.entity;
import com.hc.sys.common.entity.LongPKEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.util.Date;
/**
 * @Description: 染缸厂商
 * @Author: fangyong
 * @CreateDate: 2018/11/1 11:12
 * @Version: 1.0.0.0
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name="dyeing_tank_manufacturer")
public class DyeingTankManufacturer extends LongPKEntity {
    /** 序列号 **/
    private static final long serialVersionUID = 1L;
    /**公司名称**/
    private String company;
    /**公司地址**/
    private String address;
    /**邮编**/
    private String zipcode;
    /**国家**/
    private String country;
    /**城市**/
    private String city;
    /**州/省**/
    private String state;
    /**联系人**/
    private String contact;
    /**联系电话**/
    private String phoneNumber;
    /**传真**/
    private String faxNumber;
    /**邮箱**/
    private String email;
    /**添加时间**/
    private Date addTime;

    /**
     * 获取 公司名称
     *
     * @return company 公司名称
     */
    public String getCompany() {
        return this.company;
    }

    /**
     * 设置 公司名称
     *
     * @param company 公司名称
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 获取 公司地址
     *
     * @return address 公司地址
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * 设置 公司地址
     *
     * @param address 公司地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取 邮编
     *
     * @return zipcode 邮编
     */
    public String getZipcode() {
        return this.zipcode;
    }

    /**
     * 设置 邮编
     *
     * @param zipcode 邮编
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * 获取 国家
     *
     * @return country 国家
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * 设置 国家
     *
     * @param country 国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取 城市
     *
     * @return city 城市
     */
    public String getCity() {
        return this.city;
    }

    /**
     * 设置 城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取 州省
     *
     * @return state 州省
     */
    public String getState() {
        return this.state;
    }

    /**
     * 设置 州省
     *
     * @param state 州省
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取 联系人
     *
     * @return contact 联系人
     */
    public String getContact() {
        return this.contact;
    }

    /**
     * 设置 联系人
     *
     * @param contact 联系人
     */
    public void setContact(String contact) {
        this.contact = contact;
    }



    /**
     * 获取 传真
     *
     * @return faxNumber 传真
     */
    public String getFaxNumber() {
        return this.faxNumber;
    }

    /**
     * 设置 传真
     *
     * @param faxNumber 传真
     */
    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    /**
     * 获取 邮箱
     *
     * @return email 邮箱
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * 设置 邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
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
     * 获取 联系电话
     *
     * @return phoneNumber 联系电话
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * 设置 联系电话
     *
     * @param phoneNumber 联系电话
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
