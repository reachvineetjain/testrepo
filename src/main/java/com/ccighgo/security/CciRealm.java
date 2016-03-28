package com.ccighgo.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccighgo.db.entities.Login;
import com.ccighgo.db.entities.LoginUserType;
import com.ccighgo.jpa.repositories.LoginRepository;
import com.ccighgo.utils.CCIConstants;

@Component
public class CciRealm extends AuthorizingRealm {

   @Autowired private LoginRepository loginRepository;

   public CciRealm() {
      setName("CciRealm");
   }

   protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
      CciToken token = (CciToken) authcToken;
      LOGGER.debug("received signature: {}", token.getCredentials());
      try {
         Login user = loginRepository.findByLoginName(token.getPrincipal().toLowerCase());
         if (user != null && user.getLoginName().toLowerCase().equals(token.getPrincipal().toLowerCase())) {
            return new SimpleAuthenticationInfo(user.getLoginName(), user.getPassword(), getName());
         }
      } catch (AuthenticationException e) {
         LOGGER.error("Authentication error", e);
         throw e;
      } catch (Exception e) {
         LOGGER.error("Unexpected error", e);
         throw new AuthenticationException("Unexpected error", e);
      }
      return null;
   }

   /**
    * Here is where we pick up the role of the user from the repository and ask
    * Shiro to carry it for us. This role of the user can be requested any where
    * in the application like
    * <code> SecurityUtils.getSubject().hasRole() </code>
    */
   protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
      if (principals.fromRealm(getName()) != null && principals.fromRealm(getName()).size() > 0) {
         String loginId = (String) principals.fromRealm(getName()).iterator().next();
         Login user = loginRepository.findByLoginName(loginId);
         if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            if (user.getLoginUserTypes() != null) {
               if (user.getLoginUserTypes().size() > 1)
                  for (LoginUserType luType : user.getLoginUserTypes()) {
                     if (luType.getDefaultUserType() == CCIConstants.ACTIVE) {
                        info.addRole(luType.getUserType().getUserTypeCode());
                     }
                  }
               return info;
            }
         }
      }
      return null;
   }

   @Override
   public boolean supports(AuthenticationToken token) {
      return token != null && token.getClass().equals(CciToken.class);
   }

   private static final Logger LOGGER = LoggerFactory.getLogger(CciRealm.class);
}
