package com.code.dream.qa;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.code.dream.dto.QaDto;

@Repository
public class QaDaoImpl implements IQaDao {

	@Autowired
	private SqlSessionTemplate sqlsession;
	
	@Override
	public List<QaDto> qalist() {
		return sqlsession.selectList("qa.selectQalist");
	}
	
	@Override
	public int insertQa(QaDto dto) {
		return sqlsession.insert("qa.insertQa", dto);
	}
}
