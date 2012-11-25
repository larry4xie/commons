package my.commons.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import my.commons.util.EncryptUtils;

import org.junit.Assert;
import org.junit.Test;

public class EncryptUtilsTest {
	@Test
	public void testByteArrayHexString() throws UnsupportedEncodingException {
		byte[] origin = "123456f".getBytes();
		
		String result = EncryptUtils.byte2hex(origin);
//		System.out.println(result);
		
		byte[] result1 = EncryptUtils.hex2byte(result);
		Assert.assertArrayEquals(origin, result1);
	}
	
	@Test
	public void testMD5() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String result = EncryptUtils.MD5Encode("123456");
		Assert.assertEquals(result, "e10adc3949ba59abbe56e057f20f883e");
		
//		System.out.println(result);
	}
	
	@Test
	public void testSHA1() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String result = EncryptUtils.SHA1Encode("123456");
		Assert.assertEquals(result, "7c4a8d09ca3762af61e59520943dc26494f8941b");
		
//		System.out.println(result);
	}
	
	@Test
	public void testSHA256() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String result = EncryptUtils.SHA256Encode("123456");
		Assert.assertEquals(result, "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92");
		
//		System.out.println(result);
	}
	
	@Test
	public void testSHA384() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String result = EncryptUtils.SHA384Encode("123456");
		Assert.assertEquals(result, "0a989ebc4a77b56a6e2bb7b19d995d185ce44090c13e2984b7ecc6d446d4b61ea9991b76a4c2f04b1b4d244841449454");
		
//		System.out.println(result);
	}
	
	@Test
	public void testSHA512() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String result = EncryptUtils.SHA512Encode("123456");
		Assert.assertEquals(result, "ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413");
		
//		System.out.println(result);
	}
	
	
	@Test
	public void testDESByte() throws Exception {
		byte[] origin = "123456".getBytes("UTF-8");
		byte[] key = "cybertech-yanjiubu".getBytes("UTF-8");
		
		byte[] result = EncryptUtils.DESEncrypt(origin, key);
		
		byte[] result1 = EncryptUtils.DESDecrypt(result, key);
		
		Assert.assertArrayEquals(origin, result1);
	}
	
	@Test
	public void testDESString() throws Exception {
		String result = EncryptUtils.DESEncrypt("123456", "I am ENCRYPT default key, ha ha ...", "UTF-8");
//		System.out.println(result);
		
		String result1 = EncryptUtils.DESDecrypt(result, "I am ENCRYPT default key, ha ha ...", "UTF-8");
//		System.out.println(result1);
		
		Assert.assertEquals(result1, "123456");
	}
}
