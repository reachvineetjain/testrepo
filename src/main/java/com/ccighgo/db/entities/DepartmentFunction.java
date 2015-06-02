package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the departmentfunctions database table.
 * 
 */
@Entity
@Table(name="departmentfunctions")
@NamedQuery(name="DepartmentFunction.findAll", query="SELECT d FROM DepartmentFunction d")
public class DepartmentFunction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int deptFunctionID;

	@Column(nullable=false)
	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(length=200)
	private String functionDescription;

	@Column(length=100)
	private String functionName;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	//bi-directional many-to-one association to Departments
	@ManyToOne
	@JoinColumn(name="departmentID", nullable=false)
	private Departments department;

	public DepartmentFunction() {
	}

	public int getDeptFunctionID() {
		return this.deptFunctionID;
	}

	public void setDeptFunctionID(int deptFunctionID) {
		this.deptFunctionID = deptFunctionID;
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

	public Departments getDepartment() {
		return this.department;
	}

	public void setDepartment(Departments department) {
		this.department = department;
	}

}