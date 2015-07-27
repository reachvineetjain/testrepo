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
 * The persistent class for the CCIStaffRolesDefaultResourcePermissions database table.
 * 
 */
@Entity
@Table(name = "CCIStaffRolesDefaultResourcePermissions")
@NamedQuery(name = "CCIStaffRolesDefaultResourcePermission.findAll", query = "SELECT c FROM CCIStaffRolesDefaultResourcePermission c")
public class CCIStaffRolesDefaultResourcePermission implements Serializable {
   private static final long serialVersionUID = 1L;

   @EmbeddedId
   private CCIStaffRolesDefaultResourcePermissionPK id;

   @Column(nullable = false)
   private Integer createdBy;

   private Timestamp createdOn;

   @Column(nullable = false)
   private Integer modifiedBy;

   @Column(nullable = false)
   private Timestamp modifiedOn;

   // bi-directional many-to-one association to CCIStaffRolesDepartment
   @ManyToOne
   @JoinColumn(name = "cciStaffRolesDepartmentId", nullable = false, insertable = false, updatable = false)
   private CCIStaffRolesDepartment ccistaffRolesDepartment;

   // bi-directional many-to-one association to DepartmentResourceGroup
   @ManyToOne
   @JoinColumn(name = "departmentResourceGroupId", nullable = false)
   private DepartmentResourceGroup departmentResourceGroup;

   // bi-directional many-to-one association to ResourceAction
   @ManyToOne
   @JoinColumn(name = "resourceActionId", nullable = false)
   private ResourceAction resourceAction;

   // bi-directional many-to-one association to ResourcePermission
   @ManyToOne
   @JoinColumn(name = "resourcePermissionId", nullable = false, insertable = false, updatable = false)
   private ResourcePermission resourcePermission;

   public CCIStaffRolesDefaultResourcePermission() {
   }

   public CCIStaffRolesDefaultResourcePermissionPK getId() {
      return this.id;
   }

   public void setId(CCIStaffRolesDefaultResourcePermissionPK id) {
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

   public CCIStaffRolesDepartment getCcistaffRolesDepartment() {
      return this.ccistaffRolesDepartment;
   }

   public void setCcistaffRolesDepartment(CCIStaffRolesDepartment ccistaffRolesDepartment) {
      this.ccistaffRolesDepartment = ccistaffRolesDepartment;
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