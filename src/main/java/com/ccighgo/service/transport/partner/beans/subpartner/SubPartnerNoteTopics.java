package com.ccighgo.service.transport.partner.beans.subpartner;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubPartnerNoteTopics", propOrder = {
      "subPartnerNoteTopics"
})

public class SubPartnerNoteTopics {

   @XmlElement(required = true)
   protected List<SubPartnerNoteTopic> subPartnerNoteTopics;
   
   public List<SubPartnerNoteTopic> getSubPartnerNoteTopics() {
      if (subPartnerNoteTopics == null) {
         subPartnerNoteTopics = new ArrayList<SubPartnerNoteTopic>();
      }
      return this.subPartnerNoteTopics;
  }

}
