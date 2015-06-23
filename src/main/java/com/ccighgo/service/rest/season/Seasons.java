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
   
 //HSP J1HS services
   
   @GET
   @Path("j1hs/details/{seasonId}")
   @Produces("application/json")
   public SeasonHspJ1HSDetails getJ1Details(@PathParam("seasonId") String seasonId){
      return seasonServices.getHSPJ1HSSeasonDetails(seasonId);
   }
   
   @GET
   @Path("j1hs/details/{seasonId}")
   @Produces("application/json")
   public J1HSBasicDetail getJ1NameAndStatus(@PathParam("seasonId") String seasonId){
      return seasonServices.getHSPJ1HSSeasonNameAndStatus(seasonId);
   }
   
   
   @GET
   @Path("j1hs/details/{seasonId}")
   @Produces("application/json")
   public J1HSJanStart getJ1JanStartDetails(@PathParam("seasonId") String seasonId){
      return seasonServices.getHSPJ1HSSeasonJanStartDetails(seasonId);
   }
   
   @GET
   @Path("j1hs/details/{seasonId}")
   @Produces("application/json")
   public J1HSAugStart getJ1AugStartDetails(@PathParam("seasonId") String seasonId){
      return seasonServices.getHSPJ1HSSeasonAugStartDetails(seasonId);
   }
   
   @GET
   @Path("j1hs/details/{seasonId}")
   @Produces("application/json")
   public J1HSFieldSettings getJ1FieldSettings(@PathParam("seasonId") String seasonId){
      return seasonServices.getHSPJ1HSSeasonFieldSettings(seasonId);
   }
   
   @GET
   @Path("j1hs/details/{seasonId}")
   @Produces("application/json")
   public J1HSProgramAllocations getJ1ProgramAllocation(@PathParam("seasonId") String seasonId){
      return seasonServices.getHSPJ1HSSeasonProgramAllocation(seasonId);
   }
   
	@GET
	@Path("clone/{id}/{newSeasonName}/")
	@Produces("application/json")
	public String cloneSeason(@PathParam("id") String id,@PathParam("newSeasonName") String newSeasonName) {
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
}
