package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the PartnerSeasonDocument database table.
 * 
 */
@Entity
@Table(name="PartnerSeasonDocument")
@NamedQuery(name="PartnerSeasonDocument.findAll", query="SELECT p FROM PartnerSeasonDocument p")
public class PartnerSeasonDocument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer partnerSeasonDocumentId;

	@Column(nullable=false)
	private byte active;

	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@Lob
	private String description;

	private Integer modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	@JoinColumn(name="partnerGoId")
	private Partner partner;

	//bi-directional many-to-one association to SeasonProgramDocument
	@ManyToOne
	@JoinColumn(name="seasonProgramDocumentId")
	private SeasonProgramDocument seasonProgramDocument;

	//bi-directional many-to-one association to DocumentInformation
	@ManyToOne
	@JoinColumn(name="documentInformationId")
	private DocumentInformation documentInformation;

	//bi-directional many-to-one association to PartnerSeason
	@ManyToOne
	@JoinColumn(name="partnerSeasonId")
	private PartnerSeason partnerSeason;

	public PartnerSeasonDocument() {
	}

	public Integer getPartnerSeasonDocumentId() {
		return this.partnerSeasonDocumentId;
	}

	public void setPartnerSeasonDocumentId(Integer partnerSeasonDocumentId) {
		this.partnerSeasonDocumentId = partnerSeasonDocumentId;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Partner getPartner() {
		return this.partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public SeasonProgramDocument getSeasonProgramDocument() {
		return this.seasonProgramDocument;
	}

	public void setSeasonProgramDocument(SeasonProgramDocument seasonProgramDocument) {
		this.seasonProgramDocument = seasonProgramDocument;
	}

	public DocumentInformation getDocumentInformation() {
		return this.documentInformation;
	}

	public void setDocumentInformation(DocumentInformation documentInformation) {
		this.documentInformation = documentInformation;
	}

	public PartnerSeason getPartnerSeason() {
		return this.partnerSeason;
	}

	public void setPartnerSeason(PartnerSeason partnerSeason) {
		this.partnerSeason = partnerSeason;
	}

}