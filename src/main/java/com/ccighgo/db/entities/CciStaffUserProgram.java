package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ccistaffuserprogram database table.
 * 
 */
@Entity
@Table(name="ccistaffuserprogram")
@NamedQuery(name="CciStaffUserProgram.findAll", query="SELECT c FROM CciStaffUserProgram c")
public class CciStaffUserProgram implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CciStaffUserProgramPK id;

	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to CciStaffUser
	@ManyToOne
	@JoinColumn(name="cciStaffUserID", nullable=false, insertable=false, updatable=false)
	private CciStaffUser ccistaffuser;

	//bi-directional many-to-one association to DepartmentProgram
	@ManyToOne
	@JoinColumn(name="programID", nullable=false, insertable=false, updatable=false)
	private DepartmentProgram departmentprogram;

	public CciStaffUserProgram() {
	}

	public CciStaffUserProgramPK getId() {
		return this.id;
	}

	public void setId(CciStaffUserProgramPK id) {
		this.id = id;
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

	public CciStaffUser getCcistaffuser() {
		return this.ccistaffuser;
	}

	public void setCcistaffuser(CciStaffUser ccistaffuser) {
		this.ccistaffuser = ccistaffuser;
	}

	public DepartmentProgram getDepartmentprogram() {
		return this.departmentprogram;
	}

	public void setDepartmentprogram(DepartmentProgram departmentprogram) {
		this.departmentprogram = departmentprogram;
	}

}