package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the FieldStaffWorkQueueCategoryAggregate database table.
 * 
 */
@Entity
@Table(name="FieldStaffWorkQueueCategoryAggregate")
@NamedQuery(name="FieldStaffWorkQueueCategoryAggregate.findAll", query="SELECT f FROM FieldStaffWorkQueueCategoryAggregate f")
public class FieldStaffWorkQueueCategoryAggregate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer fieldStaffWQCategoryAggregateId;

	private Integer fieldStaffWQCategoryAggregate;

	@Column(length=50)
	private String fieldStaffWQCategoryName;

	@Column(nullable=false)
	private Timestamp modifiedDate;

	//bi-directional many-to-one association to FieldStaff
	@ManyToOne
	@JoinColumn(name="fieldStaffGoId")
	private FieldStaff fieldStaff;

	//bi-directional many-to-one association to FieldStaffWorkQueueCategory
	@ManyToOne
	@JoinColumn(name="fieldStaffWQCategoryId")
	private FieldStaffWorkQueueCategory fieldStaffWorkQueueCategory;

	//bi-directional many-to-one association to FieldStaffWorkQueueType
	@ManyToOne
	@JoinColumn(name="fieldStaffWQTypeId")
	private FieldStaffWorkQueueType fieldStaffWorkQueueType;

	//bi-directional many-to-one association to LookupDepartmentProgram
	@ManyToOne
	@JoinColumn(name="lookupDepartmentProgramId")
	private LookupDepartmentProgram lookupDepartmentProgram;

	public FieldStaffWorkQueueCategoryAggregate() {
	}

	public Integer getFieldStaffWQCategoryAggregateId() {
		return this.fieldStaffWQCategoryAggregateId;
	}

	public void setFieldStaffWQCategoryAggregateId(Integer fieldStaffWQCategoryAggregateId) {
		this.fieldStaffWQCategoryAggregateId = fieldStaffWQCategoryAggregateId;
	}

	public Integer getFieldStaffWQCategoryAggregate() {
		return this.fieldStaffWQCategoryAggregate;
	}

	public void setFieldStaffWQCategoryAggregate(Integer fieldStaffWQCategoryAggregate) {
		this.fieldStaffWQCategoryAggregate = fieldStaffWQCategoryAggregate;
	}

	public String getFieldStaffWQCategoryName() {
		return this.fieldStaffWQCategoryName;
	}

	public void setFieldStaffWQCategoryName(String fieldStaffWQCategoryName) {
		this.fieldStaffWQCategoryName = fieldStaffWQCategoryName;
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

	public FieldStaffWorkQueueCategory getFieldStaffWorkQueueCategory() {
		return this.fieldStaffWorkQueueCategory;
	}

	public void setFieldStaffWorkQueueCategory(FieldStaffWorkQueueCategory fieldStaffWorkQueueCategory) {
		this.fieldStaffWorkQueueCategory = fieldStaffWorkQueueCategory;
	}

	public FieldStaffWorkQueueType getFieldStaffWorkQueueType() {
		return this.fieldStaffWorkQueueType;
	}

	public void setFieldStaffWorkQueueType(FieldStaffWorkQueueType fieldStaffWorkQueueType) {
		this.fieldStaffWorkQueueType = fieldStaffWorkQueueType;
	}

	public LookupDepartmentProgram getLookupDepartmentProgram() {
		return this.lookupDepartmentProgram;
	}

	public void setLookupDepartmentProgram(LookupDepartmentProgram lookupDepartmentProgram) {
		this.lookupDepartmentProgram = lookupDepartmentProgram;
	}

}