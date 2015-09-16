package com.ccighgo.service.transport.partner.beans.userdetailandroles;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerUserProgramAndRole", propOrder = { 
      "programName", 
      "partnerUserRole"

})
public class PartnerUserProgramAndRole {

   protected String programName;

   protected List<PartnerUserRole> partnerUserRole;

   public String getProgramName() {
      return programName;
   }

   public void setProgramName(String programName) {
      this.programName = programName;
   }

   public List<PartnerUserRole> getPartnerUserRole() {
      if(partnerUserRole == null)
         partnerUserRole = new ArrayList<PartnerUserRole>();
      return partnerUserRole;
   }

   public void setPartnerUserRole(List<PartnerUserRole> partnerUserRole) {
      this.partnerUserRole = partnerUserRole;
   }

}
