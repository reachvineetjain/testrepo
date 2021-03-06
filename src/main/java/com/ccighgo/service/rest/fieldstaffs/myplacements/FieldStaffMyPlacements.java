package com.ccighgo.service.rest.fieldstaffs.myplacements;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.fieldstaffs.placements.FieldStaffMyPlacementsInterface;
import com.ccighgo.service.transport.fieldstaff.beans.erdparticipant.ERDParticipants;
import com.ccighgo.service.transport.fieldstaff.beans.placement.erdplacement.ERDPlacements;
import com.ccighgo.service.transport.fieldstaff.beans.placement.participants.ERDPlacementParticipants;
import com.ccighgo.service.transport.partner.beans.fieldstaff.placement.myplacement.MyPlacements;

/**
 * @author sinshaw.demisse
 *
 */
@Path("/fieldstaff/placement")
@Produces("application/json")
@Consumes("application/json")
public class FieldStaffMyPlacements {
   private static final Logger LOGGER = LoggerFactory.getLogger(FieldStaffMyPlacements.class);

   @Autowired FieldStaffMyPlacementsInterface fieldStaffMyPlacementsInterface;

   /**
    * @param goId
    * @param catagories
    * @return
    */
   @GET
   @Path("/myplacement/{goid}/{catagories}")
   @Produces("application/json")
   public MyPlacements getMyPlacements(@PathParam("goid") String goId, @PathParam("catagories") String catagories) {
      LOGGER.info("call fieldStaffMyPlacementsInterface.getERDMyPlacement");
      return fieldStaffMyPlacementsInterface.getERDMyPlacement(goId, catagories);
   }

   /**
    * @param goId
    * @param catagories
    * @return
    */
   @GET
   @Path("/myteamplacement/{goid}/{catagories}")
   @Produces("application/json")
   public ERDPlacements getMyTeamPlacement(@PathParam("goid") String goId, @PathParam("catagories") String catagories) {
      LOGGER.info("call fieldStaffMyPlacementsInterface.getERDPlacementMyTeamPlacement");
      return fieldStaffMyPlacementsInterface.getERDPlacementMyTeamPlacement(goId, catagories);
   }

   /**
    * @param goId
    * @return
    */
   @GET
   @Path("/participant/{goId}")
   @Produces("application/json")
   public ERDPlacementParticipants getERDPlacementParticipant(@PathParam("goId") String goId) {
      LOGGER.info("call fieldStaffMyPlacementsInterface.getERDPlacementParticipant");
      return fieldStaffMyPlacementsInterface.getERDPlacementParticipant(goId);
   }

   /**
    * @param fsGoId
    * @param catagorie
    * @return
    */
   @GET
   @Path("/monitoring/myParticipant/{fsGoId}/{catagorie}")
   @Produces("application/json")
   public ERDParticipants getAllParticipant(@PathParam("fsGoId") String fsGoId, @PathParam("catagorie") String catagorie) {
      LOGGER.info("calling fieldStaffParticipantInterface.getAll");
      return fieldStaffMyPlacementsInterface.getAllParticipant(fsGoId, catagorie);
   }

   /**
    * @param fsGoId
    * @param catagorie
    * @return
    */
   @GET
   @Path("/monitoring/MyTeamParticipant/{fsGoId}/{catagorie}")
   @Produces("application/json")
   public ERDParticipants getMyTeam(@PathParam("fsGoId") String fsGoId, @PathParam("catagorie") String catagorie) {
      LOGGER.info("calling fieldStaffParticipantInterface.getMyTeam");
      return fieldStaffMyPlacementsInterface.getMyTeamParticipant(fsGoId, catagorie);
   }
}
