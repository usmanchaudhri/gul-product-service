package com.gul.product.service.representation;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

// holds the category for clothes
// recursive relationship with the sub-category as child categories
@Entity
@Table(name = "CATEGORY")
@NamedQueries({
    @NamedQuery(
            name = "com.gul.product.service.representation.Category.findAll",
            query = "SELECT c FROM Category c"
    )
})
public class Category {
	
	@Id 
    @SequenceGenerator(name = "categorySeq", sequenceName="category_category_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorySeq")
	@Column(name = "category_id", nullable = false) private Long id;
	@Column(name = "code", nullable = true) private String code;
	@Column(name = "name", nullable = false) private String name;

	@OneToMany(mappedBy="category", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Product> products = new HashSet<Product>();
	
	@ManyToOne
	private Category parentCategory;
	
	@OneToMany(mappedBy="parentCategory", fetch = FetchType.EAGER)
	private Collection<Category> subCategories;
	
	public Category() {}

	public Category(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public Category(Long id, String code, String name) {
		this.id = id;
		this.code = code;
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		
		Category category = (Category) o;
		if(id != null ? !id.equals(category.id) : category.id != null) return false;
		if(code != null ? !code.equals(category.code) : category.code != null) return false;
		if(name != null ? !name.equals(category.name) : category.name != null) return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (code != null ? code.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public Category getParentCategory() {
		return parentCategory;
	}
	
	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	public Set<Product> getProducts() {
		return new HashSet<Product>(products);
	}

	public Collection<Category> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(Collection<Category> subCategories) {
		this.subCategories = subCategories;
	}

	public void addProducts(Product product) {
		if(products.contains(product)) {
			return;
		}
		products.add(product);
		product.setCategory(this);
	}
	
	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
