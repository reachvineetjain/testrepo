package com.ccighgo.service.components.season;

import java.util.List;

public class CommonNotesObject {
   private Integer seasonId;
   private Integer departmentProgramId;
   private List<String> notes;

   public Integer getSeasonId() {
      return seasonId;
   }

   public void setSeasonId(Integer seasonId) {
      this.seasonId = seasonId;
   }

   public List<String> getNotes() {
      return notes;
   }

   public void setNotes(List<String> notes) {
      this.notes = notes;
   }

   public Integer getDepartmentProgramId() {
      return departmentProgramId;
   }

   public void setDepartmentProgramId(Integer departmentProgramId) {
      this.departmentProgramId = departmentProgramId;
   }

}
