package com.gul.product.service.representation;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SHOP")
public class Shop {

	@Id
	@SequenceGenerator(name = "shopseq", sequenceName = "shop_shop_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shopseq")
	@Column(name = "shop_id", nullable = false)
	private Long id;
	
	@Column(name = "shop_name", nullable = false) private String name;
	
	// TODO - each shop would have a designer
	private String designer;
	
	@OneToMany(mappedBy="shop", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<Product> products;

	public Shop(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Product> getProducts() {
		return products;
	}

	public void setProducts(Collection<Product> products) {
		this.products = products;
	} 

}
