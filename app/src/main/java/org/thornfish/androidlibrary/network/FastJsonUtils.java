package org.thornfish.androidlibrary.network;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

public class FastJsonUtils {

	public static Object toBean(String text) throws Exception{
		return JSON.parse(text);
	}

	public static <T> T toBean(String text, Class<T> clazz) throws Exception{
		return JSON.parseObject(text, clazz);
	}

	// 转换为数组
	public static <T> Object[] toArray(String text) throws Exception{
		return toArray(text, null);
	}

	// 转换为数组
	public static <T> Object[] toArray(String text, Class<T> clazz) throws Exception{
		return JSON.parseArray(text, clazz).toArray();
	}

	// 转换为List
	public static <T> List<T> toList(String text, Class<T> clazz) throws Exception{
		return JSON.parseArray(text, clazz);
	}

	/**
	 * 将javabean转化为序列化的json字符串
	 * @return
	 */
	public static String beanToJson(Object clazz ) {
		String textJson = JSON.toJSONString(clazz);
		Object objectJson  = JSON.parse(textJson);
		return textJson;
	}

	/**
	 * 将string转化为序列化的json字符串
	 * @return
	 */
	public static Object textToJson(String text) {
		Object objectJson  = JSON.parse(text);
		return objectJson;
	}

	/**
	 * json字符串转化为map
	 * @param s
	 * @return
	 */
	public static Map stringToCollect(String s) {
		Map m = JSONObject.parseObject(s);
		return m;
	}

	/**
	 * 将map转化为string
	 * @param m
	 * @return
	 */
	public static String collectToString(Map m) {
		String s = JSONObject.toJSONString(m);
		return s;
	}

	/**
	 * 将List转化为string
	 * @param m
	 * @return
	 * , SerializerFeature.DisableCircularReferenceDetect
	 */
	public static String listToString(List m) {
		String s = JSON.toJSONString(m);
		return s;
	}



	/**
	 * 功能描述：把JSON数据转换成普通字符串列表
	 * 
	 * @param jsonData
	 *            JSON数据
	 * @return
	 * @throws Exception
	 * @author myclover
	 */
	public static List<String> getStringList(String jsonData) throws Exception {
		return JSON.parseArray(jsonData, String.class);
	}

	/**
	 * 功能描述：把JSON数据转换成指定的java对象
	 * 
	 * @param jsonData
	 *            JSON数据
	 * @param clazz
	 *            指定的java对象
	 * @return
	 * @throws Exception
	 * @author myclover
	 */
	public static <T> T getSingleBean(String jsonData, Class<T> clazz)
			throws Exception {
		return JSON.parseObject(jsonData, clazz);
	}

	/**
	 * 功能描述：把JSON数据转换成指定的java对象列表
	 * 
	 * @param jsonData
	 *            JSON数据
	 * @param clazz
	 *            指定的java对象
	 * @return
	 * @throws Exception
	 * @author myclover
	 */
	public static <T> List<T> getBeanList(String jsonData, Class<T> clazz)
			throws Exception {
		return JSON.parseArray(jsonData, clazz);
	}

	/**
	 * 功能描述：把JSON数据转换成较为复杂的java对象列表
	 * 
	 * @param jsonData
	 *            JSON数据
	 * @return
	 * @throws Exception
	 * @author myclover
	 */
	public static List<Map<String, Object>> getBeanMapList(String jsonData)
			throws Exception {
		return JSON.parseObject(jsonData,
				new TypeReference<List<Map<String, Object>>>() {
				});
	}

	/**
	 * 将网络请求下来的数据用fastjson处理空的情况，并将时间戳转化为标准时间格式
	 * @param result
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String dealResponseResult(String result) {
		result = JSONObject.toJSONString(result,
				SerializerFeature.WriteClassName,
				SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullBooleanAsFalse,
				SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.WriteNullNumberAsZero,
				SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteDateUseDateFormat,
				SerializerFeature.WriteEnumUsingToString,
				SerializerFeature.WriteSlashAsSpecial,
				SerializerFeature.WriteTabAsSpecial);
		return result;
	}
}
