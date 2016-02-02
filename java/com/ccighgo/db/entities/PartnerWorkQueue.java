package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the PartnerWorkQueue database table.
 * 
 */
@Entity
@Table(name="PartnerWorkQueue")
@NamedQuery(name="PartnerWorkQueue.findAll", query="SELECT p FROM PartnerWorkQueue p")
public class PartnerWorkQueue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer partnerWQId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Lob
	private String queueData;

	private int targetGoId;

	@Column(length=45)
	private String targetRoleType;

	//bi-directional many-to-one association to DepartmentProgram
	@ManyToOne
	@JoinColumn(name="departmentProgramId")
	private DepartmentProgram departmentProgram;

	//bi-directional many-to-one association to LookupDepartmentProgram
	@ManyToOne
	@JoinColumn(name="lookupDepartmentProgramId")
	private LookupDepartmentProgram lookupDepartmentProgram;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	@JoinColumn(name="partnerGoId")
	private Partner partner;

	//bi-directional many-to-one association to PartnerWorkQueueCategory
	@ManyToOne
	@JoinColumn(name="partnerWQCategoryId")
	private PartnerWorkQueueCategory partnerWorkQueueCategory;

	//bi-directional many-to-one association to PartnerWorkQueueType
	@ManyToOne
	@JoinColumn(name="partnerWQTypeId")
	private PartnerWorkQueueType partnerWorkQueueType;

	//bi-directional many-to-one association to Season
	@ManyToOne
	@JoinColumn(name="seasonId")
	private Season season;

	//bi-directional many-to-one association to StateType
	@ManyToOne
	@JoinColumn(name="stateTypeId")
	private StateType stateType;

	public PartnerWorkQueue() {
	}

	public Integer getPartnerWQId() {
		return this.partnerWQId;
	}

	public void setPartnerWQId(Integer partnerWQId) {
		this.partnerWQId = partnerWQId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getQueueData() {
		return this.queueData;
	}

	public void setQueueData(String queueData) {
		this.queueData = queueData;
	}

	public int getTargetGoId() {
		return this.targetGoId;
	}

	public void setTargetGoId(int targetGoId) {
		this.targetGoId = targetGoId;
	}

	public String getTargetRoleType() {
		return this.targetRoleType;
	}

	public void setTargetRoleType(String targetRoleType) {
		this.targetRoleType = targetRoleType;
	}

	public DepartmentProgram getDepartmentProgram() {
		return this.departmentProgram;
	}

	public void setDepartmentProgram(DepartmentProgram departmentProgram) {
		this.departmentProgram = departmentProgram;
	}

	public LookupDepartmentProgram getLookupDepartmentProgram() {
		return this.lookupDepartmentProgram;
	}

	public void setLookupDepartmentProgram(LookupDepartmentProgram lookupDepartmentProgram) {
		this.lookupDepartmentProgram = lookupDepartmentProgram;
	}

	public Partner getPartner() {
		return this.partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public PartnerWorkQueueCategory getPartnerWorkQueueCategory() {
		return this.partnerWorkQueueCategory;
	}

	public void setPartnerWorkQueueCategory(PartnerWorkQueueCategory partnerWorkQueueCategory) {
		this.partnerWorkQueueCategory = partnerWorkQueueCategory;
	}

	public PartnerWorkQueueType getPartnerWorkQueueType() {
		return this.partnerWorkQueueType;
	}

	public void setPartnerWorkQueueType(PartnerWorkQueueType partnerWorkQueueType) {
		this.partnerWorkQueueType = partnerWorkQueueType;
	}

	public Season getSeason() {
		return this.season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public StateType getStateType() {
		return this.stateType;
	}

	public void setStateType(StateType stateType) {
		this.stateType = stateType;
	}

}