package club.xiehaiyang.entity;

/**
 * ��ҳModel��
 * 
 * @author
 *
 */
public class PageBean {

	private int page; // �ڼ�ҳ
	private int pageSize; // ÿҳ��¼��
	@SuppressWarnings("unused")
	private int start; // ��ʼҳ

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
