package com.code.dream.file;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.code.dream.dto.AttachFileDto;

@Repository
public class FileDaoImpl implements IFileDao {

	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public boolean insertAttachfile(List<AttachFileDto> list) {
		return session.insert("attach.insertAttachfile", list)>0?true:false;
	}

	@Override
	public AttachFileDto searchgid(String filename) {
		return session.selectOne("attach.searchgid",filename);
	}

	@Override
	public AttachFileDto searchfile(String filename) {
		return session.selectOne("attach.searchfile",filename);
	}

}
