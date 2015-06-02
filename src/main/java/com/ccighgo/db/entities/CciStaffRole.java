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
@NamedQuery(name="CciStaffRole.findAll", query="SELECT c FROM CciStaffRole c")
public class CciStaffRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int cciStaffRoleID;

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

	//bi-directional many-to-one association to CciStaffRolesDepartment
	@OneToMany(mappedBy="ccistaffrole")
	private List<CciStaffRolesDepartment> ccistaffrolesdepartments;

	//bi-directional many-to-one association to CciStaffUsersCciStaffRole
	@OneToMany(mappedBy="ccistaffrole")
	private List<CciStaffUsersCciStaffRole> ccistaffusersccistaffroles;

	public CciStaffRole() {
	}

	public int getCciStaffRoleID() {
		return this.cciStaffRoleID;
	}

	public void setCciStaffRoleID(int cciStaffRoleID) {
		this.cciStaffRoleID = cciStaffRoleID;
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

	public List<CciStaffRolesDepartment> getCcistaffrolesdepartments() {
		return this.ccistaffrolesdepartments;
	}

	public void setCcistaffrolesdepartments(List<CciStaffRolesDepartment> ccistaffrolesdepartments) {
		this.ccistaffrolesdepartments = ccistaffrolesdepartments;
	}

	public CciStaffRolesDepartment addCcistaffrolesdepartment(CciStaffRolesDepartment ccistaffrolesdepartment) {
		getCcistaffrolesdepartments().add(ccistaffrolesdepartment);
		ccistaffrolesdepartment.setCcistaffrole(this);

		return ccistaffrolesdepartment;
	}

	public CciStaffRolesDepartment removeCcistaffrolesdepartment(CciStaffRolesDepartment ccistaffrolesdepartment) {
		getCcistaffrolesdepartments().remove(ccistaffrolesdepartment);
		ccistaffrolesdepartment.setCcistaffrole(null);

		return ccistaffrolesdepartment;
	}

	public List<CciStaffUsersCciStaffRole> getCcistaffusersccistaffroles() {
		return this.ccistaffusersccistaffroles;
	}

	public void setCcistaffusersccistaffroles(List<CciStaffUsersCciStaffRole> ccistaffusersccistaffroles) {
		this.ccistaffusersccistaffroles = ccistaffusersccistaffroles;
	}

	public CciStaffUsersCciStaffRole addCcistaffusersccistaffrole(CciStaffUsersCciStaffRole ccistaffusersccistaffrole) {
		getCcistaffusersccistaffroles().add(ccistaffusersccistaffrole);
		ccistaffusersccistaffrole.setCcistaffrole(this);

		return ccistaffusersccistaffrole;
	}

	public CciStaffUsersCciStaffRole removeCcistaffusersccistaffrole(CciStaffUsersCciStaffRole ccistaffusersccistaffrole) {
		getCcistaffusersccistaffroles().remove(ccistaffusersccistaffrole);
		ccistaffusersccistaffrole.setCcistaffrole(null);

		return ccistaffusersccistaffrole;
	}

}