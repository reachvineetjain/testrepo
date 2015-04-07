package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cci_permissions_process_groups database table.
 * 
 */
@Entity
@Table(name="cci_permissions_process_groups")
@NamedQuery(name="CciPermissionsProcessGroup.findAll", query="SELECT c FROM CciPermissionsProcessGroup c")
public class CciPermissionsProcessGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=50)
	private String processGroupName;

	//bi-directional many-to-one association to CciDepartmentalProcess
	@OneToMany(mappedBy="cciPermissionsProcessGroup")
	private List<CciDepartmentalProcess> cciDepartmentalProcesses;

	public CciPermissionsProcessGroup() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProcessGroupName() {
		return this.processGroupName;
	}

	public void setProcessGroupName(String processGroupName) {
		this.processGroupName = processGroupName;
	}

	public List<CciDepartmentalProcess> getCciDepartmentalProcesses() {
		return this.cciDepartmentalProcesses;
	}

	public void setCciDepartmentalProcesses(List<CciDepartmentalProcess> cciDepartmentalProcesses) {
		this.cciDepartmentalProcesses = cciDepartmentalProcesses;
	}

	public CciDepartmentalProcess addCciDepartmentalProcess(CciDepartmentalProcess cciDepartmentalProcess) {
		getCciDepartmentalProcesses().add(cciDepartmentalProcess);
		cciDepartmentalProcess.setCciPermissionsProcessGroup(this);

		return cciDepartmentalProcess;
	}

	public CciDepartmentalProcess removeCciDepartmentalProcess(CciDepartmentalProcess cciDepartmentalProcess) {
		getCciDepartmentalProcesses().remove(cciDepartmentalProcess);
		cciDepartmentalProcess.setCciPermissionsProcessGroup(null);

		return cciDepartmentalProcess;
	}

}