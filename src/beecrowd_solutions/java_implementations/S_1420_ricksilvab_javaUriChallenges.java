package ad_hoc;

import java.util.Calendar;
import java.util.Scanner;

//https://www.urionlinejudge.com.br/judge/en/problems/view/1420
public class CountingSeconds1420 {
	static int[] baseDateTime;
	static int seconds;
	static int YEARSECONDS = 31536000;// ONE YEAR WITH 365 DAYS IN SECONDS
	static int DAYSECONDS = 86400;// ONE DAY IN SECONDS
	static int HOURSECONDS = 3600;// ONE HOUR IN SECONDS
	static int MINUTESECONDS = 60; // ONE MINUTE IN SECONDS

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		int previsao = 1;
		baseDateTime = new int[7];
		while (!line.equals("FIM")) {
			System.out.println("Previsao #" + previsao);
			for (int i = 0; i < 5; i++) {
				convertToBaseDate(line);
				System.out.println(convertSegToDate(Integer.parseInt(sc.nextLine())));
			}
			line = sc.nextLine();
			previsao++;
		}
	}

	private static void convertToBaseDate(String line) {
		baseDateTime[0] = convertToDayNumber(line.substring(0, 3));// Day of week
		baseDateTime[1] = Integer.parseInt(line.substring(4, 6)); // Day of month
		baseDateTime[2] = convertToMonthNumber(line.substring(6, 9));// Month
		baseDateTime[3] = Integer.parseInt(line.substring(9, 13));// Year
		baseDateTime[4] = Integer.parseInt(line.substring(14, 16));// Hour
		baseDateTime[5] = Integer.parseInt(line.substring(17, 19));// Minute
		baseDateTime[6] = Integer.parseInt(line.substring(20, 22));// Seconds
	}

	private static int convertToMonthNumber(String month) {
		switch (month) {
		case "JAN":
			return 1;
		case "FEV":
			return 2;
		case "MAR":
			return 3;
		case "ABR":
			return 4;
		case "MAI":
			return 5;
		case "JUN":
			return 6;
		case "JUL":
			return 7;
		case "AGO":
			return 8;
		case "SET":
			return 9;
		case "OUT":
			return 10;
		case "NOV":
			return 11;
		case "DEZ":
			return 12;
		default:
			return -1;
		}
	}

	private static int convertToDayNumber(String day) {
		switch (day) {
		case "DOM":
			return 1;
		case "SEG":
			return 2;
		case "TER":
			return 3;
		case "QUA":
			return 4;
		case "QUI":
			return 5;
		case "SEX":
			return 6;
		case "SAB":
			return 7;
		default:
			return -1;
		}
	}

	private static String convertToDayString(int day) {
		switch (day) {
		case 0:
			return "DOM";
		case 1:
			return "SEG";
		case 2:
			return "TER";
		case 3:
			return "QUA";
		case 4:
			return "QUI";
		case 5:
			return "SEX";
		case 6:
			return "SAB";
		default:
			return null;
		}
	}

	private static String convertToMonthString(int month) {
		switch (month) {
		case 1:
			return "JAN";
		case 2:
			return "FEV";
		case 3:
			return "MAR";
		case 4:
			return "ABR";
		case 5:
			return "MAI";
		case 6:
			return "JUN";
		case 7:
			return "JUL";
		case 8:
			return "AGO";
		case 9:
			return "SET";
		case 10:
			return "OUT";
		case 11:
			return "NOV";
		case 12:
			return "DEZ";
		default:
			return null;
		}
	}

	private static boolean isLeapYear(int year) {
		return ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)));
	}

	private static int monthDays(int month, int year) {
		switch (month) {
		case 1:
			return 31;
		case 2:
			return isLeapYear(year) ? 29 : 28;
		case 3:
			return 31;
		case 4:
			return 30;
		case 5:
			return 31;
		case 6:
			return 30;
		case 7:
			return 31;
		case 8:
			return 31;
		case 9:
			return 30;
		case 10:
			return 31;
		case 11:
			return 30;
		case 12:
			return 31;
		default:
			return -1;
		}
	}

	private static String convertSegToDate(int parseInt) {
		seconds = parseInt;
		int year = getYear();
		int month = getMonth();
		String baseDate = getDay() + "" + convertToMonthString(month) + "" + year + ":";
		String weekDay = getDayOfWeek(baseDateTime[1], month, year) + ",";
		String hour = getHour() + ":" + getMinute() + ":" + getSecond();
		return weekDay + baseDate + hour;
	}

	private static int getYear() {
		int baseYear = baseDateTime[3];
		int baseMonth = getMonth();
		int secondsInYear = isLeapYear(baseYear) ? (YEARSECONDS + DAYSECONDS) : YEARSECONDS;
		while (seconds / secondsInYear > 0) {
			seconds -= secondsInYear;
			baseYear++;
			secondsInYear = isLeapYear(baseYear) ? (YEARSECONDS + DAYSECONDS) : YEARSECONDS;
		}

		int days = seconds / DAYSECONDS + baseDateTime[1];
		seconds %= DAYSECONDS;

		int hour = seconds / HOURSECONDS + baseDateTime[4];
		seconds %= HOURSECONDS;

		int minute = seconds / MINUTESECONDS + baseDateTime[5];
		seconds %= MINUTESECONDS;
		seconds += baseDateTime[6];

		if (seconds >= 60) {
			seconds %= 60;
			minute++;
		}
		if (minute >= 60) {
			minute %= 60;
			hour++;
		}
		if (hour >= 24) {
			hour %= 24;
			days++;
		}

		int actualLimitMonthDays = monthDays(baseMonth, baseYear);
		while (days > actualLimitMonthDays) {
			days = days - actualLimitMonthDays;
			baseMonth++;
			if (baseMonth > 12) {
				baseMonth = 1;
				baseYear++;
			}
			actualLimitMonthDays = monthDays(baseMonth, baseYear);
		}

		baseDateTime[1] = days;
		baseDateTime[2] = baseMonth;
		baseDateTime[3] = baseYear;
		baseDateTime[4] = hour;
		baseDateTime[5] = minute;
		baseDateTime[6] = seconds;
		return baseYear;
	}

	private static String getDayOfWeek(int day, int month, int year) {
		if(month==1)month =0;
		else month--;
		Calendar c = Calendar.getInstance();		
		c.set(year, month, day);
		return convertToDayString(c.get(Calendar.DAY_OF_WEEK)-1);
	}

	private static int getMonth() {
		return baseDateTime[2];
	}

	private static String getDay() {
		return addZero(baseDateTime[1]);
	}

	private static String getHour() {
		return addZero(baseDateTime[4]);
	}

	private static String getMinute() {
		return addZero(baseDateTime[5]);
	}

	private static String getSecond() {
		return addZero(baseDateTime[6]);
	}

	private static String addZero(int num) {
		if (num / 10 < 1)
			return 0 + "" + num;
		return num + "";
	}
}
