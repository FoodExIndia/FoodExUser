package com.foodex.user.model.dataentities;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fe_menu_list")
public class FoodItemEntity {
	@Column(name = "food_image")
	private byte[] foodImage;
	@Id
	@Column(name = "food_key")
	private String foodKey;
	@Column(name = "food_item")
	private String itemName;
	@Column(name = "food_item_mrp")
	private String itemPrice;
	@Column(name = "food_short_desc")
	private String itemShortDesc;
	@Column(name = "food_long_desc")
	private String itemLongDesc;
	@Column(name = "food_type_flag")
	private String courseFlag;

	public byte[] getFoodImage() {
		return foodImage;
	}

	public void setFoodImage(byte[] foodImage) {
		this.foodImage = foodImage;
	}

	public String getFoodKey() {
		return foodKey;
	}

	public void setFoodKey(String foodKey) {
		this.foodKey = foodKey;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemShortDesc() {
		return itemShortDesc;
	}

	public void setItemShortDesc(String itemShortDesc) {
		this.itemShortDesc = itemShortDesc;
	}

	public String getItemLongDesc() {
		return itemLongDesc;
	}

	public void setItemLongDesc(String itemLongDesc) {
		this.itemLongDesc = itemLongDesc;
	}

	public String getCourseFlag() {
		return courseFlag;
	}

	public void setCourseFlag(String courseFlag) {
		this.courseFlag = courseFlag;
	}

	@Override
	public String toString() {
		return "FoodItemEntity [foodImage=" + Arrays.toString(foodImage) + ", foodKey=" + foodKey + ", itemName="
				+ itemName + ", itemPrice=" + itemPrice + ", itemShortDesc=" + itemShortDesc + ", itemLongDesc="
				+ itemLongDesc + ", courseFlag=" + courseFlag + "]";
	}

}
