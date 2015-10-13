package com.ccighgo.security;

import java.nio.charset.Charset;
import java.util.Base64;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccighgo.utils.PasswordUtil;

public class CciAuthenticationFilter extends AuthenticatingFilter {

   @Override
   protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
      LOGGER.trace("enter createToken");
      String authorization = ((HttpServletRequest) request).getHeader("Authorization");
      String base64Credentials = authorization.substring("Basic".length()).trim();
      String credentials = new String(Base64.getDecoder().decode(base64Credentials), Charset.forName("UTF-8"));
      String[] values = credentials.split(":", 2);
      validateAuthData(values);
      CciToken token = new CciToken(((HttpServletRequest) request).getRequestURI(), values[0], PasswordUtil.hashKey(values[1]));
      LOGGER.trace("exit createToken");
      return token;
   }

   private void validateAuthData(String[] data) throws Exception {
      LOGGER.trace("enter validateAuthData");
      if (data[0] == null || data[0].isEmpty() || data[1] == null || data[1].isEmpty()) {
         throw new SecurityException("Request does not contain required authentication data");
      }
      LOGGER.trace("exit validateAuthData");
   }
   
   @Override
   protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
       HttpServletRequest httpRequest = WebUtils.toHttp(request);
       if ("OPTIONS".equals(httpRequest.getMethod())) {
           return true;
       }
       return super.isAccessAllowed(request, response, mappedValue);
   }

   @Override
   protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
      boolean result = false;
      try {
         result = executeLogin(request, response);
      } catch (Exception e) {
         LOGGER.error("Error in Login", e);
      }
      if (!result) {
         ((HttpServletResponse) response).sendError(401, "System cannot authenticate provided credentials, please check if entered username and password are correct");
      } else {
         LOGGER.debug("Login success");
      }
      return result;
   }

   @Override
   protected boolean isRememberMe(ServletRequest request) {
      return false;
   }

   @Override
   protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
      ((HttpServletResponse) response).setHeader("cache-control", "no-cache");
      return super.onLoginSuccess(token, subject, request, response);
   }

   private static final Logger LOGGER = LoggerFactory.getLogger(CciAuthenticationFilter.class);
}
