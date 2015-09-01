package com.ccighgo.service.transport.usermanagement.beans.cciuser;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.ccighgo.service.transport.common.response.beans.Response;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SupervisorDetails", propOrder = {
    "recordCount",
    "supervisorDetails"
})
public class SupervisorDetails extends Response{
   
   protected Integer recordCount;
   protected List<SupervisorDetail> SupervisorDetails;
   
   public Integer getRecordCount() {
      return recordCount;
   }
   public void setRecordCount(Integer recordCount) {
      this.recordCount = recordCount;
   }
   public List<SupervisorDetail> getSupervisorDetails() {
      if (SupervisorDetails == null) {
         SupervisorDetails = new ArrayList<SupervisorDetail>();
     }
     return this.SupervisorDetails;
   }
   public void setSupervisorDetails(List<SupervisorDetail> supervisorDetails) {
      SupervisorDetails = supervisorDetails;
   }
   
   

}
