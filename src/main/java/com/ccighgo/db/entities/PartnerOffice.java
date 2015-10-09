package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the PartnerOffice database table.
 * 
 */
@Entity
@Table(name="PartnerOffice")
@NamedQuery(name="PartnerOffice.findAll", query="SELECT p FROM PartnerOffice p")
public class PartnerOffice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer partnerOfficeId;

	@Column(length=150)
	private String adressOne;

	@Column(length=150)
	private String adressTwo;

	@Column(length=30)
	private String city;

	private Integer createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(length=150)
	private String faxNumber;

	private Integer modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	@Column(length=2000)
	private String officeNotes;

	@Column(length=150)
	private String phoneNumber;

	@Column(length=13)
	private String postalCode;

	@Column(length=30)
	private String state;

	@Column(length=150)
	private String website;

	//bi-directional many-to-one association to PartnerContact
	@OneToMany(mappedBy="partnerOffice")
	private List<PartnerContact> partnerContacts;

	//bi-directional many-to-one association to LookupCountry
	@ManyToOne
	@JoinColumn(name="countryId")
	private LookupCountry lookupCountry;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	@JoinColumn(name="partnerGoId")
	private Partner partner;

	//bi-directional many-to-one association to PartnerOfficeType
	@ManyToOne
	@JoinColumn(name="partnerOfficeTypeId")
	private PartnerOfficeType partnerOfficeType;

	public PartnerOffice() {
	}

	public Integer getPartnerOfficeId() {
		return this.partnerOfficeId;
	}

	public void setPartnerOfficeId(Integer partnerOfficeId) {
		this.partnerOfficeId = partnerOfficeId;
	}

	public String getAdressOne() {
		return this.adressOne;
	}

	public void setAdressOne(String adressOne) {
		this.adressOne = adressOne;
	}

	public String getAdressTwo() {
		return this.adressTwo;
	}

	public void setAdressTwo(String adressTwo) {
		this.adressTwo = adressTwo;
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

	public String getFaxNumber() {
		return this.faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
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

	public String getOfficeNotes() {
		return this.officeNotes;
	}

	public void setOfficeNotes(String officeNotes) {
		this.officeNotes = officeNotes;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public List<PartnerContact> getPartnerContacts() {
		return this.partnerContacts;
	}

	public void setPartnerContacts(List<PartnerContact> partnerContacts) {
		this.partnerContacts = partnerContacts;
	}

	public PartnerContact addPartnerContact(PartnerContact partnerContact) {
		getPartnerContacts().add(partnerContact);
		partnerContact.setPartnerOffice(this);

		return partnerContact;
	}

	public PartnerContact removePartnerContact(PartnerContact partnerContact) {
		getPartnerContacts().remove(partnerContact);
		partnerContact.setPartnerOffice(null);

		return partnerContact;
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

	public PartnerOfficeType getPartnerOfficeType() {
		return this.partnerOfficeType;
	}

	public void setPartnerOfficeType(PartnerOfficeType partnerOfficeType) {
		this.partnerOfficeType = partnerOfficeType;
	}

}