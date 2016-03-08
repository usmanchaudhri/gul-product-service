package com.gul.product.service.app;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayFactory;

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

@Slf4j
public class ProductServiceConfigurationTest extends Configuration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceConfigurationTest.class);

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();
    
    @Valid
    private FlywayFactory flyway = new FlywayFactory();

    @NotNull
    private Map<String, Map<String, String>> viewRendererConfiguration = Collections.emptyMap();
	
	@NotEmpty
	@JsonProperty
	private String template;
	
	@NotEmpty
	@JsonProperty
	private String defaultName = "Stranger";

	@NotEmpty
	@JsonProperty
	private String localdev = "yes";
	
	@JsonProperty
	private String twillioAccountSid;

	@JsonProperty
	private String twillioAuthToken;

	@JsonProperty
	private String twillioServiceSid;

	@JsonProperty
	private String twillioAuthorizationHeaderName;

	@JsonProperty
	private String twillioAccessUrl;

	
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

	public FlywayFactory getFlyway() {
		return flyway;
	}

	public void setFlyway(FlywayFactory flyway) {
		this.flyway = flyway;
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
