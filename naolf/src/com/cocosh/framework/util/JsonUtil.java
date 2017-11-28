package com.cocosh.framework.util;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json对象转换
 * 依赖包jackson-core-2.2.3.jar、jackson-databind-2.2.3.jar、jackson-annotations
 * -2.2.3.jar
 * 
 * @author jerry
 */
public class JsonUtil {
	private static JsonFactory jf;
	private static ObjectMapper mapper;

	private JsonUtil() {
	}

	static {
		getFactory();
		getMapper();
	}

	public static ObjectMapper getMapper() {
		if (mapper == null) {
			mapper = new ObjectMapper();
		}
		return mapper;
	}

	public static JsonFactory getFactory() {
		if (jf == null)
			jf = new JsonFactory();
		return jf;
	}

	/**
	 * Object转成json
	 * 
	 * @param obj
	 * @return
	 */
	public static String obj2json(Object obj) {
		JsonGenerator jg = null;
		try {
			StringWriter out = new StringWriter();
			jg = jf.createGenerator(out);
			mapper.writeValue(jg, obj);
			return out.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (jg != null)
					jg.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * json转成Object json数组转成List时 默认是LinkedHashMap类型 List<LinkedHashMap<String,
	 * Object>> list = objectMapper.readValue(json, List.class); for (int i = 0;
	 * i < list.size(); i++) { Map<String, Object> map = list.get(i);
	 * Set<String> set = map.keySet(); for (Iterator<String> it =
	 * set.iterator();it.hasNext();) { String key = it.next();
	 * System.out.println(key + ":" + map.get(key)); } }
	 * 
	 * @param json
	 * @param clz
	 * @return
	 */
	public static Object json2obj(String json, Class<?> clz) {
		try {
			return mapper.readValue(json, clz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object map2bean(Object bean,LinkedHashMap<String, Object> data) {
		Method[] methods = bean.getClass().getDeclaredMethods();
		for (Method method : methods) {
			try {
				if (method.getName().startsWith("set")) {
					String field = method.getName();
					field = field.substring(field.indexOf("set") + 3);
					field = field.toLowerCase().charAt(0) + field.substring(1);
					method.invoke(bean, new Object[] { data.get(field) });
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bean;
	}
	
}
