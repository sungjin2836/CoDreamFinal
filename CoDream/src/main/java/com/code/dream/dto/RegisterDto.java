package com.code.dream.dto;

import java.util.Date;

public class RegisterDto {
	
	private String id;
	private String name;
	private String password;
	private String phone;
	private String email;
	private String address;
	private String delflag;
	private String resume;
	private Date regdate;
	private String adrecieve;
	
	public RegisterDto() {
	}

	public RegisterDto(String id, String name, String password, String phone, String email, String address, String delflag,
			String resume, Date regdate, String adrecieve) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.delflag = delflag;
		this.resume = resume;
		this.regdate = regdate;
		this.adrecieve = adrecieve;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getAdrecieve() {
		return adrecieve;
	}

	public void setAdrecieve(String adrecieve) {
		this.adrecieve = adrecieve;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", password=" + password + ", phone=" + phone + ", email="
				+ email + ", address=" + address + ", delflag=" + delflag + ", resume=" + resume + ", regdate="
				+ regdate + ", adrecieve=" + adrecieve + "]";
	}
	
}
