/**
 * 
 */
package com.ccighgo.service.rest.partner.admin.partner;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.partner.admin.partner.AdminPartnerInterface;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.admin.add.partner.AdminAddPartner;

/**
 * @author ravi
 *
 */
@Path("/admin/partner/")
@Produces("application/json")
@Consumes("application/json")
public class AdminPartner {
   
   private static final Logger LOGGER = LoggerFactory.getLogger(AdminPartner.class);
   
   @Autowired AdminPartnerInterface adminPartnerInterface;
   @Context HttpServletRequest request;
   
   @POST
   @Path("/add/partner/")
   @Produces("application/json")
   @Consumes("application/json")
   public Response addPartner(AdminAddPartner partner){
      return adminPartnerInterface.addPartner(partner, request);
   }
}
