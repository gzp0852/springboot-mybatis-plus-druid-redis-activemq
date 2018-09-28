/*
 * 文件名:  com.fiberhome.sdn.core.common.utils.StringUtils.java
 * 版  权:  FiberHome Copyright YYYY-YYYY,  All rights reserved
 * 创建人:  sunhuan
 * 创建时间:  2017/8/24
 */
package com.wistronits.aml.commons.util;

/**
 * 简单的String工具类 可扩展
 *
 * Created by sunhuan on 2017/8/22.
 */
public class StringUtils {

    /**
     * 检验字符串是否为null或者""
     * @param cs [字符串]
     * @return boolean
     */
    public static boolean isEmpty(CharSequence cs) {
        return (cs == null) || (cs.length() == 0);
    }

    /**
     * 检验字符串是否不为null或者""
     * @param cs [字符串]
     * @return boolean
     */
    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * 校验字符串是否为null或者""或者"   "
     * @param cs [字符串]
     * @return boolean
     */
    public static boolean isBlank(CharSequence cs) {

        if (isEmpty(cs)) {
            return true;
        }

        final int strLen = cs.length();

        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 校验字符串是否不为null或者""或者"   "
     *
     * @param cs [字符串]
     * @return boolean
     */
    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    /**
     * 是否是为纯数字字符串
     * @param cs [字符串]
     * @return boolean
     */
    public static boolean isNumeric(CharSequence cs) {
        if (isEmpty(cs)) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
