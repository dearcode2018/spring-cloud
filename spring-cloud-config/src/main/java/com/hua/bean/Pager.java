/**
* Pager.java
* 
* @author qye.zheng
* 	version 1.0
 */
package com.hua.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述: 
 * @author qye.zheng
 * Pager
 */
public final class Pager<T> implements Serializable {
	
	private static final long serialVersionUID = 1000L;
	
	/* 页_大小 */
	private Integer pageSize = 20;
	
	/* 当前页 */
	private Integer currentPage = 0;
	
	/* 记录总条数 */
	private Integer dataSize = 0;
	
	/*  */
	private boolean needPage = true;
	
	/* 分页数据 */
	private List<T> beanList = new ArrayList<T>();

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the dataSize
	 */
	public Integer getDataSize() {
		return dataSize;
	}

	/**
	 * @param dataSize the dataSize to set
	 */
	public void setDataSize(Integer dataSize) {
		this.dataSize = dataSize;
	}

	/**
	 * @return the needPage
	 */
	public boolean isNeedPage() {
		return needPage;
	}

	/**
	 * @param needPage the needPage to set
	 */
	public void setNeedPage(boolean needPage) {
		this.needPage = needPage;
	}

	/**
	 * @return the beanList
	 */
	public List<T> getBeanList() {
		return beanList;
	}

	/**
	 * @param beanList the beanList to set
	 */
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	
}
