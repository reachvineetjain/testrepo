package com.ccighgo.db.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the FieldStaffLCSeason database table.
 * 
 */
@Entity
@NamedQuery(name = "FieldStaffLCSeason.findAll", query = "SELECT f FROM FieldStaffLCSeason f")
public class FieldStaffLCSeason implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer fieldStaffLCSeasonId;

   private Integer createdBy;

   private Timestamp createdOn;

   private Integer modifiedBy;

   private Timestamp modifiedOn;

   // bi-directional many-to-one association to DepartmentProgram
   @ManyToOne
   @JoinColumn(name = "departmentProgramId")
   private DepartmentProgram departmentProgram;

   // bi-directional many-to-one association to FieldStaff
   @ManyToOne
   @JoinColumn(name = "fieldStaffId")
   private FieldStaff fieldStaff;

   // bi-directional many-to-one association to Season
   @ManyToOne
   @JoinColumn(name = "seasonId")
   private Season season;

   // bi-directional many-to-one association to SeasonGeographyConfiguration
   @ManyToOne
   @JoinColumn(name = "seasonGeographyConfigurationId")
   private SeasonGeographyConfiguration seasonGeographyConfiguration;

   public FieldStaffLCSeason() {
   }

   public Integer getFieldStaffLCSeasonId() {
      return this.fieldStaffLCSeasonId;
   }

   public void setFieldStaffLCSeasonId(Integer fieldStaffLCSeasonId) {
      this.fieldStaffLCSeasonId = fieldStaffLCSeasonId;
   }

   public Integer getCreatedBy() {
      return this.createdBy;
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
      return this.modifiedBy;
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

   public DepartmentProgram getDepartmentProgram() {
      return this.departmentProgram;
   }

   public void setDepartmentProgram(DepartmentProgram departmentProgram) {
      this.departmentProgram = departmentProgram;
   }

   public FieldStaff getFieldStaff() {
      return this.fieldStaff;
   }

   public void setFieldStaff(FieldStaff fieldStaff) {
      this.fieldStaff = fieldStaff;
   }

   public Season getSeason() {
      return this.season;
   }

   public void setSeason(Season season) {
      this.season = season;
   }

   public SeasonGeographyConfiguration getSeasonGeographyConfiguration() {
      return this.seasonGeographyConfiguration;
   }

   public void setSeasonGeographyConfiguration(SeasonGeographyConfiguration seasonGeographyConfiguration) {
      this.seasonGeographyConfiguration = seasonGeographyConfiguration;
   }

}