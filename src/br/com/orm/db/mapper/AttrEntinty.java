package br.com.my.db.mapper;

import java.lang.reflect.Field;
import java.util.function.Function;

import br.com.my.db.annotations.Column;

public class AttrEntinty {
	
	public final Field field;
	public final String nameDb;
	public final Class<?> fieldType;
	private final Function<Object, Object> mapValue;


	public AttrEntinty(String nameDb, Field field) {
		this.field = field;
		this.nameDb = nameDb;
		
		Column column = field.getAnnotation(Column.class);
		
		if (column == null) {
			
			fieldType = field.getType();
			mapValue = value -> value;
		} else {
			
			fieldType = column.type().mapType(field.getType());
			mapValue = value -> column.mapValue().map(value);
			
		}
		
	}

	public Object mapValue(Object value) {
		return mapValue.apply(value);
	}
	
	
}
