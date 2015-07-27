package com.ccighgo.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExcurtOauthRealm extends AuthorizingRealm {

	/*@Autowired
	private  UsrRepository userRepository;
	
	@Autowired private EncryptionService encryptionService;*/

    public ExcurtOauthRealm() {
        setName("ExcurtOauthRealm");
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {/*
    	HMACSignatureToken token = (HMACSignatureToken) authcToken;
		LOGGER.debug("received signature: {}", token.getOauthSignature());
		try{
	        Usr user = userRepository.findUsrByLoginName(token.getLoginId());
	        if( user != null && user.getLoginName().equals(token.getLoginId())) {
		        if(user.getActivated()==0){
		        	throw new AccountException("User account not activated");
		        }
		        if(user.getArchived()==1){
		        	throw new AccountException("User account cancelled");
		        }
	            return new SimpleAuthenticationInfo(user.getLoginName(), makeSignature(token.getUri(), token.getLoginId(), token.getTimeStamp(), token.getNonce(), user.getPassword()), getName());
	        }
		}
		catch(AuthenticationException e){
			LOGGER.error("Authentication error", e);
			throw e;
		}
		catch(Exception e){
			LOGGER.error("Unexpected error", e);
			throw new AuthenticationException("Unexpected error", e);
		}
        return null;
    */return null;}

    
    private String makeSignature(String uri, String login, String timestamp, String nonce, String password) {/*
    	String source = uri + "&" + login + "&" + timestamp + "&" + nonce;
    	try {
    		String hashString = encryptionService.encryptAndBase64Encode(source, password);
			String hashEncoded = java.net.URLEncoder.encode(hashString, "UTF-8");
			LOGGER.debug("original signature: {}", hashEncoded);
			return hashEncoded;
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Error while creating signature", e);
			throw new AuthenticationException(e);
		} catch (InvalidKeyException e) {
			LOGGER.error("Error while creating signature", e);
			throw new AuthenticationException(e);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("Error while creating signature", e);
			throw new AuthenticationException(e);
		}
    */return null;}
    
    /**
     * Here is where we pick up the role of the user from the repository
     * and ask Shiro to carry it for us. This role of the user can be requested any where in the application 
     * like <code> SecurityUtils.getSubject().hasRole() </code>
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {/*
        String loginId = (String) principals.fromRealm(getName()).iterator().next();
        Usr user = userRepository.findUsrByLoginName(loginId);
        if( user != null ) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            if(user.getUsrType() != null)
            	info.addRole(user.getUsrType().getName());
            return info;
        } else {
            return null;
        }
    */return null;}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token!=null && token.getClass().equals(HMACSignatureToken.class);
	}
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcurtOauthRealm.class); 
}