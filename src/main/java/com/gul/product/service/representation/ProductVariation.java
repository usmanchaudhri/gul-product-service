package com.gul.product.service.representation;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Variations are optional but each product will most likely will have a
 * variation i.e color, quantity, material etc.
 * 
 * {"productVariation":[{"size":"x","color":"blue","quantity":"10","material":"leather"},{"size":"m","color":"blue","quantity":"4","material":"cotton"}]}
 * this class could be enhanced to support more variants for products, that may
 * also include customized Variants.
 * 
 * Currently use to store product size and quantity.
 * 
 * TODO: 
 * This class could be generic to handle mode customization.
 **/
@Entity
@Table(name = "PRODUCT_VARIATION")
public class ProductVariation {

	@Id
	@SequenceGenerator(name = "productvariationseq", sequenceName = "productvariation_productvariation_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productvariationseq")
	@Column(name = "productvariation_id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "size", nullable = true) public String size;
	@Column(name = "color", nullable = true)  public String color;
	@Column(name = "quantity", nullable = true) public String quantity;
	@Column(name = "material", nullable = true) public String material;
	@Column(name = "price", nullable = true) public String price;
	@Column(name = "mics", nullable = true) public String mics;
	
	@ManyToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name="product_id", referencedColumnName="product_id", nullable = true)
	private Product product;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getMics() {
		return mics;
	}

	public void setMics(String mics) {
		this.mics = mics;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
}
