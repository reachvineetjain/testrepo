package com.ccighgo.service.components.gciapi;

import org.springframework.stereotype.Service;

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

@Service
public interface IGCIAPI {

   GCIAPI_Response addUser(GCIAPI_AddUser_Param userParam);

   GCIAPI_Response userExists(GCIAPI_UserExist_Param userExistParam);

   GCIAPI_Response loginUser(GCIAPI_LoginUser_Param loginUserParam);

   GCIAPI_Response setKey(GCIAPI_SetKey_Param setKeyParam);

   GCIAPI_Response setProgram(GCIAPI_SetProgram_Param setProgramParam);

   GCIAPI_Response setUser(GCIAPI_SetUser_Param userParam);

   GCIAPI_Response setPassword(GCIAPI_SetPassword_Param setPasswordParam);

   GCIAPI_Response getVolunteerHours(GCIAPI_GetVolunteerHours_Param volunteerHours_Param);

   GCIAPI_Response getAllVolunteerHours(GCIAPI_GetAllVolunteerHours_Param allVolunteerHours_Param);

}
