package com.ccighgo.service.transport.partner.beans.partnerseason;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.ccighgo.service.transport.partner.beans.partnerseasondetail.PartnerSeasonStatus;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerAgentDetails", propOrder = {
    "partnerId",
    "partnerSeasonStatus",
    "canHaveSubPartner",
    "insuranceCarrierName",
    "insurancePhoneNumber",
    "insurancePolicyNumber",
    "questionaireRequired",
    "disableAddParticipant",
    "programAllocations",
    "partnerSeasonAgentDates",
    "PartnerSeasonDocuments",
    "PartnerSeasonNotes"
})
public class PartnerAgentSeasonDetails {
   
   protected String partnerId;
   protected PartnerSeasonStatus partnerSeasonStatus;
   protected Byte canHaveSubPartner;
   protected String insuranceCarrierName;
   protected String insurancePhoneNumber;
   protected String insurancePolicyNumber;
   protected Byte questionaireRequired;
   protected Byte disableAddParticipant;
   protected ProgramAllocations programAllocations;
   protected PartnerSeasonAgentDates partnerSeasonAgentDates;
   protected PartnerSeasonDocuments PartnerSeasonDocuments;
   
   public String getPartnerId() {
      return partnerId;
   }
   public void setPartnerId(String partnerId) {
      this.partnerId = partnerId;
   }
   public PartnerSeasonStatus getPartnerSeasonStatus() {
      return partnerSeasonStatus;
   }
   public void setPartnerSeasonStatus(PartnerSeasonStatus partnerSeasonStatus) {
      this.partnerSeasonStatus = partnerSeasonStatus;
   }
   public Byte getCanHaveSubPartner() {
      return canHaveSubPartner;
   }
   public void setCanHaveSubPartner(Byte canHaveSubPartner) {
      this.canHaveSubPartner = canHaveSubPartner;
   }
   public String getInsuranceCarrierName() {
      return insuranceCarrierName;
   }
   public void setInsuranceCarrierName(String insuranceCarrierName) {
      this.insuranceCarrierName = insuranceCarrierName;
   }
   public String getInsurancePhoneNumber() {
      return insurancePhoneNumber;
   }
   public void setInsurancePhoneNumber(String insurancePhoneNumber) {
      this.insurancePhoneNumber = insurancePhoneNumber;
   }
   public String getInsurancePolicyNumber() {
      return insurancePolicyNumber;
   }
   public void setInsurancePolicyNumber(String insurancePolicyNumber) {
      this.insurancePolicyNumber = insurancePolicyNumber;
   }
   public Byte getQuestionaireRequired() {
      return questionaireRequired;
   }
   public void setQuestionaireRequired(Byte questionaireRequired) {
      this.questionaireRequired = questionaireRequired;
   }
   public Byte getDisableAddParticipant() {
      return disableAddParticipant;
   }
   public void setDisableAddParticipant(Byte disableAddParticipant) {
      this.disableAddParticipant = disableAddParticipant;
   }
   public PartnerSeasonAgentDates getPartnerSeasonAgentDates() {
      return partnerSeasonAgentDates;
   }
   public void setPartnerSeasonAgentDates(PartnerSeasonAgentDates partnerSeasonAgentDates) {
      this.partnerSeasonAgentDates = partnerSeasonAgentDates;
   }
   public ProgramAllocations getProgramAllocations() {
      return programAllocations;
   }
   public void setProgramAllocations(ProgramAllocations programAllocations) {
      this.programAllocations = programAllocations;
   }
    
}
