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
 * The persistent class for the SeasonHSPAllocation database table.
 * 
 */
@Entity
@Table(name = "SeasonHSPAllocation")
@NamedQuery(name = "SeasonHSPAllocation.findAll", query = "SELECT s FROM SeasonHSPAllocation s")
public class SeasonHSPAllocation implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer seasonHSPAllocationId;

   @Column(nullable = false)
   private Integer createdBy;

   private Timestamp createdOn;

   private Integer maxGuaranteedPax;

   private Integer maxUnguaranteedPax;

   @Column(nullable = false)
   private Integer modifiedBy;

   @Column(nullable = false)
   private Timestamp modifiedOn;

   // bi-directional many-to-one association to DepartmentProgramOption
   @ManyToOne
   @JoinColumn(name = "departmentProgramOptionId", nullable = false)
   private DepartmentProgramOption departmentProgramOption;

   // bi-directional many-to-one association to Season
   @ManyToOne
   @JoinColumn(name = "seasonId", nullable = false)
   private Season season;

   public SeasonHSPAllocation() {
   }

   public Integer getSeasonHSPAllocationId() {
      if (this.seasonHSPAllocationId != null)
         return this.seasonHSPAllocationId;
      return 0;
   }

   public void setSeasonHSPAllocationId(Integer seasonHSPAllocationId) {
      this.seasonHSPAllocationId = seasonHSPAllocationId;
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

   public Integer getMaxGuaranteedPax() {
      if (this.maxGuaranteedPax != null)
         return this.maxGuaranteedPax;
      return 0;
   }

   public void setMaxGuaranteedPax(Integer maxGuaranteedPax) {
      this.maxGuaranteedPax = maxGuaranteedPax;
   }

   public Integer getMaxUnguaranteedPax() {
      if (this.maxUnguaranteedPax != null)
         return this.maxUnguaranteedPax;
      return 0;
   }

   public void setMaxUnguaranteedPax(Integer maxUnguaranteedPax) {
      this.maxUnguaranteedPax = maxUnguaranteedPax;
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

   public DepartmentProgramOption getDepartmentProgramOption() {
      return this.departmentProgramOption;
   }

   public void setDepartmentProgramOption(DepartmentProgramOption departmentProgramOption) {
      this.departmentProgramOption = departmentProgramOption;
   }

   public Season getSeason() {
      return this.season;
   }

   public void setSeason(Season season) {
      this.season = season;
   }

}