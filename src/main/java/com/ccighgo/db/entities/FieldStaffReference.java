package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


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
	@Column(unique=true, nullable=false)
	private Integer feldStaffReferencesId;

	@Column(length=100)
	private String city;

	@Column(length=100)
	private String email;

	@Column(length=50)
	private String firstName;

	@Column(length=45)
	private String lastName;

	@Column(length=25)
	private String phone;

	@Column(length=100)
	private String relationshipToApplicant;

	@Column(length=100)
	private String streetAddress;

	@Column(length=25)
	private String zipCode;

	//bi-directional many-to-one association to FieldStaff
	@ManyToOne
	@JoinColumn(name="fieldStaffGoId")
	private FieldStaff fieldStaff;

	//bi-directional many-to-one association to LookupUSState
	@ManyToOne
	@JoinColumn(name="stateId")
	private LookupUSState lookupUsstate;

	public FieldStaffReference() {
	}

	public Integer getFeldStaffReferencesId() {
		return this.feldStaffReferencesId;
	}

	public void setFeldStaffReferencesId(Integer feldStaffReferencesId) {
		this.feldStaffReferencesId = feldStaffReferencesId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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