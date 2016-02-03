package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the FieldStaffQuickStatsCategoryAggregate database table.
 * 
 */
@Entity
@NamedQuery(name="FieldStaffQuickStatsCategoryAggregate.findAll", query="SELECT f FROM FieldStaffQuickStatsCategoryAggregate f")
public class FieldStaffQuickStatsCategoryAggregate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer fieldStaffQSCategoryAggregateId;

	private Integer fieldStaffQSCategoryAggregate;

	private String fieldStaffQSCategoryName;

	private Timestamp modifiedDate;

	//bi-directional many-to-one association to FieldStaff
	@ManyToOne
	@JoinColumn(name="fieldStaffGoId",insertable=false,updatable=false)
	private FieldStaff fieldStaff;

	//bi-directional many-to-one association to FieldStaffQuickStatsCategory
	@ManyToOne
	@JoinColumn(name="fieldStaffQSCategoryId",insertable=false,updatable=false)
	private FieldStaffQuickStatsCategory fieldStaffQuickStatsCategory;

	//bi-directional many-to-one association to FieldStaffQuickStatsType
	@ManyToOne
	@JoinColumn(name="fieldStaffQSTypeId",insertable=false,updatable=false)
	private FieldStaffQuickStatsType fieldStaffQuickStatsType;

	//bi-directional many-to-one association to LookupDepartmentProgram
	@ManyToOne
	@JoinColumn(name="lookupDepartmentProgramId",insertable=false,updatable=false)
	private LookupDepartmentProgram lookupDepartmentProgram;

	public FieldStaffQuickStatsCategoryAggregate() {
	}

	public Integer getFieldStaffQSCategoryAggregateId() {
		return this.fieldStaffQSCategoryAggregateId;
	}

	public void setFieldStaffQSCategoryAggregateId(Integer fieldStaffQSCategoryAggregateId) {
		this.fieldStaffQSCategoryAggregateId = fieldStaffQSCategoryAggregateId;
	}

	public Integer getFieldStaffQSCategoryAggregate() {
		return this.fieldStaffQSCategoryAggregate;
	}

	public void setFieldStaffQSCategoryAggregate(Integer fieldStaffQSCategoryAggregate) {
		this.fieldStaffQSCategoryAggregate = fieldStaffQSCategoryAggregate;
	}

	public String getFieldStaffQSCategoryName() {
		return this.fieldStaffQSCategoryName;
	}

	public void setFieldStaffQSCategoryName(String fieldStaffQSCategoryName) {
		this.fieldStaffQSCategoryName = fieldStaffQSCategoryName;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public FieldStaff getFieldStaff() {
		return this.fieldStaff;
	}

	public void setFieldStaff(FieldStaff fieldStaff) {
		this.fieldStaff = fieldStaff;
	}

	public FieldStaffQuickStatsCategory getFieldStaffQuickStatsCategory() {
		return this.fieldStaffQuickStatsCategory;
	}

	public void setFieldStaffQuickStatsCategory(FieldStaffQuickStatsCategory fieldStaffQuickStatsCategory) {
		this.fieldStaffQuickStatsCategory = fieldStaffQuickStatsCategory;
	}

	public FieldStaffQuickStatsType getFieldStaffQuickStatsType() {
		return this.fieldStaffQuickStatsType;
	}

	public void setFieldStaffQuickStatsType(FieldStaffQuickStatsType fieldStaffQuickStatsType) {
		this.fieldStaffQuickStatsType = fieldStaffQuickStatsType;
	}

	public LookupDepartmentProgram getLookupDepartmentProgram() {
		return this.lookupDepartmentProgram;
	}

	public void setLookupDepartmentProgram(LookupDepartmentProgram lookupDepartmentProgram) {
		this.lookupDepartmentProgram = lookupDepartmentProgram;
	}

}