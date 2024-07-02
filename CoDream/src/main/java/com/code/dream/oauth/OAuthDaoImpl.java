package com.code.dream.oauth;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.code.dream.dto.RegisterDto;

@Repository
public class OAuthDaoImpl implements IOAuthDao {

	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public Map<String, String> selectAllOAuth(String id) {
		return session.selectOne("oauth.selectAllOAuth", id);
	}

	@Override
	public String selectOneOAuth(String service, String value) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("service", service);
		map.put("value", value);
		return session.selectOne("oauth.selectOneOAuth",map);
	}

	@Override
	public String emailOAuth(String email) {
		return session.selectOne("oauth.emailOAuth", email);
	}

	@Override
	public boolean registOAuth(String service, String id, String oauth) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("service", service);
		map.put("id", id);
		map.put("oauth", oauth);
		return session.insert("oauth.registOAuth", map)>0;
	}

	@Override
	public boolean updateOAuth(String service, String id, String oauth) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("service", service);
		map.put("id", id);
		map.put("oauth", oauth);
		return session.insert("oauth.updateOAuth", map)>0;
	}

	@Override
	public RegisterDto loginOAuth(String service, String id, String oauth) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("service", service);
		map.put("id", id);
		map.put("oauth", oauth);
		return session.selectOne("oauth.loginOAuth",map);
	}

	@Override
	public String selectOAuthById(String service, String id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("service", service);
		map.put("id", id);
		return session.selectOne("oauth.selectOAuthById",map);
	}

}
