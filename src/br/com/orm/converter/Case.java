package br.com.my.converter;

public class Case {
	
	public static String camelToSnake(String value) {
		return value.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
	}
}
