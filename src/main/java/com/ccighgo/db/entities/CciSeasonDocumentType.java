package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cci_season_document_type database table.
 * 
 */
@Entity
@Table(name="cci_season_document_type")
@NamedQuery(name="CciSeasonDocumentType.findAll", query="SELECT c FROM CciSeasonDocumentType c")
public class CciSeasonDocumentType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int seasonDocumentTypeID;

	@Column(nullable=false, length=1)
	private String active;

	@Column(nullable=false, length=100)
	private String seasonDocumentTypeName;

	@Column(nullable=false)
	private int sequenceNo;

	//bi-directional many-to-one association to CciSeasonDocument
	@OneToMany(mappedBy="cciSeasonDocumentType")
	private List<CciSeasonDocument> cciSeasonDocuments;

	public CciSeasonDocumentType() {
	}

	public int getSeasonDocumentTypeID() {
		return this.seasonDocumentTypeID;
	}

	public void setSeasonDocumentTypeID(int seasonDocumentTypeID) {
		this.seasonDocumentTypeID = seasonDocumentTypeID;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getSeasonDocumentTypeName() {
		return this.seasonDocumentTypeName;
	}

	public void setSeasonDocumentTypeName(String seasonDocumentTypeName) {
		this.seasonDocumentTypeName = seasonDocumentTypeName;
	}

	public int getSequenceNo() {
		return this.sequenceNo;
	}

	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public List<CciSeasonDocument> getCciSeasonDocuments() {
		return this.cciSeasonDocuments;
	}

	public void setCciSeasonDocuments(List<CciSeasonDocument> cciSeasonDocuments) {
		this.cciSeasonDocuments = cciSeasonDocuments;
	}

	public CciSeasonDocument addCciSeasonDocument(CciSeasonDocument cciSeasonDocument) {
		getCciSeasonDocuments().add(cciSeasonDocument);
		cciSeasonDocument.setCciSeasonDocumentType(this);

		return cciSeasonDocument;
	}

	public CciSeasonDocument removeCciSeasonDocument(CciSeasonDocument cciSeasonDocument) {
		getCciSeasonDocuments().remove(cciSeasonDocument);
		cciSeasonDocument.setCciSeasonDocumentType(null);

		return cciSeasonDocument;
	}

}