package com.code.dream.regteacher;

import java.util.List;

import com.code.dream.dto.AttachFileDto;
import com.code.dream.dto.PageDto;
import com.code.dream.dto.RegteacherDto;

public interface IRegteacherDao {

	public boolean insertRegteacher(RegteacherDto dto);
	public List<RegteacherDto> selectRegteacher(PageDto dto);
	public RegteacherDto detailRegteacher(String te_seq);
	public RegteacherDto countRegteacher(String te_seq);
	public boolean modifyRegteacher(String te_admit, String te_seq);
	public List<AttachFileDto> selectFiles(String file_gid);
	public int regteacherCount();
	
}
