package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the AdminQuickStatsCategoryAggregate database table.
 * 
 */
@Entity
@Table(name="AdminQuickStatsCategoryAggregate")
@NamedQuery(name="AdminQuickStatsCategoryAggregate.findAll", query="SELECT a FROM AdminQuickStatsCategoryAggregate a")
public class AdminQuickStatsCategoryAggregate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer adminQSCategoryAggregateId;

	@Column(length=50)
	private String adminQSCategoryName;

	@Column(nullable=false)
	private Timestamp modifiedDate;

	//bi-directional many-to-one association to AdminQuickStatsCategory
	@ManyToOne
	@JoinColumn(name="adminQSCategoryId")
	private AdminQuickStatsCategory adminQuickStatsCategory;

	//bi-directional many-to-one association to AdminQuickStatsType
	@ManyToOne
	@JoinColumn(name="adminQSTypeId")
	private AdminQuickStatsType adminQuickStatsType;

	//bi-directional many-to-one association to CCIStaffUser
	@ManyToOne
	@JoinColumn(name="adminGoId")
	private CCIStaffUser ccistaffUser;

	//bi-directional many-to-one association to LookupDepartmentProgram
	@ManyToOne
	@JoinColumn(name="lookupdepartmentProgramId")
	private LookupDepartmentProgram lookupDepartmentProgram;

	public AdminQuickStatsCategoryAggregate() {
	}

	public Integer getAdminQSCategoryAggregateId() {
		return this.adminQSCategoryAggregateId;
	}

	public void setAdminQSCategoryAggregateId(Integer adminQSCategoryAggregateId) {
		this.adminQSCategoryAggregateId = adminQSCategoryAggregateId;
	}

	public String getAdminQSCategoryName() {
		return this.adminQSCategoryName;
	}

	public void setAdminQSCategoryName(String adminQSCategoryName) {
		this.adminQSCategoryName = adminQSCategoryName;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public AdminQuickStatsCategory getAdminQuickStatsCategory() {
		return this.adminQuickStatsCategory;
	}

	public void setAdminQuickStatsCategory(AdminQuickStatsCategory adminQuickStatsCategory) {
		this.adminQuickStatsCategory = adminQuickStatsCategory;
	}

	public AdminQuickStatsType getAdminQuickStatsType() {
		return this.adminQuickStatsType;
	}

	public void setAdminQuickStatsType(AdminQuickStatsType adminQuickStatsType) {
		this.adminQuickStatsType = adminQuickStatsType;
	}

	public CCIStaffUser getCcistaffUser() {
		return this.ccistaffUser;
	}

	public void setCcistaffUser(CCIStaffUser ccistaffUser) {
		this.ccistaffUser = ccistaffUser;
	}

	public LookupDepartmentProgram getLookupDepartmentProgram() {
		return this.lookupDepartmentProgram;
	}

	public void setLookupDepartmentProgram(LookupDepartmentProgram lookupDepartmentProgram) {
		this.lookupDepartmentProgram = lookupDepartmentProgram;
	}

}