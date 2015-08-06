package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CCIStaffUsersCCIStaffRoles database table.
 * 
 */
@Embeddable
public class CCIStaffUsersCCIStaffRolePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int cciStaffUserId;

	@Column(insertable=false, updatable=false)
	private int cciStaffRoleId;

	public CCIStaffUsersCCIStaffRolePK() {
	}
	public int getCciStaffUserId() {
		return this.cciStaffUserId;
	}
	public void setCciStaffUserId(int cciStaffUserId) {
		this.cciStaffUserId = cciStaffUserId;
	}
	public int getCciStaffRoleId() {
		return this.cciStaffRoleId;
	}
	public void setCciStaffRoleId(int cciStaffRoleId) {
		this.cciStaffRoleId = cciStaffRoleId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CCIStaffUsersCCIStaffRolePK)) {
			return false;
		}
		CCIStaffUsersCCIStaffRolePK castOther = (CCIStaffUsersCCIStaffRolePK)other;
		return 
			(this.cciStaffUserId == castOther.cciStaffUserId)
			&& (this.cciStaffRoleId == castOther.cciStaffRoleId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cciStaffUserId;
		hash = hash * prime + this.cciStaffRoleId;
		
		return hash;
	}
}