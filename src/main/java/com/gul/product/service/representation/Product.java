package com.gul.product.service.representation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.NamedQuery;

@Entity
@Table(name = "PRODUCT")
@NamedQueries({
    @NamedQuery(
            name = "com.gul.product.service.representation.Product.findAll",
            query = "SELECT p FROM Product p"
    )
})
public class Product {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) public Integer id;
    @Column(name = "sku", nullable = false) public String sku;	
    @Column(name = "name", nullable = false) public String name;
    @Column(name = "shortDesc", nullable = false) public String shortDesc;
    @Column(name = "longDesc", nullable = true) public String longDesc;
    @Column(name = "imagePath", nullable = false) public String imagePath;

	public Product() {}
	
	public Product(String sku, String name, String shortDesc, String longDesc, String imagePath) {
		this.sku = sku;
		this.name = name;
		this.shortDesc = shortDesc;
		this.longDesc = longDesc;
		this.imagePath = imagePath;
	}

	public Product(Integer id, String sku, String name, String shortDesc, String longDesc, String imagePath) {
		this.id = id;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	
	// merchant 	- this can also track the product inventory at various locations 
	// designer
	// pricing
	// shipping
	// details
	
}