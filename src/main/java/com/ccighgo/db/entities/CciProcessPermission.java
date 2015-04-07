package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cci_process_permissions database table.
 * 
 */
@Entity
@Table(name="cci_process_permissions")
@NamedQuery(name="CciProcessPermission.findAll", query="SELECT c FROM CciProcessPermission c")
public class CciProcessPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	//bi-directional many-to-one association to CciDepartmentalProcess
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="processID", nullable=false)
	private CciDepartmentalProcess cciDepartmentalProcess;

	//bi-directional many-to-one association to CciPermissionsOption
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="permissionsOptionsID", nullable=false)
	private CciPermissionsOption cciPermissionsOption;

	public CciProcessPermission() {
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

}