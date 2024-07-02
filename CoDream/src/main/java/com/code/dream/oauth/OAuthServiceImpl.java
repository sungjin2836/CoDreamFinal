package com.code.dream.oauth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.dream.dto.RegisterDto;

@Service
public class OAuthServiceImpl implements IOAuthService {

	@Autowired
	private IOAuthDao dao;
	
	@Override
	public Map<String, String> selectAllOAuth(String id) {
		return dao.selectAllOAuth(id);
	}

	@Override
	public String selectOneOAuth(String service, String value) {
		return dao.selectOneOAuth(service, value);
	}

	@Override
	public String emailOAuth(String email) {
		return dao.emailOAuth(email);
	}

	@Override
	public boolean registOAuth(String service, String id, String oauth) {
		return dao.registOAuth(service, id, oauth);
	}

	@Override
	public boolean updateOAuth(String service, String id, String oauth) {
		return dao.updateOAuth(service, id, oauth);
	}

	@Override
	public RegisterDto loginOAuth(String service, String id, String oauth) {
		return dao.loginOAuth(service, id, oauth);
	}

	@Override
	public String selectOAuthById(String service, String id) {
		return dao.selectOAuthById(service, id);
	}

}
