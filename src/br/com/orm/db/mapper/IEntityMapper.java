package br.com.my.db.mapper;

import java.sql.ResultSet;
import java.util.List;

public interface IEntityMapper {

	public <T> List<T> toListAllRowsInResultSet(ResultSet resultSet, Class<T> tableType);
	
	public <T> T mapActualRowInResultSet(ResultSet resultSet, Class<T> embeddedType);

}
