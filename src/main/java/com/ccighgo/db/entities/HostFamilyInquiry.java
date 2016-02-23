package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the HostFamilyInquiry database table.
 * 
 */
@Entity
@Table(name="HostFamilyInquiry")
@NamedQuery(name="HostFamilyInquiry.findAll", query="SELECT h FROM HostFamilyInquiry h")
public class HostFamilyInquiry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Integer hostFamilyInquiryId;

	@Column(name="18YearsOrOlder")
	private Byte _8YearsOrOlder;

	@Column(length=50)
	private String address;

	@Lob
	private String cciComments;

	@Column(length=50)
	private String cciHostFamily;

	@Column(length=50)
	private String cityTownHighSchoolLocated;

	@Column(length=50)
	private String currentCity;

	@Column(length=50)
	private String emailAddress;

	@Column(length=50)
	private String firstName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date followUpDate;

	@Column(length=50)
	private String friendRelative;

	@Column(length=50)
	private String heardAboutCCIThrough;

	@Column(length=50)
	private String interestedStudentFrom;

	@Column(length=50)
	private String lastName;

	@Column(length=50)
	private String localPublicHighSchool;

	@Column(length=50)
	private String nearestLargeCityOrMetroArea;

	@Column(length=20)
	private String optionalPhoneNumber;

	@Column(length=20)
	private String preferredPhoneNumber;

	@Column(length=50)
	private String previousHostingExperience;

	@Column(length=50)
	private String requestedLocalCoordinator;

	@Temporal(TemporalType.TIMESTAMP)
	private Date submittedOn;

	@Column(length=50)
	private String website;

	@Column(length=30)
	private String zipCode;

	//bi-directional many-to-one association to HostFamily
	@ManyToOne
	@JoinColumn(name="hostFamilyGoId")
	private HostFamily hostFamily;

	//bi-directional many-to-one association to HostFamilyStatus
	@ManyToOne
	@JoinColumn(name="leadStatusId")
	private HostFamilyStatus hostFamilyStatus;

	//bi-directional many-to-one association to LookupUSState
	@ManyToOne
	@JoinColumn(name="currentState")
	private LookupUSState lookupUsstate;

	public HostFamilyInquiry() {
	}

	public Integer getHostFamilyInquiryId() {
		return this.hostFamilyInquiryId;
	}

	public void setHostFamilyInquiryId(Integer hostFamilyInquiryId) {
		this.hostFamilyInquiryId = hostFamilyInquiryId;
	}

	public Byte get_8YearsOrOlder() {
		return this._8YearsOrOlder;
	}

	public void set_8YearsOrOlder(Byte _8YearsOrOlder) {
		this._8YearsOrOlder = _8YearsOrOlder;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCciComments() {
		return this.cciComments;
	}

	public void setCciComments(String cciComments) {
		this.cciComments = cciComments;
	}

	public String getCciHostFamily() {
		return this.cciHostFamily;
	}

	public void setCciHostFamily(String cciHostFamily) {
		this.cciHostFamily = cciHostFamily;
	}

	public String getCityTownHighSchoolLocated() {
		return this.cityTownHighSchoolLocated;
	}

	public void setCityTownHighSchoolLocated(String cityTownHighSchoolLocated) {
		this.cityTownHighSchoolLocated = cityTownHighSchoolLocated;
	}

	public String getCurrentCity() {
		return this.currentCity;
	}

	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getFollowUpDate() {
		return this.followUpDate;
	}

	public void setFollowUpDate(Date followUpDate) {
		this.followUpDate = followUpDate;
	}

	public String getFriendRelative() {
		return this.friendRelative;
	}

	public void setFriendRelative(String friendRelative) {
		this.friendRelative = friendRelative;
	}

	public String getHeardAboutCCIThrough() {
		return this.heardAboutCCIThrough;
	}

	public void setHeardAboutCCIThrough(String heardAboutCCIThrough) {
		this.heardAboutCCIThrough = heardAboutCCIThrough;
	}

	public String getInterestedStudentFrom() {
		return this.interestedStudentFrom;
	}

	public void setInterestedStudentFrom(String interestedStudentFrom) {
		this.interestedStudentFrom = interestedStudentFrom;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLocalPublicHighSchool() {
		return this.localPublicHighSchool;
	}

	public void setLocalPublicHighSchool(String localPublicHighSchool) {
		this.localPublicHighSchool = localPublicHighSchool;
	}

	public String getNearestLargeCityOrMetroArea() {
		return this.nearestLargeCityOrMetroArea;
	}

	public void setNearestLargeCityOrMetroArea(String nearestLargeCityOrMetroArea) {
		this.nearestLargeCityOrMetroArea = nearestLargeCityOrMetroArea;
	}

	public String getOptionalPhoneNumber() {
		return this.optionalPhoneNumber;
	}

	public void setOptionalPhoneNumber(String optionalPhoneNumber) {
		this.optionalPhoneNumber = optionalPhoneNumber;
	}

	public String getPreferredPhoneNumber() {
		return this.preferredPhoneNumber;
	}

	public void setPreferredPhoneNumber(String preferredPhoneNumber) {
		this.preferredPhoneNumber = preferredPhoneNumber;
	}

	public String getPreviousHostingExperience() {
		return this.previousHostingExperience;
	}

	public void setPreviousHostingExperience(String previousHostingExperience) {
		this.previousHostingExperience = previousHostingExperience;
	}

	public String getRequestedLocalCoordinator() {
		return this.requestedLocalCoordinator;
	}

	public void setRequestedLocalCoordinator(String requestedLocalCoordinator) {
		this.requestedLocalCoordinator = requestedLocalCoordinator;
	}

	public Date getSubmittedOn() {
		return this.submittedOn;
	}

	public void setSubmittedOn(Date submittedOn) {
		this.submittedOn = submittedOn;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public HostFamily getHostFamily() {
		return this.hostFamily;
	}

	public void setHostFamily(HostFamily hostFamily) {
		this.hostFamily = hostFamily;
	}

	public HostFamilyStatus getHostFamilyStatus() {
		return this.hostFamilyStatus;
	}

	public void setHostFamilyStatus(HostFamilyStatus hostFamilyStatus) {
		this.hostFamilyStatus = hostFamilyStatus;
	}

	public LookupUSState getLookupUsstate() {
		return this.lookupUsstate;
	}

	public void setLookupUsstate(LookupUSState lookupUsstate) {
		this.lookupUsstate = lookupUsstate;
	}

}