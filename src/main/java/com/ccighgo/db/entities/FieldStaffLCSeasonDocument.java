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
	private Integer fieldStaffLCSeasonDocumentId;

	private Byte active;

	private Byte approvedByCCI;

	@Temporal(TemporalType.TIMESTAMP)
	private Date approvedDate;

	private Integer createdBy;

	private Timestamp createdOn;

	@Column(length=1000)
	private String description;

	private Timestamp modfiedOn;

	@Column(length=150)
	private String rejectionMessage;

	@Temporal(TemporalType.TIMESTAMP)
	private Date submittedDate;

	private Byte submittedToCCI;

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

	public Integer getFieldStaffLCSeasonDocumentId() {
		return this.fieldStaffLCSeasonDocumentId;
	}

	public void setFieldStaffLCSeasonDocumentId(Integer fieldStaffLCSeasonDocumentId) {
		this.fieldStaffLCSeasonDocumentId = fieldStaffLCSeasonDocumentId;
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

	public FieldStaffLCSeason getFieldStaffLcseason() {
		return this.fieldStaffLcseason;
	}

	public void setFieldStaffLcseason(FieldStaffLCSeason fieldStaffLcseason) {
		this.fieldStaffLcseason = fieldStaffLcseason;
	}

}