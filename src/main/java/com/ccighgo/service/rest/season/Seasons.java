package com.ccighgo.service.rest.season;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.db.entities.SeasonHSADetail;
import com.ccighgo.service.components.season.SeasonServiceInterfaceImpl;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.GHTSection1Base;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.GHTSection2Dates;
import com.ccighgo.service.transport.season.beans.seasonghtdetails.SeasonGHTDetails;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSAugStart;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSBasicDetail;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSFieldSettings;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSJanStart;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.J1HSProgramAllocations;
import com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails.SeasonHspJ1HSDetails;
import com.ccighgo.service.transport.season.beans.seasonprogram.SeasonPrograms;
import com.ccighgo.service.transport.season.beans.seasonstatus.SeasonStatuses;
import com.ccighgo.service.transport.seasons.beans.season.SeasonBean;
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
   public SeasonPrograms getSeasonProgram(@PathParam("seasonId") String seasonId) {
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
   
   @POST
   @Path("j1hs/details/update")
   @Consumes("application/json")
   public SeasonHspJ1HSDetails updateJ1Details(SeasonHspJ1HSDetails seasonHspJ1HSDetails) {
      return seasonServices.updateHSPJ1HSSeasonDetails(seasonHspJ1HSDetails);
   }

   @POST
   @Path("j1hs/base/update")
   @Consumes("application/json")
   public J1HSBasicDetail updateJ1NameAndStatus(J1HSBasicDetail j1hsBasicDetail) {
      return seasonServices.updateHSPJ1HSSeasonNameAndStatus(j1hsBasicDetail);
   }

   @POST
   @Path("j1hs/jan/update")
   @Consumes("application/json")
   public J1HSJanStart updateJ1JanStartDetails(J1HSJanStart j1hsJanStart) {
      return seasonServices.updateHSPJ1HSSeasonJanStartDetails(j1hsJanStart);
   }

   @POST
   @Path("j1hs/aug/update")
   @Consumes("application/json")
   public J1HSAugStart updateJ1AugStartDetails(J1HSAugStart j1hsAugStart) {
      return seasonServices.updateHSPJ1HSSeasonAugStartDetails(j1hsAugStart);
   }

   @POST
   @Path("j1hs/field/settings/update")
   @Consumes("application/json")
   public J1HSFieldSettings updateJ1FieldSettings(J1HSFieldSettings j1hsFieldSettings) {
      return seasonServices.updateHSPJ1HSSeasonFieldSettings(j1hsFieldSettings);
   }

   @POST
   @Path("j1hs/program/update/llocation")
   @Consumes("application/json")
   public J1HSProgramAllocations updateJ1ProgramAllocation(J1HSProgramAllocations j1hsProgramAllocations) {
      return seasonServices.updateHSPJ1HSSeasonProgramAllocation(j1hsProgramAllocations);
   }
// HSP F1 services
   /**
    * view - Edit- update
    */
   
   @GET
   @Path("f1/view/details/{seasonId}")
   @Produces("application/json")
   public SeasonHSPF1Details getF1Details(@PathParam("seasonId") String seasonId){
      return seasonServices.getSeasonHSPF1Details(seasonId);
   }
   
   @GET
   @Path("f1/edit/details/{seasonId}")
   @Produces("application/json")
   public SeasonHSPF1Details editF1Details(@PathParam("seasonId") String seasonId){
      return seasonServices.getSeasonHSPF1Details(seasonId);
   }
   
   @POST
   @Path("f1/update/details")
   @Produces("application/json")
   public SeasonHSPF1Details updateF1Details(SeasonHSPF1Details seasonHSPF1Details){
      return seasonServices.updateF1Details(seasonHSPF1Details);
   }
   
   
   @GET
   @Path("f1/view/basicdetails/{seasonId}")
   @Produces("application/json")
   public HSPF1BasicDetails getF1NameAndStatus(@PathParam("seasonId") String seasonId){
      return seasonServices.getHSPF1NameAndStatus(seasonId);
   }
   @GET
   @Path("f1/edit/basicdetails/{seasonId}")
   @Produces("application/json")
   public HSPF1BasicDetails editF1NameAndStatus(@PathParam("seasonId") String seasonId){
      return seasonServices.getHSPF1NameAndStatus(seasonId);
   }
   
   @POST
   @Path("f1/update/basicdetails")
   @Produces("application/json")
   public HSPF1BasicDetails updateF1NameAndStatus(HSPF1BasicDetails hspf1BasicDetails){
      return seasonServices.updateHSPF1NameAndStatus(hspf1BasicDetails);
   }
   
   
   @GET
   @Path("f1/view/accounting/{seasonId}")
   @Produces("application/json")
   public HSPF1Accounting getF1Accounting(@PathParam("seasonId") String seasonId){
      return seasonServices.getHSPF1Accounting(seasonId);
   }
   
   @GET
   @Path("f1/edit/accounting/{seasonId}")
   @Produces("application/json")
   public HSPF1Accounting editF1Accounting(@PathParam("seasonId") String seasonId){
      return seasonServices.getHSPF1Accounting(seasonId);
   }
   
   @POST
   @Path("f1/update/accounting")
   @Produces("application/json")
   public HSPF1Accounting updateF1Accounting(HSPF1Accounting hspf1Accounting){
      return seasonServices.updateF1Accounting(hspf1Accounting);
   }
   
   
   @GET
   @Path("f1/view/jan/startdetails/{seasonId}")
   @Produces("application/json")
   public HSPF1JanuaryStart2NdSemesterDetails getF1JanStart2NdSemesterDetails(@PathParam("seasonId") String seasonId){
      return seasonServices.getHSPF1JanuaryStart2NdSemesterDetails(seasonId);
   }
   
   @GET
   @Path("f1/edit/jan/startdetails/{seasonId}")
   @Produces("application/json")
   public HSPF1JanuaryStart2NdSemesterDetails editF1JanStart2NdSemesterDetails(@PathParam("seasonId") String seasonId){
      return seasonServices.getHSPF1JanuaryStart2NdSemesterDetails(seasonId);
   }
   
   @POST
   @Path("f1/update/jan/startdetails")
   @Produces("application/json")
   public HSPF1JanuaryStart2NdSemesterDetails updateF1JanStart2NdSemesterDetails(HSPF1JanuaryStart2NdSemesterDetails hspf1JanuaryStart2NdSemesterDetails){
      return seasonServices.updateF1JanStart2NdSemesterDetails(hspf1JanuaryStart2NdSemesterDetails);
   }
   
   @GET
   @Path("f1/view/jan/fulldetails/{seasonId}")
   @Produces("application/json")
   public HSPF1JanuaryStartFullYearDetail getF1JanStartFullYearDetails(@PathParam("seasonId") String seasonId){
      return seasonServices.getHSPF1JanuaryStartFullYearDetails(seasonId);
   }
   
   @GET
   @Path("f1/edit/jan/fulldetails/{seasonId}")
   @Produces("application/json")
   public HSPF1JanuaryStartFullYearDetail editF1JanStartFullYearDetails(@PathParam("seasonId") String seasonId){
      return seasonServices.getHSPF1JanuaryStartFullYearDetails(seasonId);
   }
   
   @POST
   @Path("f1/update/jan/fulldetails")
   @Produces("application/json")
   public HSPF1JanuaryStartFullYearDetail updateF1JanStartFullYearDetails(HSPF1JanuaryStartFullYearDetail hspf1JanuaryStartFullYearDetail){
      return seasonServices.updateF1JanStartFullYearDetails(hspf1JanuaryStartFullYearDetail);
   }
   
   @GET
   @Path("f1/view/august/startdetail/{seasonId}")
   @Produces("application/json")
   public HSPF1AugustStart1StSemesterDetails getF1AugStart1StSemesterDetails(@PathParam("seasonId") String seasonId){
      return seasonServices.getHSPF1AugustStart1StSemesterDetails(seasonId);
   }
   @GET
   @Path("f1/edit/august/startdetail/{seasonId}")
   @Produces("application/json")
   public HSPF1AugustStart1StSemesterDetails editF1AugStart1StSemesterDetails(@PathParam("seasonId") String seasonId){
      return seasonServices.getHSPF1AugustStart1StSemesterDetails(seasonId);
   }
   
   @POST
   @Path("f1/update/august/startdetail")
   @Produces("application/json")
   public HSPF1AugustStart1StSemesterDetails updateF1AugStart1StSemesterDetails(HSPF1AugustStart1StSemesterDetails hspf1AugustStart1StSemesterDetails){
      return seasonServices.updateF1AugStart1StSemesterDetails(hspf1AugustStart1StSemesterDetails);
   }
   
   
   @GET
   @Path("f1/view/august/fulldetail/{seasonId}")
   @Produces("application/json")
   public HSPF1AugustStartFullYearDetails getF1AugStartFullYearDetails(@PathParam("seasonId") String seasonId){
      return seasonServices.getHSPF1AugustStartFullYearDetails(seasonId);
   }
   
   @GET
   @Path("f1/edit/august/fulldetail/{seasonId}")
   @Produces("application/json")
   public HSPF1AugustStartFullYearDetails editF1AugStartFullYearDetails(@PathParam("seasonId") String seasonId){
      return seasonServices.getHSPF1AugustStartFullYearDetails(seasonId);
   }
   
   @POST
   @Path("f1/update/august/fulldetail")
   @Produces("application/json")
   public HSPF1AugustStartFullYearDetails updateF1AugStartFullYearDetails(HSPF1AugustStartFullYearDetails hspf1AugustStartFullYearDetails){
      return seasonServices.updateF1AugStartFullYearDetails(hspf1AugustStartFullYearDetails);
   }
   
   @GET
   @Path("f1/view/fieldsettings/{seasonId}")
   @Produces("application/json")
   public HSPF1FieldSettings getF1FieldSettings(@PathParam("seasonId") String seasonId){
      return seasonServices.getHSPF1FieldSettings(seasonId);
   }
   @GET
   @Path("f1/edit/fieldsettings/{seasonId}")
   @Produces("application/json")
   public HSPF1FieldSettings editF1FieldSettings(@PathParam("seasonId") String seasonId){
      return seasonServices.getHSPF1FieldSettings(seasonId);
   }
   @POST
   @Path("f1/update/fieldsettings")
   @Produces("application/json")
   public HSPF1FieldSettings updateF1FieldSettings(HSPF1FieldSettings hspf1FieldSettings){
      return seasonServices.updateF1FieldSettings(hspf1FieldSettings);
   }
   
   
   
   @GET
   @Path("f1/view/allocation/{seasonId}")
   @Produces("application/json")
   public HSPF1ProgramAllocations getF1ProgramAllocation(@PathParam("seasonId") String seasonId){
      return seasonServices.getHSPF1ProgramAllocations(seasonId);
   }
   
   @GET
   @Path("f1/edit/allocation/{seasonId}")
   @Produces("application/json")
   public HSPF1ProgramAllocations editF1ProgramAllocation(@PathParam("seasonId") String seasonId){
      return seasonServices.getHSPF1ProgramAllocations(seasonId);
   }
   @POST
   @Path("f1/update/allocation")
   @Produces("application/json")
   public HSPF1ProgramAllocations updateF1ProgramAllocation(HSPF1ProgramAllocations hspf1ProgramAllocations){
      return seasonServices.updateF1ProgramAllocation(hspf1ProgramAllocations);
   }
   
   
// End of HSP F1 services
   
   //GHT Volunteer abroad services
   
   @GET
   @Path("ght/va/details/view/{seasonId}")
   @Produces("application/json")
   public SeasonGHTDetails getVADetails(@PathParam("seasonId") String seasonId) {
      return seasonServices.getGHTVASeasonDetails(seasonId);
   }

   @GET
   @Path("ght/va/base/view/{seasonId}")
   @Produces("application/json")
   public GHTSection1Base getVANameAndStatus(@PathParam("seasonId") String seasonId) {
      return seasonServices.getGHTVASeasonNameAndStatus(seasonId);
   }

   @GET
   @Path("ght/va/date/section/view/{seasonId}")
   @Produces("application/json")
   public GHTSection2Dates getVADateDetails(@PathParam("seasonId") String seasonId) {
      return seasonServices.getGHTVASeasonDateDetails(seasonId);
   }

   //edit
   @GET
   @Path("ght/va/details/edit/{seasonId}")
   @Produces("application/json")
   public SeasonGHTDetails editVADetails(@PathParam("seasonId") String seasonId) {
      return seasonServices.getGHTVASeasonDetails(seasonId);
   }

   @GET
   @Path("ght/va/base/edit/{seasonId}")
   @Produces("application/json")
   public GHTSection1Base editVANameAndStatus(@PathParam("seasonId") String seasonId) {
      return seasonServices.getGHTVASeasonNameAndStatus(seasonId);
   }

   @GET
   @Path("ght/va/date/section/edit/{seasonId}")
   @Produces("application/json")
   public GHTSection2Dates editVADateDetails(@PathParam("seasonId") String seasonId) {
      return seasonServices.getGHTVASeasonDateDetails(seasonId);
   }
   
   //update
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
   
   //GHT work abroad services
   
   @GET
   @Path("ght/wa/details/view/{seasonId}")
   @Produces("application/json")
   public SeasonGHTDetails getWADetails(@PathParam("seasonId") String seasonId) {
      return seasonServices.getGHTWASeasonDetails(seasonId);
   }

   @GET
   @Path("ght/wa/base/view/{seasonId}")
   @Produces("application/json")
   public GHTSection1Base getWANameAndStatus(@PathParam("seasonId") String seasonId) {
      return seasonServices.getGHTWASeasonNameAndStatus(seasonId);
   }

   @GET
   @Path("ght/wa/date/section/view/{seasonId}")
   @Produces("application/json")
   public GHTSection2Dates getWADateDetails(@PathParam("seasonId") String seasonId) {
      return seasonServices.getGHTWASeasonDateDetails(seasonId);
   }

   //edit
   @GET
   @Path("ght/wa/details/edit/{seasonId}")
   @Produces("application/json")
   public SeasonGHTDetails editWADetails(@PathParam("seasonId") String seasonId) {
      return seasonServices.getGHTWASeasonDetails(seasonId);
   }

   @GET
   @Path("ght/wa/base/edit/{seasonId}")
   @Produces("application/json")
   public GHTSection1Base editWANameAndStatus(@PathParam("seasonId") String seasonId) {
      return seasonServices.getGHTWASeasonNameAndStatus(seasonId);
   }

   @GET
   @Path("ght/wa/date/section/edit/{seasonId}")
   @Produces("application/json")
   public GHTSection2Dates editWADateDetails(@PathParam("seasonId") String seasonId) {
      return seasonServices.getGHTWASeasonDateDetails(seasonId);
   }
   
   //update
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
   @Path("ght/hsa/view/details/{seasonId}")
   @Produces("application/json")
   public SeasonGHTDetails getGHTHSAbroad(@PathParam("seasonId") String seasonId){
	   return seasonServices.getGHTHSAbroad(seasonId);
   }
   @GET
   @Path("ght/hsa/edit/details/{seasonId}")
   @Produces("application/json")
   public SeasonGHTDetails editGHTHSAbroad(@PathParam("seasonId") String seasonId){
	   return seasonServices.getGHTHSAbroad(seasonId);
   }
   
   @POST
   @Path("ght/hsa/update/details")
   @Produces("application/json")
   public SeasonGHTDetails updateGHTHSAbroad(SeasonGHTDetails seasonGHTDetails){
	   return seasonServices.updateGHTHSAbroad(seasonGHTDetails);
   }
   
// sections
   @GET
   @Path("ght/hsa/view/sec1/{seasonId}")
   @Produces("application/json")
   public GHTSection1Base getGHTHSSection1BaseAbroad(@PathParam("seasonId") String seasonId){
      return seasonServices.getGHTHSSection1BaseAbroad(seasonId);
   }   
   @GET
   @Path("ght/hsa/edit/sec1/{seasonId}")
   @Produces("application/json")
   public GHTSection1Base editGHTHSSection1BaseAbroad(@PathParam("seasonId") String seasonId){
	   return seasonServices.getGHTHSSection1BaseAbroad(seasonId);
   }
   
   @POST
   @Path("ght/hsa/update/sec1")
   @Produces("application/json")
   public GHTSection1Base updateGHTHSSection1BaseAbroad(GHTSection1Base ghtSection1Base){
	   return seasonServices.updateGHTHSSection1BaseAbroad(ghtSection1Base);
   }
   
   @GET
   @Path("ght/hsa/view/datesec/{seasonId}")
   @Produces("application/json")
   public GHTSection2Dates getGHTHSSection2DatesAbroad(@PathParam("seasonId") String seasonId){
      return seasonServices.getGHTHSSection2DatesAbroad(seasonId);
   }
   @GET
   @Path("ght/hsa/edit/datesec/{seasonId}")
   @Produces("application/json")
   public GHTSection2Dates editGHTHSSection2DatesAbroad(@PathParam("seasonId") String seasonId){
	   return seasonServices.getGHTHSSection2DatesAbroad(seasonId);
   }
   
   @POST
   @Path("ght/hsa/update/datesec")
   @Produces("application/json")
   public GHTSection2Dates updateGHTHSSection2DatesAbroad(GHTSection2Dates ghtSection2Dates){
	   return seasonServices.updateGHTHSSection2DatesAbroad(ghtSection2Dates);
   }
   
   // LS 
   
   @GET
   @Path("ght/ls/view/{seasonId}")
   @Produces("application/json")
   public SeasonGHTDetails getGHTLanguageSchool(@PathParam("seasonId") String seasonId){
      return seasonServices.getGHTLanguageSchool(seasonId);
   }
   
   @GET
   @Path("ght/ls/edit/{seasonId}")
   @Produces("application/json")
   public SeasonGHTDetails editGHTLanguageSchool(@PathParam("seasonId") String seasonId){
      return seasonServices.getGHTLanguageSchool(seasonId);
   }
   
   @POST
   @Path("ght/ls/update")
   @Produces("application/json")
   public SeasonGHTDetails updateGHTLanguageSchool(SeasonGHTDetails seasonGHTDetails){
      return seasonServices.updateGHTLanguageSchool(seasonGHTDetails);
   }
   
// sections
   @GET
   @Path("ght/ls/view/sec1/{seasonId}")
   @Produces("application/json")
   public GHTSection1Base getGHTLanguageSchoolSection1(@PathParam("seasonId") String seasonId){
      return seasonServices.getGHTLanguageSchoolSection1(seasonId);
   }   
   @GET
   @Path("ght/ls/edit/sec1/{seasonId}")
   @Produces("application/json")
   public GHTSection1Base editGHTLanguageSchoolSection1(@PathParam("seasonId") String seasonId){
	   return seasonServices.getGHTLanguageSchoolSection1(seasonId);
   }
   
   @POST
   @Path("ght/ls/update/sec1")
   @Produces("application/json")
   public GHTSection1Base updateGHTLanguageSchoolSection1(GHTSection1Base seasonGHTDetails){
	   return seasonServices.updateGHTLanguageSchoolSection1(seasonGHTDetails);
   }
   
   @GET
   @Path("ght/ls/view/datesec/{seasonId}")
   @Produces("application/json")
   public GHTSection2Dates getGHTLanguageSchoolSection2Dates(@PathParam("seasonId") String seasonId){
      return seasonServices.getGHTLanguageSchoolSection2Dates(seasonId);
   }
   @GET
   @Path("ght/ls/edit/datesec/{seasonId}")
   @Produces("application/json")
   public GHTSection2Dates editGHTLanguageSchoolSection2Dates(@PathParam("seasonId") String seasonId){
	   return seasonServices.getGHTLanguageSchoolSection2Dates(seasonId);
   }
   
   @POST
   @Path("ght/ls/update/datesec")
   @Produces("application/json")
   public GHTSection2Dates updateGHTLanguageSchoolSection2Dates(GHTSection2Dates ghtSection2Dates){
	   return seasonServices.updateGHTLanguageSchoolSection2Dates(ghtSection2Dates);
   }
   
   
   //TA
   
   @GET
   @Path("ght/ta/view/{seasonId}")
   @Produces("application/json")
   public SeasonGHTDetails getGHTTeachAbroad(@PathParam("seasonId") String seasonId){
      return seasonServices.getGHTTeachAbroad(seasonId);
   }
   
   @GET
   @Path("ght/ta/edit/{seasonId}")
   @Produces("application/json")
   public SeasonGHTDetails editGHTTeachAbroad(@PathParam("seasonId") String seasonId){
      return seasonServices.getGHTTeachAbroad(seasonId);
   }
   
   @POST
   @Path("ght/ta/update")
   @Produces("application/json")
   public SeasonGHTDetails updateGHTTeachAbroad(SeasonGHTDetails seasonGHTDetails){
      return seasonServices.updateGHTTeachAbroad(seasonGHTDetails);
   }
// sections
   @GET
   @Path("ght/ta/view/sec1/{seasonId}")
   @Produces("application/json")
   public GHTSection1Base getGHTTeachAbroadSection1(@PathParam("seasonId") String seasonId){
      return seasonServices.getGHTTeachAbroadSection1(seasonId);
   }   
   @GET
   @Path("ght/ta/edit/sec1/{seasonId}")
   @Produces("application/json")
   public GHTSection1Base editGHTTeachAbroadSection1(@PathParam("seasonId") String seasonId){
	   return seasonServices.getGHTTeachAbroadSection1(seasonId);
   }
   
   @POST
   @Path("ght/ta/update/sec1")
   @Produces("application/json")
   public GHTSection1Base updateGHTTeachAbroadSection1(GHTSection1Base ghtSection1Base){
	   return seasonServices.updateGHTTeachAbroadSection1(ghtSection1Base);
   }
   
   @GET
   @Path("ght/ta/view/datesec/{seasonId}")
   @Produces("application/json")
   public GHTSection2Dates getGHTTeachAbroadSection2Dates(@PathParam("seasonId") String seasonId){
      return seasonServices.getGHTTeachAbroadSection2Dates(seasonId);
   }
   @GET
   @Path("ght/ta/edit/datesec/{seasonId}")
   @Produces("application/json")
   public GHTSection2Dates editGHTTeachAbroadSection2Dates(@PathParam("seasonId") String seasonId){
	   return seasonServices.getGHTTeachAbroadSection2Dates(seasonId);
   }
   
   @POST
   @Path("ght/ta/update/datesec")
   @Produces("application/json")
   public GHTSection2Dates updateGHTTeachAbroadSection2Dates(GHTSection2Dates ghtSection2Dates){
	   return seasonServices.updateGHTTeachAbroadSection2Dates(ghtSection2Dates);
   }
   
   // work program --> Cap
   @GET
   @Path("wp/cap/view/details/{seasonId}")
   @Produces("application/json")
   public SeasonWPCAPDetails getWPCAPDetails(@PathParam("seasonId") String seasonId){
      return seasonServices.getWPCAPDetails(seasonId);
   }
   @GET
   @Path("wp/cap/edit/details/{seasonId}")
   @Produces("application/json")
   public SeasonWPCAPDetails editWPCAPDetails(@PathParam("seasonId") String seasonId){
	   return seasonServices.getWPCAPDetails(seasonId);
   }
   
   @POST
   @Path("wp/cap/update/details")
   @Produces("application/json")
   public SeasonWPCAPDetails updateWPCAPDetails(SeasonWPCAPDetails seasonWPCAPDetails){
	   return seasonServices.updateWPCAPDetails(seasonWPCAPDetails);
   }
   //basic
   @GET
   @Path("wp/cap/view/basic/details/{seasonId}")
   @Produces("application/json")
   public WPCAPBasicDetails getWPCAPBasicDetails(@PathParam("seasonId") String seasonId){
      return seasonServices.getWPCAPBasicDetails(seasonId);
   }
   @GET
   @Path("wp/cap/edit/basic/details/{seasonId}")
   @Produces("application/json")
   public WPCAPBasicDetails editWPCAPBasicDetails(@PathParam("seasonId") String seasonId){
	   return seasonServices.getWPCAPBasicDetails(seasonId);
   }
   
   @POST
   @Path("wp/cap/update/basic/details")
   @Produces("application/json")
   public WPCAPBasicDetails updateWPCAPBasicDetails(WPCAPBasicDetails seasonWpcapBasicDetails){
	   return seasonServices.updateWPCAPBasicDetails(seasonWpcapBasicDetails);
   }
   
   
   // internship
   @GET
   @Path("wp/cap/view/internship/details/{seasonId}")
   @Produces("application/json")
   public WPCAPInternshipDetails getWPCAPInternshipDetails(@PathParam("seasonId") String seasonId){
      return seasonServices.getWPCAPInternshipDetails(seasonId);
   }
   @GET
   @Path("wp/cap/edit/internship/details/{seasonId}")
   @Produces("application/json")
   public WPCAPInternshipDetails editWPCAPInternshipDetails(@PathParam("seasonId") String seasonId){
	   return seasonServices.getWPCAPInternshipDetails(seasonId);
   }
   
   @POST
   @Path("wp/cap/update/internship/details")
   @Produces("application/json")
   public WPCAPInternshipDetails updateWPCAPInternshipDetails(WPCAPInternshipDetails wpcapInternshipDetails){
	   return seasonServices.updateWPCAPInternshipDetails(wpcapInternshipDetails);
   }
   
   // trainee details
   @GET
   @Path("wp/cap/view/trainee/details/{seasonId}")
   @Produces("application/json")
   public WPCAPTraineeDetails getWPCAPTraineeDetails(@PathParam("seasonId") String seasonId){
      return seasonServices.getWPCAPTraineeDetails(seasonId);
   }
   @GET
   @Path("wp/cap/edit/trainee/details/{seasonId}")
   @Produces("application/json")
   public WPCAPTraineeDetails editWPCAPTraineeDetails(@PathParam("seasonId") String seasonId){
	   return seasonServices.getWPCAPTraineeDetails(seasonId);
   }
   
   @POST
   @Path("wp/cap/update/trainee/details")
   @Produces("application/json")
   public WPCAPTraineeDetails updateWPCAPTraineeDetails(WPCAPTraineeDetails wpcapTraineeDetails){
	   return seasonServices.updateWPCAPTraineeDetails(wpcapTraineeDetails);
   }
   
   
}
