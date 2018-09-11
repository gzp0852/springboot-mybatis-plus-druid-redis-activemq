package com.wistronits.aml.commons.util;

/**
 * PageBeanField
 * @author gzp
 * @date 2018/8/24 14:41
 */
public enum PageBeanField {
	/**
	 * 每页封装记录数
	 */
	SIZE("size"),
	/**
	 * 每页记录数
	 */
	PAGESIZE("pageSize"),
	/**
	 * 当前页码
	 */
	PAGENUM("pageNum"),
	/**
	 * 总页数
	 */
	TOTALPAGE("totalPage"),
	/**
	 * 总记录条数
	 */
	TOTALCOUNT("totalCount"),
	/**
	 * 查询开始数目
	 */
	BEGIN("begin"),

	/**
	 * 排序字段
	 */
	SORTFIELD("sortField"),

	/**
	 * 排序方式
	 */
	SORTWAY("sortWay"),
	;
	private String field;

	PageBeanField(String field) {
		this.field = field;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
}
