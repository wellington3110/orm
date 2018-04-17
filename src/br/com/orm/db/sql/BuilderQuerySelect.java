package br.com.my.db.sql;

import java.lang.reflect.Field;

import br.com.my.converter.Case;
import br.com.my.db.annotations.Table;
import br.com.my.functional.Monada;

public class BuilderQuerySelect<T> {

	private StringBuilder query = new StringBuilder();
	private final Class<T> type;
	private String tableName;
	
	public BuilderQuerySelect(Class<T> type) {
		this.type = type;
		this.tableName = Monada.of(type.getAnnotation(Table.class))
				.mapIfNullOrContains(table -> type.getSimpleName(), Table::value)
				.map(Case::camelToSnake)
				.get();
		query.append("SELECT * FROM ").append(tableName);
	}
	
	public BuilderQuerySelect<T> orderBy(Field ... fields) {
		query.append(" ORDER BY ");
		for (Field field : fields) {
			query
				.append(tableName)
				.append(".")
				.append(Case.camelToSnake(field.getName()));
		}
		return this;
		
	}
	
	public IQuerySelect<T> build() {
		return new QuerySelect<T>(query.toString(), type);
	}

}
