package com.gul.product.service.persistance;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.UUID;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

public class UuidType implements UserType {

	@Override
	public int[] sqlTypes() {
		return new int[] { Types.OTHER };
	}

	@Override
	public Class returnedClass() {
		return UUID.class;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
	      if (x == null && y == null) {
	          return true;
	       } else if (x == null || y == null) {
	          return false;
	       }
	       UUID u1 = (UUID) x;
	       UUID u2 = (UUID) y;
	       return u1.equals(u2);	
	 }

	@Override
	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names,
			SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		Object val = rs.getObject(names[0]);
		if (val instanceof UUID || val == null) {
			return val;
		} else {
			throw new MappingException("Unexpected Object type "
					+ val.getClass() + " for UUID");
		}
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index,
			SessionImplementor session) throws HibernateException, SQLException {
	      st.setObject(index, value);
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public Object assemble(Serializable cached, Object owner)
			throws HibernateException {
		return cached;
	}

	@Override
	public Object replace(Object original, Object target, Object owner)
			throws HibernateException {
		return original;
	}

}
