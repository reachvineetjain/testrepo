package com.ccighgo.db.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the CCIStaffUsersResourcePermissions database table.
 * 
 */
@Embeddable
public class CCIStaffUsersResourcePermissionPK implements Serializable {
   // default serial version id, required for serializable classes.
   private static final long serialVersionUID = 1L;

   @Column(insertable = false, updatable = false, unique = true, nullable = false)
   private Integer cciStaffUserId;

   @Column(insertable = false, updatable = false, unique = true, nullable = false)
   private Integer resourcePermissionId;

   public CCIStaffUsersResourcePermissionPK() {
   }

   public Integer getCciStaffUserId() {
      if (this.cciStaffUserId != null)
         return this.cciStaffUserId;
      return 0;
   }

   public void setCciStaffUserId(Integer cciStaffUserId) {
      this.cciStaffUserId = cciStaffUserId;
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
      if (!(other instanceof CCIStaffUsersResourcePermissionPK)) {
         return false;
      }
      CCIStaffUsersResourcePermissionPK castOther = (CCIStaffUsersResourcePermissionPK) other;
      return (this.cciStaffUserId == castOther.cciStaffUserId) && (this.resourcePermissionId == castOther.resourcePermissionId);
   }

   public int hashCode() {
      final Integer prime = 31;
      Integer hash = 17;
      hash = hash * prime + this.cciStaffUserId;
      hash = hash * prime + this.resourcePermissionId;

      return hash;
   }
}