package com.gul.product.service.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import com.gul.product.service.representation.Category;

public class CategoryMapper implements ResultSetMapper<Category> {

	@Override
	public Category map(int arg0, ResultSet rs, StatementContext statementContext)
			throws SQLException {
		return new Category(rs.getLong("id"), rs.getString("code"),
				rs.getString("name"), rs.getLong("parent_id"));
	}

}
