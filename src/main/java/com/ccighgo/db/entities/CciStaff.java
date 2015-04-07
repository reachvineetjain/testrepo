package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cci_staff database table.
 * 
 */
@Entity
@Table(name="cci_staff")
@NamedQuery(name="CciStaff.findAll", query="SELECT c FROM CciStaff c")
public class CciStaff implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=50)
	private String firstName;

	@Column(length=50)
	private String lastName;

	//bi-directional many-to-one association to CciStaffProcessPermission
	@OneToMany(mappedBy="cciStaff")
	private List<CciStaffProcessPermission> cciStaffProcessPermissions;

	//bi-directional many-to-many association to CciStaffRole
	@ManyToMany(mappedBy="cciStaffs")
	private List<CciStaffRole> cciStaffRoles;

	//bi-directional many-to-one association to CciStaffStaffRolesMap
	@OneToMany(mappedBy="cciStaff")
	private List<CciStaffStaffRolesMap> cciStaffStaffRolesMaps;

	public CciStaff() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<CciStaffProcessPermission> getCciStaffProcessPermissions() {
		return this.cciStaffProcessPermissions;
	}

	public void setCciStaffProcessPermissions(List<CciStaffProcessPermission> cciStaffProcessPermissions) {
		this.cciStaffProcessPermissions = cciStaffProcessPermissions;
	}

	public CciStaffProcessPermission addCciStaffProcessPermission(CciStaffProcessPermission cciStaffProcessPermission) {
		getCciStaffProcessPermissions().add(cciStaffProcessPermission);
		cciStaffProcessPermission.setCciStaff(this);

		return cciStaffProcessPermission;
	}

	public CciStaffProcessPermission removeCciStaffProcessPermission(CciStaffProcessPermission cciStaffProcessPermission) {
		getCciStaffProcessPermissions().remove(cciStaffProcessPermission);
		cciStaffProcessPermission.setCciStaff(null);

		return cciStaffProcessPermission;
	}

	public List<CciStaffRole> getCciStaffRoles() {
		return this.cciStaffRoles;
	}

	public void setCciStaffRoles(List<CciStaffRole> cciStaffRoles) {
		this.cciStaffRoles = cciStaffRoles;
	}

	public List<CciStaffStaffRolesMap> getCciStaffStaffRolesMaps() {
		return this.cciStaffStaffRolesMaps;
	}

	public void setCciStaffStaffRolesMaps(List<CciStaffStaffRolesMap> cciStaffStaffRolesMaps) {
		this.cciStaffStaffRolesMaps = cciStaffStaffRolesMaps;
	}

	public CciStaffStaffRolesMap addCciStaffStaffRolesMap(CciStaffStaffRolesMap cciStaffStaffRolesMap) {
		getCciStaffStaffRolesMaps().add(cciStaffStaffRolesMap);
		cciStaffStaffRolesMap.setCciStaff(this);

		return cciStaffStaffRolesMap;
	}

	public CciStaffStaffRolesMap removeCciStaffStaffRolesMap(CciStaffStaffRolesMap cciStaffStaffRolesMap) {
		getCciStaffStaffRolesMaps().remove(cciStaffStaffRolesMap);
		cciStaffStaffRolesMap.setCciStaff(null);

		return cciStaffStaffRolesMap;
	}

}