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
	private Integer partnerAgentGoId;

	private String adressLineOne;

	private String adressLineTwo;

	private Byte ambassadorScholershipParticipants;

	private String businessName;

	private String businessYears;

	private String city;

	private String countryFlag;

	private Integer countryId;

	private String currentlyOfferingPrograms;

	private Byte currentlySendingParticipantToUS;

	private String email;

	private String firstName;

	private String howDidYouHearAboutCCI;

	private String lastName;

	private String logo;

	private Byte participantsForHomeCountry;

	private String phone;

	private String salutation;

	private String state;

	@Temporal(TemporalType.TIMESTAMP)
	private Date submittedOn;

	private String website;

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

	public Integer getPartnerAgentGoId() {
		return this.partnerAgentGoId;
	}

	public void setPartnerAgentGoId(Integer partnerAgentGoId) {
		this.partnerAgentGoId = partnerAgentGoId;
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

	public String getCountryFlag() {
		return this.countryFlag;
	}

	public void setCountryFlag(String countryFlag) {
		this.countryFlag = countryFlag;
	}

	public Integer getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
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