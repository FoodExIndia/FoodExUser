package com.foodex.user.controller;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.foodex.user.model.UsersEntity;

public class test {

	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");// populates the data of the
											// configuration file

		// creating seession factory object
		SessionFactory factory = cfg.buildSessionFactory();

		// creating session object
		Session session = factory.openSession();

		// creating transaction object
		Transaction t = session.beginTransaction();

		
		
		UsersEntity e1 = new UsersEntity();
		//e1.setId(1);
		e1.setUsername("santhosh");
		e1.setPassword("12345");
		session.save(e1);
	/*	Query query=session.createQuery("insert into user (id,username,password) " + "select id,:username,:password from dual");
		query.setInteger("id", e1.getId());
		query.setString("username", e1.getUsername());
		query.setString("password", e1.getPassword());
		query.executeUpdate();*/
		//session.persist(e1);// persisting the object
		//session.merge(e1);
		

		t.commit();// transaction is committed
		
		session.close();
	}

}
