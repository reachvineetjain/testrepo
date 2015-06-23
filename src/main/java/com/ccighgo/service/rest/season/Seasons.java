package com.ccighgo.service.rest.season;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.season.SeasonServiceInterfaceImpl;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSAugStart;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSBasicDetail;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSFieldSettings;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSJanStart;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSProgramAllocations;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.SeasonHspJ1HSDetails;
import com.ccighgo.service.transport.season.beans.seasonprogram.SeasonProgram;
import com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatuses;
import com.ccighgo.service.transport.seasons.beans.season.SeasonBean;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonsList;

@Path("/season/")
@Produces("application/json")
@Consumes("application/json")
public class Seasons {

   private static final Logger LOGGER = LoggerFactory.getLogger(Seasons.class);

   @Autowired
   SeasonServiceInterfaceImpl seasonServices;

   @GET
   @Path("ping/{input}")
   @Produces("text/plain")
   public String ping(@PathParam("input") String input) {
      LOGGER.debug("Pinging !!");
      return input;
   }

   @GET
   @Path("list/")
   @Produces("application/json")
   public SeasonsList getAllSeasons() {
      LOGGER.debug("Calling Get All Seasons 'func:getAllSeasons'");
      SeasonsList result = seasonServices.getAllSeasons();
      LOGGER.debug("Result Count : " + result.getRecordCount());
      return result;
   }

   @GET
   @Path("edit/{id}")
   @Produces("application/json")
   public SeasonBean editSeason(@PathParam("id") String id) {
      LOGGER.debug("Calling Edit Season By Id 'func:editSeason'");
      SeasonBean result = seasonServices.editSeason(id);
      return result;
   }

   @GET
   @Path("view/{id}")
   @Produces("application/json")
   public SeasonBean view(@PathParam("id") String id) {
      LOGGER.debug("Calling Get Season By Id 'func:View'");
      SeasonBean result = seasonServices.viewSeason(id);
      return result;
   }

   @POST
   @Path("create")
   @Consumes("application/json")
   public SeasonBean createSeason(SeasonBean seasonBean) {
      LOGGER.debug("Calling Create Season function 'func:createSeason'");
      return seasonServices.createSeason(seasonBean);
   }

   @POST
   @Path("update")
   @Consumes("application/json")
   public SeasonBean updateSeason(SeasonBean seasonBean) {
      LOGGER.debug("Calling Update Season'func:updateSeason'");
      return seasonServices.updateSeason(seasonBean);
   }

   @GET
   @Path("deleteSeason/{id}")
   @Produces("application/json")
   public String deleteSeason(@PathParam("id") String id) {
      LOGGER.debug("Calling Delete Season'func:deleteSeason'");
      return seasonServices.deleteSeason(id);
   }

   @GET
   @Path("program/season/{seasonId}")
   @Produces("application/json")
   public SeasonProgram getSeasonProgram(@PathParam("seasonId") String seasonId) {
      return seasonServices.getSeasonPrograms(seasonId);
   }

   @GET
   @Path("status")
   @Produces("application/json")
   public SeasonStatuses getSeasonStatus() {
      return seasonServices.getSeasonStatus();
   }

   // HSP J1HS view services

   @GET
   @Path("j1hs/details/view/{seasonId}")
   @Produces("application/json")
   public SeasonHspJ1HSDetails getJ1Details(@PathParam("seasonId") String seasonId) {
      return seasonServices.getHSPJ1HSSeasonDetails(seasonId);
   }

   @GET
   @Path("j1hs/base/view/{seasonId}")
   @Produces("application/json")
   public J1HSBasicDetail getJ1NameAndStatus(@PathParam("seasonId") String seasonId) {
      return seasonServices.getHSPJ1HSSeasonNameAndStatus(seasonId);
   }

   @GET
   @Path("j1hs/jan/view/{seasonId}")
   @Produces("application/json")
   public J1HSJanStart getJ1JanStartDetails(@PathParam("seasonId") String seasonId) {
      return seasonServices.getHSPJ1HSSeasonJanStartDetails(seasonId);
   }

   @GET
   @Path("j1hs/aug/view/{seasonId}")
   @Produces("application/json")
   public J1HSAugStart getJ1AugStartDetails(@PathParam("seasonId") String seasonId) {
      return seasonServices.getHSPJ1HSSeasonAugStartDetails(seasonId);
   }

   @GET
   @Path("j1hs/field/view/{seasonId}/settings")
   @Produces("application/json")
   public J1HSFieldSettings getJ1FieldSettings(@PathParam("seasonId") String seasonId) {
      return seasonServices.getHSPJ1HSSeasonFieldSettings(seasonId);
   }

   @GET
   @Path("j1hs/program/view/{seasonId}/allocation")
   @Produces("application/json")
   public J1HSProgramAllocations getJ1ProgramAllocation(@PathParam("seasonId") String seasonId) {
      return seasonServices.getHSPJ1HSSeasonProgramAllocation(seasonId);
   }

   @GET
   @Path("clone/{id}/{newSeasonName}/")
   @Produces("application/json")
   public String cloneSeason(@PathParam("id") String id, @PathParam("newSeasonName") String newSeasonName) {
      LOGGER.debug("Calling clone Season'func:cloneSeason'");
      return seasonServices.cloneSeason(id, newSeasonName);
   }

   @GET
   @Path("custom/{seasonName}/")
   @Produces("application/json")
   public String customService(@PathParam("seasonName") String id) {
      LOGGER.debug("Calling clone Season'func:'");
      seasonServices.customService(id);
      return "200: OK";
   }

   // HSP J1HS edit services

   @GET
   @Path("j1hs/details/edit/{seasonId}")
   @Produces("application/json")
   public SeasonHspJ1HSDetails editJ1Details(@PathParam("seasonId") String seasonId) {
      return seasonServices.getHSPJ1HSSeasonDetails(seasonId);
   }

   @GET
   @Path("j1hs/base/edit/{seasonId}")
   @Produces("application/json")
   public J1HSBasicDetail editJ1NameAndStatus(@PathParam("seasonId") String seasonId) {
      return seasonServices.getHSPJ1HSSeasonNameAndStatus(seasonId);
   }

   @GET
   @Path("j1hs/jan/edit/{seasonId}")
   @Produces("application/json")
   public J1HSJanStart editJ1JanStartDetails(@PathParam("seasonId") String seasonId) {
      return seasonServices.getHSPJ1HSSeasonJanStartDetails(seasonId);
   }

   @GET
   @Path("j1hs/aug/edit/{seasonId}")
   @Produces("application/json")
   public J1HSAugStart editJ1AugStartDetails(@PathParam("seasonId") String seasonId) {
      return seasonServices.getHSPJ1HSSeasonAugStartDetails(seasonId);
   }

   @GET
   @Path("j1hs/field/edit/{seasonId}/settings")
   @Produces("application/json")
   public J1HSFieldSettings editJ1FieldSettings(@PathParam("seasonId") String seasonId) {
      return seasonServices.getHSPJ1HSSeasonFieldSettings(seasonId);
   }

   @GET
   @Path("j1hs/program/edit/{seasonId}/allocation")
   @Produces("application/json")
   public J1HSProgramAllocations editJ1ProgramAllocation(@PathParam("seasonId") String seasonId) {
      return seasonServices.getHSPJ1HSSeasonProgramAllocation(seasonId);
   }
   
  // HSP J1HS update services
}
