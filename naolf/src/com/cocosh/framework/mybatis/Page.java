package com.cocosh.framework.mybatis;

import java.util.ArrayList;
import java.util.List;

import com.cocosh.framework.util.CommonUtil;

/**
 * Mybatis分页参数及查询结果封装
 * 
 * @author jerry
 */
public class Page<T>{
    /**
     * 页编号 : 第几页
     */
    protected int pageNo = 1;
    /**
     * 页大小 : 每页的数量
     */
    protected int pageSize = CommonUtil.PAGESIZE;

    /**
     * 偏移量 : 第一条数据在表中的位置
     */
    protected int offset;

    /**
     * 限定数 : 每页的数量
     */
    protected int limit;

    // --结果 --//
    /**
     * 查询结果
     */
    protected List<T> result = new ArrayList<T>();

    /**
     * 总条数
     */
    protected int totalCount;

    /**
     * 总页数
     */
    protected int totalPages;
    
    /**
     * 页码导航(后台)
     */
    protected String pageContent;
    
    /**
     * 页码导航(官网)
     */
    protected String pageContentNews;
    
    private int startPageNo; // 开始页数
	private int endPageNo; // 结束页数
	private int pageShowNum = CommonUtil.PAGESHOWNUM;// 显示的页数

    /**
     * 计算偏移量
     */
    private void calcOffset() {
        this.offset = ((pageNo - 1) * pageSize);
    }

    /**
     * 计算限定数
     */
    private void calcLimit() {
        this.limit = pageSize;
    }

    // -- 构造函数 --//
    public Page() {
        this.calcOffset();
        this.calcLimit();
    }

    public Page(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.calcOffset();
        this.calcLimit();
    }

    // -- 访问查询参数函数 --//
    /**
     * 获得当前页的页号,序号从1开始,默认为1.
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 获得每页的记录数量,默认为1.
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
     */
    public int getFirst() {
        return ((pageNo - 1) * pageSize) + 1;
    }

    /**
     * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从0开始.
     */
    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    // -- 访问查询结果函数 --//
    /**
     * 取得页内的记录列表.
     */
    public List<T> getResult() {
        return result;
    }

    /**
     * 设置页内的记录列表.
     */
    public void setResult(final List<T> result) {
        this.result = result;
    }

    /**
     * 取得总记录数, 默认值为-1.
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 设置总记录数.
     */
    public void setTotalCount(final int totalCount) {
        this.totalCount = totalCount;
        this.totalPages = this.getTotalPages();
    }

    /**
     * 根据pageSize与totalCount计算总页数, 默认值为-1.
     */
    public int getTotalPages() {
        if (totalCount < 0) {
            return -1;
        }
        int pages = totalCount / pageSize;
        return totalCount % pageSize > 0 ? ++pages : pages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

	public String getPageContent() {
		StringBuffer sb = new StringBuffer();
		if(totalCount>0){
			sb.append("<div class=\"pull-left pagination-detail\">"+
							"<span class=\"pagination-info\">显示第 "+offset+" 到第 "+(offset+result.size())+" 条记录，总共 "+totalCount+" 条记录</span><span class=\"page-list\">每页显示 <select class=\"input-sm\">"+
							"<option value=\"10\" "+isSelected(10)+">10</option>"+
							"<option value=\"25\" "+isSelected(25)+">25</option>"+
							"<option value=\"50\" "+isSelected(50)+">50</option>"+
							"</select> 条记录</span>"+
						"</div>");
			sb.append("<ul class=\"pagination pull-right\">");
			if (pageNo <= 1) {
				sb.append("<li class=\"footable-page-arrow disabled\" data-page=\"1\"><a href=\"javascript:;\">«</a></li>");
				sb.append("<li class=\"footable-page-arrow disabled\" data-page=\"prev\"><a href=\"javascript:;\">‹</a></li>");
			} else {
				sb.append("<li class=\"footable-page-arrow\" data-page=\"1\"><a href=\"javascript:;\">«</a></li>");
				sb.append("<li class=\"footable-page-arrow\" data-page=\"prev\"><a href=\"javascript:;\">‹</a></li>");
			}
			countPages();
			for (int i = startPageNo; i <= endPageNo; i++) {
				if (i == pageNo) {
					sb.append("<li class=\"footable-page active disabled\" data-page=\""+i+"\"><a href=\"javascript:;\">"+i+"</a></li>");
				} else {
					sb.append("<li class=\"footable-page\" data-page=\""+i+"\"><a href=\"javascript:;\">"+i+"</a></li>");
				}
			}
			if (pageNo >= totalPages) {
				sb.append("<li class=\"footable-page-arrow disabled\" data-page=\"next\"><a href=\"javascript:;\">›</a></li>");
				sb.append("<li class=\"footable-page-arrow disabled\" data-page=\""+totalPages+"\"><a href=\"javascript:;\">»</a></li>");
			} else {
				sb.append("<li class=\"footable-page-arrow\" data-page=\"next\"><a href=\"javascript:;\">›</a></li>");
				sb.append("<li class=\"footable-page-arrow\" data-page=\""+totalPages+"\"><a href=\"javascript:;\">»</a></li>");
			}
			sb.append("</ul>");
		}
		pageContent = sb.toString();
		return pageContent;
	}

	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}
	
