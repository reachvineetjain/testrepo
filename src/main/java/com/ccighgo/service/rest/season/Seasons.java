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
import com.ccighgo.service.transport.season.beans.cloneseason.CloneSeason;
import com.ccighgo.service.transport.season.beans.seasondepartdoc.SeasonDepartmentDocument;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.GHTSection1Base;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.GHTSection2Dates;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.SeasonGHTDetails;
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
import com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails.WPCAPTraineeDetails;

@Path("/season/")
@Produces("application/json")
@Consumes("application/json")
public class Seasons {

   private static final Logger LOGGER = Logger.getLogger(Seasons.class);

   @Autowired
   SeasonServiceInterface seasonServices;

   @GET
   @Path("ping/{input}")
   @Produces("text/plain")
   public String ping(@PathParam("input") String input) {
      LOGGER.debug("Pinging !! ( value : " + input + " )");
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
      LOGGER.debug("Season ID  : " + id);
      SeasonBean result = seasonServices.editSeason(id);
      return result;
   }

   @GET
   @Path("view/{id}")
   @Produces("application/json")
   public SeasonBean view(@PathParam("id") String id) {
      LOGGER.debug("Calling Get Season By Id 'func:View'");
      LOGGER.debug("Season ID  : " + id);
      SeasonBean result = seasonServices.viewSeason(id);
      return result;
   }

   @POST
   @Path("create")
   @Consumes("application/json")
   public SeasonBean createSeason(SeasonBean seasonBean) {
      LOGGER.debug("Calling Create Season function 'func:createSeason'");
      if (seasonBean != null)
         LOGGER.debug("Season ID  : " + seasonBean.getSeasonId());
      else
         LOGGER.debug("Object SeasonBean is NULL !!");
      return seasonServices.createSeason(seasonBean);
   }

   @POST
   @Path("update")
   @Consumes("application/json")
   public SeasonBean updateSeason(SeasonBean seasonBean) {
      LOGGER.debug("Calling Update Season'func:updateSeason'");
      if (seasonBean != null)
         LOGGER.debug("Season ID  : " + seasonBean.getSeasonId());
      else
         LOGGER.debug("Object SeasonBean is NULL !!");
      return seasonServices.updateSeason(seasonBean);
   }

   @GET
   @Path("deleteSeason/{id}")
   @Produces("application/json")
   public String deleteSeason(@PathParam("id") String id) {
      LOGGER.debug("Calling Delete Season'func:deleteSeason'");
      LOGGER.debug("Season ID  : " + id);
      return seasonServices.deleteSeason(id);
   }

   @GET
   @Path("program/season/{seasonId}")
   @Produces("application/json")
   public SeasonPrograms getSeasonProgram(@PathParam("seasonId") String seasonId) {
      LOGGER.debug("Calling 'fun:getSeasonProgram' ");
      LOGGER.debug("Season ID  : " + seasonId);
      return seasonServices.getSeasonPrograms(seasonId);
   }

   @GET
   @Path("status")
   @Produces("application/json")
   public SeasonStatuses getSeasonStatus() {
      LOGGER.debug("Calling 'fun: getSeasonStatus' ");
      return seasonServices.getSeasonStatus();
   }

   // HSP J1HS view services

   @GET
   @Path("j1hs/details/view/{seasonProgramId}")
   @Produces("application/json")
   public SeasonHspJ1HSDetails getJ1Details(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getJ1Details'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPJ1HSSeasonDetails(seasonProgramId);
   }

   @GET
   @Path("j1hs/base/view/{seasonProgramId}")
   @Produces("application/json")
   public J1HSBasicDetail getJ1NameAndStatus(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling  'fun:getJ1NameAndStatus'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPJ1HSSeasonNameAndStatus(seasonProgramId);
   }

