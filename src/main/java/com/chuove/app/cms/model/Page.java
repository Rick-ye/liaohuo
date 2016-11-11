package com.chuove.app.cms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页类
 * @author <a href="mailto:lauking1001@163.com">chenjq</a>
 */
public class Page<T> implements Serializable{
	private static final long serialVersionUID = 3249989163244891147L;

	public static int DEFAULT_PAGE_SIZE =20;

	private int pageSize = DEFAULT_PAGE_SIZE; // 每页的记录数
	private int currentPage = 1;// 当前页数
	private int previousPage = 1;// 上一页数
	private int nextPage = 1;// 下一页数
	private int totalCount; // 总记录数
	private int totalPageCount;// 总页数
	private int start;
	private List<T> data; // 当前页中存放的记录,类型一般为List

	private int pageNum;// 每页当前的记录数

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		getTotalPageCount();
		getCurrentPage();
		getPreviouPage();
		getNextPage();
		getLastPage();
		getMinPage();
		getMaxPageNo(this.minPage);
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNum(){
		return this.pageNum;
	}
	/**
	 * 构造方法，只构造空页.
	 */
	public Page() {
		this(DEFAULT_PAGE_SIZE, new ArrayList<T>());
	}

	/**
	 * 默认构造方法.
	 * 
	 * @param start
	 *            本页数据在数据库中的起始位置
	 * @param totalSize
	 *            数据库中总记录条数
	 * @param pageSize
	 *            本页容量
	 * @param data
	 *            本页包含的数据
	 */
	public Page(int pageSize, List<T> data) {
		this.pageSize = pageSize;
		this.data = data;
	}

	/**
	 * 取总记录数.
	 */
	public int getTotalCount() {
		return this.totalCount;
	}

	/**
	 * 取总页数.
	 */
	public int getTotalPageCount() {
		if (totalCount % pageSize == 0)
			totalPageCount = totalCount / pageSize;
		else
			totalPageCount = totalCount / pageSize + 1;
		return totalPageCount;
	}

	/**
	 * 取每页数据容量.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 取当前页中的记录.
	 */
	public List<T> getData() {
		return this.data;
	}
	public void setData( List<T> data) {
		this.data = data;
		if (data != null)
			this.pageNum = data.size();
	}

	/**
	 * 取该页当前页码,页码从1开始.
	 */
	public int getCurrentPage() {
		this.currentPage = start / pageSize + 1;
		return this.currentPage;
	}

	/**
	 * 该页是否有下一页.
	 */
	public boolean hasNextPage() {
		return this.getCurrentPage() < this.getTotalPageCount();
	}

	/**
	 * 该页是否有上一页.
	 */
	public boolean hasPreviousPage() {
		return this.getCurrentPage() > 1;
	}

	/**
	 * 获取任一页第一条数据在数据集的位置，每页条数使用默认值.
	 * 
	 */
	protected static int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}

	/**
	 * 获取任一页第一条数据在数据集的位置.
	 * 
	 * @param pageNo
	 *            从1开始的页号
	 * @param pageSize
	 *            每页记录条数
	 * @return 该页第一条数据
	 */
	public static int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}

	private int nMaxPages = 4;
	private int lastPage;

	/** 未页的页号 * */
	public long getLastPage() {
		lastPage = getTotalPageCount();
		return (getCurrentPage() < lastPage) ? lastPage : 0;
	}

	/** 下一页 * */
	public int getNextPage() {
		nextPage = getCurrentPage() + 1;
		return (nextPage <= getTotalPageCount()) ? nextPage : 0;
	}

	/** 上一页的页号 * */
	public int getPreviouPage() {
		previousPage = getCurrentPage() - 1;
		return (previousPage > 0) ? previousPage : 0;
	}

	private int minPage = 1;

	/** 获取显示的最小页码 * */
	public long getMinPage() {
		// 设置开始
		int pageno = getCurrentPage();
		int pageTotal = getTotalPageCount();
		if (pageno - (nMaxPages + 1) / 2 > 0) {
			minPage = pageno - (nMaxPages + 1) / 2;
		}

		if (minPage + nMaxPages > pageTotal)
			minPage = pageTotal - nMaxPages;
		if (minPage <= 0)
			minPage = 1;
		return minPage;
	}

	private int maxPage = 0;

	/** 获取显示的最大页码 * */
	public int getMaxPageNo(int nMinPageNo) {
		// 设置结束
		int pageTotal = getTotalPageCount();
		if (nMinPageNo + nMaxPages <= pageTotal)
			maxPage = nMinPageNo + nMaxPages;
		else
			maxPage = pageTotal;
		return maxPage;
	}
}
