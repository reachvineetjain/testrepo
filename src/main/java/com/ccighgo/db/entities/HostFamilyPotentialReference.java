package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the HostFamilyPotentialReference database table.
 * 
 */
@Entity
@Table(name="HostFamilyPotentialReference")
@NamedQuery(name="HostFamilyPotentialReference.findAll", query="SELECT h FROM HostFamilyPotentialReference h")
public class HostFamilyPotentialReference implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer hostFamilyPotentialReference;

	@Column(length=20)
	private String city;

	private Integer createdBy;

	private Timestamp cretatedOn;

	@Column(length=50)
	private String email;

	@Column(length=50)
	private String firstName;

	@Column(length=50)
	private String lastName;

	@Column(length=50)
	private String mailingAddress;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	@Column(length=50)
	private String refereeType;

	@Column(length=20)
	private String referenceCity;

	@Column(length=50)
	private String referenceEmail;

	@Column(length=50)
	private String referenceFirstName;

	@Column(length=50)
	private String referenceLastName;

	@Column(length=50)
	private String referenceMailingAddress;

	@Column(length=30)
	private String referencePhone;

	@Column(length=150)
	private String referenceReason;

	@Column(length=50)
	private String referenceStreetAddress;

	@Column(length=10)
	private String referenceZipCode;

	@Column(length=50)
	private String streetAddress;

	@Column(length=10)
	private String zipCode;

	//bi-directional many-to-one association to HostFamily
	@ManyToOne
	@JoinColumn(name="hostFamilyGoId")
	private HostFamily hostFamily;

	//bi-directional many-to-one association to LookupUSState
	@ManyToOne
	@JoinColumn(name="referenceStateId")
	private LookupUSState lookupUsstate1;

	//bi-directional many-to-one association to LookupUSState
	@ManyToOne
	@JoinColumn(name="stateId")
	private LookupUSState lookupUsstate2;

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

	public String getRefereeType() {
		return this.refereeType;
	}

	public void setRefereeType(String refereeType) {
		this.refereeType = refereeType;
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

	public LookupUSState getLookupUsstate1() {
		return this.lookupUsstate1;
	}

	public void setLookupUsstate1(LookupUSState lookupUsstate1) {
		this.lookupUsstate1 = lookupUsstate1;
	}

	public LookupUSState getLookupUsstate2() {
		return this.lookupUsstate2;
	}

	public void setLookupUsstate2(LookupUSState lookupUsstate2) {
		this.lookupUsstate2 = lookupUsstate2;
	}

}