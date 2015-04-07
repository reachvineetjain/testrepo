package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cci_department_programs database table.
 * 
 */
@Entity
@Table(name="cci_department_programs")
@NamedQuery(name="CciDepartmentProgram.findAll", query="SELECT c FROM CciDepartmentProgram c")
public class CciDepartmentProgram implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=100)
	private String description;

	@Column(nullable=false, length=50)
	private String program;

	//bi-directional many-to-one association to CciDepartment
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="departmentID", nullable=false)
	private CciDepartments cciDepartment;

	public CciDepartmentProgram() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProgram() {
		return this.program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public CciDepartments getCciDepartment() {
		return this.cciDepartment;
	}

	public void setCciDepartment(CciDepartments cciDepartment) {
		this.cciDepartment = cciDepartment;
	}

}