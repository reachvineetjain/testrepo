/**
 * 
 */
package com.ccighgo.service.rest.partner;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccighgo.service.components.partner.PartnerServiceImpl;

/**
 * @author ravi
 *
 */
@Path("/partner/")
@Produces("application/json")
@Consumes("application/json")
public class Partner {
   
   private static final Logger LOGGER = LoggerFactory.getLogger(Partner.class);
   
  PartnerServiceImpl partnerServiceImpl;
   
}
