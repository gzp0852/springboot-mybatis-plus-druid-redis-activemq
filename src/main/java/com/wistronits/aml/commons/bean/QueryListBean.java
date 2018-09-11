package com.wistronits.aml.commons.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 列表查询的条件bean
 *
 * @author Mengjun Wen
 */
public class QueryListBean<T> {
	/**
	 * 开始查询的索引
	 */
	@JsonIgnore
	private Integer begin;
	/**
	 * 页面展示数量
	 */
	private Integer pageSize;
	/**
	 * 页码
	 */
	private Integer pageNum;
	/**
	 * 查询条件
	 */
	private T queryTerm;
	/**
	 * 排序条件
	 */
	private String sortItem;
	/**
	 * 排序方式 0:降序 1:升序
	 */
	private String sortWay;

	/**
	 * equals
	 *
	 * @param o Object
	 * @return boolean
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {

			return true;
		}

		if (o == null || getClass() != o.getClass()) {

			return false;
		}

		QueryListBean<?> that = (QueryListBean<?>) o;

		if (begin != null ? !begin.equals(that.begin) : that.begin != null) {

			return false;
		}
		if (pageSize != null ? !pageSize.equals(that.pageSize) : that.pageSize != null) {

			return false;
		}
		if (pageNum != null ? !pageNum.equals(that.pageNum) : that.pageNum != null) {

			return false;
		}
		if (queryTerm != null ? !queryTerm.equals(that.queryTerm) : that.queryTerm != null) {

			return false;
		}
		if (sortItem != null ? !sortItem.equals(that.sortItem) : that.sortItem != null) {

			return false;
		}
		return sortWay != null ? sortWay.equals(that.sortWay) : that.sortWay == null;
	}

	/**
	 * hashCode
	 * @return int
	 */
	@Override
	public int hashCode() {
		int result = begin != null ? begin.hashCode() : 0;
		result = 31 * result + (pageSize != null ? pageSize.hashCode() : 0);
		result = 31 * result + (pageNum != null ? pageNum.hashCode() : 0);
		result = 31 * result + (queryTerm != null ? queryTerm.hashCode() : 0);
		result = 31 * result + (sortItem != null ? sortItem.hashCode() : 0);
		result = 31 * result + (sortWay != null ? sortWay.hashCode() : 0);
		return result;
	}

	/**
	 * getBegin
	 * @return Integer
	 */
	public Integer getBegin() {
		if (pageSize != null && pageNum != null) {
			return (pageNum - 1) * pageSize;
		}
		return begin;
	}

	public void setBegin(Integer begin) {
		this.begin = begin;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public T getQueryTerm() {
		return queryTerm;
	}

	public void setQueryTerm(T queryTerm) {
		this.queryTerm = queryTerm;
	}

	public String getSortItem() {
		return sortItem;
	}

	public void setSortItem(String sortItem) {
		this.sortItem = sortItem;
	}

	public String getSortWay() {
		return sortWay;
	}

	public void setSortWay(String sortWay) {
		this.sortWay = sortWay;
	}
}
