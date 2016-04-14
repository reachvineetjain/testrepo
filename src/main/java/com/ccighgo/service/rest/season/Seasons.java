package com.ccighgo.service.rest.season;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.season.SeasonServiceInterface;
import com.ccighgo.service.transport.common.beans.deletereq.DeleteRequest;
import com.ccighgo.service.transport.season.beans.cloneseason.CloneSeason;
import com.ccighgo.service.transport.season.beans.seasondepartdoc.SeasonDepartmentDocument;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.GHTSection1Base;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.GHTSection2Dates;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.SeasonGHTDetails;
import com.ccighgo.service.transport.season.beans.seasonhspihpdetails.IHPDates;
import com.ccighgo.service.transport.season.beans.seasonhspihpdetails.IHPNameAndStatus;
import com.ccighgo.service.transport.season.beans.seasonhspihpdetails.IHPProgramConfiguration;
import com.ccighgo.service.transport.season.beans.seasonhspihpdetails.SeasonHspStpIhpDetails;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSAugStart;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSBasicDetail;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSFieldSettings;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSJanStart;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSProgramAllocations;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.SeasonHspJ1HSDetails;
import com.ccighgo.service.transport.season.beans.seasonprgdoc.SeasonProgramDocument;
import com.ccighgo.service.transport.season.beans.seasonprgnote.SeasonProgramNote;
import com.ccighgo.service.transport.season.beans.seasonprogram.SeasonPrograms;
import com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatuses;
import com.ccighgo.service.transport.season.beans.seasonwpdetails.SeasonWPDetails;
import com.ccighgo.service.transport.season.beans.seasonwpdetails.WPBasicDetail;
import com.ccighgo.service.transport.season.beans.seasonwpdetails.WPProgramAllocations;
import com.ccighgo.service.transport.season.beans.seasonwpdetails.WPSectionOne;
import com.ccighgo.service.transport.seasons.beans.season.SeasonBean;
import com.ccighgo.service.transport.seasons.beans.season.SeasonDepartmentNotes;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1Accounting;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1AugustStart1StSemesterDetails;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1AugustStartFullYearDetails;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1BasicDetails;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1FieldSettings;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1JanuaryStart2NdSemesterDetails;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1JanuaryStartFullYearDetail;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.HSPF1ProgramAllocations;
import com.ccighgo.service.transport.seasons.beans.seasonhspf1details.SeasonHSPF1Details;
import com.ccighgo.service.transport.seasons.beans.seasonslist.SeasonsList;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.SeasonWPCAPDetails;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.WPCAPBasicDetails;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.WPCAPInternshipDetails;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.WPCAPProgramAllocations;
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.WPCAPTraineeDetails;
import com.ccighgo.service.transport.utility.beans.documenttype.DocumentTypes;

/**
 * <p>
 * Rest service interface exposes list of services for Season management.
 * </p>
 * 
 * @see com.ccighgo.service.components.season.SeasonServiceInterface
 * @see com.ccighgo.service.components.season.SeasonServiceInterfaceImpl
 * 
 * @author ravi
 *
 */
@Path("/season/")
@Produces("application/json")
@Consumes("application/json")
public class Seasons {

   private static final Logger LOGGER = Logger.getLogger(Seasons.class);

   @Autowired SeasonServiceInterface seasonServices;

   @GET
   @Path("ping/{input}")
   @Produces("text/plain")
   public String ping(@PathParam("input") String input) {
      LOGGER.debug("Pinging !! ( value : " + input + " )");
      return input;
   }

   /**
    * Service to list all available seasons in the system
    * 
    * @return list of seasons in json format
    */
   @GET
   @Path("list/")
   @Produces("application/json")
   public SeasonsList getAllSeasons() {
      LOGGER.debug("Calling Get All Seasons 'func:getAllSeasons'");
      return seasonServices.getAllSeasons();
   }

   /**
    * Service to edit existing season.
    * 
    * @param id
    * @return Season object as JSON 
    */
   @GET
   @Path("edit/{id}")
   @Produces("application/json")
   public SeasonBean editSeason(@PathParam("id") String id) {
      LOGGER.debug("Calling Edit Season By Id 'func:editSeason'");
      return seasonServices.editSeason(id);
   }

   /**
    * Service to view details of existing season
    * 
    * @param id
    * @return
    */
   @GET
   @Path("view/{id}")
   @Produces("application/json")
   public SeasonBean view(@PathParam("id") String id) {
      LOGGER.debug("Calling Get Season By Id 'func:View'");
      return seasonServices.viewSeason(id);
   }

   /**
    * Service to create a new season.
    * 
    * @param seasonBean
    * @return newly created season
    */
   @POST
   @Path("create")
   @Consumes("application/json")
   public SeasonBean createSeason(SeasonBean seasonBean) {
      LOGGER.debug("Calling Create Season function 'func:createSeason'");
      return seasonServices.createSeason(seasonBean);
   }

   /**
    * Update an existing season.
    * 
    * @param seasonBean
    * @return updated season
    */
   @POST
   @Path("update")
   @Consumes("application/json")
   public SeasonBean updateSeason(SeasonBean seasonBean) {
      LOGGER.debug("Calling Update Season'func:updateSeason'");
      return seasonServices.updateSeason(seasonBean);
   }

   /**
    * Gets season programs for high level season
    * 
    * @param seasonId
    * @return
    */
   @GET
   @Path("program/season/{seasonId}")
   @Produces("application/json")
   public SeasonPrograms getSeasonProgram(@PathParam("seasonId") String seasonId) {
      LOGGER.debug("Calling 'fun:getSeasonProgram' ");
      return seasonServices.getSeasonPrograms(seasonId);
   }

   /**
    * Gets the status of season if it is open, closed or draft
    * 
    * @return
    */
   @GET
   @Path("status")
   @Produces("application/json")
   public SeasonStatuses getSeasonStatus() {
      LOGGER.debug("Calling 'fun: getSeasonStatus' ");
      return seasonServices.getSeasonStatus();
   }

