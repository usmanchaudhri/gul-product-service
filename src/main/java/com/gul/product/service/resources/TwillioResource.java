package com.gul.product.service.resources;

public abstract class TwillioResource {

	public static String ACCOUNT_SID;
	public static String AUTH_TOKEN;
	public static String SERVICE_SID;
	public static String AUTHORIZATION_HEADER_NAME;
	public static String TWILLIO_ACCESS_URL;
	
	protected TwillioResource(
			String accountSid, 
			String authToken, 
			String serviceSid, 
			String authorizationHeaderName,
			String twillioAccessUrl) {
		this.ACCOUNT_SID = accountSid;
		this.AUTH_TOKEN = authToken;
		this.SERVICE_SID = serviceSid;
		this.AUTHORIZATION_HEADER_NAME = authorizationHeaderName;
		this.TWILLIO_ACCESS_URL = twillioAccessUrl;
	}

}
