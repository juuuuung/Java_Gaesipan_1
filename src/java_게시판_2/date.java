package java_게시판_2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class date {
	public static String main() {
		LocalDateTime now = LocalDateTime.now();

		String FormatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
		return FormatedNow;
	}
}
