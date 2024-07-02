package com.code.dream.dto;

public class ReceiptDto {

	private String re_seq            ;
	private String buyer             ;
	private String product_seq       ;
	private String price             ;
	private String cl_title             ;
	private String status            ;
	private String pay_seq           ;
	private String tid               ;
	private String paydate           ;
	public ReceiptDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReceiptDto(String re_seq, String buyer, String product_seq, String price, String cl_title, String status,
			String pay_seq, String tid, String paydate) {
		super();
		this.re_seq = re_seq;
		this.buyer = buyer;
		this.product_seq = product_seq;
		this.price = price;
		this.cl_title = cl_title;
		this.status = status;
		this.pay_seq = pay_seq;
		this.tid = tid;
		this.paydate = paydate;
	}
	@Override
	public String toString() {
		return "ReceiptDto [re_seq=" + re_seq + ", buyer=" + buyer + ", product_seq=" + product_seq + ", price=" + price
				+ ", cl_title=" + cl_title + ", status=" + status + ", pay_seq=" + pay_seq + ", tid=" + tid
				+ ", paydate=" + paydate + "]";
	}
	public String getRe_seq() {
		return re_seq;
	}
	public void setRe_seq(String re_seq) {
		this.re_seq = re_seq;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public String getProduct_seq() {
		return product_seq;
	}
	public void setProduct_seq(String product_seq) {
		this.product_seq = product_seq;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCl_title() {
		return cl_title;
	}
	public void setCl_title(String cl_title) {
		this.cl_title = cl_title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPay_seq() {
		return pay_seq;
	}
	public void setPay_seq(String pay_seq) {
		this.pay_seq = pay_seq;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getPaydate() {
		return paydate;
	}
	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}
	
	
}
