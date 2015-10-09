package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the PartnerSeasonAllocation database table.
 * 
 */
@Entity
@Table(name="PartnerSeasonAllocation")
@NamedQuery(name="PartnerSeasonAllocation.findAll", query="SELECT p FROM PartnerSeasonAllocation p")
public class PartnerSeasonAllocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer partnerSeasonAllocationId;

	@Column(nullable=false)
	private Integer createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	private Integer expectedPaxCount;

	private Integer maxGuaranteedPax;

	private Integer maxPax;

	@Column(nullable=false)
	private Integer modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to DepartmentProgramOption
	@ManyToOne
	@JoinColumn(name="departmentProgramOptionId", nullable=false, insertable =false, updatable=false)
	private DepartmentProgramOption departmentProgramOption;

	//bi-directional many-to-one association to PartnerSeason
	@ManyToOne
	@JoinColumn(name="partnerSeasonId")
	private PartnerSeason partnerSeason;

	public PartnerSeasonAllocation() {
	}

	public Integer getPartnerSeasonAllocationId() {
		return this.partnerSeasonAllocationId;
	}

	public void setPartnerSeasonAllocationId(Integer partnerSeasonAllocationId) {
		this.partnerSeasonAllocationId = partnerSeasonAllocationId;
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

	public DepartmentProgramOption getDepartmentProgramOption() {
		return this.departmentProgramOption;
	}

	public void setDepartmentProgramOption(DepartmentProgramOption departmentProgramOption) {
		this.departmentProgramOption = departmentProgramOption;
	}

	public PartnerSeason getPartnerSeason() {
		return this.partnerSeason;
	}

	public void setPartnerSeason(PartnerSeason partnerSeason) {
		this.partnerSeason = partnerSeason;
	}

}