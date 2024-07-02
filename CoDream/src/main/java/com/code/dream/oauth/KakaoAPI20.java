package com.code.dream.oauth;

import com.github.scribejava.core.builder.api.DefaultApi20;

public class KakaoAPI20 extends DefaultApi20 implements SnsUrls {
	
	private static KakaoAPI20 _instance;
	
	private KakaoAPI20() {}
	
	public static KakaoAPI20 getInstance() {
		if(_instance == null) {
			_instance = new KakaoAPI20();
		}
		return _instance;
	}

	@Override
	public String getAccessTokenEndpoint() {
		return KAKAO_ACCESS_TOKEN;
	}

	@Override
	protected String getAuthorizationBaseUrl() {
		return KAKAO_AUTH;
	}

}
