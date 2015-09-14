package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CCIStaffRolesDefaultResourcePermissions database table.
 * 
 */
@Embeddable
public class CCIStaffRolesDefaultResourcePermissionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private Integer cciStaffRolesDepartmentId;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private Integer resourcePermissionId;

	public CCIStaffRolesDefaultResourcePermissionPK() {
	}
	public Integer getCciStaffRolesDepartmentId() {
		return this.cciStaffRolesDepartmentId;
	}
	public void setCciStaffRolesDepartmentId(Integer cciStaffRolesDepartmentId) {
		this.cciStaffRolesDepartmentId = cciStaffRolesDepartmentId;
	}
	public Integer getResourcePermissionId() {
		return this.resourcePermissionId;
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
		CCIStaffRolesDefaultResourcePermissionPK castOther = (CCIStaffRolesDefaultResourcePermissionPK)other;
		return 
			(this.cciStaffRolesDepartmentId == castOther.cciStaffRolesDepartmentId)
			&& (this.resourcePermissionId == castOther.resourcePermissionId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cciStaffRolesDepartmentId;
		hash = hash * prime + this.resourcePermissionId;
		
		return hash;
	}
}