package com.gul.product.service.imageInfo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Test;

import com.gul.product.service.product.AbstractProductServiceIntegrationTest;
import com.gul.product.service.representation.Category;
import com.gul.product.service.representation.Designer;
import com.gul.product.service.representation.ImageInfo;
import com.gul.product.service.representation.Product;
import com.gul.product.service.representation.ProductVariation;
import com.gul.product.service.representation.Shop;

public class ImageInfoIntegrationTest extends AbstractProductServiceIntegrationTest {
	
	@Test
	public void test_update_imageInfo_image_path() {
		Client client = JerseyClientBuilder.createClient();
		
		// create product
		Product productRequest = new Product();
		productRequest.setName("Test Women Skirt");
		productRequest.setSku("SKU101");
		productRequest.setShortDesc("Short Description Women Skirt");
		productRequest.setLongDesc("Long Description Women Skirt");
		productRequest.setQuantity(10L);
		
		// create category
		Category categoryRequest = new Category("1000", "Women");
		Category categoryPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort())).path("/category")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(categoryRequest), Category.class);
		assertThat(categoryPersisted.getId());

		// create shop
		Shop shopRequest = new Shop("gulgs");
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
		imageInfo.setImagePath("/oldpath");
		productRequest.setImageInfo(imageInfo);
		
		// persist product
		Product productPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort())).path("/product")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(productRequest), Product.class);
		assertThat(productPersisted.getId()).isNotNull();
		
		Long imageInfoId = productPersisted.getImageInfo().getId();
		Long productId = productPersisted.getId();
		
		// set imageInfo
		ImageInfo updatedImageInfo = new ImageInfo();
		updatedImageInfo.setImagePath("/newpath");
		productRequest.setImageInfo(updatedImageInfo);
		
		ImageInfo imageInfoPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/product/").append(productId).append("/imageinfo/").append(imageInfoId).toString())
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.json(updatedImageInfo), ImageInfo.class);
		
		assertThat(imageInfoPersisted.getId()).isNotNull();
		assertThat(imageInfoPersisted.getImagePath()).isEqualTo("/newpath");

		Product updatedPersistedProduct = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/product/").append(productPersisted.getId()).toString())
				.request(MediaType.APPLICATION_JSON)
				.get(Product.class);
		assertThat(updatedPersistedProduct.getImageInfo().getImagePath()).isEqualTo("/newpath");
		
	}
		
	
}
