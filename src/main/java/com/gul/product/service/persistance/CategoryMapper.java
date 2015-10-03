package com.gul.product.service.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.gul.product.service.representation.Category;
import com.gul.product.service.representation.Product;

public class CategoryMapper implements ResultSetMapper<Category> {

	@Override
	public Category map(int arg0, ResultSet rs, StatementContext statementContext)
			throws SQLException {
		Category category = new Category(rs.getLong("id"), rs.getString("code"),
				rs.getString("name"), rs.getLong("parent_id"));
		List<Product> products = (List<Product>) rs.getObject("products");
		category.setProducts(products);
		return category;
	}

}