   @GET
   @Path("j1hs/jan/view/{seasonProgramId}")
   @Produces("application/json")
   public J1HSJanStart getJ1JanStartDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getJ1JanStartDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPJ1HSSeasonJanStartDetails(seasonProgramId);
   }

   @GET
   @Path("j1hs/aug/view/{seasonProgramId}")
   @Produces("application/json")
   public J1HSAugStart getJ1AugStartDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getJ1AugStartDetails'");
      LOGGER.debug("seasonProgramId :" + seasonProgramId);
      return seasonServices.getHSPJ1HSSeasonAugStartDetails(seasonProgramId);
   }

   @GET
   @Path("j1hs/field/view/{seasonProgramId}/settings")
   @Produces("application/json")
   public J1HSFieldSettings getJ1FieldSettings(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getJ1FieldSettings'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPJ1HSSeasonFieldSettings(seasonProgramId);
   }

   @GET
   @Path("j1hs/program/view/{seasonProgramId}/allocation")
   @Produces("application/json")
   public J1HSProgramAllocations getJ1ProgramAllocation(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getJ1ProgramAllocation'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPJ1HSSeasonProgramAllocation(seasonProgramId);
   }

   // HSP J1HS edit services

   @GET
   @Path("j1hs/details/edit/{seasonProgramId}")
   @Produces("application/json")
   public SeasonHspJ1HSDetails editJ1Details(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editJ1Details'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPJ1HSSeasonDetails(seasonProgramId);
   }

   @GET
   @Path("j1hs/base/edit/{seasonProgramId}")
   @Produces("application/json")
   public J1HSBasicDetail editJ1NameAndStatus(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editJ1NameAndStatus'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPJ1HSSeasonNameAndStatus(seasonProgramId);
   }

   @GET
   @Path("j1hs/jan/edit/{seasonProgramId}")
   @Produces("application/json")
   public J1HSJanStart editJ1JanStartDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editJ1JanStartDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPJ1HSSeasonJanStartDetails(seasonProgramId);
   }

   @GET
   @Path("j1hs/aug/edit/{seasonProgramId}")
   @Produces("application/json")
   public J1HSAugStart editJ1AugStartDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editJ1AugStartDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPJ1HSSeasonAugStartDetails(seasonProgramId);
   }

   @GET
   @Path("j1hs/field/edit/{seasonProgramId}/settings")
   @Produces("application/json")
   public J1HSFieldSettings editJ1FieldSettings(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editJ1FieldSettings'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPJ1HSSeasonFieldSettings(seasonProgramId);
   }

   @GET
   @Path("j1hs/program/edit/{seasonProgramId}/allocation")
   @Produces("application/json")
   public J1HSProgramAllocations editJ1ProgramAllocation(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editJ1ProgramAllocation'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPJ1HSSeasonProgramAllocation(seasonProgramId);
   }

   // HSP J1HS update services

   @POST
   @Path("j1hs/details/update")
   @Consumes("application/json")
   public SeasonHspJ1HSDetails updateJ1Details(SeasonHspJ1HSDetails seasonHspJ1HSDetails) {
      LOGGER.debug("Calling 'fun:updateJ1Details' ");
      if (seasonHspJ1HSDetails != null)
         LOGGER.debug("Season ID  : " + seasonHspJ1HSDetails.getSeasonId());
      else
         LOGGER.debug("Object seasonHspJ1HSDetails is Null");
      return seasonServices.updateHSPJ1HSSeasonDetails(seasonHspJ1HSDetails);
   }

   @POST
   @Path("j1hs/base/update")
   @Consumes("application/json")
   public J1HSBasicDetail updateJ1NameAndStatus(J1HSBasicDetail j1hsBasicDetail) {
      LOGGER.debug("Calling 'fun:updateJ1NameAndStatus'");
      if (j1hsBasicDetail != null)
         LOGGER.debug("Season ID  : " + j1hsBasicDetail.getSeasonId());
      else
         LOGGER.debug("Object updateJ1NameAndStatus  is Null");
      return seasonServices.updateHSPJ1HSSeasonNameAndStatus(j1hsBasicDetail);
   }

   @POST
   @Path("j1hs/jan/update")
   @Consumes("application/json")
   public J1HSJanStart updateJ1JanStartDetails(J1HSJanStart j1hsJanStart) {
      LOGGER.debug("Calling 'fun:updateJ1JanStartDetails'");
      LOGGER.debug("Season ID  : " + j1hsJanStart.getSeasonId());
      return seasonServices.updateHSPJ1HSSeasonJanStartDetails(j1hsJanStart);
   }

   @POST
   @Path("j1hs/aug/update")
   @Consumes("application/json")
   public J1HSAugStart updateJ1AugStartDetails(J1HSAugStart j1hsAugStart) {
      LOGGER.debug("Calling ");
      if (j1hsAugStart != null)
         LOGGER.debug("Season ID  :" + j1hsAugStart.getSeasonId());
      else
         LOGGER.debug("Object j1hsAugStart is Null");
      return seasonServices.updateHSPJ1HSSeasonAugStartDetails(j1hsAugStart);
   }

   @POST
   @Path("j1hs/field/settings/update")
   @Consumes("application/json")
   public J1HSFieldSettings updateJ1FieldSettings(J1HSFieldSettings j1hsFieldSettings) {
      LOGGER.debug("Calling 'fun:updateJ1FieldSettings'");
      if (j1hsFieldSettings != null)
         LOGGER.debug("Season ID  : " + j1hsFieldSettings.getSeasonId());
      else
         LOGGER.debug("Object j1hsFieldSettings is Null");
      return seasonServices.updateHSPJ1HSSeasonFieldSettings(j1hsFieldSettings);
   }

   @POST
   @Path("j1hs/program/update/allocation")
   @Consumes("application/json")
   public J1HSProgramAllocations updateJ1ProgramAllocation(J1HSProgramAllocations j1hsProgramAllocations) {
      LOGGER.debug("Calling  'fun:updateJ1ProgramAllocation'");
      if (j1hsProgramAllocations != null)
         LOGGER.debug("Season ID  : " + j1hsProgramAllocations.getSeasonId());
      else
         LOGGER.debug("Object j1hsProgramAllocations is Null");

      return seasonServices.updateHSPJ1HSSeasonProgramAllocation(j1hsProgramAllocations);
   }

   // HSP F1 services
   /**
    * view - Edit- update
    */

   @GET
   @Path("f1/view/details/{seasonProgramId}")
   @Produces("application/json")
   public SeasonHSPF1Details getF1Details(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getF1Details'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getSeasonHSPF1Details(seasonProgramId);
   }

   @GET
   @Path("f1/edit/details/{seasonProgramId}")
   @Produces("application/json")
   public SeasonHSPF1Details editF1Details(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editF1Details'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getSeasonHSPF1Details(seasonProgramId);
   }

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

   @GET
   @Path("f1/view/basicdetails/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1BasicDetails getF1NameAndStatus(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getF1NameAndStatus'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1NameAndStatus(seasonProgramId);
   }

   @GET
   @Path("f1/edit/basicdetails/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1BasicDetails editF1NameAndStatus(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editF1NameAndStatus'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1NameAndStatus(seasonProgramId);
   }

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

   @GET
   @Path("f1/view/accounting/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1Accounting getF1Accounting(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getF1Accounting'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1Accounting(seasonProgramId);
   }

   @GET
   @Path("f1/edit/accounting/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1Accounting editF1Accounting(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editF1Accounting'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1Accounting(seasonProgramId);
   }

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

   @GET
   @Path("f1/view/jan/startdetails/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1JanuaryStart2NdSemesterDetails getF1JanStart2NdSemesterDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getF1JanStart2NdSemesterDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1JanuaryStart2NdSemesterDetails(seasonProgramId);
   }

   @GET
   @Path("f1/edit/jan/startdetails/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1JanuaryStart2NdSemesterDetails editF1JanStart2NdSemesterDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editF1JanStart2NdSemesterDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1JanuaryStart2NdSemesterDetails(seasonProgramId);
   }

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

   @GET
   @Path("f1/view/jan/fulldetails/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1JanuaryStartFullYearDetail getF1JanStartFullYearDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getF1JanStartFullYearDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1JanuaryStartFullYearDetails(seasonProgramId);
   }

   @GET
   @Path("f1/edit/jan/fulldetails/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1JanuaryStartFullYearDetail editF1JanStartFullYearDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editF1JanStartFullYearDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1JanuaryStartFullYearDetails(seasonProgramId);
   }

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

   @GET
   @Path("f1/view/august/startdetail/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1AugustStart1StSemesterDetails getF1AugStart1StSemesterDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getF1AugStart1StSemesterDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1AugustStart1StSemesterDetails(seasonProgramId);
   }

   @GET
   @Path("f1/edit/august/startdetail/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1AugustStart1StSemesterDetails editF1AugStart1StSemesterDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editF1AugStart1StSemesterDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1AugustStart1StSemesterDetails(seasonProgramId);
   }

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

   @GET
   @Path("f1/view/august/fulldetail/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1AugustStartFullYearDetails getF1AugStartFullYearDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getF1AugStartFullYearDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1AugustStartFullYearDetails(seasonProgramId);
   }

   @GET
   @Path("f1/edit/august/fulldetail/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1AugustStartFullYearDetails editF1AugStartFullYearDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editF1AugStartFullYearDetails'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1AugustStartFullYearDetails(seasonProgramId);
   }

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

   @GET
   @Path("f1/view/fieldsettings/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1FieldSettings getF1FieldSettings(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getF1FieldSettings'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1FieldSettings(seasonProgramId);
   }

   @GET
   @Path("f1/edit/fieldsettings/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1FieldSettings editF1FieldSettings(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editF1FieldSettings'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1FieldSettings(seasonProgramId);
   }

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

   @GET
   @Path("f1/view/allocation/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1ProgramAllocations getF1ProgramAllocation(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:getF1ProgramAllocation'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1ProgramAllocations(seasonProgramId);
   }

   @GET
   @Path("f1/edit/allocation/{seasonProgramId}")
   @Produces("application/json")
   public HSPF1ProgramAllocations editF1ProgramAllocation(@PathParam("seasonProgramId") String seasonProgramId) {
      LOGGER.debug("Calling 'fun:editF1ProgramAllocation'");
      LOGGER.debug("seasonProgramId  : " + seasonProgramId);
      return seasonServices.getHSPF1ProgramAllocations(seasonProgramId);
   }

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

   @GET
   @Path("ght/va/details/view/{seasonProgramId}")
   @Produces("application/json")
   public SeasonGHTDetails getVADetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTVASeasonDetails(seasonProgramId);
   }

   @GET
   @Path("ght/va/base/view/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection1Base getVANameAndStatus(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTVASeasonNameAndStatus(seasonProgramId);
   }

   @GET
   @Path("ght/va/date/section/view/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection2Dates getVADateDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTVASeasonDateDetails(seasonProgramId);
   }

   // edit
   @GET
   @Path("ght/va/details/edit/{seasonProgramId}")
   @Produces("application/json")
   public SeasonGHTDetails editVADetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTVASeasonDetails(seasonProgramId);
   }

   @GET
   @Path("ght/va/base/edit/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection1Base editVANameAndStatus(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTVASeasonNameAndStatus(seasonProgramId);
   }

   @GET
   @Path("ght/va/date/section/edit/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection2Dates editVADateDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTVASeasonDateDetails(seasonProgramId);
   }

   // update
   @POST
   @Path("ght/va/details/update")
   @Consumes("application/json")
   public SeasonGHTDetails updateVADetails(SeasonGHTDetails seasonGHTDetails) {
      return seasonServices.updateGHTVASeasonDetails(seasonGHTDetails);
   }

   @POST
   @Path("ght/va/base/update")
   @Consumes("application/json")
   public GHTSection1Base updateVANameAndStatus(GHTSection1Base ghtSection1Base) {
      return seasonServices.updateGHTVASeasonNameAndStatus(ghtSection1Base);
   }

   @POST
   @Path("ght/va/date/section/update")
   @Consumes("application/json")
   public GHTSection2Dates updateVADateDetails(GHTSection2Dates ghtSection2Dates) {
      return seasonServices.updateGHTVASeasonDateDetails(ghtSection2Dates);
   }

   // GHT work abroad services

   @GET
   @Path("ght/wa/details/view/{seasonProgramId}")
   @Produces("application/json")
   public SeasonGHTDetails getWADetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTWASeasonDetails(seasonProgramId);
   }

   @GET
   @Path("ght/wa/base/view/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection1Base getWANameAndStatus(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTWASeasonNameAndStatus(seasonProgramId);
   }

   @GET
   @Path("ght/wa/date/section/view/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection2Dates getWADateDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTWASeasonDateDetails(seasonProgramId);
   }

   // edit
   @GET
   @Path("ght/wa/details/edit/{seasonProgramId}")
   @Produces("application/json")
   public SeasonGHTDetails editWADetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTWASeasonDetails(seasonProgramId);
   }

   @GET
   @Path("ght/wa/base/edit/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection1Base editWANameAndStatus(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTWASeasonNameAndStatus(seasonProgramId);
   }

   @GET
   @Path("ght/wa/date/section/edit/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection2Dates editWADateDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTWASeasonDateDetails(seasonProgramId);
   }

   // update
   @POST
   @Path("ght/wa/details/update")
   @Consumes("application/json")
   public SeasonGHTDetails updateWADetails(SeasonGHTDetails seasonGHTDetails) {
      return seasonServices.updateGHTWASeasonDetails(seasonGHTDetails);
   }

   @POST
   @Path("ght/wa/base/update")
   @Consumes("application/json")
   public GHTSection1Base updateWANameAndStatus(GHTSection1Base ghtSection1Base) {
      return seasonServices.updateGHTWASeasonNameAndStatus(ghtSection1Base);
   }

   @POST
   @Path("ght/wa/date/section/update")
   @Consumes("application/json")
   public GHTSection2Dates updateWADateDetails(GHTSection2Dates ghtSection2Dates) {
      return seasonServices.updateGHTWASeasonDateDetails(ghtSection2Dates);
   }

   // GHT (HS abroad , Language School, Teach Abroad)
   @GET
   @Path("ght/hsa/view/details/{seasonProgramId}")
   @Produces("application/json")
   public SeasonGHTDetails getGHTHSAbroad(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTHSAbroad(seasonProgramId);
   }

   @GET
   @Path("ght/hsa/edit/details/{seasonProgramId}")
   @Produces("application/json")
   public SeasonGHTDetails editGHTHSAbroad(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTHSAbroad(seasonProgramId);
   }

   @POST
   @Path("ght/hsa/update/details")
   @Produces("application/json")
   public SeasonGHTDetails updateGHTHSAbroad(SeasonGHTDetails seasonGHTDetails) {
      return seasonServices.updateGHTHSAbroad(seasonGHTDetails);
   }

   // sections
   @GET
   @Path("ght/hsa/view/sec1/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection1Base getGHTHSSection1BaseAbroad(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTHSSection1BaseAbroad(seasonProgramId);
   }

   @GET
   @Path("ght/hsa/edit/sec1/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection1Base editGHTHSSection1BaseAbroad(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTHSSection1BaseAbroad(seasonProgramId);
   }

   @POST
   @Path("ght/hsa/update/sec1")
   @Produces("application/json")
   public GHTSection1Base updateGHTHSSection1BaseAbroad(GHTSection1Base ghtSection1Base) {
      return seasonServices.updateGHTHSSection1BaseAbroad(ghtSection1Base);
   }

   @GET
   @Path("ght/hsa/view/datesec/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection2Dates getGHTHSSection2DatesAbroad(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTHSSection2DatesAbroad(seasonProgramId);
   }

   @GET
   @Path("ght/hsa/edit/datesec/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection2Dates editGHTHSSection2DatesAbroad(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTHSSection2DatesAbroad(seasonProgramId);
   }

   @POST
   @Path("ght/hsa/update/datesec")
   @Produces("application/json")
   public GHTSection2Dates updateGHTHSSection2DatesAbroad(GHTSection2Dates ghtSection2Dates) {
      return seasonServices.updateGHTHSSection2DatesAbroad(ghtSection2Dates);
   }

   // LS

   @GET
   @Path("ght/ls/view/{seasonProgramId}")
   @Produces("application/json")
   public SeasonGHTDetails getGHTLanguageSchool(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTLanguageSchool(seasonProgramId);
   }

   @GET
   @Path("ght/ls/edit/{seasonProgramId}")
   @Produces("application/json")
   public SeasonGHTDetails editGHTLanguageSchool(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTLanguageSchool(seasonProgramId);
   }

   @POST
   @Path("ght/ls/update")
   @Produces("application/json")
   public SeasonGHTDetails updateGHTLanguageSchool(SeasonGHTDetails seasonGHTDetails) {
      return seasonServices.updateGHTLanguageSchool(seasonGHTDetails);
   }

   // sections
   @GET
   @Path("ght/ls/view/sec1/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection1Base getGHTLanguageSchoolSection1(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTLanguageSchoolSection1(seasonProgramId);
   }

   @GET
   @Path("ght/ls/edit/sec1/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection1Base editGHTLanguageSchoolSection1(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTLanguageSchoolSection1(seasonProgramId);
   }

   @POST
   @Path("ght/ls/update/sec1")
   @Produces("application/json")
   public GHTSection1Base updateGHTLanguageSchoolSection1(GHTSection1Base seasonGHTDetails) {
      return seasonServices.updateGHTLanguageSchoolSection1(seasonGHTDetails);
   }

   @GET
   @Path("ght/ls/view/datesec/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection2Dates getGHTLanguageSchoolSection2Dates(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTLanguageSchoolSection2Dates(seasonProgramId);
   }

   @GET
   @Path("ght/ls/edit/datesec/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection2Dates editGHTLanguageSchoolSection2Dates(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTLanguageSchoolSection2Dates(seasonProgramId);
   }

   @POST
   @Path("ght/ls/update/datesec")
   @Produces("application/json")
   public GHTSection2Dates updateGHTLanguageSchoolSection2Dates(GHTSection2Dates ghtSection2Dates) {
      return seasonServices.updateGHTLanguageSchoolSection2Dates(ghtSection2Dates);
   }

   // TA

   @GET
   @Path("ght/ta/view/{seasonProgramId}")
   @Produces("application/json")
   public SeasonGHTDetails getGHTTeachAbroad(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTTeachAbroad(seasonProgramId);
   }

   @GET
   @Path("ght/ta/edit/{seasonProgramId}")
   @Produces("application/json")
   public SeasonGHTDetails editGHTTeachAbroad(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTTeachAbroad(seasonProgramId);
   }

   @POST
   @Path("ght/ta/update")
   @Produces("application/json")
   public SeasonGHTDetails updateGHTTeachAbroad(SeasonGHTDetails seasonGHTDetails) {
      return seasonServices.updateGHTTeachAbroad(seasonGHTDetails);
   }

   // sections
   @GET
   @Path("ght/ta/view/sec1/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection1Base getGHTTeachAbroadSection1(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTTeachAbroadSection1(seasonProgramId);
   }

   @GET
   @Path("ght/ta/edit/sec1/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection1Base editGHTTeachAbroadSection1(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTTeachAbroadSection1(seasonProgramId);
   }

   @POST
   @Path("ght/ta/update/sec1")
   @Produces("application/json")
   public GHTSection1Base updateGHTTeachAbroadSection1(GHTSection1Base ghtSection1Base) {
      return seasonServices.updateGHTTeachAbroadSection1(ghtSection1Base);
   }

   @GET
   @Path("ght/ta/view/datesec/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection2Dates getGHTTeachAbroadSection2Dates(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTTeachAbroadSection2Dates(seasonProgramId);
   }

   @GET
   @Path("ght/ta/edit/datesec/{seasonProgramId}")
   @Produces("application/json")
   public GHTSection2Dates editGHTTeachAbroadSection2Dates(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getGHTTeachAbroadSection2Dates(seasonProgramId);
   }

   @POST
   @Path("ght/ta/update/datesec")
   @Produces("application/json")
   public GHTSection2Dates updateGHTTeachAbroadSection2Dates(GHTSection2Dates ghtSection2Dates) {
      return seasonServices.updateGHTTeachAbroadSection2Dates(ghtSection2Dates);
   }

   // work program --> Cap
   @GET
   @Path("wp/cap/view/details/{seasonProgramId}")
   @Produces("application/json")
   public SeasonWPCAPDetails getWPCAPDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPCAPDetails(seasonProgramId);
   }

   @GET
   @Path("wp/cap/edit/details/{seasonProgramId}")
   @Produces("application/json")
   public SeasonWPCAPDetails editWPCAPDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPCAPDetails(seasonProgramId);
   }

   @POST
   @Path("wp/cap/update/details")
   @Produces("application/json")
   public SeasonWPCAPDetails updateWPCAPDetails(SeasonWPCAPDetails seasonWPCAPDetails) {
      return seasonServices.updateWPCAPDetails(seasonWPCAPDetails);
   }

   // basic
   @GET
   @Path("wp/cap/view/basic/details/{seasonProgramId}")
   @Produces("application/json")
   public WPCAPBasicDetails getWPCAPBasicDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPCAPBasicDetails(seasonProgramId);
   }

   @GET
   @Path("wp/cap/edit/basic/details/{seasonProgramId}")
   @Produces("application/json")
   public WPCAPBasicDetails editWPCAPBasicDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPCAPBasicDetails(seasonProgramId);
   }

   @POST
   @Path("wp/cap/update/basic/details")
   @Produces("application/json")
   public WPCAPBasicDetails updateWPCAPBasicDetails(WPCAPBasicDetails seasonWpcapBasicDetails) {
      return seasonServices.updateWPCAPBasicDetails(seasonWpcapBasicDetails);
   }

   // internship
   @GET
   @Path("wp/cap/view/internship/details/{seasonProgramId}")
   @Produces("application/json")
   public WPCAPInternshipDetails getWPCAPInternshipDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPCAPInternshipDetails(seasonProgramId);
   }

   @GET
   @Path("wp/cap/edit/internship/details/{seasonProgramId}")
   @Produces("application/json")
   public WPCAPInternshipDetails editWPCAPInternshipDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPCAPInternshipDetails(seasonProgramId);
   }

   @POST
   @Path("wp/cap/update/internship/details")
   @Produces("application/json")
   public WPCAPInternshipDetails updateWPCAPInternshipDetails(WPCAPInternshipDetails wpcapInternshipDetails) {
      return seasonServices.updateWPCAPInternshipDetails(wpcapInternshipDetails);
   }

   // trainee details
   @GET
   @Path("wp/cap/view/trainee/details/{seasonProgramId}")
   @Produces("application/json")
   public WPCAPTraineeDetails getWPCAPTraineeDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPCAPTraineeDetails(seasonProgramId);
   }

   @GET
   @Path("wp/cap/edit/trainee/details/{seasonProgramId}")
   @Produces("application/json")
   public WPCAPTraineeDetails editWPCAPTraineeDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPCAPTraineeDetails(seasonProgramId);
   }

   @POST
   @Path("wp/cap/update/trainee/details")
   @Produces("application/json")
   public WPCAPTraineeDetails updateWPCAPTraineeDetails(WPCAPTraineeDetails wpcapTraineeDetails) {
      return seasonServices.updateWPCAPTraineeDetails(wpcapTraineeDetails);
   }

   // Season WP summer, winter and spring services

   // wp summer full details

   @GET
   @Path("wp/summer/view/details/{seasonProgramId}")
   @Produces("application/json")
   public SeasonWPDetails getWPSumDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSumDetails(seasonProgramId);
   }

   @GET
   @Path("wp/summer/edit/details/{seasonProgramId}")
   @Produces("application/json")
   public SeasonWPDetails editWPSumDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSumDetails(seasonProgramId);
   }

   @POST
   @Path("wp/summer/update/details")
   @Consumes("application/json")
   @Produces("application/json")
   public SeasonWPDetails updateWPSumDetails(SeasonWPDetails seasonWPDetails) {
      return seasonServices.updateWPSumDetails(seasonWPDetails);
   }

   // wp summer basic details

   @GET
   @Path("wp/summer/view/base/details/{seasonProgramId}")
   @Produces("application/json")
   public WPBasicDetail getWPSumBaseDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSumBaseDetails(seasonProgramId);
   }

   @GET
   @Path("wp/summer/edit/base/details/{seasonProgramId}")
   @Produces("application/json")
   public WPBasicDetail editWPSumBaseDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSumBaseDetails(seasonProgramId);
   }

   @POST
   @Path("wp/summer/update/base/details")
   @Consumes("application/json")
   @Produces("application/json")
   public WPBasicDetail updateWPSumBaseDetails(WPBasicDetail wpBasicDetail) {
      return seasonServices.updateWPSumBaseDetails(wpBasicDetail);
   }

   // wp summer section 1 details

   @GET
   @Path("wp/summer/view/section/one/details/{seasonProgramId}")
   @Produces("application/json")
   public WPSectionOne getWPSumSectionOneDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSumSectionOneDetails(seasonProgramId);
   }

   @GET
   @Path("wp/summer/edit/section/one/details/{seasonProgramId}")
   @Produces("application/json")
   public WPSectionOne editWPSumSectionOneDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSumSectionOneDetails(seasonProgramId);
   }

   @POST
   @Path("wp/summer/update/section/one/details")
   @Consumes("application/json")
   @Produces("application/json")
   public WPSectionOne updateWPSumSectionOneDetails(WPSectionOne wpSectionOne) {
      return seasonServices.updateWPSumSectionOneDetails(wpSectionOne);
   }

   // wp summer allocation details

   @GET
   @Path("wp/summer/view/allocation/details/{seasonProgramId}")
   @Produces("application/json")
   public WPSectionOne getWPSumAllocationDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSumAllocationDetails(seasonProgramId);
   }

   @GET
   @Path("wp/summer/edit/allocation/details/{seasonProgramId}")
   @Produces("application/json")
   public WPSectionOne editWPSumAllocationDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSumAllocationDetails(seasonProgramId);
   }

   @POST
   @Path("wp/summer/update/allocation/details")
   @Consumes("application/json")
   @Produces("application/json")
   public WPSectionOne updateWPSumAllocationDetails(WPSectionOne wpSectionOne) {
      return seasonServices.updateWPSumAllocationDetails(wpSectionOne);
   }

   // wp spring full details

   @GET
   @Path("wp/spring/view/details/{seasonProgramId}")
   @Produces("application/json")
   public SeasonWPDetails getWPSpringDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSpringDetails(seasonProgramId);
   }

   @GET
   @Path("wp/spring/edit/details/{seasonProgramId}")
   @Produces("application/json")
   public SeasonWPDetails editWPSpringDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSpringDetails(seasonProgramId);
   }

   @POST
   @Path("wp/spring/update/details")
   @Consumes("application/json")
   @Produces("application/json")
   public SeasonWPDetails updateWPSpringDetails(SeasonWPDetails seasonWPDetails) {
      return seasonServices.updateWPSpringDetails(seasonWPDetails);
   }

   // wp spring basic details

   @GET
   @Path("wp/spring/view/base/details/{seasonProgramId}")
   @Produces("application/json")
   public WPBasicDetail getWPSpringBaseDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSpringBaseDetails(seasonProgramId);
   }

   @GET
   @Path("wp/spring/edit/base/details/{seasonProgramId}")
   @Produces("application/json")
   public WPBasicDetail editWPSpringBaseDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSpringBaseDetails(seasonProgramId);
   }

   @POST
   @Path("wp/spring/update/base/details")
   @Consumes("application/json")
   @Produces("application/json")
   public WPBasicDetail updateWPSpringBaseDetails(WPBasicDetail wpBasicDetail) {
      return seasonServices.updateWPSpringBaseDetails(wpBasicDetail);
   }

   // wp spring section 1 details

   @GET
   @Path("wp/spring/view/section/one/details/{seasonProgramId}")
   @Produces("application/json")
   public WPSectionOne getWPSpringSectionOneDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSpringSectionOneDetails(seasonProgramId);
   }

   @GET
   @Path("wp/spring/edit/section/one/details/{seasonProgramId}")
   @Produces("application/json")
   public WPSectionOne editWPSpringSectionOneDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSpringSectionOneDetails(seasonProgramId);
   }

   @POST
   @Path("wp/spring/update/section/one/details")
   @Consumes("application/json")
   @Produces("application/json")
   public WPSectionOne updateWPSpringSectionOneDetails(WPSectionOne wpSectionOne) {
      return seasonServices.updateWPSpringSectionOneDetails(wpSectionOne);
   }

   // wp spring allocation details

   @GET
   @Path("wp/spring/view/allocation/details/{seasonProgramId}")
   @Produces("application/json")
   public WPSectionOne getWPSpringAllocationDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSpringAllocationDetails(seasonProgramId);
   }

   @GET
   @Path("wp/spring/edit/allocation/details/{seasonProgramId}")
   @Produces("application/json")
   public WPSectionOne editWPSpringAllocationDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPSpringAllocationDetails(seasonProgramId);
   }

   @POST
   @Path("wp/spring/update/allocation/details")
   @Consumes("application/json")
   @Produces("application/json")
   public WPSectionOne updateWPSpringAllocationDetails(WPSectionOne wpSectionOne) {
      return seasonServices.updateWPSpringAllocationDetails(wpSectionOne);
   }

   // wp winter full details

   @GET
   @Path("wp/winter/view/details/{seasonProgramId}")
   @Produces("application/json")
   public SeasonWPDetails getWPWinterDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPWinterDetails(seasonProgramId);
   }

   @GET
   @Path("wp/winter/edit/details/{seasonProgramId}")
   @Produces("application/json")
   public SeasonWPDetails editWPWinterDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.editWPWinterDetails(seasonProgramId);
   }

   @POST
   @Path("wp/winter/update/details")
   @Consumes("application/json")
   @Produces("application/json")
   public SeasonWPDetails updateWPWinterDetails(SeasonWPDetails seasonWPDetails) {
      return seasonServices.updateWPWinterDetails(seasonWPDetails);
   }

   // wp winter basic details

   @GET
   @Path("wp/winter/view/base/details/{seasonProgramId}")
   @Produces("application/json")
   public WPBasicDetail getWPWinterBaseDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPWinterBaseDetails(seasonProgramId);
   }

   @GET
   @Path("wp/winter/edit/base/details/{seasonProgramId}")
   @Produces("application/json")
   public WPBasicDetail editWPWinterBaseDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.editWPWinterBaseDetails(seasonProgramId);
   }

   @POST
   @Path("wp/winter/update/base/details")
   @Consumes("application/json")
   @Produces("application/json")
   public WPBasicDetail updateWPWinterBaseDetails(WPBasicDetail wpBasicDetail) {
      return seasonServices.updateWPWinterBaseDetails(wpBasicDetail);
   }

   // wp winter section 1 details

   @GET
   @Path("wp/winter/view/section/one/details/{seasonProgramId}")
   @Produces("application/json")
   public WPSectionOne getWPWinterSectionOneDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPWinterSectionOneDetails(seasonProgramId);
   }

   @GET
   @Path("wp/winter/edit/section/one/details/{seasonProgramId}")
   @Produces("application/json")
   public WPSectionOne editWPWinterSectionOneDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.editWPWinterSectionOneDetails(seasonProgramId);
   }

   @POST
   @Path("wp/winter/update/section/one/details")
   @Consumes("application/json")
   @Produces("application/json")
   public WPSectionOne updateWPWinterSectionOneDetails(WPSectionOne wpSectionOne) {
      return seasonServices.updateWPWinterSectionOneDetails(wpSectionOne);
   }

   // wp winter allocation details

   @GET
   @Path("wp/winter/view/allocation/details/{seasonProgramId}")
   @Produces("application/json")
   public WPSectionOne getWPWinterAllocationDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.getWPWinterAllocationDetails(seasonProgramId);
   }

   @GET
   @Path("wp/winter/edit/allocation/details/{seasonProgramId}")
   @Produces("application/json")
   public WPSectionOne editWPWinterAllocationDetails(@PathParam("seasonProgramId") String seasonProgramId) {
      return seasonServices.editWPWinterAllocationDetails(seasonProgramId);
   }

   @POST
   @Path("wp/winter/update/allocation/details")
   @Consumes("application/json")
   @Produces("application/json")
   public WPSectionOne updateWPWinterAllocationDetails(WPSectionOne wpSectionOne) {
      return seasonServices.updateWPWinterAllocationDetails(wpSectionOne);
   }

   @POST
   @Path("clone")
   @Consumes("application/json")
   @Produces("application/json")
   public CloneSeason cloneSeason(CloneSeason cloneSeason) {
      return seasonServices.cloneSeason(cloneSeason);
   }
   
   @POST
   @Path("add/department/note")
   @Consumes("application/json")
   @Produces("application/json")
   public SeasonDepartmentNotes addSeasonDepartmentNote(SeasonDepartmentNotes seasonDepartmentNotes){
      return seasonServices.addSeasonDepartmentNote(seasonDepartmentNotes);
   }
   
   @POST
   @Path("add/department/doc")
   @Consumes("application/json")
   @Produces("application/json")
   public SeasonDepartmentDocument addSeasonDepartmentDoc(SeasonDepartmentDocument seasonDepartmentDocument){
      return seasonServices.addSeasonDepartmentDoc(seasonDepartmentDocument);
   }
   
   @POST
   @Path("add/program/note")
   @Consumes("application/json")
   @Produces("application/json")
   public SeasonProgramNote addSeasonProgramNote(SeasonProgramNote seasonProgramNote){
      return seasonServices.addSeasonProgramNote(seasonProgramNote);
   }
   
   @POST
   @Path("add/program/doc")
   @Consumes("application/json")
   @Produces("application/json")
   public SeasonProgramDocument addSeasonProgramDoc(SeasonProgramDocument seasonProgramDocument){
      return seasonServices.addSeasonProgramDoc(seasonProgramDocument);
   }
}
