package com.code.dream.file;

import java.util.List;

import com.code.dream.dto.AttachFileDto;

public interface IFileService {

	public boolean insertAttachfile(List<AttachFileDto> list);
	public AttachFileDto searchgid(String filename);
	public AttachFileDto searchfile(String filename);
}
