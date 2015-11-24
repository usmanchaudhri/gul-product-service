package com.gul.product.service.representation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * display featured products
 **/
@Entity
@Table(name = "FEATURED_PRODUCT")
public class FeaturedProduct {
	
	@Id
	@SequenceGenerator(name = "featuredproductsseq", sequenceName = "featuredproducts_featuredproducts_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "featuredproductsseq")
	@Column(name = "featured_product_id", nullable = false, unique = true) private Long id;
//	@OneToOne(mappedBy = "featuredProduct") private Product product;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Product getProduct() {
//		return product;
//	}
//
//	public void setProduct(Product product) {
//		this.product = product;
//	}
	
}
