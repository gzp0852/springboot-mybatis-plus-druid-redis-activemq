package com.wistronits.aml.commons.util;


import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 简单的map工具类  可扩展
 * @author gzp
 * @date 2018/8/27 21:21
 */
public class MapUtils {
	/**
	 * 校验map是否为null或者空map
	 *
	 * @param map [map]
	 * @return boolean
	 */
	public static boolean isEmpty(Map map) {
		return (map == null) || (map.isEmpty());
	}

	/**
	 * 校验map是否不为null或者空map
	 *
	 * @param map [map]
	 * @return boolean
	 */
	public static boolean isNotEmpty(Map map) {
		return !isEmpty(map);
	}

	/**
	 * 将资源映射文件内容读取为Map<String, Object>结构
	 *
	 * @param resourceBundle [ResourceBundle对象]
	 * @return Map
	 */
	public static Map toMap(final ResourceBundle resourceBundle) {
		Enumeration enumeration = resourceBundle.getKeys();
		Map map = new HashMap();

		while (enumeration.hasMoreElements()) {
			String key = (String) enumeration.nextElement();
			Object value = resourceBundle.getObject(key);
			map.put(key, value);
		}

		return map;
	}

	/**
	 * 将map内容读取为Properties对象结构
	 *
	 * @param map [Map对象]
	 * @return Properties
	 */
	public static Properties toProperties(final Map map) {
		Properties answer = new Properties();
		if (map != null) {
			for (Iterator iter = map.entrySet().iterator(); iter.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				Object key = entry.getKey();
				Object value = entry.getValue();
				answer.put(key, value);
			}
		}
		return answer;
	}
}

