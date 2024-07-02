package com.code.dream.qa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.dream.dto.QaDto;

@Service
public class QaServiceImpl implements IQaService {

	@Autowired
	private IQaDao dao;
	
	
	@Override
	public List<QaDto> qalist() {
		return dao.qalist();
	}
	
	@Override
	public int insertQa(QaDto dto) {
		return dao.insertQa(dto);
	}
}
