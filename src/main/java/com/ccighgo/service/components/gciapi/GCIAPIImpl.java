package com.ccighgo.service.components.gciapi;

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

@Component
public class GCIAPIImpl implements IGCIAPI {

   @Override
   public GCIAPI_Response addUser(GCIAPI_AddUser_Param userParam) {
      // TODO Auto-generated method stub
      return null;
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
