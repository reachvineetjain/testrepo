package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the StateTypes database table.
 * 
 */
@Entity
@Table(name="StateTypes")
@NamedQuery(name="StateType.findAll", query="SELECT s FROM StateType s")
public class StateType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer stateTypeId;

	private Byte isLastStep;

	private Byte isVisibleToAdmin;

	private Byte isVisibleToParticipant;

	private Byte isVisibleToPartner;

	@Column(length=45)
	private String stateName;

	private Integer workQueueCategoryId;

	@Column(length=50)
	private String workQueueRoleType;

	private Integer workQueueTypeId;

	//bi-directional many-to-one association to AdminWorkQueue
	@OneToMany(mappedBy="stateType")
	private List<AdminWorkQueue> adminWorkQueues;

	//bi-directional many-to-one association to PartnerWorkQueue
	@OneToMany(mappedBy="stateType")
	private List<PartnerWorkQueue> partnerWorkQueues;

	//bi-directional many-to-one association to StateAction
	@OneToMany(mappedBy="stateType")
	private List<StateAction> stateActions;

	//bi-directional many-to-one association to StateTransition
	@OneToMany(mappedBy="stateType1")
	private List<StateTransition> stateTransitions1;

	//bi-directional many-to-one association to StateTransition
	@OneToMany(mappedBy="stateType2")
	private List<StateTransition> stateTransitions2;

	//bi-directional many-to-one association to StateTypeResourcePermission
	@OneToMany(mappedBy="stateType")
	private List<StateTypeResourcePermission> stateTypeResourcePermissions;

	//bi-directional many-to-one association to StateProcess
	@ManyToOne
	@JoinColumn(name="stateProcessId")
	private StateProcess stateProcess;

	public StateType() {
	}

	public Integer getStateTypeId() {
		return this.stateTypeId;
	}

	public void setStateTypeId(Integer stateTypeId) {
		this.stateTypeId = stateTypeId;
	}

	public Byte getIsLastStep() {
		return this.isLastStep;
	}

	public void setIsLastStep(Byte isLastStep) {
		this.isLastStep = isLastStep;
	}

	public Byte getIsVisibleToAdmin() {
		return this.isVisibleToAdmin;
	}

	public void setIsVisibleToAdmin(Byte isVisibleToAdmin) {
		this.isVisibleToAdmin = isVisibleToAdmin;
	}

	public Byte getIsVisibleToParticipant() {
		return this.isVisibleToParticipant;
	}

	public void setIsVisibleToParticipant(Byte isVisibleToParticipant) {
		this.isVisibleToParticipant = isVisibleToParticipant;
	}

	public Byte getIsVisibleToPartner() {
		return this.isVisibleToPartner;
	}

	public void setIsVisibleToPartner(Byte isVisibleToPartner) {
		this.isVisibleToPartner = isVisibleToPartner;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Integer getWorkQueueCategoryId() {
		return this.workQueueCategoryId;
	}

	public void setWorkQueueCategoryId(Integer workQueueCategoryId) {
		this.workQueueCategoryId = workQueueCategoryId;
	}

	public String getWorkQueueRoleType() {
		return this.workQueueRoleType;
	}

	public void setWorkQueueRoleType(String workQueueRoleType) {
		this.workQueueRoleType = workQueueRoleType;
	}

	public Integer getWorkQueueTypeId() {
		return this.workQueueTypeId;
	}

	public void setWorkQueueTypeId(Integer workQueueTypeId) {
		this.workQueueTypeId = workQueueTypeId;
	}

	public List<AdminWorkQueue> getAdminWorkQueues() {
		return this.adminWorkQueues;
	}

	public void setAdminWorkQueues(List<AdminWorkQueue> adminWorkQueues) {
		this.adminWorkQueues = adminWorkQueues;
	}

	public AdminWorkQueue addAdminWorkQueue(AdminWorkQueue adminWorkQueue) {
		getAdminWorkQueues().add(adminWorkQueue);
		adminWorkQueue.setStateType(this);

		return adminWorkQueue;
	}

	public AdminWorkQueue removeAdminWorkQueue(AdminWorkQueue adminWorkQueue) {
		getAdminWorkQueues().remove(adminWorkQueue);
		adminWorkQueue.setStateType(null);

		return adminWorkQueue;
	}

	public List<PartnerWorkQueue> getPartnerWorkQueues() {
		return this.partnerWorkQueues;
	}

	public void setPartnerWorkQueues(List<PartnerWorkQueue> partnerWorkQueues) {
		this.partnerWorkQueues = partnerWorkQueues;
	}

	public PartnerWorkQueue addPartnerWorkQueue(PartnerWorkQueue partnerWorkQueue) {
		getPartnerWorkQueues().add(partnerWorkQueue);
		partnerWorkQueue.setStateType(this);

		return partnerWorkQueue;
	}

	public PartnerWorkQueue removePartnerWorkQueue(PartnerWorkQueue partnerWorkQueue) {
		getPartnerWorkQueues().remove(partnerWorkQueue);
		partnerWorkQueue.setStateType(null);

		return partnerWorkQueue;
	}

	public List<StateAction> getStateActions() {
		return this.stateActions;
	}

	public void setStateActions(List<StateAction> stateActions) {
		this.stateActions = stateActions;
	}

	public StateAction addStateAction(StateAction stateAction) {
		getStateActions().add(stateAction);
		stateAction.setStateType(this);

		return stateAction;
	}

	public StateAction removeStateAction(StateAction stateAction) {
		getStateActions().remove(stateAction);
		stateAction.setStateType(null);

		return stateAction;
	}

	public List<StateTransition> getStateTransitions1() {
		return this.stateTransitions1;
	}

	public void setStateTransitions1(List<StateTransition> stateTransitions1) {
		this.stateTransitions1 = stateTransitions1;
	}

	public StateTransition addStateTransitions1(StateTransition stateTransitions1) {
		getStateTransitions1().add(stateTransitions1);
		stateTransitions1.setStateType1(this);

		return stateTransitions1;
	}

	public StateTransition removeStateTransitions1(StateTransition stateTransitions1) {
		getStateTransitions1().remove(stateTransitions1);
		stateTransitions1.setStateType1(null);

		return stateTransitions1;
	}

	public List<StateTransition> getStateTransitions2() {
		return this.stateTransitions2;
	}

	public void setStateTransitions2(List<StateTransition> stateTransitions2) {
		this.stateTransitions2 = stateTransitions2;
	}

	public StateTransition addStateTransitions2(StateTransition stateTransitions2) {
		getStateTransitions2().add(stateTransitions2);
		stateTransitions2.setStateType2(this);

		return stateTransitions2;
	}

	public StateTransition removeStateTransitions2(StateTransition stateTransitions2) {
		getStateTransitions2().remove(stateTransitions2);
		stateTransitions2.setStateType2(null);

		return stateTransitions2;
	}

	public List<StateTypeResourcePermission> getStateTypeResourcePermissions() {
		return this.stateTypeResourcePermissions;
	}

	public void setStateTypeResourcePermissions(List<StateTypeResourcePermission> stateTypeResourcePermissions) {
		this.stateTypeResourcePermissions = stateTypeResourcePermissions;
	}

	public StateTypeResourcePermission addStateTypeResourcePermission(StateTypeResourcePermission stateTypeResourcePermission) {
		getStateTypeResourcePermissions().add(stateTypeResourcePermission);
		stateTypeResourcePermission.setStateType(this);

		return stateTypeResourcePermission;
	}

	public StateTypeResourcePermission removeStateTypeResourcePermission(StateTypeResourcePermission stateTypeResourcePermission) {
		getStateTypeResourcePermissions().remove(stateTypeResourcePermission);
		stateTypeResourcePermission.setStateType(null);

		return stateTypeResourcePermission;
	}

	public StateProcess getStateProcess() {
		return this.stateProcess;
	}

	public void setStateProcess(StateProcess stateProcess) {
		this.stateProcess = stateProcess;
	}

}