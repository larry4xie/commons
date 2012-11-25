package my.commons.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 获取与当前时间相关的自增序列工具<br/>
 * </p>
 * 
 * @author xiegang
 * @since 2011-12-13
 *
 */
public class TimeSeqUtils {
	private static TimeSeqUtils.FORMAT defaultFormat = TimeSeqUtils.FORMAT.YYYYMMDDHHMMSS; // 默认时间格式
	private static int defaultDigit = 3; // 默认序列位数
	
	/**
	 * 支持的序列数位数(1-8)
	 */
	private static int digit = 8;
	
	/**
	 * 自增序列数
	 */
	private static int[] sequence = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
	
	/**
	 * 自增序列数最大值
	 */
	private static int[] sequenceMax = new int[]{9, 99, 999, 9999, 99999, 999999, 9999999, 99999999};
	
	/**
	 * 时间序列的格式
	 */
	public static enum FORMAT {
		/** yyyyMMdd */
		YYYYMMDD {
			@Override
			public DateFormat getDateFormat() {
				return new SimpleDateFormat("yyyyMMdd");
			}
		},
		/** HHmmss */
		HHMMSS {
			@Override
			public DateFormat getDateFormat() {
				return new SimpleDateFormat("HHmmss");
			}
		},
		/** yyyyMMddHHmmss */
		YYYYMMDDHHMMSS {
			@Override
			public DateFormat getDateFormat() {
				return new SimpleDateFormat("yyyyMMddHHmmss");
			}
		},
		/** yyyyMMddHHmmssSSS */
		YYYYMMDDHHMMSSSSS {
			@Override
			public DateFormat getDateFormat() {
				return new SimpleDateFormat("yyyyMMddHHmmssSSS");
			}
		};
		
		/**
		 * 获取日期格式化类
		 * @return
		 */
		public abstract DateFormat getDateFormat();
	}
	
	/**
	 * 获取一个当前时间的序列号
	 * 使用默认时间序列
	 * 
	 * @return
	 */
	public static String getTimeSequence(int digit) {
		return getTimeSequence(defaultFormat, digit);
	}
	
	/**
	 * 获取一个当前时间的序列号，采用默认长度
	 * 使用默认时间序列
	 * 
	 * @return
	 */
	public static String getTimeSequence() {
		return TimeSeqUtils.getTimeSequence(defaultFormat, defaultDigit);
	}
	
	/**
	 * 获取一个当前时间的序列号，采用默认长度
	 * 
	 * @return
	 */
	public static String getTimeSequence(TimeSeqUtils.FORMAT format) {
		return TimeSeqUtils.getTimeSequence(format, defaultDigit);
	}
	
	/**
	 * 获取一个当前时间的序列号
	 * @param format
	 * @param digit
	 * @return
	 */
	public static String getTimeSequence(TimeSeqUtils.FORMAT format, int digit) {
		DateFormat sdf = format.getDateFormat();
		String dateString = sdf.format(new Date(System.currentTimeMillis()));
		
		return (new StringBuffer(dateString)).append(getSequence(digit)).toString();
	}

	private static synchronized String getSequence(int digit) {
		digit = digit < 1 ? defaultDigit : digit > TimeSeqUtils.digit ? defaultDigit : digit;
		sequence[digit - 1] = ++sequence[digit - 1] <= sequenceMax[digit - 1] ? sequence[digit - 1] : 0;
		
		String pattern = "";
		for(int i = 0; i < digit; i++) {
			pattern += "0";
		}
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(sequence[digit - 1]);
	}
}
