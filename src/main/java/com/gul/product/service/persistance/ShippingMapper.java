package com.gul.product.service.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import com.gul.product.service.representation.Shipping;

public class ShippingMapper implements ResultSetMapper<Shipping> {

	@Override
	public Shipping map(int index, ResultSet rs, StatementContext ctx)
			throws SQLException {
		Shipping shipping = new Shipping(rs.getString("countryName"),
				rs.getLong("processingDays"), rs.getDouble("shippingCost"));
		return shipping;
	}

}
