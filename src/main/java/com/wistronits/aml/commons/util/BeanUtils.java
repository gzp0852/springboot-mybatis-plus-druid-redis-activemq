package com.wistronits.aml.commons.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * BeanUtils
 * @author Mengjun Wen
 * 定义一个工具类，做实体类与map集合或者json的互相转换
 */
public class BeanUtils {
	/**
	 * map转实体类
	 * @param map map集合
	 * @param beanClass 实体类class对象
	 * @param <T> 泛型
	 * @return 实例化的对象
	 */
	public static<T> T mapToObject(Map<String, Object> map, Class<T> beanClass) {
		T obj = null;
		if (map == null) {
			return null;
		}
		try {
			obj = beanClass.newInstance();
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				int mod = field.getModifiers();
				if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
					continue;
				}
				field.setAccessible(true);
				field.set(obj, map.get(field.getName()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return obj;
	}

	/**
	 * 通过getDeclaredFields 的方式复制属性值
	 *  getDeclaredFields方式不会返回父类的属性
	 *  @param o o
	 *  @return map
	 * */
	public static Map objectToMap(Object o) {
		Map<String, Object> resMap = new HashMap<>(8);
		try {
			Field[] declaredFields = o.getClass().getDeclaredFields();
			for (Field field : declaredFields) {
				field.setAccessible(true);
				//过滤内容为空的
				if (field.get(o) == null) {
					continue;
				}
				resMap.put(field.getName(), field.get(o));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * json转对象
	 * @param json json
	 * @param clazz clazz
	 * @param <T> <T>
	 * @return t
	 */
	public static<T> T jsonToObject(String json,Class<T> clazz){
		ObjectMapper mapper = new ObjectMapper();
		// 忽略不匹配的字段
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			T t = mapper.readValue(json, clazz);
			return t;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 对象转json
	 * @param  object object
	 * @return String
	 */
	public static String objectToJson(Object object){
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(object);
			return json;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

}