package club.xiehaiyang.entity;

import java.util.Date;

public class SCpu {
	public SCpu() {
		super(); 
	}

	private String cpu0;

	private String cpu1;

	private String cpu2;

	private String cpu3;

	private Integer id;

	private Date time;

	public SCpu(String cpu0, String cpu1) {
		super();
		this.cpu0 = cpu0;
		this.cpu1 = cpu1;
	}

	public String getCpu0() {
		return cpu0;
	}

	public String getCpu1() {
		return cpu1;
	}

	public String getCpu2() {
		return cpu2;
	}

	public String getCpu3() {
		return cpu3;
	}

	public Integer getId() {
		return id;
	}

	public Date getTime() {
		return time;
	}

	public void setCpu0(String cpu0) {
		this.cpu0 = cpu0;
	}

	public void setCpu1(String cpu1) {
		this.cpu1 = cpu1;
	}

	public void setCpu2(String cpu2) {
		this.cpu2 = cpu2;
	}

	public void setCpu3(String cpu3) {
		this.cpu3 = cpu3;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}