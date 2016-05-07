package com.gul.product.service.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Ignore;
import org.junit.Test;

import com.gul.product.service.representation.AttributeDefinition;
import com.gul.product.service.representation.AttributeValue;
import com.gul.product.service.representation.Category;
import com.gul.product.service.representation.Customer;
import com.gul.product.service.representation.Designer;
import com.gul.product.service.representation.ImageInfo;
import com.gul.product.service.representation.Product;
import com.gul.product.service.representation.ProductVariation;
import com.gul.product.service.representation.Shop;

/**
 * End to end testing for product service. This class uses Flyway (db utility)
 * which loads the DB migrations for initial database setup. Rest resources are
 * called using HTTP client and response are than verified.
 **/
public class ProductServiceIntegrationTest extends AbstractProductServiceIntegrationTest {
	
	@Test
	public void test_add_attribute_definition_to_product() {
		Client client = JerseyClientBuilder.createClient();
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

		// CREATE SHOP
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
		Designer persistedDesigner = shopPersisted.getDesigners().get(0);
		assertThat(persistedDesigner.getId()).isNotNull();

		productRequest.setCategory(categoryPersisted);
		productRequest.setShop(shopPersisted);
		
		// set empty variations if there are none 
		ProductVariation variation = new ProductVariation();
		List<ProductVariation> variations = new ArrayList<ProductVariation>();
		variations.add(variation);
		productRequest.setProductVariation(variations);
		
		// set imageInfo
		ImageInfo imageInfo = new ImageInfo();
		imageInfo.setImagePath("/winter/clothes");
		productRequest.setImageInfo(imageInfo);
		
		// set attributeDefinition
		AttributeDefinition attrDefinition = new AttributeDefinition();
		attrDefinition.setAttributeName("Neckline");
		attrDefinition.setIsActive(true);
		
		// set attributeValue
		AttributeValue attrValue = new AttributeValue();
		attrValue.setAttrValue("Round Neck");
		attrValue.setImagePath("/listing/customization/images");
		attrValue.setIsActive(true);
		attrValue.setAttributeDefinition(attrDefinition);
		
		List<AttributeValue> attrValues = new ArrayList<AttributeValue>();
		attrValues.add(attrValue);
		
		attrDefinition.setAttributeValues(attrValues);
		Set<AttributeDefinition> attrDefinitions = new HashSet<AttributeDefinition>();
		attrDefinitions.add(attrDefinition);
		
			// setting definition in product request
		attrDefinition.setProduct(productRequest);
		productRequest.setAttributeDefinitions(attrDefinitions);
		
		// persist product
		Product productPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort())).path("/product")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(productRequest), Product.class);

		// check if the attributeDefinition is saved
		Set<AttributeDefinition> persistedAttrDefinition = productPersisted.getAttributeDefinitions();
		assertThat(persistedAttrDefinition).isNotNull();
		Iterator<AttributeDefinition> ite = persistedAttrDefinition.iterator();
		while(ite.hasNext()) {
			AttributeDefinition temp = ite.next();
			assertThat(temp.getId()).isNotNull();
		}
			
	}
	
	
	@Test
	public void test_fetch_all_product_pagination() {
		Client client = JerseyClientBuilder.createClient();

		// CREATE PRODUCT
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

		// CREATE SHOP
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
		Designer persistedDesigner = shopPersisted.getDesigners().get(0);
		assertThat(persistedDesigner.getId()).isNotNull();

		productRequest.setCategory(categoryPersisted);
		productRequest.setShop(shopPersisted);
		
		// set empty variations if there are none 
		ProductVariation variation = new ProductVariation();
		List<ProductVariation> variations = new ArrayList<ProductVariation>();
		variations.add(variation);
		productRequest.setProductVariation(variations);
		
		// set imageInfo
		ImageInfo imageInfo = new ImageInfo();
		imageInfo.setImagePath("/winter/clothes");
		productRequest.setImageInfo(imageInfo);
		
		// persist product
		Product productPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort())).path("/product")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(productRequest), Product.class);

		List<Product> fetchAllProducts = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.queryParam("first", 0)
				.queryParam("max", 1)
				.path("/product")
				.request(MediaType.APPLICATION_JSON)
				.get(List.class);
		
		assertThat(fetchAllProducts).isNotNull();
		assertThat(fetchAllProducts.size()).isEqualTo(1);
	}

	@Ignore
	@Test
	public void test_create_new_category_when_saving_product() {
		Client client = JerseyClientBuilder.createClient();

		// CREATE PRODUCT
		Product productRequest = new Product();
		productRequest.setName("Test Women Skirt");
		productRequest.setSku("SKU101");
		productRequest.setShortDesc("Short Description Women Skirt");
		productRequest.setLongDesc("Long Description Women Skirt");
		productRequest.setQuantity(10L);
	
		// CREATE CATEGORY
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
		Designer persistedDesigner = shopPersisted.getDesigners().get(0);
		assertThat(persistedDesigner.getId()).isNotNull();

		productRequest.setCategory(categoryPersisted);
		productRequest.setShop(shopPersisted);
		
		// set empty variations if there are none 
		ProductVariation variation = new ProductVariation();
		List<ProductVariation> variations = new ArrayList<ProductVariation>();
		variations.add(variation);
		productRequest.setProductVariation(variations);
		
		// set imageInfo
		ImageInfo imageInfo = new ImageInfo();
		imageInfo.setImagePath("/winter/clothes");
		productRequest.setImageInfo(imageInfo);
		
		// persist product
		Product productPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort())).path("/product")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(productRequest), Product.class);
		
		// i expect to see shop with a designer
		assertThat(productPersisted.getId()).isNotNull();
		assertThat(productPersisted.getCategory().getId()).isNotNull();
		assertThat(productPersisted.getShop().getId()).isNotNull();
		
		// TODO - designer results into null but with break point and a bit of delay works fine ???? Why ??????
		List<Designer> persistedDesigners = productPersisted.getShop().getDesigners();
		Designer persistedFirstDesigner = persistedDesigners.get(0);
		Long persistedFirstDesignerId = persistedFirstDesigner.getId();
		assertThat(persistedFirstDesignerId).isNotNull();
	}
	
	@Test
	public void test_updating_product() {
		Client client = JerseyClientBuilder.createClient();

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
		assertThat(categoryPersisted).isNotNull();

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
		Designer persistedDesigner = shopPersisted.getDesigners().get(0);
		assertThat(persistedDesigner.getId()).isNotNull();
		
		productRequest.setShop(shopPersisted);
		productRequest.setCategory(categoryPersisted);
		
		// testing passing empty variation
		ProductVariation variation = new ProductVariation();
		variation.setProduct(productRequest);
		List<ProductVariation> variations = new ArrayList<ProductVariation>();
		variations.add(variation);
		productRequest.setProductVariation(variations);
		
		ImageInfo imageInfo = new ImageInfo();
		imageInfo.setImagePath("/2015/winter");
		productRequest.setImageInfo(imageInfo);
		
		Product persistedProduct = client
			.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort())).path("/product")
			.request(MediaType.APPLICATION_JSON)
			.post(Entity.json(productRequest), Product.class);
		assertThat(persistedProduct).isNotNull();

		persistedProduct.setName("Updated Test Women Skirt");
		
		Product updatedPersistedProduct = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort())).path("/product/"+ persistedProduct.getId())
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.json(persistedProduct), Product.class);

		assertThat(updatedPersistedProduct.getId()).isNotNull();
		assertThat(updatedPersistedProduct.getName()).isEqualTo("Updated Test Women Skirt");
		assertThat(updatedPersistedProduct.getSku()).isEqualTo("SKU101");
	}
	
	@Test
	public void test_add_variation_size_when_creating_a_new_product() {
		Client client = JerseyClientBuilder.createClient();

		Product productRequest = new Product(); 
		productRequest.setName("Test Women Skirt");
		productRequest.setSku("SKU101");
		productRequest.setShortDesc("Short Description Women Skirt");
		productRequest.setLongDesc("Long Description Women Skirt");
		productRequest.setQuantity(10L);

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
		Designer persistedDesigner = shopPersisted.getDesigners().get(0);
		assertThat(persistedDesigner.getId()).isNotNull();
		productRequest.setShop(shopPersisted);
		
		ProductVariation variation1 = new ProductVariation();
		variation1.setColor("blue");
		variation1.setQuantity("5");
		variation1.setSize("x");
		variation1.setProduct(productRequest);
		ProductVariation variation2 = new ProductVariation();
		variation2.setColor("blue");
		variation2.setQuantity("2");
		variation2.setSize("m");
		variation2.setProduct(productRequest);

		List<ProductVariation> variations = new ArrayList<ProductVariation>();
		variations.add(variation1);
		variations.add(variation2);
		productRequest.setProductVariation(variations);
		
		ImageInfo imageInfo = new ImageInfo();
		imageInfo.setImagePath("/2015/winter");
		productRequest.setImageInfo(imageInfo);
		
		Category categoryRequest = new Category("1000", "Women");
		Category categoryPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort())).path("/category")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(categoryRequest), Category.class);
		assertThat(categoryPersisted).isNotNull();
	
		productRequest.setCategory(categoryPersisted);
		Product persistedProduct = client
			.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort())).path("/product")
			.request(MediaType.APPLICATION_JSON)
			.post(Entity.json(productRequest), Product.class);
		assertThat(persistedProduct).isNotNull();

		assertThat(persistedProduct.getId()).isNotNull();
		List<ProductVariation> persistedVariations =  persistedProduct.getProductVariation();
		for(ProductVariation persisterVariation : persistedVariations) {
			assertThat(persisterVariation.getId()).isNotNull();
		}
	}
	
	public void restAssuredTesting() {
//		given()
//		.contentType(MediaType.APPLICATION_JSON)
//		.body(MAPPER.writeValueAsString(productRequest))
//		.expect()
//		.statusCode(Status.CREATED.getStatusCode())
//		.contentType(MediaType.APPLICATION_JSON)
//		.when()
//		.body("sku", Matchers.equalTo("SKU101"), 
//				"name", Matchers.equalTo("Test Women Skirt"), 
//				"shortDesc", Matchers.equalTo("Short Description Women Skirt"), 
//				"longDesc", Matchers.equalTo("Long Description Women Skirt"), 
//				"imagePath", Matchers.equalTo("/winter/2015/women"))
//		.post(String.format("http://localhost:%d/gul-product-service/product", RULE.getLocalPort()));
	}
	
	
}
