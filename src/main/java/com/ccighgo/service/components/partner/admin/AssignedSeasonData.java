package com.ccighgo.service.components.partner.admin;

public class AssignedSeasonData {

   private Integer seasonId;;
   private Integer departmentProgramId;
   private String seasonName;
   private String startDate;
   private String appDeadlineDate;
   private String endDate;

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

   public String getStartDate() {
      return startDate;
   }

   public void setStartDate(String startDate) {
      this.startDate = startDate;
   }

   public String getAppDeadlineDate() {
      return appDeadlineDate;
   }

   public void setAppDeadlineDate(String appDeadlineDate) {
      this.appDeadlineDate = appDeadlineDate;
   }

   public String getEndDate() {
      return endDate;
   }

   public void setEndDate(String endDate) {
      this.endDate = endDate;
   }

}
