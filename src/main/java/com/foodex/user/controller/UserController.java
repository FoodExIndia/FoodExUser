package com.foodex.user.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodex.user.common.AreaEnum;
import com.foodex.user.common.HibernateUtil;
import com.foodex.user.common.HttpHelper;
import com.foodex.user.common.UniqueIdGenerator;
import com.foodex.user.model.dataentities.ClientEntity;
import com.foodex.user.model.dataentities.DeliveryAddressEntity;
import com.foodex.user.model.dataentities.FoodItemEntity;
import com.foodex.user.model.dataentities.MenuEntity;
import com.foodex.user.model.dataentities.UsersEntity;
import com.foodex.user.model.requestentities.SignUpRequest;
import com.foodex.user.model.responseentities.UserDetailsResponseEntity;
import com.google.gson.Gson;

@RestController
@RequestMapping("User")
public class UserController {
	@RequestMapping(produces = "text/plain", value = "/login", method = RequestMethod.GET)
	public String loginValidation(@RequestParam String loginId, @RequestParam String password)
			throws InterruptedException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session
				.createQuery("from com.foodex.user.model.dataentities.UsersEntity where mobileNum= :mobileNum");
		query.setString("mobileNum", loginId);
		UsersEntity user = (UsersEntity) query.uniqueResult();
		Thread.sleep(5000);
		if (user == null) {
			return "failure";
		}
		t.commit();
		session.close();
		System.out.println(user.getPassword());
		return user.getPassword().equals(password) ? "success" : "failure";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(produces = "text/plain", value = "/menulist", method = RequestMethod.GET)
	public String retrieveMenuList() {
		Gson jsonConverter = new Gson();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		// query for menu entity table
		List<MenuEntity> menuEntityList = session.createQuery("from com.foodex.user.model.dataentities.MenuEntity")
				.list();
		t.commit();
		session.close();
		return jsonConverter.toJson(menuEntityList);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(produces = "text/plain", value = "/userdetails", method = RequestMethod.GET)
	public String retrieveUserDetailsResponseEntity(@RequestParam String loginId) {
		UserDetailsResponseEntity userDetailsResponseEntity = new UserDetailsResponseEntity();
		Gson jsonConverter = new Gson();
		// session creation
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		// query for user entity table
		Query userQuery = session
				.createQuery("from com.foodex.user.model.dataentities.UsersEntity where mobileNum= :mobileNum");
		userQuery.setString("mobileNum", loginId);
		UsersEntity userEntity = (UsersEntity) userQuery.uniqueResult();
		// query for delivery address entity table
		Query addressQuery = session.createQuery(
				"from com.foodex.user.model.dataentities.DeliveryAddressEntity where clientKey= :clientKey");
		addressQuery.setString("clientKey", userEntity.getClientKey());
		List<DeliveryAddressEntity> deliveryAddressList = (List<DeliveryAddressEntity>) addressQuery.list();
		if (deliveryAddressList == null)
			deliveryAddressList = new ArrayList<DeliveryAddressEntity>();
		t.commit();
		session.close();
		userDetailsResponseEntity.setUserEntity(userEntity);
		userDetailsResponseEntity.setDeliveryAddressList(deliveryAddressList);
		return jsonConverter.toJson(userDetailsResponseEntity);
	}

	@RequestMapping(produces = "text/plain", value = "/signup", method = RequestMethod.POST)
	public String signUp(@RequestParam String signUpJson) {
		Gson gson = new Gson();
		SignUpRequest signUpRequest = gson.fromJson(signUpJson, SignUpRequest.class);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		AreaEnum areaCode = AreaEnum.valueOf("CHENNAI" + "_" + "MEDAVAKKAM");
		String clientKey = UniqueIdGenerator.newClientId(areaCode.getValue());
		ClientEntity client = new ClientEntity();
		client.setClientKey(clientKey);
		client.setClientFirsteName(signUpRequest.getFirstName());
		client.setClientLastName(signUpRequest.getLastname());
		client.setClientEmail(signUpRequest.getEmailId());
		client.setClientMobileNum(Long.parseLong(signUpRequest.getPhoneNumber()));
		client.setClientAddressLine1(signUpRequest.getAddressLine1());
		client.setClientAddressLine2(signUpRequest.getAddressLine2());
		client.setClientArea("area");
		client.setClientCity(signUpRequest.getCity());
		client.setClientDOB(new Date(System.currentTimeMillis()));
		client.setClientGpsLocation("gps");
		client.setClientState(signUpRequest.getState());
		client.setClientZip(Long.parseLong(signUpRequest.getPincode()));
		client.setInsertDate(new Date(System.currentTimeMillis()));
		UsersEntity user = new UsersEntity();
		user.setClientKey(clientKey);
		user.setEmailId(signUpRequest.getEmailId());
		user.setMobileNum(Long.parseLong(signUpRequest.getPhoneNumber()));
		user.setPassword(signUpRequest.getPassword());
		user.setInsertDate(new Date(System.currentTimeMillis()));
		user.setStatus("NewUser");
		user.setRecentOTP(12345);
		try {
			session.save(client);
			session.save(user);
			t.commit();
			if (sendOTP(user.getMobileNum()) == "failure") {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "failure" + e.getStackTrace().toString();
		}
		System.out.println(signUpRequest.toString());
		return signUpRequest.getPhoneNumber();
	}

	@RequestMapping(produces = "text/plain", value = "/otp", method = RequestMethod.GET)
	public String otpValidation(@RequestParam String loginId, @RequestParam int otp) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session
				.createQuery("from com.foodex.user.model.dataentities.UsersEntity where mobileNum= :mobileNum");
		query.setString("mobileNum", loginId);
		UsersEntity user = (UsersEntity) query.uniqueResult();
		if (user == null) {
			return "failure";
		}
		t.commit();
		session.close();
		System.out.println(user.getRecentOTP());
		return user.getRecentOTP() == otp ? "success" : "failure";
	}

	@RequestMapping(produces = "text/plain", value = "/newpassword", method = RequestMethod.GET)
	public String newPassword(@RequestParam long loginId, @RequestParam String password) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction t = session.beginTransaction();
			Query query = session.createQuery(
					"update com.foodex.user.model.dataentities.UsersEntity set password= :password where mobileNum= :mobileNum");
			query.setLong("mobileNum", loginId);
			query.setString("password", password);
			t.commit();
			session.close();
			return "success";
		} catch (Exception ex) {
			return "failure";
		}
	}

	@RequestMapping(produces = "text/plain", value = "/sendotp", method = RequestMethod.GET)
	public String forgotPassword(@RequestParam long loginId) {
		return sendOTP(loginId);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(produces = "text/plain", value = "/foodItem", method = RequestMethod.GET)
	public String getFoodItems() {
		try {
			Gson gson = new Gson();
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction t = session.beginTransaction();
			Query query = session.createQuery("from com.foodex.user.model.dataentities.FoodItemEntity");
			List<FoodItemEntity> foodItemList = (List<FoodItemEntity>) query.list();
			t.commit();
			session.close();
			return gson.toJson(foodItemList);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "failure";
		}
	}
	
	@RequestMapping(produces = "text/plain", value = "/verifymobilenum", method = RequestMethod.GET)
	public String verifyMobileNumber(@RequestParam long loginId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session
				.createQuery("from com.foodex.user.model.dataentities.UsersEntity where mobileNum= :mobileNum");
		query.setLong("mobileNum", loginId);
		UsersEntity user = (UsersEntity) query.uniqueResult();
		if(user != null)
		{
			return "failure";
		}
		return "success";
	}

	private String sendOTP(long mobileNumber) {
		int otp = UniqueIdGenerator.generateOTP();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session.createQuery(
				"update com.foodex.user.model.dataentities.UsersEntity set recentOTP= :recentOTP where mobileNum= :mobileNum");
		query.setLong("mobileNum", mobileNumber);
		query.setInteger("recentOTP", otp);
		try {
			query.executeUpdate();
			t.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "failure";
		}
		String smsAPI = "http://api.mvaayoo.com/mvaayooapi/MessageCompose?user=foodexhome@gmail.com:foodex123&senderID=TEST%20SMS&receipientno="
				+ mobileNumber + "&msgtxt=Hi,%20Welcome%20to%20FOODEX.%20Please%20enter%20this%20OTP%20" + otp
				+ ".&state=4";
		HttpHelper httpHelper = new HttpHelper();
		httpHelper.get(smsAPI);
		return "success";
	}

}