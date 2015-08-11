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
 * The persistent class for the CCIStaffUsersResourcePermissions database table.
 * 
 */
@Entity
@Table(name = "CCIStaffUsersResourcePermissions")
@NamedQuery(name = "CCIStaffUsersResourcePermission.findAll", query = "SELECT c FROM CCIStaffUsersResourcePermission c")
public class CCIStaffUsersResourcePermission implements Serializable {
   private static final long serialVersionUID = 1L;

   @EmbeddedId
   private CCIStaffUsersResourcePermissionPK id;

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

   // bi-directional many-to-one association to DepartmentResourceGroup
   @ManyToOne
   @JoinColumn(name = "departmentResourceGroupId", nullable = false)
   private DepartmentResourceGroup departmentResourceGroup;

   // bi-directional many-to-one association to ResourceAction
   @ManyToOne
   @JoinColumn(name = "resourceActionId")
   private ResourceAction resourceAction;

   // bi-directional many-to-one association to ResourcePermission
   @ManyToOne
   @JoinColumn(name = "resourcePermissionId", nullable = false, insertable = false, updatable = false)
   private ResourcePermission resourcePermission;

   public CCIStaffUsersResourcePermission() {
   }

   public CCIStaffUsersResourcePermissionPK getId() {
      return this.id;
   }

   public void setId(CCIStaffUsersResourcePermissionPK id) {
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

   public DepartmentResourceGroup getDepartmentResourceGroup() {
      return this.departmentResourceGroup;
   }

   public void setDepartmentResourceGroup(DepartmentResourceGroup departmentResourceGroup) {
      this.departmentResourceGroup = departmentResourceGroup;
   }

   public ResourceAction getResourceAction() {
      return this.resourceAction;
   }

   public void setResourceAction(ResourceAction resourceAction) {
      this.resourceAction = resourceAction;
   }

   public ResourcePermission getResourcePermission() {
      return this.resourcePermission;
   }

   public void setResourcePermission(ResourcePermission resourcePermission) {
      this.resourcePermission = resourcePermission;
   }

}