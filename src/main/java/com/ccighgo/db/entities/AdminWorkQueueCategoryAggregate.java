package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the AdminWorkQueueCategoryAggregate database table.
 * 
 */
@Entity
@Table(name="AdminWorkQueueCategoryAggregate")
@NamedQuery(name="AdminWorkQueueCategoryAggregate.findAll", query="SELECT a FROM AdminWorkQueueCategoryAggregate a")
public class AdminWorkQueueCategoryAggregate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer adminWQCategoryAggregateId;

	private Integer adminWQCategoryAggregate;

	@Column(length=50)
	private String adminWQCategoryName;

	@Column(nullable=false)
	private Timestamp modifiedDate;

	//bi-directional many-to-one association to AdminWorkQueueCategory
	@ManyToOne
	@JoinColumn(name="adminWQCategoryId")
	private AdminWorkQueueCategory adminWorkQueueCategory;

	//bi-directional many-to-one association to AdminWorkQueueType
	@ManyToOne
	@JoinColumn(name="adminWQTypeId")
	private AdminWorkQueueType adminWorkQueueType;

	//bi-directional many-to-one association to CCIStaffUser
	@ManyToOne
	@JoinColumn(name="adminGoId")
	private CCIStaffUser ccistaffUser;

	//bi-directional many-to-one association to LookupDepartmentProgram
	@ManyToOne
	@JoinColumn(name="lookupDepartmentProgramId")
	private LookupDepartmentProgram lookupDepartmentProgram;

	public AdminWorkQueueCategoryAggregate() {
	}

	public Integer getAdminWQCategoryAggregateId() {
		return this.adminWQCategoryAggregateId;
	}

	public void setAdminWQCategoryAggregateId(Integer adminWQCategoryAggregateId) {
		this.adminWQCategoryAggregateId = adminWQCategoryAggregateId;
	}

	public Integer getAdminWQCategoryAggregate() {
		return this.adminWQCategoryAggregate;
	}

	public void setAdminWQCategoryAggregate(Integer adminWQCategoryAggregate) {
		this.adminWQCategoryAggregate = adminWQCategoryAggregate;
	}

	public String getAdminWQCategoryName() {
		return this.adminWQCategoryName;
	}

	public void setAdminWQCategoryName(String adminWQCategoryName) {
		this.adminWQCategoryName = adminWQCategoryName;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public AdminWorkQueueCategory getAdminWorkQueueCategory() {
		return this.adminWorkQueueCategory;
	}

	public void setAdminWorkQueueCategory(AdminWorkQueueCategory adminWorkQueueCategory) {
		this.adminWorkQueueCategory = adminWorkQueueCategory;
	}

	public AdminWorkQueueType getAdminWorkQueueType() {
		return this.adminWorkQueueType;
	}

	public void setAdminWorkQueueType(AdminWorkQueueType adminWorkQueueType) {
		this.adminWorkQueueType = adminWorkQueueType;
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