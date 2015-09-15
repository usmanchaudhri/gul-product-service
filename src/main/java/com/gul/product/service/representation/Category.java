package com.gul.product.service.representation;

import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class Category {
	
//	@Id 
//    @SequenceGenerator(name = "categorySeq", sequenceName="category_id_seq", allocationSize=1)
//    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "categorySeq")
	private String id;
	private String name;
	private List<Category> child;
	private Product product;	// will always exits at the last node
	
}
