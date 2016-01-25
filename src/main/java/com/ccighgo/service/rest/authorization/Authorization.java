/**
 * 
 */
package com.ccighgo.service.rest.authorization;



import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;







import com.ccighgo.service.components.authorization.AuthorizationManagerInterface;
import com.ccighgo.service.transport.integration.thirdparty.beans.partnerLeadViewForPartnerInquiryData.PartnerRecruitmentLead;
import com.ccighgo.service.transport.partner.beans.partnerdashboard.PartnerDashboard;
import com.ccighgo.service.transport.usermanagement.beans.user.User;

/**
 * @author ravi
 *
 */
@Path("/authorize/")
@Produces("application/json")
@Consumes("application/json")
public class Authorization {
   private static final Logger LOGGER = LoggerFactory.getLogger(Authorization.class); 
  
   @Autowired AuthorizationManagerInterface authorizationManager;
   
   /**
    * RESTFul service gets user by id for edit
    * 
    * @param id
    * @return User by id
    */
   @GET
   @Path("cciusr/{userId}")
   @Produces("application/json")
   public User getCCIUserDetails(@PathParam("userId") String userId) {
	   return authorizationManager.getCCIUserDetails(userId);
   }
   
   /**
    * RESTFul service gets user by id for edit
    * 
    * @param id
    * @return User by id
    */
   @GET
   @Path("partner/{partnerGoId}")
   @Produces("application/json")
   public PartnerDashboard getPartnerDashboard(@PathParam("partnerGoId") String partnerGoId) {
       return authorizationManager.getPartnerDashboard(partnerGoId);
   }
   
   @GET
   @Path("partneragent/{partnerGoId}")
   @Produces("application/json")
   public PartnerRecruitmentLead getPartnerAgentDashboard(@PathParam("partnerGoId") String partnerGoId) {
      return authorizationManager.getPartnerAgentDashboard(Integer.parseInt(partnerGoId));
   }
   
   @GET
   @Path("fs/{goId}")
   @Produces("application/json")
   public PartnerRecruitmentLead getFSDashboard(@PathParam("goId") String goId) {
      return authorizationManager.getFSDashboard(Integer.parseInt(goId));
   }
   
}
