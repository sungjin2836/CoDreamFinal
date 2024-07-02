package com.code.dream.oauth;

import org.apache.commons.lang3.StringUtils;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.api.DefaultApi20;

public class SnsValue implements SnsUrls {

	private String service;
	private String clientId;
	private String clientSecret;
	private String redirectUrl;
	private DefaultApi20 api20Instance;
	private String profileUrl;
	
	public SnsValue(String service, String clientId, String clientSecret, String redirectUrl) {
		this.service = service;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.redirectUrl = redirectUrl;
		if(StringUtils.equalsIgnoreCase(service, "naver")) {
			this.api20Instance = NaverAPI20.getInstance();
			this.profileUrl = NAVER_PROFILE_URL;
		} else if(StringUtils.equalsIgnoreCase(service, "kakao")) {
			this.api20Instance = KakaoAPI20.getInstance();
			this.profileUrl = KAKAO_PROFILE_URL;
		} else if(StringUtils.equalsIgnoreCase(service, "google")) {
			this.api20Instance = GoogleApi20.instance();
			this.profileUrl = GOOGLE_PROFILE_URL;
		}
	}
	
	public SnsValue() {
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public DefaultApi20 getApi20Instance() {
		return api20Instance;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	@Override
	public String toString() {
		return "SNS [service=" + service + ", clientId=" + clientId + ", clientSecret=" + clientSecret + ", redirectUrl="
				+ redirectUrl + "]";
	}

	
	
}

