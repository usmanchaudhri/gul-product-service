package com.gul.product.service.representation;

import javax.persistence.CascadeType;
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

/**
 * represents chat with customers.
 **/
@Entity
@Table(name = "CCHAT")
@NamedQueries({
	@NamedQuery(
	        name = "com.gul.product.service.representation.CChat.findAll",
	        query = "SELECT cc FROM CChat cc"
	)
})
public class CChat {

	@Id
	@SequenceGenerator(name = "cchatseq", sequenceName = "cchat_cchat_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cchatseq")
	@Column(name = "cchat_id", nullable = false) private Long id;
	@Column(name = "unique_name", nullable = false) private String uniqueName;

	@ManyToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name="customer_id", referencedColumnName="customer_id", nullable = true)	
	private Customer customer;

	public CChat() {}
	
	public CChat(String uniqueName) {
		this.uniqueName = uniqueName;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUniqueName() {
		return uniqueName;
	}

	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
