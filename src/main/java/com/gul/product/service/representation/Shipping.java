package com.gul.product.service.representation;

import java.util.Collection;
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

/**
 * Shipping services - where does gul provides shipping services to.
 * this will be part of when the product will be created.
 * ships from USA to USA, Pakistan, Dubai, London etc.
 **/
@Entity
@Table(name = "SHIPPING")
public class Shipping {
	
	@Id 
    @SequenceGenerator(name = "shippingSeq", sequenceName="shipping_shipping_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shippingSeq")
	private Long id;

	@Column(name="country_name", nullable=false) private String countryName;
	@Column(name="shipping_processing_time", nullable=true) private Long processingDays;		// approximate number of days for shipping
	@Column(name="shipping_cost", nullable=true) private Double shippingCost;

	@ManyToOne
	private Shipping shippingFrom;
	
	@OneToMany(mappedBy="shippingFrom", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Shipping> shippingTo;

	public Shipping() {}

	public Shipping(String countryName) {
		this.countryName = countryName;
	}
	
	public Shipping(String countryName, Long processingDays, Double shippingCost) {
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

	public Shipping getShippingFrom() {
		return shippingFrom;
	}

	public void setShippingFrom(Shipping shippingFrom) {
		this.shippingFrom = shippingFrom;
	}

	public Collection<Shipping> getShippingTo() {
		return shippingTo;
	}

	public void setShippingTo(Collection<Shipping> shippingTo) {
		this.shippingTo = shippingTo;
	}
		
	
}
