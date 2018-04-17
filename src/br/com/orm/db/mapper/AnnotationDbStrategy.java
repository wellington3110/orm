package br.com.my.db.mapper;

import java.sql.ResultSet;

public interface AnnotationDbStrategy {
	
	public void setAttrInInstance(AttrEntinty attr, Object instance, ResultSet result);
}
