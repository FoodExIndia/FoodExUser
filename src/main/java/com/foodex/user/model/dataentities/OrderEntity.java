package com.foodex.user.model.dataentities;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fe_orders")
public class OrderEntity {

	@Column(name = "client_id")
	private long clientId;
	@Id
	@Column(name = "order_id")
	private String orderId;
	@Column(name = "plan_key")
	private int planKey;
	@Column(name = "food_order_from_date")
	private Date foodOrderFromDate;
	@Column(name = "food_order_to_date")
	private Date foodOrderToDate;
	@Column(name = "total_bill_amt")
	private double totalBillAmt;
	@Column(name = "order_status")
	private int orderStatus;
	@Column(name = "orphanage_flag")
	private int orphanageFlag;
	@Column(name = "bf_time")
	private Timestamp breakfastTime;
	@Column(name = "lunch_time")
	private Timestamp lunchTime;
	@Column(name = "dinner_time")
	private Timestamp dinnerTime;
	@Column(name = "insert_date")
	private Date insertDate;
	@Column(name = "update_date")
	private Date updateDate;
	@Column(name = "BREAKFAST_ADDRESS")
	private int breakbreakfastAddress;
	@Column(name = "LUNCH_ADDRESS")
	private int lunchAddress;
	@Column(name = "DINNER_ADDRESS")
	private int dinnerAddress;
	@Column(name = "menu_json")
	private String menu;
	@Column(name = "order_cancellation_flag")
	private int orderCancellationFlag;

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getPlanKey() {
		return planKey;
	}

	public void setPlanKey(int planKey) {
		this.planKey = planKey;
	}

	public Date getFoodOrderFromDate() {
		return foodOrderFromDate;
	}

	public void setFoodOrderFromDate(Date foodOrderFromDate) {
		this.foodOrderFromDate = foodOrderFromDate;
	}

	public Date getFoodOrderToDate() {
		return foodOrderToDate;
	}

	public void setFoodOrderToDate(Date foodOrderToDate) {
		this.foodOrderToDate = foodOrderToDate;
	}

	public double getTotalBillAmt() {
		return totalBillAmt;
	}

	public void setTotalBillAmt(double totalBillAmt) {
		this.totalBillAmt = totalBillAmt;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getOrphanageFlag() {
		return orphanageFlag;
	}

	public void setOrphanageFlag(int orphanageFlag) {
		this.orphanageFlag = orphanageFlag;
	}

	public Timestamp getBreakfastTime() {
		return breakfastTime;
	}

	public void setBreakfastTime(Timestamp breakfastTime) {
		this.breakfastTime = breakfastTime;
	}

	public Timestamp getLunchTime() {
		return lunchTime;
	}

	public void setLunchTime(Timestamp lunchTime) {
		this.lunchTime = lunchTime;
	}

	public Timestamp getDinnerTime() {
		return dinnerTime;
	}

	public void setDinnerTime(Timestamp dinnerTime) {
		this.dinnerTime = dinnerTime;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public int getOrderCancellationFlag() {
		return orderCancellationFlag;
	}

	public void setOrderCancellationFlag(int orderCancellationFlag) {
		this.orderCancellationFlag = orderCancellationFlag;
	}
	
	public int getBreakbreakfastAddress() {
		return breakbreakfastAddress;
	}

	public void setBreakbreakfastAddress(int breakbreakfastAddress) {
		this.breakbreakfastAddress = breakbreakfastAddress;
	}

	public int getLunchAddress() {
		return lunchAddress;
	}

	public void setLunchAddress(int lunchAddress) {
		this.lunchAddress = lunchAddress;
	}

	public int getDinnerAddress() {
		return dinnerAddress;
	}

	public void setDinnerAddress(int dinnerAddress) {
		this.dinnerAddress = dinnerAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((breakfastTime == null) ? 0 : breakfastTime.hashCode());
		result = prime * result + (int) (clientId ^ (clientId >>> 32));
		result = prime * result + ((dinnerTime == null) ? 0 : dinnerTime.hashCode());
		result = prime * result + ((foodOrderFromDate == null) ? 0 : foodOrderFromDate.hashCode());
		result = prime * result + ((foodOrderToDate == null) ? 0 : foodOrderToDate.hashCode());
		result = prime * result + ((insertDate == null) ? 0 : insertDate.hashCode());
		result = prime * result + ((lunchTime == null) ? 0 : lunchTime.hashCode());
		result = prime * result + ((menu == null) ? 0 : menu.hashCode());
		result = prime * result + orderCancellationFlag;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + orderStatus;
		result = prime * result + orphanageFlag;
		result = prime * result + planKey;
		long temp;
		temp = Double.doubleToLongBits(totalBillAmt);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((updateDate == null) ? 0 : updateDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderEntity other = (OrderEntity) obj;
		if (breakfastTime == null) {
			if (other.breakfastTime != null)
				return false;
		} else if (!breakfastTime.equals(other.breakfastTime))
			return false;
		if (clientId != other.clientId)
			return false;
		if (dinnerTime == null) {
			if (other.dinnerTime != null)
				return false;
		} else if (!dinnerTime.equals(other.dinnerTime))
			return false;
		if (foodOrderFromDate == null) {
			if (other.foodOrderFromDate != null)
				return false;
		} else if (!foodOrderFromDate.equals(other.foodOrderFromDate))
			return false;
		if (foodOrderToDate == null) {
			if (other.foodOrderToDate != null)
				return false;
		} else if (!foodOrderToDate.equals(other.foodOrderToDate))
			return false;
		if (insertDate == null) {
			if (other.insertDate != null)
				return false;
		} else if (!insertDate.equals(other.insertDate))
			return false;
		if (lunchTime == null) {
			if (other.lunchTime != null)
				return false;
		} else if (!lunchTime.equals(other.lunchTime))
			return false;
		if (menu == null) {
			if (other.menu != null)
				return false;
		} else if (!menu.equals(other.menu))
			return false;
		if (orderCancellationFlag != other.orderCancellationFlag)
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (orderStatus != other.orderStatus)
			return false;
		if (orphanageFlag != other.orphanageFlag)
			return false;
		if (planKey != other.planKey)
			return false;
		if (Double.doubleToLongBits(totalBillAmt) != Double.doubleToLongBits(other.totalBillAmt))
			return false;
		if (updateDate == null) {
			if (other.updateDate != null)
				return false;
		} else if (!updateDate.equals(other.updateDate))
			return false;
		return true;
	}

}
