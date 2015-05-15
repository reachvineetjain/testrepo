package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the ccistaffroles_departments database table.
 * 
 */
@Entity
@Table(name="ccistaffroles_departments")
@NamedQuery(name="CcistaffrolesDepartment.findAll", query="SELECT c FROM CcistaffrolesDepartment c")
public class CcistaffrolesDepartment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false)
	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to CcistaffrolesDefaultresourcepermission
	@OneToMany(mappedBy="ccistaffrolesDepartment")
	private List<CcistaffrolesDefaultresourcepermission> ccistaffrolesDefaultresourcepermissions;

	//bi-directional many-to-one association to Ccistaffrole
	@ManyToOne
	@JoinColumn(name="cciStaffRoleId", nullable=false)
	private Ccistaffrole ccistaffrole;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="departmentId", nullable=false)
	private Department department;

	public CcistaffrolesDepartment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
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

	public List<CcistaffrolesDefaultresourcepermission> getCcistaffrolesDefaultresourcepermissions() {
		return this.ccistaffrolesDefaultresourcepermissions;
	}

	public void setCcistaffrolesDefaultresourcepermissions(List<CcistaffrolesDefaultresourcepermission> ccistaffrolesDefaultresourcepermissions) {
		this.ccistaffrolesDefaultresourcepermissions = ccistaffrolesDefaultresourcepermissions;
	}

	public CcistaffrolesDefaultresourcepermission addCcistaffrolesDefaultresourcepermission(CcistaffrolesDefaultresourcepermission ccistaffrolesDefaultresourcepermission) {
		getCcistaffrolesDefaultresourcepermissions().add(ccistaffrolesDefaultresourcepermission);
		ccistaffrolesDefaultresourcepermission.setCcistaffrolesDepartment(this);

		return ccistaffrolesDefaultresourcepermission;
	}

	public CcistaffrolesDefaultresourcepermission removeCcistaffrolesDefaultresourcepermission(CcistaffrolesDefaultresourcepermission ccistaffrolesDefaultresourcepermission) {
		getCcistaffrolesDefaultresourcepermissions().remove(ccistaffrolesDefaultresourcepermission);
		ccistaffrolesDefaultresourcepermission.setCcistaffrolesDepartment(null);

		return ccistaffrolesDefaultresourcepermission;
	}

	public Ccistaffrole getCcistaffrole() {
		return this.ccistaffrole;
	}

	public void setCcistaffrole(Ccistaffrole ccistaffrole) {
		this.ccistaffrole = ccistaffrole;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}