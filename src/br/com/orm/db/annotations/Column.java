package br.com.my.db.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import br.com.my.db.enums.MapValue;
import br.com.my.db.enums.Type;

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Column{
	
	String value();
	MapValue mapValue() default MapValue.KEEP;
	Type type() default Type.FROM_FIELD;

}
