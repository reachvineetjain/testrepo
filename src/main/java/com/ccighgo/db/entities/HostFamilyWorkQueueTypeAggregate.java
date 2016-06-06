package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the HostFamilyWorkQueueTypeAggregate database table.
 * 
 */
@Entity
@Table(name="HostFamilyWorkQueueTypeAggregate")
@NamedQuery(name="HostFamilyWorkQueueTypeAggregate.findAll", query="SELECT h FROM HostFamilyWorkQueueTypeAggregate h")
public class HostFamilyWorkQueueTypeAggregate implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer hostFamilyWorkQueueTypeAggregateId;

   private Integer hostFamilyWQTypeAggregate;

   @Column(length=50)
   private String hostFamilyWQTypeName;

   @Temporal(TemporalType.TIMESTAMP)
   private Date modifiedDate;

   //bi-directional many-to-one association to HostFamily
   @ManyToOne
   @JoinColumn(name="hostFamilyGoId")
   private HostFamily hostFamily;

   //bi-directional many-to-one association to HostFamilyWorkQueueType
   @ManyToOne
   @JoinColumn(name="hostFamilyWQTypeId")
   private HostFamilyWorkQueueType hostFamilyWorkQueueType;

   //bi-directional many-to-one association to LookupDepartmentProgram
   @ManyToOne
   @JoinColumn(name="lookupDepartmentProgramId")
   private LookupDepartmentProgram lookupDepartmentProgram;

   public HostFamilyWorkQueueTypeAggregate() {
   }

   public Integer getHostFamilyWorkQueueTypeAggregateId() {
      return this.hostFamilyWorkQueueTypeAggregateId;
   }

   public void setHostFamilyWorkQueueTypeAggregateId(Integer hostFamilyWorkQueueTypeAggregateId) {
      this.hostFamilyWorkQueueTypeAggregateId = hostFamilyWorkQueueTypeAggregateId;
   }

   public Integer getHostFamilyWQTypeAggregate() {
      return this.hostFamilyWQTypeAggregate;
   }

   public void setHostFamilyWQTypeAggregate(Integer hostFamilyWQTypeAggregate) {
      this.hostFamilyWQTypeAggregate = hostFamilyWQTypeAggregate;
   }

   public String getHostFamilyWQTypeName() {
      return this.hostFamilyWQTypeName;
   }

   public void setHostFamilyWQTypeName(String hostFamilyWQTypeName) {
      this.hostFamilyWQTypeName = hostFamilyWQTypeName;
   }

   public Date getModifiedDate() {
      return this.modifiedDate;
   }

   public void setModifiedDate(Date modifiedDate) {
      this.modifiedDate = modifiedDate;
   }

   public HostFamily getHostFamily() {
      return this.hostFamily;
   }

   public void setHostFamily(HostFamily hostFamily) {
      this.hostFamily = hostFamily;
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

}