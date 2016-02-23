package com.gul.product.service.app;

import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import java.util.Collections;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gul.product.service.core.Template;
import com.gul.product.service.heroku.db.HerokuDatabaseConfiguration;

@Slf4j
public class ProductServiceConfiguration extends Configuration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceConfiguration.class);

    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory database = new DataSourceFactory();

    @NotNull
    private Map<String, Map<String, String>> viewRendererConfiguration = Collections.emptyMap();

    @Valid
    @NotNull
    private JerseyClientConfiguration jerseyClient = new JerseyClientConfiguration();
    
	@NotEmpty
	@JsonProperty
	private String template;
	
	@NotEmpty
	@JsonProperty
	private String defaultName = "Stranger";

	@NotEmpty
	@JsonProperty
	private String localdev = "yes";
	
	@JsonProperty("swagger")
	public SwaggerBundleConfiguration swaggerBundleConfiguration;
	
	@JsonProperty
	public String twillioAccountSid;

	@JsonProperty
	public String twillioAuthToken;

	@JsonProperty
	public String twillioServiceSid;

	@JsonProperty
	public String twillioAuthorizationHeaderName;

	@JsonProperty
	public String twillioAccessUrl;
	
	public Template buildTemplate() {
		return new Template(template, defaultName);
	}

	public String getTemplate() {
		return template;
	}

	public String getDefaultName() {
		return defaultName;
	}

    @JsonProperty("database")
	public DataSourceFactory getDatabase() {
//        LOGGER.info("Dropwizard dummy DB URL (will be overridden)=" + database.getUrl());
//        DatabaseConfiguration databaseConfiguration = HerokuDatabaseConfiguration.create(System.getenv("DATABASE_URL"));
//        database = databaseConfiguration.getDataSourceFactory(null);
//        LOGGER.info("Heroku DB URL=" + database.getUrl());
        return database;
	}

    @JsonProperty("database")
	public void setDatabase(DataSourceFactory database) {
		this.database = database;
	}

	public Map<String, Map<String, String>> getViewRendererConfiguration() {
		return viewRendererConfiguration;
	}

	public void setViewRendererConfiguration(
			Map<String, Map<String, String>> viewRendererConfiguration) {
		this.viewRendererConfiguration = viewRendererConfiguration;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}

	public String getLocaldev() {
		return localdev;
	}

	public void setLocaldev(String localdev) {
		this.localdev = localdev;
	}

	public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {
		return swaggerBundleConfiguration;
	}

	public void setSwaggerBundleConfiguration(
			SwaggerBundleConfiguration swaggerBundleConfiguration) {
		this.swaggerBundleConfiguration = swaggerBundleConfiguration;
	}

	@JsonProperty("jerseyClient")
	public JerseyClientConfiguration getJerseyClient() {
		return jerseyClient;
	}

	public void setJerseyClient(JerseyClientConfiguration jerseyClient) {
		this.jerseyClient = jerseyClient;
	}

	public String getTwillioAccountSid() {
		return twillioAccountSid;
	}

	public void setTwillioAccountSid(String twillioAccountSid) {
		this.twillioAccountSid = twillioAccountSid;
	}

	public String getTwillioAuthToken() {
		return twillioAuthToken;
	}

	public void setTwillioAuthToken(String twillioAuthToken) {
		this.twillioAuthToken = twillioAuthToken;
	}

	public String getTwillioServiceSid() {
		return twillioServiceSid;
	}

	public void setTwillioServiceSid(String twillioServiceSid) {
		this.twillioServiceSid = twillioServiceSid;
	}

	public String getTwillioAuthorizationHeaderName() {
		return twillioAuthorizationHeaderName;
	}

	public void setTwillioAuthorizationHeaderName(
			String twillioAuthorizationHeaderName) {
		this.twillioAuthorizationHeaderName = twillioAuthorizationHeaderName;
	}

	public String getTwillioAccessUrl() {
		return twillioAccessUrl;
	}

	public void setTwillioAccessUrl(String twillioAccessUrl) {
		this.twillioAccessUrl = twillioAccessUrl;
	}

}
