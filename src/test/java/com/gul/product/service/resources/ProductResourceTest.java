package com.gul.product.service.resources;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.dropwizard.testing.junit.ResourceTestRule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.ContextResolver;

import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.inmemory.InMemoryTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.jukito.JukitoRunner;
import org.junit.After;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.common.eventbus.EventBus;
import com.gul.product.service.indexing.ResourceEventSubscriber;
import com.gul.product.service.persistance.CategoryDao;
import com.gul.product.service.persistance.ProductDao;
import com.gul.product.service.representation.Category;
import com.gul.product.service.representation.PricingProduct;
import com.gul.product.service.representation.Product;
import com.gul.product.service.representation.ProductVariation;


/**
 * these tests are at the REST level
 **/
@RunWith(JukitoRunner.class)
public class ProductResourceTest {

	private static ProductDao productDao = mock(ProductDao.class);
	private static CategoryDao categoryDao = mock(CategoryDao.class);

//	@Inject private ResourceEventSubscriber entityEventSubscriber;
	
//	public static class EventBusConfig extends ResourceConfig {
//		public EventBusConfig() {
//			register(new AbstractBinder() {
//				@Override
//				protected void configure() {
//					bind(new EventBus()).to(EventBus.class);
//				}
//			});
//			packages(true, "com.gul.product.service.indexing");
//		}
//	}
	
	public static class EventBusBinder extends AbstractBinder {
		@Override
		protected void configure() {
			bind(new EventBus()).to(EventBus.class);
		}
	}
	
	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder()
			.addResource(new ProductResource(productDao, categoryDao))
			.addProvider(new EventBusBinder())
//			.addProvider(EntityEventSubscriber.class)
			.build();
	
//	ResourceTestRule.ResourceTestResourceConfig config = new ResourceTestResourceConfig(getServletConfig());

	public void init() {
//		ServiceLocatorUtilities.addClasses(locator, toAdd)
	}
	
	@Test
	public void test_get_product() {
		Product product = new Product(
				"abc131", 
				"test product",
				"this is a test product", 
				"test product long description",
				10L);
		
		when(productDao.findById(10L)).thenReturn(product);
		assertThat(resources.client().target("/product/10").request().get(Product.class)).isEqualTo(product);
		verify(productDao).findById(10L);
	}
	
	@Ignore
	@Test
	public void test_product_creation() {
		Product product = new Product(
				"Cloth_1001", 
				"Embroided Skirt",
				"Handmade embroidreded skirt", 
				"Pakistani cultural Skirt, hand embroidery",
				10L);
		Category category = new Category("1001", "Sub Girls Clothing");
		category.setId(10L);
		product.setCategory(category);
		PricingProduct pricingProduct = new PricingProduct(50.98);
		product.setPricingProduct(pricingProduct);
		
		ProductVariation variation1 = new ProductVariation();
		variation1.setColor("blue");
		variation1.setQuantity("5");
		variation1.setSize("x");
		variation1.setProduct(product);
		ProductVariation variation2 = new ProductVariation();
		variation2.setColor("blue");
		variation2.setQuantity("2");
		variation2.setSize("m");
		variation2.setProduct(product);

		List<ProductVariation> variations = new ArrayList<ProductVariation>();
		variations.add(variation1);
		variations.add(variation2);
		product.setProductVariation(variations);
		
		when(productDao.create(product)).thenReturn(product);		
		when(categoryDao.findById(category.getId())).thenReturn(category);
		
//		assertThat(resources.client().target("/product").request().post(Entity.json(product), Product.class)).isEqualTo(product);
		verify(productDao).create(product);
	}
	
	@Ignore
	@Test
	public void test_get_category_without_products() {
		Product product = new Product(
				"Cloth_1001", 
				"Embroided Skirt",
				"Handmade embroidreded skirt", 
				"Pakistani cultural Skirt, hand embroidery",
				10L);
		Category category = new Category("1001", "Sub Girls Clothing");
		category.setId(10L);
		product.setCategory(category);
		PricingProduct pricingProduct = new PricingProduct(50.98);
		product.setPricingProduct(pricingProduct);
		
		ProductVariation variation1 = new ProductVariation();
		variation1.setColor("blue");
		variation1.setQuantity("5");
		variation1.setSize("x");
		variation1.setProduct(product);
		ProductVariation variation2 = new ProductVariation();
		variation2.setColor("blue");
		variation2.setQuantity("2");
		variation2.setSize("m");
		variation2.setProduct(product);

		List<ProductVariation> variations = new ArrayList<ProductVariation>();
		variations.add(variation1);
		variations.add(variation2);
		product.setProductVariation(variations);

		when(productDao.create(product)).thenReturn(product);
		when(categoryDao.findById(category.getId())).thenReturn(category);
//		assertThat(resources.client().target("/product").request().post(Entity.json(product), Product.class)).isEqualTo(product);
		verify(productDao).create(product);
	}
	
	@Ignore
	@Test
	public void test_add_variation_size_when_saving_product() {
		Product product = new Product(
				"Cloth_1001", 
				"Embroided Skirt",
				"Handmade embroidreded skirt", 
				"Pakistani cultural Skirt, hand embroidery",
				10L);
		Category category = new Category("1001", "Sub Girls Clothing");
		category.setId(10L);
		product.setCategory(category);
		PricingProduct pricingProduct = new PricingProduct(50.98);
		product.setPricingProduct(pricingProduct);
	
		ProductVariation variation1 = new ProductVariation();
		variation1.setColor("blue");
		variation1.setQuantity("5");
		variation1.setSize("x");
		variation1.setProduct(product);
		ProductVariation variation2 = new ProductVariation();
		variation2.setColor("blue");
		variation2.setQuantity("2");
		variation2.setSize("m");
		variation2.setProduct(product);

		List<ProductVariation> variations = new ArrayList<ProductVariation>();
		variations.add(variation1);
		variations.add(variation2);
		product.setProductVariation(variations);
		
		when(productDao.create(product)).thenReturn(product);
		when(categoryDao.findById(category.getId())).thenReturn(category);
//		assertThat(resources.client().target("/product").request().post(Entity.json(product), Product.class)).isEqualTo(product);
		verify(productDao).create(product);
	}
	
	@After
    public void tearDown() {
        // we have to reset the mock after each test because of the
        // @ClassRule, or use a @Rule as mentioned below.
        reset(productDao);
    }

//	public EventBus getEventBus() {
//		return eventBus;
//	}
//
//	public void setEventBus(EventBus eventBus) {
//		this.eventBus = eventBus;
//	}

//	public ResourceEventSubscriber getEntityEventSubscriber() {
//		return entityEventSubscriber;
//	}
//
//	public void setEntityEventSubscriber(ResourceEventSubscriber entityEventSubscriber) {
//		this.entityEventSubscriber = entityEventSubscriber;
//	}

	
}
