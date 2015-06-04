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
	private int cciStaffUserID;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int programID;

	public CCIStaffUserProgramPK() {
	}
	public int getCciStaffUserID() {
		return this.cciStaffUserID;
	}
	public void setCciStaffUserID(int cciStaffUserID) {
		this.cciStaffUserID = cciStaffUserID;
	}
	public int getProgramID() {
		return this.programID;
	}
	public void setProgramID(int programID) {
		this.programID = programID;
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
			(this.cciStaffUserID == castOther.cciStaffUserID)
			&& (this.programID == castOther.programID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cciStaffUserID;
		hash = hash * prime + this.programID;
		
		return hash;
	}
}