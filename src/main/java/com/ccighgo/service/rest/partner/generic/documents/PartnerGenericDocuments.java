/**
 * 
 */
package com.ccighgo.service.rest.partner.generic.documents;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccighgo.service.components.partnerinquiry.PartnerInquiryService;

/**
 * @author Ahmed
 *
 */
@Path("/partnerGenericDocuments/")
@Produces("application/json")
@Consumes("application/json")
public class PartnerGenericDocuments {

   private static final Logger LOGGER = LoggerFactory.getLogger(PartnerGenericDocuments.class);

   @GET
   @Path("ping/{input}")
   @Produces("text/plain")
   public String ping(@PathParam("input") String input) {
       return input;
   }
  
   
   
}
