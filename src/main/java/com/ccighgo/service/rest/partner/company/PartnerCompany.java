/**
 * 
 */
package com.ccighgo.service.rest.partner.company;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.partner.company.PartnerCompanyService;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.partner.beans.add.partner.office.NewPartnerOffice;
import com.ccighgo.service.transport.partner.beans.companydetail.PartnerCompanyDetail;

/**
 * @author ravi
 *
 */
@Path("/partner/company/")
@Produces("application/json")
@Consumes("application/json")
public class PartnerCompany {

   private static final Logger LOGGER = LoggerFactory.getLogger(PartnerCompany.class);

   @Autowired PartnerCompanyService partnerCompanyService;

   @GET
   @Path("get/details/{partnerGoId}")
   @Produces("application/json")
   public PartnerCompanyDetail getPartnerCompanyDetails(@PathParam("partnerGoId") String partnerGoId) {
      return partnerCompanyService.getPartnerCompanyDetails(partnerGoId);
   }

   @POST
   @Path("update/details")
   @Consumes("application/json")
   @Produces("application/json")
   public PartnerCompanyDetail addNewPartnerCompany(PartnerCompanyDetail partnerCompanyDetail) {
      LOGGER.info("calling PartnerCompany.updatePartnerCompanyDetails");
      return partnerCompanyService.updatePartnerCompanyDetails(partnerCompanyDetail);
   }

   @POST
   @Path("add/office/{partnerGoId}")
   @Consumes("application/json")
   @Produces("application/json")
   public Response addNewPartnerOffice(@PathParam("partnerGoId") String partnerGoId, NewPartnerOffice newPartnerOffice) {
      LOGGER.info("calling PartnerCompany.addNewPartnerOffice");
      return partnerCompanyService.addNewPartnerOffice(partnerGoId, newPartnerOffice);
   }

   @GET
   @Path("delete/office/{partnerOfficeId}")
   @Consumes("application/json")
   public Response deletePartnerOffice( @PathParam("partnerOfficeId") String partnerOfficeId) {
      LOGGER.info("calling PartnerCompany.deletePartnerOffice");
      return partnerCompanyService.deletePartnerOffice(partnerOfficeId);
   }

}
