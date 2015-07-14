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
	@Column(unique=true, nullable=false)
	private int deptFunctionId;

	@Column(nullable=false)
	private int createdBy;

	private Timestamp createdOn;

	@Column(nullable=false)
	private int departmentId;

	@Column(length=200)
	private String functionDescription;

	@Column(length=100)
	private String functionName;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	public DepartmentFunction() {
	}

	public int getDeptFunctionId() {
		return this.deptFunctionId;
	}

	public void setDeptFunctionId(int deptFunctionId) {
		this.deptFunctionId = deptFunctionId;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public int getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(int departmentId) {
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

	public int getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

}