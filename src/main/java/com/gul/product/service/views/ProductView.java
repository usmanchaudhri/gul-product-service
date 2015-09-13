package com.gul.product.service.views;

import com.gul.product.service.representation.Product;

import io.dropwizard.views.View;

public class ProductView extends View {
    private final Product product;

    public enum Template {
    	FREEMARKER("freemarker/product.ftl"),
    	MUSTACHE("mustache/product.mustache");
    	
    	private String templateName;
    	private Template(String templateName){
    		this.templateName = templateName;
    	}
    	
    	public String getTemplateName(){
    		return templateName;
    	}
    }
    	 
    
	public ProductView(ProductView.Template template, Product product) {
		super(template.getTemplateName());
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

}
