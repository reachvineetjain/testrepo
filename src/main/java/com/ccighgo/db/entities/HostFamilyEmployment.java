package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the HostFamilyEmployment database table.
 * 
 */
@Entity
@Table(name="HostFamilyEmployment")
@NamedQuery(name="HostFamilyEmployment.findAll", query="SELECT h FROM HostFamilyEmployment h")
public class HostFamilyEmployment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer hostFamilyEmploymentId;

	private Byte active;

	@Column(length=100)
	private String contactName;

	private Integer createdBy;

	private Timestamp createdOn;

	@Column(length=100)
	private String employerName;

	@Column(length=100)
	private String hostFamilyMemberFirstName;

	@Column(length=100)
	private String hostFamilyMemberLastName;

	@Column(length=100)
	private String jobTitle;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	@Column(length=100)
	private String phoneNumber;

	private Byte residenceSiteFunctioningBusiness;

	@Column(length=50)
	private String specifyTypeOfBusiness;

	//bi-directional many-to-one association to HostFamilySeason
	@ManyToOne
	@JoinColumn(name="hostFamilySeasonId")
	private HostFamilySeason hostFamilySeason;

	public HostFamilyEmployment() {
	}

	public Integer getHostFamilyEmploymentId() {
		return this.hostFamilyEmploymentId;
	}

	public void setHostFamilyEmploymentId(Integer hostFamilyEmploymentId) {
		this.hostFamilyEmploymentId = hostFamilyEmploymentId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
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

	public String getEmployerName() {
		return this.employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public String getHostFamilyMemberFirstName() {
		return this.hostFamilyMemberFirstName;
	}

	public void setHostFamilyMemberFirstName(String hostFamilyMemberFirstName) {
		this.hostFamilyMemberFirstName = hostFamilyMemberFirstName;
	}

	public String getHostFamilyMemberLastName() {
		return this.hostFamilyMemberLastName;
	}

	public void setHostFamilyMemberLastName(String hostFamilyMemberLastName) {
		this.hostFamilyMemberLastName = hostFamilyMemberLastName;
	}

	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
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

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Byte getResidenceSiteFunctioningBusiness() {
		return this.residenceSiteFunctioningBusiness;
	}

	public void setResidenceSiteFunctioningBusiness(Byte residenceSiteFunctioningBusiness) {
		this.residenceSiteFunctioningBusiness = residenceSiteFunctioningBusiness;
	}

	public String getSpecifyTypeOfBusiness() {
		return this.specifyTypeOfBusiness;
	}

	public void setSpecifyTypeOfBusiness(String specifyTypeOfBusiness) {
		this.specifyTypeOfBusiness = specifyTypeOfBusiness;
	}

	public HostFamilySeason getHostFamilySeason() {
		return this.hostFamilySeason;
	}

	public void setHostFamilySeason(HostFamilySeason hostFamilySeason) {
		this.hostFamilySeason = hostFamilySeason;
	}

}