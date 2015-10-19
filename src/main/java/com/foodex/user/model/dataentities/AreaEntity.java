package com.foodex.user.model.dataentities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fe_area")
public class AreaEntity {
	@Id
	@Column(name = "area_code")
	private String areaCode;
	@Column(name = "area_name")
	private String areaName;
	@Column(name = "city_code")
	private String cityCode;
	@Column(name = "city_name")
	private String cityName;
	@Column(name = "customer_count")
	private int customerCount;
	@Column(name = "cook_count")
	private int cookCount;
	@Column(name = "delivery_boys_count")
	private int deliveryBoysCount;
	@Column(name = "area_manager_name")
	private String areaManagerName;
	@Column(name = "area_manager_no")
	private int areaManagerNo;
	@Column(name = "insert_date")
	private Date insertDate;
	@Column(name = "update_date")
	private int updateDate;
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public int getCustomerCount() {
		return customerCount;
	}
	public void setCustomerCount(int customerCount) {
		this.customerCount = customerCount;
	}
	public int getCookCount() {
		return cookCount;
	}
	public void setCookCount(int cookCount) {
		this.cookCount = cookCount;
	}
	public int getDeliveryBoysCount() {
		return deliveryBoysCount;
	}
	public void setDeliveryBoysCount(int deliveryBoysCount) {
		this.deliveryBoysCount = deliveryBoysCount;
	}
	public String getAreaManagerName() {
		return areaManagerName;
	}
	public void setAreaManagerName(String areaManagerName) {
		this.areaManagerName = areaManagerName;
	}
	public int getAreaManagerNo() {
		return areaManagerNo;
	}
	public void setAreaManagerNo(int areaManagerNo) {
		this.areaManagerNo = areaManagerNo;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public int getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(int updateDate) {
		this.updateDate = updateDate;
	}
}
