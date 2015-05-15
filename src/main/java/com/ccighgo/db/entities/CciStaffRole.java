package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the ccistaffroles database table.
 * 
 */
@Entity
@Table(name="ccistaffroles")
@NamedQuery(name="Ccistaffrole.findAll", query="SELECT c FROM Ccistaffrole c")
public class Ccistaffrole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=50)
	private String cciStaffRole;

	@Column(nullable=false)
	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	private int hierarchy;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to CcistaffrolesDepartment
	@OneToMany(mappedBy="ccistaffrole")
	private List<CcistaffrolesDepartment> ccistaffrolesDepartments;

	//bi-directional many-to-one association to CcistaffusersCcistaffrole
	@OneToMany(mappedBy="ccistaffrole")
	private List<CcistaffusersCcistaffrole> ccistaffusersCcistaffroles;

	public Ccistaffrole() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCciStaffRole() {
		return this.cciStaffRole;
	}

	public void setCciStaffRole(String cciStaffRole) {
		this.cciStaffRole = cciStaffRole;
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

	public int getHierarchy() {
		return this.hierarchy;
	}

	public void setHierarchy(int hierarchy) {
		this.hierarchy = hierarchy;
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

	public List<CcistaffrolesDepartment> getCcistaffrolesDepartments() {
		return this.ccistaffrolesDepartments;
	}

	public void setCcistaffrolesDepartments(List<CcistaffrolesDepartment> ccistaffrolesDepartments) {
		this.ccistaffrolesDepartments = ccistaffrolesDepartments;
	}

	public CcistaffrolesDepartment addCcistaffrolesDepartment(CcistaffrolesDepartment ccistaffrolesDepartment) {
		getCcistaffrolesDepartments().add(ccistaffrolesDepartment);
		ccistaffrolesDepartment.setCcistaffrole(this);

		return ccistaffrolesDepartment;
	}

	public CcistaffrolesDepartment removeCcistaffrolesDepartment(CcistaffrolesDepartment ccistaffrolesDepartment) {
		getCcistaffrolesDepartments().remove(ccistaffrolesDepartment);
		ccistaffrolesDepartment.setCcistaffrole(null);

		return ccistaffrolesDepartment;
	}

	public List<CcistaffusersCcistaffrole> getCcistaffusersCcistaffroles() {
		return this.ccistaffusersCcistaffroles;
	}

	public void setCcistaffusersCcistaffroles(List<CcistaffusersCcistaffrole> ccistaffusersCcistaffroles) {
		this.ccistaffusersCcistaffroles = ccistaffusersCcistaffroles;
	}

	public CcistaffusersCcistaffrole addCcistaffusersCcistaffrole(CcistaffusersCcistaffrole ccistaffusersCcistaffrole) {
		getCcistaffusersCcistaffroles().add(ccistaffusersCcistaffrole);
		ccistaffusersCcistaffrole.setCcistaffrole(this);

		return ccistaffusersCcistaffrole;
	}

	public CcistaffusersCcistaffrole removeCcistaffusersCcistaffrole(CcistaffusersCcistaffrole ccistaffusersCcistaffrole) {
		getCcistaffusersCcistaffroles().remove(ccistaffusersCcistaffrole);
		ccistaffusersCcistaffrole.setCcistaffrole(null);

		return ccistaffusersCcistaffrole;
	}

}