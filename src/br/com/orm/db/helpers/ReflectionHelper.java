package br.com.my.db.helpers;

import java.lang.reflect.Field;

public class ReflectionHelper {

	public static <T> void set(T obj, Field field, Object value) {
		try {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	public static <T> T newInstance(Class<? extends T> clazz) {
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}

	public static Field getDeclaredField(Class<?> clazz, String string) {
		try {
			return clazz.getDeclaredField(string);
		} catch (NoSuchFieldException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}

}
