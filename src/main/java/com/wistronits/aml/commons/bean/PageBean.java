package com.wistronits.aml.commons.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * PageBean   封装分页数据
 * @author gzp
 * @param <T>
 */
public class PageBean<T> {
	/**
	 * 当前页码
	 */
	private Integer pageNum;
	/**
	 * 总页数
	 */
	private Integer totalPage;
	/**
	 * 总记录条数
	 */
	private Integer totalCount;
	/**
	 * 每页显示记录数
	 */
	private Integer size;
	/**
	 * 数据信息
	 */
	private List<T> data = new ArrayList<>();

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	/**
	 * equals
	 * @param o o
	 * @return  boolean
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {

			return true;
		}
		if (o == null || getClass() != o.getClass()) {

			return false;
		}

		PageBean<?> pageBean = (PageBean<?>) o;

		if (pageNum != null ? !pageNum.equals(pageBean.pageNum) : pageBean.pageNum != null) {

			return false;
		}
		if (totalPage != null ? !totalPage.equals(pageBean.totalPage) : pageBean.totalPage != null) {

			return false;
		}
		if (totalCount != null ? !totalCount.equals(pageBean.totalCount) : pageBean.totalCount != null) {

			return false;
		}
		if (size != null ? !size.equals(pageBean.size) : pageBean.size != null) {

			return false;
		}
		return data != null ? data.equals(pageBean.data) : pageBean.data == null;
	}

	/**
	 * hashCode
	 * @return int
	 */
	@Override
	public int hashCode() {
		int result = pageNum != null ? pageNum.hashCode() : 0;
		result = 31 * result + (totalPage != null ? totalPage.hashCode() : 0);
		result = 31 * result + (totalCount != null ? totalCount.hashCode() : 0);
		result = 31 * result + (size != null ? size.hashCode() : 0);
		result = 31 * result + (data != null ? data.hashCode() : 0);
		return result;
	}

	/**
	 * getNullPageBean
	 * @param queryListBean queryListBean
	 * @return PageBean
	 */
	public static PageBean getNullPageBean(QueryListBean queryListBean) {
		PageBean pageBean = new PageBean();
		Integer pageNum = queryListBean.getPageNum();
		Integer pageSize = queryListBean.getPageSize();
		if (pageNum != null && pageSize != null) {
			pageBean.setSize(pageSize);
			pageBean.setPageNum(pageNum);
		}
		pageBean.setTotalPage(0);
		pageBean.setTotalCount(0);
		pageBean.setData(new ArrayList(1));
		return pageBean;
	}
}
