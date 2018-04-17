package br.com.my.db.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import br.com.my.db.mapper.EntityMapper;
import br.com.my.db.mapper.IEntityMapper;

public class QuerySelect<T> implements IQuerySelect<T> {

	private String query = "";
	private final IEntityMapper mapper = EntityMapper.getInstance();
	private final Class<T> type;
	

	public QuerySelect(String query, Class<T> type) {
		this.query = query;
		this.type = type;
	}

	@Override
	public Optional<List<T>> getOptList(Connection connection){
		
		try(PreparedStatement prepareStatement = connection.prepareStatement(query)) {
			
			ResultSet resultSet = connection.prepareStatement(query).executeQuery();
			return Optional.ofNullable(mapper.toListAllRowsInResultSet(resultSet, type));
			
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		
	}

}
