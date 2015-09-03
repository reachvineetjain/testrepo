package com.ccighgo.service.transport.usermanagement.beans.cciuser;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SupervisorDetail", propOrder = {
    "cciUserId",
    "firstName",
    "lastName",
    "photoPath",
    "userRole"
})
public class SupervisorDetail {
   
   protected int cciUserId;
   @XmlElement(required = true)
   protected String firstName;
   @XmlElement(required = true)
   protected String lastName;
   protected String photoPath;
   protected List<CCIUserStaffRole> userRole;
   
   
   public int getCciUserId() {
      return cciUserId;
   }
   public void setCciUserId(int cciUserId) {
      this.cciUserId = cciUserId;
   }
   public String getFirstName() {
      return firstName;
   }
   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }
   public String getLastName() {
      return lastName;
   }
   public void setLastName(String lastName) {
      this.lastName = lastName;
   }
   public String getPhotoPath() {
      return photoPath;
   }
   public void setPhotoPath(String photoPath) {
      this.photoPath = photoPath;
   }
   public List<CCIUserStaffRole> getUserRole() {
      if (userRole == null) {
         userRole = new ArrayList<CCIUserStaffRole>();
     }
     return this.userRole;
   }
   public void setUserRole(List<CCIUserStaffRole> userRole) {
      this.userRole = userRole;
   }

}
