package com.code.dream.security;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.code.dream.dto.PageDto;
import com.code.dream.dto.RegisterDto;
import com.code.dream.oauth.IOAuthDao;
import com.code.dream.oauth.IOAuthService;

@Service
@Transactional
public class UserSecurityServiceImpl implements IUserSecurityService  {

	@Autowired
	private IUserSecurityDao dao;
	
	@Autowired
	private IOAuthDao auth;
	
	@Autowired
	private PasswordEncoder passwordEncoding;
	
	@Override
	public UserDetails loadUserByUsername(String inputUserId) {

		// 최종적으로 리턴해야할 객체
		UserSecurityDto userDetails = new UserSecurityDto();
		
		String naver = "naver#";
		String google = "google#";
		String kakao = "kakao#";
		String service = "";
		RegisterDto userInfo = null;
		//OAuth인지 확인.
		// 사용자 정보 select
		if(StringUtils.substring(inputUserId, 0, naver.length()).equals(naver)) {
			inputUserId = inputUserId.substring(naver.length());
			service = "naver";
			userDetails.setService(service);
			String oauth = auth.selectOAuthById(service, inputUserId);
			System.out.println(service + inputUserId + oauth);
			userInfo = loadUserByOAuth(service, inputUserId, oauth);
			userInfo.setPassword(passwordEncoding.encode(oauth));
		} else if(StringUtils.substring(inputUserId, 0, google.length()).equals(google)) {
			service = "google";
			userDetails.setService(service);
			inputUserId = inputUserId.substring(google.length());
			String oauth = auth.selectOAuthById(service, inputUserId);
			userInfo = loadUserByOAuth(service, inputUserId, oauth);
			userInfo.setPassword(passwordEncoding.encode(oauth));
		} else if(StringUtils.substring(inputUserId, 0, kakao.length()).equals(kakao)) {
			service = "kakao";
			userDetails.setService(service);
			inputUserId = inputUserId.substring(kakao.length());
			String oauth = auth.selectOAuthById(service, inputUserId);
			userInfo = loadUserByOAuth(service, inputUserId, oauth);
			userInfo.setPassword(passwordEncoding.encode(oauth));
		} else {
			service="normal";
			userDetails.setService(service);
			userInfo = dao.login(inputUserId);
		}
		
		// 사용자 정보 없으면 null 처리
		if (userInfo == null) {
			return null;

		// 사용자 정보 있을 경우 로직 전개 (userDetails에 데이터 넣기)
		} else {
			userDetails.setUsername(userInfo.getId());
			userDetails.setPassword(userInfo.getPassword());

			// 사용자 권한 select해서 받아온 List<String> 객체 주입
			List<String> auth = dao.selectRole(inputUserId);
			userDetails.setDto(userInfo);
			userDetails.setAuthorities(auth);
		}

		return userDetails;
	}

	@Override
	public RegisterDto loadUserByOAuth(String service, String inputUserId, String oauth) {
		RegisterDto dto = null;
		dto = auth.loginOAuth(service, inputUserId, oauth);
		
		return dto;
	}
	
	@Override
	public boolean regist(RegisterDto dto) {
		String enPassword = passwordEncoding.encode(dto.getPassword());
		dto.setPassword(enPassword);
		return dao.registUser(dto)&&dao.addRole(dto.getId(), "ROLE_USER");
	}
	
	public boolean idChk(String id) {
		return dao.idChk(id);
	}

	@Override
	public List<RegisterDto> selectUser() {
		return dao.selectUser();
	}

	@Override
	public boolean deleteUser(String id) {
		return dao.deleteUser(id);
	}

	@Override
	public boolean modifyUser(RegisterDto dto) {
		String enPassword = passwordEncoding.encode(dto.getPassword());
		dto.setPassword(enPassword);
		return dao.modifyUser(dto);
	}

	@Override
	public boolean deleteRole(String id, String role) {
		return dao.deleteRole(id, role);
	}

	@Override
	public List<UserSecurityDto> selectUserList(PageDto dto) {
		return dao.selectUserList(dto);
	}

	@Override
	public List<String> selectRole(String id) {
		return dao.selectRole(id);
	}

	@Override
	public boolean addRole(String id, String role) {
		return dao.addRole(id, role);
	}

	@Override
	public RegisterDto selectInfo(String id) {
		RegisterDto dto = dao.login(id);
		dto.setPassword(null);
		return dto;
	}

	@Override
	public int userCount() {
		return dao.userCount();
	}


}
