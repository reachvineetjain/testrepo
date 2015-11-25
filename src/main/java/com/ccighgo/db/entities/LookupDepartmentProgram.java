package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the LookupDepartmentPrograms database table.
 * 
 */
@Entity
@Table(name="LookupDepartmentPrograms")
@NamedQuery(name="LookupDepartmentProgram.findAll", query="SELECT l FROM LookupDepartmentProgram l")
public class LookupDepartmentProgram implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer lookupDepartmentProgramId;

	@Column(nullable=false)
	private Integer createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(length=100)
	private String description;

	@Column(nullable=false)
	private Integer modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	@Column(nullable=false, length=50)
	private String programName;

	//bi-directional many-to-one association to AdminQuickStatsType
	@OneToMany(mappedBy="lookupDepartmentProgram")
	private List<AdminQuickStatsType> adminQuickStatsTypes;

	//bi-directional many-to-one association to AdminQuickStatsTypeAggregate
	@OneToMany(mappedBy="lookupDepartmentProgram")
	private List<AdminQuickStatsTypeAggregate> adminQuickStatsTypeAggregates;

	//bi-directional many-to-one association to AdminWorkQueueCategoryAggregate
	@OneToMany(mappedBy="lookupDepartmentProgram")
	private List<AdminWorkQueueCategoryAggregate> adminWorkQueueCategoryAggregates;

	//bi-directional many-to-one association to AdminWorkQueueType
	@OneToMany(mappedBy="lookupDepartmentProgram")
	private List<AdminWorkQueueType> adminWorkQueueTypes;

	//bi-directional many-to-one association to AdminWorkQueueTypeAggregate
	@OneToMany(mappedBy="lookupDepartmentProgram")
	private List<AdminWorkQueueTypeAggregate> adminWorkQueueTypeAggregates;

	//bi-directional many-to-one association to CCIStaffUserProgram
	@OneToMany(mappedBy="lookupDepartmentProgram")
	private List<CCIStaffUserProgram> ccistaffUserPrograms;

	//bi-directional many-to-one association to LookupDepartment
	@ManyToOne
	@JoinColumn(name="departmentId", nullable=false)
	private LookupDepartment lookupDepartment;

	//bi-directional many-to-one association to PartnerHelpOptionProgram
	@OneToMany(mappedBy="lookupDepartmentProgram")
	private List<PartnerHelpOptionProgram> partnerHelpOptionPrograms;

	//bi-directional many-to-one association to PartnerHelpRequest
	@OneToMany(mappedBy="lookupDepartmentProgram")
	private List<PartnerHelpRequest> partnerHelpRequests;

	//bi-directional many-to-one association to PartnerProgram
	@OneToMany(mappedBy="lookupDepartmentProgram")
	private List<PartnerProgram> partnerPrograms;

	//bi-directional many-to-one association to PartnerQuickStatsCategoryAggregate
	@OneToMany(mappedBy="lookupDepartmentProgram")
	private List<PartnerQuickStatsCategoryAggregate> partnerQuickStatsCategoryAggregates;

	//bi-directional many-to-one association to PartnerQuickStatsType
	@OneToMany(mappedBy="lookupDepartmentProgram")
	private List<PartnerQuickStatsType> partnerQuickStatsTypes;

	//bi-directional many-to-one association to PartnerQuickStatsTypeAggregate
	@OneToMany(mappedBy="lookupDepartmentProgram")
	private List<PartnerQuickStatsTypeAggregate> partnerQuickStatsTypeAggregates;

	//bi-directional many-to-one association to PartnerWorkQueue
	@OneToMany(mappedBy="lookupDepartmentProgram")
	private List<PartnerWorkQueue> partnerWorkQueues;

	//bi-directional many-to-one association to PartnerWorkQueueCategoryAggregate
	@OneToMany(mappedBy="lookupDepartmentProgram")
	private List<PartnerWorkQueueCategoryAggregate> partnerWorkQueueCategoryAggregates;

	//bi-directional many-to-one association to PartnerWorkQueueType
	@OneToMany(mappedBy="lookupDepartmentProgram")
	private List<PartnerWorkQueueType> partnerWorkQueueTypes;

	//bi-directional many-to-one association to PartnerWorkQueueTypeAggregate
	@OneToMany(mappedBy="lookupDepartmentProgram")
	private List<PartnerWorkQueueTypeAggregate> partnerWorkQueueTypeAggregates;

	//bi-directional many-to-one association to StateTypeResourcePermission
	@OneToMany(mappedBy="lookupDepartmentProgram")
	private List<StateTypeResourcePermission> stateTypeResourcePermissions;

	public LookupDepartmentProgram() {
	}

	public Integer getLookupDepartmentProgramId() {
		return this.lookupDepartmentProgramId;
	}

	public void setLookupDepartmentProgramId(Integer lookupDepartmentProgramId) {
		this.lookupDepartmentProgramId = lookupDepartmentProgramId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getProgramName() {
		return this.programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public List<AdminQuickStatsType> getAdminQuickStatsTypes() {
		return this.adminQuickStatsTypes;
	}

	public void setAdminQuickStatsTypes(List<AdminQuickStatsType> adminQuickStatsTypes) {
		this.adminQuickStatsTypes = adminQuickStatsTypes;
	}

	public AdminQuickStatsType addAdminQuickStatsType(AdminQuickStatsType adminQuickStatsType) {
		getAdminQuickStatsTypes().add(adminQuickStatsType);
		adminQuickStatsType.setLookupDepartmentProgram(this);

		return adminQuickStatsType;
	}

	public AdminQuickStatsType removeAdminQuickStatsType(AdminQuickStatsType adminQuickStatsType) {
		getAdminQuickStatsTypes().remove(adminQuickStatsType);
		adminQuickStatsType.setLookupDepartmentProgram(null);

		return adminQuickStatsType;
	}

	public List<AdminQuickStatsTypeAggregate> getAdminQuickStatsTypeAggregates() {
		return this.adminQuickStatsTypeAggregates;
	}

	public void setAdminQuickStatsTypeAggregates(List<AdminQuickStatsTypeAggregate> adminQuickStatsTypeAggregates) {
		this.adminQuickStatsTypeAggregates = adminQuickStatsTypeAggregates;
	}

	public AdminQuickStatsTypeAggregate addAdminQuickStatsTypeAggregate(AdminQuickStatsTypeAggregate adminQuickStatsTypeAggregate) {
		getAdminQuickStatsTypeAggregates().add(adminQuickStatsTypeAggregate);
		adminQuickStatsTypeAggregate.setLookupDepartmentProgram(this);

		return adminQuickStatsTypeAggregate;
	}

	public AdminQuickStatsTypeAggregate removeAdminQuickStatsTypeAggregate(AdminQuickStatsTypeAggregate adminQuickStatsTypeAggregate) {
		getAdminQuickStatsTypeAggregates().remove(adminQuickStatsTypeAggregate);
		adminQuickStatsTypeAggregate.setLookupDepartmentProgram(null);

		return adminQuickStatsTypeAggregate;
	}

	public List<AdminWorkQueueCategoryAggregate> getAdminWorkQueueCategoryAggregates() {
		return this.adminWorkQueueCategoryAggregates;
	}

	public void setAdminWorkQueueCategoryAggregates(List<AdminWorkQueueCategoryAggregate> adminWorkQueueCategoryAggregates) {
		this.adminWorkQueueCategoryAggregates = adminWorkQueueCategoryAggregates;
	}

	public AdminWorkQueueCategoryAggregate addAdminWorkQueueCategoryAggregate(AdminWorkQueueCategoryAggregate adminWorkQueueCategoryAggregate) {
		getAdminWorkQueueCategoryAggregates().add(adminWorkQueueCategoryAggregate);
		adminWorkQueueCategoryAggregate.setLookupDepartmentProgram(this);

		return adminWorkQueueCategoryAggregate;
	}

	public AdminWorkQueueCategoryAggregate removeAdminWorkQueueCategoryAggregate(AdminWorkQueueCategoryAggregate adminWorkQueueCategoryAggregate) {
		getAdminWorkQueueCategoryAggregates().remove(adminWorkQueueCategoryAggregate);
		adminWorkQueueCategoryAggregate.setLookupDepartmentProgram(null);

		return adminWorkQueueCategoryAggregate;
	}

	public List<AdminWorkQueueType> getAdminWorkQueueTypes() {
		return this.adminWorkQueueTypes;
	}

	public void setAdminWorkQueueTypes(List<AdminWorkQueueType> adminWorkQueueTypes) {
		this.adminWorkQueueTypes = adminWorkQueueTypes;
	}

	public AdminWorkQueueType addAdminWorkQueueType(AdminWorkQueueType adminWorkQueueType) {
		getAdminWorkQueueTypes().add(adminWorkQueueType);
		adminWorkQueueType.setLookupDepartmentProgram(this);

		return adminWorkQueueType;
	}

	public AdminWorkQueueType removeAdminWorkQueueType(AdminWorkQueueType adminWorkQueueType) {
		getAdminWorkQueueTypes().remove(adminWorkQueueType);
		adminWorkQueueType.setLookupDepartmentProgram(null);

		return adminWorkQueueType;
	}

	public List<AdminWorkQueueTypeAggregate> getAdminWorkQueueTypeAggregates() {
		return this.adminWorkQueueTypeAggregates;
	}

	public void setAdminWorkQueueTypeAggregates(List<AdminWorkQueueTypeAggregate> adminWorkQueueTypeAggregates) {
		this.adminWorkQueueTypeAggregates = adminWorkQueueTypeAggregates;
	}

	public AdminWorkQueueTypeAggregate addAdminWorkQueueTypeAggregate(AdminWorkQueueTypeAggregate adminWorkQueueTypeAggregate) {
		getAdminWorkQueueTypeAggregates().add(adminWorkQueueTypeAggregate);
		adminWorkQueueTypeAggregate.setLookupDepartmentProgram(this);

		return adminWorkQueueTypeAggregate;
	}

	public AdminWorkQueueTypeAggregate removeAdminWorkQueueTypeAggregate(AdminWorkQueueTypeAggregate adminWorkQueueTypeAggregate) {
		getAdminWorkQueueTypeAggregates().remove(adminWorkQueueTypeAggregate);
		adminWorkQueueTypeAggregate.setLookupDepartmentProgram(null);

		return adminWorkQueueTypeAggregate;
	}

	public List<CCIStaffUserProgram> getCcistaffUserPrograms() {
		return this.ccistaffUserPrograms;
	}

	public void setCcistaffUserPrograms(List<CCIStaffUserProgram> ccistaffUserPrograms) {
		this.ccistaffUserPrograms = ccistaffUserPrograms;
	}

	public CCIStaffUserProgram addCcistaffUserProgram(CCIStaffUserProgram ccistaffUserProgram) {
		getCcistaffUserPrograms().add(ccistaffUserProgram);
		ccistaffUserProgram.setLookupDepartmentProgram(this);

		return ccistaffUserProgram;
	}

	public CCIStaffUserProgram removeCcistaffUserProgram(CCIStaffUserProgram ccistaffUserProgram) {
		getCcistaffUserPrograms().remove(ccistaffUserProgram);
		ccistaffUserProgram.setLookupDepartmentProgram(null);

		return ccistaffUserProgram;
	}

	public LookupDepartment getLookupDepartment() {
		return this.lookupDepartment;
	}

	public void setLookupDepartment(LookupDepartment lookupDepartment) {
		this.lookupDepartment = lookupDepartment;
	}

	public List<PartnerHelpOptionProgram> getPartnerHelpOptionPrograms() {
		return this.partnerHelpOptionPrograms;
	}

	public void setPartnerHelpOptionPrograms(List<PartnerHelpOptionProgram> partnerHelpOptionPrograms) {
		this.partnerHelpOptionPrograms = partnerHelpOptionPrograms;
	}

	public PartnerHelpOptionProgram addPartnerHelpOptionProgram(PartnerHelpOptionProgram partnerHelpOptionProgram) {
		getPartnerHelpOptionPrograms().add(partnerHelpOptionProgram);
		partnerHelpOptionProgram.setLookupDepartmentProgram(this);

		return partnerHelpOptionProgram;
	}

	public PartnerHelpOptionProgram removePartnerHelpOptionProgram(PartnerHelpOptionProgram partnerHelpOptionProgram) {
		getPartnerHelpOptionPrograms().remove(partnerHelpOptionProgram);
		partnerHelpOptionProgram.setLookupDepartmentProgram(null);

		return partnerHelpOptionProgram;
	}

	public List<PartnerHelpRequest> getPartnerHelpRequests() {
		return this.partnerHelpRequests;
	}

	public void setPartnerHelpRequests(List<PartnerHelpRequest> partnerHelpRequests) {
		this.partnerHelpRequests = partnerHelpRequests;
	}

	public PartnerHelpRequest addPartnerHelpRequest(PartnerHelpRequest partnerHelpRequest) {
		getPartnerHelpRequests().add(partnerHelpRequest);
		partnerHelpRequest.setLookupDepartmentProgram(this);

		return partnerHelpRequest;
	}

	public PartnerHelpRequest removePartnerHelpRequest(PartnerHelpRequest partnerHelpRequest) {
		getPartnerHelpRequests().remove(partnerHelpRequest);
		partnerHelpRequest.setLookupDepartmentProgram(null);

		return partnerHelpRequest;
	}

	public List<PartnerProgram> getPartnerPrograms() {
		return this.partnerPrograms;
	}

	public void setPartnerPrograms(List<PartnerProgram> partnerPrograms) {
		this.partnerPrograms = partnerPrograms;
	}

	public PartnerProgram addPartnerProgram(PartnerProgram partnerProgram) {
		getPartnerPrograms().add(partnerProgram);
		partnerProgram.setLookupDepartmentProgram(this);

		return partnerProgram;
	}

	public PartnerProgram removePartnerProgram(PartnerProgram partnerProgram) {
		getPartnerPrograms().remove(partnerProgram);
		partnerProgram.setLookupDepartmentProgram(null);

		return partnerProgram;
	}

	public List<PartnerQuickStatsCategoryAggregate> getPartnerQuickStatsCategoryAggregates() {
		return this.partnerQuickStatsCategoryAggregates;
	}

	public void setPartnerQuickStatsCategoryAggregates(List<PartnerQuickStatsCategoryAggregate> partnerQuickStatsCategoryAggregates) {
		this.partnerQuickStatsCategoryAggregates = partnerQuickStatsCategoryAggregates;
	}

	public PartnerQuickStatsCategoryAggregate addPartnerQuickStatsCategoryAggregate(PartnerQuickStatsCategoryAggregate partnerQuickStatsCategoryAggregate) {
		getPartnerQuickStatsCategoryAggregates().add(partnerQuickStatsCategoryAggregate);
		partnerQuickStatsCategoryAggregate.setLookupDepartmentProgram(this);

		return partnerQuickStatsCategoryAggregate;
	}

	public PartnerQuickStatsCategoryAggregate removePartnerQuickStatsCategoryAggregate(PartnerQuickStatsCategoryAggregate partnerQuickStatsCategoryAggregate) {
		getPartnerQuickStatsCategoryAggregates().remove(partnerQuickStatsCategoryAggregate);
		partnerQuickStatsCategoryAggregate.setLookupDepartmentProgram(null);

		return partnerQuickStatsCategoryAggregate;
	}

	public List<PartnerQuickStatsType> getPartnerQuickStatsTypes() {
		return this.partnerQuickStatsTypes;
	}

	public void setPartnerQuickStatsTypes(List<PartnerQuickStatsType> partnerQuickStatsTypes) {
		this.partnerQuickStatsTypes = partnerQuickStatsTypes;
	}

	public PartnerQuickStatsType addPartnerQuickStatsType(PartnerQuickStatsType partnerQuickStatsType) {
		getPartnerQuickStatsTypes().add(partnerQuickStatsType);
		partnerQuickStatsType.setLookupDepartmentProgram(this);

		return partnerQuickStatsType;
	}

	public PartnerQuickStatsType removePartnerQuickStatsType(PartnerQuickStatsType partnerQuickStatsType) {
		getPartnerQuickStatsTypes().remove(partnerQuickStatsType);
		partnerQuickStatsType.setLookupDepartmentProgram(null);

		return partnerQuickStatsType;
	}

	public List<PartnerQuickStatsTypeAggregate> getPartnerQuickStatsTypeAggregates() {
		return this.partnerQuickStatsTypeAggregates;
	}

	public void setPartnerQuickStatsTypeAggregates(List<PartnerQuickStatsTypeAggregate> partnerQuickStatsTypeAggregates) {
		this.partnerQuickStatsTypeAggregates = partnerQuickStatsTypeAggregates;
	}

	public PartnerQuickStatsTypeAggregate addPartnerQuickStatsTypeAggregate(PartnerQuickStatsTypeAggregate partnerQuickStatsTypeAggregate) {
		getPartnerQuickStatsTypeAggregates().add(partnerQuickStatsTypeAggregate);
		partnerQuickStatsTypeAggregate.setLookupDepartmentProgram(this);

		return partnerQuickStatsTypeAggregate;
	}

	public PartnerQuickStatsTypeAggregate removePartnerQuickStatsTypeAggregate(PartnerQuickStatsTypeAggregate partnerQuickStatsTypeAggregate) {
		getPartnerQuickStatsTypeAggregates().remove(partnerQuickStatsTypeAggregate);
		partnerQuickStatsTypeAggregate.setLookupDepartmentProgram(null);

		return partnerQuickStatsTypeAggregate;
	}

	public List<PartnerWorkQueue> getPartnerWorkQueues() {
		return this.partnerWorkQueues;
	}

	public void setPartnerWorkQueues(List<PartnerWorkQueue> partnerWorkQueues) {
		this.partnerWorkQueues = partnerWorkQueues;
	}

	public PartnerWorkQueue addPartnerWorkQueue(PartnerWorkQueue partnerWorkQueue) {
		getPartnerWorkQueues().add(partnerWorkQueue);
		partnerWorkQueue.setLookupDepartmentProgram(this);

		return partnerWorkQueue;
	}

	public PartnerWorkQueue removePartnerWorkQueue(PartnerWorkQueue partnerWorkQueue) {
		getPartnerWorkQueues().remove(partnerWorkQueue);
		partnerWorkQueue.setLookupDepartmentProgram(null);

		return partnerWorkQueue;
	}

	public List<PartnerWorkQueueCategoryAggregate> getPartnerWorkQueueCategoryAggregates() {
		return this.partnerWorkQueueCategoryAggregates;
	}

	public void setPartnerWorkQueueCategoryAggregates(List<PartnerWorkQueueCategoryAggregate> partnerWorkQueueCategoryAggregates) {
		this.partnerWorkQueueCategoryAggregates = partnerWorkQueueCategoryAggregates;
	}

	public PartnerWorkQueueCategoryAggregate addPartnerWorkQueueCategoryAggregate(PartnerWorkQueueCategoryAggregate partnerWorkQueueCategoryAggregate) {
		getPartnerWorkQueueCategoryAggregates().add(partnerWorkQueueCategoryAggregate);
		partnerWorkQueueCategoryAggregate.setLookupDepartmentProgram(this);

		return partnerWorkQueueCategoryAggregate;
	}

	public PartnerWorkQueueCategoryAggregate removePartnerWorkQueueCategoryAggregate(PartnerWorkQueueCategoryAggregate partnerWorkQueueCategoryAggregate) {
		getPartnerWorkQueueCategoryAggregates().remove(partnerWorkQueueCategoryAggregate);
		partnerWorkQueueCategoryAggregate.setLookupDepartmentProgram(null);

		return partnerWorkQueueCategoryAggregate;
	}

	public List<PartnerWorkQueueType> getPartnerWorkQueueTypes() {
		return this.partnerWorkQueueTypes;
	}

	public void setPartnerWorkQueueTypes(List<PartnerWorkQueueType> partnerWorkQueueTypes) {
		this.partnerWorkQueueTypes = partnerWorkQueueTypes;
	}

	public PartnerWorkQueueType addPartnerWorkQueueType(PartnerWorkQueueType partnerWorkQueueType) {
		getPartnerWorkQueueTypes().add(partnerWorkQueueType);
		partnerWorkQueueType.setLookupDepartmentProgram(this);

		return partnerWorkQueueType;
	}

	public PartnerWorkQueueType removePartnerWorkQueueType(PartnerWorkQueueType partnerWorkQueueType) {
		getPartnerWorkQueueTypes().remove(partnerWorkQueueType);
		partnerWorkQueueType.setLookupDepartmentProgram(null);

		return partnerWorkQueueType;
	}

	public List<PartnerWorkQueueTypeAggregate> getPartnerWorkQueueTypeAggregates() {
		return this.partnerWorkQueueTypeAggregates;
	}

	public void setPartnerWorkQueueTypeAggregates(List<PartnerWorkQueueTypeAggregate> partnerWorkQueueTypeAggregates) {
		this.partnerWorkQueueTypeAggregates = partnerWorkQueueTypeAggregates;
	}

	public PartnerWorkQueueTypeAggregate addPartnerWorkQueueTypeAggregate(PartnerWorkQueueTypeAggregate partnerWorkQueueTypeAggregate) {
		getPartnerWorkQueueTypeAggregates().add(partnerWorkQueueTypeAggregate);
		partnerWorkQueueTypeAggregate.setLookupDepartmentProgram(this);

		return partnerWorkQueueTypeAggregate;
	}

	public PartnerWorkQueueTypeAggregate removePartnerWorkQueueTypeAggregate(PartnerWorkQueueTypeAggregate partnerWorkQueueTypeAggregate) {
		getPartnerWorkQueueTypeAggregates().remove(partnerWorkQueueTypeAggregate);
		partnerWorkQueueTypeAggregate.setLookupDepartmentProgram(null);

		return partnerWorkQueueTypeAggregate;
	}

	public List<StateTypeResourcePermission> getStateTypeResourcePermissions() {
		return this.stateTypeResourcePermissions;
	}

	public void setStateTypeResourcePermissions(List<StateTypeResourcePermission> stateTypeResourcePermissions) {
		this.stateTypeResourcePermissions = stateTypeResourcePermissions;
	}

	public StateTypeResourcePermission addStateTypeResourcePermission(StateTypeResourcePermission stateTypeResourcePermission) {
		getStateTypeResourcePermissions().add(stateTypeResourcePermission);
		stateTypeResourcePermission.setLookupDepartmentProgram(this);

		return stateTypeResourcePermission;
	}

	public StateTypeResourcePermission removeStateTypeResourcePermission(StateTypeResourcePermission stateTypeResourcePermission) {
		getStateTypeResourcePermissions().remove(stateTypeResourcePermission);
		stateTypeResourcePermission.setLookupDepartmentProgram(null);

		return stateTypeResourcePermission;
	}

}