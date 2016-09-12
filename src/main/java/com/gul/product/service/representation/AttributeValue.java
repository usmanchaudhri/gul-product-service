package com.gul.product.service.representation;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gul.product.service.audit.TimeStamped;

/**
 * hold the value for a particular customization offered
 **/
@Entity
@Table(name = "ATTRIBUTE_VALUE")
@NamedQueries({
    @NamedQuery(
            name = "com.gul.product.service.representation.AttributeValue.findAll",
            query = "SELECT AV FROM AttributeValue AV"
    )
})
public class AttributeValue implements TimeStamped {

	@Id
	@SequenceGenerator(name = "attributevalueseq", sequenceName = "attributevalue_attributevalue_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attributevalueseq")
	@Column(name = "attribute_value_id", nullable = false, unique = true) private Long id;
	
	@Column(name = "attr_value", nullable = false, unique = true) private String attrValue;
	@Column(name = "image_path", nullable = false) private String imagePath;	
	@Column(name = "active_flag", nullable = false) private Boolean isActive;
	
	@ManyToOne
	@JoinColumn(name="attribute_definition_id", referencedColumnName ="attribute_definition_id", nullable=false)
	private AttributeDefinition attributeDefinition;
	
	@Column(name = "created_on", nullable = true) private Date createdOn;
	@Column(name = "updated_on", nullable = true) private Date updatedOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public AttributeDefinition getAttributeDefinition() {
		return attributeDefinition;
	}

	public void setAttributeDefinition(AttributeDefinition attributeDefinition) {
		this.attributeDefinition = attributeDefinition;
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
