package com.gul.product.service.twilio.services;

import java.util.HashMap;
import java.util.Map;

import com.twilio.sdk.TwilioIPMessagingClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.ipmessaging.Channel;
import com.twilio.sdk.resource.instance.ipmessaging.Service;
import com.twilio.sdk.resource.list.ipmessaging.ChannelList;

public class ChannelService {

	private String ACCOUNT_SID;
	private String AUTH_TOKEN;
	private String SERVICE_SID;
	private TwilioIPMessagingClient client = null;
	private Service service = null;
	
	public ChannelService(String accountSid, String authToken) {
		this.ACCOUNT_SID = accountSid;
		this.AUTH_TOKEN = authToken;
        client = new TwilioIPMessagingClient(ACCOUNT_SID, AUTH_TOKEN);
	}

	public ChannelService(String accountSid, String authToken, String serviceSid) {
		this.ACCOUNT_SID = accountSid;
		this.AUTH_TOKEN = authToken;
		this.SERVICE_SID = serviceSid;
		
        client = new TwilioIPMessagingClient(ACCOUNT_SID, AUTH_TOKEN);
        service = client.getService(SERVICE_SID);
	}
	
	public Channel createChannel(String uniqueName, String friendlyName) throws TwilioRestException {
		final Map<String, String> channelParams = new HashMap<String, String>();
        channelParams.put("FriendlyName", friendlyName);
		channelParams.put("UniqueName", uniqueName);
		return service.createChannel(channelParams);
	}
	
	public Channel getChannel(String uniqueName) {
		return service.getChannel(uniqueName);
	}
	
	public ChannelList getChannels() {
        return service.getChannels();
	}
	
}
