package com.gul.product.service.audit;

import java.util.Date;

public interface TimeStamped {

	public Date getCreatedOn();
	public void setCreatedOn(Date createdOn);
	public Date getUpdatedOn();
	public void setUpdatedOn(Date updatedOn);
}
