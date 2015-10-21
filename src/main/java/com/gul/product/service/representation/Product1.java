package com.gul.product.service.representation;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT1")
public class Product1 {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id", nullable = false) private Long id;
    @Column(name = "name", nullable = false) private String name;

	@ManyToOne
	@JoinColumn(name="category_id", referencedColumnName ="category_id", nullable=false)
	private Category1 category;

	public Product1(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Category1 getCategory() {
		return category;
	}

	public void setCategory(Category1 category) {
		this.category = category;
	}


}
