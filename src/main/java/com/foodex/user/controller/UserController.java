package com.foodex.user.controller;

import java.sql.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.foodex.user.common.AreaEnum;
import com.foodex.user.common.HibernateUtil;
import com.foodex.user.common.HttpHelper;
import com.foodex.user.common.UniqueIdGenerator;
import com.foodex.user.model.dataentities.ClientEntity;
import com.foodex.user.model.dataentities.UsersEntity;
import com.foodex.user.model.requestentities.SignUpRequest;
import com.google.gson.Gson;

@RestController
@RequestMapping("User")
public class UserController {
	@RequestMapping(produces="text/plain",value = "/login", method = RequestMethod.GET)
	public String loginValidation(@RequestParam String loginId, @RequestParam String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session.createQuery("from com.foodex.user.model.dataentities.UsersEntity where emailId= :emailId");
		query.setString("emailId", loginId);
		UsersEntity user = (UsersEntity) query.uniqueResult();
		if(user == null)
		{
			return "failure";
		}
		t.commit();
		session.close();
		System.out.println(user.getPassword());
		return user.getPassword().equals(password) ? "success" : "failure";
	}

	@RequestMapping(produces="text/plain",value = "/signup", method = RequestMethod.POST)
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

		try{   
			session.save(client);
			session.save(user); 
			t.commit();
			if(sendOTP(user.getMobileNum()) == "failure")
			{
				throw new Exception();
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return "failure" + e.getStackTrace().toString();
		}
		System.out.println(signUpRequest.toString());
		return "success";
	}

	@RequestMapping(value="/sayHello", method=RequestMethod.GET)
	@ResponseBody
	public String whatever() {
		return "Hello";
	}

	@RequestMapping(produces="text/plain",value = "/otp", method = RequestMethod.GET)
	public String otpValidation(@RequestParam String loginId, @RequestParam int otp) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session.createQuery("from com.foodex.user.model.dataentities.UsersEntity where mobileNum= :mobileNum");
		query.setString("mobileNum", loginId);
		UsersEntity user = (UsersEntity) query.uniqueResult();
		if(user == null)
		{
			return "failure";
		}
		t.commit();
		session.close();
		System.out.println(user.getRecentOTP());
		return user.getRecentOTP() == otp ? "success" : "failure";
	}

	@RequestMapping(produces="text/plain",value = "/newpassword", method = RequestMethod.GET)
	public String newPassword(@RequestParam long loginId, @RequestParam String password) {
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction t = session.beginTransaction();
			Query query = session.createQuery("update com.foodex.user.model.dataentities.UsersEntity set password= :password where mobileNum= :mobileNum");
			query.setLong("mobileNum", loginId);
			query.setString("password", password);
			t.commit();
			session.close();
			return "success";
		}
		catch(Exception ex)
		{
			return "failure";	
		}
	}

	@RequestMapping(produces="text/plain",value = "/sendotp", method = RequestMethod.GET)
	public String forgotPassword(@RequestParam long loginId) {
		return sendOTP(loginId);
	}

	private String sendOTP(long mobileNumber)
	{
		int otp = UniqueIdGenerator.generateOTP();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query query = session.createQuery("update com.foodex.user.model.dataentities.UsersEntity set recentOTP= :recentOTP where mobileNum= :mobileNum");
		query.setLong("mobileNum", mobileNumber);
		query.setInteger("recentOTP", otp);
		try
		{
			t.commit();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return "failure";
		}
		String smsAPI = "http://api.mvaayoo.com/mvaayooapi/MessageCompose?user=foodexhome@gmail.com:foodex123&senderID=TEST%20SMS&receipientno="+mobileNumber+"&msgtxt=Hi,%20Welcome%20to%20FOODEX.%20Please%20enter%20this%20OTP%20"+otp+".&state=4";
		HttpHelper httpHelper = new HttpHelper();
		httpHelper.get(smsAPI);
		return "success";
	}

}