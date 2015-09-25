package com.ccighgo.service.transport.partner.beans.subpartner;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubPartnerNotes", propOrder = {
      "subPartnerNotes"
})
public class SubPartnerNotes {

   @XmlElement(required = true)
   protected List<SubPartnerNote> subPartnerNotes;
   
   public List<SubPartnerNote> getSubPartnerNotes() {
      if (subPartnerNotes == null) {
         subPartnerNotes = new ArrayList<SubPartnerNote>();
      }
      return this.subPartnerNotes;
  }
}
