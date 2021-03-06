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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.gul.product.service.audit.TimeStamped;

/**
 * represents a customer who could also be a shop owner.
 * - A Customer is a buyer. 
 * - A Customer could be a shop owner.
 **/
@Entity
@Table(name = "CUSTOMER")
@NamedQueries({
	@NamedQuery(
	        name = "com.gul.product.service.representation.Customer.findAll",
	        query = "SELECT c FROM Customer c"
	),	
    @NamedQuery(
            name = "com.gul.product.service.representation.Customer.findCustomer",
            query = "SELECT c FROM Customer c WHERE c.username = :username and c.password = :password"
    )
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "username")
public class Customer implements TimeStamped {

	@Id
	@SequenceGenerator(name = "customerseq", sequenceName = "customer_customer_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerseq")
	@Column(name = "customer_id", nullable = false) private Long id;
	
	// START new fields
	@Column(name = "username", nullable = false, unique = true) private String username;
	@Column(name = "password", nullable = false) private String password;

//	@Column(name = "first_name", nullable = false) private String firstName;
//	@Column(name = "last_name", nullable = false) private String lastName;
//	@Column(name = "email", nullable = false) private String email;
//	@Length(min = 12, max = 12) @Column(name = "mobile_number", nullable = false) private String mobileNumber;

	@OneToMany(mappedBy="customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<CustomerShipping> customerShipping; 	// = new ArrayList<CustomerShipping>();
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="shop_id")
	private Shop shop;

	@OneToMany(mappedBy="customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Order> order;

	@OneToMany(mappedBy="customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CChat> cchat;

	@Column(name = "created_on", nullable = true) private Date createdOn;
	@Column(name = "updated_on", nullable = true) private Date updatedOn;

	public Customer() {}
	
	public Customer(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<CustomerShipping> getCustomerShipping() {
		return customerShipping;
	}

	public void setCustomerShipping(List<CustomerShipping> customerShipping) {
		this.customerShipping = customerShipping;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
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

	public List<CChat> getCchat() {
		return cchat;
	}

	public void setCchat(List<CChat> cchat) {
		this.cchat = cchat;
	}

	
}
