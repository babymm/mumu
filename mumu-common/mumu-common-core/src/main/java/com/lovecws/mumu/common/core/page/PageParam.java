package com.lovecws.mumu.common.core.page;

import java.io.Serializable;

/**
 * @desc 分页参数传递工具类
 * @author ganliang
 * @version 2016年8月10日 上午9:26:51
 */
public class PageParam implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 6297178964005032338L;

	/**
	 * 默认为第一页.
	 */
	public static final int DEFAULT_PAGE_NUM = 1;

	/**
	 * 默认每页记录数(15).
	 */
	public static final int DEFAULT_NUM_PER_PAGE = 10;
	
	/**
	 * 默认每页记录数(5).
	 */
	public static final int NUM_PER_PAGE_FIVE = 5;

	/**
	 * 最大每页记录数(100).
	 */
	public static final int MAX_PAGE_SIZE = 100;

	private int currentPage; // 当前页数

	private int numPerPage; // 每页记录数

	private int beginIndex;// 开始索引处

	/**
	 * 默认构造函数
	 */
	public PageParam() {
		this.currentPage=DEFAULT_PAGE_NUM;
		this.numPerPage=DEFAULT_NUM_PER_PAGE;
		this.beginIndex=(currentPage-1)*numPerPage;
	}
	
	/**
	 * 带参数的构造函数
	 * @param currentPage
	 */
	public PageParam(int currentPage) {
		if(currentPage<1){
			currentPage=1;
		}
		this.currentPage=currentPage;
		this.numPerPage=DEFAULT_PAGE_NUM;
		this.beginIndex=(currentPage-1)*numPerPage;
	}

	/**
	 * 带参数的构造函数
	 * @param currentPage
	 * @param numPerPage
	 */
	public PageParam(int currentPage, int numPerPage) {
		if(currentPage<1){
			currentPage=1;
		}
		this.currentPage=currentPage;
		this.numPerPage=numPerPage;
		this.beginIndex=(currentPage-1)*numPerPage;
	}

	/** 当前页数 */
	public int getCurrentPage() {
		return currentPage;
	}

	/** 当前页数 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/** 每页记录数 */
	public int getNumPerPage() {
		return numPerPage;
	}

	/** 每页记录数 */
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

}
