package com.ccighgo.service.transport.partner.beans.userdetailandroles;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.ccighgo.service.transport.utility.beans.gender.Salutation;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerUsersDetailAndRoles", propOrder = {
    "partnerUsersDetailAndRoles"
})
public class PartnerUsersDetailAndRoles {

   
   @XmlElement(required = true)
   protected List<PartnerUserDetailAndRoles> partnerUsersDetailAndRoles;
   
   public List<PartnerUserDetailAndRoles> getPartnerUsersDetailAndRoles() {
      if (partnerUsersDetailAndRoles == null) {
         partnerUsersDetailAndRoles = new ArrayList<PartnerUserDetailAndRoles>();
      }
      return this.partnerUsersDetailAndRoles;
  }
}
