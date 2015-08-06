package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the DepartmentFunctions database table.
 * 
 */
@Entity
@Table(name="DepartmentFunctions")
@NamedQuery(name="DepartmentFunction.findAll", query="SELECT d FROM DepartmentFunction d")
public class DepartmentFunction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer deptFunctionId;

	private Integer createdBy;

	private Timestamp createdOn;

	private Integer departmentId;

	private String functionDescription;

	private String functionName;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	public DepartmentFunction() {
	}

	public Integer getDeptFunctionId() {
		return this.deptFunctionId;
	}

	public void setDeptFunctionId(Integer deptFunctionId) {
		this.deptFunctionId = deptFunctionId;
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

	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getFunctionDescription() {
		return this.functionDescription;
	}

	public void setFunctionDescription(String functionDescription) {
		this.functionDescription = functionDescription;
	}

	public String getFunctionName() {
		return this.functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
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

}