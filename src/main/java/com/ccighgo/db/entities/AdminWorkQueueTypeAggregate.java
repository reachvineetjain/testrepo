package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the AdminWorkQueueTypeAggregate database table.
 * 
 */
@Entity
@Table(name="AdminWorkQueueTypeAggregate")
@NamedQuery(name="AdminWorkQueueTypeAggregate.findAll", query="SELECT a FROM AdminWorkQueueTypeAggregate a")
public class AdminWorkQueueTypeAggregate implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer adminWQTypeAggregateId;

   private Integer adminWQTypeAggregate;

   @Column(length=50)
   private String adminWQTypeName;

   @Column(nullable=false)
   private Timestamp modifiedDate;

   //bi-directional many-to-one association to AdminWorkQueueType
   @ManyToOne
   @JoinColumn(name="adminWQTypeId")
   private AdminWorkQueueType adminWorkQueueType;

   //bi-directional many-to-one association to CCIStaffUser
   @ManyToOne
   @JoinColumn(name="adminGoId")
   private CCIStaffUser ccistaffUser;

   //bi-directional many-to-one association to LookupDepartmentProgram
   @ManyToOne
   @JoinColumn(name="lookupDepartmentProgramId")
   private LookupDepartmentProgram lookupDepartmentProgram;

   public AdminWorkQueueTypeAggregate() {
   }

   public Integer getAdminWQTypeAggregateId() {
      return this.adminWQTypeAggregateId;
   }

   public void setAdminWQTypeAggregateId(Integer adminWQTypeAggregateId) {
      this.adminWQTypeAggregateId = adminWQTypeAggregateId;
   }

   public Integer getAdminWQTypeAggregate() {
      return this.adminWQTypeAggregate;
   }

   public void setAdminWQTypeAggregate(Integer adminWQTypeAggregate) {
      this.adminWQTypeAggregate = adminWQTypeAggregate;
   }

   public String getAdminWQTypeName() {
      return this.adminWQTypeName;
   }

   public void setAdminWQTypeName(String adminWQTypeName) {
      this.adminWQTypeName = adminWQTypeName;
   }

   public Timestamp getModifiedDate() {
      return this.modifiedDate;
   }

   public void setModifiedDate(Timestamp modifiedDate) {
      this.modifiedDate = modifiedDate;
   }

   public AdminWorkQueueType getAdminWorkQueueType() {
      return this.adminWorkQueueType;
   }

   public void setAdminWorkQueueType(AdminWorkQueueType adminWorkQueueType) {
      this.adminWorkQueueType = adminWorkQueueType;
   }

   public CCIStaffUser getCcistaffUser() {
      return this.ccistaffUser;
   }

   public void setCcistaffUser(CCIStaffUser ccistaffUser) {
      this.ccistaffUser = ccistaffUser;
   }

   public LookupDepartmentProgram getLookupDepartmentProgram() {
      return this.lookupDepartmentProgram;
   }

   public void setLookupDepartmentProgram(LookupDepartmentProgram lookupDepartmentProgram) {
      this.lookupDepartmentProgram = lookupDepartmentProgram;
   }

}