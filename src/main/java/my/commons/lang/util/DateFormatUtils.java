package my.commons.lang.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <ol>
 * <li>timeago</li>
 * <li>nicedate</li>
 * <li>format</li>
 * </ol>
 * 
 * L syntax:
 * <ul>
 * <li>L 月日</li>
 * <li>LL 年月日</li>
 * <li>LLL 年月日时分</li>
 * <li>LLLL 年月日时分秒</li>
 * </ul>
 * 
 * @author larry xie
 * @since 2014-03-27
 */
public class DateFormatUtils {
	/** 一年 = 365天5小时48分钟45秒 */
	public static int YEAR_SECONDS = 365 * 86400 + 5 * 3600 + 48 * 60 + 45;
	
	/** full日期格式 */
	public final static ThreadLocal<SimpleDateFormat> FUll_DATE_FORMAT = new ThreadLocal<SimpleDateFormat>(){
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy年MM月dd日");
		}
	};
	
	/** 日期格式 */
	public final static ThreadLocal<SimpleDateFormat> DATE_FORMAT = new ThreadLocal<SimpleDateFormat>(){
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("MM月dd日");
		}
	};
	
	/** full时间格式 */
	public final static ThreadLocal<SimpleDateFormat> FULL_TIME_FORMAT = new ThreadLocal<SimpleDateFormat>(){
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		}
	};
	
	/** 时间格式 */
	public final static ThreadLocal<SimpleDateFormat> TIME_FORMAT = new ThreadLocal<SimpleDateFormat>(){
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		}
	};

	/**
	 * 格式化时间已经过去多长时间<br/>
	 * 时间必须是在现在之前，否则一律显示"刚刚"<br/>
	 * 年的计算不是很精确{@link #YEAR_SECONDS}
	 * 
	 * @param trunc 截断级别(包括): <=0(时) 1(7日) 2(日) 3(月) >=4(年)
	 * @param L syntax
	 */
	public static String timeago(Date date, int trunc, String L) {
		// s
		int ct = (int)((System.currentTimeMillis() - date.getTime()) / 1000);
		if (ct < 60) {
			// 一分钟内
			return "刚刚";
		} else if (ct < 3600) {
			// 一个小时以内
			return ct / 60 + "分钟前";
		} else if (ct >= 3600 && ct < 86400) {
			// 一天以内
			return ct / 3600 + "小时前";
		} else if (ct >= 86400 && ct < 604800 && trunc >= 1){
			// 7天以内
			int day = ct / 86400;
			return day > 1 ? day + "天前" : "昨天";
		} else if (ct >= 604800 && ct < 2592000 && trunc >= 2){
			// 30天以内
			return ct / 86400 + "天前";
		} else if (ct >= 2592000 && ct < YEAR_SECONDS && trunc >= 3) {
			// 一年（365天）以内
			return ct / 2592000 + "个月前";
		} else if (ct >= YEAR_SECONDS && trunc >= 4) {
			// 一年按照365天计算
			return ct / YEAR_SECONDS + "年前";
		}
		
		// 上述都没有匹配成功执行 L syntax format
		return formart(date, L);
	}
	
	/**
	 * timeago(date, 1, "LLLL")
	 * @see #timeago(Date, int, String)
	 */
	public static String timeago(Date date) {
		return timeago(date, 1, "LLL");
	}
	
	/**
	 * timeago(date, 100, "LLLL")
	 * @see #timeago(Date, int, String)
	 */
	public static String fullTimeago(Date date) {
		return timeago(date, 100, null);
	}
	
	/**
	 * format Date use L syntax
	 * @param L L syntax
	 */
	public static String formart(Date date, String L) {
		if ("L".equalsIgnoreCase(L)) {
			return DATE_FORMAT.get().format(date);
		} else if ("Ll".equalsIgnoreCase(L)) {
			return FUll_DATE_FORMAT.get().format(date);
		} else if ("LLL".equalsIgnoreCase(L)) {
			return TIME_FORMAT.get().format(date);
		} else {
			return FULL_TIME_FORMAT.get().format(date);
		} 
	}
}