package com.ccighgo.service.rest.backgroundcheck;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.backgroundcheck.BackgroundServiceInterface;
import com.ccighgo.service.transport.seasons.beans.backgroundscreenrequest.ScreenRequest;
import com.ccighgo.service.transport.seasons.beans.backgroundscreenresponse.ScreenResponse;

@Path("/backgroundcheck/")
@Produces("application/json")
@Consumes("application/json")
public class BackgroundCheck {

   private static final Logger LOGGER = Logger.getLogger(BackgroundCheck.class);

   @Autowired
   BackgroundServiceInterface backgroundServiceInterface;

   @GET
   @Path("requestScreen")
   @Produces("application/json")
   public ScreenResponse requestScreen(ScreenRequest screenRequest) {
      LOGGER.debug("Calling func:requestScreen");
      return backgroundServiceInterface.requestScreen(screenRequest);
   }

}