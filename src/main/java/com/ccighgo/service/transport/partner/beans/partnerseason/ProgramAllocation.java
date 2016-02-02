package com.ccighgo.service.transport.partner.beans.partnerseason;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProgramAllocation", propOrder = {
    "semesters",
    "maxUnguarantedParticipants",
    "maxGuarantedParticipants",
    "totalAllocations",
    "participantApproved"
})
public class ProgramAllocation {
   
   protected String semesters;
   protected Integer maxUnguarantedParticipants;
   protected Integer maxGuarantedParticipants;
   protected Integer totalAllocations;
   protected Integer participantApproved;
   
   
   public String getSemesters() {
      return semesters;
   }
   public void setSemesters(String semesters) {
      this.semesters = semesters;
   }
   public Integer getMaxUnguarantedParticipants() {
      return maxUnguarantedParticipants;
   }
   public void setMaxUnguarantedParticipants(Integer maxUnguarantedParticipants) {
      this.maxUnguarantedParticipants = maxUnguarantedParticipants;
   }
   public Integer getMaxGuarantedParticipants() {
      return maxGuarantedParticipants;
   }
   public void setMaxGuarantedParticipants(Integer maxGuarantedParticipants) {
      this.maxGuarantedParticipants = maxGuarantedParticipants;
   }
   public Integer getTotalAllocations() {
      return totalAllocations;
   }
   public void setTotalAllocations(Integer totalAllocations) {
      this.totalAllocations = totalAllocations;
   }
   public Integer getParticipantApproved() {
      return participantApproved;
   }
   public void setParticipantApproved(Integer participantApproved) {
      this.participantApproved = participantApproved;
   } 

}
