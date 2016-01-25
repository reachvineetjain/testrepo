package com.ccighgo.service.rest.fieldstaffs.participant;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.component.fieldstaffs.participant.AdminERDParticipantInterface;
import com.ccighgo.service.transport.fieldstaff.beans.adminerdparticipant.AdminERDParticipants;
import com.ccighgo.service.transport.fieldstaff.beans.placement.adminerdplacement.AdminERDPlacements;
import com.ccighgo.service.transport.partner.beans.fieldstaff.placement.adminerdmyplacement.AdminMyPlacements;
/**
 * @author sinshaw.demisse
 *
 */
@Path("/fieldstaff/participant")
@Produces("application/json")
@Consumes("application/json")
public class AdminERDParticipant {

   private static final Logger LOGGER = LoggerFactory.getLogger(AdminERDParticipant.class);

   @Autowired
   AdminERDParticipantInterface adminERDParticipantInterface;

   /**
    * @return FieldStaffParticipants
    */
   @GET
   @Path("monitoring/myparticipant/{fsGoId}")
   @Produces("application/json")
   public AdminERDParticipants getAllParticipant(@PathParam("fsGoId") String fsGoId) {
      LOGGER.info("calling fieldStaffParticipantInterface.getAll");
      return adminERDParticipantInterface.getAll(fsGoId);
   }

   /**
    * @return FieldStaffParticipants
    */
   @GET
   @Path("monitorin/MyTeamParticipant/{fsGoId}")
   @Produces("application/json")
   public AdminERDParticipants getMyTeam(@PathParam("fsGoId") String fsGoId) {
      LOGGER.info("calling fieldStaffParticipantInterface.getMyTeam");
      return adminERDParticipantInterface.getMyTeam(fsGoId);
   }
   
   @GET
   @Path("/placement/myparticipant/{goId}/{catagories}")
   @Produces("application/json")
   public AdminMyPlacements getERDMyPlacement(@PathParam("goId")String goId, @PathParam("catagories")String catagories)
   {
      LOGGER.info("calling adminERDParticipantInterface.getERDMyPlacement");
     return adminERDParticipantInterface.getERDMyPlacement(goId, catagories);
   }
   @GET
   @Path("/placement/myteamparticipant/{goId}/{catagories}")
   public AdminERDPlacements getERDPlacementMyTeamPlacement(@PathParam("goId")String goId, @PathParam("catagories")String catagories)
   {
      LOGGER.info("calling adminERDParticipantInterface.getERDPlacementMyTeamPlacement");
      return adminERDParticipantInterface.getERDPlacementMyTeamPlacement(goId, catagories);
   }
}
