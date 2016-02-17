package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the HostFamilyMember database table.
 * 
 */
@Entity
@Table(name="HostFamilyMember")
@NamedQuery(name="HostFamilyMember.findAll", query="SELECT h FROM HostFamilyMember h")
public class HostFamilyMember implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer hostFamilyMemberId;

	private Byte active;

	@Temporal(TemporalType.TIMESTAMP)
	private Date birthDate;

	@Lob
	private String communityInvolvement;

	@Column(length=100)
	private String contactName1;

	@Column(length=50)
	private String contactName2;

	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@Column(length=50)
	private String educationLevel;

	@Column(length=50)
	private String employed;

	@Column(length=50)
	private String employer1;

	@Column(length=50)
	private String employer2;

	@Column(length=50)
	private String firstName;

	private Byte haveAnotherJob;

	@Lob
	private String interests;

	private Byte isFamilyChild;

	private Byte isHostParent;

	private Byte isSingleAdult;

	@Column(length=100)
	private String jobTitle1;

	@Column(length=50)
	private String jobTitle2;

	@Column(length=50)
	private String lastName;

	private Byte livingAtHome;

	@Column(length=100)
	private String livingAtHomeExplanation;

	@Column(length=50)
	private String memberEmail;

	@Column(length=25)
	private String memberPhone;

	private Integer modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;

	@Column(length=100)
	private String phone1;

	@Column(length=50)
	private String phone2;

	private Byte reasonForRejection;

	@Column(length=50)
	private String relationship;

	@Column(length=20)
	private String residencyTime;

	//bi-directional many-to-one association to HostFamilySeason
	@ManyToOne
	@JoinColumn(name="hostFamilySeasonId")
	private HostFamilySeason hostFamilySeason;

	//bi-directional many-to-one association to LookupGender
	@ManyToOne
	@JoinColumn(name="genderId")
	private LookupGender lookupGender;

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

	public String getContactName1() {
		return this.contactName1;
	}

	public void setContactName1(String contactName1) {
		this.contactName1 = contactName1;
	}

	public String getContactName2() {
		return this.contactName2;
	}

	public void setContactName2(String contactName2) {
		this.contactName2 = contactName2;
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

	public String getEmployed() {
		return this.employed;
	}

	public void setEmployed(String employed) {
		this.employed = employed;
	}

	public String getEmployer1() {
		return this.employer1;
	}

	public void setEmployer1(String employer1) {
		this.employer1 = employer1;
	}

	public String getEmployer2() {
		return this.employer2;
	}

	public void setEmployer2(String employer2) {
		this.employer2 = employer2;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Byte getHaveAnotherJob() {
		return this.haveAnotherJob;
	}

	public void setHaveAnotherJob(Byte haveAnotherJob) {
		this.haveAnotherJob = haveAnotherJob;
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

	public Byte getIsHostParent() {
		return this.isHostParent;
	}

	public void setIsHostParent(Byte isHostParent) {
		this.isHostParent = isHostParent;
	}

	public Byte getIsSingleAdult() {
		return this.isSingleAdult;
	}

	public void setIsSingleAdult(Byte isSingleAdult) {
		this.isSingleAdult = isSingleAdult;
	}

	public String getJobTitle1() {
		return this.jobTitle1;
	}

	public void setJobTitle1(String jobTitle1) {
		this.jobTitle1 = jobTitle1;
	}

	public String getJobTitle2() {
		return this.jobTitle2;
	}

	public void setJobTitle2(String jobTitle2) {
		this.jobTitle2 = jobTitle2;
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

	public String getLivingAtHomeExplanation() {
		return this.livingAtHomeExplanation;
	}

	public void setLivingAtHomeExplanation(String livingAtHomeExplanation) {
		this.livingAtHomeExplanation = livingAtHomeExplanation;
	}

	public String getMemberEmail() {
		return this.memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberPhone() {
		return this.memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
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

	public String getPhone1() {
		return this.phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return this.phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public Byte getReasonForRejection() {
		return this.reasonForRejection;
	}

	public void setReasonForRejection(Byte reasonForRejection) {
		this.reasonForRejection = reasonForRejection;
	}

	public String getRelationship() {
		return this.relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
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

}