package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the StateProcess database table.
 * 
 */
@Entity
@Table(name="StateProcess")
@NamedQuery(name="StateProcess.findAll", query="SELECT s FROM StateProcess s")
public class StateProcess implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer stateProcessId;

	@Column(length=50)
	private String stateProcessName;

	@Column(length=50)
	private String workQueue;

	//bi-directional many-to-one association to PartnerWorkQueueType
	@OneToMany(mappedBy="stateProcess")
	private List<PartnerWorkQueueType> partnerWorkQueueTypes;

	//bi-directional many-to-one association to StateType
	@OneToMany(mappedBy="stateProcess")
	private List<StateType> stateTypes;

	//bi-directional many-to-one association to FieldStaffWorkQueueType
	@OneToMany(mappedBy="stateProcess")
	private List<FieldStaffWorkQueueType> fieldStaffWorkQueueTypes;

	public StateProcess() {
	}

	public Integer getStateProcessId() {
		return this.stateProcessId;
	}

	public void setStateProcessId(Integer stateProcessId) {
		this.stateProcessId = stateProcessId;
	}

	public String getStateProcessName() {
		return this.stateProcessName;
	}

	public void setStateProcessName(String stateProcessName) {
		this.stateProcessName = stateProcessName;
	}

	public String getWorkQueue() {
		return this.workQueue;
	}

	public void setWorkQueue(String workQueue) {
		this.workQueue = workQueue;
	}

	public List<PartnerWorkQueueType> getPartnerWorkQueueTypes() {
		return this.partnerWorkQueueTypes;
	}

	public void setPartnerWorkQueueTypes(List<PartnerWorkQueueType> partnerWorkQueueTypes) {
		this.partnerWorkQueueTypes = partnerWorkQueueTypes;
	}

	public PartnerWorkQueueType addPartnerWorkQueueType(PartnerWorkQueueType partnerWorkQueueType) {
		getPartnerWorkQueueTypes().add(partnerWorkQueueType);
		partnerWorkQueueType.setStateProcess(this);

		return partnerWorkQueueType;
	}

	public PartnerWorkQueueType removePartnerWorkQueueType(PartnerWorkQueueType partnerWorkQueueType) {
		getPartnerWorkQueueTypes().remove(partnerWorkQueueType);
		partnerWorkQueueType.setStateProcess(null);

		return partnerWorkQueueType;
	}

	public List<StateType> getStateTypes() {
		return this.stateTypes;
	}

	public void setStateTypes(List<StateType> stateTypes) {
		this.stateTypes = stateTypes;
	}

	public StateType addStateType(StateType stateType) {
		getStateTypes().add(stateType);
		stateType.setStateProcess(this);

		return stateType;
	}

	public StateType removeStateType(StateType stateType) {
		getStateTypes().remove(stateType);
		stateType.setStateProcess(null);

		return stateType;
	}

	public List<FieldStaffWorkQueueType> getFieldStaffWorkQueueTypes() {
		return this.fieldStaffWorkQueueTypes;
	}

	public void setFieldStaffWorkQueueTypes(List<FieldStaffWorkQueueType> fieldStaffWorkQueueTypes) {
		this.fieldStaffWorkQueueTypes = fieldStaffWorkQueueTypes;
	}

	public FieldStaffWorkQueueType addFieldStaffWorkQueueType(FieldStaffWorkQueueType fieldStaffWorkQueueType) {
		getFieldStaffWorkQueueTypes().add(fieldStaffWorkQueueType);
		fieldStaffWorkQueueType.setStateProcess(this);

		return fieldStaffWorkQueueType;
	}

	public FieldStaffWorkQueueType removeFieldStaffWorkQueueType(FieldStaffWorkQueueType fieldStaffWorkQueueType) {
		getFieldStaffWorkQueueTypes().remove(fieldStaffWorkQueueType);
		fieldStaffWorkQueueType.setStateProcess(null);

		return fieldStaffWorkQueueType;
	}

}