package com.code.dream.regteacher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.dream.dto.AttachFileDto;
import com.code.dream.dto.PageDto;
import com.code.dream.dto.RegteacherDto;

@Service
public class RegteacherServiceImpl implements IRegteacherService {

	@Autowired
	private IRegteacherDao dao;
	
	@Override
	public boolean insertRegteacher(RegteacherDto dto) {
		return dao.insertRegteacher(dto);
	}

	@Override
	public List<RegteacherDto> selectRegteacher(PageDto dto) {
		return dao.selectRegteacher(dto);
	}

	@Override
	public RegteacherDto detailRegteacher(String te_seq) {
		return dao.detailRegteacher(te_seq);
	}

	@Override
	public boolean modifyRegteacher(String te_admit, String te_seq) {
		return dao.modifyRegteacher(te_admit, te_seq);
	}

	@Override
	public List<AttachFileDto> selectFiles(String file_gid) {
		return dao.selectFiles(file_gid);
	}

	@Override
	public RegteacherDto countRegteacher(String userid) {
		return dao.countRegteacher(userid);
  }
  
	@Override
	public int regteacherCount() {
		return dao.regteacherCount();
	}

	
}
