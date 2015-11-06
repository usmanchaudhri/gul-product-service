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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.TypeDef;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 *	TODO - incase if we need to generate UUID's for products
 *	@Id
 *	@GeneratedValue(generator = "uuid-gen")
 *	@GenericGenerator(name = "uuid-gen", strategy = "uuid")
 *	@Column(name = "product_id", columnDefinition="uuid", nullable = false) 
 *	@Type(type="org.hibernate.type.PostgresUUIDType") private UUID id;				// uncomment this for it work with postgresql
 *	@Type(type="org.hibernate.type.UUIDCharType") private UUID id;
 *	@Type(type="uuid") private UUID id;
 **/
@Entity
@TypeDef(name = "uuid", typeClass = UuidType.class)
@Table(name = "PRODUCT")
@NamedQueries({
    @NamedQuery(
            name = "com.gul.product.service.representation.Product.findAll",
            query = "SELECT p FROM Product p"
    ),
    @NamedQuery(
            name = "com.gul.product.service.representation.Product.findProductsByCategory",
            query = "SELECT p FROM Product p WHERE p.category.id = :categoryId"
    )
})

//@JsonPropertyOrder(value = {"id", "name", "sku", "shortDesc", "longDesc", "imagePath", "quantity", "pricingProduct", "category", "shop"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
public class Product {
	
	@Id
	@SequenceGenerator(name = "productseq", sequenceName = "product_product_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productseq")
	@Column(name = "product_id", nullable = false)
	private Long id;
	@Column(name = "sku", nullable = false, unique = true) private String sku;	
    @Column(name = "name", nullable = false) private String name;
    @Column(name = "short_desc", nullable = false) private String shortDesc;
    @Column(name = "long_desc", nullable = true) private String longDesc;
    @Column(name = "image_path", nullable = false) private String imagePath;
    @Column(name = "quantity", nullable = false) private Long quantity;
    
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="pricing_product_id")
	private PricingProduct pricingProduct;
	
	@ManyToOne
	@JoinColumn(name="category_id", referencedColumnName ="category_id", nullable=false)
//	@JsonBackReference(value="productCategory")
	private Category category;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="shop_id", referencedColumnName="shop_id", nullable=false)
	private Shop shop;

	public Product() {}
	
	public Product(String sku, String name, String shortDesc, String longDesc, String imagePath) {
		this.sku = sku;
		this.name = name;
		this.shortDesc = shortDesc;
		this.longDesc = longDesc;
		this.imagePath = imagePath;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		
		Product product = (Product) o;
		if(id != null ? !id.equals(product.id) : product.id != null) return false;
		if(sku != null ? !sku.equals(product.sku) : product.sku != null) return false;
		if(name != null ? !name.equals(product.name) : product.name != null) return false;
		if(shortDesc != null ? !shortDesc.equals(product.shortDesc) : product.shortDesc != null) return false;
		if(longDesc != null ? !longDesc.equals(product.longDesc) : product.longDesc != null) return false;
		if(imagePath != null ? !imagePath.equals(product.imagePath) : product.imagePath != null) return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (sku != null ? sku.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (shortDesc != null ? shortDesc.hashCode() : 0);
		result = 31 * result + (longDesc != null ? longDesc.hashCode() : 0);
		result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
		return result;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	@JsonIgnore
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setPricingProduct(PricingProduct pricingProduct) {
		this.pricingProduct = pricingProduct;
	}

	public PricingProduct getPricingProduct() {
		return pricingProduct;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

}
