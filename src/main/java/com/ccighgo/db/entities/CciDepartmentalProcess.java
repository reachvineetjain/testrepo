package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cci_departmental_processes database table.
 * 
 */
@Entity
@Table(name="cci_departmental_processes")
@NamedQuery(name="CciDepartmentalProcess.findAll", query="SELECT c FROM CciDepartmentalProcess c")
public class CciDepartmentalProcess implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=1)
	private String isActive;

	@Column(length=50)
	private String processName;

	//bi-directional many-to-one association to CciDepartment
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="departmentID", nullable=false)
	private CciDepartments cciDepartment;

	//bi-directional many-to-one association to CciPermissionsProcessGroup
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="processGroupID", nullable=false)
	private CciPermissionsProcessGroup cciPermissionsProcessGroup;

	//bi-directional many-to-many association to CciPermissionsOption
	@ManyToMany(mappedBy="cciDepartmentalProcesses")
	private List<CciPermissionsOption> cciPermissionsOptions;

	//bi-directional many-to-one association to CciProcessPermission
	@OneToMany(mappedBy="cciDepartmentalProcess")
	private List<CciProcessPermission> cciProcessPermissions;

	//bi-directional many-to-one association to CciRoleDefaultPermission
	@OneToMany(mappedBy="cciDepartmentalProcess")
	private List<CciRoleDefaultPermission> cciRoleDefaultPermissions;

	//bi-directional many-to-one association to CciStaffProcessPermission
	@OneToMany(mappedBy="cciDepartmentalProcess")
	private List<CciStaffProcessPermission> cciStaffProcessPermissions;

	public CciDepartmentalProcess() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getProcessName() {
		return this.processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public CciDepartments getCciDepartment() {
		return this.cciDepartment;
	}

	public void setCciDepartment(CciDepartments cciDepartment) {
		this.cciDepartment = cciDepartment;
	}

	public CciPermissionsProcessGroup getCciPermissionsProcessGroup() {
		return this.cciPermissionsProcessGroup;
	}

	public void setCciPermissionsProcessGroup(CciPermissionsProcessGroup cciPermissionsProcessGroup) {
		this.cciPermissionsProcessGroup = cciPermissionsProcessGroup;
	}

	public List<CciPermissionsOption> getCciPermissionsOptions() {
		return this.cciPermissionsOptions;
	}

	public void setCciPermissionsOptions(List<CciPermissionsOption> cciPermissionsOptions) {
		this.cciPermissionsOptions = cciPermissionsOptions;
	}

	public List<CciProcessPermission> getCciProcessPermissions() {
		return this.cciProcessPermissions;
	}

	public void setCciProcessPermissions(List<CciProcessPermission> cciProcessPermissions) {
		this.cciProcessPermissions = cciProcessPermissions;
	}

	public CciProcessPermission addCciProcessPermission(CciProcessPermission cciProcessPermission) {
		getCciProcessPermissions().add(cciProcessPermission);
		cciProcessPermission.setCciDepartmentalProcess(this);

		return cciProcessPermission;
	}

	public CciProcessPermission removeCciProcessPermission(CciProcessPermission cciProcessPermission) {
		getCciProcessPermissions().remove(cciProcessPermission);
		cciProcessPermission.setCciDepartmentalProcess(null);

		return cciProcessPermission;
	}

	public List<CciRoleDefaultPermission> getCciRoleDefaultPermissions() {
		return this.cciRoleDefaultPermissions;
	}

	public void setCciRoleDefaultPermissions(List<CciRoleDefaultPermission> cciRoleDefaultPermissions) {
		this.cciRoleDefaultPermissions = cciRoleDefaultPermissions;
	}

	public CciRoleDefaultPermission addCciRoleDefaultPermission(CciRoleDefaultPermission cciRoleDefaultPermission) {
		getCciRoleDefaultPermissions().add(cciRoleDefaultPermission);
		cciRoleDefaultPermission.setCciDepartmentalProcess(this);

		return cciRoleDefaultPermission;
	}

	public CciRoleDefaultPermission removeCciRoleDefaultPermission(CciRoleDefaultPermission cciRoleDefaultPermission) {
		getCciRoleDefaultPermissions().remove(cciRoleDefaultPermission);
		cciRoleDefaultPermission.setCciDepartmentalProcess(null);

		return cciRoleDefaultPermission;
	}

	public List<CciStaffProcessPermission> getCciStaffProcessPermissions() {
		return this.cciStaffProcessPermissions;
	}

	public void setCciStaffProcessPermissions(List<CciStaffProcessPermission> cciStaffProcessPermissions) {
		this.cciStaffProcessPermissions = cciStaffProcessPermissions;
	}

	public CciStaffProcessPermission addCciStaffProcessPermission(CciStaffProcessPermission cciStaffProcessPermission) {
		getCciStaffProcessPermissions().add(cciStaffProcessPermission);
		cciStaffProcessPermission.setCciDepartmentalProcess(this);

		return cciStaffProcessPermission;
	}

	public CciStaffProcessPermission removeCciStaffProcessPermission(CciStaffProcessPermission cciStaffProcessPermission) {
		getCciStaffProcessPermissions().remove(cciStaffProcessPermission);
		cciStaffProcessPermission.setCciDepartmentalProcess(null);

		return cciStaffProcessPermission;
	}

}