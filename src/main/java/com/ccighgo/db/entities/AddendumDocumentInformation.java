package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the AddendumDocumentInformation database table.
 * 
 */
@Entity
@NamedQuery(name="AddendumDocumentInformation.findAll", query="SELECT a FROM AddendumDocumentInformation a")
public class AddendumDocumentInformation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer addendumDocumentInformationId;

	private Byte active;

	private Integer createdBy;

	private Timestamp createdOn;

	private String documentName;

	private String fileName;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;

	private String url;

	//bi-directional many-to-one association to DocumentInformation
	@ManyToOne
	@JoinColumn(name="documentInformationId")
	private DocumentInformation documentInformation;

	public AddendumDocumentInformation() {
	}

	public Integer getAddendumDocumentInformationId() {
		return this.addendumDocumentInformationId;
	}

	public void setAddendumDocumentInformationId(Integer addendumDocumentInformationId) {
		this.addendumDocumentInformationId = addendumDocumentInformationId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
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

	public String getDocumentName() {
		return this.documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public DocumentInformation getDocumentInformation() {
		return this.documentInformation;
	}

	public void setDocumentInformation(DocumentInformation documentInformation) {
		this.documentInformation = documentInformation;
	}

}