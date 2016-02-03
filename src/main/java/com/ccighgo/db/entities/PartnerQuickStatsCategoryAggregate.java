package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the PartnerQuickStatsCategoryAggregate database table.
 * 
 */
@Entity
@NamedQuery(name="PartnerQuickStatsCategoryAggregate.findAll", query="SELECT p FROM PartnerQuickStatsCategoryAggregate p")
public class PartnerQuickStatsCategoryAggregate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer partnerQSCategoryAggregateId;

	private Timestamp modifiedDate;

	private Integer partnerQSCategoryAggregate;

	private String partnerQSCategoryName;

	//bi-directional many-to-one association to LookupDepartmentProgram
	@ManyToOne
	@JoinColumn(name="lookupDepartmentProgramId",insertable=false,updatable=false)
	private LookupDepartmentProgram lookupDepartmentProgram;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	@JoinColumn(name="partnerGoId",insertable=false,updatable=false)
	private Partner partner;

	//bi-directional many-to-one association to PartnerQuickStatsCategory
	@ManyToOne
	@JoinColumn(name="partnerQSCategoryId",insertable=false,updatable=false)
	private PartnerQuickStatsCategory partnerQuickStatsCategory;

	//bi-directional many-to-one association to PartnerQuickStatsType
	@ManyToOne
	@JoinColumn(name="partnerQSTypeId",insertable=false,updatable=false)
	private PartnerQuickStatsType partnerQuickStatsType;

	public PartnerQuickStatsCategoryAggregate() {
	}

	public Integer getPartnerQSCategoryAggregateId() {
		return this.partnerQSCategoryAggregateId;
	}

	public void setPartnerQSCategoryAggregateId(Integer partnerQSCategoryAggregateId) {
		this.partnerQSCategoryAggregateId = partnerQSCategoryAggregateId;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Integer getPartnerQSCategoryAggregate() {
		return this.partnerQSCategoryAggregate;
	}

	public void setPartnerQSCategoryAggregate(Integer partnerQSCategoryAggregate) {
		this.partnerQSCategoryAggregate = partnerQSCategoryAggregate;
	}

	public String getPartnerQSCategoryName() {
		return this.partnerQSCategoryName;
	}

	public void setPartnerQSCategoryName(String partnerQSCategoryName) {
		this.partnerQSCategoryName = partnerQSCategoryName;
	}

	public LookupDepartmentProgram getLookupDepartmentProgram() {
		return this.lookupDepartmentProgram;
	}

	public void setLookupDepartmentProgram(LookupDepartmentProgram lookupDepartmentProgram) {
		this.lookupDepartmentProgram = lookupDepartmentProgram;
	}

	public Partner getPartner() {
		return this.partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public PartnerQuickStatsCategory getPartnerQuickStatsCategory() {
		return this.partnerQuickStatsCategory;
	}

	public void setPartnerQuickStatsCategory(PartnerQuickStatsCategory partnerQuickStatsCategory) {
		this.partnerQuickStatsCategory = partnerQuickStatsCategory;
	}

	public PartnerQuickStatsType getPartnerQuickStatsType() {
		return this.partnerQuickStatsType;
	}

	public void setPartnerQuickStatsType(PartnerQuickStatsType partnerQuickStatsType) {
		this.partnerQuickStatsType = partnerQuickStatsType;
	}

}