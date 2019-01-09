package club.xiehaiyang.entity;

import java.util.Date;

public class SMem {
    public SMem() {
		super(); 
	}

	private Integer id;

	private Date time;

    private String total;

    private String used;

    public SMem(String used, String total) {
		super();
		this.used = used;
		this.total = total;
	}

    public Integer getId() {
        return id;
    }

    public Date getTime() {
        return time;
    }

    public String getTotal() {
        return total;
    }

    public String getUsed() {
        return used;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setUsed(String used) {
        this.used = used;
    }
}