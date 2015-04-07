package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cci_staff_process_permissions database table.
 * 
 */
@Entity
@Table(name="cci_staff_process_permissions")
@NamedQuery(name="CciStaffProcessPermission.findAll", query="SELECT c FROM CciStaffProcessPermission c")
public class CciStaffProcessPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	//bi-directional many-to-one association to CciDepartmentalProcess
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="departmentalProcessesID", nullable=false)
	private CciDepartmentalProcess cciDepartmentalProcess;

	//bi-directional many-to-one association to CciPermissionsOption
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="permissionOptionID", nullable=false)
	private CciPermissionsOption cciPermissionsOption;

	//bi-directional many-to-one association to CciStaff
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="staffID", nullable=false)
	private CciStaff cciStaff;

	public CciStaffProcessPermission() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CciDepartmentalProcess getCciDepartmentalProcess() {
		return this.cciDepartmentalProcess;
	}

	public void setCciDepartmentalProcess(CciDepartmentalProcess cciDepartmentalProcess) {
		this.cciDepartmentalProcess = cciDepartmentalProcess;
	}

	public CciPermissionsOption getCciPermissionsOption() {
		return this.cciPermissionsOption;
	}

	public void setCciPermissionsOption(CciPermissionsOption cciPermissionsOption) {
		this.cciPermissionsOption = cciPermissionsOption;
	}

	public CciStaff getCciStaff() {
		return this.cciStaff;
	}

	public void setCciStaff(CciStaff cciStaff) {
		this.cciStaff = cciStaff;
	}

}