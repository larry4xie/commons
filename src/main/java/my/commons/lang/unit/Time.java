package my.commons.lang.unit;

import java.util.concurrent.TimeUnit;

/**
 * 时间单位
 * 
 * @author xiegang
 * @since 2012-12-20
 * 
 */
public enum Time implements Unit<Time> {
	NANOSECONDS(1, TimeUnit.NANOSECONDS),
	MICROSECONDS(1000, NANOSECONDS, TimeUnit.MICROSECONDS),
	MILLISECONDS(1000, MICROSECONDS, TimeUnit.MILLISECONDS),
	SECONDS(1000, MILLISECONDS, TimeUnit.SECONDS),
	MINUTES(60, SECONDS, TimeUnit.MINUTES),
	HOURS(60, MINUTES, TimeUnit.HOURS),
	DAYS(24, HOURS, TimeUnit.DAYS);

	private final double multiplier;
	private final TimeUnit timeUnit;

	private Time(double multiplier, TimeUnit timeUnit) {
		this.multiplier = multiplier;
		this.timeUnit = timeUnit;
	}

	private Time(double multiplier, Time base, TimeUnit timeUnit) {
		this(multiplier * base.multiplier, timeUnit);
	}

	/**
	 * Returns the equivalent {@code TimeUnit}.
	 */
	public TimeUnit getTimeUnit() {
		return timeUnit;
	}
	
	@Override
	public double multiplier() {
		return multiplier;
	}

	@Override
	public double transfer(Time to, double value) {
		return value * multiplier() / to.multiplier;
	}
}