package com.gul.product.service.representation;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * - order is to store product information which could later be re
 **/
@Entity
@Table(name = "ORDER_PRODUCT")
public class Order {
	
	@Id
	@SequenceGenerator(name = "orderseq", sequenceName = "order_order_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderseq")
	@Column(name = "order_id", nullable = false)
	private Long id;

	@Column(name = "product_id", nullable = false) private String productId;
	@Column(name = "product_name", nullable = false) private String productName;
	@Column(name = "product_sku", nullable = false) private String productSku;
	@Column(name = "product_quantity", nullable = false)  private String productQuantity;
	@Column(name = "product_price", nullable = false) private String productPrice;
	@Column(name = "product_image_path", nullable = false) private String productImagePath;
	@Column(name = "product_category_id", nullable = false)  private String productCategoryId;
	@Column(name = "product_shop_id", nullable = false)  private String productShopId;
	
	@ManyToOne
	@JoinColumn(name="customer_id", nullable=false)
	private Customer customer; 
	
	public Order(String productId, String productName, String productSku,
			String productQuantity, String productPrice,
			String productImagePath, String productCategoryId,
			String productShopId) {
		this.productId = productId;
		this.productName = productName;
		this.productSku = productSku;
		this.productQuantity = productQuantity;
		this.productPrice = productPrice;
		this.productImagePath = productImagePath;
		this.productCategoryId = productCategoryId;
		this.productShopId = productShopId;
	}
	
	public Order() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductSku() {
		return productSku;
	}

	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}

	public String getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductImagePath() {
		return productImagePath;
	}

	public void setProductImagePath(String productImagePath) {
		this.productImagePath = productImagePath;
	}

	public String getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getProductShopId() {
		return productShopId;
	}

	public void setProductShopId(String productShopId) {
		this.productShopId = productShopId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
