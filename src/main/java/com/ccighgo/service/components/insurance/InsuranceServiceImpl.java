package com.ccighgo.service.components.insurance;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ccighgo.service.transport.seasons.beans.insurancecountries.InsuranceCountries;
import com.ccighgo.service.transport.seasons.beans.insuranceparticipant.InsuranceParticipant;
import com.ccighgo.service.transport.seasons.beans.insuranceparticipant.Participant;
import com.ccighgo.service.transport.seasons.beans.insuranceparticipantobject.ParticipantData;
import com.ccighgo.service.transport.seasons.beans.insuranceplan.InsurancePlans;
import com.ccighgo.service.transport.seasons.beans.insurancestates.InsuranceStates;
import com.ccighgo.utils.ExceptionUtil;
import com.ccighgo.utils.InsuranceServiceUtil;
import com.google.gson.Gson;

@Component
public class InsuranceServiceImpl implements InsuranceServiceInterface {
   public static Gson gson = new Gson();
   private static final Logger LOGGER = Logger.getLogger(InsuranceServiceImpl.class);

   @Override
   public InsurancePlans findPlan(String planId) {
      try {
         String jsonPlan = InsuranceServiceUtil.callInsuranceService(InsuranceServiceUtil.URL_FIND_PLAN + planId);
         return gson.fromJson(jsonPlan, InsurancePlans.class);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Override
   public InsurancePlans findAllPlans() {
      try {
         String jsonPlan = InsuranceServiceUtil.callInsuranceService(InsuranceServiceUtil.URL_FIND_ALL_PLANS);
         return gson.fromJson(jsonPlan, InsurancePlans.class);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Override
   public InsuranceParticipant findPlanParticipant(String planId) {
      try {
         String jsonPlan = InsuranceServiceUtil.callInsuranceService(InsuranceServiceUtil.URL_FIND_PLAN_PARTICIPANT + planId);
         return gson.fromJson(jsonPlan, InsuranceParticipant.class);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Override
   public ParticipantData findParticipant(String participantId) {
      try {
         String jsonPlan = InsuranceServiceUtil.callInsuranceService(InsuranceServiceUtil.URL_FIND_PARTICIPANT + participantId);
         return gson.fromJson(jsonPlan, ParticipantData.class);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Override
   public InsuranceResponse addParticipant(Participant participant) {
      try {
         String result = InsuranceServiceUtil.callInsuranceService(InsuranceServiceUtil.URL_ADD_PARTICIPANT, participant);
         return gson.fromJson(result, InsuranceResponse.class);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Override
   public InsuranceResponse emailPDFParticipant(String particiantHCCID) {
      try {
         String result = InsuranceServiceUtil.callInsuranceService(InsuranceServiceUtil.URL_EMAIL_PDF_PARTICIPANT + particiantHCCID);
         return gson.fromJson(result, InsuranceResponse.class);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Override
   public InsuranceResponse emailVisaPDFParticipant(String particiantHCCID) {
      try {
         String result = InsuranceServiceUtil.callInsuranceService(InsuranceServiceUtil.URL_EMAIL_VISA_PDF_PARTICIPANT + particiantHCCID);
         return gson.fromJson(result, InsuranceResponse.class);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Override
   public InsuranceResponse cancelParticipant(String particiantHCCID) {
      try {
         String result = InsuranceServiceUtil.callInsuranceService(InsuranceServiceUtil.URL_CANCEL_PARTICIPANT + particiantHCCID);
         return gson.fromJson(result, InsuranceResponse.class);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Override
   public InsuranceStates findAllStates() {
      try {
         String jsonPlan = InsuranceServiceUtil.callInsuranceService(InsuranceServiceUtil.URL_FIND_ALL_STATES);
         return gson.fromJson(jsonPlan, InsuranceStates.class);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Override
   public InsuranceCountries findAllCountries() {
      try {
         String jsonPlan = InsuranceServiceUtil.callInsuranceService(InsuranceServiceUtil.URL_FIND_ALL_COUNTRIES);
         return gson.fromJson(jsonPlan, InsuranceCountries.class);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
         return null;
      }
   }

   @Override
   public void downloadPDFParticipant(String particiantHCCID) {
      try {
         InsuranceServiceUtil.downloadService(InsuranceServiceUtil.URL_DOWNLOAD_PDF_PARTICIPANT + particiantHCCID);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
   }

   @Override
   public void downloadVisaPDFParticipant(String particiantHCCID) {
      try {
         InsuranceServiceUtil.downloadService(InsuranceServiceUtil.URL_DOWNLOAD_VISA_PDF_PARTICIPANT + particiantHCCID);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
   }
}
