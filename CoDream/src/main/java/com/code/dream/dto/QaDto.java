package com.code.dream.dto;

public class QaDto {

	
	private String qu_seq               ;
	private String qu_title             ;
	private String qu_content           ;
	private String author               ;
	private String regdate              ;
	private String replyed              ;
	private String category             ;
	private String origin               ;
	public QaDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QaDto(String qu_seq, String qu_title, String qu_content, String author, String regdate, String replyed,
			String category, String origin) {
		super();
		this.qu_seq = qu_seq;
		this.qu_title = qu_title;
		this.qu_content = qu_content;
		this.author = author;
		this.regdate = regdate;
		this.replyed = replyed;
		this.category = category;
		this.origin = origin;
	}
	@Override
	public String toString() {
		return "QaDto [qu_seq=" + qu_seq + ", qu_title=" + qu_title + ", qu_content=" + qu_content + ", author="
				+ author + ", regdate=" + regdate + ", replyed=" + replyed + ", category=" + category + ", origin="
				+ origin + "]";
	}
	public String getQu_seq() {
		return qu_seq;
	}
	public void setQu_seq(String qu_seq) {
		this.qu_seq = qu_seq;
	}
	public String getQu_title() {
		return qu_title;
	}
	public void setQu_title(String qu_title) {
		this.qu_title = qu_title;
	}
	public String getQu_content() {
		return qu_content;
	}
	public void setQu_content(String qu_content) {
		this.qu_content = qu_content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getReplyed() {
		return replyed;
	}
	public void setReplyed(String replyed) {
		this.replyed = replyed;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	
	
	
}
