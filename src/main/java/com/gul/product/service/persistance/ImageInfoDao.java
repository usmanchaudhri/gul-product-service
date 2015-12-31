package com.gul.product.service.persistance;

import org.hibernate.SessionFactory;
import io.dropwizard.hibernate.AbstractDAO;
import com.gul.product.service.representation.ImageInfo;

public class ImageInfoDao extends AbstractDAO<ImageInfo> {

	public ImageInfoDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public ImageInfo create(ImageInfo imageInfo) {
		return persist(imageInfo);
	}
	
	public ImageInfo update(ImageInfo imageInfo) {
		return persist(imageInfo);
	}
	
	public ImageInfo findById(Long id) {
		return get(id);
	}


}
