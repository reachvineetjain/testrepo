package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cci_program database table.
 * 
 */
@Entity
@Table(name="cci_program")
@NamedQuery(name="CciProgram.findAll", query="SELECT c FROM CciProgram c")
public class CciProgram implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int programID;

	@Column(length=1)
	private String active;

	@Column(length=50)
	private String programName;

	private int sequenceNo;

	private byte[] stamp;

	//bi-directional many-to-one association to CciDepartment
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="departmentID")
	private CciDepartments cciDepartment;

	//bi-directional many-to-one association to CciSeasonProgram
	@OneToMany(mappedBy="cciProgram")
	private List<CciSeasonProgram> cciSeasonPrograms;

	public CciProgram() {
	}

	public int getProgramID() {
		return this.programID;
	}

	public void setProgramID(int programID) {
		this.programID = programID;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getProgramName() {
		return this.programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public int getSequenceNo() {
		return this.sequenceNo;
	}

	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public byte[] getStamp() {
		return this.stamp;
	}

	public void setStamp(byte[] stamp) {
		this.stamp = stamp;
	}

	public CciDepartments getCciDepartment() {
		return this.cciDepartment;
	}

	public void setCciDepartment(CciDepartments cciDepartment) {
		this.cciDepartment = cciDepartment;
	}

	public List<CciSeasonProgram> getCciSeasonPrograms() {
		return this.cciSeasonPrograms;
	}

	public void setCciSeasonPrograms(List<CciSeasonProgram> cciSeasonPrograms) {
		this.cciSeasonPrograms = cciSeasonPrograms;
	}

	public CciSeasonProgram addCciSeasonProgram(CciSeasonProgram cciSeasonProgram) {
		getCciSeasonPrograms().add(cciSeasonProgram);
		cciSeasonProgram.setCciProgram(this);

		return cciSeasonProgram;
	}

	public CciSeasonProgram removeCciSeasonProgram(CciSeasonProgram cciSeasonProgram) {
		getCciSeasonPrograms().remove(cciSeasonProgram);
		cciSeasonProgram.setCciProgram(null);

		return cciSeasonProgram;
	}

}