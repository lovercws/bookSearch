package com.kingbase.bookSearch.common.bean;


/**
 * 分页model
 * @author zhaoguang
 * PageModel.java
 * 2015-3-18 下午02:27:40
 */
public class PageModel implements java.io.Serializable
{
	private static final long serialVersionUID = 2174602184151302258L;

	private static final int PAGE_SIZE = 10;

    /** 当前的页数 */
    private int              pageIndex;
    /** 一页显示的数量 */
    private int              pageSize;
    /** 总记录条数 */
    private int              recordCount;

    private int              pagetag;

    /** setter and getter method */
    public int getPageIndex()
    {
        return pageIndex < 1 ? 1 : this.pageIndex;
    }

    public void setPageIndex(int pageIndex)
    {
        this.pageIndex = pageIndex;
    }

    public int getPageSize()
    {
        return pageSize < 1 ? PAGE_SIZE : pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getRecordCount()
    {
        return recordCount;
    }

    public void setRecordCount(int recordCount)
    {
        this.recordCount = recordCount;
    }

    public int getTotalSize()
    {
        return this.getRecordCount() % this.getPageSize() == 0 ? this
                .getRecordCount() / this.getPageSize()
            : (this.getRecordCount() / this.getPageSize()) + 1;
    }

    public int getPagetag()
    {
        double tag = recordCount / (double) pageSize;
        tag = Math.ceil(tag);
        return (int) tag;
    }

    public void setPagetag(int pagetag)
    {
        this.pagetag = pagetag;
    }

    /** 分页时获取开始的行数 */
    public int getStartRow()
    {
        // SELECT * FROM hrm_notice LIMIT 2,1 3-1 * 1: 每一页显示的记录条数
        // 3 : 当前页数： pageIndex

        this.pageIndex = this.getPageIndex() > this.getTotalSize() ? this
                .getTotalSize() : this.getPageIndex();
        return (pageIndex - 1) * this.getPageSize();
    }
}
