package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the departmentprograms database table.
 * 
 */
@Entity
@Table(name="departmentprograms")
@NamedQuery(name="Departmentprogram.findAll", query="SELECT d FROM Departmentprogram d")
public class Departmentprogram implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false)
	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(length=100)
	private String description;

	@Column(nullable=false)
	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	@Column(nullable=false, length=50)
	private String program;

	//bi-directional many-to-one association to Departmentprogramoption
	@OneToMany(mappedBy="departmentprogram")
	private List<Departmentprogramoption> departmentprogramoptions;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="departmentID", nullable=false)
	private Department department;

	public Departmentprogram() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getProgram() {
		return this.program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public List<Departmentprogramoption> getDepartmentprogramoptions() {
		return this.departmentprogramoptions;
	}

	public void setDepartmentprogramoptions(List<Departmentprogramoption> departmentprogramoptions) {
		this.departmentprogramoptions = departmentprogramoptions;
	}

	public Departmentprogramoption addDepartmentprogramoption(Departmentprogramoption departmentprogramoption) {
		getDepartmentprogramoptions().add(departmentprogramoption);
		departmentprogramoption.setDepartmentprogram(this);

		return departmentprogramoption;
	}

	public Departmentprogramoption removeDepartmentprogramoption(Departmentprogramoption departmentprogramoption) {
		getDepartmentprogramoptions().remove(departmentprogramoption);
		departmentprogramoption.setDepartmentprogram(null);

		return departmentprogramoption;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}