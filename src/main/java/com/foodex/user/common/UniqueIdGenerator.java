package com.foodex.user.common;

import java.util.Random;

public class UniqueIdGenerator {

	public static String newClientId(Integer areaCode) {
		return areaCode.toString() + String.valueOf(System.currentTimeMillis() / (1000 * 60));
	}

	public static String newOrderId(String clientId) {
		return clientId + String.valueOf(System.currentTimeMillis() / (1000 * 60));
	}
	
	public static int generateOTP() {
		Random r = new Random();
		int Low = 100000;
		int High = 999999;
		int otp = r.nextInt(High-Low) + Low;
		return otp;
	}

}
