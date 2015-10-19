package com.foodex.user.common;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

@SuppressWarnings("deprecation")
public class HttpHelper {
	HttpClient client;
	HttpResponse response;
	
	public HttpHelper(){
		client = new DefaultHttpClient();
	}
	
	public HttpResponse get(String urn)
	{
		//String url = buildUrl(urn);
		HttpGet httpGet = new HttpGet(urn);
		try {
			response = client.execute(httpGet);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

		
//	private String buildUrl(String urn)
//	{
//		return "http://192.168.0.100:8081/FoodExUser/rest/User/" + urn;
//	}
}
