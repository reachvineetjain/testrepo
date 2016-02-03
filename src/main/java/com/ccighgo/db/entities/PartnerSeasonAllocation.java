package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the PartnerSeasonAllocation database table.
 * 
 */
@Entity
@NamedQuery(name="PartnerSeasonAllocation.findAll", query="SELECT p FROM PartnerSeasonAllocation p")
public class PartnerSeasonAllocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer partnerSeasonAllocationId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date allocationRequestedOn;

	@Temporal(TemporalType.TIMESTAMP)
	private Date allocationRequestReviewedOn;

	private Integer createdBy;

	private Timestamp createdOn;

	private Integer expectedPaxCount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date followupDate;

	private Integer maxGuaranteedPax;

	private Integer maxPax;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	private Integer requestedMaxGuaranteedPax;

	private Integer requestedMaxPax;

	//bi-directional many-to-one association to CCIStaffUser
	@ManyToOne
	@JoinColumn(name="allocationRequestReviewedBy",insertable=false,updatable=false)
	private CCIStaffUser ccistaffUser;

	//bi-directional many-to-one association to DepartmentProgramOption
	@ManyToOne
	@JoinColumn(name="departmentProgramOptionId",insertable=false,updatable=false)
	private DepartmentProgramOption departmentProgramOption;

	//bi-directional many-to-one association to Login
	@ManyToOne
	@JoinColumn(name="allocationRequestedBy",insertable=false,updatable=false)
	private Login login;

	//bi-directional many-to-one association to PartnerSeason
	@ManyToOne
	@JoinColumn(name="partnerSeasonId",insertable=false,updatable=false)
	private PartnerSeason partnerSeason;

	//bi-directional many-to-one association to PartnerStatus
	@ManyToOne
	@JoinColumn(name="allocationRequestStatusId",insertable=false,updatable=false)
	private PartnerStatus partnerStatus;

	public PartnerSeasonAllocation() {
	}

	public Integer getPartnerSeasonAllocationId() {
		return this.partnerSeasonAllocationId;
	}

	public void setPartnerSeasonAllocationId(Integer partnerSeasonAllocationId) {
		this.partnerSeasonAllocationId = partnerSeasonAllocationId;
	}

	public Date getAllocationRequestedOn() {
		return this.allocationRequestedOn;
	}

	public void setAllocationRequestedOn(Date allocationRequestedOn) {
		this.allocationRequestedOn = allocationRequestedOn;
	}

	public Date getAllocationRequestReviewedOn() {
		return this.allocationRequestReviewedOn;
	}

	public void setAllocationRequestReviewedOn(Date allocationRequestReviewedOn) {
		this.allocationRequestReviewedOn = allocationRequestReviewedOn;
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

	public Integer getExpectedPaxCount() {
		return this.expectedPaxCount;
	}

	public void setExpectedPaxCount(Integer expectedPaxCount) {
		this.expectedPaxCount = expectedPaxCount;
	}

	public Date getFollowupDate() {
		return this.followupDate;
	}

	public void setFollowupDate(Date followupDate) {
		this.followupDate = followupDate;
	}

	public Integer getMaxGuaranteedPax() {
		return this.maxGuaranteedPax;
	}

	public void setMaxGuaranteedPax(Integer maxGuaranteedPax) {
		this.maxGuaranteedPax = maxGuaranteedPax;
	}

	public Integer getMaxPax() {
		return this.maxPax;
	}

	public void setMaxPax(Integer maxPax) {
		this.maxPax = maxPax;
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

	public Integer getRequestedMaxGuaranteedPax() {
		return this.requestedMaxGuaranteedPax;
	}

	public void setRequestedMaxGuaranteedPax(Integer requestedMaxGuaranteedPax) {
		this.requestedMaxGuaranteedPax = requestedMaxGuaranteedPax;
	}

	public Integer getRequestedMaxPax() {
		return this.requestedMaxPax;
	}

	public void setRequestedMaxPax(Integer requestedMaxPax) {
		this.requestedMaxPax = requestedMaxPax;
	}

	public CCIStaffUser getCcistaffUser() {
		return this.ccistaffUser;
	}

	public void setCcistaffUser(CCIStaffUser ccistaffUser) {
		this.ccistaffUser = ccistaffUser;
	}

	public DepartmentProgramOption getDepartmentProgramOption() {
		return this.departmentProgramOption;
	}

	public void setDepartmentProgramOption(DepartmentProgramOption departmentProgramOption) {
		this.departmentProgramOption = departmentProgramOption;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public PartnerSeason getPartnerSeason() {
		return this.partnerSeason;
	}

	public void setPartnerSeason(PartnerSeason partnerSeason) {
		this.partnerSeason = partnerSeason;
	}

	public PartnerStatus getPartnerStatus() {
		return this.partnerStatus;
	}

	public void setPartnerStatus(PartnerStatus partnerStatus) {
		this.partnerStatus = partnerStatus;
	}

}