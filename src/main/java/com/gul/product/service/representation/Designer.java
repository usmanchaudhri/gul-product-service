package com.gul.product.service.representation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * holds info for individual designer
 **/
@Entity
@Table(name = "DESIGNER")
public class Designer {
	
	@Id
	@SequenceGenerator(name = "designerseq", sequenceName = "designer_designer_id_seq", initialValue = 9999, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "designerseq")
	@Column(name = "designer_id", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "designer_name", nullable = false) private String name;
	@Column(name = "image_path", nullable = true) private String imagePath;
	
	@ManyToOne
	@JoinColumn(name="shop_id", referencedColumnName ="shop_id", nullable=false)
	private Shop shop;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
	

}
