package com.code.dream.dto;

import java.util.Date;

public class CouponDto {

	private String coupon_seq         ;
	private String couponname         ;
	private int discount           ;
	private int maxprice           ;
	private String status             ;
	private String clist_seq        ;
	private String id               ;
	private Date expire           ;
	private String deflag           ;
	
	public CouponDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CouponDto(String coupon_seq, String couponname, int discount, int maxprice, String status, String clist_seq,
			String id, Date expire, String deflag) {
		super();
		this.coupon_seq = coupon_seq;
		this.couponname = couponname;
		this.discount = discount;
		this.maxprice = maxprice;
		this.status = status;
		this.clist_seq = clist_seq;
		this.id = id;
		this.expire = expire;
		this.deflag = deflag;
	}

	@Override
	public String toString() {
		return "CouponDto [coupon_seq=" + coupon_seq + ", couponname=" + couponname + ", discount=" + discount
				+ ", maxprice=" + maxprice + ", status=" + status + ", clist_seq=" + clist_seq + ", id=" + id
				+ ", expire=" + expire + ", deflag=" + deflag + "]";
	}

	public String getCoupon_seq() {
		return coupon_seq;
	}

	public void setCoupon_seq(String coupon_seq) {
		this.coupon_seq = coupon_seq;
	}

	public String getCouponname() {
		return couponname;
	}

	public void setCouponname(String couponname) {
		this.couponname = couponname;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getMaxprice() {
		return maxprice;
	}

	public void setMaxprice(int maxprice) {
		this.maxprice = maxprice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getClist_seq() {
		return clist_seq;
	}

	public void setClist_seq(String clist_seq) {
		this.clist_seq = clist_seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getExpire() {
		return expire;
	}

	public void setExpire(Date expire) {
		this.expire = expire;
	}

	public String getDeflag() {
		return deflag;
	}

	public void setDeflag(String deflag) {
		this.deflag = deflag;
	}
	
	
	
}
