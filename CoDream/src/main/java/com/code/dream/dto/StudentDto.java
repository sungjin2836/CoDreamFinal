package com.code.dream.dto;

import java.util.Date;

public class StudentDto {
	private String student;
	private int cl_seq;
	private Date regdate;
	private String visit;
	private String status;

	public StudentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentDto(String student, int cl_seq, Date regdate, String visit, String status) {
		super();
		this.student = student;
		this.cl_seq = cl_seq;
		this.regdate = regdate;
		this.visit = visit;
		this.status = status;
	}

	@Override
	public String toString() {
		return "StudentDto [student=" + student + ", cl_seq=" + cl_seq + ", regdate=" + regdate + ", visit=" + visit
				+ ", status=" + status + "]";
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public int getCl_seq() {
		return cl_seq;
	}

	public void setCl_seq(int cl_seq) {
		this.cl_seq = cl_seq;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getVisit() {
		return visit;
	}

	public void setVisit(String visit) {
		this.visit = visit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
