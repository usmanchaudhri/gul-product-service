package com.gul.product.service.representation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * contains info about image for a particular product. The ImageInfo object
 * represents a resource which will
 **/
@Entity
@Table(name = "IMAGE_INFO")
public class ImageInfo {
	
	@Id
	@SequenceGenerator(name = "imageinfoseq", sequenceName = "imageinfo_imageinfo_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imageinfoseq")
	@Column(name = "image_info_id", nullable = false, unique = true) private Long id;
	@Column(name = "image_path", nullable = true) private String imagePath;
	@OneToOne(mappedBy = "imageInfo") private Product product;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}
