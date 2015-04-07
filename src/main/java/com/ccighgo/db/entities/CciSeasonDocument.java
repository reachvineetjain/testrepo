package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the cci_season_document database table.
 * 
 */
@Entity
@Table(name="cci_season_document")
@NamedQuery(name="CciSeasonDocument.findAll", query="SELECT c FROM CciSeasonDocument c")
public class CciSeasonDocument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int seasonDocumentID;

	@Column(length=1)
	private String active;

	private int createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(length=100)
	private String documentName;

	@Column(length=200)
	private String documentURL;

	@Column(length=100)
	private String fileName;

	@Column(length=100)
	private String filePath;

	private int modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	@Column(length=64)
	private String seasonDocumentGuid;

	private int sequenceNo;

	private byte[] stamp;

	//bi-directional many-to-one association to CciSeasonDocumentType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="seasonDocumentTypeID")
	private CciSeasonDocumentType cciSeasonDocumentType;

	//bi-directional many-to-one association to CciSeasonProgram
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="seasonProgramID")
	private CciSeasonProgram cciSeasonProgram;

	public CciSeasonDocument() {
	}

	public int getSeasonDocumentID() {
		return this.seasonDocumentID;
	}

	public void setSeasonDocumentID(int seasonDocumentID) {
		this.seasonDocumentID = seasonDocumentID;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
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

	public String getDocumentName() {
		return this.documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentURL() {
		return this.documentURL;
	}

	public void setDocumentURL(String documentURL) {
		this.documentURL = documentURL;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
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

	public String getSeasonDocumentGuid() {
		return this.seasonDocumentGuid;
	}

	public void setSeasonDocumentGuid(String seasonDocumentGuid) {
		this.seasonDocumentGuid = seasonDocumentGuid;
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

	public CciSeasonDocumentType getCciSeasonDocumentType() {
		return this.cciSeasonDocumentType;
	}

	public void setCciSeasonDocumentType(CciSeasonDocumentType cciSeasonDocumentType) {
		this.cciSeasonDocumentType = cciSeasonDocumentType;
	}

	public CciSeasonProgram getCciSeasonProgram() {
		return this.cciSeasonProgram;
	}

	public void setCciSeasonProgram(CciSeasonProgram cciSeasonProgram) {
		this.cciSeasonProgram = cciSeasonProgram;
	}

}