package com.code.dream;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.code.dream.dto.RegisterDto;
import com.code.dream.oauth.IOAuthService;
import com.code.dream.oauth.SNSLogin;
import com.code.dream.oauth.SnsValue;
import com.code.dream.security.UserLoginAuthenticationProvider;
import com.code.dream.security.UserSecurityDto;


@Controller
public class OauthController {

	private static final Logger logger = LoggerFactory.getLogger(OauthController.class);

	@Autowired
	private SnsValue naverSns;

	@Autowired
	private SnsValue googleSns;

	@Autowired
	private SnsValue kakaoSns;

	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;

	@Autowired
	private OAuth2Parameters googleOAuth2Parameters;

	@Autowired
	private PersistentTokenBasedRememberMeServices rememberMeServices;

	@Autowired
	private IOAuthService service;

	@Autowired
	private UserLoginAuthenticationProvider authProvider;


	@RequestMapping(value = "/login/oauth2/naver", method = {RequestMethod.GET, RequestMethod.POST})
	public String naver(Model model, HttpServletRequest request) throws IOException, InterruptedException, ExecutionException {
		String code = request.getParameter("code");

		// 1. code를 이용해서 access_token 받기
		// 2. access_token을 이용해서 사용자 profile 정보 가져오기
		SNSLogin snsLogin = new SNSLogin(naverSns);
		String profile = snsLogin.getUserProfile(code);
		System.out.println("Profile>>" + profile);


		JSONParser jsonParse = new JSONParser();
		JSONObject json = null;
		try {
			json = (JSONObject) jsonParse.parse(profile);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject naver_account =  (JSONObject) json.get("response");
		String id = naver_account.get("id").toString(); // 사이트에서 준 고유 id
		String email = naver_account.get("email").toString();
		String mobile = naver_account.get("mobile").toString();
		String name = naver_account.get("name").toString();

		StringBuilder sb = new StringBuilder();
		char ch;
		int len = name.length();
		for (int i = 0; i < len; i++) {
			ch = name.charAt(i);
			if (ch == '\\' && name.charAt(i+1) == 'u') {
				sb.append((char) Integer.parseInt(name.substring(i+2, i+6), 16));
				i+=5;
				continue;
			}
			sb.append(ch);
		}

		name = sb.toString();
		System.out.println(name);
		// 3. DB에 해당 유저가 존재하는지 체크
		String emailId = service.emailOAuth(email); // 이메일로 검색해서 나온 id
		
		// 같은 이메일로 가입되어있는 경우.
		if(emailId != null) {
			// 연동처리
			if(service.selectAllOAuth(emailId)!=null) { // 이미 다른 소셜 로그인이 등록 되어있는 경우 update
				service.updateOAuth(naverSns.getService(), emailId, id);
			} else {									// 아닌 경우 신규 등록
				service.registOAuth(naverSns.getService(), emailId, id);
			}
		}
		
		String OAuthId = service.selectOneOAuth(naverSns.getService(), id); // OAuth로 검색해서 나온 id
		// 등록된 아이디가 없는 경우.
		if(OAuthId == null) {
			RegisterDto dto = new RegisterDto(null, name, null, mobile, email, null, null, null, null, null);
			model.addAttribute("site", naverSns.getService());
			model.addAttribute("uid", id);
			model.addAttribute("dto", dto);
			return "member/signup";
		}
		Authentication token = new UsernamePasswordAuthenticationToken(naverSns.getService()+"#"+emailId, id, null); 
		login(token, request);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/login/oauth2/google", method = {RequestMethod.GET, RequestMethod.POST})
	public String google(Model model, HttpServletRequest request) throws IOException, InterruptedException, ExecutionException {
		String code = request.getParameter("code");

		// 1. code를 이용해서 access_token 받기
		// 2. access_token을 이용해서 사용자 profile 정보 가져오기
		SNSLogin snsLogin = new SNSLogin(googleSns);
		String profile = snsLogin.getUserProfile(code);
		System.out.println("Profile>>" + profile);

		JSONParser jsonParse = new JSONParser();
		JSONObject json = null;
		try {
			json = (JSONObject) jsonParse.parse(profile);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		String id = json.get("id").toString();
		String email = json.get("email").toString();
		// 3. DB에 해당 유저가 존재하는지 체크 (googleid, naverid 컬럼 추가)
		String emailId = service.emailOAuth(email); // 이메일로 검색
		// 같은 이메일로 가입되어있는 경우.
		if(emailId != null) {
			// 연동처리
			if(service.selectAllOAuth(emailId)!=null) { // 이미 다른 소셜 로그인이 등록 되어있는 경우 update
				service.updateOAuth(googleSns.getService(), emailId, id);
			} else {									// 아닌 경우 신규 등록
				service.registOAuth(googleSns.getService(), emailId, id);
			}
		}
		
		String OAuthId = service.selectOneOAuth(googleSns.getService(), id); // OAuth로 검색해서 나온 id
		// 등록된 아이디가 없는 경우.
		if(OAuthId == null) {
			RegisterDto dto = new RegisterDto(null, null, null, null, email, null, null, null, null, null);
			model.addAttribute("site", googleSns.getService());
			model.addAttribute("uid", id);
			model.addAttribute("dto", dto);
			return "member/signup";
		}
		Authentication token = new UsernamePasswordAuthenticationToken(googleSns.getService()+"#"+emailId, id, null); 
		login(token, request);
	
		return "redirect:/";
	}

	@RequestMapping(value = "/login/oauth2/kakao", method = {RequestMethod.GET, RequestMethod.POST})
	public String kakao(Model model, HttpServletRequest request) throws IOException, InterruptedException, ExecutionException, URISyntaxException, ParseException {
		String code = request.getParameter("code");

		// 1. code를 이용해서 access_token 받기
		// 2. access_token을 이용해서 사용자 profile 정보 가져오기
		SNSLogin snsLogin = new SNSLogin(kakaoSns);
		String profile = snsLogin.getKakaoProfile(code);
		System.out.println("Profile>>" + profile);


		JSONParser jsonParse = new JSONParser();
		JSONObject json = null;
		try {
			json = (JSONObject) jsonParse.parse(profile);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String id = json.get("id").toString();
		JSONObject kakao_account =  (JSONObject) json.get("kakao_account");
		String email = "";
		if((boolean) kakao_account.get("has_email")) {
			email = (String) kakao_account.get("email");
		}


		// 3. DB에 해당 유저가 존재하는지 체크
		String emailId = service.emailOAuth(email); // 이메일로 검색
		// 같은 이메일로 가입되어있는 경우.
		if(emailId != null) {
			// 연동처리
			if(service.selectAllOAuth(emailId)!=null) { // 이미 다른 소셜 로그인이 등록 되어있는 경우 update
				service.updateOAuth(kakaoSns.getService(), emailId, id);
			} else {									// 아닌 경우 신규 등록
				service.registOAuth(kakaoSns.getService(), emailId, id);
			}
		}
		
		String OAuthId = service.selectOneOAuth(kakaoSns.getService(), id); // OAuth로 검색해서 나온 id
		// 등록된 아이디가 없는 경우.
		if(OAuthId == null) {
			RegisterDto dto = new RegisterDto(null, null, null, null, email, null, null, null, null, null);
			model.addAttribute("site", kakaoSns.getService());
			model.addAttribute("uid", id);
			model.addAttribute("dto", dto);
			return "member/signup";
		}
		Authentication token = new UsernamePasswordAuthenticationToken(kakaoSns.getService()+"#"+emailId, id, null); 
		login(token, request);

		return "redirect:/";
	}
	
	public void login(Authentication token, HttpServletRequest request) {
		Authentication authentication = authProvider.authenticate(token);
		System.out.println(authentication);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		HttpSession session = request.getSession(true);
		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
	}

	@RequestMapping(value = "/member/login")
	public String loginForm(Model model, HttpServletRequest request) {

		SNSLogin naverLogin = new SNSLogin(naverSns);
		model.addAttribute("naver_url", naverLogin.getAuthURL());
		SNSLogin kakaoLogin = new SNSLogin(kakaoSns);
		model.addAttribute("kakao_url", kakaoLogin.getAuthURL());

		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		String url = oauthOperations.buildAuthenticateUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
		model.addAttribute("google_url", url);

		String uri = request.getHeader("Referer");
		if (uri!=null && !uri.contains("/loginForm.do")) {
			request.getSession().setAttribute("prevPage",
					request.getHeader("Referer"));
		}
		return "member/login";
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			rememberMeServices.logout(request, response, auth);
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}


	//	@RequestMapping(value = "/login/oauth2/google")
	//	public String google(String idtoken, Model model) throws GeneralSecurityException, IOException {
	//		
	//		return "home";
	//	    
	//	}

}
