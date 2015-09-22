package com.ccighgo.service.transport.partner.beans.userdetailandroles;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerUserProgramsAndRoles", propOrder = { 
      "partnerUserProgramAndRole"

})
public class PartnerUserProgramsAndRoles {

   protected List<PartnerUserProgramAndRole> partnerUserProgramAndRole;

   public List<PartnerUserProgramAndRole> getPartnerUserProgramAndRole() {
      if(partnerUserProgramAndRole == null)
         partnerUserProgramAndRole = new ArrayList<PartnerUserProgramAndRole>();
      return partnerUserProgramAndRole;
   }

   public void setPartnerUserProgramAndRole(List<PartnerUserProgramAndRole> partnerUserProgramAndRole) {
      this.partnerUserProgramAndRole = partnerUserProgramAndRole;
   }

}
