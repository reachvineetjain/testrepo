package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ccistaffusers_departments database table.
 * 
 */
@Entity
@Table(name="ccistaffusers_departments")
@NamedQuery(name="CcistaffusersDepartment.findAll", query="SELECT c FROM CcistaffusersDepartment c")
public class CcistaffusersDepartment implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CcistaffusersDepartmentPK id;

	@Column(nullable=false)
	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to Ccistaffuser
	@ManyToOne
	@JoinColumn(name="cciStaffUserId", nullable=false, insertable=false, updatable=false)
	private Ccistaffuser ccistaffuser;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="departmentID", nullable=false, insertable=false, updatable=false)
	private Department department;

	public CcistaffusersDepartment() {
	}

	public CcistaffusersDepartmentPK getId() {
		return this.id;
	}

	public void setId(CcistaffusersDepartmentPK id) {
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

	public Ccistaffuser getCcistaffuser() {
		return this.ccistaffuser;
	}

	public void setCcistaffuser(Ccistaffuser ccistaffuser) {
		this.ccistaffuser = ccistaffuser;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}