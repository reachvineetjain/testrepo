package com.ccighgo.security;

import org.apache.shiro.authc.AuthenticationToken;

public class HMACSignatureToken implements AuthenticationToken {

	private static final long serialVersionUID = 3440452521712918464L;
	
	private String uri;
	private String loginId;
	private String timeStamp;
	private String nonce;
	private String oauthSignature;
	private String oauthVersion;
	
	public HMACSignatureToken(String uri, String loginId, String timeStamp, String nonce, String oauthSignature, String oauthVersion){
		this.uri = uri;
		this.loginId = loginId;
		this.timeStamp = timeStamp;
		this.nonce = nonce;
		this.oauthSignature = oauthSignature;
		this.oauthVersion = oauthVersion;
	}
	
	public String getUri() {
		return uri;
	}

	public String getLoginId() {
		return loginId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public String getNonce() {
		return nonce;
	}

	public String getOauthSignature() {
		return oauthSignature;
	}

	public String getOauthVersion() {
		return oauthVersion;
	}
	
    public Object getCredentials() {
        return oauthSignature;
    }

    public Object getPrincipal() {
        return loginId;
    }

}
