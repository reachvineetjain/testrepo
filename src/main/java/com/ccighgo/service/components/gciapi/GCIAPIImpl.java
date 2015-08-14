package com.ccighgo.service.components.gciapi;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ccighgo.service.transport.seasons.beans.gciapi.GCIAPI_AddUser_Param;
import com.ccighgo.service.transport.seasons.beans.gciapi.GCIAPI_GetAllVolunteerHours_Param;
import com.ccighgo.service.transport.seasons.beans.gciapi.GCIAPI_GetVolunteerHours_Param;
import com.ccighgo.service.transport.seasons.beans.gciapi.GCIAPI_LoginUser_Param;
import com.ccighgo.service.transport.seasons.beans.gciapi.GCIAPI_Response;
import com.ccighgo.service.transport.seasons.beans.gciapi.GCIAPI_SetKey_Param;
import com.ccighgo.service.transport.seasons.beans.gciapi.GCIAPI_SetPassword_Param;
import com.ccighgo.service.transport.seasons.beans.gciapi.GCIAPI_SetProgram_Param;
import com.ccighgo.service.transport.seasons.beans.gciapi.GCIAPI_SetUser_Param;
import com.ccighgo.service.transport.seasons.beans.gciapi.GCIAPI_UserExist_Param;
import com.ccighgo.utils.CCIConstants;
import com.ccighgo.utils.ExceptionUtil;

@Component
public class GCIAPIImpl implements IGCIAPI {

   private static final Logger LOGGER = Logger.getLogger(GCIAPIImpl.class);

   @Override
   public GCIAPI_Response addUser(GCIAPI_AddUser_Param userParam) {
      try {
         StringBuilder url = new StringBuilder();
         url.append(GCIAPI_Util.API_BASE).append("?");
         url.append("display_name").append(CCIConstants.EQUAL).append(userParam.getDisplay_name()).append(CCIConstants.BITWISE_AND);
         url.append("email").append(CCIConstants.EQUAL).append(userParam.getEmail()).append(CCIConstants.BITWISE_AND);
         url.append("first_name").append(CCIConstants.EQUAL).append(userParam.getFirst_name()).append(CCIConstants.BITWISE_AND);
         url.append("last_name").append(CCIConstants.EQUAL).append(userParam.getLast_name()).append(CCIConstants.BITWISE_AND);
         url.append("option").append(CCIConstants.EQUAL).append(userParam.getOption()).append(CCIConstants.BITWISE_AND);
         url.append("password").append(CCIConstants.EQUAL).append(userParam.getPassword()).append(CCIConstants.BITWISE_AND);
         url.append("program").append(CCIConstants.EQUAL).append(userParam.getProgram()).append(CCIConstants.BITWISE_AND);
         url.append("token").append(CCIConstants.EQUAL).append(userParam.getToken()).append(CCIConstants.BITWISE_AND);
         url.append("u_key").append(CCIConstants.EQUAL).append(userParam.getU_key()).append(CCIConstants.BITWISE_AND);
         url.append("username").append(CCIConstants.EQUAL).append(userParam.getUsername());
         LOGGER.info("URL : " + url.toString());
         return GCIAPI_Util.parseGCIAPIResult(GCIAPI_Util.executeURL(url.toString()));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }

   }

