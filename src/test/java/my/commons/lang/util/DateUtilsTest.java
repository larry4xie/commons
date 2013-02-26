package my.commons.lang.util;

import java.util.Date;

import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void testIgnoreTimeDate() {
		Date date = new Date();
		date = DateUtils.ignoreTime(date);
		System.out.println(date);
	}

	@Test
	public void testGetDayEnd() {
		Date date = DateUtils.getDayEnd();
		System.out.println(date);
	}

	@Test
	public void testGetWeekEnd() {
		Date date = DateUtils.getWeekEnd();
		System.out.println(date);
	}

	@Test
	public void testGetMonthEnd() {
		Date date = DateUtils.getMonthEnd();
		System.out.println(date);
	}

	@Test
	public void testIsSameDayDateDate() {
		boolean bool = DateUtils.isSameDay(new Date(), new Date());
		System.out.println(bool);
	}

	@Test
	public void testIsSameWeekDateDate() {
		boolean bool = DateUtils.isSameWeek(new Date(), new Date());
		System.out.println(bool);
	}

	@Test
	public void testIsSameMonthDateDate() {
		boolean bool = DateUtils.isSameMonth(new Date(), new Date());
		System.out.println(bool);
	}

}
