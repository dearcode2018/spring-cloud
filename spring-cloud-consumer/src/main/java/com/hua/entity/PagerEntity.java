/**
* PagerEntity.java
* 
* @author qye.zheng
* 	version 1.0
 */
package com.hua.entity;

/**
 * 描述: 
 * @author qye.zheng
 * PagerEntity
 */
public final class PagerEntity {
	
	/* 数据页_开始下标 */
	private long startIndex;
	
	/* 数据页_结束下标 */
	private long endIndex;
	
	/* 搜索条件对象 */
	private Object entity;

	/**
	 * @return the startIndex
	 */
	public long getStartIndex() {
		return startIndex;
	}

	/**
	 * @param startIndex the startIndex to set
	 */
	public void setStartIndex(long startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * @return the endIndex
	 */
	public long getEndIndex() {
		return endIndex;
	}

	/**
	 * @param endIndex the endIndex to set
	 */
	public void setEndIndex(long endIndex) {
		this.endIndex = endIndex;
	}

	/**
	 * @return the entity
	 */
	public Object getEntity() {
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	public void setEntity(Object entity) {
		this.entity = entity;
	}
	
}
