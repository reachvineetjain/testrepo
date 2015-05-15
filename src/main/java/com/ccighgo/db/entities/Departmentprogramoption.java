package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the departmentprogramoptions database table.
 * 
 */
@Entity
@Table(name="departmentprogramoptions")
@NamedQuery(name="Departmentprogramoption.findAll", query="SELECT d FROM Departmentprogramoption d")
public class Departmentprogramoption implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=50)
	private String programOption;

	@Column(nullable=false, length=10)
	private String programOptionCode;

	//bi-directional many-to-one association to Departmentprogram
	@ManyToOne
	@JoinColumn(name="programID", nullable=false)
	private Departmentprogram departmentprogram;

	public Departmentprogramoption() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProgramOption() {
		return this.programOption;
	}

	public void setProgramOption(String programOption) {
		this.programOption = programOption;
	}

	public String getProgramOptionCode() {
		return this.programOptionCode;
	}

	public void setProgramOptionCode(String programOptionCode) {
		this.programOptionCode = programOptionCode;
	}

	public Departmentprogram getDepartmentprogram() {
		return this.departmentprogram;
	}

	public void setDepartmentprogram(Departmentprogram departmentprogram) {
		this.departmentprogram = departmentprogram;
	}

}