package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cci_departmental_functions database table.
 * 
 */
@Entity
@Table(name="cci_departmental_functions")
@NamedQuery(name="CciDepartmentalFunction.findAll", query="SELECT c FROM CciDepartmentalFunction c")
public class CciDepartmentalFunction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=200)
	private String functionDescription;

	@Column(length=100)
	private String functionName;

	//bi-directional many-to-one association to CciDepartment
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="departmentID", nullable=false)
	private CciDepartments cciDepartment;

	public CciDepartmentalFunction() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public CciDepartments getCciDepartment() {
		return this.cciDepartment;
	}

	public void setCciDepartment(CciDepartments cciDepartment) {
		this.cciDepartment = cciDepartment;
	}

}