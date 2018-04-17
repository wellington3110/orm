package br.com.my.db.mapper;

import java.sql.ResultSet;

import br.com.my.db.helpers.ReflectionHelper;

public class EmbeddedStrategy implements AnnotationDbStrategy {
	
	IEntityMapper mapper = EntityMapper.getInstance();

	@Override
	public void setAttrInInstance(AttrEntinty attr, Object instance, ResultSet result) {
		Object valueEmbedded = mapper.mapActualRowInResultSet(result, attr.fieldType);
		ReflectionHelper.set(instance, attr.field, valueEmbedded);
	}

}
