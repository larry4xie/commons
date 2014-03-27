package my.commons.lang.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

public class DateFormatUtilsTest {
	@Test
	public void testFormat() {
		Date now = new Date();
		
		Assert.assertEquals(new SimpleDateFormat("MM月dd日").format(now), DateFormatUtils.formart(now, "L"));
		Assert.assertEquals(new SimpleDateFormat("yyyy年MM月dd日").format(now), DateFormatUtils.formart(now, "LL"));
		Assert.assertEquals(new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(now), DateFormatUtils.formart(now, "LLL"));
		Assert.assertEquals(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(now), DateFormatUtils.formart(now, "LLLL"));
	}

	@Test
	public void testTimeago() {
		Date now = new Date();

		Assert.assertEquals("刚刚", DateFormatUtils.timeago(now));
		Assert.assertEquals("10分钟前", DateFormatUtils.timeago(new Date(now.getTime() - 10 * 60 * 1000L)));
		Assert.assertEquals("2小时前", DateFormatUtils.timeago(new Date(now.getTime() - 2 * 60 * 60 * 1000L)));
		Assert.assertEquals("昨天", DateFormatUtils.timeago(new Date(now.getTime() - 1 * 24 * 60 * 60 * 1000L)));
		Assert.assertEquals("6天前", DateFormatUtils.timeago(new Date(now.getTime() - 6 * 24 * 60 * 60 * 1000L)));
		Assert.assertEquals(new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(new Date(now.getTime() - 8 * 24 * 60 * 60 * 1000L)), DateFormatUtils.timeago(new Date(now.getTime() - 8 * 24 * 60 * 60 * 1000L)));

		Assert.assertEquals("8天前", DateFormatUtils.fullTimeago(new Date(now.getTime() - 8 * 24 * 60 * 60 * 1000L)));
		Assert.assertEquals("2个月前", DateFormatUtils.fullTimeago(new Date(now.getTime() - 80 * 24 * 60 * 60 * 1000L)));
		Assert.assertEquals("3年前", DateFormatUtils.fullTimeago(new Date(now.getTime() - 1200 * 24 * 60 * 60 * 1000L)));
	}

}
