package com.gul.product.service.representation;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *	- products should not be saved along with category	
 * 
 **/
@Entity
@Table(name = "CATEGORY1")
public class Category1 {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_id", nullable = false) private Long id;
	@Column(name = "name", nullable = false) private String name;

	@OneToMany(mappedBy="category", fetch = FetchType.LAZY)
	private Set<Product1> products = new HashSet<Product1>();

	public Category1(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Product1> getProducts() {
		return new HashSet<Product1>(products);
	}

	public void setProducts(Set<Product1> products) {
		this.products = products;
	}

}
