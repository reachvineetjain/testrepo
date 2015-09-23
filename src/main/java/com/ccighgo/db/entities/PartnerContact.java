package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the PartnerContact database table.
 * 
 */
@Entity
@Table(name="PartnerContact")
@NamedQuery(name="PartnerContact.findAll", query="SELECT p FROM PartnerContact p")
public class PartnerContact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer partnerContactId;

	private byte active;

	private Integer createdBy;

	private Timestamp createdOn;

	@Column(length=150)
	private String email;

	@Column(length=150)
	private String emergencyPhone;

	@Column(length=150)
	private String fax;

	@Column(length=150)
	private String firstName;

	@Column(length=150)
	private String lastName;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	@Column(length=150)
	private String phone;

	private Byte receiveNotificationEmails;

	@Column(length=10)
	private String salutation;

	@Column(length=50)
	private String skypeId;

	@Column(length=150)
	private String title;

	@Column(length=50)
	private String website;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	@JoinColumn(name="partnerGoId")
	private Partner partner;

	//bi-directional many-to-one association to PartnerOffice
	@ManyToOne
	@JoinColumn(name="partnerOfficeId")
	private PartnerOffice partnerOffice;

	public PartnerContact() {
	}

	public Integer getPartnerContactId() {
		return this.partnerContactId;
	}

	public void setPartnerContactId(Integer partnerContactId) {
		this.partnerContactId = partnerContactId;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
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

	public String getEmergencyPhone() {
		return this.emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
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

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Byte getReceiveNotificationEmails() {
		return this.receiveNotificationEmails;
	}

	public void setReceiveNotificationEmails(Byte receiveNotificationEmails) {
		this.receiveNotificationEmails = receiveNotificationEmails;
	}

	public String getSalutation() {
		return this.salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getSkypeId() {
		return this.skypeId;
	}

	public void setSkypeId(String skypeId) {
		this.skypeId = skypeId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Partner getPartner() {
		return this.partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public PartnerOffice getPartnerOffice() {
		return this.partnerOffice;
	}

	public void setPartnerOffice(PartnerOffice partnerOffice) {
		this.partnerOffice = partnerOffice;
	}

}