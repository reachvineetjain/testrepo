package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the HostFamilyDocument database table.
 * 
 */
@Entity
@NamedQuery(name="HostFamilyDocument.findAll", query="SELECT h FROM HostFamilyDocument h")
public class HostFamilyDocument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer hostFamilyDocumentId;

	private Byte approvedByCCI;

	private String description;

	private Byte rejectedByCCI;

	private Byte submittedToCCI;

	//bi-directional many-to-one association to DocumentInformation
	@ManyToOne
	@JoinColumn(name="documentInformationId")
	private DocumentInformation documentInformation;

	//bi-directional many-to-one association to HostFamilySeason
	@ManyToOne
	@JoinColumn(name="hostFamilySeasonId")
	private HostFamilySeason hostFamilySeason;

	public HostFamilyDocument() {
	}

	public Integer getHostFamilyDocumentId() {
		return this.hostFamilyDocumentId;
	}

	public void setHostFamilyDocumentId(Integer hostFamilyDocumentId) {
		this.hostFamilyDocumentId = hostFamilyDocumentId;
	}

	public Byte getApprovedByCCI() {
		return this.approvedByCCI;
	}

	public void setApprovedByCCI(Byte approvedByCCI) {
		this.approvedByCCI = approvedByCCI;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Byte getRejectedByCCI() {
		return this.rejectedByCCI;
	}

	public void setRejectedByCCI(Byte rejectedByCCI) {
		this.rejectedByCCI = rejectedByCCI;
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

	public HostFamilySeason getHostFamilySeason() {
		return this.hostFamilySeason;
	}

	public void setHostFamilySeason(HostFamilySeason hostFamilySeason) {
		this.hostFamilySeason = hostFamilySeason;
	}

}