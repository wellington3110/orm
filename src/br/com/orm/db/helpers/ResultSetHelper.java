package br.com.my.db.helpers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import br.com.my.db.mapper.AttrEntinty;

public class ResultSetHelper {

	public static <T> List<T> map(ResultSet result, Function<ResultSet, T> map) {
		try {
			List<T> list = new ArrayList<>();
			
			while(result.next()) {
				list.add(map.apply(result));
			}
			
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public static <T> Object getValueFromResultSet(AttrEntinty attr, ResultSet result) {
		try {
			
			Object value = result.getClass()
					.getMethod(getMethodName(attr.fieldType), String.class)
					.invoke(result, attr.nameDb);
			
			return attr.mapValue(value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	private static <T> String getMethodName(Class<T> classField) {
		return "get" + classField.getSimpleName();
	}
	
}
