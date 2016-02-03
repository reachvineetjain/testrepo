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
@NamedQuery(name="PartnerOffice.findAll", query="SELECT p FROM PartnerOffice p")
public class PartnerOffice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer partnerOfficeId;

	private String adressOne;

	private String adressTwo;

	private String city;

	private Integer createdBy;

	private Timestamp createdOn;

	private String email;

	private String faxNumber;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	private String officeNotes;

	private String phoneNumber;

	private String postalCode;

	private String state;

	private String website;

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

	//bi-directional many-to-one association to PartnerUser
	@OneToMany(mappedBy="partnerOffice")
	private List<PartnerUser> partnerUsers;

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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<PartnerUser> getPartnerUsers() {
		return this.partnerUsers;
	}

	public void setPartnerUsers(List<PartnerUser> partnerUsers) {
		this.partnerUsers = partnerUsers;
	}

	public PartnerUser addPartnerUser(PartnerUser partnerUser) {
		getPartnerUsers().add(partnerUser);
		partnerUser.setPartnerOffice(this);

		return partnerUser;
	}

	public PartnerUser removePartnerUser(PartnerUser partnerUser) {
		getPartnerUsers().remove(partnerUser);
		partnerUser.setPartnerOffice(null);

		return partnerUser;
	}

}