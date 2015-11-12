package com.gul.product.service.representation;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gul.product.service.representation.serializer.ProductShopSerializer;

/**
 * represents a Shop which contains list of products. 
 * - A shop can exits without a product.
 * - A shop needs to have a return address in case if customer wants to return items.
 **/

@Entity
@Table(name = "SHOP")
@NamedQueries({
    @NamedQuery(
            name = "com.gul.product.service.representation.Shop.findAll",
            query = "SELECT s FROM Shop s"
    )
})
public class Shop implements Serializable {

	@Id
	@SequenceGenerator(name = "shopseq", sequenceName = "shop_shop_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shopseq")
	@Column(name = "shop_id", nullable = false)
	private Long id;
	
	@Column(name = "shop_name", nullable = false) private String name;
	
	@OneToMany(mappedBy="shop", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<Product> products;
	
//	private String returnAddress;
	
	public Shop() {}
	
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
