package com.code.dream.file;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.dream.dto.AttachFileDto;

@Service
public class FileServiceImpl implements IFileService {

	@Autowired
	private IFileDao dao;
	
	@Override
	public boolean insertAttachfile(List<AttachFileDto> list) {
		return dao.insertAttachfile(list);
	}

	@Override
	public AttachFileDto searchgid(String filename) {
		return dao.searchgid(filename);
	}

	@Override
	public AttachFileDto searchfile(String filename) {
		return dao.searchfile(filename);
	}

}
