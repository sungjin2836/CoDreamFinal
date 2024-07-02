package com.code.dream.coupon;

import java.util.List;

import com.code.dream.dto.CouponDto;
import com.code.dream.dto.ReceiptDto;

public interface ICouponDao {

	public List<CouponDto> CouponAll();
	public int insertCoupon(CouponDto dto);
	public List<CouponDto> MemCoupon(String id);
	public int insertMemCoupon(CouponDto dto);
	public int insertpayment(ReceiptDto dto);
	public int insertReceipt(ReceiptDto dto);
	public List<ReceiptDto> PaymentAll();
	public List<ReceiptDto> selectReceipt(String id);
	public int updateReceipt(String re_seq);
}
