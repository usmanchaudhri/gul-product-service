package com.gul.product.service.representation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// holds the current price of the product
@Entity
@Table(name = "PRICING_PRODUCT")
public class PricingProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "pricing_product_id", nullable = false) private Long id;
	@OneToOne(mappedBy="pricingProduct") private Product product;
	@Column(name = "stored_value", nullable = false) private Double storedValue;

	public PricingProduct() {}
	
	public PricingProduct(Double storedValue) {
		this.storedValue = storedValue;
	}
	
	// TODO - audit fields
	
	public Long getPricingProductId() {
		return id;
	}
	public void setPricingProductId(Long id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Double getStoredValue() {
		return storedValue;
	}
	public void setStoredValue(Double storedValue) {
		this.storedValue = storedValue;
	}
	
}
