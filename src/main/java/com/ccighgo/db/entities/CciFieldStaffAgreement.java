package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the cci_field_staff_agreement database table.
 * 
 */
@Entity
@Table(name="cci_field_staff_agreement")
@NamedQuery(name="CciFieldStaffAgreement.findAll", query="SELECT c FROM CciFieldStaffAgreement c")
public class CciFieldStaffAgreement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int fieldStaffAgreementID;

	@Column(length=1)
	private String active;

	private String agreementHTML;

	@Column(length=50)
	private String agreementName;

	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	private byte[] stamp;

	//bi-directional many-to-one association to CciSeasonProgram
	@OneToMany(mappedBy="cciFieldStaffAgreement")
	private List<CciSeasonProgram> cciSeasonPrograms;

	public CciFieldStaffAgreement() {
	}

	public int getFieldStaffAgreementID() {
		return this.fieldStaffAgreementID;
	}

	public void setFieldStaffAgreementID(int fieldStaffAgreementID) {
		this.fieldStaffAgreementID = fieldStaffAgreementID;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getAgreementHTML() {
		return this.agreementHTML;
	}

	public void setAgreementHTML(String agreementHTML) {
		this.agreementHTML = agreementHTML;
	}

	public String getAgreementName() {
		return this.agreementName;
	}

	public void setAgreementName(String agreementName) {
		this.agreementName = agreementName;
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

	public byte[] getStamp() {
		return this.stamp;
	}

	public void setStamp(byte[] stamp) {
		this.stamp = stamp;
	}

	public List<CciSeasonProgram> getCciSeasonPrograms() {
		return this.cciSeasonPrograms;
	}

	public void setCciSeasonPrograms(List<CciSeasonProgram> cciSeasonPrograms) {
		this.cciSeasonPrograms = cciSeasonPrograms;
	}

	public CciSeasonProgram addCciSeasonProgram(CciSeasonProgram cciSeasonProgram) {
		getCciSeasonPrograms().add(cciSeasonProgram);
		cciSeasonProgram.setCciFieldStaffAgreement(this);

		return cciSeasonProgram;
	}

	public CciSeasonProgram removeCciSeasonProgram(CciSeasonProgram cciSeasonProgram) {
		getCciSeasonPrograms().remove(cciSeasonProgram);
		cciSeasonProgram.setCciFieldStaffAgreement(null);

		return cciSeasonProgram;
	}

}