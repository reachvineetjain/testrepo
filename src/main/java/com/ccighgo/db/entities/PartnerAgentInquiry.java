package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the PartnerAgentInquiries database table.
 * 
 */
@Entity
@Table(name="PartnerAgentInquiries")
@NamedQuery(name="PartnerAgentInquiry.findAll", query="SELECT p FROM PartnerAgentInquiry p")
public class PartnerAgentInquiry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer partnerAgentInquiriesId;

	@Column(length=150)
	private String adressLineOne;

	@Column(length=150)
	private String adressLineTwo;

	private Byte ambassadorScholershipParticipants;

	@Column(length=250)
	private String businessName;

	@Column(length=50)
	private String businessYears;

	@Column(length=30)
	private String city;

	@Column(length=250)
	private String companyName;

	@Column(length=300)
	private String countryFlag;

	@Column(length=150)
	private String currentlyOfferingPrograms;

	private Byte currentlySendingParticipantToUS;

	@Column(length=50)
	private String email;

	@Column(length=50)
	private String firstName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date followUpDate;

	private Byte highSchoolAbroad;

	@Column(length=100)
	private String howDidYouHearAboutCCI;

	@Column(length=50)
	private String lastName;

	@Column(length=300)
	private String logo;

	private Byte other;

	private Byte participantsForHomeCountry;

	@Column(length=25)
	private String phone;

	@Column(length=30)
	private String state;

	@Temporal(TemporalType.TIMESTAMP)
	private Date submittedOn;

	private Byte teachAbroad;

	private Byte volunteerAbroad;

	@Column(length=50)
	private String website;

	//bi-directional many-to-one association to LookupCountry
	@ManyToOne
	@JoinColumn(name="countryId")
	private LookupCountry lookupCountry;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	@JoinColumn(name="partnerGoId")
	private Partner partner;

	//bi-directional many-to-one association to Salutation
	@ManyToOne
	@JoinColumn(name="salutationId")
	private Salutation salutation;

	public PartnerAgentInquiry() {
	}

	public Integer getPartnerAgentInquiriesId() {
		return this.partnerAgentInquiriesId;
	}

	public void setPartnerAgentInquiriesId(Integer partnerAgentInquiriesId) {
		this.partnerAgentInquiriesId = partnerAgentInquiriesId;
	}

	public String getAdressLineOne() {
		return this.adressLineOne;
	}

	public void setAdressLineOne(String adressLineOne) {
		this.adressLineOne = adressLineOne;
	}

	public String getAdressLineTwo() {
		return this.adressLineTwo;
	}

	public void setAdressLineTwo(String adressLineTwo) {
		this.adressLineTwo = adressLineTwo;
	}

	public Byte getAmbassadorScholershipParticipants() {
		return this.ambassadorScholershipParticipants;
	}

	public void setAmbassadorScholershipParticipants(Byte ambassadorScholershipParticipants) {
		this.ambassadorScholershipParticipants = ambassadorScholershipParticipants;
	}

	public String getBusinessName() {
		return this.businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBusinessYears() {
		return this.businessYears;
	}

	public void setBusinessYears(String businessYears) {
		this.businessYears = businessYears;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCountryFlag() {
		return this.countryFlag;
	}

	public void setCountryFlag(String countryFlag) {
		this.countryFlag = countryFlag;
	}

	public String getCurrentlyOfferingPrograms() {
		return this.currentlyOfferingPrograms;
	}

	public void setCurrentlyOfferingPrograms(String currentlyOfferingPrograms) {
		this.currentlyOfferingPrograms = currentlyOfferingPrograms;
	}

	public Byte getCurrentlySendingParticipantToUS() {
		return this.currentlySendingParticipantToUS;
	}

	public void setCurrentlySendingParticipantToUS(Byte currentlySendingParticipantToUS) {
		this.currentlySendingParticipantToUS = currentlySendingParticipantToUS;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Byte getHighSchoolAbroad() {
		return this.highSchoolAbroad;
	}

	public void setHighSchoolAbroad(Byte highSchoolAbroad) {
		this.highSchoolAbroad = highSchoolAbroad;
	}

	public String getHowDidYouHearAboutCCI() {
		return this.howDidYouHearAboutCCI;
	}

	public void setHowDidYouHearAboutCCI(String howDidYouHearAboutCCI) {
		this.howDidYouHearAboutCCI = howDidYouHearAboutCCI;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Byte getOther() {
		return this.other;
	}

	public void setOther(Byte other) {
		this.other = other;
	}

	public Byte getParticipantsForHomeCountry() {
		return this.participantsForHomeCountry;
	}

	public void setParticipantsForHomeCountry(Byte participantsForHomeCountry) {
		this.participantsForHomeCountry = participantsForHomeCountry;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getSubmittedOn() {
		return this.submittedOn;
	}

	public void setSubmittedOn(Date submittedOn) {
		this.submittedOn = submittedOn;
	}

	public Byte getTeachAbroad() {
		return this.teachAbroad;
	}

	public void setTeachAbroad(Byte teachAbroad) {
		this.teachAbroad = teachAbroad;
	}

	public Byte getVolunteerAbroad() {
		return this.volunteerAbroad;
	}

	public void setVolunteerAbroad(Byte volunteerAbroad) {
		this.volunteerAbroad = volunteerAbroad;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public LookupCountry getLookupCountry() {
		return this.lookupCountry;
	}

	public void setLookupCountry(LookupCountry lookupCountry) {
		this.lookupCountry = lookupCountry;
	}

	public Partner getPartner() {
		return this.partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public Salutation getSalutation() {
		return this.salutation;
	}

	public void setSalutation(Salutation salutation) {
		this.salutation = salutation;
	}

}