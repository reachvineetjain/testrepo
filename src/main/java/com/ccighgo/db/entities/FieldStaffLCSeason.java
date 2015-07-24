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
 * The persistent class for the FieldStaffLCSeason database table.
 * 
 */
@Entity
@Table(name = "FieldStaffLCSeason")
@NamedQuery(name = "FieldStaffLCSeason.findAll", query = "SELECT f FROM FieldStaffLCSeason f")
public class FieldStaffLCSeason implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer fieldStaffLCSeasonId;

   @Column(nullable = false)
   private Integer createdBy;

   private Timestamp createdOn;

   private Integer erdId;

   private Integer fieldStaffId;

   @Column(nullable = false)
   private Integer modifiedBy;

   @Column(nullable = false)
   private Timestamp modifiedOn;

   private Integer rdId;

   private Integer rmId;

   // bi-directional many-to-one association to Season
   @ManyToOne
   @JoinColumn(name = "seasonId")
   private Season season;

   // bi-directional many-to-one association to FieldStaffType
   @ManyToOne
   @JoinColumn(name = "fieldStaffTypeId")
   private FieldStaffType fieldStaffType;

   // bi-directional many-to-one association to DepartmentProgram
   @ManyToOne
   @JoinColumn(name = "departmentProgramId")
   private DepartmentProgram departmentProgram;

   // bi-directional many-to-one association to SeasonGeographyConfiguration
   @ManyToOne
   @JoinColumn(name = "seasonGeographyConfigurationId")
   private SeasonGeographyConfiguration seasonGeographyConfiguration;

   public FieldStaffLCSeason() {
   }

   public Integer getFieldStaffLCSeasonId() {
      if (this.fieldStaffLCSeasonId != null)
         return this.fieldStaffLCSeasonId;
      return 0;
   }

   public void setFieldStaffLCSeasonId(Integer fieldStaffLCSeasonId) {
      this.fieldStaffLCSeasonId = fieldStaffLCSeasonId;
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

   public Integer getErdId() {
      if (this.erdId != null)
         return this.erdId;
      return 0;
   }

   public void setErdId(Integer erdId) {
      this.erdId = erdId;
   }

   public Integer getFieldStaffId() {
      if (this.fieldStaffId != null)
         return this.fieldStaffId;
      return 0;
   }

   public void setFieldStaffId(Integer fieldStaffId) {
      this.fieldStaffId = fieldStaffId;
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

   public Integer getRdId() {
      if (this.rdId != null)
         return this.rdId;
      return 0;
   }

   public void setRdId(Integer rdId) {
      this.rdId = rdId;
   }

   public Integer getRmId() {
      if (this.rmId != null)
         return this.rmId;
      return 0;
   }

   public void setRmId(Integer rmId) {
      this.rmId = rmId;
   }

   public Season getSeason() {
      return this.season;
   }

   public void setSeason(Season season) {
      this.season = season;
   }

   public FieldStaffType getFieldStaffType() {
      return this.fieldStaffType;
   }

   public void setFieldStaffType(FieldStaffType fieldStaffType) {
      this.fieldStaffType = fieldStaffType;
   }

   public DepartmentProgram getDepartmentProgram() {
      return this.departmentProgram;
   }

   public void setDepartmentProgram(DepartmentProgram departmentProgram) {
      this.departmentProgram = departmentProgram;
   }

   public SeasonGeographyConfiguration getSeasonGeographyConfiguration() {
      return this.seasonGeographyConfiguration;
   }

   public void setSeasonGeographyConfiguration(SeasonGeographyConfiguration seasonGeographyConfiguration) {
      this.seasonGeographyConfiguration = seasonGeographyConfiguration;
   }

}