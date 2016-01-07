package com.gul.product.service.representation;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gul.product.service.audit.TimeStamped;

// holds the current price of the product
@Entity
@Table(name = "PRICING_PRODUCT")
public class PricingProduct implements Serializable, TimeStamped {
	
	@Id 
	@SequenceGenerator(name = "pricingProductSeq", sequenceName="pricing_product_pricing_product_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pricingProductSeq")
	@Column(name = "pricing_product_id", nullable = false) private Long id;
	
    @OneToOne(mappedBy="pricingProduct") private Product product;
	@Column(name = "stored_value", nullable = false) private Double storedValue;
	
	@Column(name = "created_on", nullable = true) private Date createdOn;
	@Column(name = "updated_on", nullable = true) private Date updatedOn;

	public PricingProduct() {}
	
	public PricingProduct(Double storedValue) {
		this.storedValue = storedValue;
	}
	public Long getPricingProductId() {
		return id;
	}
	public void setPricingProductId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
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

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
}
