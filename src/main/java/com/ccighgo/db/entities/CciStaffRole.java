package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cci_staff_roles database table.
 * 
 */
@Entity
@Table(name="cci_staff_roles")
@NamedQuery(name="CciStaffRole.findAll", query="SELECT c FROM CciStaffRole c")
public class CciStaffRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	private int departmentID;

	@Column(nullable=false, length=50)
	private String staffRole;

	//bi-directional many-to-one association to CciRoleDefaultPermission
	@OneToMany(mappedBy="cciStaffRole")
	private List<CciRoleDefaultPermission> cciRoleDefaultPermissions;

	//bi-directional many-to-many association to CciStaff
	@ManyToMany
	@JoinTable(
		name="cci_staff_staff_roles_map"
		, joinColumns={
			@JoinColumn(name="staffRolesID", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="staffID", nullable=false)
			}
		)
	private List<CciStaff> cciStaffs;

	//bi-directional many-to-one association to CciStaffStaffRolesMap
	@OneToMany(mappedBy="cciStaffRole")
	private List<CciStaffStaffRolesMap> cciStaffStaffRolesMaps;

	public CciStaffRole() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDepartmentID() {
		return this.departmentID;
	}

	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}

	public String getStaffRole() {
		return this.staffRole;
	}

	public void setStaffRole(String staffRole) {
		this.staffRole = staffRole;
	}

	public List<CciRoleDefaultPermission> getCciRoleDefaultPermissions() {
		return this.cciRoleDefaultPermissions;
	}

	public void setCciRoleDefaultPermissions(List<CciRoleDefaultPermission> cciRoleDefaultPermissions) {
		this.cciRoleDefaultPermissions = cciRoleDefaultPermissions;
	}

	public CciRoleDefaultPermission addCciRoleDefaultPermission(CciRoleDefaultPermission cciRoleDefaultPermission) {
		getCciRoleDefaultPermissions().add(cciRoleDefaultPermission);
		cciRoleDefaultPermission.setCciStaffRole(this);

		return cciRoleDefaultPermission;
	}

	public CciRoleDefaultPermission removeCciRoleDefaultPermission(CciRoleDefaultPermission cciRoleDefaultPermission) {
		getCciRoleDefaultPermissions().remove(cciRoleDefaultPermission);
		cciRoleDefaultPermission.setCciStaffRole(null);

		return cciRoleDefaultPermission;
	}

	public List<CciStaff> getCciStaffs() {
		return this.cciStaffs;
	}

	public void setCciStaffs(List<CciStaff> cciStaffs) {
		this.cciStaffs = cciStaffs;
	}

	public List<CciStaffStaffRolesMap> getCciStaffStaffRolesMaps() {
		return this.cciStaffStaffRolesMaps;
	}

	public void setCciStaffStaffRolesMaps(List<CciStaffStaffRolesMap> cciStaffStaffRolesMaps) {
		this.cciStaffStaffRolesMaps = cciStaffStaffRolesMaps;
	}

	public CciStaffStaffRolesMap addCciStaffStaffRolesMap(CciStaffStaffRolesMap cciStaffStaffRolesMap) {
		getCciStaffStaffRolesMaps().add(cciStaffStaffRolesMap);
		cciStaffStaffRolesMap.setCciStaffRole(this);

		return cciStaffStaffRolesMap;
	}

	public CciStaffStaffRolesMap removeCciStaffStaffRolesMap(CciStaffStaffRolesMap cciStaffStaffRolesMap) {
		getCciStaffStaffRolesMaps().remove(cciStaffStaffRolesMap);
		cciStaffStaffRolesMap.setCciStaffRole(null);

		return cciStaffStaffRolesMap;
	}

}