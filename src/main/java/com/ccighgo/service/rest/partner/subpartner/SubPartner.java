/**
 * 
 */
package com.ccighgo.service.rest.partner.subpartner;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.partner.subpartner.SubPartnerInterface;
import com.ccighgo.service.transport.partner.beans.subpartner.PartnerSubPartners;

/**
 * @author ravi
 *
 */
@Path("/subpartner/")
@Produces("application/json")
@Consumes("application/json")
public class SubPartner {
   
   private static final Logger LOGGER = LoggerFactory.getLogger(SubPartner.class);
   
   @Autowired SubPartnerInterface subPartnerInterface;
   
   @GET
   @Path("list/{partnerId}")
   @Produces("application/json")
   public PartnerSubPartners getSubPartnersOfpartners(@PathParam("partnerId") String partnerId){
      LOGGER.debug("calling SubPartner.getSubPartnersOfpartners for partner id {}",partnerId);
      return subPartnerInterface.getSubPartnersOfpartners(partnerId);
   }

}
