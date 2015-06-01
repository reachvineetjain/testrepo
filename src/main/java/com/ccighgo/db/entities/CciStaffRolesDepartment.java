package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the ccistaffrolesdepartments database table.
 * 
 */
@Entity
@Table(name="ccistaffrolesdepartments")
@NamedQuery(name="CciStaffRolesDepartment.findAll", query="SELECT c FROM CciStaffRolesDepartment c")
public class CciStaffRolesDepartment implements Serializable {
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

	//bi-directional many-to-one association to CciStaffRolesDefaultResourcePermission
	@OneToMany(mappedBy="ccistaffrolesdepartment")
	private List<CciStaffRolesDefaultResourcePermission> ccistaffrolesdefaultresourcepermissions;

	//bi-directional many-to-one association to CciStaffRole
	@ManyToOne
	@JoinColumn(name="cciStaffRoleId", nullable=false)
	private CciStaffRole ccistaffrole;

	//bi-directional many-to-one association to Departments
	@ManyToOne
	@JoinColumn(name="departmentId", nullable=false)
	private Departments department;

	public CciStaffRolesDepartment() {
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

	public List<CciStaffRolesDefaultResourcePermission> getCcistaffrolesdefaultresourcepermissions() {
		return this.ccistaffrolesdefaultresourcepermissions;
	}

	public void setCcistaffrolesdefaultresourcepermissions(List<CciStaffRolesDefaultResourcePermission> ccistaffrolesdefaultresourcepermissions) {
		this.ccistaffrolesdefaultresourcepermissions = ccistaffrolesdefaultresourcepermissions;
	}

	public CciStaffRolesDefaultResourcePermission addCcistaffrolesdefaultresourcepermission(CciStaffRolesDefaultResourcePermission ccistaffrolesdefaultresourcepermission) {
		getCcistaffrolesdefaultresourcepermissions().add(ccistaffrolesdefaultresourcepermission);
		ccistaffrolesdefaultresourcepermission.setCcistaffrolesdepartment(this);

		return ccistaffrolesdefaultresourcepermission;
	}

	public CciStaffRolesDefaultResourcePermission removeCcistaffrolesdefaultresourcepermission(CciStaffRolesDefaultResourcePermission ccistaffrolesdefaultresourcepermission) {
		getCcistaffrolesdefaultresourcepermissions().remove(ccistaffrolesdefaultresourcepermission);
		ccistaffrolesdefaultresourcepermission.setCcistaffrolesdepartment(null);

		return ccistaffrolesdefaultresourcepermission;
	}

	public CciStaffRole getCcistaffrole() {
		return this.ccistaffrole;
	}

	public void setCcistaffrole(CciStaffRole ccistaffrole) {
		this.ccistaffrole = ccistaffrole;
	}

	public Departments getDepartment() {
		return this.department;
	}

	public void setDepartment(Departments department) {
		this.department = department;
	}

}