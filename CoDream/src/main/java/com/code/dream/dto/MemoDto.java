package com.code.dream.dto;

import java.util.Date;

public class MemoDto {
	private int me_seq;
	private String me_title;
	private String me_content;
	private String author;
	private Date regdate;
	private int file_gid;
	private int cl_seq;
	public MemoDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemoDto(int me_seq, String me_title, String me_content, String author, Date regdate, int file_gid,
			int cl_seq) {
		super();
		this.me_seq = me_seq;
		this.me_title = me_title;
		this.me_content = me_content;
		this.author = author;
		this.regdate = regdate;
		this.file_gid = file_gid;
		this.cl_seq = cl_seq;
	}
	@Override
	public String toString() {
		return "MemoDto [me_seq=" + me_seq + ", me_title=" + me_title + ", me_content=" + me_content + ", author="
				+ author + ", regdate=" + regdate + ", file_gid=" + file_gid + ", cl_seq=" + cl_seq + "]";
	}
	public int getMe_seq() {
		return me_seq;
	}
	public void setMe_seq(int me_seq) {
		this.me_seq = me_seq;
	}
	public String getMe_title() {
		return me_title;
	}
	public void setMe_title(String me_title) {
		this.me_title = me_title;
	}
	public String getMe_content() {
		return me_content;
	}
	public void setMe_content(String me_content) {
		this.me_content = me_content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getFile_gid() {
		return file_gid;
	}
	public void setFile_gid(int file_gid) {
		this.file_gid = file_gid;
	}
	public int getCl_seq() {
		return cl_seq;
	}
	public void setCl_seq(int cl_seq) {
		this.cl_seq = cl_seq;
	}
	
	
}
