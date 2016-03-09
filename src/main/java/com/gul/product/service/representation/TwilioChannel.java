package com.gul.product.service.representation;

public class TwilioChannel {
	
	private String accountSid;
	private String sid;
	private String uniqueName;
	private String serviceSid;
	private String roleSid;
	private String channelSid;
	
	// messages
	private String body;
	private String from;
	private String to;
	
	public TwilioChannel(String accountSid, String sid, String uniqueName, String serviceSid) {
		this.accountSid = accountSid;
		this.sid = sid;
		this.uniqueName = uniqueName;
		this.serviceSid = serviceSid;
	}
	
	public String getAccountSid() {
		return accountSid;
	}
	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getUniqueName() {
		return uniqueName;
	}
	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}
	public String getServiceSid() {
		return serviceSid;
	}
	public void setServiceSid(String serviceSid) {
		this.serviceSid = serviceSid;
	}

	public String getRoleSid() {
		return roleSid;
	}

	public void setRoleSid(String roleSid) {
		this.roleSid = roleSid;
	}

	public String getChannelSid() {
		return channelSid;
	}

	public void setChannelSid(String channelSid) {
		this.channelSid = channelSid;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
	

}
