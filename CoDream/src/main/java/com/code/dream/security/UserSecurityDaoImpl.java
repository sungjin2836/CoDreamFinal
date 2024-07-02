package com.code.dream.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.code.dream.dto.PageDto;
import com.code.dream.dto.RegisterDto;

@Repository
public class UserSecurityDaoImpl implements IUserSecurityDao {

	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public RegisterDto login(String id) {
		return session.selectOne("register.login", id);
	}

	@Override
	public List<String> selectRole(String id) {
		return session.selectList("register.selectRole",id);
	}

	@Override
	public boolean registUser(RegisterDto dto) {
		return session.insert("register.registUser", dto)>0?true:false;
	}
	
	@Override
	public boolean idChk(String id) {
		int n = session.selectOne("register.idDuplicateCheck",id);
		return n>0?true:false;
	}

	@Override
	public List<RegisterDto> selectUser() {
		return session.selectList("register.selectUser");
	}

	@Override
	public boolean deleteUser(String id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		return session.update("register.deleteUser",map)>0?true:false;
	}

	@Override
	public boolean modifyUser(RegisterDto dto) {
		return session.update("register.modifyUser",dto)>0?true:false;
	}

	@Override
	public boolean addRole(String id, String role) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("role", role);
		return session.insert("register.addRole",map)>0?true:false;
	}

	@Override
	public boolean deleteRole(String id, String role) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("role", role);
		return session.delete("register.deleteRole",map)>0?true:false;
	}

	@Override
	public List<UserSecurityDto> selectUserList(PageDto dto) {
		List<UserSecurityDto> list = session.selectList("register.selectUserList", dto);
		return list;
	}

	@Override
	public int userCount() {
		return session.selectOne("register.userCount");
	}
}