   @Override
   public GCIAPI_Response userExists(GCIAPI_UserExist_Param userExistParam) {
      try {
         StringBuilder url = new StringBuilder();
         url.append(GCIAPI_Util.API_BASE).append("?");
         url.append("token").append(CCIConstants.EQUAL).append(userExistParam.getToken()).append(CCIConstants.BITWISE_AND);
         url.append("u_key").append(CCIConstants.EQUAL).append(userExistParam.getU_key()).append(CCIConstants.BITWISE_AND);
         url.append("option").append(CCIConstants.EQUAL).append(userExistParam.getOption());
         LOGGER.info("URL : " + url.toString());
         return GCIAPI_Util.parseGCIAPIResult(GCIAPI_Util.executeURL(url.toString()));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Override
   public GCIAPI_Response loginUser(GCIAPI_LoginUser_Param loginUserParam) {
      try {
         StringBuilder url = new StringBuilder();
         url.append(GCIAPI_Util.API_BASE).append("?");
         url.append("token").append(CCIConstants.EQUAL).append(loginUserParam.getToken()).append(CCIConstants.BITWISE_AND);
         url.append("u_key").append(CCIConstants.EQUAL).append(loginUserParam.getU_key()).append(CCIConstants.BITWISE_AND);
         url.append("option").append(CCIConstants.EQUAL).append(loginUserParam.getOption()).append(CCIConstants.BITWISE_AND);
         url.append("password").append(CCIConstants.EQUAL).append(loginUserParam.getPassword());

         LOGGER.info("URL : " + url.toString());
         return GCIAPI_Util.parseGCIAPIResult(GCIAPI_Util.executeURL(url.toString()));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Override
   public GCIAPI_Response setKey(GCIAPI_SetKey_Param setKeyParam) {
      try {
         StringBuilder url = new StringBuilder();
         url.append(GCIAPI_Util.API_BASE).append("?");
         url.append("token").append(CCIConstants.EQUAL).append(setKeyParam.getToken()).append(CCIConstants.BITWISE_AND);
         url.append("u_key_new").append(CCIConstants.EQUAL).append(setKeyParam.getU_key_new()).append(CCIConstants.BITWISE_AND);
         url.append("option").append(CCIConstants.EQUAL).append(setKeyParam.getOption()).append(CCIConstants.BITWISE_AND);
         url.append("password").append(CCIConstants.EQUAL).append(setKeyParam.getPassword()).append(CCIConstants.BITWISE_AND);
         url.append("username").append(CCIConstants.EQUAL).append(setKeyParam.getUsername());

         LOGGER.info("URL : " + url.toString());
         return GCIAPI_Util.parseGCIAPIResult(GCIAPI_Util.executeURL(url.toString()));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Override
   public GCIAPI_Response setProgram(GCIAPI_SetProgram_Param setProgramParam) {
      try {
         StringBuilder url = new StringBuilder();
         url.append(GCIAPI_Util.API_BASE).append("?");
         url.append("token").append(CCIConstants.EQUAL).append(setProgramParam.getToken()).append(CCIConstants.BITWISE_AND);
         url.append("program").append(CCIConstants.EQUAL).append(setProgramParam.getProgram()).append(CCIConstants.BITWISE_AND);
         url.append("option").append(CCIConstants.EQUAL).append(setProgramParam.getOption()).append(CCIConstants.BITWISE_AND);
         url.append("u_key").append(CCIConstants.EQUAL).append(setProgramParam.getU_key());

         LOGGER.info("URL : " + url.toString());
         return GCIAPI_Util.parseGCIAPIResult(GCIAPI_Util.executeURL(url.toString()));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Override
   public GCIAPI_Response setUser(GCIAPI_SetUser_Param userParam) {
      try {
         StringBuilder url = new StringBuilder();
         url.append(GCIAPI_Util.API_BASE).append("?");
         url.append("token").append(CCIConstants.EQUAL).append(userParam.getToken()).append(CCIConstants.BITWISE_AND);
         url.append("username").append(CCIConstants.EQUAL).append(userParam.getUsername()).append(CCIConstants.BITWISE_AND);
         url.append("option").append(CCIConstants.EQUAL).append(userParam.getOption()).append(CCIConstants.BITWISE_AND);
         url.append("u_key").append(CCIConstants.EQUAL).append(userParam.getU_key());

         LOGGER.info("URL : " + url.toString());
         return GCIAPI_Util.parseGCIAPIResult(GCIAPI_Util.executeURL(url.toString()));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Override
   public GCIAPI_Response setPassword(GCIAPI_SetPassword_Param setPasswordParam) {
      try {
         StringBuilder url = new StringBuilder();
         url.append(GCIAPI_Util.API_BASE).append("?");
         url.append("token").append(CCIConstants.EQUAL).append(setPasswordParam.getToken()).append(CCIConstants.BITWISE_AND);
         url.append("password").append(CCIConstants.EQUAL).append(setPasswordParam.getPassword()).append(CCIConstants.BITWISE_AND);
         url.append("option").append(CCIConstants.EQUAL).append(setPasswordParam.getOption()).append(CCIConstants.BITWISE_AND);
         url.append("u_key").append(CCIConstants.EQUAL).append(setPasswordParam.getU_key());

         LOGGER.info("URL : " + url.toString());
         return GCIAPI_Util.parseGCIAPIResult(GCIAPI_Util.executeURL(url.toString()));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Override
   public GCIAPI_Response getVolunteerHours(GCIAPI_GetVolunteerHours_Param volunteerHours_Param) {
      try {
         StringBuilder url = new StringBuilder();
         url.append(GCIAPI_Util.API_BASE).append("?");
         url.append("token").append(CCIConstants.EQUAL).append(volunteerHours_Param.getToken()).append(CCIConstants.BITWISE_AND);
         url.append("option").append(CCIConstants.EQUAL).append(volunteerHours_Param.getOption()).append(CCIConstants.BITWISE_AND);
         url.append("u_key").append(CCIConstants.EQUAL).append(volunteerHours_Param.getU_key());

         LOGGER.info("URL : " + url.toString());
         return GCIAPI_Util.parseGCIAPIResult(GCIAPI_Util.executeURL(url.toString()));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Override
   public GCIAPI_Response getAllVolunteerHours(GCIAPI_GetAllVolunteerHours_Param allVolunteerHours_Param) {
      try {
         StringBuilder url = new StringBuilder();
         url.append(GCIAPI_Util.API_BASE).append("?");
         url.append("token").append(CCIConstants.EQUAL).append(allVolunteerHours_Param.getToken()).append(CCIConstants.BITWISE_AND);
         url.append("datetime").append(CCIConstants.EQUAL).append(allVolunteerHours_Param.getDatetime()).append(CCIConstants.BITWISE_AND);
         url.append("option").append(CCIConstants.EQUAL).append(allVolunteerHours_Param.getOption()).append(CCIConstants.BITWISE_AND);
         url.append("program").append(CCIConstants.EQUAL).append(allVolunteerHours_Param.getProgram());

         LOGGER.info("URL : " + url.toString());
         return GCIAPI_Util.parseGCIAPIResult(GCIAPI_Util.executeURL(url.toString()));
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

}
