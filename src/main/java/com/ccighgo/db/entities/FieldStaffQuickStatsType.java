package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the FieldStaffQuickStatsType database table.
 * 
 */
@Entity
@Table(name="FieldStaffQuickStatsType")
@NamedQuery(name="FieldStaffQuickStatsType.findAll", query="SELECT f FROM FieldStaffQuickStatsType f")
public class FieldStaffQuickStatsType implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer fieldStaffQSTypeId;

   @Column(length=50)
   private String fieldStaffQSTypeName;

   //bi-directional many-to-one association to FieldStaffQuickStatsCategory
   @OneToMany(mappedBy="fieldStaffQuickStatsType")
   private List<FieldStaffQuickStatsCategory> fieldStaffQuickStatsCategories;

   //bi-directional many-to-one association to FieldStaffQuickStatsCategoryAggregate
   @OneToMany(mappedBy="fieldStaffQuickStatsType")
   private List<FieldStaffQuickStatsCategoryAggregate> fieldStaffQuickStatsCategoryAggregates;

   //bi-directional many-to-one association to LookupDepartmentProgram
   @ManyToOne
   @JoinColumn(name="lookupDepartmentProgramId")
   private LookupDepartmentProgram lookupDepartmentProgram;

   //bi-directional many-to-one association to FieldStaffQuickStatsTypeAggregate
   @OneToMany(mappedBy="fieldStaffQuickStatsType")
   private List<FieldStaffQuickStatsTypeAggregate> fieldStaffQuickStatsTypeAggregates;

   public FieldStaffQuickStatsType() {
   }

   public Integer getFieldStaffQSTypeId() {
      return this.fieldStaffQSTypeId;
   }

   public void setFieldStaffQSTypeId(Integer fieldStaffQSTypeId) {
      this.fieldStaffQSTypeId = fieldStaffQSTypeId;
   }

   public String getFieldStaffQSTypeName() {
      return this.fieldStaffQSTypeName;
   }

   public void setFieldStaffQSTypeName(String fieldStaffQSTypeName) {
      this.fieldStaffQSTypeName = fieldStaffQSTypeName;
   }

   public List<FieldStaffQuickStatsCategory> getFieldStaffQuickStatsCategories() {
      return this.fieldStaffQuickStatsCategories;
   }

   public void setFieldStaffQuickStatsCategories(List<FieldStaffQuickStatsCategory> fieldStaffQuickStatsCategories) {
      this.fieldStaffQuickStatsCategories = fieldStaffQuickStatsCategories;
   }

   public FieldStaffQuickStatsCategory addFieldStaffQuickStatsCategory(FieldStaffQuickStatsCategory fieldStaffQuickStatsCategory) {
      getFieldStaffQuickStatsCategories().add(fieldStaffQuickStatsCategory);
      fieldStaffQuickStatsCategory.setFieldStaffQuickStatsType(this);

      return fieldStaffQuickStatsCategory;
   }

   public FieldStaffQuickStatsCategory removeFieldStaffQuickStatsCategory(FieldStaffQuickStatsCategory fieldStaffQuickStatsCategory) {
      getFieldStaffQuickStatsCategories().remove(fieldStaffQuickStatsCategory);
      fieldStaffQuickStatsCategory.setFieldStaffQuickStatsType(null);

      return fieldStaffQuickStatsCategory;
   }

   public List<FieldStaffQuickStatsCategoryAggregate> getFieldStaffQuickStatsCategoryAggregates() {
      return this.fieldStaffQuickStatsCategoryAggregates;
   }

   public void setFieldStaffQuickStatsCategoryAggregates(List<FieldStaffQuickStatsCategoryAggregate> fieldStaffQuickStatsCategoryAggregates) {
      this.fieldStaffQuickStatsCategoryAggregates = fieldStaffQuickStatsCategoryAggregates;
   }

   public FieldStaffQuickStatsCategoryAggregate addFieldStaffQuickStatsCategoryAggregate(FieldStaffQuickStatsCategoryAggregate fieldStaffQuickStatsCategoryAggregate) {
      getFieldStaffQuickStatsCategoryAggregates().add(fieldStaffQuickStatsCategoryAggregate);
      fieldStaffQuickStatsCategoryAggregate.setFieldStaffQuickStatsType(this);

      return fieldStaffQuickStatsCategoryAggregate;
   }

   public FieldStaffQuickStatsCategoryAggregate removeFieldStaffQuickStatsCategoryAggregate(FieldStaffQuickStatsCategoryAggregate fieldStaffQuickStatsCategoryAggregate) {
      getFieldStaffQuickStatsCategoryAggregates().remove(fieldStaffQuickStatsCategoryAggregate);
      fieldStaffQuickStatsCategoryAggregate.setFieldStaffQuickStatsType(null);

      return fieldStaffQuickStatsCategoryAggregate;
   }

   public LookupDepartmentProgram getLookupDepartmentProgram() {
      return this.lookupDepartmentProgram;
   }

   public void setLookupDepartmentProgram(LookupDepartmentProgram lookupDepartmentProgram) {
      this.lookupDepartmentProgram = lookupDepartmentProgram;
   }

   public List<FieldStaffQuickStatsTypeAggregate> getFieldStaffQuickStatsTypeAggregates() {
      return this.fieldStaffQuickStatsTypeAggregates;
   }

   public void setFieldStaffQuickStatsTypeAggregates(List<FieldStaffQuickStatsTypeAggregate> fieldStaffQuickStatsTypeAggregates) {
      this.fieldStaffQuickStatsTypeAggregates = fieldStaffQuickStatsTypeAggregates;
   }

   public FieldStaffQuickStatsTypeAggregate addFieldStaffQuickStatsTypeAggregate(FieldStaffQuickStatsTypeAggregate fieldStaffQuickStatsTypeAggregate) {
      getFieldStaffQuickStatsTypeAggregates().add(fieldStaffQuickStatsTypeAggregate);
      fieldStaffQuickStatsTypeAggregate.setFieldStaffQuickStatsType(this);

      return fieldStaffQuickStatsTypeAggregate;
   }

   public FieldStaffQuickStatsTypeAggregate removeFieldStaffQuickStatsTypeAggregate(FieldStaffQuickStatsTypeAggregate fieldStaffQuickStatsTypeAggregate) {
      getFieldStaffQuickStatsTypeAggregates().remove(fieldStaffQuickStatsTypeAggregate);
      fieldStaffQuickStatsTypeAggregate.setFieldStaffQuickStatsType(null);

      return fieldStaffQuickStatsTypeAggregate;
   }

}