package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cci_permissions_options database table.
 * 
 */
@Entity
@Table(name="cci_permissions_options")
@NamedQuery(name="CciPermissionsOption.findAll", query="SELECT c FROM CciPermissionsOption c")
public class CciPermissionsOption implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=1)
	private String isActive;

	@Column(nullable=false, length=50)
	private String permissionOption;

	//bi-directional many-to-many association to CciDepartmentalProcess
	@ManyToMany
	@JoinTable(
		name="cci_process_permissions"
		, joinColumns={
			@JoinColumn(name="permissionsOptionsID", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="processID", nullable=false)
			}
		)
	private List<CciDepartmentalProcess> cciDepartmentalProcesses;

	//bi-directional many-to-one association to CciProcessPermission
	@OneToMany(mappedBy="cciPermissionsOption")
	private List<CciProcessPermission> cciProcessPermissions;

	//bi-directional many-to-one association to CciRoleDefaultPermission
	@OneToMany(mappedBy="cciPermissionsOption")
	private List<CciRoleDefaultPermission> cciRoleDefaultPermissions;

	//bi-directional many-to-one association to CciStaffProcessPermission
	@OneToMany(mappedBy="cciPermissionsOption")
	private List<CciStaffProcessPermission> cciStaffProcessPermissions;

	public CciPermissionsOption() {
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

	public String getPermissionOption() {
		return this.permissionOption;
	}

	public void setPermissionOption(String permissionOption) {
		this.permissionOption = permissionOption;
	}

	public List<CciDepartmentalProcess> getCciDepartmentalProcesses() {
		return this.cciDepartmentalProcesses;
	}

	public void setCciDepartmentalProcesses(List<CciDepartmentalProcess> cciDepartmentalProcesses) {
		this.cciDepartmentalProcesses = cciDepartmentalProcesses;
	}

	public List<CciProcessPermission> getCciProcessPermissions() {
		return this.cciProcessPermissions;
	}

	public void setCciProcessPermissions(List<CciProcessPermission> cciProcessPermissions) {
		this.cciProcessPermissions = cciProcessPermissions;
	}

	public CciProcessPermission addCciProcessPermission(CciProcessPermission cciProcessPermission) {
		getCciProcessPermissions().add(cciProcessPermission);
		cciProcessPermission.setCciPermissionsOption(this);

		return cciProcessPermission;
	}

	public CciProcessPermission removeCciProcessPermission(CciProcessPermission cciProcessPermission) {
		getCciProcessPermissions().remove(cciProcessPermission);
		cciProcessPermission.setCciPermissionsOption(null);

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
		cciRoleDefaultPermission.setCciPermissionsOption(this);

		return cciRoleDefaultPermission;
	}

	public CciRoleDefaultPermission removeCciRoleDefaultPermission(CciRoleDefaultPermission cciRoleDefaultPermission) {
		getCciRoleDefaultPermissions().remove(cciRoleDefaultPermission);
		cciRoleDefaultPermission.setCciPermissionsOption(null);

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
		cciStaffProcessPermission.setCciPermissionsOption(this);

		return cciStaffProcessPermission;
	}

	public CciStaffProcessPermission removeCciStaffProcessPermission(CciStaffProcessPermission cciStaffProcessPermission) {
		getCciStaffProcessPermissions().remove(cciStaffProcessPermission);
		cciStaffProcessPermission.setCciPermissionsOption(null);

		return cciStaffProcessPermission;
	}

}