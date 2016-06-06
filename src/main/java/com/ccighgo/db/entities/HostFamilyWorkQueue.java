package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the HostFamilyWorkQueue database table.
 * 
 */
@Entity
@Table(name="HostFamilyWorkQueue")
@NamedQuery(name="HostFamilyWorkQueue.findAll", query="SELECT h FROM HostFamilyWorkQueue h")
public class HostFamilyWorkQueue implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer hostFamilyWorkQueueId;

   @Temporal(TemporalType.TIMESTAMP)
   private Date createdDate;

   private Integer targetGoId;

   @Column(length=50)
   private String targetRoleType;

   //bi-directional many-to-one association to DepartmentProgram
   @ManyToOne
   @JoinColumn(name="departmentProgramId")
   private DepartmentProgram departmentProgram;

   //bi-directional many-to-one association to HostFamily
   @ManyToOne
   @JoinColumn(name="hostFamilyGoId")
   private HostFamily hostFamily;

   //bi-directional many-to-one association to HostFamilyWorkQueueCategory
   @ManyToOne
   @JoinColumn(name="hostFamilyWQCategoryId")
   private HostFamilyWorkQueueCategory hostFamilyWorkQueueCategory;

   //bi-directional many-to-one association to HostFamilyWorkQueueType
   @ManyToOne
   @JoinColumn(name="hostFamilyWQTypeId")
   private HostFamilyWorkQueueType hostFamilyWorkQueueType;

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

   public HostFamilyWorkQueue() {
   }

   public Integer getHostFamilyWorkQueueId() {
      return this.hostFamilyWorkQueueId;
   }

   public void setHostFamilyWorkQueueId(Integer hostFamilyWorkQueueId) {
      this.hostFamilyWorkQueueId = hostFamilyWorkQueueId;
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

   public HostFamily getHostFamily() {
      return this.hostFamily;
   }

   public void setHostFamily(HostFamily hostFamily) {
      this.hostFamily = hostFamily;
   }

   public HostFamilyWorkQueueCategory getHostFamilyWorkQueueCategory() {
      return this.hostFamilyWorkQueueCategory;
   }

   public void setHostFamilyWorkQueueCategory(HostFamilyWorkQueueCategory hostFamilyWorkQueueCategory) {
      this.hostFamilyWorkQueueCategory = hostFamilyWorkQueueCategory;
   }

   public HostFamilyWorkQueueType getHostFamilyWorkQueueType() {
      return this.hostFamilyWorkQueueType;
   }

   public void setHostFamilyWorkQueueType(HostFamilyWorkQueueType hostFamilyWorkQueueType) {
      this.hostFamilyWorkQueueType = hostFamilyWorkQueueType;
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