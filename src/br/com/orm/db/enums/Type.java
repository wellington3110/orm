package br.com.my.db.enums;

import java.sql.Timestamp;

public enum Type {
	TIMESTAMP {
		@Override
		public Class<?> mapType(Class<?> type) {
			return Timestamp.class;
		}
	}, 
	FROM_FIELD;

	public Class<?> mapType(Class<?> type) {
		return type;
	}

}
