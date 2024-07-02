package com.code.dream.coupon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.code.dream.dto.CouponDto;
import com.code.dream.dto.ReceiptDto;

@Transactional
@Service
public class CouponServiceImpl implements ICouponService {

	@Autowired
	private ICouponDao dao;
	
	@Override
	public List<CouponDto> CouponAll() {
		
		return dao.CouponAll();
	}
	
	@Override
	public int insertCoupon(CouponDto dto) {
		return dao.insertCoupon(dto);
	}
	
	@Override
	public List<CouponDto> MemCoupon(String id) {
		return dao.MemCoupon(id);
	}
	
	@Override
	public int insertMemCoupon(CouponDto dto) {
		return dao.insertMemCoupon(dto);
	}
	
	@Override
	public int insertPay(ReceiptDto dto) {
		dao.insertpayment(dto);
		dto.setPay_seq(dto.getRe_seq());		
		return dao.insertReceipt(dto);
	}
	
	@Override
	public List<ReceiptDto> PaymentAll() {
		return dao.PaymentAll();
	}
	@Override
	public List<ReceiptDto> selectReceipt(String id) {
		return dao.selectReceipt(id);
	}
	@Override
	public int updateReceipt(String re_seq) {
		return dao.updateReceipt(re_seq);
	}
	
}
