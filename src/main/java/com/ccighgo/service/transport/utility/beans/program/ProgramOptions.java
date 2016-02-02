package com.ccighgo.service.transport.utility.beans.program;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.ccighgo.service.transport.common.response.beans.Response;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProgramOptions", propOrder = {
    "programOptions"
})
public class ProgramOptions extends Response {

   @XmlElement(required = true)
   protected List<ProgramOption> programOptions;
   
   public List<ProgramOption> getProgramOptions() {
      if (programOptions == null) {
         programOptions = new ArrayList<ProgramOption>();
      }
      return this.programOptions;
  }
}
