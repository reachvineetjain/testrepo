/**
 * 
 */
package com.ccighgo.service.rest.partner.subpartner;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

   @GET
   @Path("view/{subPartnerId}")
   @Produces("application/json")
   public com.ccighgo.service.transport.partner.beans.subpartner.SubPartner viewSubPartners(@PathParam("subPartnerId") String subPartnerId){
      LOGGER.debug("calling SubPartner.viewSubPartners for subPartner id {}",subPartnerId);
      return subPartnerInterface.viewSubPartners(subPartnerId);
   }
   
   @POST
   @Path("create")
   @Produces("application/json")
   public com.ccighgo.service.transport.partner.beans.subpartner.SubPartner createSubPartner(com.ccighgo.service.transport.partner.beans.subpartner.SubPartner subPartner){
      LOGGER.debug("calling SubPartner.createSubPartner",subPartner);
      return subPartnerInterface.createSubPartner(subPartner);
   }
   
   @POST
   @Path("update")
   @Produces("application/json")
   public com.ccighgo.service.transport.partner.beans.subpartner.SubPartner updateSubPartner(com.ccighgo.service.transport.partner.beans.subpartner.SubPartner subPartner){
      LOGGER.debug("calling SubPartner.updateSubPartner",subPartner);
      return subPartnerInterface.updateSubPartner(subPartner);
   }
   
}
