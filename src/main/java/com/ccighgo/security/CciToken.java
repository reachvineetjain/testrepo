package com.ccighgo.security;

import org.apache.shiro.authc.AuthenticationToken;

public class CciToken implements AuthenticationToken {

   private static final long serialVersionUID = -2798790281885602492L;

   private String uri;
   private String login;
   private String password;

   public CciToken(String uri, String login, String password) {
      this.uri = uri;
      this.login = login;
      this.password = password;
   }

   public String getUri() {
      return uri;
   }

   public void setUri(String uri) {
      this.uri = uri;
   }

   @Override
   public String getCredentials() {
      return password;
   }

   @Override
   public String getPrincipal() {
      return login;
   }

}
