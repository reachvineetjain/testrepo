package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the HostFamilyQuickStatsCategoryAggregate database table.
 * 
 */
@Entity
@Table(name="HostFamilyQuickStatsCategoryAggregate")
@NamedQuery(name="HostFamilyQuickStatsCategoryAggregate.findAll", query="SELECT h FROM HostFamilyQuickStatsCategoryAggregate h")
public class HostFamilyQuickStatsCategoryAggregate implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer hostFamilyQuickStatsCategoryAggregateId;

   private Integer hostFamilyQSCategoryAggregate;

   @Column(length=50)
   private String hostFamilyQSCategoryName;

   private Timestamp modifiedDate;

   //bi-directional many-to-one association to HostFamily
   @ManyToOne
   @JoinColumn(name="hostFamilyGoId")
   private HostFamily hostFamily;

   //bi-directional many-to-one association to HostFamilyQuickStatsCategory
   @ManyToOne
   @JoinColumn(name="hostFamilyQSCategoryId")
   private HostFamilyQuickStatsCategory hostFamilyQuickStatsCategory;

   //bi-directional many-to-one association to HostFamilyQuickStatsType
   @ManyToOne
   @JoinColumn(name="hostFamilyQSTypeId")
   private HostFamilyQuickStatsType hostFamilyQuickStatsType;

   //bi-directional many-to-one association to LookupDepartmentProgram
   @ManyToOne
   @JoinColumn(name="lookupDepartmentProgramId")
   private LookupDepartmentProgram lookupDepartmentProgram;

   public HostFamilyQuickStatsCategoryAggregate() {
   }

   public Integer getHostFamilyQuickStatsCategoryAggregateId() {
      return this.hostFamilyQuickStatsCategoryAggregateId;
   }

   public void setHostFamilyQuickStatsCategoryAggregateId(Integer hostFamilyQuickStatsCategoryAggregateId) {
      this.hostFamilyQuickStatsCategoryAggregateId = hostFamilyQuickStatsCategoryAggregateId;
   }

   public Integer getHostFamilyQSCategoryAggregate() {
      return this.hostFamilyQSCategoryAggregate;
   }

   public void setHostFamilyQSCategoryAggregate(Integer hostFamilyQSCategoryAggregate) {
      this.hostFamilyQSCategoryAggregate = hostFamilyQSCategoryAggregate;
   }

   public String getHostFamilyQSCategoryName() {
      return this.hostFamilyQSCategoryName;
   }

   public void setHostFamilyQSCategoryName(String hostFamilyQSCategoryName) {
      this.hostFamilyQSCategoryName = hostFamilyQSCategoryName;
   }

   public Timestamp getModifiedDate() {
      return this.modifiedDate;
   }

   public void setModifiedDate(Timestamp modifiedDate) {
      this.modifiedDate = modifiedDate;
   }

   public HostFamily getHostFamily() {
      return this.hostFamily;
   }

   public void setHostFamily(HostFamily hostFamily) {
      this.hostFamily = hostFamily;
   }

   public HostFamilyQuickStatsCategory getHostFamilyQuickStatsCategory() {
      return this.hostFamilyQuickStatsCategory;
   }

   public void setHostFamilyQuickStatsCategory(HostFamilyQuickStatsCategory hostFamilyQuickStatsCategory) {
      this.hostFamilyQuickStatsCategory = hostFamilyQuickStatsCategory;
   }

   public HostFamilyQuickStatsType getHostFamilyQuickStatsType() {
      return this.hostFamilyQuickStatsType;
   }

   public void setHostFamilyQuickStatsType(HostFamilyQuickStatsType hostFamilyQuickStatsType) {
      this.hostFamilyQuickStatsType = hostFamilyQuickStatsType;
   }

   public LookupDepartmentProgram getLookupDepartmentProgram() {
      return this.lookupDepartmentProgram;
   }

   public void setLookupDepartmentProgram(LookupDepartmentProgram lookupDepartmentProgram) {
      this.lookupDepartmentProgram = lookupDepartmentProgram;
   }

}