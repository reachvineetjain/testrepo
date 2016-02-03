package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the HostFamilyPotentialReference database table.
 * 
 */
@Entity
@NamedQuery(name="HostFamilyPotentialReference.findAll", query="SELECT h FROM HostFamilyPotentialReference h")
public class HostFamilyPotentialReference implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer hostFamilyPotentialReference;

	private String city;

	private Integer createdBy;

	private Timestamp cretatedOn;

	private String email;

	private String firstName;

	private String lastName;

	private String lcName;

	private String mailingAddress;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	private String referenceCity;

	private String referenceEmail;

	private String referenceFirstName;

	private String referenceLastName;

	private String referenceMailingAddress;

	private String referencePhone;

	private String referenceReason;

	private String referenceStateOrRegion;

	private String referenceStreetAddress;

	private String referenceZipCode;

	private String stateOrRegion;

	private String streetAddress;

	private String zipCode;

	//bi-directional many-to-one association to HostFamily
	@ManyToOne
	@JoinColumn(name="hostFamilyGoId",insertable=false,updatable=false)
	private HostFamily hostFamily;

	//bi-directional many-to-one association to LookupCountry
	@ManyToOne
	@JoinColumn(name="referenceCountryId",insertable=false,updatable=false)
	private LookupCountry lookupCountry1;

	//bi-directional many-to-one association to LookupCountry
	@ManyToOne
	@JoinColumn(name="counrtyId",insertable=false,updatable=false)
	private LookupCountry lookupCountry2;

	//bi-directional many-to-one association to UserType
	@ManyToOne
	@JoinColumn(name="userTypeId",insertable=false,updatable=false)
	private UserType userType;

	public HostFamilyPotentialReference() {
	}

	public Integer getHostFamilyPotentialReference() {
		return this.hostFamilyPotentialReference;
	}

	public void setHostFamilyPotentialReference(Integer hostFamilyPotentialReference) {
		this.hostFamilyPotentialReference = hostFamilyPotentialReference;
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

	public Timestamp getCretatedOn() {
		return this.cretatedOn;
	}

	public void setCretatedOn(Timestamp cretatedOn) {
		this.cretatedOn = cretatedOn;
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

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLcName() {
		return this.lcName;
	}

	public void setLcName(String lcName) {
		this.lcName = lcName;
	}

	public String getMailingAddress() {
		return this.mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
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

	public String getReferenceCity() {
		return this.referenceCity;
	}

	public void setReferenceCity(String referenceCity) {
		this.referenceCity = referenceCity;
	}

	public String getReferenceEmail() {
		return this.referenceEmail;
	}

	public void setReferenceEmail(String referenceEmail) {
		this.referenceEmail = referenceEmail;
	}

	public String getReferenceFirstName() {
		return this.referenceFirstName;
	}

	public void setReferenceFirstName(String referenceFirstName) {
		this.referenceFirstName = referenceFirstName;
	}

	public String getReferenceLastName() {
		return this.referenceLastName;
	}

	public void setReferenceLastName(String referenceLastName) {
		this.referenceLastName = referenceLastName;
	}

	public String getReferenceMailingAddress() {
		return this.referenceMailingAddress;
	}

	public void setReferenceMailingAddress(String referenceMailingAddress) {
		this.referenceMailingAddress = referenceMailingAddress;
	}

	public String getReferencePhone() {
		return this.referencePhone;
	}

	public void setReferencePhone(String referencePhone) {
		this.referencePhone = referencePhone;
	}

	public String getReferenceReason() {
		return this.referenceReason;
	}

	public void setReferenceReason(String referenceReason) {
		this.referenceReason = referenceReason;
	}

	public String getReferenceStateOrRegion() {
		return this.referenceStateOrRegion;
	}

	public void setReferenceStateOrRegion(String referenceStateOrRegion) {
		this.referenceStateOrRegion = referenceStateOrRegion;
	}

	public String getReferenceStreetAddress() {
		return this.referenceStreetAddress;
	}

	public void setReferenceStreetAddress(String referenceStreetAddress) {
		this.referenceStreetAddress = referenceStreetAddress;
	}

	public String getReferenceZipCode() {
		return this.referenceZipCode;
	}

	public void setReferenceZipCode(String referenceZipCode) {
		this.referenceZipCode = referenceZipCode;
	}

	public String getStateOrRegion() {
		return this.stateOrRegion;
	}

	public void setStateOrRegion(String stateOrRegion) {
		this.stateOrRegion = stateOrRegion;
	}

	public String getStreetAddress() {
		return this.streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
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

	public LookupCountry getLookupCountry1() {
		return this.lookupCountry1;
	}

	public void setLookupCountry1(LookupCountry lookupCountry1) {
		this.lookupCountry1 = lookupCountry1;
	}

	public LookupCountry getLookupCountry2() {
		return this.lookupCountry2;
	}

	public void setLookupCountry2(LookupCountry lookupCountry2) {
		this.lookupCountry2 = lookupCountry2;
	}

	public UserType getUserType() {
		return this.userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

}