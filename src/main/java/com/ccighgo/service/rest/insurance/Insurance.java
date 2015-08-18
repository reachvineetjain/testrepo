package com.ccighgo.service.rest.insurance;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccighgo.service.components.insurance.InsuranceServiceInterface;
import com.ccighgo.service.transport.seasons.beans.insurancecountries.InsuranceCountries;
import com.ccighgo.service.transport.seasons.beans.insuranceparticipant.InsuranceParticipant;
import com.ccighgo.service.transport.seasons.beans.insuranceplan.InsurancePlan;
import com.ccighgo.service.transport.seasons.beans.insurancestates.InsuranceStates;

@Path("/insurance/")
@Produces("application/json")
@Consumes("application/json")
public class Insurance {

   private static final Logger LOGGER = Logger.getLogger(Insurance.class);

   @Autowired
   InsuranceServiceInterface insuranceServiceInterface;

   @POST
   @Path("find/plan/{planId}")
   @Produces("application/json")
   public InsurancePlan findPlan(@PathParam("planId") String planId) {
      LOGGER.debug("Calling  fun:findPlan");
      return insuranceServiceInterface.findPlan(planId);
   }

   @POST
   @Path("find/plan/all")
   @Produces("application/json")
   public List<InsurancePlan> findAllPlans() {
      LOGGER.debug("Calling fun:findAllPlans");
      return insuranceServiceInterface.findAllPlans();
   }

   @POST
   @Path("find/participant/{hccid}")
   @Produces("application/json")
   public InsuranceParticipant findParticipant(@PathParam("hccid") String particiantHCCID) {
      LOGGER.debug("Calling fun:findParticipant");
      return insuranceServiceInterface.findParticipant(particiantHCCID);
   }

   @POST
   @Path("add/Participant/")
   @Produces("application/json")
   public String addParticipant(InsuranceParticipant participant) {
      LOGGER.debug("Calling fun:addParticipant");
      return insuranceServiceInterface.addParticipant(participant);
   }

   @POST
   @Path("email/pdf/Participant/{hccid}")
   @Produces("application/json")
   public String emailPDFParticipant(@PathParam("hccid") String particiantHCCID) {
      LOGGER.debug("Calling fun:emailPDFParticipant");
      return insuranceServiceInterface.emailPDFParticipant(particiantHCCID);
   }

   @POST
   @Path("email/visapdf/Participant/{hccid}")
   @Produces("application/json")
   public String emailVisaPDFParticipant(@PathParam("hccid") String particiantHCCID) {
      LOGGER.debug("Calling fun:emailVisaPDFParticipant");
      return insuranceServiceInterface.emailVisaPDFParticipant(particiantHCCID);
   }

   @POST
   @Path("download/pdf/Participant/{hccid}")
   @Produces("application/json")
   public void downloadPDFParticipant(@PathParam("hccid") String particiantHCCID) {
      LOGGER.debug("");
      insuranceServiceInterface.downloadPDFParticipant(particiantHCCID);
   }

   @POST
   @Path("download/visapdf/Participant/{hccid}")
   @Produces("application/json")
   public void downloadVisaPDFParticipant(@PathParam("hccid") String particiantHCCID) {
      LOGGER.debug("");
      insuranceServiceInterface.downloadVisaPDFParticipant(particiantHCCID);
   }

   @POST
   @Path("cancel/Participant/{hccid}")
   @Produces("application/json")
   public String cancelParticipant(@PathParam("hccid") String particiantHCCID) {
      LOGGER.debug("Calling fun:cancelParticipant");
      return insuranceServiceInterface.cancelParticipant(particiantHCCID);
   }

   @POST
   @Path("find/state/all")
   @Produces("application/json")
   public List<InsuranceStates> findAllStates() {
      LOGGER.debug("Calling fun:findAllStates");
      return insuranceServiceInterface.findAllStates();
   }

   @POST
   @Path("find/country/all")
   @Produces("application/json")
   public List<InsuranceCountries> findAllCountries() {
      LOGGER.debug("Calling fun:findAllCountries");
      return insuranceServiceInterface.findAllCountries();
   }
}