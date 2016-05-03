package com.gul.product.service.representation;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.gul.product.service.audit.TimeStamped;

/**
 * this is the class which will hold the customization for particular products
 **/
@Entity
@Table(name = "ATTRIBUTE_DEFINITION")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "attributeName")
public class AttributeDefinition implements TimeStamped {
	
	@Id
	@SequenceGenerator(name = "attributedefinitionseq", sequenceName = "attributedefinition_attributedefinition_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attributedefinitionseq")
	@Column(name = "attribute_definition_id", nullable = false, unique = true) public Long id;
	@Column(name = "attribute_name", nullable = false, unique = true) private String attributeName;
	@Column(name = "active_flag", nullable = false) private Boolean isActive;
	
	@OneToMany(mappedBy="attributeDefinition", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<AttributeValue> attributeValues;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="product_id", referencedColumnName="product_id", nullable=false)
	private Product product;
	
	@Column(name = "created_on", nullable = true) private Date createdOn;
	@Column(name = "updated_on", nullable = true) private Date updatedOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public List<AttributeValue> getAttributeValues() {
		return attributeValues;
	}

	public void setAttributeValues(List<AttributeValue> attributeValues) {
		this.attributeValues = attributeValues;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public Date getCreatedOn() {
		return createdOn;
	}

	@Override
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public Date getUpdatedOn() {
		return updatedOn;
	}

	@Override
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

}
