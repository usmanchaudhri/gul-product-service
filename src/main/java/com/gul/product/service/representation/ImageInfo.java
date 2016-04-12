package com.gul.product.service.representation;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gul.product.service.audit.TimeStamped;

/**
 * contains info about image for a particular product. The ImageInfo object
 * represents a resource which will
 **/
@Entity
@Table(name = "IMAGE_INFO")
public class ImageInfo implements TimeStamped {
	
	@Id
	@SequenceGenerator(name = "imageinfoseq", sequenceName = "imageinfo_imageinfo_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imageinfoseq")
	@Column(name = "image_info_id", nullable = false, unique = true) private Long id;
	@Column(name = "image_path", nullable = true) private String imagePath;
	@Column(name = "image_count", nullable = true) private String imageCount;

	@OneToOne(mappedBy = "imageInfo") private Product product;
	
	@Column(name = "created_on", nullable = true) private Date createdOn;
	@Column(name = "updated_on", nullable = true) private Date updatedOn;

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

	public String getImageCount() {
		return imageCount;
	}

	public void setImageCount(String imageCount) {
		this.imageCount = imageCount;
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
	
}
