package com.hc.sys.common.util.json;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.hc.sys.common.util.validate.StringUtil;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.ObjectMapper;
/**
 * Json转换工具类
 * @author tongpuxin
 */
@SuppressWarnings("unchecked")
public class JsonUtil {
	private static ObjectMapper om = new ObjectMapper();
	private static JsonFactory jsonFactory = new JsonFactory();

	/**
	 * json转String
	 * @param obj
	 */
	public static String formatToStr(Object obj) {
		String rst = "";
		if (obj != null) {
			try {
				rst = om.writeValueAsString(obj);
			} catch (Exception e) {
			    throw new java.lang.RuntimeException(e);
			}
		}
		return rst;
	}

	/**
	 * 转换json对象为Map
	 * @param json
	 */
	public static Map<String, Object> formatToMap(String json) {
		return (Map<String, Object>) formatObject(json, Map.class);
	}

	/**
	 * 转换json为对象
	 * @param json
	 * @param c
	 */
	public static <T> T formatObject(String json, Class<T> c) {
		T t = null;
		if (StringUtil.isNotBlank(json) && c != null) {
			try {
				t = (T) om.readValue(json, c);
			} catch (Exception e) {
			    throw new java.lang.RuntimeException(e);
			}
		}
		return t;
	}

	/**
	 * 转换json对象为List
	 * @param <T>
	 * @param json
	 * @param c
	 */
	public static <T> List<T> formatToList(String json, Class<T> c) {
		return (List<T>) toCollection(json, c, new ArrayList<T>());
	}

	/**
	 * 转换json对象为Set
	 * @param <T>
	 * @param json
	 * @param c
	 */
	public static <T> Set<T> formatToSet(String json, Class<T> c) {
		return (Set<T>) toCollection(json, c, new HashSet<T>());
	}

	/**
	 * 转换json对象为Collection
	 * @param <T>
	 * @param json
	 * @param c
	 * @param coll
	 */
	private static <T> Collection<T> toCollection(String json, Class<T> c, Collection<T> coll) {
		JsonParser jp = null;
		try {
			jp = jsonFactory.createJsonParser(json);
			jp.nextToken();
			while (jp.nextToken() == JsonToken.START_OBJECT) {
				T t = (T) om.readValue(jp, c);
				coll.add(t);
			}
		} catch (Exception e) {
		    throw new java.lang.RuntimeException(e);
		} finally {
			if (jp != null)
				try {
					jp.close();
				} catch (IOException e) {
				    throw new java.lang.RuntimeException(e);
				}
		}
		return coll;
	}
}
