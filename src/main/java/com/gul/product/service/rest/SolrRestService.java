package com.gul.product.service.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.solr.common.SolrDocument;

import com.gul.product.service.representation.Product;

public class SolrRestService {
	
	private Client client;
	
	public void createDocument(Product product) {
		SolrDocument solrDocument = new SolrDocument();
		solrDocument.addField("id", product.getId());
		solrDocument.addField("productName", product.getName());
		solrDocument.addField("productDesc", product.getShortDesc());
		solrDocument.addField("productSku", product.getSku());
		solrDocument.addField("productCategory", product.getCategory());
		solrDocument.addField("productShop", product.getShop());
		solrDocument.addField("productPrice", product.getPricingProduct().getStoredValue());
		
		// store additional tags here which will expand the Solr search
	}

	public void post(SolrDocument solrDocument) {
		WebTarget target = client.
				target("http://localhost:8983").
				path("/solr").
				path("/collection").
				path("/update").
				path("/json").
				path("/docs").
				queryParam("commit", "true");
		
		Response solrResponse = target.request(MediaType.APPLICATION_JSON).post(Entity.json(solrDocument), Response.class);
		String temp = solrResponse.readEntity(String.class);
		// TODO - should either return success or failure
	}
	
	// return String 
	public String get() {
		return null;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
