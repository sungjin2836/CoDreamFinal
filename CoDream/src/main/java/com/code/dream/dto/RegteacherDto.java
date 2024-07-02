package com.code.dream.dto;

public class RegteacherDto {

	private int te_seq;		// seq
	private String te_title;	// 제목
	private String userid;		// 작성자
	private String te_content;	// 내용
	private String file_gid;	// 파일 group id
	private String te_admit;	// 승인 여부
	public int getTe_seq() {
		return te_seq;
	}
	public void setTe_seq(int te_seq) {
		this.te_seq = te_seq;
	}
	public String getTe_title() {
		return te_title;
	}
	public void setTe_title(String te_title) {
		this.te_title = te_title;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTe_content() {
		return te_content;
	}
	public void setTe_content(String te_content) {
		this.te_content = te_content;
	}
	public String getFile_gid() {
		return file_gid;
	}
	public void setFile_gid(String file_gid) {
		this.file_gid = file_gid;
	}
	public String getTe_admit() {
		return te_admit;
	}
	public void setTe_admit(String te_admit) {
		this.te_admit = te_admit;
	}
	@Override
	public String toString() {
		return "RegteacherDto [te_seq=" + te_seq + ", te_title=" + te_title + ", userid=" + userid + ", te_content="
				+ te_content + ", file_gid=" + file_gid + ", te_admit=" + te_admit + "]";
	}
}
