package com.gul.product.service.product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.dropwizard.flyway.FlywayFactory;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;

import org.apache.commons.lang3.StringUtils;
import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;

import com.gul.product.service.app.ProductServiceApplicationTest;
import com.gul.product.service.app.ProductServiceConfigurationTest;

public class AbstractProductServiceIntegrationTest {
	
	protected static final String REST_PRODUCT_SERVICE_URL = "http://localhost:%d/gul-product-service";
	protected static Flyway flyway;

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
		flyway.migrate();		// 1  migrate category
		flyway.migrate();		// 2  migrate product
		flyway.migrate();		// 3  migrate shop
		flyway.migrate();		// 4  migrate customer
		flyway.migrate();		// 5  migrate productVariation
		flyway.migrate();		// 6  migrate featureProducts
		flyway.migrate();		// 7  migrate note
		flyway.migrate();		// 8  migrate image info
		flyway.migrate();		// 9  migrate EmailSubscription
		flyway.migrate();		// 10 migrate Shipping
	}
	
//	@After
//	public void tearDown() {
//		flyway.clean();
//	}
	
	@AfterClass
	public static void teardownClass() throws IOException {
		String dblocation = StringUtils.substringAfter(RULE.getConfiguration().getDatabase().getUrl(), ".");
		Files.deleteIfExists(Paths.get( new StringBuilder(".").append(dblocation).append(".").append("mv").append(".").append("db").toString() ));
		Files.deleteIfExists(Paths.get( new StringBuilder(".").append(dblocation).append(".").append("trace").append(".").append("db").toString() ));
	}


}
