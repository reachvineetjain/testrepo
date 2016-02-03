package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the FieldStaffQuickStatsTypeAggregate database table.
 * 
 */
@Entity
@NamedQuery(name="FieldStaffQuickStatsTypeAggregate.findAll", query="SELECT f FROM FieldStaffQuickStatsTypeAggregate f")
public class FieldStaffQuickStatsTypeAggregate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer fieldStaffQSTypeAggregateId;

	private Integer fieldStaffQSTypeAggregate;

	private String fieldStaffQSTypeName;

	private Timestamp modifiedDate;

	//bi-directional many-to-one association to FieldStaff
	@ManyToOne
	@JoinColumn(name="fieldStaffGoId",insertable=false,updatable=false)
	private FieldStaff fieldStaff;

	//bi-directional many-to-one association to FieldStaffQuickStatsType
	@ManyToOne
	@JoinColumn(name="fieldStaffQSTypeId",insertable=false,updatable=false)
	private FieldStaffQuickStatsType fieldStaffQuickStatsType;

	//bi-directional many-to-one association to LookupDepartmentProgram
	@ManyToOne
	@JoinColumn(name="lookupDepartmentProgramId",insertable=false,updatable=false)
	private LookupDepartmentProgram lookupDepartmentProgram;

	public FieldStaffQuickStatsTypeAggregate() {
	}

	public Integer getFieldStaffQSTypeAggregateId() {
		return this.fieldStaffQSTypeAggregateId;
	}

	public void setFieldStaffQSTypeAggregateId(Integer fieldStaffQSTypeAggregateId) {
		this.fieldStaffQSTypeAggregateId = fieldStaffQSTypeAggregateId;
	}

	public Integer getFieldStaffQSTypeAggregate() {
		return this.fieldStaffQSTypeAggregate;
	}

	public void setFieldStaffQSTypeAggregate(Integer fieldStaffQSTypeAggregate) {
		this.fieldStaffQSTypeAggregate = fieldStaffQSTypeAggregate;
	}

	public String getFieldStaffQSTypeName() {
		return this.fieldStaffQSTypeName;
	}

	public void setFieldStaffQSTypeName(String fieldStaffQSTypeName) {
		this.fieldStaffQSTypeName = fieldStaffQSTypeName;
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