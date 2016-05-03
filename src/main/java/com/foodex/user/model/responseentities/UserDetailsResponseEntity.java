package com.foodex.user.model.responseentities;

import java.util.List;

import com.foodex.user.model.dataentities.DeliveryAddressEntity;
import com.foodex.user.model.dataentities.UsersEntity;

public class UserDetailsResponseEntity {
	private List<DeliveryAddressEntity> deliveryAddressList;
	private UsersEntity userEntity;

	public List<DeliveryAddressEntity> getDeliveryAddressList() {
		return deliveryAddressList;
	}

	public void setDeliveryAddressList(List<DeliveryAddressEntity> deliveryAddressList) {
		this.deliveryAddressList = deliveryAddressList;
	}

	public UsersEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UsersEntity userEntity) {
		this.userEntity = userEntity;
	}

	@Override
	public String toString() {
		return "UserDetailsResponseEntity [deliveryAddressList=" + deliveryAddressList + ", userEntity=" + userEntity
				+ "]";
	}

}
