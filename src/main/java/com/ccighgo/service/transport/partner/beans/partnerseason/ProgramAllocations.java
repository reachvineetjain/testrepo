package com.ccighgo.service.transport.partner.beans.partnerseason;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProgramAllocations", propOrder = {
    "programAllocations"
})
public class ProgramAllocations {

   @XmlElement(required = true)
   protected List<ProgramAllocation> programAllocations;

   public List<ProgramAllocation> getProgramAllocations() {
      if (programAllocations == null) {
         programAllocations = new ArrayList<ProgramAllocation>();
      }
      return this.programAllocations;
  }
}
