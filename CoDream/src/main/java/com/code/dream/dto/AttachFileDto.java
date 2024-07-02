package com.code.dream.dto;

public class AttachFileDto {

	private String filename;
	private String file_gid;
	private String origname;
	private String extension;
	private String filepath;
	private String regdate;
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFile_gid() {
		return file_gid;
	}
	public void setFile_gid(String file_gid) {
		this.file_gid = file_gid;
	}
	public String getOrigname() {
		return origname;
	}
	public void setOrigname(String origname) {
		this.origname = origname;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "AttachFileDto [filename=" + filename + ", file_gid=" + file_gid + ", origname=" + origname
				+ ", extension=" + extension + ", filepath=" + filepath + ", regdate=" + regdate + "]";
	}
	
}
