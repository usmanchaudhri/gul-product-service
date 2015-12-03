package com.gul.product.service.representation;

public class SolrDoc {

	private Long id;
	private String productName;
	private String productDesc;
	private String productSku;
	private String productCategory;
	private String productShop;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getProductSku() {
		return productSku;
	}
	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getProductShop() {
		return productShop;
	}
	public void setProductShop(String productShop) {
		this.productShop = productShop;
	}
	
}
