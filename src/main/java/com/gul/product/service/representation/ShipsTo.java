package com.gul.product.service.representation;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * represents where shipping service is provided world wide.
 * ships from USA to USA, Pakistan, Dubai, London etc.
 **/
@Entity
@Table(name = "SHIPPING")
public class ShipsTo {
	
	@Id 
    @SequenceGenerator(name = "shippingSeq", sequenceName="shipping_shipping_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shippingSeq")
	private Long id;

	@Column(name="country_name", nullable=false) private String countryName;
	@Column(name="shipping_processing_time", nullable=true) private Long processingDays;		// approximate number of days for shipping
	@Column(name="shipping_cost", nullable=true) private Double shippingCost;

	@ManyToOne
	private ShipsTo shippingFrom;
	
	@OneToMany(mappedBy="shippingFrom", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ShipsTo> shippingTo;

	public ShipsTo() {}

	public ShipsTo(String countryName) {
		this.countryName = countryName;
	}
	
	public ShipsTo(String countryName, Long processingDays, Double shippingCost) {
		this.countryName = countryName;
		this.processingDays = processingDays;
		this.shippingCost = shippingCost;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Long getProcessingDays() {
		return processingDays;
	}

	public void setProcessingDays(Long processingDays) {
		this.processingDays = processingDays;
	}

	public Double getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(Double shippingCost) {
		this.shippingCost = shippingCost;
	}

	// prevents the JsonMapping infinite recursion exception
	@JsonIgnore
	public ShipsTo getShippingFrom() {
		return shippingFrom;
	}

	public void setShippingFrom(ShipsTo shippingFrom) {
		this.shippingFrom = shippingFrom;
	}

	public Collection<ShipsTo> getShippingTo() {
		return shippingTo;
	}

	public void setShippingTo(Set<ShipsTo> shippingTo) {
		this.shippingTo = shippingTo;
	}
		
	
}
