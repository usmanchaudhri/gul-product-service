package com.gul.product.service.persistance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.inject.ConfigurationException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.ProvisionException;
import com.gul.product.service.representation.AttributeDefinition;
import com.gul.product.service.representation.AttributeValue;
import com.gul.product.service.representation.Category;
import com.gul.product.service.representation.Product;
import com.gul.product.service.representation.Shop;

public class AttributeDefinitionMappingTest {
	
	@Test
	public void test_creating_attributeDefinitions_and_attributeValues() throws SQLException, ConfigurationException, ProvisionException {
		Injector injector = Guice.createInjector(new DbModule());	// test method will have it's own EntityManager 
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);
		
		Shop shop = new Shop("Usman Chaudhri");
		Product product = new Product(); 
		product.setName("Test Women Skirt");
		product.setSku("SKU101");
		product.setShortDesc("Short Description Women Skirt");
		product.setLongDesc("Short Description Women Skirt");
		product.setQuantity(10L);
		
		Category category = new Category("1001", "Women");
		product.setCategory(category);
		product.setShop(shop);

		persistedClassDao.saveInNewTransaction(category);					// save category first since it will be needed for the product association
		persistedClassDao.saveInNewTransaction(product);

		AttributeDefinition attrDef = new AttributeDefinition();
		attrDef.setAttributeName("Sleeve");
		attrDef.setIsActive(Boolean.TRUE);	
		AttributeValue attrValue = new AttributeValue();
		attrValue.setAttrValue("Short Sleeve");
		attrValue.setIsActive(Boolean.TRUE);
		attrValue.setImagePath("/2015/Images");
		attrValue.setAttributeDefinition(attrDef);
		
		List<AttributeValue> attrValues = new ArrayList<AttributeValue>();
		attrValues.add(attrValue);

		attrDef.setAttributeValues(attrValues);
		attrDef.setProduct(product);
		
		Set<AttributeDefinition> attrDefs = new HashSet<AttributeDefinition>();
		attrDefs.add(attrDef);
		product.setAttributeDefinitions(attrDefs);
		
		persistedClassDao.saveInNewTransaction(product);
		
		AttributeDefinition persistedAttributeDefinition = persistedClassDao.getEntityManager().find(AttributeDefinition.class, attrDef.getId());
		Assert.assertNotNull(persistedAttributeDefinition.getId());
		
