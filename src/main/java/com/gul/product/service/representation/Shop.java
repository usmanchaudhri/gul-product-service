package com.gul.product.service.representation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.gul.product.service.audit.TimeStamped;

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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
public class Shop implements Serializable, TimeStamped {

	@Id
	@SequenceGenerator(name = "shopseq", sequenceName = "shop_shop_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shopseq")
	@Column(name = "shop_id", nullable = false)
	private Long id;
	
	@Column(name = "shop_name", nullable = false) private String name;
	@Column(name = "details", nullable = true) private String details;
	@Column(name = "image_path", nullable = true) private String imagePath;
	
	@OneToMany(mappedBy="shop", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OrderBy("product_id")
	private List<Product> products;
	
	// shop should have a list of designers too.
	@OneToMany(mappedBy="shop", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Designer> designers = new ArrayList<Designer>();
	
	@OneToOne(mappedBy="shop", fetch = FetchType.LAZY)
	private Customer shopOwner;
	
	@Column(name = "created_on", nullable = true) private Date createdOn;
	@Column(name = "updated_on", nullable = true) private Date updatedOn;
	
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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Designer> getDesigners() {
		return designers;
	}

	public void addDesigners(Designer designer) {
		this.designers.add(designer);
		designer.setShop(this);
	}
	
	public void setDesigners(List<Designer> designers) {
		this.designers = designers;
		if(designers == null || designers.isEmpty()) {
			designers = new ArrayList<Designer>();
		}
		
		for(Designer designer : designers) {
			designer.setShop(this);
		}
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

	public Customer getShopOwner() {
		return shopOwner;
	}

	public void setShopOwner(Customer shopOwner) {
		this.shopOwner = shopOwner;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
