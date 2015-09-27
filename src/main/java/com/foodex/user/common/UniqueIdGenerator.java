package com.foodex.user.common;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class UniqueIdGenerator {

	public String newClientId(Session session)
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");// populates the data of the //
		Query query = session.createQuery("select clientKey from com.foodex.user.model.dataentities.ClientEntity ORDER BY clientKey");
		query.setFirstResult(0);
		query.setMaxResults(1);
		List<String> lstClientId = query.list();
		String lastClientId = lstClientId.get(0);
		Long clientId = lastClientId == null ? 1000000 : Long.parseLong(lastClientId.substring(0, 7)) + 1;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String newClientId = clientId.toString() + dateFormat.format(new Date(System.currentTimeMillis()));
		return newClientId;
	}

}
