package com.code.dream.dto;

import java.util.Date;

public class DocumentDto {
	private int doc_seq;
	private int cl_seq;
	private String doc_title;
	private String doc_content;
	private String author;
	private int file_gid;
	private Date regdate;
	public DocumentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DocumentDto(int doc_seq, int cl_seq, String doc_title, String doc_content, String author, int file_gid,
			Date regdate) {
		super();
		this.doc_seq = doc_seq;
		this.cl_seq = cl_seq;
		this.doc_title = doc_title;
		this.doc_content = doc_content;
		this.author = author;
		this.file_gid = file_gid;
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "DocumentDto [doc_seq=" + doc_seq + ", cl_seq=" + cl_seq + ", doc_title=" + doc_title + ", doc_content="
				+ doc_content + ", author=" + author + ", file_gid=" + file_gid + ", regdate=" + regdate + "]";
	}
	public int getDoc_seq() {
		return doc_seq;
	}
	public void setDoc_seq(int doc_seq) {
		this.doc_seq = doc_seq;
	}
	public int getCl_seq() {
		return cl_seq;
	}
	public void setCl_seq(int cl_seq) {
		this.cl_seq = cl_seq;
	}
	public String getDoc_title() {
		return doc_title;
	}
	public void setDoc_title(String doc_title) {
		this.doc_title = doc_title;
	}
	public String getDoc_content() {
		return doc_content;
	}
	public void setDoc_content(String doc_content) {
		this.doc_content = doc_content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getFile_gid() {
		return file_gid;
	}
	public void setFile_gid(int file_gid) {
		this.file_gid = file_gid;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	
}
