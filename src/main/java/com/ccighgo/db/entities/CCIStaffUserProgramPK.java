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
	private Integer cciStaffUserId;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private Integer departmentProgramId;

	public CCIStaffUserProgramPK() {
	}
	public Integer getCciStaffUserId() {
		return this.cciStaffUserId;
	}
	public void setCciStaffUserId(Integer cciStaffUserId) {
		this.cciStaffUserId = cciStaffUserId;
	}
	public Integer getDepartmentProgramId() {
		return this.departmentProgramId;
	}
	public void setDepartmentProgramId(Integer departmentProgramId) {
		this.departmentProgramId = departmentProgramId;
	}

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
			&& (this.departmentProgramId == castOther.departmentProgramId);
	}

	public int hashCode() {
		final Integer prime = 31;
		Integer hash = 17;
		hash = hash * prime + this.cciStaffUserId;
		hash = hash * prime + this.departmentProgramId;
		
		return hash;
	}
}