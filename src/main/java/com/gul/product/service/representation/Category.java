package com.gul.product.service.representation;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

// holds the category for clothes
@Entity
@Table(name = "CATEGORY")
public class Category {
	
	@Id 
    @SequenceGenerator(name = "categorySeq", sequenceName="category_category_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "categorySeq")
	@Column(name = "category_id", nullable = false) private Long id;
	@Column(name = "code", nullable = false) private String code;
	@Column(name = "name", nullable = false) private String name;
	@Column(name = "parent_id", nullable = false) private Long parentId;
	
	@ManyToMany(mappedBy="category")
	private List<Product> products;
	
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
