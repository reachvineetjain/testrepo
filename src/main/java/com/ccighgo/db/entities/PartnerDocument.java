package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PartnerDocument database table.
 * 
 */
@Entity
@NamedQuery(name="PartnerDocument.findAll", query="SELECT p FROM PartnerDocument p")
public class PartnerDocument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer partnerDocumentId;

	@Lob
	private String description;

	//bi-directional many-to-one association to DocumentInformation
	@ManyToOne
	@JoinColumn(name="documentInformationId",insertable=false,updatable=false)
	private DocumentInformation documentInformation;

	//bi-directional many-to-one association to Partner
	@ManyToOne
	@JoinColumn(name="partnerGoId",insertable=false,updatable=false)
	private Partner partner;

	//bi-directional many-to-one association to PartnerProgram
	@ManyToOne
	@JoinColumn(name="partnerProgramId",insertable=false,updatable=false)
	private PartnerProgram partnerProgram;

	public PartnerDocument() {
	}

	public Integer getPartnerDocumentId() {
		return this.partnerDocumentId;
	}

	public void setPartnerDocumentId(Integer partnerDocumentId) {
		this.partnerDocumentId = partnerDocumentId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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