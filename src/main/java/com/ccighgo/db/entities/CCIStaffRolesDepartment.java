package com.ccighgo.db.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the CCIStaffRolesDepartments database table.
 * 
 */
@Entity
@Table(name = "CCIStaffRolesDepartments")
@NamedQuery(name = "CCIStaffRolesDepartment.findAll", query = "SELECT c FROM CCIStaffRolesDepartment c")
public class CCIStaffRolesDepartment implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer cciStaffRolesDepartmentId;

   @Column(nullable = false)
   private Integer createdBy;

   private Timestamp createdOn;

   @Column(nullable = false)
   private Integer modifiedBy;

   @Column(nullable = false)
   private Timestamp modifiedOn;

   // bi-directional many-to-one association to CCIStaffRolesDefaultResourcePermission
   @OneToMany(mappedBy = "ccistaffRolesDepartment")
   private List<CCIStaffRolesDefaultResourcePermission> ccistaffRolesDefaultResourcePermissions;

   // bi-directional many-to-one association to CCIStaffRole
   @ManyToOne
   @JoinColumn(name = "cciStaffRoleId", nullable = false)
   private CCIStaffRole ccistaffRole;

   // bi-directional many-to-one association to LookupDepartment
   @ManyToOne
   @JoinColumn(name = "departmentId", nullable = false)
   private LookupDepartment lookupDepartment;

   public CCIStaffRolesDepartment() {
   }

   public Integer getCciStaffRolesDepartmentId() {
      if (this.cciStaffRolesDepartmentId != null)
         return this.cciStaffRolesDepartmentId;
      return 0;
   }

   public void setCciStaffRolesDepartmentId(Integer cciStaffRolesDepartmentId) {
      this.cciStaffRolesDepartmentId = cciStaffRolesDepartmentId;
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

   public List<CCIStaffRolesDefaultResourcePermission> getCcistaffRolesDefaultResourcePermissions() {
      return this.ccistaffRolesDefaultResourcePermissions;
   }

   public void setCcistaffRolesDefaultResourcePermissions(List<CCIStaffRolesDefaultResourcePermission> ccistaffRolesDefaultResourcePermissions) {
      this.ccistaffRolesDefaultResourcePermissions = ccistaffRolesDefaultResourcePermissions;
   }

   public CCIStaffRolesDefaultResourcePermission addCcistaffRolesDefaultResourcePermission(CCIStaffRolesDefaultResourcePermission ccistaffRolesDefaultResourcePermission) {
      getCcistaffRolesDefaultResourcePermissions().add(ccistaffRolesDefaultResourcePermission);
      ccistaffRolesDefaultResourcePermission.setCcistaffRolesDepartment(this);

      return ccistaffRolesDefaultResourcePermission;
   }

   public CCIStaffRolesDefaultResourcePermission removeCcistaffRolesDefaultResourcePermission(CCIStaffRolesDefaultResourcePermission ccistaffRolesDefaultResourcePermission) {
      getCcistaffRolesDefaultResourcePermissions().remove(ccistaffRolesDefaultResourcePermission);
      ccistaffRolesDefaultResourcePermission.setCcistaffRolesDepartment(null);

      return ccistaffRolesDefaultResourcePermission;
   }

   public CCIStaffRole getCcistaffRole() {
      return this.ccistaffRole;
   }

   public void setCcistaffRole(CCIStaffRole ccistaffRole) {
      this.ccistaffRole = ccistaffRole;
   }

   public LookupDepartment getLookupDepartment() {
      return this.lookupDepartment;
   }

   public void setLookupDepartment(LookupDepartment lookupDepartment) {
      this.lookupDepartment = lookupDepartment;
   }

}