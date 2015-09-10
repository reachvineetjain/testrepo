package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


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

	private byte ambassadorScholershipParticipants;

	@Column(length=255)
	private String businessName;

	@Column(length=50)
	private String businessYears;

	@Column(length=30)
	private String city;

	@Column(length=50)
	private String companyName;

	@Column(length=255)
	private String countryFlag;

	@Column(length=150)
	private String currentlyOfferingPrograms;

	private byte currentlySendingParticipantToUS;

	@Column(length=50)
	private String email;

	@Column(length=50)
	private String firstName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date followUpDate;

	@Column(length=100)
	private String howDidYouHearAboutCCI;

	@Column(length=50)
	private String lastName;

	@Column(length=255)
	private String logo;

	private byte participantsForHomeCountry;

	@Column(length=15)
	private String phone;

	@Column(length=10)
	private String salutation;

	@Column(length=30)
	private String state;

	@Temporal(TemporalType.TIMESTAMP)
	private Date submittedOn;

	@Column(length=50)
	private String website;

	//bi-directional many-to-one association to LookupCountry
	@ManyToOne
	@JoinColumn(name="countryId")
	private LookupCountry lookupCountry;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	@JoinColumn(name="partnerAgentGoId", unique=true)
	private Partner partner;

	//bi-directional many-to-one association to PartnerAgentInquiryDocument
	@OneToMany(mappedBy="partnerAgentInquiry")
	private List<PartnerAgentInquiryDocument> partnerAgentInquiryDocuments;

	//bi-directional many-to-one association to PartnerAgentInquiryNote
	@OneToMany(mappedBy="partnerAgentInquiry")
	private List<PartnerAgentInquiryNote> partnerAgentInquiryNotes;

	//bi-directional many-to-one association to PartnerAgentProgram
	@OneToMany(mappedBy="partnerAgentInquiry")
	private List<PartnerAgentProgram> partnerAgentPrograms;

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

	public byte getAmbassadorScholershipParticipants() {
		return this.ambassadorScholershipParticipants;
	}

	public void setAmbassadorScholershipParticipants(byte ambassadorScholershipParticipants) {
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

	public byte getCurrentlySendingParticipantToUS() {
		return this.currentlySendingParticipantToUS;
	}

	public void setCurrentlySendingParticipantToUS(byte currentlySendingParticipantToUS) {
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

	public byte getParticipantsForHomeCountry() {
		return this.participantsForHomeCountry;
	}

	public void setParticipantsForHomeCountry(byte participantsForHomeCountry) {
		this.participantsForHomeCountry = participantsForHomeCountry;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSalutation() {
		return this.salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
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

	public List<PartnerAgentInquiryDocument> getPartnerAgentInquiryDocuments() {
		return this.partnerAgentInquiryDocuments;
	}

	public void setPartnerAgentInquiryDocuments(List<PartnerAgentInquiryDocument> partnerAgentInquiryDocuments) {
		this.partnerAgentInquiryDocuments = partnerAgentInquiryDocuments;
	}

	public PartnerAgentInquiryDocument addPartnerAgentInquiryDocument(PartnerAgentInquiryDocument partnerAgentInquiryDocument) {
		getPartnerAgentInquiryDocuments().add(partnerAgentInquiryDocument);
		partnerAgentInquiryDocument.setPartnerAgentInquiry(this);

		return partnerAgentInquiryDocument;
	}

	public PartnerAgentInquiryDocument removePartnerAgentInquiryDocument(PartnerAgentInquiryDocument partnerAgentInquiryDocument) {
		getPartnerAgentInquiryDocuments().remove(partnerAgentInquiryDocument);
		partnerAgentInquiryDocument.setPartnerAgentInquiry(null);

		return partnerAgentInquiryDocument;
	}

	public List<PartnerAgentInquiryNote> getPartnerAgentInquiryNotes() {
		return this.partnerAgentInquiryNotes;
	}

	public void setPartnerAgentInquiryNotes(List<PartnerAgentInquiryNote> partnerAgentInquiryNotes) {
		this.partnerAgentInquiryNotes = partnerAgentInquiryNotes;
	}

	public PartnerAgentInquiryNote addPartnerAgentInquiryNote(PartnerAgentInquiryNote partnerAgentInquiryNote) {
		getPartnerAgentInquiryNotes().add(partnerAgentInquiryNote);
		partnerAgentInquiryNote.setPartnerAgentInquiry(this);

		return partnerAgentInquiryNote;
	}

	public PartnerAgentInquiryNote removePartnerAgentInquiryNote(PartnerAgentInquiryNote partnerAgentInquiryNote) {
		getPartnerAgentInquiryNotes().remove(partnerAgentInquiryNote);
		partnerAgentInquiryNote.setPartnerAgentInquiry(null);

		return partnerAgentInquiryNote;
	}

	public List<PartnerAgentProgram> getPartnerAgentPrograms() {
		return this.partnerAgentPrograms;
	}

	public void setPartnerAgentPrograms(List<PartnerAgentProgram> partnerAgentPrograms) {
		this.partnerAgentPrograms = partnerAgentPrograms;
	}

	public PartnerAgentProgram addPartnerAgentProgram(PartnerAgentProgram partnerAgentProgram) {
		getPartnerAgentPrograms().add(partnerAgentProgram);
		partnerAgentProgram.setPartnerAgentInquiry(this);

		return partnerAgentProgram;
	}

	public PartnerAgentProgram removePartnerAgentProgram(PartnerAgentProgram partnerAgentProgram) {
		getPartnerAgentPrograms().remove(partnerAgentProgram);
		partnerAgentProgram.setPartnerAgentInquiry(null);

		return partnerAgentProgram;
	}

}