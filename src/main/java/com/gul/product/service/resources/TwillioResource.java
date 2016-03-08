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

	protected String getErrorString(int errorCode) {
		StringBuilder error = new StringBuilder();
		switch(errorCode) {
			case 50001:
				error.append("FriendlyName not provided");
				break;
			case 50002:
				error.append("Account SID not provided");
				break;
			case 50050:
				error.append("Service Instance not found");
				break;
			case 50051:
				error.append("Service SID not provided");
				break;
			case 50052:
				error.append("Invalid consumption interval format");
				break;
			case 50053:
				error.append("Invalid typing indicator format");
				break;
			case 50054:
				error.append("Invalid webhook format");
				break;
			case 50055:
				error.append("Invalid webhook method");
				break;
			case 50056:
				error.append("Webhook disabled processing of command");
				break;
			case 50100:
				error.append("Role not found");
				break;
			case 50101:
				error.append("Channel role not found");
				break;
			case 50102:
				error.append("Deployment role not found");
				break;
			case 50103:
				error.append("Role SID not provided");
				break;
			case 50104:
				error.append("Permission not found");
				break;
			case 50105:
				error.append("Invalid role type");
				break;
			case 50106:
				error.append("Channel creator role not found");
				break;
			case 50107:
				error.append("User not authorized for command");
				break;
			case 50200:
				error.append("User not found");
				break;
			case 50201:
				error.append("User already exists");
				break;
			case 50202:
				error.append("User SID not provided");
				break;
			case 50203:
				error.append("Username reserved");
				break;
			case 50204:
				error.append("Username not provided");
				break;
			case 50205:
				error.append("User unauthorized to set role");
				break;
			case 50300:
				error.append("Channel not found");
				break;
			case 50301:
				error.append("Channel key not provided");
				break;
			case 50302:
				error.append("Unknown channel command");
				break;
			case 50303:
				error.append("Attributes too long");
				break;
			case 50304:
				error.append("Attributes not valid JSON");
				break;
			case 50305:
				error.append("Channel SID not provided");
				break;
			case 50306:
				error.append("Unique name should not match channel SID pattern");
				break;
			case 50307:
				error.append("Channel with provided unique name already exists");
				break;
			case 50400:
				error.append("User not member of channel");
				break;
			case 50401:
				error.append("Member SID not provided");
				break;
			case 50402:
				error.append("Member not found");
				break;
			case 50500:
				error.append("Message not found");
				break;
			case 50501:
				error.append("Message SID not provided");
				break;
			case 50502:
				error.append("Message index not provided");
				break;
			case 50503:
				error.append("Message body not provided");
				break;
			case 50600:
				error.append("Invite SID not provided");
				break;
			case 50601:
				error.append("Invite not found");
				break;
			default:
				error.append("error calling twilio service");
		}
		
		return error.toString();
	}
}
