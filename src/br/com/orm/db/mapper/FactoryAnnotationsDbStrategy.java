package br.com.my.db.mapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import br.com.my.db.annotations.Embedded;

public class FactoryAnnotationsDbStrategy {

	public static FactoryAnnotationsDbStrategy instance;
	
	public static final ColumnStrategy COLUMN_STRATEGY = new ColumnStrategy();
	public static final EmbeddedStrategy EMBEDDED_STRATEGY = new EmbeddedStrategy();
	
	private FactoryAnnotationsDbStrategy() {
		
	}
	
	public static FactoryAnnotationsDbStrategy getInstance() {
		if (instance == null) {
			instance = new FactoryAnnotationsDbStrategy();
		}
		return instance;
	}
	
	
	public AnnotationDbStrategy getPopulateInstanceStrategy(AttrEntinty attr) {
		Field field = attr.field;
		
		if ( has(Embedded.class, field) ) {
			return EMBEDDED_STRATEGY;
		}

		return COLUMN_STRATEGY;
		

	};
	
	private static boolean has(Class<? extends Annotation> classAnnotation, Field field) {
		if (field.getAnnotation(classAnnotation) != null) {
			return true;
		}
		return false;
	}

}
