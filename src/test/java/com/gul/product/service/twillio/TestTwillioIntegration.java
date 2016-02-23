package com.gul.product.service.twillio;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Ignore;
import org.junit.Test;

import com.twilio.sdk.TwilioIPMessagingClient;
import com.twilio.sdk.resource.instance.ipmessaging.Channel;
import com.twilio.sdk.resource.instance.ipmessaging.Service;
import com.twilio.sdk.resource.instance.ipmessaging.User;
import com.twilio.sdk.resource.list.ipmessaging.ChannelList;
import com.twilio.sdk.resource.list.ipmessaging.UserList;

//import com.twilio.sdk.TwilioRestClient;
//import com.twilio.sdk.TwilioRestException;
//import com.twilio.sdk.resource.instance.Account;
//import com.twilio.sdk.resource.instance.Message;
//import com.twilio.sdk.resource.list.AccountList;
//import com.twilio.sdk.resource.list.MessageList;

public class TestTwillioIntegration {

	// Find your Account Sid and Token at twilio.com/user/account
	public static final String ACCOUNT_SID = "ACf241176efd7d94d2512d1ecd4a6f943e";
	public static final String AUTH_TOKEN = "08690c795b201adaaac4135eb2f0b553";
	public static final String SERVICE_SID = "ISab9dc2089e6e483fadae58078216bb03";
	public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
	
	@Ignore
	@Test
	public void testTwillioRestCall() {
		String username = "ACf241176efd7d94d2512d1ecd4a6f943e";
		String password = "08690c795b201adaaac4135eb2f0b553";
		String usernamePassword = new StringBuilder().append(username).append(":").append(password).toString();
		String authorizationHeaderValue = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(usernamePassword.getBytes());

		Client client = JerseyClientBuilder.createClient();
		Channel channel = client
				.target(String.format("https://ip-messaging.twilio.com/v1"))
				.path(new StringBuilder("Services/ISab9dc2089e6e483fadae58078216bb03/Channels/CH3287f7e5dd794408bc7ccd23afd80f39").toString())
				.request(MediaType.APPLICATION_JSON)
				.header(AUTHORIZATION_HEADER_NAME, authorizationHeaderValue)
				.get(Channel.class);
		channel.getMembers();
		System.out.println("success");
	}
	
	@Ignore
	@Test
	public void testTwillioCallViaSdk() {
        TwilioIPMessagingClient client = new TwilioIPMessagingClient(ACCOUNT_SID, AUTH_TOKEN);
        Service service = client.getService(SERVICE_SID);
        ChannelList channelList = service.getChannels();
        for(Channel channel : channelList) {
        	channel.getAccountSid();
        }
        
        UserList userList = service.getUsers();
        for(User user : userList) {
        	user.getAccountSid();
        }
	}
	
//	public static void main(String[] args) throws TwilioRestException {
//		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
//
//		// client.getAccount(sid)
//		final Account account = client.getAccount();
//		
//		AccountList accountList = client.getAccounts();
//		for(Account acct : accountList) {
//		      System.out.println("Account Friendly Name " + acct.getFriendlyName());
//		      MessageList messageList = acct.getMessages();
//		      for(Message msg : messageList) {
//			      System.out.println("Twillio Message Body " + msg.getBody());
//		      }
//		}
//		
//		
		// Build a filter for the MessageList
//		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("Body", "Junaid please?! I love you <3"));
//		params.add(new BasicNameValuePair("To", "+13367937762"));
//		params.add(new BasicNameValuePair("From", "+13108098581"));
//
//		MessageFactory messageFactory = client.getAccount().getMessageFactory();
//		Message message = messageFactory.create(params);
//		System.out.println(message.getSid());
//	}
}