		List<AttributeValue> persistedAttributeValues = persistedAttributeDefinition.getAttributeValues();
		for(AttributeValue persistedAttributeValue : persistedAttributeValues) {
			Assert.assertNotNull(persistedAttributeValue.getId());
		}
	}
	
	@Test
	public void test_customize_wedding_dress() throws SQLException, ConfigurationException, ProvisionException {
		Injector injector = Guice.createInjector(new DbModule());	// test method will have it's own EntityManager 
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);

		Shop shop = new Shop("Gulgs");
		Product product = new Product(); 
		product.setName("Wedding Dress");
		product.setSku("SKU_WEDDING_DRESS_101");
		product.setShortDesc("Fusion wedding dress");
		product.setLongDesc("Embroided fusion wedding dress");
		product.setQuantity(10L);
		
		Category category = new Category("1001", "Women");
		product.setCategory(category);
		product.setShop(shop);

		persistedClassDao.saveInNewTransaction(category);					// save category first since it will be needed for the product association
		persistedClassDao.saveInNewTransaction(product);

		// custom sleeve styles
		AttributeDefinition attributeSleeve = new AttributeDefinition();
		attributeSleeve.setAttributeName("Sleeves");
		attributeSleeve.setIsActive(Boolean.TRUE);
		attributeSleeve.setProduct(product);
		AttributeValue halfSleeve = new AttributeValue();
		halfSleeve.setAttrValue("Half sleeve");
		halfSleeve.setIsActive(Boolean.TRUE);
		halfSleeve.setImagePath("/2015/wedding-dress/83736363/custom/");
		halfSleeve.setAttributeDefinition(attributeSleeve);
		AttributeValue dolmanSleeve = new AttributeValue();
		dolmanSleeve.setAttrValue("Dolman sleeve");
		dolmanSleeve.setIsActive(Boolean.TRUE);
		dolmanSleeve.setImagePath("/2015/wedding-dress/83736363/custom/");
		dolmanSleeve.setAttributeDefinition(attributeSleeve);
		AttributeValue raglanSleeve = new AttributeValue();
		raglanSleeve.setAttrValue("Raglan sleeve");
		raglanSleeve.setIsActive(Boolean.TRUE);
		raglanSleeve.setImagePath("/2015/wedding-dress/83736363/custom/");
		raglanSleeve.setAttributeDefinition(attributeSleeve);
		List<AttributeValue> attrValuesSleeve = new ArrayList<AttributeValue>();
		attrValuesSleeve.add(halfSleeve);
		attrValuesSleeve.add(dolmanSleeve);
		attrValuesSleeve.add(raglanSleeve);
		attributeSleeve.setAttributeValues(attrValuesSleeve);
		
		// custom neck styles
		AttributeDefinition attributeNeck = new AttributeDefinition();
		attributeNeck.setAttributeName("Neck");
		attributeNeck.setIsActive(Boolean.TRUE);
		attributeNeck.setProduct(product);
		AttributeValue sweetHeart = new AttributeValue();
		sweetHeart.setAttrValue("Sweetheart");
		sweetHeart.setIsActive(Boolean.TRUE);
		sweetHeart.setImagePath("/2015/wedding-dress/83736363/custom/");
		sweetHeart.setAttributeDefinition(attributeNeck);
		AttributeValue straightAcross = new AttributeValue();
		straightAcross.setAttrValue("StraightAcross");
		straightAcross.setIsActive(Boolean.TRUE);
		straightAcross.setImagePath("/2015/wedding-dress/83736363/custom/");
		straightAcross.setAttributeDefinition(attributeNeck);
		AttributeValue vNeck = new AttributeValue();
		vNeck.setAttrValue("V Neck");
		vNeck.setIsActive(Boolean.TRUE);
		vNeck.setImagePath("/2015/wedding-dress/83736363/custom/");
		vNeck.setAttributeDefinition(attributeNeck);
		List<AttributeValue> attrValuesNeck = new ArrayList<AttributeValue>();
		attrValuesNeck.add(halfSleeve);
		attrValuesNeck.add(dolmanSleeve);
		attrValuesNeck.add(raglanSleeve);
		attributeNeck.setAttributeValues(attrValuesNeck);
		
		Set<AttributeDefinition> attributeDefinitions = new HashSet<AttributeDefinition>();
		attributeDefinitions.add(attributeSleeve);
		attributeDefinitions.add(attributeNeck);
		
		attributeSleeve.setProduct(product);
		attributeNeck.setProduct(product);
		product.setAttributeDefinitions(attributeDefinitions);
		
		persistedClassDao.saveInNewTransaction(product);
		
		AttributeDefinition persistedAttributeDefinitionSleeve = persistedClassDao.getEntityManager().find(AttributeDefinition.class, attributeSleeve.getId());
		Assert.assertNotNull(persistedAttributeDefinitionSleeve.getId());
		List<AttributeValue> persistedSleeveAttrValues = persistedAttributeDefinitionSleeve.getAttributeValues();
		for(AttributeValue persistedSleeveAttrValue : persistedSleeveAttrValues) {
			Assert.assertNotNull(persistedSleeveAttrValue.getId());
		}

		AttributeDefinition persistedAttributeDefinitionNeck = persistedClassDao.getEntityManager().find(AttributeDefinition.class, attributeNeck.getId());
		Assert.assertNotNull(persistedAttributeDefinitionNeck.getId());
		List<AttributeValue> persistedNeckAttrValues = persistedAttributeDefinitionNeck.getAttributeValues();
		for(AttributeValue persistedNeckAttrValue : persistedNeckAttrValues) {
			Assert.assertNotNull(persistedNeckAttrValue.getId());
		}
		
	}	
	
	
}
