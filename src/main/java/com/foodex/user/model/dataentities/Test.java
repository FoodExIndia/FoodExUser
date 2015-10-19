package com.foodex.user.model.dataentities;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.foodex.user.common.UniqueIdGenerator;

public class Test {

	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory factory = cfg.buildSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		OrderEntity order = new OrderEntity();
		order.setBreakfastTime(new Timestamp(new Date().getTime()));
		order.setClientId(100000020);
		order.setFoodOrderFromDate(new java.sql.Date(new Date().getTime()));
		order.setFoodOrderToDate(new java.sql.Date(new Date().getTime()));
		order.setMenu("");
		order.setOrderCancellationFlag(0);
		order.setOrderStatus(1);
		order.setOrderId(UniqueIdGenerator.newOrderId("100000020"));
		order.setOrphanageFlag(0);
		order.setPlanKey(1);
		order.setTotalBillAmt(120.23);
		session.save(order);
		session.beginTransaction().commit();
	}

}
