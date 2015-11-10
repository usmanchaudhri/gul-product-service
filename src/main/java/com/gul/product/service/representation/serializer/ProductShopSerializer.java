package com.gul.product.service.representation.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.gul.product.service.representation.Product;

/**
 * NOTE: not currently used. this is a custom serializer incase of infinite
 * cyclic expcetion when creating entity relation.
 **/
public class ProductShopSerializer extends JsonSerializer<Product> {

	@Override
	public void serialize(Product value, JsonGenerator jgen,
			SerializerProvider serializers) throws IOException,
			JsonProcessingException {
		jgen.writeStartObject();
		jgen.writeNumberField("id", value.getId());
		jgen.writeStringField("name", value.getName());
		jgen.writeStringField("sku", value.getSku());
		jgen.writeStringField("shortDesc", value.getShortDesc());
		jgen.writeStringField("longDesc", value.getLongDesc());
		jgen.writeStringField("imagePath", value.getImagePath());
		jgen.writeNumberField("quantity", value.getQuantity());

		jgen.writeNumberField("storedValue", value.getPricingProduct().getStoredValue());
		jgen.writeStringField("shop", value.getShop().getName());
		jgen.writeStringField("category", value.getCategory().getName());
		jgen.writeEndObject();
	}

}
