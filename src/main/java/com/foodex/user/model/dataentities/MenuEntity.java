package com.foodex.user.model.dataentities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fe_menu_list")
public class MenuEntity {
	@Id
	@Column(name = "food_key")
	private String foodKey;
	@Column(name = "food_item")
	private String itemName;
	@Column(name = "food_type_flag")
	private int courseFlag;
	@Column(name = "food_item_mrp")
	private double foodItemPrice;

	public String getFoodKey() {
		return foodKey;
	}

	public void setFoodKey(String foodKey) {
		this.foodKey = foodKey;
	}

	public String getFoodItem() {
		return itemName;
	}

	public void setFoodItem(String foodItem) {
		this.itemName = foodItem;
	}

	public int getFoodTypeFlag() {
		return courseFlag;
	}

	public void setFoodTypeFlag(int foodTypeFlag) {
		this.courseFlag = foodTypeFlag;
	}

	public double getFoodItemMRP() {
		return foodItemPrice;
	}

	public void setFoodItemMRP(double foodItemMRP) {
		this.foodItemPrice = foodItemMRP;
	}

	@Override
	public String toString() {
		return "MenuEntity [foodKey=" + foodKey + ", foodItem=" + itemName + ", foodTypeFlag=" + courseFlag
				+ ", foodItemMRP=" + foodItemPrice + "]";
	}

}
