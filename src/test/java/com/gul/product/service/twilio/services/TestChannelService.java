package com.gul.product.service.twilio.services;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import com.twilio.sdk.resource.instance.ipmessaging.Channel;

public class TestChannelService {
	
	@Test
	public void test_get_channel_with_unique_name() {
		ChannelService channelService = new ChannelService("ACf241176efd7d94d2512d1ecd4a6f943e", 
				"08690c795b201adaaac4135eb2f0b553", 
				"ISab9dc2089e6e483fadae58078216bb03");
		Channel channel = channelService.getChannel("AmjadGulgs");

		assertThat(channel.getSid()).isNotNull();
		assertThat(channel.getFriendlyName().isEmpty()).isTrue();
		assertThat(channel.getUniqueName()).isEqualToIgnoringCase("AmjadGulgs");
		
		System.out.println("Friendly Name" + channel.getFriendlyName());
	}

}
