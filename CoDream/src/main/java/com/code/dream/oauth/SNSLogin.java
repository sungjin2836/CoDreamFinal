package com.code.dream.oauth;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;


public class SNSLogin {

	private OAuth20Service oauthService;
	private String profileUrl;
	private SnsValue sns;
	
	public SNSLogin(SnsValue sns) {
		this.sns = sns;
		this.profileUrl = sns.getProfileUrl();
		if(sns.getService().equals("naver")) {
			this.oauthService = new ServiceBuilder(sns.getClientId())
					.apiSecret(sns.getClientSecret())
					.callback(sns.getRedirectUrl())
					.scope("profile")
					.build(sns.getApi20Instance());
		} else if(sns.getService().equals("kakao")) {
			this.oauthService = new ServiceBuilder(sns.getClientId())
					.apiSecret(sns.getClientSecret())
					.callback(sns.getRedirectUrl())
					.scope("account_email")
					.build(sns.getApi20Instance());
		} else if(sns.getService().equals("google")) {
			this.oauthService = new ServiceBuilder(sns.getClientId())
					.apiSecret(sns.getClientSecret())
					.callback(sns.getRedirectUrl())
					.scope("email")
					.build(sns.getApi20Instance());
		}
		
	}

	public String getAuthURL() {
		return this.oauthService.getAuthorizationUrl();
	}

	public String getUserProfile(String code) throws IOException, InterruptedException, ExecutionException {
		System.out.println(code);
		OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
		OAuthRequest request = new OAuthRequest(Verb.GET, this.profileUrl);
		oauthService.signRequest(accessToken, request);
		
		Response responce = oauthService.execute(request);
		
		return responce.getBody();
	}

	
	public String getKakaoProfile(String code) throws IOException, InterruptedException, ExecutionException, URISyntaxException, ParseException {
		System.out.println(code);
		String[] headers = {"Content-Type","application/x-www-form-urlencoded"};
		String url = sns.getApi20Instance().getAccessTokenEndpoint();
		url += "&code=" + code;
		url += "&client_id=" + sns.getClientId();
		url += "&client_secret=" + sns.getClientSecret();
		HttpClient client = HttpClient.newBuilder().version(Version.HTTP_1_1).build();
        String token = client.sendAsync(
            HttpRequest.newBuilder(
                new URI(url)).GET().headers(headers).build(),  //GET방식 요청
                HttpResponse.BodyHandlers.ofString()  //응답은 문자형태
            ).thenApply(HttpResponse::body)  //thenApply메소드로 응답body값만 받기
            .get();  //get메소드로 body값의 문자를 확인
        
        JSONParser jsonParse = new JSONParser();
		JSONObject json = (JSONObject) jsonParse.parse(token);
        OAuth2AccessToken accessToken = new OAuth2AccessToken(
        						json.get("access_token").toString(),
        						json.get("token_type").toString(),
        						Integer.parseInt(json.get("expires_in").toString()),
        						json.get("refresh_token").toString(),
        						json.get("scope").toString(),
        						null
        					);
		System.out.println(accessToken.getAccessToken());
		OAuthRequest request = new OAuthRequest(Verb.GET, this.profileUrl);
		oauthService.signRequest(accessToken, request);
		
		Response responce = oauthService.execute(request);
		
		return responce.getBody();
	}
	
}
