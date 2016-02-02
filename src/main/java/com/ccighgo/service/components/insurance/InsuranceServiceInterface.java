package com.ccighgo.service.components.insurance;

import org.springframework.stereotype.Service;

import com.ccighgo.service.transport.seasons.beans.insurancecountries.InsuranceCountries;
import com.ccighgo.service.transport.seasons.beans.insuranceparticipant.InsuranceParticipant;
import com.ccighgo.service.transport.seasons.beans.insuranceparticipant.Participant;
import com.ccighgo.service.transport.seasons.beans.insuranceparticipantobject.ParticipantData;
import com.ccighgo.service.transport.seasons.beans.insuranceplan.InsurancePlans;
import com.ccighgo.service.transport.seasons.beans.insurancestates.InsuranceStates;

@Service
public interface InsuranceServiceInterface {

   public InsurancePlans findPlan(String planId);

   public InsurancePlans findAllPlans();

   public InsuranceParticipant findPlanParticipant(String particiantHCCID);

   public InsuranceResponse addParticipant(Participant participant);

   public InsuranceResponse emailPDFParticipant(String particiantHCCID);

   public InsuranceResponse emailVisaPDFParticipant(String particiantHCCID);

   public InsuranceResponse cancelParticipant(String particiantHCCID);

   public InsuranceStates findAllStates();

   public InsuranceCountries findAllCountries();

   public void downloadPDFParticipant(String particiantHCCID);

   public void downloadVisaPDFParticipant(String particiantHCCID);

   public ParticipantData findParticipant(String participantId);

}
