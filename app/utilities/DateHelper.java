package utilities;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateHelper {
	public static Calendar calendar = Calendar.getInstance();
	public static SimpleDateFormat simpleDateFormatter = new SimpleDateFormat(
			"dd.MM.yy");

	public static List<String> days = Arrays.asList("Montag", "Dienstag",
			"Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag");

	public static List<String> months = Arrays.asList("Januar", "Februar",
			"März", "April", "Mai", "Juni", "Juli", "August", "September",
			"Oktober", "November", "Dezember");
	
	public static List<String> shortMonths = Arrays.asList("Jan.", "Febr",
			"März", "Apr", "Mai", "Juni", "Juli", "Aug", "Sept",
			"Okt", "Nov", "Dez");

	public static Date getCurrentDate() {
		calendar.setTime(new Date());
		return calendar.getTime();
	}

	public static String getCurrentDay() {
		return new SimpleDateFormat("dd").format(new Date());
	}

	public static String getCurrentDayName() {
		return new SimpleDateFormat("EEEE").format(new Date());
	}

	public static String getCurrentMonth() {
		return new SimpleDateFormat("MM").format(new Date());
	}

	public static String getCurrentMonthName() {
		return new SimpleDateFormat("MMMM").format(new Date());
	}

	public static String getCurrentYear() {
		return new SimpleDateFormat("yyyy").format(new Date());
	}

	public static int getDaysInMonth() {
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static int getDaysInMonth(String month) {
		return 1;
	}

	// month starts with 0 - day starts with 0 (= -1)
	@SuppressWarnings("deprecation")
	public static String getDayName(int year, int month, int day) {
		return new SimpleDateFormat("EEEE").format(new Date(year, month - 1,
				day - 1));
	}
}
