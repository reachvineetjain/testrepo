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

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private Integer cciStaffUserId;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private Integer cciStaffRoleId;

	public CCIStaffUsersCCIStaffRolePK() {
	}
	public Integer getCciStaffUserId() {
		return this.cciStaffUserId;
	}
	public void setCciStaffUserId(Integer cciStaffUserId) {
		this.cciStaffUserId = cciStaffUserId;
	}
	public Integer getCciStaffRoleId() {
		return this.cciStaffRoleId;
	}
	public void setCciStaffRoleId(Integer cciStaffRoleId) {
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