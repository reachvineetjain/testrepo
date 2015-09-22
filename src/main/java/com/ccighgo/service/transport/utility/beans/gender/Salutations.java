package com.ccighgo.service.transport.utility.beans.gender;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.ccighgo.service.transport.common.response.beans.Response;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Salutations", propOrder = {
    "salutations"
})

public class Salutations extends Response {

   @XmlElement(required = true)
   protected List<Salutation> salutations;
   
   public List<Salutation> getSalutations() {
      if (salutations == null) {
         salutations = new ArrayList<Salutation>();
      }
      return this.salutations;
  }
}
