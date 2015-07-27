package com.ccighgo.db.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * The persistent class for the CCIStaffRoles database table.
 * 
 */
@Entity
@Table(name = "CCIStaffRoles")
@NamedQuery(name = "CCIStaffRole.findAll", query = "SELECT c FROM CCIStaffRole c")
public class CCIStaffRole implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer cciStaffRoleId;

   @Column(nullable = false, length = 50)
   private String cciStaffRoleName;

   @Column(nullable = false)
   private Integer createdBy;

   private Timestamp createdOn;

   private Integer hierarchy;

   @Column(nullable = false)
   private Integer modifiedBy;

   @Column(nullable = false)
   private Timestamp modifiedOn;

   // bi-directional many-to-one association to CCIStaffRolesDepartment
   @OneToMany(mappedBy = "ccistaffRole", fetch = FetchType.EAGER)
   @Fetch(value = FetchMode.SUBSELECT)
   private List<CCIStaffRolesDepartment> ccistaffRolesDepartments;

   // bi-directional many-to-one association to CCIStaffUsersCCIStaffRole
   @OneToMany(mappedBy = "ccistaffRole")
   private List<CCIStaffUsersCCIStaffRole> ccistaffUsersCcistaffRoles;

   public CCIStaffRole() {
   }

   public Integer getCciStaffRoleId() {
      if (this.cciStaffRoleId != null)
         return this.cciStaffRoleId;
      return 0;
   }

   public void setCciStaffRoleId(Integer cciStaffRoleId) {
      this.cciStaffRoleId = cciStaffRoleId;
   }

   public String getCciStaffRoleName() {
      return this.cciStaffRoleName;
   }

   public void setCciStaffRoleName(String cciStaffRoleName) {
      this.cciStaffRoleName = cciStaffRoleName;
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

   public Integer getHierarchy() {
      if (this.hierarchy != null)
         return this.hierarchy;
      return 0;
   }

   public void setHierarchy(Integer hierarchy) {
      this.hierarchy = hierarchy;
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

   public List<CCIStaffRolesDepartment> getCcistaffRolesDepartments() {
      return this.ccistaffRolesDepartments;
   }

   public void setCcistaffRolesDepartments(List<CCIStaffRolesDepartment> ccistaffRolesDepartments) {
      this.ccistaffRolesDepartments = ccistaffRolesDepartments;
   }

   public CCIStaffRolesDepartment addCcistaffRolesDepartment(CCIStaffRolesDepartment ccistaffRolesDepartment) {
      getCcistaffRolesDepartments().add(ccistaffRolesDepartment);
      ccistaffRolesDepartment.setCcistaffRole(this);

      return ccistaffRolesDepartment;
   }

   public CCIStaffRolesDepartment removeCcistaffRolesDepartment(CCIStaffRolesDepartment ccistaffRolesDepartment) {
      getCcistaffRolesDepartments().remove(ccistaffRolesDepartment);
      ccistaffRolesDepartment.setCcistaffRole(null);

      return ccistaffRolesDepartment;
   }

   public List<CCIStaffUsersCCIStaffRole> getCcistaffUsersCcistaffRoles() {
      return this.ccistaffUsersCcistaffRoles;
   }

   public void setCcistaffUsersCcistaffRoles(List<CCIStaffUsersCCIStaffRole> ccistaffUsersCcistaffRoles) {
      this.ccistaffUsersCcistaffRoles = ccistaffUsersCcistaffRoles;
   }

   public CCIStaffUsersCCIStaffRole addCcistaffUsersCcistaffRole(CCIStaffUsersCCIStaffRole ccistaffUsersCcistaffRole) {
      getCcistaffUsersCcistaffRoles().add(ccistaffUsersCcistaffRole);
      ccistaffUsersCcistaffRole.setCcistaffRole(this);

      return ccistaffUsersCcistaffRole;
   }

   public CCIStaffUsersCCIStaffRole removeCcistaffUsersCcistaffRole(CCIStaffUsersCCIStaffRole ccistaffUsersCcistaffRole) {
      getCcistaffUsersCcistaffRoles().remove(ccistaffUsersCcistaffRole);
      ccistaffUsersCcistaffRole.setCcistaffRole(null);

      return ccistaffUsersCcistaffRole;
   }

}