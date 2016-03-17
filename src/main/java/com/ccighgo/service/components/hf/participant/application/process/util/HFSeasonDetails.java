package com.ccighgo.service.components.hf.participant.application.process.util;

public class HFSeasonDetails {
   private String seasonName;
   private int seasonId;
   private int departmentProgramId;
   private int hostFamilySeasonId;

   public String getSeasonName() {
      return seasonName;
   }

   public void setSeasonName(String seasonName) {
      this.seasonName = seasonName;
   }

   public int getSeasonId() {
      return seasonId;
   }

   public void setSeasonId(int seasonId) {
      this.seasonId = seasonId;
   }

   public int getDepartmentProgramId() {
      return departmentProgramId;
   }

   public void setDepartmentProgramId(int departmentProgramId) {
      this.departmentProgramId = departmentProgramId;
   }

   public int getHostFamilySeasonId() {
      return hostFamilySeasonId;
   }

   public void setHostFamilySeasonId(int hostFamilySeasonId) {
      this.hostFamilySeasonId = hostFamilySeasonId;
   }
}
