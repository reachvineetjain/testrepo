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

import com.ccighgo.service.components.partner.PartnerServiceImpl;
import com.ccighgo.service.components.partner.company.PartnerCompanyServiceImpl;
import com.ccighgo.service.transport.partner.beans.companydetail.PartnerCompanyDetail;
import com.ccighgo.service.transport.partner.beans.partnerseason.PartnerSeasons;

/**
 * @author Ahmed
 *
 */
@Path("/partnerCompany/")
@Produces("application/json")
@Consumes("application/json")
public class PartnerCompany {

   private static final Logger LOGGER = LoggerFactory.getLogger(PartnerCompany.class);

   PartnerCompanyServiceImpl partnerCompanyServiceImpl;
   
   @POST
   @Path("addNewOne")
   @Produces("application/json")
   public PartnerCompanyDetail addNewPartnerCompany(PartnerCompanyDetail companyDetail) {
      LOGGER.info("calling PartnerCompany.addNewPartnerCompany");
      return partnerCompanyServiceImpl.addNewPartnerCompany(companyDetail);
   }


}
