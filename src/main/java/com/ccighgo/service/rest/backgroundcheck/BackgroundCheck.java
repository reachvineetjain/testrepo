package com.ccighgo.service.rest.backgroundcheck;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
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

   @POST
   @Path("requestScreen")
   @Produces("application/json")
   @Consumes("application/json")
   public ScreenResponse requestScreen(ScreenRequest screenRequest) {
      LOGGER.debug("Calling func:requestScreen");
      return backgroundServiceInterface.requestScreen(screenRequest);
   }

   @POST
   @Path("response")
   @Produces("application/json")
   @Consumes("application/json")
   public String test(ScreenResponse screenRequest) {
      LOGGER.debug("TEST");
      return backgroundServiceInterface.test(screenRequest);
   }

}
