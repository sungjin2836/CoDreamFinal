package com.code.dream.qa;

import java.util.List;

import com.code.dream.dto.QaDto;

public interface IQaService {

	public List<QaDto> qalist();
	public int insertQa(QaDto dto);
}
