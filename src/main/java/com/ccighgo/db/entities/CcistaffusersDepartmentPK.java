package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ccistaffusers_departments database table.
 * 
 */
@Embeddable
public class CcistaffusersDepartmentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int cciStaffUserId;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int departmentID;

	public CcistaffusersDepartmentPK() {
	}
	public int getCciStaffUserId() {
		return this.cciStaffUserId;
	}
	public void setCciStaffUserId(int cciStaffUserId) {
		this.cciStaffUserId = cciStaffUserId;
	}
	public int getDepartmentID() {
		return this.departmentID;
	}
	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CcistaffusersDepartmentPK)) {
			return false;
		}
		CcistaffusersDepartmentPK castOther = (CcistaffusersDepartmentPK)other;
		return 
			(this.cciStaffUserId == castOther.cciStaffUserId)
			&& (this.departmentID == castOther.departmentID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cciStaffUserId;
		hash = hash * prime + this.departmentID;
		
		return hash;
	}
}