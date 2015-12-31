package com.gul.product.service.resources;

import io.dropwizard.hibernate.UnitOfWork;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.solr.common.SolrDocument;
import org.hibernate.validator.constraints.NotEmpty;

import com.codahale.metrics.annotation.Timed;
import com.gul.product.service.persistance.CategoryDao;
import com.gul.product.service.persistance.ProductDao;
import com.gul.product.service.representation.Category;
import com.gul.product.service.representation.Product;
import com.gul.product.service.representation.ProductVariation;
import com.gul.product.service.representation.SolrDoc;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Api("/product")
@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

	private ProductDao productDao;
	private CategoryDao categoryDao;
	private Client client;
	
	public ProductResource(ProductDao productDao, CategoryDao categoryDao) {
		this.productDao = productDao;
		this.categoryDao = categoryDao;
	}
	
	public ProductResource(ProductDao productDao, CategoryDao categoryDao, Client client) {
		this.productDao = productDao;
		this.categoryDao = categoryDao;
		this.client = client;
	}

	@POST
	@UnitOfWork
	@Timed
	@ApiOperation(
            value = "Adding a new product",
            notes = "When adding a new product just provide the category id, when specifying the associated category",
            response = Product.class)	
	public Response add(@Valid Product product) {
		Product p = null;
		Long catgeoryId = product.getCategory().getId();
		Category category = categoryDao.findById(catgeoryId);
		if(category != null && category.getId() != null && category.getId() > 0) {
			product.setCategory(category);
			setProductVariation(product);
			p = productDao.create(product);
			
			// send data to Solr
		} else {
			throw new WebApplicationException("cannot add product without category");
		}
		return Response.status(Response.Status.CREATED).entity(p).build();
	}
	
	@PUT
    @Path("/{productId}")
	@UnitOfWork
	@Timed
	@ApiOperation(
            value = "Update an existing product",
            notes = "Currently supports updatig for the following fields i.e. name, sku, shortDesc, longDesc, imagePath",
            response = Product.class)	
	public Response update(@PathParam("productId") Long productId, @Valid Product product) {
		Product p = null;
		Product persistedProduct = productDao.findById(productId);
		if(persistedProduct != null) {
			updateProduct(persistedProduct, product);
			p = productDao.update(persistedProduct);
		} else {
			// the product was not updated successfully
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(p).build();
		}
		
		return Response.status(Response.Status.OK).entity(p).build();
	}
	
	@GET
	@UnitOfWork
	@Path("/{id}")
    @ApiOperation("Get individual product for passed-in id")
	public Response getProduct(@PathParam("id") @NotEmpty Long productId) {
		Product product = productDao.findById(productId);
		return Response.status(Response.Status.OK).entity(product).build();
	}

	@GET
	@UnitOfWork
	@Timed
    @ApiOperation("Get list of existing products")
	public Response listProducts() {
		List<Product> products = productDao.findAll();
		return Response.status(Response.Status.OK).entity(products).build();
	}	

	// sets product in ProductVariation since it is a bi-relation.
	private void setProductVariation(Product request) {
		List<ProductVariation> variations = request.getProductVariation();
		for(ProductVariation variation : variations) {
			variation.setProduct(request);
		}
	}
	
	private void updateProduct(Product persistedProduct, Product requestProduct) {
		persistedProduct.setName(requestProduct.getName());
		persistedProduct.setSku(requestProduct.getSku());
		persistedProduct.setShortDesc(requestProduct.getShortDesc());
		persistedProduct.setLongDesc(requestProduct.getLongDesc()); 
	}

	private SolrDoc sendToSolr(Product product) {
		SolrDocument solrDocument = new SolrDocument();
		solrDocument.addField("id", "456");
		solrDocument.addField("productName", "Handbag tote");
		solrDocument.addField("productDesc", "Handmade handbag tote");
		solrDocument.addField("productSku", "SKU_HANDMADE_HANDBAG_101");
		solrDocument.addField("productCategory", "handbags");
		solrDocument.addField("productShop", "gulgs test");
		solrDocument.addField("productPrice", "30.99");

		SolrDoc solrDoc = new SolrDoc();
		solrDoc.setId(1L);
		solrDoc.setProductName("Handbag");
		solrDoc.setProductDesc("Handbag handmade");
		solrDoc.setProductSku("SKU_HANDBAG_101");
		solrDoc.setProductShop("gulgs");
		return solrDoc;
	}
	
	public Product findProduct(@PathParam("id") Long id) {
		return productDao.findById(id);
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	
}
