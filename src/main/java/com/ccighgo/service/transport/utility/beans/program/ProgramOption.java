package com.ccighgo.service.transport.utility.beans.program;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProgramOption", propOrder = {
    "departmentProgramOptionId",
    "departmentProgramId",
    "programOptionCode",
    "programOptionName"
})
public class ProgramOption {

   protected int departmentProgramOptionId;
   protected int departmentProgramId;
   protected String programOptionCode;
   protected String programOptionName;
   
   
   public int getDepartmentProgramOptionId() {
      return departmentProgramOptionId;
   }
   public void setDepartmentProgramOptionId(int departmentProgramOptionId) {
      this.departmentProgramOptionId = departmentProgramOptionId;
   }
   public int getDepartmentProgramId() {
      return departmentProgramId;
   }
   public void setDepartmentProgramId(int departmentProgramId) {
      this.departmentProgramId = departmentProgramId;
   }
   public String getProgramOptionCode() {
      return programOptionCode;
   }
   public void setProgramOptionCode(String programOptionCode) {
      this.programOptionCode = programOptionCode;
   }
   public String getProgramOptionName() {
      return programOptionName;
   }
   public void setProgramOptionName(String programOptionName) {
      this.programOptionName = programOptionName;
   }
   
}
