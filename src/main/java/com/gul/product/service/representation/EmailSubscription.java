package com.gul.product.service.representation;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.TypeDef;

/**
 * subscribes users email address - stores Users email and sends a notification
 * to the user.
 **/
@Entity
@Table(name = "EMAILSUBSCRIPTION")
public class EmailSubscription {

	@Id
	@SequenceGenerator(name = "emailsubscriptionseq", sequenceName = "emailsubscription_emailsubscription_id_seq", initialValue = 0, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emailsubscriptionseq")
	@Column(name = "emailsubscription_id", nullable = false, unique = true)
	private Long id;

	@Column(name = "email", nullable = true, unique = true) private String email;	
	@Column(name = "created_on", nullable = true) private Date createdOn;
	@Column(name = "updated_on", nullable = true) private Date updatedOn;
	
	public EmailSubscription() {
	}
	
	public EmailSubscription(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

}
