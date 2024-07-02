package com.code.dream.security;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.code.dream.dto.PageDto;
import com.code.dream.dto.RegisterDto;

public interface IUserSecurityService extends UserDetailsService  {

	public RegisterDto loadUserByOAuth(String service,String inputUserId, String oauth);
	public boolean regist(RegisterDto dto);
	public List<RegisterDto> selectUser();
	public List<UserSecurityDto> selectUserList(PageDto dto);
	public boolean deleteUser(String id);
	public boolean modifyUser(RegisterDto dto);
	public List<String> selectRole(String id);
	public boolean addRole(String id, String role);
	public boolean deleteRole(String id, String role);
	public boolean idChk(String id);
	public RegisterDto selectInfo(String id);
	public int userCount();
}
