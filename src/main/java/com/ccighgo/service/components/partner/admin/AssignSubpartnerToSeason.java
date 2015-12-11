package com.ccighgo.service.components.partner.admin;

import java.util.List;

public class AssignSubpartnerToSeason {

   private List<AssignedSeasonData> assignedSeasonData;
   private Integer subPartner;
   private Integer loginId;
   public List<AssignedSeasonData> getAssignedSeasonData() {
      return assignedSeasonData;
   }
   public void setAssignedSeasonData(List<AssignedSeasonData> assignedSeasonData) {
      this.assignedSeasonData = assignedSeasonData;
   }
   public Integer getSubPartner() {
      return subPartner;
   }
   public void setSubPartner(Integer subPartner) {
      this.subPartner = subPartner;
   }
   public Integer getLoginId() {
      return loginId;
   }
   public void setLoginId(Integer loginId) {
      this.loginId = loginId;
   }
   

}
