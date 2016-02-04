/**
 * 
 */
package com.ccighgo.service.rest.hf.participant.application.process;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.hf.participant.application.process.HFApplication;
import com.ccighgo.service.transport.hostfamily.beans.application.photo.upload.HFApplicationUploadPhotos;

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

   @POST
   @Path("upload/photo/{goId}/{seasonId}")
   @Consumes("application/json")
   @Produces("application/json")
   public HFApplicationUploadPhotos uploadHFPhotos(@PathParam("goId") String goId, @PathParam("seasonId") String seasonId, HFApplicationUploadPhotos hfApplicationUploadPhotos) {
      LOGGER.info("Calling service HFApplicationProcess.uploadHFPhotos for goId {} and seasonId {}", goId, seasonId);
      return hfApplication.uploadHFPhotos(goId, seasonId, hfApplicationUploadPhotos);
   }

}
