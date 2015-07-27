package com.ccighgo.db.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the CCIStaffUserProgram database table.
 * 
 */
@Entity
@Table(name = "CCIStaffUserProgram")
@NamedQuery(name = "CCIStaffUserProgram.findAll", query = "SELECT c FROM CCIStaffUserProgram c")
public class CCIStaffUserProgram implements Serializable {
   private static final long serialVersionUID = 1L;

   @EmbeddedId
   private CCIStaffUserProgramPK id;

   @Column(nullable = false)
   private Integer createdBy;

   private Timestamp createdOn;

   @Column(nullable = false)
   private Integer modifiedBy;

   @Column(nullable = false)
   private Timestamp modifiedOn;

   // bi-directional many-to-one association to CCIStaffUser
   @ManyToOne
   @JoinColumn(name = "cciStaffUserId", nullable = false, insertable = false, updatable = false)
   private CCIStaffUser ccistaffUser;

   // bi-directional many-to-one association to DepartmentProgram
   @ManyToOne
   @JoinColumn(name = "departmentProgramId", nullable = false, insertable = false, updatable = false)
   private DepartmentProgram departmentProgram;

   public CCIStaffUserProgram() {
   }

   public CCIStaffUserProgramPK getId() {
      return this.id;
   }

   public void setId(CCIStaffUserProgramPK id) {
      this.id = id;
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

   public CCIStaffUser getCcistaffUser() {
      return this.ccistaffUser;
   }

   public void setCcistaffUser(CCIStaffUser ccistaffUser) {
      this.ccistaffUser = ccistaffUser;
   }

   public DepartmentProgram getDepartmentProgram() {
      return this.departmentProgram;
   }

   public void setDepartmentProgram(DepartmentProgram departmentProgram) {
      this.departmentProgram = departmentProgram;
   }

}