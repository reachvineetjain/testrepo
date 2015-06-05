package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the CCIStaffUserProgram database table.
 * 
 */
@Entity
@Table(name="CCIStaffUserProgram")
@NamedQuery(name="CCIStaffUserProgram.findAll", query="SELECT c FROM CCIStaffUserProgram c")
public class CCIStaffUserProgram implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int cciStaffUserProgramId;

	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to CCIStaffUser
	@ManyToOne
	@JoinColumn(name="cciStaffUserId", nullable=false)
	private CCIStaffUser ccistaffUser;

	//bi-directional many-to-one association to DepartmentProgram
	@ManyToOne
	@JoinColumn(name="departmentProgramId", nullable=false)
	private DepartmentProgram departmentProgram;

	public CCIStaffUserProgram() {
	}

	public int getCciStaffUserProgramId() {
		return this.cciStaffUserProgramId;
	}

	public void setCciStaffUserProgramId(int cciStaffUserProgramId) {
		this.cciStaffUserProgramId = cciStaffUserProgramId;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public int getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public CCIStaffUser getCcistaffUser() {
		return this.ccistaffUser;
	}

	public void setCcistaffUser(CCIStaffUser ccistaffUser) {
		this.ccistaffUser = ccistaffUser;
	}

	public DepartmentProgram getDepartmentProgram() {
		return this.departmentProgram;
	}

	public void setDepartmentProgram(DepartmentProgram departmentProgram) {
		this.departmentProgram = departmentProgram;
	}

}