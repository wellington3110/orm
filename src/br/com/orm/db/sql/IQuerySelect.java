package br.com.my.db.sql;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface IQuerySelect<T> {

	public Optional<List<T>> getOptList(Connection connection);
	
}
