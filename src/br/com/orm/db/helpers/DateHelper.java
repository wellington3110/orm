package br.com.my.db.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateHelper {

	public static String convertToBrazilianTime(LocalDateTime date) {
		return date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));
	}
}
