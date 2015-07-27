package com.ccighgo.db.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the SeasonProgramNotes database table.
 * 
 */
@Entity
@Table(name = "SeasonProgramNotes")
@NamedQuery(name = "SeasonProgramNote.findAll", query = "SELECT s FROM SeasonProgramNote s")
public class SeasonProgramNote implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer seasonProgramNotesId;

   private byte active;

   @Column(nullable = false)
   private Integer createdBy;

   private Timestamp createdOn;

   @Column(nullable = false)
   private Integer modifiedBy;

   @Column(nullable = false)
   private Timestamp modifiedOn;

   @Column(length = 1000)
   private String programNote;

   // bi-directional many-to-one association to DepartmentProgram
   @ManyToOne
   @JoinColumn(name = "departmentProgramId")
   private DepartmentProgram departmentProgram;

   // bi-directional many-to-one association to Season
   @ManyToOne
   @JoinColumn(name = "seasonId", nullable = false)
   private Season season;

   public SeasonProgramNote() {
   }

   public Integer getSeasonProgramNotesId() {
      if (this.seasonProgramNotesId != null)
         return this.seasonProgramNotesId;
      return 0;
   }

   public void setSeasonProgramNotesId(Integer seasonProgramNotesId) {
      this.seasonProgramNotesId = seasonProgramNotesId;
   }

   public byte getActive() {
      return this.active;
   }

   public void setActive(byte active) {
      this.active = active;
   }

   public Integer getCreatedBy() {
      if (this.createdBy != null)
         return this.createdBy;
      return 0;
   }

   public void setCreatedBy(Integer createdBy) {
      this.createdBy = createdBy;
   }

   public Timestamp getCreatedOn() {
      return this.createdOn;
   }

   public void setCreatedOn(Timestamp createdOn) {
      this.createdOn = createdOn;
   }

   public Integer getModifiedBy() {
      if (this.modifiedBy != null)
         return this.modifiedBy;
      return 0;
   }

   public void setModifiedBy(Integer modifiedBy) {
      this.modifiedBy = modifiedBy;
   }

   public Timestamp getModifiedOn() {
      return this.modifiedOn;
   }

   public void setModifiedOn(Timestamp modifiedOn) {
      this.modifiedOn = modifiedOn;
   }

   public String getProgramNote() {
      return this.programNote;
   }

   public void setProgramNote(String programNote) {
      this.programNote = programNote;
   }

   public DepartmentProgram getDepartmentProgram() {
      return this.departmentProgram;
   }

   public void setDepartmentProgram(DepartmentProgram departmentProgram) {
      this.departmentProgram = departmentProgram;
   }

   public Season getSeason() {
      return this.season;
   }

   public void setSeason(Season season) {
      this.season = season;
   }

}