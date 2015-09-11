package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


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

	private byte active;

	private Integer createdBy;

	private Timestamp createdOn;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	@JoinColumn(name="partnerGoId")
	private Partner partner;

	//bi-directional many-to-one association to SeasonProgramDocument
	@ManyToOne
	@JoinColumn(name="seasonProgramDocumentId")
	private SeasonProgramDocument seasonProgramDocument;

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

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
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

}