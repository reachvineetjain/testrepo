package com.ccighgo.service.components.partner.admin;

import java.util.List;

public class AssignSubpartnerToSeason {

   private List<Integer> seasonId;
   private Integer subPartner;
   private Integer departmentProgramId;
   private Integer loginId;
   public List<Integer> getSeasonId() {
      return seasonId;
   }
   public void setSeasonId(List<Integer> seasonId) {
      this.seasonId = seasonId;
   }
   public Integer getSubPartner() {
      return subPartner;
   }
   public void setSubPartner(Integer subPartner) {
      this.subPartner = subPartner;
   }
   public Integer getDepartmentProgramId() {
      return departmentProgramId;
   }
   public void setDepartmentProgramId(Integer departmentProgramId) {
      this.departmentProgramId = departmentProgramId;
   }
   public Integer getLoginId() {
      return loginId;
   }
   public void setLoginId(Integer loginId) {
      this.loginId = loginId;
   }

   
}
