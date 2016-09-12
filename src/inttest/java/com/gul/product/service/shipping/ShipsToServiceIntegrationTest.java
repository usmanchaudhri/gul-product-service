package com.gul.product.service.shipping;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import com.gul.product.service.product.AbstractProductServiceIntegrationTest;
import com.gul.product.service.representation.ShipsTo;

public class ShipsToServiceIntegrationTest extends AbstractProductServiceIntegrationTest {	
	
	@Test
	public void test_create_shop_with_shipto_info() {
		Client client = JerseyClientBuilder.createClient();
		
		// shipping from
		ShipsTo shipsFrom = new ShipsTo();
		shipsFrom.setCountryName("PAKISTAN");

		// shipping to
		ShipsTo shipsToUSA = new ShipsTo();
		shipsToUSA.setShippingFrom(shipsFrom);
		shipsToUSA.setCountryName("USA");
		shipsToUSA.setProcessingDays(10L);
		shipsToUSA.setShippingCost(2000.00);

		ShipsTo shipsToBRAZIL = new ShipsTo();
		shipsToBRAZIL.setShippingFrom(shipsFrom);
		shipsToBRAZIL.setCountryName("BRAZIL");
		shipsToBRAZIL.setProcessingDays(10L);
		shipsToBRAZIL.setShippingCost(1500.40);

		ShipsTo shipsToDUBAI = new ShipsTo();
		shipsToDUBAI.setShippingFrom(shipsFrom);
		shipsToDUBAI.setCountryName("DUBAI");
		shipsToDUBAI.setProcessingDays(5L);
		shipsToDUBAI.setShippingCost(1000.00);

		Set<ShipsTo> allShipsTo = new HashSet<ShipsTo>();
		allShipsTo.add(shipsToUSA);
		allShipsTo.add(shipsToBRAZIL);
		allShipsTo.add(shipsToDUBAI);
		shipsFrom.setShippingTo(allShipsTo);
				
		ShipsTo shipsToPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/shipping").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(shipsFrom), ShipsTo.class);
		assertThat(shipsToPersisted.getId()).isNotNull();		
		
		Collection<ShipsTo> allShippingsTo = shipsToPersisted.getShippingTo();
		Assert.assertTrue(allShippingsTo.size() == 3);
		
		Iterator ite = allShippingsTo.iterator();
		while(ite.hasNext()) {
			ShipsTo shippings = (ShipsTo) ite.next();
			Assert.assertNotNull(shippings.getId());
		}
	}
	

}
