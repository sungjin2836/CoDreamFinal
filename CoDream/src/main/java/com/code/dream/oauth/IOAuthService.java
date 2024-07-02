package com.code.dream.oauth;

import java.util.Map;

import com.code.dream.dto.RegisterDto;

public interface IOAuthService {

	public Map<String, String> selectAllOAuth(String id);
	public String selectOneOAuth(String service, String value);
	public String emailOAuth(String email);
	public boolean registOAuth(String service, String id, String oauth);
	public boolean updateOAuth(String service, String id, String oauth);
	public String selectOAuthById(String service, String id);
	public RegisterDto loginOAuth(String service, String id, String oauth);
}
