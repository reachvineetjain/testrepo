package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the FieldStaffReferences database table.
 * 
 */
@Entity
@Table(name="FieldStaffReferences")
@NamedQuery(name="FieldStaffReference.findAll", query="SELECT f FROM FieldStaffReference f")
public class FieldStaffReference implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer feldStaffReferencesId;

	private Byte approved;

	private String city;

	@Lob
	private String comments;

	private String communityRating;

	private Integer createdBy;

	private Timestamp createdOn;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfReference;

	private String email;

	private Byte emailSent;

	private String firstName;

	private String flexibilityRating;

	private String knownFamilyMethod;

	private String lastName;

	private String maturityRating;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	private Integer monthsKnown;

	private Byte needsPhoneCall;

	private String objectivityRating;

	@Lob
	private String ownChildReasons;

	private Integer ownChildSupervised;

	private String phone;

	private Byte rejected;

	private String relationshipToApplicant;

	private String streetAddress;

	private Byte submitted;

	private String teenRating;

	private String warmthRating;

	private Integer yearsKnown;

	private String zipCode;

	//bi-directional many-to-one association to FieldStaff
	@ManyToOne
	@JoinColumn(name="fieldStaffGoId",insertable=false,updatable=false)
	private FieldStaff fieldStaff;

	//bi-directional many-to-one association to LookupUSState
	@ManyToOne
	@JoinColumn(name="stateId",insertable=false,updatable=false)
	private LookupUSState lookupUsstate;

	public FieldStaffReference() {
	}

	public Integer getFeldStaffReferencesId() {
		return this.feldStaffReferencesId;
	}

	public void setFeldStaffReferencesId(Integer feldStaffReferencesId) {
		this.feldStaffReferencesId = feldStaffReferencesId;
	}

	public Byte getApproved() {
		return this.approved;
	}

	public void setApproved(Byte approved) {
		this.approved = approved;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCommunityRating() {
		return this.communityRating;
	}

	public void setCommunityRating(String communityRating) {
		this.communityRating = communityRating;
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

	public Date getDateOfReference() {
		return this.dateOfReference;
	}

	public void setDateOfReference(Date dateOfReference) {
		this.dateOfReference = dateOfReference;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Byte getEmailSent() {
		return this.emailSent;
	}

	public void setEmailSent(Byte emailSent) {
		this.emailSent = emailSent;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFlexibilityRating() {
		return this.flexibilityRating;
	}

	public void setFlexibilityRating(String flexibilityRating) {
		this.flexibilityRating = flexibilityRating;
	}

	public String getKnownFamilyMethod() {
		return this.knownFamilyMethod;
	}

	public void setKnownFamilyMethod(String knownFamilyMethod) {
		this.knownFamilyMethod = knownFamilyMethod;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMaturityRating() {
		return this.maturityRating;
	}

	public void setMaturityRating(String maturityRating) {
		this.maturityRating = maturityRating;
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

	public Integer getMonthsKnown() {
		return this.monthsKnown;
	}

	public void setMonthsKnown(Integer monthsKnown) {
		this.monthsKnown = monthsKnown;
	}

	public Byte getNeedsPhoneCall() {
		return this.needsPhoneCall;
	}

	public void setNeedsPhoneCall(Byte needsPhoneCall) {
		this.needsPhoneCall = needsPhoneCall;
	}

	public String getObjectivityRating() {
		return this.objectivityRating;
	}

	public void setObjectivityRating(String objectivityRating) {
		this.objectivityRating = objectivityRating;
	}

	public String getOwnChildReasons() {
		return this.ownChildReasons;
	}

	public void setOwnChildReasons(String ownChildReasons) {
		this.ownChildReasons = ownChildReasons;
	}

	public Integer getOwnChildSupervised() {
		return this.ownChildSupervised;
	}

	public void setOwnChildSupervised(Integer ownChildSupervised) {
		this.ownChildSupervised = ownChildSupervised;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Byte getRejected() {
		return this.rejected;
	}

	public void setRejected(Byte rejected) {
		this.rejected = rejected;
	}

	public String getRelationshipToApplicant() {
		return this.relationshipToApplicant;
	}

	public void setRelationshipToApplicant(String relationshipToApplicant) {
		this.relationshipToApplicant = relationshipToApplicant;
	}

	public String getStreetAddress() {
		return this.streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public Byte getSubmitted() {
		return this.submitted;
	}

	public void setSubmitted(Byte submitted) {
		this.submitted = submitted;
	}

	public String getTeenRating() {
		return this.teenRating;
	}

	public void setTeenRating(String teenRating) {
		this.teenRating = teenRating;
	}

	public String getWarmthRating() {
		return this.warmthRating;
	}

	public void setWarmthRating(String warmthRating) {
		this.warmthRating = warmthRating;
	}

	public Integer getYearsKnown() {
		return this.yearsKnown;
	}

	public void setYearsKnown(Integer yearsKnown) {
		this.yearsKnown = yearsKnown;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public FieldStaff getFieldStaff() {
		return this.fieldStaff;
	}

	public void setFieldStaff(FieldStaff fieldStaff) {
		this.fieldStaff = fieldStaff;
	}

	public LookupUSState getLookupUsstate() {
		return this.lookupUsstate;
	}

	public void setLookupUsstate(LookupUSState lookupUsstate) {
		this.lookupUsstate = lookupUsstate;
	}

}