package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PartnerDocument database table.
 * 
 */
@Entity
@Table(name="PartnerDocument")
@NamedQuery(name="PartnerDocument.findAll", query="SELECT p FROM PartnerDocument p")
public class PartnerDocument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer partnerDocumentId;

	//bi-directional many-to-one association to DocumentInformation
	@ManyToOne
	@JoinColumn(name="documentInformationId")
	private DocumentInformation documentInformation;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	@JoinColumn(name="partnerGoId")
	private Partner partner;

	//bi-directional many-to-one association to PartnerProgram
	@ManyToOne
	@JoinColumn(name="partnerProgramId")
	private PartnerProgram partnerProgram;

	public PartnerDocument() {
	}

	public Integer getPartnerDocumentId() {
		return this.partnerDocumentId;
	}

	public void setPartnerDocumentId(Integer partnerDocumentId) {
		this.partnerDocumentId = partnerDocumentId;
	}

	public DocumentInformation getDocumentInformation() {
		return this.documentInformation;
	}

	public void setDocumentInformation(DocumentInformation documentInformation) {
		this.documentInformation = documentInformation;
	}

	public Partner getPartner() {
		return this.partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public PartnerProgram getPartnerProgram() {
		return this.partnerProgram;
	}

	public void setPartnerProgram(PartnerProgram partnerProgram) {
		this.partnerProgram = partnerProgram;
	}

}