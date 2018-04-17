package br.com.my.db.enums;

import java.sql.Timestamp;

public enum MapValue {
	
	KEEP {
		@Override
		public Object map(Object object) {
			return object;
		}
	},	
	LOCAL_DATE_TIME {
		@Override
		public Object map(Object obj) {
			if (obj == null) return obj;
			Timestamp date = (Timestamp) obj;
			return date.toLocalDateTime();
		}
	};
	
	public abstract Object map(Object object);
	
	
}
