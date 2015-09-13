package com.foodex.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodex.user.security.Cipher;
import com.foodex.user.security.Decrypt;

@Service
@Controller
@RequestMapping("/User")
public class UserController {
	@Autowired
	Testing test;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@Cipher
	public @ResponseBody String loginValidation(@Decrypt @RequestParam String loginId, @Decrypt @RequestParam String password) {
		System.out.println(loginId + "  " + password);
		test.test("safsfkjsjkd");
		/*
		 * Configuration cfg = new Configuration();
		 * cfg.configure("hibernate.cfg.xml");// populates the data of the //
		 * configuration file
		 * 
		 * // creating seession factory object SessionFactory factory =
		 * cfg.buildSessionFactory();
		 * 
		 * // creating session object Session session = factory.openSession();
		 * 
		 * // creating transaction object Transaction t =
		 * session.beginTransaction();
		 * 
		 * UsersEntity e1 = new UsersEntity(); e1.setId(1);
		 * e1.setUsername("santhosh"); e1.setPassword("12345"); Query
		 * query=session.createQuery("insert into user (id,username,password) "
		 * + "select :id,:username,:password from dual"); query.setInteger("id",
		 * e1.getId()); query.setString("username", e1.getUsername());
		 * query.setString("password", e1.getPassword()); query.executeUpdate();
		 * //session.persist(e1);// persisting the object //session.merge(e1);
		 * 
		 * 
		 * t.commit();// transaction is committed
		 * 
		 * session.close();
		 */

		return "welcome";

	}

	/*
	 * @RequestMapping(value = "/login", method = RequestMethod.GET)
	 * public @ResponseBody String loginValidation(@RequestParam String
	 * loginId, @RequestParam String password) { System.out.println(loginId +
	 * "  " + password); return "welcome";
	 * 
	 * }
	 */

}