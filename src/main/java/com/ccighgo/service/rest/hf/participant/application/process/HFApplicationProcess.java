/**
 * 
 */
package com.ccighgo.service.rest.hf.participant.application.process;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.hf.participant.application.process.HFApplication;

/**
 * @author ravi/ahmed
 *
 */
@Path("/hf/application/")
@Produces("application/json")
@Consumes("application/json")
public class HFApplicationProcess {
   
   private static final Logger LOGGER = LoggerFactory.getLogger(HFApplicationProcess.class); 
   
   @Autowired HFApplication hfApplication;

}
