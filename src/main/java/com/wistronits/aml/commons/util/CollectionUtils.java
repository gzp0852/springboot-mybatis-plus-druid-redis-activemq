package com.wistronits.aml.commons.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 简单的collection工具类 可扩展
 * @author gzp
 * @date 2018/8/27 21:15
 */
public class CollectionUtils {

	/**
	 * 校验集合是否为null或者空集合
	 *
	 * @param coll [集合]
	 * @return boolean
	 */
	public static boolean isEmpty(Collection coll) {
		return (null == coll || coll.isEmpty());
	}

	/**
	 * 校验集合是否不为null或者空集合
	 * @param coll [集合]
	 * @return boolean
	 */
	public static boolean isNotEmpty(Collection coll) {
		return !CollectionUtils.isEmpty(coll);
	}

	/**
	 * 集合a与b的差集（集合元素必须重写equals）
	 *
	 * @param a [a集合 not null]
	 * @param b [b集合 not null]
	 * @return Collection
	 */
	public static Collection subtract(final Collection a, final Collection b) {

		List list = new ArrayList(a);
		Iterator it = b.iterator();
		while (it.hasNext()) {
			list.remove(it.next());
		}
		return list;
	}

	/**
	 * 集合a与b的交集 （集合元素必须重写equals）
	 *
	 * @param a [a集合 not null]
	 * @param b [b集合 not null]
	 * @return Collection
	 */
	public static Collection intersection(final Collection a, final Collection b) {

		List list = new ArrayList();
		Iterator it = a.iterator();
		while (it.hasNext()) {
			Object obj = it.next();
			if (b.contains(obj)) {
				list.add(obj);
			}
		}
		return list;
	}

	/**
	 * 集合a与b的并集 （集合元素必须重写equals）
	 * @param a [a集合 not null]
	 * @param b [b集合 not null]
	 * @return Collection
	 */
	public static Collection union(final Collection a, final Collection b) {

		List list = new ArrayList(a);
		Iterator it = b.iterator();
		while (it.hasNext()) {
			Object obj = it.next();
			if (!a.contains(obj)) {
				list.add(obj);
			}
		}
		return list;
	}

}

