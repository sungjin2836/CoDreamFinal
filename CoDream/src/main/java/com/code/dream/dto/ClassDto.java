package com.code.dream.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ClassDto {
	private int cl_seq;
	private String cl_title;
	private String cl_content;
	private String teacher;
	private int price;
	private Date startday;
	private Date endday;
	private String term;
	private String address;
	private int maxmember;
	private List<Map<Integer,String>> hashList;

	public ClassDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClassDto(int cl_seq, String cl_title, String cl_content, String teacher, int price, Date startday,
			Date endday, String term, String address, int maxmember, List<Map<Integer,String>> hashList) {
		super();
		this.cl_seq = cl_seq;
		this.cl_title = cl_title;
		this.cl_content = cl_content;
		this.teacher = teacher;
		this.price = price;
		this.startday = startday;
		this.endday = endday;
		this.term = term;
		this.address = address;
		this.maxmember = maxmember;
		this.hashList = hashList;
	}

	@Override
	public String toString() {
		return "ClassDto [cl_seq=" + cl_seq + ", cl_title=" + cl_title + ", cl_content=" + cl_content + ", teacher="
				+ teacher + ", price=" + price + ", startday=" + startday + ", endday=" + endday + ", term=" + term
				+ ", address=" + address + ", maxmember=" + maxmember + ", hashList=" + hashList + "]";
	}

	public int getCl_seq() {
		return cl_seq;
	}

	public void setCl_seq(int cl_seq) {
		this.cl_seq = cl_seq;
	}

	public String getCl_title() {
		return cl_title;
	}

	public void setCl_title(String cl_title) {
		this.cl_title = cl_title;
	}

	public String getCl_content() {
		return cl_content;
	}

	public void setCl_content(String cl_content) {
		this.cl_content = cl_content;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getStartday() {
		return startday;
	}

	public void setStartday(Date startday) {
		this.startday = startday;
	}

	public Date getEndday() {
		return endday;
	}

	public void setEndday(Date endday) {
		this.endday = endday;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getMaxmember() {
		return maxmember;
	}

	public void setMaxmember(int maxmember) {
		this.maxmember = maxmember;
	}

	public List<Map<Integer,String>> getHashList() {
		return hashList;
	}

	public void setHashList(List<Map<Integer,String>> hashList) {
		this.hashList = hashList;
	}

}
