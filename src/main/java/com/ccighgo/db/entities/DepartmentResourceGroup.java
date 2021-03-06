package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the DepartmentResourceGroups database table.
 * 
 */
@Entity
@Table(name="DepartmentResourceGroups")
@NamedQuery(name="DepartmentResourceGroup.findAll", query="SELECT d FROM DepartmentResourceGroup d")
public class DepartmentResourceGroup implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer departmentResourceGroupId;

   @Column(nullable=false)
   private Integer createdBy;

   @Column(nullable=false)
   private Timestamp createdOn;

   @Column(nullable=false)
   private Integer modifiedBy;

   @Column(nullable=false)
   private Timestamp modifiedOn;

   @Column(nullable=false, length=50)
   private String resourceGroupName;

   //bi-directional many-to-one association to CCIStaffRolesDefaultResourcePermission
   @OneToMany(mappedBy="departmentResourceGroup")
   private List<CCIStaffRolesDefaultResourcePermission> ccistaffRolesDefaultResourcePermissions;

   //bi-directional many-to-one association to CCIStaffUsersResourcePermission
   @OneToMany(mappedBy="departmentResourceGroup")
   private List<CCIStaffUsersResourcePermission> ccistaffUsersResourcePermissions;

   //bi-directional many-to-one association to LookupDepartment
   @ManyToOne
   @JoinColumn(name="departmentId", nullable=false)
   private LookupDepartment lookupDepartment;

   //bi-directional many-to-one association to ResourcePermission
   @OneToMany(mappedBy="departmentResourceGroup")
   private List<ResourcePermission> resourcePermissions;

   public DepartmentResourceGroup() {
   }

   public Integer getDepartmentResourceGroupId() {
      return this.departmentResourceGroupId;
   }

   public void setDepartmentResourceGroupId(Integer departmentResourceGroupId) {
      this.departmentResourceGroupId = departmentResourceGroupId;
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

   public String getResourceGroupName() {
      return this.resourceGroupName;
   }

   public void setResourceGroupName(String resourceGroupName) {
      this.resourceGroupName = resourceGroupName;
   }

   public List<CCIStaffRolesDefaultResourcePermission> getCcistaffRolesDefaultResourcePermissions() {
      return this.ccistaffRolesDefaultResourcePermissions;
   }

   public void setCcistaffRolesDefaultResourcePermissions(List<CCIStaffRolesDefaultResourcePermission> ccistaffRolesDefaultResourcePermissions) {
      this.ccistaffRolesDefaultResourcePermissions = ccistaffRolesDefaultResourcePermissions;
   }

   public CCIStaffRolesDefaultResourcePermission addCcistaffRolesDefaultResourcePermission(CCIStaffRolesDefaultResourcePermission ccistaffRolesDefaultResourcePermission) {
      getCcistaffRolesDefaultResourcePermissions().add(ccistaffRolesDefaultResourcePermission);
      ccistaffRolesDefaultResourcePermission.setDepartmentResourceGroup(this);

      return ccistaffRolesDefaultResourcePermission;
   }

   public CCIStaffRolesDefaultResourcePermission removeCcistaffRolesDefaultResourcePermission(CCIStaffRolesDefaultResourcePermission ccistaffRolesDefaultResourcePermission) {
      getCcistaffRolesDefaultResourcePermissions().remove(ccistaffRolesDefaultResourcePermission);
      ccistaffRolesDefaultResourcePermission.setDepartmentResourceGroup(null);

      return ccistaffRolesDefaultResourcePermission;
   }

   public List<CCIStaffUsersResourcePermission> getCcistaffUsersResourcePermissions() {
      return this.ccistaffUsersResourcePermissions;
   }

   public void setCcistaffUsersResourcePermissions(List<CCIStaffUsersResourcePermission> ccistaffUsersResourcePermissions) {
      this.ccistaffUsersResourcePermissions = ccistaffUsersResourcePermissions;
   }

   public CCIStaffUsersResourcePermission addCcistaffUsersResourcePermission(CCIStaffUsersResourcePermission ccistaffUsersResourcePermission) {
      getCcistaffUsersResourcePermissions().add(ccistaffUsersResourcePermission);
      ccistaffUsersResourcePermission.setDepartmentResourceGroup(this);

      return ccistaffUsersResourcePermission;
   }

   public CCIStaffUsersResourcePermission removeCcistaffUsersResourcePermission(CCIStaffUsersResourcePermission ccistaffUsersResourcePermission) {
      getCcistaffUsersResourcePermissions().remove(ccistaffUsersResourcePermission);
      ccistaffUsersResourcePermission.setDepartmentResourceGroup(null);

      return ccistaffUsersResourcePermission;
   }

   public LookupDepartment getLookupDepartment() {
      return this.lookupDepartment;
   }

   public void setLookupDepartment(LookupDepartment lookupDepartment) {
      this.lookupDepartment = lookupDepartment;
   }

   public List<ResourcePermission> getResourcePermissions() {
      return this.resourcePermissions;
   }

   public void setResourcePermissions(List<ResourcePermission> resourcePermissions) {
      this.resourcePermissions = resourcePermissions;
   }

   public ResourcePermission addResourcePermission(ResourcePermission resourcePermission) {
      getResourcePermissions().add(resourcePermission);
      resourcePermission.setDepartmentResourceGroup(this);

      return resourcePermission;
   }

   public ResourcePermission removeResourcePermission(ResourcePermission resourcePermission) {
      getResourcePermissions().remove(resourcePermission);
      resourcePermission.setDepartmentResourceGroup(null);

      return resourcePermission;
   }

}