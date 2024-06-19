package io.github.gleysongomes.ensino.util.v1;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public final class DateUtil {

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static String formatDate(ZonedDateTime date) {
		return date.format(DATE_FORMATTER);
	}
}
