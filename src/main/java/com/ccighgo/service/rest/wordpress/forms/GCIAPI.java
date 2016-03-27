package com.ccighgo.service.rest.wordpress.forms;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.gciapi.IGCIAPI;
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

@Path("/gciapi/")
@Produces("application/json")
@Consumes("application/json")
public class GCIAPI {

   public static final Logger LOGGER = Logger.getLogger(GCIAPI.class);
   @Autowired
   IGCIAPI gciApi;

   @GET
   @Path("ping/{pingValue}")
   @Produces("application/json")
   public String ping(@PathParam("pingValue") String pingValue) {
      return pingValue;
   }

   @POST
   @Path("addUser")
   @Consumes("application/json")
   @Produces("application/json")
   public GCIAPI_Response addUser(GCIAPI_AddUser_Param userParam) {
      return gciApi.addUser(userParam);
   }

   @POST
   @Path("userExists")
   @Consumes("application/json")
   public GCIAPI_Response userExists(GCIAPI_UserExist_Param userExistParam) {
      return gciApi.userExists(userExistParam);
   }

   @POST
   @Path("loginUser")
   @Consumes("application/json")
   public GCIAPI_Response loginUser(GCIAPI_LoginUser_Param loginUserParam) {
      return gciApi.loginUser(loginUserParam);
   }

   @POST
   @Path("setKey")
   @Consumes("application/json")
   public GCIAPI_Response setKey(GCIAPI_SetKey_Param setKeyParam) {
      return gciApi.setKey(setKeyParam);
   }

   @POST
   @Path("setProgram")
   @Consumes("application/json")
   public GCIAPI_Response setProgram(GCIAPI_SetProgram_Param setProgramParam) {
      return gciApi.setProgram(setProgramParam);
   }

   @POST
   @Path("setUser")
   @Consumes("application/json")
   public GCIAPI_Response setUser(GCIAPI_SetUser_Param userParam) {
      return gciApi.setUser(userParam);
   }

   @POST
   @Path("setPassword")
   @Consumes("application/json")
   public GCIAPI_Response setPassword(GCIAPI_SetPassword_Param setPasswordParam) {
      return gciApi.setPassword(setPasswordParam);
   }

   @POST
   @Path("getVolunteerHours")
   @Consumes("application/json")
   public GCIAPI_Response getVolunteerHours(GCIAPI_GetVolunteerHours_Param volunteerHours_Param) {
      return gciApi.getVolunteerHours(volunteerHours_Param);
   }

   @POST
   @Path("getAllVolunteerHours")
   @Consumes("application/json")
   public GCIAPI_Response getAllVolunteerHours(GCIAPI_GetAllVolunteerHours_Param allVolunteerHours_Param) {
      return gciApi.getAllVolunteerHours(allVolunteerHours_Param);
   }

}
