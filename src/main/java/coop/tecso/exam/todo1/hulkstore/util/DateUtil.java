package coop.tecso.exam.todo1.hulkstore.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	private static final String FULL_DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
	
	private DateUtil() {
	}
	
	public static String convert(LocalDateTime date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FULL_DATE_FORMAT);
		return date.format(formatter);
	}
	
}
