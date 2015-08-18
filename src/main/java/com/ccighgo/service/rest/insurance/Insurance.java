package com.ccighgo.service.rest.insurance;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.insurance.InsuranceResponse;
import com.ccighgo.service.components.insurance.InsuranceServiceInterface;
import com.ccighgo.service.transport.seasons.beans.insurancecountries.InsuranceCountries;
import com.ccighgo.service.transport.seasons.beans.insuranceparticipant.InsuranceParticipant;
import com.ccighgo.service.transport.seasons.beans.insuranceparticipant.Participant;
import com.ccighgo.service.transport.seasons.beans.insuranceparticipantobject.ParticipantData;
import com.ccighgo.service.transport.seasons.beans.insuranceplan.InsurancePlans;
import com.ccighgo.service.transport.seasons.beans.insurancestates.InsuranceStates;

@Path("/insurance/")
@Produces("application/json")
@Consumes("application/json")
public class Insurance {

   private static final Logger LOGGER = Logger.getLogger(Insurance.class);

   @Autowired
   InsuranceServiceInterface insuranceServiceInterface;

   @GET
   @Path("ping/{input}")
   @Produces("text/plain")
   public String ping(@PathParam("input") String input) {
      LOGGER.debug("Pinging !! ( value : " + input + " )");
      return input;
   }

   @GET
   @Path("find/plan/{planId}")
   @Produces("application/json")
   public InsurancePlans findPlan(@PathParam("planId") String planId) {
      LOGGER.debug("Calling  fun:findPlan");
      return insuranceServiceInterface.findPlan(planId);
   }

   @GET
   @Path("find/plan/all")
   @Produces("application/json")
   public InsurancePlans findAllPlans() {
      LOGGER.debug("Calling fun:findAllPlans");
      return insuranceServiceInterface.findAllPlans();
   }

   @GET
   @Path("find/planParticipant/{planId}")
   @Produces("application/json")
   public InsuranceParticipant findPlanParticipant(@PathParam("planId") String planId) {
      LOGGER.debug("Calling fun:findPlanParticipant");
      return insuranceServiceInterface.findPlanParticipant(planId);
   }

   @GET
   @Path("find/participant/{hccId}")
   @Produces("application/json")
   public ParticipantData findParticipant(@PathParam("hccId") String participantId) {
      LOGGER.debug("Calling fun:findParticipant");
      return insuranceServiceInterface.findParticipant(participantId);
   }

   @POST
   @Path("add/Participant/")
   @Produces("application/json")
   public InsuranceResponse addParticipant(Participant participant) {
      LOGGER.debug("Calling fun:addParticipant");
      return insuranceServiceInterface.addParticipant(participant);
   }

   @GET
   @Path("email/pdf/Participant/{hccid}")
   @Produces("application/json")
   public InsuranceResponse emailPDFParticipant(@PathParam("hccid") String particiantHCCID) {
      LOGGER.debug("Calling fun:emailPDFParticipant");
      return insuranceServiceInterface.emailPDFParticipant(particiantHCCID);
   }

   @GET
   @Path("email/visapdf/Participant/{hccid}")
   @Produces("application/json")
   public InsuranceResponse emailVisaPDFParticipant(@PathParam("hccid") String particiantHCCID) {
      LOGGER.debug("Calling fun:emailVisaPDFParticipant");
      return insuranceServiceInterface.emailVisaPDFParticipant(particiantHCCID);
   }

   @GET
   @Path("download/pdf/Participant/{hccid}")
   @Produces("application/json")
   public void downloadPDFParticipant(@PathParam("hccid") String particiantHCCID) {
      LOGGER.debug("Download PDF participant");
      insuranceServiceInterface.downloadPDFParticipant(particiantHCCID);
   }

   @GET
   @Path("download/visapdf/Participant/{hccid}")
   @Produces("application/json")
   public void downloadVisaPDFParticipant(@PathParam("hccid") String particiantHCCID) {
      LOGGER.debug("Download Visa PDF participant");
      insuranceServiceInterface.downloadVisaPDFParticipant(particiantHCCID);
   }

   @GET
   @Path("cancel/Participant/{hccid}")
   @Produces("application/json")
   public InsuranceResponse cancelParticipant(@PathParam("hccid") String particiantHCCID) {
      LOGGER.debug("Calling fun:cancelParticipant");
      return insuranceServiceInterface.cancelParticipant(particiantHCCID);
   }

   @GET
   @Path("find/state/all")
   @Produces("application/json")
   public InsuranceStates findAllStates() {
      LOGGER.debug("Calling fun:findAllStates");
      return insuranceServiceInterface.findAllStates();
   }

   @GET
   @Path("find/country/all")
   @Produces("application/json")
   public InsuranceCountries findAllCountries() {
      LOGGER.debug("Calling fun:findAllCountries");
      return insuranceServiceInterface.findAllCountries();
   }
}