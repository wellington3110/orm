package br.com.my.db.mapper;

import java.sql.ResultSet;

import br.com.my.db.helpers.ReflectionHelper;
import br.com.my.db.helpers.ResultSetHelper;

public class ColumnStrategy implements AnnotationDbStrategy {

	@Override
	public void setAttrInInstance(AttrEntinty attr, Object instance, ResultSet result) {
		Object value = ResultSetHelper.getValueFromResultSet(attr, result);
		ReflectionHelper.set(instance, attr.field, value);
	}
	
}
