package com.sherl.tmall.util;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.sherl.tmall.entity.Status;

public class StatusEnumHandler extends BaseTypeHandler<Status> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Status parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setInt(i, parameter.getId());
	}

	@Override
	public Status getNullableResult(ResultSet rs, String columnName) throws SQLException {
		if (rs.wasNull())
			return null;
		int id = rs.getInt(columnName);
		return Status.get(id);
	}

	@Override
	public Status getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		if (rs.wasNull())
			return null;
		int id = rs.getInt(columnIndex);
		return Status.get(id);
	}

	@Override
	public Status getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		if (cs.wasNull())
			return null;
		int id = cs.getInt(columnIndex);
		return Status.get(id);
	}

}
