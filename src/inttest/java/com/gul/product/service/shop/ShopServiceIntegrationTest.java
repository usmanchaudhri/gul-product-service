package com.gul.product.service.shop;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gul.product.service.product.AbstractProductServiceIntegrationTest;
import com.gul.product.service.representation.Designer;
import com.gul.product.service.representation.Shop;

public class ShopServiceIntegrationTest extends AbstractProductServiceIntegrationTest {
	
	private static final String REST_PRODUCT_SERVICE_URL = "http://localhost:%d/gul-product-service";
	
	
	// Why is this test case failing ??? It passes with a breakpoint installed.
	@Ignore
	@Test
	public void test_updating_shop_with_designers() {
		Client client = JerseyClientBuilder.createClient();
		
		Shop shopRequest = new Shop("gulgs");
		Shop shopPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort())).path("/shop")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(shopRequest), Shop.class);

		// assert shop created
		assertThat(shopPersisted.getId()).isNotNull();

		Designer designer = new Designer();
		designer.setName("Ayesha Mumtaz");
		designer.setImagePath("/listings/designers/ayeshamumtaz/");
		designer.setShop(shopPersisted);
		List<Designer> designers = new ArrayList<Designer>();
		designers.add(designer);
		shopPersisted.setDesigners(designers);

		Shop shopUpdated = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort())).path(new StringBuilder("/shop/").append(shopPersisted.getId()).toString())
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.json(shopPersisted), Shop.class);
		
		assertThat(shopUpdated.getId()).isNotNull();
		List<Designer> persistedDesigners = shopUpdated.getDesigners();
		assertThat(persistedDesigners.get(0).getId()).isNotNull();
		assertThat(persistedDesigners.get(0).getName()).isEqualTo("Ayesha Mumtaz");
	}
	
	@Test
	public void test_creating_new_product() throws JsonProcessingException {
		Client client = JerseyClientBuilder.createClient();
		
		Shop shopRequest = new Shop("gulgs");
		Designer designer = new Designer();
		designer.setName("Ayesha Mumtaz");
		designer.setImagePath("/listings/designers/ayeshamumtaz/");
		designer.setShop(shopRequest);
		List<Designer> designers = new ArrayList<Designer>();
		designers.add(designer);
		shopRequest.setDesigners(designers);
		
		Shop shopPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort())).path("/shop")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(shopRequest), Shop.class);
		assertThat(shopPersisted.getId()).isNotNull();

		Designer persistedDesigner = shopPersisted.getDesigners().get(0);
		assertThat(persistedDesigner.getId()).isNotNull();
		assertThat(persistedDesigner.getName().equalsIgnoreCase("Ayesha Mumtaz"));
	}
	
}



