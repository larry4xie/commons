package my.commons.util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

/**
 * <p>
 * json 工具类<br/>
 * 主要功能：
 * <ul>
 * <li>parse json string to java key-value object</li>
 * <li>parse json string to java array object</li>
 * <li>java object to json string</li>
 * </ul>
 * 基于fastjson实现
 * </p>
 * 
 * @author xiegang
 * @since 2011-12-30
 *
 */
public class JsonUtils {
	/**
	 * 默认序列化特征
	 */
	private final static SerializerFeature[] features;
	
	/**
	 * 美化的序列化特征
	 */
	private final static SerializerFeature[] prettyFeatures;
	
	static{
		// default deploy
		// feature
		features = new SerializerFeature[]{SerializerFeature.WriteEnumUsingToString,SerializerFeature.DisableCircularReferenceDetect};
		prettyFeatures = new SerializerFeature[]{SerializerFeature.WriteEnumUsingToString, SerializerFeature.PrettyFormat,SerializerFeature.DisableCircularReferenceDetect};
	}
	
	/**
	 * 使用默认格式将java对象转换成json串
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJSONString(Object obj) {
		return toJSONString(obj, "yyyy-MM-dd", false);
	}
	
	/**
	 * 使用默认格式将java对象转换成json串
	 * 
	 * @param obj
	 * @param prettyFormat 是否美化输出
	 * @return
	 */
	public static String toJSONString(Object obj, boolean prettyFormat) {
		return toJSONString(obj, "yyyy-MM-dd", prettyFormat);
	}
	
	/**
	 * 使用指定格式将java对象转换成json串
	 * 
	 * @param obj
	 * @param dateFormatString 日期的格式化
	 * @param prettyFormat 是否美化输出
	 * @return
	 */
	public static String toJSONString(Object obj, String dateFormatString, boolean prettyFormat) {
		SerializeConfig mapping= new SerializeConfig();
		mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormatString));
		
		return JSON.toJSONString(obj, mapping, prettyFormat ? prettyFeatures : features);
	}
	
	/**
	 * 解析json text to map
	 * @param text
	 * @return
	 */
	public static Map<String, Object> parse(String text) {
		JSONObject obj = JSON.parseObject(text);
		return obj;
	}
	
	/**
	 * 解析json array text to list
	 * 
	 * @param text
	 * @return
	 */
	public static List<Object> parseArray(String text) {
		JSONArray obj = JSON.parseArray(text);
		return obj;
	}
}
