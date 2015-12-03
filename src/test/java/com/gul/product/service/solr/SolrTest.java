package com.gul.product.service.solr;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrDocument;
import org.junit.Ignore;
import org.junit.Test;
import com.gul.product.service.representation.SolrDoc;

public class SolrTest {

	private Client client = ClientBuilder.newClient();

	@Ignore
	@Test
	public void test_get_data_from_solr() {
		SolrQuery query = new SolrQuery();
		query.set("productName", "Tunic Top");
		query.add("wt", "json");
//		query.add("indent", "true");		// optional 
		query.setStart(0);
		query.setRows(10);
		
		SolrDoc solrDoc = new SolrDoc();
		solrDoc.setId(1L);
		solrDoc.setProductName("Test tunic top");
		solrDoc.setProductSku("SKU_TUNIC_101");
		solrDoc.setProductShop("gulgs");
		
		WebTarget target = client.
				target("http://localhost:8983").
				path("/solr").
				path("/collection").
				path("/select").
				queryParam("q", query.toString());
		 Response solrResponse = target.request(MediaType.APPLICATION_JSON).get(Response.class);
		 String temp = solrResponse.readEntity(String.class);
		 System.out.println("Solr Response:" + solrResponse.toString());
		
//		SolrDoc solrResponse = target.request(MediaType.APPLICATION_JSON).post(Entity.json(solrDoc), SolrDoc.class);
		UriBuilder uriBuilder = target.getUriBuilder();
		System.out.println("created rest");
	}
	
	/**
	 * {
		"id":"207",
		"productName":"leather purse",
		"productDesc":"handmade leather purse",
		"productPrice":"20.00",
		"productSku":"SKU_BAG_101",
		"productCategory":"bags",
		"productShop":"gulgs"
	   }
	   http://localhost:8983/solr/collection/update/json/docs?commit=true	
	 * 
	 **/
	@Ignore
	@Test
	public void test_post_data_to_solr() {
		SolrDocument solrDocument = new SolrDocument();
		solrDocument.addField("id", "456");
		solrDocument.addField("productName", "Handbag tote");
		solrDocument.addField("productDesc", "Handmade handbag tote");
		solrDocument.addField("productSku", "SKU_HANDMADE_HANDBAG_101");
		solrDocument.addField("productCategory", "handbags");
		solrDocument.addField("productShop", "gulgs test");
		solrDocument.addField("productPrice", "30.99");
		
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
		System.out.println("Solr Response:" + solrResponse.toString());
	}
	
	
	
	
}
