/**
 * 
 */
package com.ccighgo.service.rest.partner.inquiry;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccighgo.service.components.partnerinquiry.PartnerInquiryServiceImpl;

/**
 * @author Ahmed
 *
 */
@Path("/partnerInquiry/")
@Produces("application/json")
@Consumes("application/json")
public class PartnerInquiry {

   private static final Logger LOGGER = LoggerFactory.getLogger(PartnerInquiry.class);

   PartnerInquiryServiceImpl partnerInquiryServiceImpl;

}
