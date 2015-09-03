/**
 * 
 */
package com.ccighgo.service.rest.partner.subpartner;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.partner.subpartner.SubPartnerInterface;

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

}
