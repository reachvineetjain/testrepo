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
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public GCIAPI_Response loginUser(GCIAPI_LoginUser_Param loginUserParam) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public GCIAPI_Response setKey(GCIAPI_SetKey_Param setKeyParam) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public GCIAPI_Response setProgram(GCIAPI_SetProgram_Param setProgramParam) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public GCIAPI_Response setUser(GCIAPI_SetUser_Param userParam) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public GCIAPI_Response setPassword(GCIAPI_SetPassword_Param setPasswordParam) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public GCIAPI_Response getVolunteerHours(GCIAPI_GetVolunteerHours_Param volunteerHours_Param) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public GCIAPI_Response getAllVolunteerHours(GCIAPI_GetAllVolunteerHours_Param allVolunteerHours_Param) {
      // TODO Auto-generated method stub
      return null;
   }

}
