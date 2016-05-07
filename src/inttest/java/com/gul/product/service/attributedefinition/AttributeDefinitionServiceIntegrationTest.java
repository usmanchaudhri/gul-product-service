package com.gul.product.service.attributedefinition;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gul.product.service.product.AbstractProductServiceIntegrationTest;
import com.gul.product.service.representation.AttributeDefinition;
import com.gul.product.service.representation.AttributeValue;
import com.gul.product.service.representation.Category;
import com.gul.product.service.representation.Customer;
import com.gul.product.service.representation.Designer;
import com.gul.product.service.representation.ImageInfo;
import com.gul.product.service.representation.Order;
import com.gul.product.service.representation.Product;
import com.gul.product.service.representation.ProductVariation;
import com.gul.product.service.representation.Shop;

public class AttributeDefinitionServiceIntegrationTest extends AbstractProductServiceIntegrationTest {
	
	@Test
	public void test_create_and_associate_attributeDefinition_to_a_product() throws JsonParseException, JsonMappingException, IOException {
		Client client = JerseyClientBuilder.createClient();
		
		// NEW PRODUCT
		Product productRequest = new Product();
		productRequest.setName("Test Women Skirt");
		productRequest.setSku("SKU101");
		productRequest.setShortDesc("Short Description Women Skirt");
		productRequest.setLongDesc("Long Description Women Skirt");
		productRequest.setQuantity(10L);

		Category categoryRequest = new Category("1000", "Women");
		Category categoryPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort())).path("/category")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(categoryRequest), Category.class);
		assertThat(categoryPersisted.getId());
		
		// CREATE CUSTOMER
		Customer customer = new Customer();
		customer.setUsername("usman.chaudhri@gmail.com");
		customer.setPassword("password");
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer").append("/signup").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);
		assertThat(customerPersisted.getId()).isNotNull();
		
		// CREATE SHOP
		Shop shopRequest = new Shop("gulgs");
		shopRequest.setShopOwner(customerPersisted);

		// CREATE DESIGNER
		Designer designer = new Designer();
		designer.setName("Nayyar Chaudhri");
		designer.setImagePath("/winter/clothes");
		List<Designer> designers = new ArrayList<Designer>();
		designers.add(designer);
		shopRequest.setDesigners(designers);
		Shop shopPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort())).path("/shop")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(shopRequest), Shop.class);
		assertThat(shopPersisted.getId());
		
		ProductVariation variation = new ProductVariation();

		// SET IMAGE INFO
		ImageInfo imageInfo = new ImageInfo();
		imageInfo.setImagePath("/2015/winter");
		productRequest.setImageInfo(imageInfo);

		// PERSIST PRODUCT
		productRequest.setCategory(categoryPersisted);
		productRequest.setShop(shopPersisted);
		productRequest.setProductVariation(Arrays.asList(variation));
		Product productPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort())).path("/product")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(productRequest), Product.class);
		assertThat(productPersisted.getId()).isNotNull();

		// CREATING ATTRIBUTES
		
		AttributeDefinition attrDef = new AttributeDefinition();
		attrDef.setAttributeName("Neckline");
		attrDef.setProduct(productPersisted);
		
		Set<AttributeDefinition> attrDefs = new HashSet<AttributeDefinition>();
		attrDefs.add(attrDef);
		productPersisted.setAttributeDefinitions(attrDefs);
	
		// Neckline - simple cut
		AttributeValue simpleCut = new AttributeValue();
		simpleCut.setAttrValue("Simple Cut");
		simpleCut.setImagePath("/attributeDefinition/imagePath");
		simpleCut.setIsActive(true);
		simpleCut.setAttributeDefinition(attrDef);
		
		// Neckline - round cut
		AttributeValue roundCut = new AttributeValue();
		roundCut.setAttrValue("Round Cut");
		roundCut.setImagePath("/attributeDefinition/imagePath");
		roundCut.setIsActive(true);
		roundCut.setAttributeDefinition(attrDef);

		// Neckline - round cut
		AttributeValue squareCut = new AttributeValue();
		squareCut.setAttrValue("Round Cut");
		squareCut.setImagePath("/attributeDefinition/imagePath");
		squareCut.setIsActive(true);
		squareCut.setAttributeDefinition(attrDef);
		
		List<AttributeValue> necklineCuts = new ArrayList<AttributeValue>();
		necklineCuts.add(simpleCut);
		necklineCuts.add(roundCut);
		necklineCuts.add(squareCut);
		
		attrDef.setAttributeValues(necklineCuts);
		attrDef.setIsActive(true);

		// PERSIST ATTRIBUTE
		AttributeDefinition attributePersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/product/").append(productPersisted.getId()).append("/attributedefinition").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(attrDef), AttributeDefinition.class);
		assertThat(attributePersisted.getId()).isNotNull();
		assertThat(attributePersisted.getAttributeName()).isEqualToIgnoringCase("Neckline");
		assertThat(attributePersisted.getProduct().getName()).isEqualToIgnoringCase("Test Women Skirt");
		
		AttributeValue value1 = attributePersisted.getAttributeValues().get(0);
		AttributeValue value2 = attributePersisted.getAttributeValues().get(1);
		AttributeValue value3 = attributePersisted.getAttributeValues().get(2);
		assertThat(value1.getId()).isNotNull();
		assertThat(value2.getId()).isNotNull();
		assertThat(value3.getId()).isNotNull();
		
		
		// GET PRODUCT
		JsonNode getPersistedProduct = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/product/").append(productPersisted.getId()).append("/attributedefinition").toString())
				.request(MediaType.APPLICATION_JSON)
				.get(JsonNode.class);
		ObjectMapper mapper = new ObjectMapper();
		List<AttributeDefinition> getPersistedProducts = mapper.readValue(mapper.treeAsTokens(getPersistedProduct) , new TypeReference<List<AttributeDefinition>>(){});
		AttributeDefinition getPersistedAttributeDefinition = getPersistedProducts.get(0);
		assertThat(getPersistedAttributeDefinition.getId()).isNotNull();
		assertThat(getPersistedAttributeDefinition.getAttributeName()).isEqualTo("Neckline");
		assertThat(getPersistedAttributeDefinition.getIsActive()).isEqualTo(true);
		assertThat(getPersistedAttributeDefinition.getAttributeValues().get(0).getId());

	}

}
