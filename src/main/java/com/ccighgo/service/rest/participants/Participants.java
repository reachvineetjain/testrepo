/**
 * 
 */
package com.ccighgo.service.rest.participants;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.participants.ParticipantsInterface;

/**
 * @author ravi
 *
 */
@Path("/participant/")
@Produces("application/json")
@Consumes("application/json")
public class Participants {

   private static final Logger LOGGER = LoggerFactory.getLogger(Participants.class);

   @Autowired ParticipantsInterface participantsInterface;

}
