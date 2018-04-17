package br.com.my.db.mapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import br.com.my.converter.Case;
import br.com.my.db.annotations.Column;
import br.com.my.db.helpers.ReflectionHelper;
import br.com.my.db.helpers.ResultSetHelper;
import br.com.my.functional.Monada;

public class EntityMapper implements IEntityMapper {
	
	private final FactoryAnnotationsDbStrategy factory = FactoryAnnotationsDbStrategy.getInstance();
	
	public static IEntityMapper instance = null;
	private Function<Field, AttrEntinty> mapToAttrEntinty = (Field field) -> Monada.of(field.getAnnotation(Column.class))
																					.mapIfNullOrContains(c -> field.getName(), Column::value)
																					.map(Case::camelToSnake)
																					.map(fieldDb -> new AttrEntinty(fieldDb, field))
																					.get();		

	
	private EntityMapper() {
		

	}

	public static IEntityMapper getInstance() {
		if(instance == null) {
			instance = new EntityMapper();
		}
		return instance;
	}

	@Override
	public <T> List<T> toListAllRowsInResultSet(ResultSet resultSet, Class<T> tableType) {
		List<AttrEntinty> attrs = getAttrsEntity(tableType);
		return ResultSetHelper.map(resultSet, r -> mapObj(tableType, r, attrs));
		
	}
	
	@Override
	public <T> T mapActualRowInResultSet(ResultSet resultSet, Class<T> embeddedType) {
		List<AttrEntinty> attrs = getAttrsEntity(embeddedType);
		return mapObj(embeddedType, resultSet, attrs);
	}

	public <T> T mapObj(Class<T> typeTable, ResultSet result, List<AttrEntinty> attrs) {
		T obj = ReflectionHelper.newInstance(typeTable);
		attrs.forEach(attr -> factory.getAnnotationDBStrategy(attr).setAttrInInstance(attr, obj, result));
		return obj;
	}

	public <T> List<AttrEntinty> getAttrsEntity(Class<T> type) {
		return Arrays.asList(type.getDeclaredFields())
						.stream()
						.map(mapToAttrEntinty)
						.collect(Collectors.toList());
	}
}
