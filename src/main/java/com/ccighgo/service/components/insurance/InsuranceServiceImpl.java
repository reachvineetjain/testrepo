package com.ccighgo.service.components.insurance;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ccighgo.service.transport.seasons.beans.insurancecountries.InsuranceCountries;
import com.ccighgo.service.transport.seasons.beans.insuranceparticipant.InsuranceParticipant;
import com.ccighgo.service.transport.seasons.beans.insuranceplan.InsurancePlan;
import com.ccighgo.service.transport.seasons.beans.insurancestates.InsuranceStates;
import com.ccighgo.utils.ExceptionUtil;
import com.ccighgo.utils.InsuranceServiceUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Component
public class InsuranceServiceImpl implements InsuranceServiceInterface {
   public static Gson gson = new Gson();
   private static final Logger LOGGER = Logger.getLogger(InsuranceServiceImpl.class);

   @Override
   public InsurancePlan findPlan(String planId) {
      try {
         String jsonPlan = InsuranceServiceUtil.callInsuranceService(InsuranceServiceUtil.URL_FIND_PLAN + planId);
         return gson.fromJson(jsonPlan, InsurancePlan.class);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Override
   public List<InsurancePlan> findAllPlans() {
      try {
         Type listType = new TypeToken<ArrayList<InsurancePlan>>() {
         }.getType();
         String jsonPlan = InsuranceServiceUtil.callInsuranceService(InsuranceServiceUtil.URL_FIND_ALL_PLANS);
         return gson.fromJson(jsonPlan, listType);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Override
   public InsuranceParticipant findParticipant(String particiantHCCID) {
      try {
         String jsonPlan = InsuranceServiceUtil.callInsuranceService(InsuranceServiceUtil.URL_FIND_PARTICIPANT + particiantHCCID);
         return gson.fromJson(jsonPlan, InsuranceParticipant.class);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Override
   public String addParticipant(InsuranceParticipant participant) {
      try {
         return InsuranceServiceUtil.callService(InsuranceServiceUtil.URL_ADD_PARTICIPANT, participant);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Override
   public String emailPDFParticipant(String particiantHCCID) {
      try {
         return InsuranceServiceUtil.callInsuranceService(InsuranceServiceUtil.URL_EMAIL_PDF_PARTICIPANT + particiantHCCID);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Override
   public String emailVisaPDFParticipant(String particiantHCCID) {
      try {
         return InsuranceServiceUtil.callInsuranceService(InsuranceServiceUtil.URL_EMAIL_VISA_PDF_PARTICIPANT + particiantHCCID);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Override
   public String cancelParticipant(String particiantHCCID) {
      try {
         return InsuranceServiceUtil.callInsuranceService(InsuranceServiceUtil.URL_CANCEL_PARTICIPANT + particiantHCCID);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Override
   public List<InsuranceStates> findAllStates() {
      try {
         Type listType = new TypeToken<ArrayList<InsuranceStates>>() {
         }.getType();
         String jsonPlan = InsuranceServiceUtil.callInsuranceService(InsuranceServiceUtil.URL_FIND_ALL_STATES);
         return gson.fromJson(jsonPlan, listType);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
   }

   @Override
   public List<InsuranceCountries> findAllCountries() {
      try {
         Type listType = new TypeToken<ArrayList<InsuranceCountries>>() {
         }.getType();
         String jsonPlan = InsuranceServiceUtil.callInsuranceService(InsuranceServiceUtil.URL_FIND_ALL_COUNTRIES);
         return gson.fromJson(jsonPlan, listType);
      } catch (Exception e) {
         ExceptionUtil.logException(e, LOGGER);
      }
      return null;
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
