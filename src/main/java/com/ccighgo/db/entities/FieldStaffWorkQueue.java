package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the FieldStaffWorkQueue database table.
 * 
 */
@Entity
@Table(name="FieldStaffWorkQueue")
@NamedQuery(name="FieldStaffWorkQueue.findAll", query="SELECT f FROM FieldStaffWorkQueue f")
public class FieldStaffWorkQueue implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer fieldStaffWQId;

   @Temporal(TemporalType.TIMESTAMP)
   private Date createdDate;

   private Integer targetGoId;

   @Column(length=45)
   private String targetRoleType;

   //bi-directional many-to-one association to DepartmentProgram
   @ManyToOne
   @JoinColumn(name="departmentProgramId")
   private DepartmentProgram departmentProgram;

   //bi-directional many-to-one association to FieldStaff
   @ManyToOne
   @JoinColumn(name="fieldStaffGoId")
   private FieldStaff fieldStaff;

   //bi-directional many-to-one association to FieldStaffWorkQueueCategory
   @ManyToOne
   @JoinColumn(name="fieldStaffWQCategoryId")
   private FieldStaffWorkQueueCategory fieldStaffWorkQueueCategory;

   //bi-directional many-to-one association to FieldStaffWorkQueueType
   @ManyToOne
   @JoinColumn(name="fieldStaffWQTypeId")
   private FieldStaffWorkQueueType fieldStaffWorkQueueType;

   //bi-directional many-to-one association to LookupDepartmentProgram
   @ManyToOne
   @JoinColumn(name="lookupDepartmentProgramId")
   private LookupDepartmentProgram lookupDepartmentProgram;

   //bi-directional many-to-one association to Season
   @ManyToOne
   @JoinColumn(name="seasonId")
   private Season season;

   //bi-directional many-to-one association to StateType
   @ManyToOne
   @JoinColumn(name="stateTypeId")
   private StateType stateType;

   public FieldStaffWorkQueue() {
   }

   public Integer getFieldStaffWQId() {
      return this.fieldStaffWQId;
   }

   public void setFieldStaffWQId(Integer fieldStaffWQId) {
      this.fieldStaffWQId = fieldStaffWQId;
   }

   public Date getCreatedDate() {
      return this.createdDate;
   }

   public void setCreatedDate(Date createdDate) {
      this.createdDate = createdDate;
   }

   public Integer getTargetGoId() {
      return this.targetGoId;
   }

   public void setTargetGoId(Integer targetGoId) {
      this.targetGoId = targetGoId;
   }

   public String getTargetRoleType() {
      return this.targetRoleType;
   }

   public void setTargetRoleType(String targetRoleType) {
      this.targetRoleType = targetRoleType;
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

   public FieldStaffWorkQueueCategory getFieldStaffWorkQueueCategory() {
      return this.fieldStaffWorkQueueCategory;
   }

   public void setFieldStaffWorkQueueCategory(FieldStaffWorkQueueCategory fieldStaffWorkQueueCategory) {
      this.fieldStaffWorkQueueCategory = fieldStaffWorkQueueCategory;
   }

   public FieldStaffWorkQueueType getFieldStaffWorkQueueType() {
      return this.fieldStaffWorkQueueType;
   }

   public void setFieldStaffWorkQueueType(FieldStaffWorkQueueType fieldStaffWorkQueueType) {
      this.fieldStaffWorkQueueType = fieldStaffWorkQueueType;
   }

   public LookupDepartmentProgram getLookupDepartmentProgram() {
      return this.lookupDepartmentProgram;
   }

   public void setLookupDepartmentProgram(LookupDepartmentProgram lookupDepartmentProgram) {
      this.lookupDepartmentProgram = lookupDepartmentProgram;
   }

   public Season getSeason() {
      return this.season;
   }

   public void setSeason(Season season) {
      this.season = season;
   }

   public StateType getStateType() {
      return this.stateType;
   }

   public void setStateType(StateType stateType) {
      this.stateType = stateType;
   }

}