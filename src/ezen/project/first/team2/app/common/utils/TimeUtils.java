////////////////////////////////////////////////////////////////////////////////
//
// [SGLEE:20231120MON_025300] Created
//
////////////////////////////////////////////////////////////////////////////////

package ezen.project.first.team2.app.common.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class TimeUtils {
	private static LocalTime mLastTime = null;
	private static long mElapsedMillis = 0;
	private static LocalTime mElapsedTime;

	// return: "yyyy.mm.dd.ddd"
	public static String currDateStr() {
		final String[] WeekNames = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };

		LocalDate d = LocalDate.now();
		String s = String.format("%04d.%02d.%02d.%s",
				d.getYear(), d.getMonthValue(), d.getDayOfMonth(),
				WeekNames[d.getDayOfWeek().getValue() - 1]);

		return s;
	}

	// return: "hh:mm:ss" or "hh:mm:ss.mil"
	public static String currTimeStr(boolean millis) {
		LocalTime t = LocalTime.now();
		String _millis = millis ? String.format(".%03d", t.getNano() / 1000000) : "";
		String s = String.format("%02d:%02d:%02d%s",
				t.getHour(), t.getMinute(), t.getSecond(), _millis);

		// if (squarBrackets)
		// s = "[" + s + "]";

		// System.err.println(ChronoUnit.MILLIS.between(t, LocalTime.now()));

		if (mLastTime != null)
			mElapsedMillis = ChronoUnit.MILLIS.between(mLastTime, LocalTime.now());

		mLastTime = t;

		return s;
	}

	// return: "hh:mm:ss.mil"
	public static String currTimeStr() {
		return currTimeStr(true);
	}

	// return: "yyyy.mm.dd.ddd hh:mm:ss"
	public static String currDateTimeStr() {
		return currDateStr() + " " + currTimeStr(false);
	}

	public static long elapsedMillis() {
		return mElapsedMillis;
	}

	// return: "%03d"
	public static String elapsedMillisStr() {
		return String.format("%03d", elapsedMillis());
	}

	public static void startElapsedTime() {
		mElapsedTime = LocalTime.now();
	}

	public static long getElapsedTime() {
		long et = ChronoUnit.MILLIS.between(mElapsedTime, LocalTime.now());
		return et;
	}

	public static String getElapsedTimeStr() {
		long millis = getElapsedTime();
		long hours = TimeUnit.MILLISECONDS.toHours(millis);
		millis -= hours * 60 * 60 * 1000;
		long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
		millis -= minutes * 60 * 1000;
		long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
		millis -= seconds * 1000;

		String s = String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, millis);

		s = String.format("(%d.%03ds)", seconds, millis);

		return s;
	}
}