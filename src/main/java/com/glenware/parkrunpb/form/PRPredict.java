package com.glenware.parkrunpb.form;

import org.hibernate.validator.constraints.NotEmpty;

public class PRPredict {
	
	@NotEmpty 
	private Integer courseId;
	
	private Integer mmInt = 0;
	
	private Integer ssInt = 0;
	
	private String mm;
	
	private String ss;
	
	private PRCourse prCourse;

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getMmInt() {
		return mmInt;
	}

	public void setMmInt(Integer mmInt) {
			this.mmInt =  mmInt;
	}

	public Integer getSsInt() {
		return ssInt;
	}

	public void setSsInt(int ssInt) {
		this.ssInt = ssInt;
	}

	public int getRaceTime() {
		return (mmInt * 60) + ssInt;
	}

	public PRCourse getPrCourse() {
		return prCourse;
	}

	public void setPrCourse(PRCourse prCourse) {
		this.prCourse = prCourse;
	}

	public String getMm() {
		return mm;
	}

	public void setMm(String mm) {
		this.mm = mm;
	}

	public String getSs() {
		return ss;
	}

	public void setSs(String ss) {
		this.ss = ss;
	}

}