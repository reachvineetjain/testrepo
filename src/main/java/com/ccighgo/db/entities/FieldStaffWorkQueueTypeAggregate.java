package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the FieldStaffWorkQueueTypeAggregate database table.
 * 
 */
@Entity
@NamedQuery(name="FieldStaffWorkQueueTypeAggregate.findAll", query="SELECT f FROM FieldStaffWorkQueueTypeAggregate f")
public class FieldStaffWorkQueueTypeAggregate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer fieldStaffWQTypeAggregateId;

	private Integer fieldStaffWQTypeAggregate;

	private String fieldStaffWQTypeName;

	private Timestamp modifiedDate;

	//bi-directional many-to-one association to FieldStaff
	@ManyToOne
	@JoinColumn(name="fieldStaffGoId")
	private FieldStaff fieldStaff;

	//bi-directional many-to-one association to FieldStaffWorkQueueType
	@ManyToOne
	@JoinColumn(name="fieldStaffWQTypeId")
	private FieldStaffWorkQueueType fieldStaffWorkQueueType;

	//bi-directional many-to-one association to LookupDepartmentProgram
	@ManyToOne
	@JoinColumn(name="lookupDepartmentProgramId")
	private LookupDepartmentProgram lookupDepartmentProgram;

	public FieldStaffWorkQueueTypeAggregate() {
	}

	public Integer getFieldStaffWQTypeAggregateId() {
		return this.fieldStaffWQTypeAggregateId;
	}

	public void setFieldStaffWQTypeAggregateId(Integer fieldStaffWQTypeAggregateId) {
		this.fieldStaffWQTypeAggregateId = fieldStaffWQTypeAggregateId;
	}

	public Integer getFieldStaffWQTypeAggregate() {
		return this.fieldStaffWQTypeAggregate;
	}

	public void setFieldStaffWQTypeAggregate(Integer fieldStaffWQTypeAggregate) {
		this.fieldStaffWQTypeAggregate = fieldStaffWQTypeAggregate;
	}

	public String getFieldStaffWQTypeName() {
		return this.fieldStaffWQTypeName;
	}

	public void setFieldStaffWQTypeName(String fieldStaffWQTypeName) {
		this.fieldStaffWQTypeName = fieldStaffWQTypeName;
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