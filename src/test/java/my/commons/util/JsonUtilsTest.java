package my.commons.util;

import java.util.List;
import java.util.Map;

import my.commons.util.JsonUtils;

import org.junit.Assert;
import org.junit.Test;

public class JsonUtilsTest {

	@Test
	public void testParse() {
		String json = "{ LOOPBACK : \"desc\", ETHERNET: [ { MAC: \"e0:cb:4e:cc:53:75\", DESC: \"desc\" }, { MAC: \"04:7d:7b:70:cd:d8\", DESC: \"desc\" } ] } ";

		Map<String, Object> jsonObj = JsonUtils.parse(json);
		
		Assert.assertEquals(jsonObj.get("LOOPBACK"), "desc");
		
		List<?> ethernet = (List<?>) jsonObj.get("ETHERNET");
		@SuppressWarnings("unchecked")
		Map<String, Object> e = (Map<String, Object>) ethernet.get(1);
		
		Assert.assertEquals(e.get("MAC"), "04:7d:7b:70:cd:d8");
	}

}
