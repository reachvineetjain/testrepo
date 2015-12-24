package com.ccighgo.db.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the CCIStaffUsers database table.
 * 
 */
@Entity
@Table(name="CCIStaffUsers")
@NamedQuery(name="CCIStaffUser.findAll", query="SELECT c FROM CCIStaffUser c")
public class CCIStaffUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer cciStaffUserId;

	@Column(nullable=false, length=64)
	private String cciAdminGuid;

	@Column(length=50)
	private String city;

	@Column(nullable=false)
	private Integer createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(length=15)
	private String emergencyPhone;

	@Column(nullable=false, length=30)
	private String firstName;

	@Column(length=100)
	private String homeAddressLineOne;

	@Column(length=100)
	private String homeAddressLineTwo;

	@Column(nullable=false, length=30)
	private String lastName;

	@Column(nullable=false)
	private Integer modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	@Column(length=10)
	private String phoneExtension;

	@Column(length=300)
	private String photo;

	@Column(length=15)
	private String primaryPhone;

	@Column(length=20)
	private String sevisId;

	private Integer supervisorId;

	@Column(length=50)
	private String zip;

	//bi-directional many-to-one association to AdminQuickStatsTypeAggregate
	@OneToMany(mappedBy="ccistaffUser")
	private List<AdminQuickStatsTypeAggregate> adminQuickStatsTypeAggregates;

	//bi-directional many-to-one association to AdminWorkQueueCategoryAggregate
	@OneToMany(mappedBy="ccistaffUser")
	private List<AdminWorkQueueCategoryAggregate> adminWorkQueueCategoryAggregates;

	//bi-directional many-to-one association to AdminWorkQueueTypeAggregate
	@OneToMany(mappedBy="ccistaffUser")
	private List<AdminWorkQueueTypeAggregate> adminWorkQueueTypeAggregates;

	//bi-directional many-to-one association to CCIStaffUserNote
	@OneToMany(mappedBy = "ccistaffUser", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<CCIStaffUserNote> ccistaffUserNotes;

	//bi-directional many-to-one association to CCIStaffUserProgram
	@OneToMany(mappedBy = "ccistaffUser", fetch = FetchType.EAGER)
	   @Fetch(value = FetchMode.SUBSELECT)
	private List<CCIStaffUserProgram> ccistaffUserPrograms;

	//bi-directional one-to-one association to GoIdSequence
	@OneToOne
	@JoinColumn(name="cciStaffUserId", nullable=false, insertable=false, updatable=false)
	private GoIdSequence goIdSequence;

	//bi-directional many-to-one association to LookupCountry
	@ManyToOne
	@JoinColumn(name="countryId")
	private LookupCountry lookupCountry;

	//bi-directional many-to-one association to LookupGender
	@ManyToOne
	@JoinColumn(name="genderId")
	private LookupGender lookupGender;

	//bi-directional many-to-one association to LookupUSState
	@ManyToOne
	@JoinColumn(name="usStatesId")
	private LookupUSState lookupUsstate;

	//bi-directional many-to-one association to CCIStaffUsersCCIStaffRole
	@OneToMany(mappedBy = "ccistaffUser", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<CCIStaffUsersCCIStaffRole> ccistaffUsersCcistaffRoles;

	//bi-directional many-to-one association to CCIStaffUsersResourcePermission
	@OneToMany(mappedBy="ccistaffUser")
	private List<CCIStaffUsersResourcePermission> ccistaffUsersResourcePermissions;

	//bi-directional many-to-one association to FieldStaff
	@OneToMany(mappedBy="ccistaffUser")
	private List<FieldStaff> fieldStaffs;

	//bi-directional many-to-one association to PartnerMessage
	@OneToMany(mappedBy="ccistaffUser")
	private List<PartnerMessage> partnerMessages;

	//bi-directional many-to-one association to PartnerProgram
	@OneToMany(mappedBy="ccistaffUser")
	private List<PartnerProgram> partnerPrograms;

	//bi-directional many-to-one association to PartnerReviewStatus
	@OneToMany(mappedBy="ccistaffUser")
	private List<PartnerReviewStatus> partnerReviewStatuses;

	//bi-directional many-to-one association to PartnerSeason
	@OneToMany(mappedBy="ccistaffUser1")
	private List<PartnerSeason> partnerSeasons1;

	//bi-directional many-to-one association to PartnerSeason
	@OneToMany(mappedBy="ccistaffUser2")
	private List<PartnerSeason> partnerSeasons2;

	//bi-directional many-to-one association to PartnerSeasonAllocation
	@OneToMany(mappedBy="ccistaffUser")
	private List<PartnerSeasonAllocation> partnerSeasonAllocations;

	public CCIStaffUser() {
	}

	public Integer getCciStaffUserId() {
		return this.cciStaffUserId;
	}

	public void setCciStaffUserId(Integer cciStaffUserId) {
		this.cciStaffUserId = cciStaffUserId;
	}

	public String getCciAdminGuid() {
		return this.cciAdminGuid;
	}

	public void setCciAdminGuid(String cciAdminGuid) {
		this.cciAdminGuid = cciAdminGuid;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getEmergencyPhone() {
		return this.emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getHomeAddressLineOne() {
		return this.homeAddressLineOne;
	}

	public void setHomeAddressLineOne(String homeAddressLineOne) {
		this.homeAddressLineOne = homeAddressLineOne;
	}

	public String getHomeAddressLineTwo() {
		return this.homeAddressLineTwo;
	}

	public void setHomeAddressLineTwo(String homeAddressLineTwo) {
		this.homeAddressLineTwo = homeAddressLineTwo;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getPhoneExtension() {
		return this.phoneExtension;
	}

	public void setPhoneExtension(String phoneExtension) {
		this.phoneExtension = phoneExtension;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPrimaryPhone() {
		return this.primaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public String getSevisId() {
		return this.sevisId;
	}

	public void setSevisId(String sevisId) {
		this.sevisId = sevisId;
	}

	public Integer getSupervisorId() {
		return this.supervisorId;
	}

	public void setSupervisorId(Integer supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public List<AdminQuickStatsTypeAggregate> getAdminQuickStatsTypeAggregates() {
		return this.adminQuickStatsTypeAggregates;
	}

	public void setAdminQuickStatsTypeAggregates(List<AdminQuickStatsTypeAggregate> adminQuickStatsTypeAggregates) {
		this.adminQuickStatsTypeAggregates = adminQuickStatsTypeAggregates;
	}

	public AdminQuickStatsTypeAggregate addAdminQuickStatsTypeAggregate(AdminQuickStatsTypeAggregate adminQuickStatsTypeAggregate) {
		getAdminQuickStatsTypeAggregates().add(adminQuickStatsTypeAggregate);
		adminQuickStatsTypeAggregate.setCcistaffUser(this);

		return adminQuickStatsTypeAggregate;
	}

	public AdminQuickStatsTypeAggregate removeAdminQuickStatsTypeAggregate(AdminQuickStatsTypeAggregate adminQuickStatsTypeAggregate) {
		getAdminQuickStatsTypeAggregates().remove(adminQuickStatsTypeAggregate);
		adminQuickStatsTypeAggregate.setCcistaffUser(null);

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
		adminWorkQueueCategoryAggregate.setCcistaffUser(this);

		return adminWorkQueueCategoryAggregate;
	}

	public AdminWorkQueueCategoryAggregate removeAdminWorkQueueCategoryAggregate(AdminWorkQueueCategoryAggregate adminWorkQueueCategoryAggregate) {
		getAdminWorkQueueCategoryAggregates().remove(adminWorkQueueCategoryAggregate);
		adminWorkQueueCategoryAggregate.setCcistaffUser(null);

		return adminWorkQueueCategoryAggregate;
	}

	public List<AdminWorkQueueTypeAggregate> getAdminWorkQueueTypeAggregates() {
		return this.adminWorkQueueTypeAggregates;
	}

	public void setAdminWorkQueueTypeAggregates(List<AdminWorkQueueTypeAggregate> adminWorkQueueTypeAggregates) {
		this.adminWorkQueueTypeAggregates = adminWorkQueueTypeAggregates;
	}

	public AdminWorkQueueTypeAggregate addAdminWorkQueueTypeAggregate(AdminWorkQueueTypeAggregate adminWorkQueueTypeAggregate) {
		getAdminWorkQueueTypeAggregates().add(adminWorkQueueTypeAggregate);
		adminWorkQueueTypeAggregate.setCcistaffUser(this);

		return adminWorkQueueTypeAggregate;
	}

	public AdminWorkQueueTypeAggregate removeAdminWorkQueueTypeAggregate(AdminWorkQueueTypeAggregate adminWorkQueueTypeAggregate) {
		getAdminWorkQueueTypeAggregates().remove(adminWorkQueueTypeAggregate);
		adminWorkQueueTypeAggregate.setCcistaffUser(null);

		return adminWorkQueueTypeAggregate;
	}

	public List<CCIStaffUserNote> getCcistaffUserNotes() {
		return this.ccistaffUserNotes;
	}

	public void setCcistaffUserNotes(List<CCIStaffUserNote> ccistaffUserNotes) {
		this.ccistaffUserNotes = ccistaffUserNotes;
	}

	public CCIStaffUserNote addCcistaffUserNote(CCIStaffUserNote ccistaffUserNote) {
		getCcistaffUserNotes().add(ccistaffUserNote);
		ccistaffUserNote.setCcistaffUser(this);

		return ccistaffUserNote;
	}

	public CCIStaffUserNote removeCcistaffUserNote(CCIStaffUserNote ccistaffUserNote) {
		getCcistaffUserNotes().remove(ccistaffUserNote);
		ccistaffUserNote.setCcistaffUser(null);

		return ccistaffUserNote;
	}

	public List<CCIStaffUserProgram> getCcistaffUserPrograms() {
		return this.ccistaffUserPrograms;
	}

	public void setCcistaffUserPrograms(List<CCIStaffUserProgram> ccistaffUserPrograms) {
		this.ccistaffUserPrograms = ccistaffUserPrograms;
	}

	public CCIStaffUserProgram addCcistaffUserProgram(CCIStaffUserProgram ccistaffUserProgram) {
		getCcistaffUserPrograms().add(ccistaffUserProgram);
		ccistaffUserProgram.setCcistaffUser(this);

		return ccistaffUserProgram;
	}

	public CCIStaffUserProgram removeCcistaffUserProgram(CCIStaffUserProgram ccistaffUserProgram) {
		getCcistaffUserPrograms().remove(ccistaffUserProgram);
		ccistaffUserProgram.setCcistaffUser(null);

		return ccistaffUserProgram;
	}

	public GoIdSequence getGoIdSequence() {
		return this.goIdSequence;
	}

	public void setGoIdSequence(GoIdSequence goIdSequence) {
		this.goIdSequence = goIdSequence;
	}

	public LookupCountry getLookupCountry() {
		return this.lookupCountry;
	}

	public void setLookupCountry(LookupCountry lookupCountry) {
		this.lookupCountry = lookupCountry;
	}

	public LookupGender getLookupGender() {
		return this.lookupGender;
	}

	public void setLookupGender(LookupGender lookupGender) {
		this.lookupGender = lookupGender;
	}

	public LookupUSState getLookupUsstate() {
		return this.lookupUsstate;
	}

	public void setLookupUsstate(LookupUSState lookupUsstate) {
		this.lookupUsstate = lookupUsstate;
	}

	public List<CCIStaffUsersCCIStaffRole> getCcistaffUsersCcistaffRoles() {
		return this.ccistaffUsersCcistaffRoles;
	}

	public void setCcistaffUsersCcistaffRoles(List<CCIStaffUsersCCIStaffRole> ccistaffUsersCcistaffRoles) {
		this.ccistaffUsersCcistaffRoles = ccistaffUsersCcistaffRoles;
	}

	public CCIStaffUsersCCIStaffRole addCcistaffUsersCcistaffRole(CCIStaffUsersCCIStaffRole ccistaffUsersCcistaffRole) {
		getCcistaffUsersCcistaffRoles().add(ccistaffUsersCcistaffRole);
		ccistaffUsersCcistaffRole.setCcistaffUser(this);

		return ccistaffUsersCcistaffRole;
	}

	public CCIStaffUsersCCIStaffRole removeCcistaffUsersCcistaffRole(CCIStaffUsersCCIStaffRole ccistaffUsersCcistaffRole) {
		getCcistaffUsersCcistaffRoles().remove(ccistaffUsersCcistaffRole);
		ccistaffUsersCcistaffRole.setCcistaffUser(null);

		return ccistaffUsersCcistaffRole;
	}

	public List<CCIStaffUsersResourcePermission> getCcistaffUsersResourcePermissions() {
		return this.ccistaffUsersResourcePermissions;
	}

	public void setCcistaffUsersResourcePermissions(List<CCIStaffUsersResourcePermission> ccistaffUsersResourcePermissions) {
		this.ccistaffUsersResourcePermissions = ccistaffUsersResourcePermissions;
	}

	public CCIStaffUsersResourcePermission addCcistaffUsersResourcePermission(CCIStaffUsersResourcePermission ccistaffUsersResourcePermission) {
		getCcistaffUsersResourcePermissions().add(ccistaffUsersResourcePermission);
		ccistaffUsersResourcePermission.setCcistaffUser(this);

		return ccistaffUsersResourcePermission;
	}

	public CCIStaffUsersResourcePermission removeCcistaffUsersResourcePermission(CCIStaffUsersResourcePermission ccistaffUsersResourcePermission) {
		getCcistaffUsersResourcePermissions().remove(ccistaffUsersResourcePermission);
		ccistaffUsersResourcePermission.setCcistaffUser(null);

		return ccistaffUsersResourcePermission;
	}

	public List<FieldStaff> getFieldStaffs() {
		return this.fieldStaffs;
	}

	public void setFieldStaffs(List<FieldStaff> fieldStaffs) {
		this.fieldStaffs = fieldStaffs;
	}

	public FieldStaff addFieldStaff(FieldStaff fieldStaff) {
		getFieldStaffs().add(fieldStaff);
		fieldStaff.setCcistaffUser(this);

		return fieldStaff;
	}

	public FieldStaff removeFieldStaff(FieldStaff fieldStaff) {
		getFieldStaffs().remove(fieldStaff);
		fieldStaff.setCcistaffUser(null);

		return fieldStaff;
	}

	public List<PartnerMessage> getPartnerMessages() {
		return this.partnerMessages;
	}

	public void setPartnerMessages(List<PartnerMessage> partnerMessages) {
		this.partnerMessages = partnerMessages;
	}

	public PartnerMessage addPartnerMessage(PartnerMessage partnerMessage) {
		getPartnerMessages().add(partnerMessage);
		partnerMessage.setCcistaffUser(this);

		return partnerMessage;
	}

	public PartnerMessage removePartnerMessage(PartnerMessage partnerMessage) {
		getPartnerMessages().remove(partnerMessage);
		partnerMessage.setCcistaffUser(null);

		return partnerMessage;
	}

	public List<PartnerProgram> getPartnerPrograms() {
		return this.partnerPrograms;
	}

	public void setPartnerPrograms(List<PartnerProgram> partnerPrograms) {
		this.partnerPrograms = partnerPrograms;
	}

	public PartnerProgram addPartnerProgram(PartnerProgram partnerProgram) {
		getPartnerPrograms().add(partnerProgram);
		partnerProgram.setCcistaffUser(this);

		return partnerProgram;
	}

	public PartnerProgram removePartnerProgram(PartnerProgram partnerProgram) {
		getPartnerPrograms().remove(partnerProgram);
		partnerProgram.setCcistaffUser(null);

		return partnerProgram;
	}

	public List<PartnerReviewStatus> getPartnerReviewStatuses() {
		return this.partnerReviewStatuses;
	}

	public void setPartnerReviewStatuses(List<PartnerReviewStatus> partnerReviewStatuses) {
		this.partnerReviewStatuses = partnerReviewStatuses;
	}

	public PartnerReviewStatus addPartnerReviewStatus(PartnerReviewStatus partnerReviewStatus) {
		getPartnerReviewStatuses().add(partnerReviewStatus);
		partnerReviewStatus.setCcistaffUser(this);

		return partnerReviewStatus;
	}

	public PartnerReviewStatus removePartnerReviewStatus(PartnerReviewStatus partnerReviewStatus) {
		getPartnerReviewStatuses().remove(partnerReviewStatus);
		partnerReviewStatus.setCcistaffUser(null);

		return partnerReviewStatus;
	}

	public List<PartnerSeason> getPartnerSeasons1() {
		return this.partnerSeasons1;
	}

	public void setPartnerSeasons1(List<PartnerSeason> partnerSeasons1) {
		this.partnerSeasons1 = partnerSeasons1;
	}

	public PartnerSeason addPartnerSeasons1(PartnerSeason partnerSeasons1) {
		getPartnerSeasons1().add(partnerSeasons1);
		partnerSeasons1.setCcistaffUser1(this);

		return partnerSeasons1;
	}

	public PartnerSeason removePartnerSeasons1(PartnerSeason partnerSeasons1) {
		getPartnerSeasons1().remove(partnerSeasons1);
		partnerSeasons1.setCcistaffUser1(null);

		return partnerSeasons1;
	}

	public List<PartnerSeason> getPartnerSeasons2() {
		return this.partnerSeasons2;
	}

	public void setPartnerSeasons2(List<PartnerSeason> partnerSeasons2) {
		this.partnerSeasons2 = partnerSeasons2;
	}

	public PartnerSeason addPartnerSeasons2(PartnerSeason partnerSeasons2) {
		getPartnerSeasons2().add(partnerSeasons2);
		partnerSeasons2.setCcistaffUser2(this);

		return partnerSeasons2;
	}

	public PartnerSeason removePartnerSeasons2(PartnerSeason partnerSeasons2) {
		getPartnerSeasons2().remove(partnerSeasons2);
		partnerSeasons2.setCcistaffUser2(null);

		return partnerSeasons2;
	}

	public List<PartnerSeasonAllocation> getPartnerSeasonAllocations() {
		return this.partnerSeasonAllocations;
	}

	public void setPartnerSeasonAllocations(List<PartnerSeasonAllocation> partnerSeasonAllocations) {
		this.partnerSeasonAllocations = partnerSeasonAllocations;
	}

	public PartnerSeasonAllocation addPartnerSeasonAllocation(PartnerSeasonAllocation partnerSeasonAllocation) {
		getPartnerSeasonAllocations().add(partnerSeasonAllocation);
		partnerSeasonAllocation.setCcistaffUser(this);

		return partnerSeasonAllocation;
	}

	public PartnerSeasonAllocation removePartnerSeasonAllocation(PartnerSeasonAllocation partnerSeasonAllocation) {
		getPartnerSeasonAllocations().remove(partnerSeasonAllocation);
		partnerSeasonAllocation.setCcistaffUser(null);

		return partnerSeasonAllocation;
	}

}