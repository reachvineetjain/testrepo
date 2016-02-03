package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the FieldStaffSeasonDocument database table.
 * 
 */
@Entity
@NamedQuery(name="FieldStaffSeasonDocument.findAll", query="SELECT f FROM FieldStaffSeasonDocument f")
public class FieldStaffSeasonDocument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer fieldStaffSeasonDocumentId;

	private Byte active;

	private Byte approvedByCCI;

	@Temporal(TemporalType.TIMESTAMP)
	private Date approvedDate;

	private Integer createdBy;

	private Timestamp createdOn;

	private String description;

	private Timestamp modfiedOn;

	private String rejectionMessage;

	@Temporal(TemporalType.TIMESTAMP)
	private Date submittedDate;

	private Byte submittedToCCI;

	//bi-directional many-to-one association to DocumentInformation
	@ManyToOne
	@JoinColumn(name="documentInformationId")
	private DocumentInformation documentInformation;

	//bi-directional many-to-one association to FieldStaffSeason
	@ManyToOne
	@JoinColumn(name="fieldStaffSeasonId")
	private FieldStaffSeason fieldStaffSeason;

	public FieldStaffSeasonDocument() {
	}

	public Integer getFieldStaffSeasonDocumentId() {
		return this.fieldStaffSeasonDocumentId;
	}

	public void setFieldStaffSeasonDocumentId(Integer fieldStaffSeasonDocumentId) {
		this.fieldStaffSeasonDocumentId = fieldStaffSeasonDocumentId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public Byte getApprovedByCCI() {
		return this.approvedByCCI;
	}

	public void setApprovedByCCI(Byte approvedByCCI) {
		this.approvedByCCI = approvedByCCI;
	}

	public Date getApprovedDate() {
		return this.approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getModfiedOn() {
		return this.modfiedOn;
	}

	public void setModfiedOn(Timestamp modfiedOn) {
		this.modfiedOn = modfiedOn;
	}

	public String getRejectionMessage() {
		return this.rejectionMessage;
	}

	public void setRejectionMessage(String rejectionMessage) {
		this.rejectionMessage = rejectionMessage;
	}

	public Date getSubmittedDate() {
		return this.submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public Byte getSubmittedToCCI() {
		return this.submittedToCCI;
	}

	public void setSubmittedToCCI(Byte submittedToCCI) {
		this.submittedToCCI = submittedToCCI;
	}

	public DocumentInformation getDocumentInformation() {
		return this.documentInformation;
	}

	public void setDocumentInformation(DocumentInformation documentInformation) {
		this.documentInformation = documentInformation;
	}

	public FieldStaffSeason getFieldStaffSeason() {
		return this.fieldStaffSeason;
	}

	public void setFieldStaffSeason(FieldStaffSeason fieldStaffSeason) {
		this.fieldStaffSeason = fieldStaffSeason;
	}

}