package com.foodex.user.common;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class UniqueIdGenerator {

	public static String newClientId(Session session)
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		Query query = session.createQuery("select clientKey from com.foodex.user.model.dataentities.ClientEntity ORDER BY clientKey desc");
		query.setFirstResult(0);
		query.setMaxResults(1);
		@SuppressWarnings("unchecked")
		List<String> lstClientId = query.list();
		Long clientId;
		if(lstClientId.size() == 0)
		{
			clientId = (long) 1000000;
		}
		else
		{
			clientId = Long.parseLong(lstClientId.get(0).substring(0, 7)) + 1;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String newClientId = clientId.toString() + dateFormat.format(new Date(System.currentTimeMillis()));
		return newClientId;
	}

}
