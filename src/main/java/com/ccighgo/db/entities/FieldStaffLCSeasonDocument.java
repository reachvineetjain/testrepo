package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the FieldStaffLCSeasonDocument database table.
 * 
 */
@Entity
@Table(name="FieldStaffLCSeasonDocument")
@NamedQuery(name="FieldStaffLCSeasonDocument.findAll", query="SELECT f FROM FieldStaffLCSeasonDocument f")
public class FieldStaffLCSeasonDocument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int fieldStaffLCSeasonDocumentId;

	private byte active;

	private byte approvedByCCI;

	@Temporal(TemporalType.TIMESTAMP)
	private Date approvedDate;

	private int createdBy;

	private Timestamp createdOn;

	@Column(length=1000)
	private String description;

	private Timestamp modfiedOn;

	@Column(length=150)
	private String rejectionMessage;

	@Temporal(TemporalType.TIMESTAMP)
	private Date submittedDate;

	private byte submittedToCCI;

	//bi-directional many-to-one association to DocumentInformation
	@ManyToOne
	@JoinColumn(name="documentInformationId")
	private DocumentInformation documentInformation;

	//bi-directional many-to-one association to FieldStaffLCSeason
	@ManyToOne
	@JoinColumn(name="fieldStaffLCSeasonId")
	private FieldStaffLCSeason fieldStaffLcseason;

	public FieldStaffLCSeasonDocument() {
	}

	public int getFieldStaffLCSeasonDocumentId() {
		return this.fieldStaffLCSeasonDocumentId;
	}

	public void setFieldStaffLCSeasonDocumentId(int fieldStaffLCSeasonDocumentId) {
		this.fieldStaffLCSeasonDocumentId = fieldStaffLCSeasonDocumentId;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public byte getApprovedByCCI() {
		return this.approvedByCCI;
	}

	public void setApprovedByCCI(byte approvedByCCI) {
		this.approvedByCCI = approvedByCCI;
	}

	public Date getApprovedDate() {
		return this.approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
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

	public byte getSubmittedToCCI() {
		return this.submittedToCCI;
	}

	public void setSubmittedToCCI(byte submittedToCCI) {
		this.submittedToCCI = submittedToCCI;
	}

	public DocumentInformation getDocumentInformation() {
		return this.documentInformation;
	}

	public void setDocumentInformation(DocumentInformation documentInformation) {
		this.documentInformation = documentInformation;
	}

	public FieldStaffLCSeason getFieldStaffLcseason() {
		return this.fieldStaffLcseason;
	}

	public void setFieldStaffLcseason(FieldStaffLCSeason fieldStaffLcseason) {
		this.fieldStaffLcseason = fieldStaffLcseason;
	}

}