package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the HostFamilyMember database table.
 * 
 */
@Entity
@NamedQuery(name="HostFamilyMember.findAll", query="SELECT h FROM HostFamilyMember h")
public class HostFamilyMember implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer hostFamilyMemberId;

	private Byte active;

	@Temporal(TemporalType.TIMESTAMP)
	private Date backGroundCheckDate;

	private Byte backGroundCheckPassed;

	private String backGroundCheckReportUrl;

	private Byte backGroundCheckSubmitted;

	@Temporal(TemporalType.TIMESTAMP)
	private Date birthDate;

	@Lob
	private String communityInvolvement;

	private String contactName;

	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	private String educationLevel;

	private Byte employed;

	private String firstName;

	@Lob
	private String interests;

	private Byte isFamilyChild;

	private String isHostParent;

	private Byte isSingleAdult;

	private String jobTitle;

	private String lastName;

	private Byte livingAtHome;

	private Integer modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;

	private String phone;

	private Byte reasonForRejection;

	private String residencyTime;

	//bi-directional many-to-one association to HostFamilySeason
	@ManyToOne
	@JoinColumn(name="hostFamilySeasonId")
	private HostFamilySeason hostFamilySeason;

	//bi-directional many-to-one association to LookupGender
	@ManyToOne
	@JoinColumn(name="genderId")
	private LookupGender lookupGender;

	//bi-directional many-to-one association to Relationship
	@ManyToOne
	@JoinColumn(name="relationshipId")
	private Relationship relationship;

	public HostFamilyMember() {
	}

	public Integer getHostFamilyMemberId() {
		return this.hostFamilyMemberId;
	}

	public void setHostFamilyMemberId(Integer hostFamilyMemberId) {
		this.hostFamilyMemberId = hostFamilyMemberId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public Date getBackGroundCheckDate() {
		return this.backGroundCheckDate;
	}

	public void setBackGroundCheckDate(Date backGroundCheckDate) {
		this.backGroundCheckDate = backGroundCheckDate;
	}

	public Byte getBackGroundCheckPassed() {
		return this.backGroundCheckPassed;
	}

	public void setBackGroundCheckPassed(Byte backGroundCheckPassed) {
		this.backGroundCheckPassed = backGroundCheckPassed;
	}

	public String getBackGroundCheckReportUrl() {
		return this.backGroundCheckReportUrl;
	}

	public void setBackGroundCheckReportUrl(String backGroundCheckReportUrl) {
		this.backGroundCheckReportUrl = backGroundCheckReportUrl;
	}

	public Byte getBackGroundCheckSubmitted() {
		return this.backGroundCheckSubmitted;
	}

	public void setBackGroundCheckSubmitted(Byte backGroundCheckSubmitted) {
		this.backGroundCheckSubmitted = backGroundCheckSubmitted;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getCommunityInvolvement() {
		return this.communityInvolvement;
	}

	public void setCommunityInvolvement(String communityInvolvement) {
		this.communityInvolvement = communityInvolvement;
	}

	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getEducationLevel() {
		return this.educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public Byte getEmployed() {
		return this.employed;
	}

	public void setEmployed(Byte employed) {
		this.employed = employed;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getInterests() {
		return this.interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	public Byte getIsFamilyChild() {
		return this.isFamilyChild;
	}

	public void setIsFamilyChild(Byte isFamilyChild) {
		this.isFamilyChild = isFamilyChild;
	}

	public String getIsHostParent() {
		return this.isHostParent;
	}

	public void setIsHostParent(String isHostParent) {
		this.isHostParent = isHostParent;
	}

	public Byte getIsSingleAdult() {
		return this.isSingleAdult;
	}

	public void setIsSingleAdult(Byte isSingleAdult) {
		this.isSingleAdult = isSingleAdult;
	}

	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Byte getLivingAtHome() {
		return this.livingAtHome;
	}

	public void setLivingAtHome(Byte livingAtHome) {
		this.livingAtHome = livingAtHome;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Byte getReasonForRejection() {
		return this.reasonForRejection;
	}

	public void setReasonForRejection(Byte reasonForRejection) {
		this.reasonForRejection = reasonForRejection;
	}

	public String getResidencyTime() {
		return this.residencyTime;
	}

	public void setResidencyTime(String residencyTime) {
		this.residencyTime = residencyTime;
	}

	public HostFamilySeason getHostFamilySeason() {
		return this.hostFamilySeason;
	}

	public void setHostFamilySeason(HostFamilySeason hostFamilySeason) {
		this.hostFamilySeason = hostFamilySeason;
	}

	public LookupGender getLookupGender() {
		return this.lookupGender;
	}

	public void setLookupGender(LookupGender lookupGender) {
		this.lookupGender = lookupGender;
	}

	public Relationship getRelationship() {
		return this.relationship;
	}

	public void setRelationship(Relationship relationship) {
		this.relationship = relationship;
	}

}