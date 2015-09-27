package com.foodex.user.controller;

import java.sql.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
		System.out.println(loginId + "  " + password);
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
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
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String clientKey = UniqueIdGenerator.newClientId(session);
		ClientEntity client = new ClientEntity();
		client.setClientKey(clientKey);
		client.setClientFirsteName(signUpRequest.getFirstName());
		client.setClientLastName(signUpRequest.getLastname());
		client.setClientEmail(signUpRequest.getEmailId());
		client.setClientMobileNum(Long.parseLong(signUpRequest.getPhoneNumber()));
		client.setClientAddressLine1("al1");
		client.setClientAddressLine2("al2");
		client.setClientArea("area");
		client.setClientCity("city");
		client.setClientDOB(new Date(System.currentTimeMillis()));
		client.setClientGpsLocation("gps");
		client.setClientLandmark("landmark");
		client.setClientState("state");
		client.setClientZip(600100);
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
		}
		catch(Exception e){
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

}