	public String getPageContentNews() {
		StringBuffer sb = new StringBuffer();
		if(totalCount>0){
			sb.append("<ul class=\"pagination\">");
			if (pageNo <= 1) {
				sb.append("<li class=\"footable-page-arrow disabled\" data-page=\"1\"><a href=\"javascript:;\">«</a></li>");
				sb.append("<li class=\"footable-page-arrow disabled\" data-page=\"prev\"><a href=\"javascript:;\">‹</a></li>");
			} else {
				sb.append("<li class=\"footable-page-arrow\" data-page=\"1\"><a href=\"javascript:;\">«</a></li>");
				sb.append("<li class=\"footable-page-arrow\" data-page=\"prev\"><a href=\"javascript:;\">‹</a></li>");
			}
			countPages();
			for (int i = startPageNo; i <= endPageNo; i++) {
				if (i == pageNo) {
					sb.append("<li class=\"footable-page active disabled\" data-page=\""+i+"\"><a href=\"javascript:;\">"+i+"</a></li>");
				} else {
					sb.append("<li class=\"footable-page\" data-page=\""+i+"\"><a href=\"javascript:;\">"+i+"</a></li>");
				}
			}
			if (pageNo >= totalPages) {
				sb.append("<li class=\"footable-page-arrow disabled\" data-page=\"next\"><a href=\"javascript:;\">›</a></li>");
				sb.append("<li class=\"footable-page-arrow disabled\" data-page=\""+totalPages+"\"><a href=\"javascript:;\">»</a></li>");
			} else {
				sb.append("<li class=\"footable-page-arrow\" data-page=\"next\"><a href=\"javascript:;\">›</a></li>");
				sb.append("<li class=\"footable-page-arrow\" data-page=\""+totalPages+"\"><a href=\"javascript:;\">»</a></li>");
			}
			sb.append("</ul>");
		}
		pageContentNews = sb.toString();
		return pageContentNews;
	}

	public void setPageContentNews(String pageContentNews) {
		this.pageContentNews = pageContentNews;
	}

	public void countPages() {
		if (pageNo - pageShowNum / 2 < 1) {
			startPageNo = 1;
			endPageNo = pageShowNum > totalPages ? totalPages : pageShowNum;
		} else if (pageNo + pageShowNum / 2 > totalPages) {
			int n = totalPages - pageShowNum + 1;
			startPageNo = n > 0 ? n : 1;
			endPageNo = totalPages;
		} else {
			startPageNo = pageNo - pageShowNum / 2;
			endPageNo = startPageNo + pageShowNum - 1;
		}
	}

	public String isSelected(int ps){
		if (ps==pageSize) {
			return "selected=\"selected\"";
		}
		return "";
	}
}
