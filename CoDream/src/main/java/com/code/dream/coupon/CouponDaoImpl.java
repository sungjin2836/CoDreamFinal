package com.code.dream.coupon;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.code.dream.dto.CouponDto;
import com.code.dream.dto.ReceiptDto;

@Repository
public class CouponDaoImpl implements ICouponDao {

	@Autowired
	private SqlSessionTemplate sqlsession;
	
	@Override
	public List<CouponDto> CouponAll() {
		return sqlsession.selectList("coupon.selectCoupon");
	}
	
	@Override
	public int insertCoupon(CouponDto dto) {
		return sqlsession.insert("coupon.insertCoupon", dto);
	}
	
	@Override
	public List<CouponDto> MemCoupon(String id) {
		return sqlsession.selectList("coupon.selectMemCoupon", id);
	}
	
	@Override
	public int insertMemCoupon(CouponDto dto) {
		return sqlsession.insert("coupon.insertMemCoupon", dto);
	}
	
	@Override
	public int insertpayment(ReceiptDto dto) {
		return sqlsession.insert("receipt.insertpayment",dto);
	}
	@Override
	public int insertReceipt(ReceiptDto dto) {
		return sqlsession.insert("receipt.insertReceipt", dto);
	}
	@Override
	public List<ReceiptDto> PaymentAll() {
		return sqlsession.selectList("receipt.selectPay");
	}
	@Override
	public List<ReceiptDto> selectReceipt(String id) {
		return sqlsession.selectList("receipt.selectReceipt", id);
	}
	@Override
	public int updateReceipt(String re_seq) {
		return sqlsession.update("receipt.updateReceipt", re_seq);
	}
	
}
