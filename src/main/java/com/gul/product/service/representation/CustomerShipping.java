package com.gul.product.service.representation;

import java.util.Date;

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

import org.hibernate.validator.constraints.Length;

import com.gul.product.service.audit.TimeStamped;

@Entity
@Table(name = "CUSTOMER_SHIPPING")
public class CustomerShipping implements TimeStamped {

	@Id
	@SequenceGenerator(name = "customershippingseq", sequenceName = "customershipping_customershipping_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customershippingseq")
	@Column(name = "customer_shipping_id", nullable = false)
	private Long id;
	
	@Column(name = "first_name", nullable = false) private String firstName;
	@Column(name = "last_name", nullable = false) private String lastName;
	@Column(name = "address", nullable = false) private String address;
	@Column(name = "city", nullable = false) private String city;
	@Column(name = "state", nullable = false) private String state;
	@Column(name = "zipcode", nullable = false) private String zipcode;
	@Column(name = "country", nullable = false) private String country;
	@Length(min = 12, max = 12) @Column(name = "mobile_number", nullable = false) private String mobileNumber;
	
	@Column(name = "created_on", nullable = true) private Date createdOn;
	@Column(name = "updated_on", nullable = true) private Date updatedOn;
	
	@ManyToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name="customer_id", referencedColumnName="customer_id", nullable = true)
	private Customer customer;
	
	public CustomerShipping() {}
	
	public CustomerShipping(String address, String city, String state, String zipcode, String country) {
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode; 
		this.country = country;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getZipcode() {
		return zipcode;
	}
	
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
