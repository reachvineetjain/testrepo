/*
 * Copyright (c) 2015, 2016, Creospan Solutions PVT LTD. All rights reserved.
 * CREOSPAN PROPRIETARY/CONFIDENTIAL.
 *
 *
 */
package com.ccighgo.service.rest.authorization;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.authorization.AuthorizationManagerInterface;
import com.ccighgo.service.transport.beans.fieldstaffdashboard.erddashboard.ErdDashboard;
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

   /**
    * @param partnerGoId
    * @return
    */
   @GET
   @Path("partneragent/{partnerGoId}")
   @Produces("application/json")
   public PartnerRecruitmentLead getPartnerAgentDashboard(@PathParam("partnerGoId") String partnerGoId) {
      return authorizationManager.getPartnerAgentDashboard(Integer.parseInt(partnerGoId));
   }

   /**
    * @param goId
    * @return
    */
   @GET
   @Path("fs/{goId}")
   @Produces("application/json")
   public ErdDashboard getFSDashboard(@PathParam("goId") String goId) {
      return authorizationManager.getFSDashboard(goId);
   }

}
