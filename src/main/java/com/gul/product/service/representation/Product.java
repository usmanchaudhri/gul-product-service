package com.gul.product.service.representation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.SequenceGenerator;
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

	@Id 
    @SequenceGenerator(name = "productSeq", sequenceName="product_product_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productSeq")
    @Column(name = "product_id", nullable = false) private Long id;
    @Column(name = "sku", nullable = false) private String sku;	
    @Column(name = "name", nullable = false) private String name;
    @Column(name = "short_desc", nullable = false) private String shortDesc;
    @Column(name = "long_desc", nullable = true) private String longDesc;
    @Column(name = "image_path", nullable = false) private String imagePath;

    // quantity and price could be separate REST services.
    @Column(name = "quantity", nullable = false) private Long quantity;
    @Column(name = "price", nullable = false) private Long price;						
    
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;

	public Product() {}
	
	public Product(Long id, String sku, String name, String shortDesc, String longDesc, String imagePath) {
		this.id = id;
		this.sku = sku;
		this.name = name;
		this.shortDesc = shortDesc;
		this.longDesc = longDesc;
		this.imagePath = imagePath;
	}

//	public Product(String sku, String name, String shortDesc, String longDesc, String imagePath, Long quantity, Long price) {
//		this.sku = sku;
//		this.name = name;
//		this.shortDesc = shortDesc;
//		this.longDesc = longDesc;
//		this.imagePath = imagePath;
//		this.quantity = quantity;
//		this.price = price;
//	}
	
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

}
