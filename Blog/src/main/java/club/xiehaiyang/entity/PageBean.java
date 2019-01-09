package club.xiehaiyang.entity;

/**
 * 分页Model类
 * 
 * @author
 *
 */
public class PageBean {

	private int page; // 第几页
	private int pageSize; // 每页记录数
	@SuppressWarnings("unused")
	private int start; // 起始页

	public PageBean(int page, int pageSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
	}

	public PageBean(int page, int pageSize, int start) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.start = start;
	}

	public int getPage() {
		return page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getStart() {
		return (page - 1) * pageSize;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setStart(int start) {
		this.start = start;
	}

}
