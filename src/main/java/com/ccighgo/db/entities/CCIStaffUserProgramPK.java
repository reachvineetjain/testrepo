package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CCIStaffUserProgram database table.
 * 
 */
@Embeddable
public class CCIStaffUserProgramPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int cciStaffUserId;

	/*@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int departmentProgramId;
*/
	@Column(insertable=false, updatable=false, unique=true, nullable=false)
   private int lookupDepartmentProgramId;

	
	public CCIStaffUserProgramPK() {
	}
	public int getCciStaffUserId() {
		return this.cciStaffUserId;
	}
	public void setCciStaffUserId(int cciStaffUserId) {
		this.cciStaffUserId = cciStaffUserId;
	}
	/*public int getDepartmentProgramId() {
		return this.departmentProgramId;
	}
	public void setDepartmentProgramId(int departmentProgramId) {
		this.departmentProgramId = departmentProgramId;
	}*/

	/*public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CCIStaffUserProgramPK)) {
			return false;
		}
		CCIStaffUserProgramPK castOther = (CCIStaffUserProgramPK)other;
		return 
			(this.cciStaffUserId == castOther.cciStaffUserId)
			&& (this.departmentProgramId == castOther.departmentProgramId);
	}*/
	
	public boolean equals(Object other) {
      if (this == other) {
         return true;
      }
      if (!(other instanceof CCIStaffUserProgramPK)) {
         return false;
      }
      CCIStaffUserProgramPK castOther = (CCIStaffUserProgramPK)other;
      return 
         (this.cciStaffUserId == castOther.cciStaffUserId)
         && (this.lookupDepartmentProgramId == castOther.lookupDepartmentProgramId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cciStaffUserId;
	//	hash = hash * prime + this.departmentProgramId;
		hash = hash * prime + this.lookupDepartmentProgramId;
		
		return hash;
	}
   public int getLookupDepartmentProgramId() {
      return lookupDepartmentProgramId;
   }
   public void setLookupDepartmentProgramId(int lookupDepartmentProgramId) {
      this.lookupDepartmentProgramId = lookupDepartmentProgramId;
   }
}