   // HSP J1HS view services

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("j1hs/details/view/{seasonProgramId}")
   @Produces("application/json")
   public SeasonHspJ1HSDetails getJ1Details(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getJ1Details'");
      return seasonServices.getHSPJ1HSSeasonDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("j1hs/base/view/{seasonProgramId}")
   @Produces("application/json")
   public J1HSBasicDetail getJ1NameAndStatus(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling  'fun:getJ1NameAndStatus'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPJ1HSSeasonNameAndStatus(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("j1hs/jan/view/{seasonProgramId}")
   @Produces("application/json")
   public J1HSJanStart getJ1JanStartDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getJ1JanStartDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPJ1HSSeasonJanStartDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("j1hs/aug/view/{seasonProgramId}")
   @Produces("application/json")
   public J1HSAugStart getJ1AugStartDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getJ1AugStartDetails'");
      LOGGER.debug("seasonProgramId :" + seasonProgramId);
      return seasonServices.getHSPJ1HSSeasonAugStartDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("j1hs/field/view/{seasonProgramId}/settings")
   @Produces("application/json")
   public J1HSFieldSettings getJ1FieldSettings(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getJ1FieldSettings'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPJ1HSSeasonFieldSettings(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("j1hs/program/view/{seasonProgramId}/allocation")
   @Produces("application/json")
   public J1HSProgramAllocations getJ1ProgramAllocation(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getJ1ProgramAllocation'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPJ1HSSeasonProgramAllocation(seasonProgramId);
   }

   // HSP J1HS edit services

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("j1hs/details/edit/{seasonProgramId}")
   @Produces("application/json")
   public SeasonHspJ1HSDetails editJ1Details(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editJ1Details'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPJ1HSSeasonDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("j1hs/base/edit/{seasonProgramId}")
   @Produces("application/json")
   public J1HSBasicDetail editJ1NameAndStatus(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editJ1NameAndStatus'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPJ1HSSeasonNameAndStatus(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("j1hs/jan/edit/{seasonProgramId}")
   @Produces("application/json")
   public J1HSJanStart editJ1JanStartDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editJ1JanStartDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPJ1HSSeasonJanStartDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("j1hs/aug/edit/{seasonProgramId}")
   @Produces("application/json")
   public J1HSAugStart editJ1AugStartDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editJ1AugStartDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPJ1HSSeasonAugStartDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("j1hs/field/edit/{seasonProgramId}/settings")
   @Produces("application/json")
   public J1HSFieldSettings editJ1FieldSettings(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editJ1FieldSettings'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPJ1HSSeasonFieldSettings(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("j1hs/program/edit/{seasonProgramId}/allocation")
   @Produces("application/json")
   public J1HSProgramAllocations editJ1ProgramAllocation(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editJ1ProgramAllocation'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPJ1HSSeasonProgramAllocation(seasonProgramId);
   }

   // HSP J1HS update services

   /**
    * @param seasonHspJ1HSDetails
    * @return
    */
   @POST
   @Path("j1hs/details/update")
   @Consumes("application/json")
   public SeasonHspJ1HSDetails updateJ1Details(SeasonHspJ1HSDetails seasonHspJ1HSDetails) {
      LOGGER.debug("Calling 'fun:updateJ1Details' ");
      return seasonServices.updateHSPJ1HSSeasonDetails(seasonHspJ1HSDetails);
   }

   /**
    * @param j1hsBasicDetail
    * @return
    */
   @POST
   @Path("j1hs/base/update")
   @Consumes("application/json")
   public J1HSBasicDetail updateJ1NameAndStatus(J1HSBasicDetail j1hsBasicDetail) {
      LOGGER.debug("Calling 'fun:updateJ1NameAndStatus'");
      return seasonServices.updateHSPJ1HSSeasonNameAndStatus(j1hsBasicDetail);
   }

   /**
    * @param j1hsJanStart
    * @return
    */
   @POST
   @Path("j1hs/jan/update")
   @Consumes("application/json")
   public J1HSJanStart updateJ1JanStartDetails(J1HSJanStart j1hsJanStart) {
      LOGGER.debug("Calling 'fun:updateJ1JanStartDetails'");
      return seasonServices.updateHSPJ1HSSeasonJanStartDetails(j1hsJanStart);
   }

   /**
    * @param j1hsAugStart
    * @return
    */
   @POST
   @Path("j1hs/aug/update")
   @Consumes("application/json")
   public J1HSAugStart updateJ1AugStartDetails(J1HSAugStart j1hsAugStart) {
      return seasonServices.updateHSPJ1HSSeasonAugStartDetails(j1hsAugStart);
   }

   /**
    * @param j1hsFieldSettings
    * @return
    */
   @POST
   @Path("j1hs/field/settings/update")
   @Consumes("application/json")
   public J1HSFieldSettings updateJ1FieldSettings(J1HSFieldSettings j1hsFieldSettings) {
      return seasonServices.updateHSPJ1HSSeasonFieldSettings(j1hsFieldSettings);
   }

   /**
    * @param j1hsProgramAllocations
    * @return
    */
   @POST
   @Path("j1hs/program/update/allocation")
   @Consumes("application/json")
   public J1HSProgramAllocations updateJ1ProgramAllocation(J1HSProgramAllocations j1hsProgramAllocations) {
      return seasonServices.updateHSPJ1HSSeasonProgramAllocation(j1hsProgramAllocations);
   }

   // HSP F1 services
   /**
    * view - Edit- update
    */

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("f1/view/details/{seasonProgramId}")
   @Produces("application/json")
   public SeasonHSPF1Details getF1Details(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getSeasonHSPF1Details(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("f1/edit/details/{seasonProgramId}")
   @Produces("application/json")
   public SeasonHSPF1Details editF1Details(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editF1Details'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getSeasonHSPF1Details(seasonProgramId);
   }

   /**
    * @param seasonHSPF1Details
    * @return
    */
   @POST
   @Path("f1/update/details")
   @Produces("application/json")
   public SeasonHSPF1Details updateF1Details(SeasonHSPF1Details seasonHSPF1Details) {
      LOGGER.debug("Calling 'fun:updateF1Details'");
      if (seasonHSPF1Details != null)
         LOGGER.debug("Season ID  : " + seasonHSPF1Details.getSeasonId());
      else
         LOGGER.debug("Object is Null!!");
      return seasonServices.updateF1Details(seasonHSPF1Details);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("f1/view/basicdetails/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1BasicDetails getF1NameAndStatus(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getF1NameAndStatus'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1NameAndStatus(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("f1/edit/basicdetails/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1BasicDetails editF1NameAndStatus(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editF1NameAndStatus'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1NameAndStatus(seasonProgramId);
   }

   /**
    * @param hspf1BasicDetails
    * @return
    */
   @POST
   @Path("f1/update/basicdetails")
   @Produces("application/json")
   public HSPF1BasicDetails updateF1NameAndStatus(HSPF1BasicDetails hspf1BasicDetails) {
      LOGGER.debug("Calling 'fun:updateF1NameAndStatus'");
      if (hspf1BasicDetails != null)
         LOGGER.debug("Season ID  : " + hspf1BasicDetails.getSeasonId());
      else
         LOGGER.debug("Object is Null");
      return seasonServices.updateHSPF1NameAndStatus(hspf1BasicDetails);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("f1/view/accounting/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1Accounting getF1Accounting(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getF1Accounting'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1Accounting(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("f1/edit/accounting/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1Accounting editF1Accounting(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editF1Accounting'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1Accounting(seasonProgramId);
   }

   /**
    * @param hspf1Accounting
    * @return
    */
   @POST
   @Path("f1/update/accounting")
   @Produces("application/json")
   public HSPF1Accounting updateF1Accounting(HSPF1Accounting hspf1Accounting) {
      LOGGER.debug("Calling 'fun:updateF1Accounting'");
      if (hspf1Accounting != null)
         LOGGER.debug("Season ID  : " + hspf1Accounting.getSeasonId());
      else
         LOGGER.debug("Object is Null");
      return seasonServices.updateF1Accounting(hspf1Accounting);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("f1/view/jan/startdetails/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1JanuaryStart2NdSemesterDetails getF1JanStart2NdSemesterDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getF1JanStart2NdSemesterDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1JanuaryStart2NdSemesterDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("f1/edit/jan/startdetails/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1JanuaryStart2NdSemesterDetails editF1JanStart2NdSemesterDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editF1JanStart2NdSemesterDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1JanuaryStart2NdSemesterDetails(seasonProgramId);
   }

   /**
    * @param hspf1JanuaryStart2NdSemesterDetails
    * @return
    */
   @POST
   @Path("f1/update/jan/startdetails")
   @Produces("application/json")
   public HSPF1JanuaryStart2NdSemesterDetails updateF1JanStart2NdSemesterDetails(HSPF1JanuaryStart2NdSemesterDetails hspf1JanuaryStart2NdSemesterDetails) {
      LOGGER.debug("Calling 'fun:updateF1JanStart2NdSemesterDetails'");
      if (hspf1JanuaryStart2NdSemesterDetails != null)
         LOGGER.debug("Season ID  : " + hspf1JanuaryStart2NdSemesterDetails.getSeasonId());
      else
         LOGGER.debug("Object is Null");
      return seasonServices.updateF1JanStart2NdSemesterDetails(hspf1JanuaryStart2NdSemesterDetails);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("f1/view/jan/fulldetails/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1JanuaryStartFullYearDetail getF1JanStartFullYearDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getF1JanStartFullYearDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1JanuaryStartFullYearDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("f1/edit/jan/fulldetails/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1JanuaryStartFullYearDetail editF1JanStartFullYearDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editF1JanStartFullYearDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1JanuaryStartFullYearDetails(seasonProgramId);
   }

   /**
    * @param hspf1JanuaryStartFullYearDetail
    * @return
    */
   @POST
   @Path("f1/update/jan/fulldetails")
   @Produces("application/json")
   public HSPF1JanuaryStartFullYearDetail updateF1JanStartFullYearDetails(HSPF1JanuaryStartFullYearDetail hspf1JanuaryStartFullYearDetail) {
      LOGGER.debug("Calling 'fun:updateF1JanStartFullYearDetails'");
      if (hspf1JanuaryStartFullYearDetail != null)
         LOGGER.debug("Season ID  : " + hspf1JanuaryStartFullYearDetail.getSeasonId());
      else
         LOGGER.debug("Object is Null");
      return seasonServices.updateF1JanStartFullYearDetails(hspf1JanuaryStartFullYearDetail);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("f1/view/august/startdetail/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1AugustStart1StSemesterDetails getF1AugStart1StSemesterDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getF1AugStart1StSemesterDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1AugustStart1StSemesterDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("f1/edit/august/startdetail/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1AugustStart1StSemesterDetails editF1AugStart1StSemesterDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editF1AugStart1StSemesterDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1AugustStart1StSemesterDetails(seasonProgramId);
   }

   /**
    * @param hspf1AugustStart1StSemesterDetails
    * @return
    */
   @POST
   @Path("f1/update/august/startdetail")
   @Produces("application/json")
   public HSPF1AugustStart1StSemesterDetails updateF1AugStart1StSemesterDetails(HSPF1AugustStart1StSemesterDetails hspf1AugustStart1StSemesterDetails) {
      LOGGER.debug("Calling 'fun:updateF1AugStart1StSemesterDetails'");
      if (hspf1AugustStart1StSemesterDetails != null)
         LOGGER.debug("Season ID  : " + hspf1AugustStart1StSemesterDetails.getSeasonId());
      else
         LOGGER.debug("Object is Null");
      return seasonServices.updateF1AugStart1StSemesterDetails(hspf1AugustStart1StSemesterDetails);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("f1/view/august/fulldetail/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1AugustStartFullYearDetails getF1AugStartFullYearDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getF1AugStartFullYearDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1AugustStartFullYearDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("f1/edit/august/fulldetail/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1AugustStartFullYearDetails editF1AugStartFullYearDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editF1AugStartFullYearDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1AugustStartFullYearDetails(seasonProgramId);
   }

   /**
    * @param hspf1AugustStartFullYearDetails
    * @return
    */
   @POST
   @Path("f1/update/august/fulldetail")
   @Produces("application/json")
   public HSPF1AugustStartFullYearDetails updateF1AugStartFullYearDetails(HSPF1AugustStartFullYearDetails hspf1AugustStartFullYearDetails) {
      LOGGER.debug("Calling 'fun:updateF1AugStartFullYearDetails'");
      if (hspf1AugustStartFullYearDetails != null)
         LOGGER.debug("Season ID  : " + hspf1AugustStartFullYearDetails.getSeasonId());
      else
         LOGGER.debug("Object is Null");
      return seasonServices.updateF1AugStartFullYearDetails(hspf1AugustStartFullYearDetails);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("f1/view/fieldsettings/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1FieldSettings getF1FieldSettings(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getF1FieldSettings'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1FieldSettings(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("f1/edit/fieldsettings/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1FieldSettings editF1FieldSettings(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editF1FieldSettings'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1FieldSettings(seasonProgramId);
   }

   /**
    * @param hspf1FieldSettings
    * @return
    */
   @POST
   @Path("f1/update/fieldsettings")
   @Produces("application/json")
   public HSPF1FieldSettings updateF1FieldSettings(HSPF1FieldSettings hspf1FieldSettings) {
      LOGGER.debug("Calling 'fun:updateF1FieldSettings'");
      if (hspf1FieldSettings != null)
         LOGGER.debug("Season ID  : " + hspf1FieldSettings.getSeasonId());
      else
         LOGGER.debug("Object is Null");
      return seasonServices.updateF1FieldSettings(hspf1FieldSettings);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("f1/view/allocation/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1ProgramAllocations getF1ProgramAllocation(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getF1ProgramAllocation'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1ProgramAllocations(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("f1/edit/allocation/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1ProgramAllocations editF1ProgramAllocation(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editF1ProgramAllocation'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1ProgramAllocations(seasonProgramId);
   }

   /**
    * @param hspf1ProgramAllocations
    * @return
    */
   @POST
   @Path("f1/update/allocation")
   @Produces("application/json")
   public HSPF1ProgramAllocations updateF1ProgramAllocation(HSPF1ProgramAllocations hspf1ProgramAllocations) {
      LOGGER.debug("Calling 'fun:updateF1ProgramAllocation'");
      if (hspf1ProgramAllocations != null)
         LOGGER.debug("Season ID  : " + hspf1ProgramAllocations.getSeasonId());
      else
         LOGGER.debug("Object is Null");

      return seasonServices.updateF1ProgramAllocation(hspf1ProgramAllocations);
   }

   // End of HSP F1 services

   // GHT Volunteer abroad services

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/va/details/view/{seasonProgramId}")
   @Produces("application/json")
   public SeasonGHTDetails getVADetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getVADetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getGHTVASeasonDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/va/base/view/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection1Base getVANameAndStatus(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTVASeasonNameAndStatus(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/va/date/section/view/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection2Dates getVADateDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTVASeasonDateDetails(seasonProgramId);
   }

   // edit
   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/va/details/edit/{seasonProgramId}")
   @Produces("application/json")
   public SeasonGHTDetails editVADetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTVASeasonDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/va/base/edit/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection1Base editVANameAndStatus(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTVASeasonNameAndStatus(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/va/date/section/edit/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection2Dates editVADateDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTVASeasonDateDetails(seasonProgramId);
   }

   // update
   /**
    * @param seasonGHTDetails
    * @return
    */
   @POST
   @Path("ght/va/details/update")
   @Consumes("application/json")
   public SeasonGHTDetails updateVADetails(SeasonGHTDetails seasonGHTDetails) {
      return seasonServices.updateGHTVASeasonDetails(seasonGHTDetails);
   }

   /**
    * @param ghtSection1Base
    * @return
    */
   @POST
   @Path("ght/va/base/update")
   @Consumes("application/json")
   public GHTSection1Base updateVANameAndStatus(GHTSection1Base ghtSection1Base) {
      return seasonServices.updateGHTVASeasonNameAndStatus(ghtSection1Base);
   }

   /**
    * @param ghtSection2Dates
    * @return
    */
   @POST
   @Path("ght/va/date/section/update")
   @Consumes("application/json")
   public GHTSection2Dates updateVADateDetails(GHTSection2Dates ghtSection2Dates) {
      return seasonServices.updateGHTVASeasonDateDetails(ghtSection2Dates);
   }

   // GHT work abroad services

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/wa/details/view/{seasonProgramId}")
   @Produces("application/json")
   public SeasonGHTDetails getWADetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTWASeasonDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/wa/base/view/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection1Base getWANameAndStatus(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTWASeasonNameAndStatus(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/wa/date/section/view/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection2Dates getWADateDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTWASeasonDateDetails(seasonProgramId);
   }

   // edit
   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/wa/details/edit/{seasonProgramId}")
   @Produces("application/json")
   public SeasonGHTDetails editWADetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTWASeasonDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/wa/base/edit/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection1Base editWANameAndStatus(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTWASeasonNameAndStatus(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/wa/date/section/edit/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection2Dates editWADateDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTWASeasonDateDetails(seasonProgramId);
   }

   // update
   /**
    * @param seasonGHTDetails
    * @return
    */
   @POST
   @Path("ght/wa/details/update")
   @Consumes("application/json")
   public SeasonGHTDetails updateWADetails(SeasonGHTDetails seasonGHTDetails) {
      return seasonServices.updateGHTWASeasonDetails(seasonGHTDetails);
   }

   /**
    * @param ghtSection1Base
    * @return
    */
   @POST
   @Path("ght/wa/base/update")
   @Consumes("application/json")
   public GHTSection1Base updateWANameAndStatus(GHTSection1Base ghtSection1Base) {
      return seasonServices.updateGHTWASeasonNameAndStatus(ghtSection1Base);
   }

   /**
    * @param ghtSection2Dates
    * @return
    */
   @POST
   @Path("ght/wa/date/section/update")
   @Consumes("application/json")
   public GHTSection2Dates updateWADateDetails(GHTSection2Dates ghtSection2Dates) {
      return seasonServices.updateGHTWASeasonDateDetails(ghtSection2Dates);
   }

   // GHT (HS abroad , Language School, Teach Abroad)
   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/hsa/view/details/{seasonProgramId}")
   @Produces("application/json")
   public SeasonGHTDetails getGHTHSAbroad(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTHSAbroad(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/hsa/edit/details/{seasonProgramId}")
   @Produces("application/json")
   public SeasonGHTDetails editGHTHSAbroad(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTHSAbroad(seasonProgramId);
   }

   /**
    * @param seasonGHTDetails
    * @return
    */
   @POST
   @Path("ght/hsa/update/details")
   @Produces("application/json")
   public SeasonGHTDetails updateGHTHSAbroad(SeasonGHTDetails seasonGHTDetails) {
      return seasonServices.updateGHTHSAbroad(seasonGHTDetails);
   }

   // sections
   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/hsa/view/sec1/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection1Base getGHTHSSection1BaseAbroad(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTHSSection1BaseAbroad(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/hsa/edit/sec1/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection1Base editGHTHSSection1BaseAbroad(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTHSSection1BaseAbroad(seasonProgramId);
   }

   /**
    * @param ghtSection1Base
    * @return
    */
   @POST
   @Path("ght/hsa/update/sec1")
   @Produces("application/json")
   public GHTSection1Base updateGHTHSSection1BaseAbroad(GHTSection1Base ghtSection1Base) {
      return seasonServices.updateGHTHSSection1BaseAbroad(ghtSection1Base);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/hsa/view/datesec/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection2Dates getGHTHSSection2DatesAbroad(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTHSSection2DatesAbroad(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/hsa/edit/datesec/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection2Dates editGHTHSSection2DatesAbroad(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTHSSection2DatesAbroad(seasonProgramId);
   }

   /**
    * @param ghtSection2Dates
    * @return
    */
   @POST
   @Path("ght/hsa/update/datesec")
   @Produces("application/json")
   public GHTSection2Dates updateGHTHSSection2DatesAbroad(GHTSection2Dates ghtSection2Dates) {
      return seasonServices.updateGHTHSSection2DatesAbroad(ghtSection2Dates);
   }

   // LS

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/ls/view/{seasonProgramId}")
   @Produces("application/json")
   public SeasonGHTDetails getGHTLanguageSchool(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTLanguageSchool(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/ls/edit/{seasonProgramId}")
   @Produces("application/json")
   public SeasonGHTDetails editGHTLanguageSchool(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTLanguageSchool(seasonProgramId);
   }

   /**
    * @param seasonGHTDetails
    * @return
    */
   @POST
   @Path("ght/ls/update")
   @Produces("application/json")
   public SeasonGHTDetails updateGHTLanguageSchool(SeasonGHTDetails seasonGHTDetails) {
      return seasonServices.updateGHTLanguageSchool(seasonGHTDetails);
   }

   // sections
   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/ls/view/sec1/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection1Base getGHTLanguageSchoolSection1(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTLanguageSchoolSection1(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/ls/edit/sec1/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection1Base editGHTLanguageSchoolSection1(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTLanguageSchoolSection1(seasonProgramId);
   }

   /**
    * @param seasonGHTDetails
    * @return
    */
   @POST
   @Path("ght/ls/update/sec1")
   @Produces("application/json")
   public GHTSection1Base updateGHTLanguageSchoolSection1(GHTSection1Base seasonGHTDetails) {
      return seasonServices.updateGHTLanguageSchoolSection1(seasonGHTDetails);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/ls/view/datesec/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection2Dates getGHTLanguageSchoolSection2Dates(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTLanguageSchoolSection2Dates(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/ls/edit/datesec/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection2Dates editGHTLanguageSchoolSection2Dates(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTLanguageSchoolSection2Dates(seasonProgramId);
   }

   /**
    * @param ghtSection2Dates
    * @return
    */
   @POST
   @Path("ght/ls/update/datesec")
   @Produces("application/json")
   public GHTSection2Dates updateGHTLanguageSchoolSection2Dates(GHTSection2Dates ghtSection2Dates) {
      return seasonServices.updateGHTLanguageSchoolSection2Dates(ghtSection2Dates);
   }

   // TA

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/ta/view/{seasonProgramId}")
   @Produces("application/json")
   public SeasonGHTDetails getGHTTeachAbroad(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTTeachAbroad(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/ta/edit/{seasonProgramId}")
   @Produces("application/json")
   public SeasonGHTDetails editGHTTeachAbroad(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTTeachAbroad(seasonProgramId);
   }

   /**
    * @param seasonGHTDetails
    * @return
    */
   @POST
   @Path("ght/ta/update")
   @Produces("application/json")
   public SeasonGHTDetails updateGHTTeachAbroad(SeasonGHTDetails seasonGHTDetails) {
      return seasonServices.updateGHTTeachAbroad(seasonGHTDetails);
   }

   // sections
   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/ta/view/sec1/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection1Base getGHTTeachAbroadSection1(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTTeachAbroadSection1(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/ta/edit/sec1/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection1Base editGHTTeachAbroadSection1(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTTeachAbroadSection1(seasonProgramId);
   }

   /**
    * @param ghtSection1Base
    * @return
    */
   @POST
   @Path("ght/ta/update/sec1")
   @Produces("application/json")
   public GHTSection1Base updateGHTTeachAbroadSection1(GHTSection1Base ghtSection1Base) {
      return seasonServices.updateGHTTeachAbroadSection1(ghtSection1Base);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/ta/view/datesec/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection2Dates getGHTTeachAbroadSection2Dates(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTTeachAbroadSection2Dates(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ght/ta/edit/datesec/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection2Dates editGHTTeachAbroadSection2Dates(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTTeachAbroadSection2Dates(seasonProgramId);
   }

   /**
    * @param ghtSection2Dates
    * @return
    */
   @POST
   @Path("ght/ta/update/datesec")
   @Produces("application/json")
   public GHTSection2Dates updateGHTTeachAbroadSection2Dates(GHTSection2Dates ghtSection2Dates) {
      return seasonServices.updateGHTTeachAbroadSection2Dates(ghtSection2Dates);
   }

   // work program --> Cap
   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/cap/view/details/{seasonProgramId}")
   @Produces("application/json")
   public SeasonWPCAPDetails getWPCAPDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPCAPDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/cap/edit/details/{seasonProgramId}")
   @Produces("application/json")
   public SeasonWPCAPDetails editWPCAPDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPCAPDetails(seasonProgramId);
   }

   /**
    * @param seasonWPCAPDetails
    * @return
    */
   @POST
   @Path("wp/cap/update/details")
   @Produces("application/json")
   public SeasonWPCAPDetails updateWPCAPDetails(SeasonWPCAPDetails seasonWPCAPDetails) {
      return seasonServices.updateWPCAPDetails(seasonWPCAPDetails);
   }

   // basic
   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/cap/view/basic/details/{seasonProgramId}")
   @Produces("application/json")
   public WPCAPBasicDetails getWPCAPBasicDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPCAPBasicDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/cap/edit/basic/details/{seasonProgramId}")
   @Produces("application/json")
   public WPCAPBasicDetails editWPCAPBasicDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPCAPBasicDetails(seasonProgramId);
   }

   /**
    * @param seasonWpcapBasicDetails
    * @return
    */
   @POST
   @Path("wp/cap/update/basic/details")
   @Produces("application/json")
   public WPCAPBasicDetails updateWPCAPBasicDetails(WPCAPBasicDetails seasonWpcapBasicDetails) {
      return seasonServices.updateWPCAPBasicDetails(seasonWpcapBasicDetails);
   }

   // internship
   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/cap/view/internship/details/{seasonProgramId}")
   @Produces("application/json")
   public WPCAPInternshipDetails getWPCAPInternshipDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPCAPInternshipDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/cap/edit/internship/details/{seasonProgramId}")
   @Produces("application/json")
   public WPCAPInternshipDetails editWPCAPInternshipDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPCAPInternshipDetails(seasonProgramId);
   }

   /**
    * @param wpcapInternshipDetails
    * @return
    */
   @POST
   @Path("wp/cap/update/internship/details")
   @Produces("application/json")
   public WPCAPInternshipDetails updateWPCAPInternshipDetails(WPCAPInternshipDetails wpcapInternshipDetails) {
      return seasonServices.updateWPCAPInternshipDetails(wpcapInternshipDetails);
   }

   // trainee details
   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/cap/view/trainee/details/{seasonProgramId}")
   @Produces("application/json")
   public WPCAPTraineeDetails getWPCAPTraineeDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPCAPTraineeDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/cap/edit/trainee/details/{seasonProgramId}")
   @Produces("application/json")
   public WPCAPTraineeDetails editWPCAPTraineeDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPCAPTraineeDetails(seasonProgramId);
   }

   /**
    * @param wpcapTraineeDetails
    * @return
    */
   @POST
   @Path("wp/cap/update/trainee/details")
   @Produces("application/json")
   public WPCAPTraineeDetails updateWPCAPTraineeDetails(WPCAPTraineeDetails wpcapTraineeDetails) {
      return seasonServices.updateWPCAPTraineeDetails(wpcapTraineeDetails);
   }

   // cap allocation
   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/cap/view/allocation/details/{seasonProgramId}")
   @Produces("application/json")
   public WPCAPProgramAllocations getWPCAPAllocationDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPCAPAllocationDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/cap/edit/allocation/details/{seasonProgramId}")
   @Produces("application/json")
   public WPCAPProgramAllocations editWPCAPAllocationDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPCAPAllocationDetails(seasonProgramId);
   }

   /**
    * @param wpcapProgramAllocations
    * @return
    */
   @POST
   @Path("wp/cap/update/allocation/details")
   @Consumes("application/json")
   @Produces("application/json")
   public WPCAPProgramAllocations updateWPCAPAllocationDetails(WPCAPProgramAllocations wpcapProgramAllocations) {
      return seasonServices.updateWPCAPAllocationDetails(wpcapProgramAllocations);
   }

   // Season WP summer, winter and spring services

   // wp summer full details

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/summer/view/details/{seasonProgramId}")
   @Produces("application/json")
   public SeasonWPDetails getWPSumDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSumDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/summer/edit/details/{seasonProgramId}")
   @Produces("application/json")
   public SeasonWPDetails editWPSumDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSumDetails(seasonProgramId);
   }

   /**
    * @param seasonWPDetails
    * @return
    */
   @POST
   @Path("wp/summer/update/details")
   @Consumes("application/json")
   @Produces("application/json")
   public SeasonWPDetails updateWPSumDetails(SeasonWPDetails seasonWPDetails) {
      return seasonServices.updateWPSumDetails(seasonWPDetails);
   }

   // wp summer basic details

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/summer/view/base/details/{seasonProgramId}")
   @Produces("application/json")
   public WPBasicDetail getWPSumBaseDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSumBaseDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/summer/edit/base/details/{seasonProgramId}")
   @Produces("application/json")
   public WPBasicDetail editWPSumBaseDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSumBaseDetails(seasonProgramId);
   }

   /**
    * @param wpBasicDetail
    * @return
    */
   @POST
   @Path("wp/summer/update/base/details")
   @Consumes("application/json")
   @Produces("application/json")
   public WPBasicDetail updateWPSumBaseDetails(WPBasicDetail wpBasicDetail) {
      return seasonServices.updateWPSumBaseDetails(wpBasicDetail);
   }

   // wp summer section 1 details

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/summer/view/section/one/details/{seasonProgramId}")
   @Produces("application/json")
   public WPSectionOne getWPSumSectionOneDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSumSectionOneDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/summer/edit/section/one/details/{seasonProgramId}")
   @Produces("application/json")
   public WPSectionOne editWPSumSectionOneDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSumSectionOneDetails(seasonProgramId);
   }

   /**
    * @param wpSectionOne
    * @return
    */
   @POST
   @Path("wp/summer/update/section/one/details")
   @Consumes("application/json")
   @Produces("application/json")
   public WPSectionOne updateWPSumSectionOneDetails(WPSectionOne wpSectionOne) {
      return seasonServices.updateWPSumSectionOneDetails(wpSectionOne);
   }

   // wp summer allocation details

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/summer/view/allocation/details/{seasonProgramId}")
   @Produces("application/json")
   public WPProgramAllocations getWPSumAllocationDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSumAllocationDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/summer/edit/allocation/details/{seasonProgramId}")
   @Produces("application/json")
   public WPProgramAllocations editWPSumAllocationDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSumAllocationDetails(seasonProgramId);
   }

   /**
    * @param wpProgramAllocations
    * @return
    */
   @POST
   @Path("wp/summer/update/allocation/details")
   @Consumes("application/json")
   @Produces("application/json")
   public WPProgramAllocations updateWPSumAllocationDetails(WPProgramAllocations wpProgramAllocations) {
      return seasonServices.updateWPSumAllocationDetails(wpProgramAllocations);
   }

   // wp spring full details

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/spring/view/details/{seasonProgramId}")
   @Produces("application/json")
   public SeasonWPDetails getWPSpringDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSpringDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/spring/edit/details/{seasonProgramId}")
   @Produces("application/json")
   public SeasonWPDetails editWPSpringDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSpringDetails(seasonProgramId);
   }

   /**
    * @param seasonWPDetails
    * @return
    */
   @POST
   @Path("wp/spring/update/details")
   @Consumes("application/json")
   @Produces("application/json")
   public SeasonWPDetails updateWPSpringDetails(SeasonWPDetails seasonWPDetails) {
      return seasonServices.updateWPSpringDetails(seasonWPDetails);
   }

   // wp spring basic details

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/spring/view/base/details/{seasonProgramId}")
   @Produces("application/json")
   public WPBasicDetail getWPSpringBaseDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSpringBaseDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/spring/edit/base/details/{seasonProgramId}")
   @Produces("application/json")
   public WPBasicDetail editWPSpringBaseDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSpringBaseDetails(seasonProgramId);
   }

   /**
    * @param wpBasicDetail
    * @return
    */
   @POST
   @Path("wp/spring/update/base/details")
   @Consumes("application/json")
   @Produces("application/json")
   public WPBasicDetail updateWPSpringBaseDetails(WPBasicDetail wpBasicDetail) {
      return seasonServices.updateWPSpringBaseDetails(wpBasicDetail);
   }

   // wp spring section 1 details

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/spring/view/section/one/details/{seasonProgramId}")
   @Produces("application/json")
   public WPSectionOne getWPSpringSectionOneDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSpringSectionOneDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/spring/edit/section/one/details/{seasonProgramId}")
   @Produces("application/json")
   public WPSectionOne editWPSpringSectionOneDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSpringSectionOneDetails(seasonProgramId);
   }

   /**
    * @param wpSectionOne
    * @return
    */
   @POST
   @Path("wp/spring/update/section/one/details")
   @Consumes("application/json")
   @Produces("application/json")
   public WPSectionOne updateWPSpringSectionOneDetails(WPSectionOne wpSectionOne) {
      return seasonServices.updateWPSpringSectionOneDetails(wpSectionOne);
   }

   // wp spring allocation details

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/spring/view/allocation/details/{seasonProgramId}")
   @Produces("application/json")
   public WPProgramAllocations getWPSpringAllocationDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSpringAllocationDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/spring/edit/allocation/details/{seasonProgramId}")
   @Produces("application/json")
   public WPProgramAllocations editWPSpringAllocationDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSpringAllocationDetails(seasonProgramId);
   }

   /**
    * @param wpProgramAllocations
    * @return
    */
   @POST
   @Path("wp/spring/update/allocation/details")
   @Consumes("application/json")
   @Produces("application/json")
   public WPProgramAllocations updateWPSpringAllocationDetails(WPProgramAllocations wpProgramAllocations) {
      return seasonServices.updateWPSpringAllocationDetails(wpProgramAllocations);
   }

   // wp winter full details

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/winter/view/details/{seasonProgramId}")
   @Produces("application/json")
   public SeasonWPDetails getWPWinterDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPWinterDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/winter/edit/details/{seasonProgramId}")
   @Produces("application/json")
   public SeasonWPDetails editWPWinterDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.editWPWinterDetails(seasonProgramId);
   }

   /**
    * @param seasonWPDetails
    * @return
    */
   @POST
   @Path("wp/winter/update/details")
   @Consumes("application/json")
   @Produces("application/json")
   public SeasonWPDetails updateWPWinterDetails(SeasonWPDetails seasonWPDetails) {
      return seasonServices.updateWPWinterDetails(seasonWPDetails);
   }

   // wp winter basic details

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/winter/view/base/details/{seasonProgramId}")
   @Produces("application/json")
   public WPBasicDetail getWPWinterBaseDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPWinterBaseDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/winter/edit/base/details/{seasonProgramId}")
   @Produces("application/json")
   public WPBasicDetail editWPWinterBaseDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.editWPWinterBaseDetails(seasonProgramId);
   }

   /**
    * @param wpBasicDetail
    * @return
    */
   @POST
   @Path("wp/winter/update/base/details")
   @Consumes("application/json")
   @Produces("application/json")
   public WPBasicDetail updateWPWinterBaseDetails(WPBasicDetail wpBasicDetail) {
      return seasonServices.updateWPWinterBaseDetails(wpBasicDetail);
   }

   // wp winter section 1 details

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/winter/view/section/one/details/{seasonProgramId}")
   @Produces("application/json")
   public WPSectionOne getWPWinterSectionOneDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPWinterSectionOneDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/winter/edit/section/one/details/{seasonProgramId}")
   @Produces("application/json")
   public WPSectionOne editWPWinterSectionOneDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.editWPWinterSectionOneDetails(seasonProgramId);
   }

   /**
    * @param wpSectionOne
    * @return
    */
   @POST
   @Path("wp/winter/update/section/one/details")
   @Consumes("application/json")
   @Produces("application/json")
   public WPSectionOne updateWPWinterSectionOneDetails(WPSectionOne wpSectionOne) {
      return seasonServices.updateWPWinterSectionOneDetails(wpSectionOne);
   }

   // wp winter allocation details

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/winter/view/allocation/details/{seasonProgramId}")
   @Produces("application/json")
   public WPProgramAllocations getWPWinterAllocationDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPWinterAllocationDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("wp/winter/edit/allocation/details/{seasonProgramId}")
   @Produces("application/json")
   public WPProgramAllocations editWPWinterAllocationDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPWinterAllocationDetails(seasonProgramId);
   }

   /**
    * @param wpProgramAllocations
    * @return
    */
   @POST
   @Path("wp/winter/update/allocation/details")
   @Consumes("application/json")
   @Produces("application/json")
   public WPProgramAllocations updateWPWinterAllocationDetails(WPProgramAllocations wpProgramAllocations) {
      return seasonServices.updateWPWinterAllocationDetails(wpProgramAllocations);
   }

   /**
    * @param cloneSeason
    * @return
    */
   @POST
   @Path("clone")
   @Consumes("application/json")
   @Produces("application/json")
   public CloneSeason cloneSeason(CloneSeason cloneSeason) {
      return seasonServices.cloneSeason(cloneSeason);
   }

   /**
    * @param seasonDepartmentNotes
    * @return
    */
   @POST
   @Path("add/department/note")
   @Consumes("application/json")
   @Produces("application/json")
   public SeasonDepartmentNotes addSeasonDepartmentNote(SeasonDepartmentNotes seasonDepartmentNotes) {
      return seasonServices.addSeasonDepartmentNote(seasonDepartmentNotes);
   }

   /**
    * @param seasonDepartmentDocument
    * @return
    */
   @POST
   @Path("add/department/doc")
   @Consumes("application/json")
   @Produces("application/json")
   public SeasonDepartmentDocument addSeasonDepartmentDoc(SeasonDepartmentDocument seasonDepartmentDocument) {
      return seasonServices.addSeasonDepartmentDoc(seasonDepartmentDocument);
   }

   /**
    * @param seasonProgramNote
    * @return
    */
   @POST
   @Path("add/program/note")
   @Consumes("application/json")
   @Produces("application/json")
   public SeasonProgramNote addSeasonProgramNote(SeasonProgramNote seasonProgramNote) {
      return seasonServices.addSeasonProgramNote(seasonProgramNote);
   }

   /**
    * @param seasonProgramDocument
    * @return
    */
   @POST
   @Path("add/program/doc")
   @Consumes("application/json")
   @Produces("application/json")
   public SeasonProgramDocument addSeasonProgramDoc(SeasonProgramDocument seasonProgramDocument) {
      return seasonServices.addSeasonProgramDoc(seasonProgramDocument);
   }

   /**
    * @return
    */
   @GET
   @Path("doc/type")
   @Produces("application/json")
   public DocumentTypes getDocumentTypes() {
      return seasonServices.getDocumentTypes();
   }

   // HST- STP- IHP Program

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ihp/details/view/{seasonProgramId}")
   @Produces("application/json")
   public SeasonHspStpIhpDetails getIHPDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'getIHPDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getIHPDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ihp/details/edit/{seasonProgramId}")
   @Produces("application/json")
   public SeasonHspStpIhpDetails editIHPDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'getIHPDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getIHPDetails(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ihp/name/details/edit/{seasonProgramId}")
   @Produces("application/json")
   public IHPNameAndStatus editIHPNameAndStatus(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling  'getIHPProgramDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getIHPNameAndStatus(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ihp/date/details/edit/{seasonProgramId}")
   @Produces("application/json")
   public IHPDates editIHPDates(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling  'getIHPProgramDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getIHPDates(seasonProgramId);
   }

   /**
    * @param seasonProgramId
    * @return
    */
   @GET
   @Path("ihp/program/config/edit/{seasonProgramId}")
   @Produces("application/json")
   public IHPProgramConfiguration editIHPProgramConfigurationDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'getIHPProgramConfigurationDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getIHPProgramConfigurationDetails(seasonProgramId);
   }

   // Update IHP

   /**
    * @param seasonHspStpIhpDetails
    * @return
    */
   @POST
   @Path("ihp/details/update")
   @Consumes("application/json")
   @Produces("application/json")
   public SeasonHspStpIhpDetails updateIHPDetails(SeasonHspStpIhpDetails seasonHspStpIhpDetails) {
      LOGGER.debug("Calling 'updateIHPDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonHspStpIhpDetails.getSeasonProgramId());
      return seasonServices.updateIHPDetails(seasonHspStpIhpDetails);
   }

   /**
    * @param ihpNameAndStatus
    * @return
    */
   @POST
   @Path("ihp/name/details/update")
   @Consumes("application/json")
   @Produces("application/json")
   public IHPNameAndStatus updateIHPNameAndStatus(IHPNameAndStatus ihpNameAndStatus) {
      LOGGER.debug("Calling  'getIHPProgramDetails'");
      LOGGER.debug("seasonProgramId  : " + ihpNameAndStatus.getSeasonProgramId());
      return seasonServices.updateIHPNameAndStatus(ihpNameAndStatus);
   }

   /**
    * @param ihpDates
    * @return
    */
   @POST
   @Path("ihp/date/details/update")
   @Consumes("application/json")
   @Produces("application/json")
   public IHPDates updateIHPDates(IHPDates ihpDates) {
      LOGGER.debug("Calling  'getIHPProgramDetails'");
      LOGGER.debug("seasonProgramId  : " + ihpDates.getSeasonProgramId());
      return seasonServices.updateIHPDates(ihpDates);
   }

   /**
    * @param ihpProgramConfiguration
    * @return
    */
   @POST
   @Path("ihp/program/config/update")
   @Consumes("application/json")
   @Produces("application/json")
   public IHPProgramConfiguration updateIHPProgramConfigurationDetails(IHPProgramConfiguration ihpProgramConfiguration) {
      LOGGER.debug("Calling 'updateIHPProgramConfigurationDetails'");
      LOGGER.debug("seasonProgramId  : " + ihpProgramConfiguration.getSeasonProgramId());
      return seasonServices.updateIHPProgramConfigurationDetails(ihpProgramConfiguration);
   }

}
