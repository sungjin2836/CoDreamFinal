package com.code.dream.regteacher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.code.dream.dto.AttachFileDto;
import com.code.dream.dto.PageDto;
import com.code.dream.dto.RegteacherDto;

@Repository
public class RegteacherDaoImpl implements IRegteacherDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public boolean insertRegteacher(RegteacherDto dto) {
		return session.insert("regteacher.insertRegteacher", dto)>0?true:false;
	}

	@Override
	public List<RegteacherDto> selectRegteacher(PageDto dto) {
		return session.selectList("regteacher.selectRegteacher", dto);
	}

	@Override
	public RegteacherDto detailRegteacher(String te_seq) {
		return session.selectOne("regteacher.detailRegteacher", te_seq);
	}

	@Override
	public boolean modifyRegteacher(String te_admit, String te_seq) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("te_admit", te_admit);
		map.put("te_seq", te_seq);
		return session.update("regteacher.modifyRegteacher", map)>0?true:false;
	}

	@Override
	public List<AttachFileDto> selectFiles(String file_gid) {
		return session.selectList("attach.searchgid",file_gid);
	}

	@Override
	public RegteacherDto countRegteacher(String userid) {
		return session.selectOne("regteacher.countRegteacher", userid);
	}
  
	@Override
	public int regteacherCount() {
		return session.selectOne("regteacher.regteacherCount");
	}

}
