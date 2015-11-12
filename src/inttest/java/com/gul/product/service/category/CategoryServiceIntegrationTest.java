package com.gul.product.service.category;

import static org.assertj.core.api.Assertions.assertThat;
import io.dropwizard.flyway.FlywayFactory;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import org.apache.commons.lang3.StringUtils;
import org.flywaydb.core.Flyway;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import com.gul.product.service.app.ProductServiceApplicationTest;
import com.gul.product.service.app.ProductServiceConfigurationTest;
import com.gul.product.service.representation.Category;

public class CategoryServiceIntegrationTest {

	private static final String REST_PRODUCT_SERVICE_URL = "http://localhost:%d/gul-product-service";
	private static Flyway flyway;
	
	@ClassRule
    public static final DropwizardAppRule<ProductServiceConfigurationTest> RULE =
            new DropwizardAppRule<ProductServiceConfigurationTest>(ProductServiceApplicationTest.class, ResourceHelpers.resourceFilePath("testProductService.yml"));

	@BeforeClass
	public static void setupClass() {
		FlywayFactory flywayFactory = RULE.getConfiguration().getFlyway();

		String url = RULE.getConfiguration().getDatabase().getUrl();
		String user = RULE.getConfiguration().getDatabase().getUser();
		String password = RULE.getConfiguration().getDatabase().getPassword();
		
		JdbcDataSource ds = new JdbcDataSource();
		ds.setURL(url);
		ds.setUser(user);
		ds.setPassword(password);		

		flyway = flywayFactory.build(ds);

		// migrate category
		flyway.migrate();		
		
		// migrate product
		flyway.migrate();
		
		// migrate shop
		flyway.migrate();

		// migrate shop
		flyway.migrate();
}
	
	@Test
	public void test_initial_category_setup() {
		Client client = JerseyClientBuilder.createClient();
		Category womenCategory = new Category("10000", "Women");

		Category womenHandbag = new Category("20000", "Handbag");
		Category womenHandbagTote = new Category("20001", "Tote bags");
		Category womenHandbagPurse = new Category("20002", "Purse bags");
		
		Category womenTunic = new Category("30000", "Top");
		Category womenTunicTop = new Category("30001", "Tunic Top");
		Category womenJerseyTop = new Category("30002", "Jersey Top");

		List<Category> womenSub = new ArrayList<>(Arrays.asList(womenHandbag, womenTunic));
		List<Category> womenHandbagSub = new ArrayList<>(Arrays.asList(womenHandbagTote, womenHandbagPurse));
		List<Category> womenTunicSub = new ArrayList<>(Arrays.asList(womenTunicTop, womenJerseyTop));
		
		womenCategory.setSubCategories(womenSub);
		womenHandbag.setSubCategories(womenHandbagSub);
		womenTunic.setSubCategories(womenTunicSub);
		
		Category categoryPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort())).path("/category")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(womenCategory), Category.class);

		Long categoryId = categoryPersisted.getId();
		assertThat(categoryId).isNotNull();
		assertThat(categoryPersisted.getSubCategories()).isNotEmpty();
		
	}
	
	
	@AfterClass
	public static void teardownClass() throws IOException {
		String dblocation = StringUtils.substringAfter(RULE.getConfiguration().getDatabase().getUrl(), ".");
		Files.deleteIfExists(Paths.get( new StringBuilder(".").append(dblocation).append(".").append("mv").append(".").append("db").toString() ));
		Files.deleteIfExists(Paths.get( new StringBuilder(".").append(dblocation).append(".").append("trace").append(".").append("db").toString() ));
	}

}
