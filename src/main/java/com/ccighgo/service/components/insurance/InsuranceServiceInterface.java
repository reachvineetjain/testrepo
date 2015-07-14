package com.ccighgo.service.components.insurance;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.seasons.beans.insurancecountries.InsuranceCountries;
import com.ccighgo.service.transport.seasons.beans.insuranceparticipant.InsuranceParticipant;
import com.ccighgo.service.transport.seasons.beans.insuranceplan.InsurancePlan;
import com.ccighgo.service.transport.seasons.beans.insurancestates.InsuranceStates;

@Service
public interface InsuranceServiceInterface {

   public InsurancePlan findPlan(String planId);

   public List<InsurancePlan> findAllPlans();

   public InsuranceParticipant findParticipant(String particiantHCCID);

   public String addParticipant(InsuranceParticipant participant);

   public String emailPDFParticipant(String particiantHCCID);

   public String emailVisaPDFParticipant(String particiantHCCID);

   public String cancelParticipant(String particiantHCCID);

   public List<InsuranceStates> findAllStates();

   public List<InsuranceCountries> findAllCountries();

}
