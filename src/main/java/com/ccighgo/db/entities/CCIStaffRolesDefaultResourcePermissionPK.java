package com.ccighgo.db.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the CCIStaffRolesDefaultResourcePermissions database table.
 * 
 */
@Embeddable
public class CCIStaffRolesDefaultResourcePermissionPK implements Serializable {
   // default serial version id, required for serializable classes.
   private static final long serialVersionUID = 1L;

   @Column(insertable = false, updatable = false, unique = true, nullable = false)
   private Integer cciStaffRolesDepartmentId;

   @Column(insertable = false, updatable = false, unique = true, nullable = false)
   private Integer resourcePermissionId;

   public CCIStaffRolesDefaultResourcePermissionPK() {
   }

   public Integer getCciStaffRolesDepartmentId() {
      if (this.cciStaffRolesDepartmentId != null)
         return this.cciStaffRolesDepartmentId;
      return 0;
   }

   public void setCciStaffRolesDepartmentId(Integer cciStaffRolesDepartmentId) {
      this.cciStaffRolesDepartmentId = cciStaffRolesDepartmentId;
   }

   public Integer getResourcePermissionId() {
      if (this.resourcePermissionId != null)
         return this.resourcePermissionId;
      return 0;
   }

   public void setResourcePermissionId(Integer resourcePermissionId) {
      this.resourcePermissionId = resourcePermissionId;
   }

   public boolean equals(Object other) {
      if (this == other) {
         return true;
      }
      if (!(other instanceof CCIStaffRolesDefaultResourcePermissionPK)) {
         return false;
      }
      CCIStaffRolesDefaultResourcePermissionPK castOther = (CCIStaffRolesDefaultResourcePermissionPK) other;
      return (this.cciStaffRolesDepartmentId == castOther.cciStaffRolesDepartmentId) && (this.resourcePermissionId == castOther.resourcePermissionId);
   }

   public int hashCode() {
      final Integer prime = 31;
      Integer hash = 17;
      hash = hash * prime + this.cciStaffRolesDepartmentId;
      hash = hash * prime + this.resourcePermissionId;

      return hash;
   }
}