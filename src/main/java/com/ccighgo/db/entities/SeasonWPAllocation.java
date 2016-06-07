package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the SeasonWPAllocation database table.
 * 
 */
@Entity
@Table(name="SeasonWPAllocation")
@NamedQuery(name="SeasonWPAllocation.findAll", query="SELECT s FROM SeasonWPAllocation s")
public class SeasonWPAllocation implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer seasonWPAllocationId;

   @Column(nullable=false)
   private Integer createdBy;

   @Column(nullable=false)
   private Timestamp createdOn;

   private Integer maxPax;

   @Column(nullable=false)
   private Integer modifiedBy;

   @Column(nullable=false)
   private Timestamp modifiedOn;

   //bi-directional many-to-one association to DepartmentProgramOption
   @ManyToOne
   @JoinColumn(name="departmentProgramOptionId", nullable=false)
   private DepartmentProgramOption departmentProgramOption;

   //bi-directional many-to-one association to Season
   @ManyToOne
   @JoinColumn(name="seasonId", nullable=false)
   private Season season;

   public SeasonWPAllocation() {
   }

   public Integer getSeasonWPAllocationId() {
      return this.seasonWPAllocationId;
   }

   public void setSeasonWPAllocationId(Integer seasonWPAllocationId) {
      this.seasonWPAllocationId = seasonWPAllocationId;
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

   public Integer getMaxPax() {
      return this.maxPax;
   }

   public void setMaxPax(Integer maxPax) {
      this.maxPax = maxPax;
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