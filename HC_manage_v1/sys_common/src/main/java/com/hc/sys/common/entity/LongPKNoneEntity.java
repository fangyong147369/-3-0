//package com.hc.sys.common.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Embeddable;
//import javax.persistence.Id;
//import javax.persistence.MappedSuperclass;
//import java.util.Objects;
//
///**
// * 统一定义long型id(主键)的entity基类.
// * 基类统一定义id的属性名称、数据类型、列名映射及生成策略.
// * @author fangyong
// * @version 1.0.0.0
// */
//@Embeddable
//public  class LongPKNoneEntity extends BaseEntity {
//
//	private static final long serialVersionUID = 1L;
//
//	@Column(length=20,name="id")
//	private long id;
//	@Column(length=20,name="angle")
//	private float angle;
//
//	public long getId() {
//		return id;
//	}
//
//	public LongPKNoneEntity() {
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	/**
//	 * 获取 @Column(length=20name="angle")
//	 *
//	 * @return angle @Column(length=20name="angle")
//	 */
//	public float getAngle() {
//		return this.angle;
//	}
//
//	/**
//	 * 设置 @Column(length=20name="angle")
//	 *
//	 * @param angle @Column(length=20name="angle")
//	 */
//	public void setAngle(float angle) {
//		this.angle = angle;
//	}
//
//	@Override
//	public boolean equals(Object o) {
//		if (o instanceof  LongPKNoneEntity){
//			LongPKNoneEntity pk=(LongPKNoneEntity)o;
//			if(this.id==pk.getId()&&this.angle==pk.getAngle()){
//				return true;
//			}
//		}
//
//
//	}
//}
