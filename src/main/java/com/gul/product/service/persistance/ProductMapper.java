package com.gul.product.service.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import com.gul.product.service.representation.Product;

public class ProductMapper implements ResultSetMapper<Product> {

	@Override
	public Product map(int arg0, ResultSet rs, StatementContext statementContext)
			throws SQLException {
		
		return new Product(rs.getLong("id"), rs.getString("sku"),
				rs.getString("name"), rs.getString("shortDesc"),
				rs.getString("longDesc"), rs.getString("imagePath"));
	}

}
