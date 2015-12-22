package com.ccighgo.service.components.partner.admin;

public class AssignedSeasonData {

   private Integer seasonId;;
   private Integer departmentProgramId;
   private String seasonName;

   public AssignedSeasonData(Integer seasonId, Integer departmentProgramId) {
      super();
      this.seasonId = seasonId;
      this.departmentProgramId = departmentProgramId;
   }
   public AssignedSeasonData() {
      // TODO Auto-generated constructor stub
   }
   public Integer getSeasonId() {
      return seasonId;
   }
   public void setSeasonId(Integer seasonId) {
      this.seasonId = seasonId;
   }
   public Integer getDepartmentProgramId() {
      return departmentProgramId;
   }
   public void setDepartmentProgramId(Integer departmentProgramId) {
      this.departmentProgramId = departmentProgramId;
   }
   
   public String getSeasonName() {
      return seasonName;
   }
   public void setSeasonName(String seasonName) {
      this.seasonName = seasonName;
   }
}
