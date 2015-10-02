package com.gul.product.service.representation;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

// reflects the units on hand
public class ProductAvailability {

//	@Id 
//    @SequenceGenerator(name = "productAvailabilitySeq", sequenceName="product_product_id_seq", allocationSize=1)
//    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "productAvailabilitySeq")
//	@Column(name = "product_availability_id", nullable = false) private Long id;
//	@Column(name = "product_id", nullable = false) private Long productId;
//	@Column(name = "on_hand_units", nullable = false) private Long quantity;
	
	// Audit fields - for tracking activity
	private Date createdDateTime;
	private Date lastUpdatedDateTime;
	private String createdBy;
	private String updatedBy;

}
