package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cci_staff_staff_roles_map database table.
 * 
 */
@Entity
@Table(name="cci_staff_staff_roles_map")
@NamedQuery(name="CciStaffStaffRolesMap.findAll", query="SELECT c FROM CciStaffStaffRolesMap c")
public class CciStaffStaffRolesMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	//bi-directional many-to-one association to CciStaff
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="staffID", nullable=false)
	private CciStaff cciStaff;

	//bi-directional many-to-one association to CciStaffRole
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="staffRolesID", nullable=false)
	private CciStaffRole cciStaffRole;

	public CciStaffStaffRolesMap() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CciStaff getCciStaff() {
		return this.cciStaff;
	}

	public void setCciStaff(CciStaff cciStaff) {
		this.cciStaff = cciStaff;
	}

	public CciStaffRole getCciStaffRole() {
		return this.cciStaffRole;
	}

	public void setCciStaffRole(CciStaffRole cciStaffRole) {
		this.cciStaffRole = cciStaffRole;
	}

}