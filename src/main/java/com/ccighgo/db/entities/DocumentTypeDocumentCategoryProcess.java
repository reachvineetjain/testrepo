package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the DocumentTypeDocumentCategoryProcess database table.
 * 
 */
@Entity
@NamedQuery(name="DocumentTypeDocumentCategoryProcess.findAll", query="SELECT d FROM DocumentTypeDocumentCategoryProcess d")
public class DocumentTypeDocumentCategoryProcess implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer documentTypeDocumentCategoryProcessId;

	private String documentTypeRole;

	//bi-directional many-to-one association to DocumentInformation
	@OneToMany(mappedBy="documentTypeDocumentCategoryProcess")
	private List<DocumentInformation> documentInformations;

	//bi-directional many-to-one association to DocumentCategoryProcess
	@ManyToOne
	@JoinColumn(name="documentCategoryProcessId",insertable=false,updatable=false)
	private DocumentCategoryProcess documentCategoryProcess;

	//bi-directional many-to-one association to DocumentType
	@ManyToOne
	@JoinColumn(name="documentTypeId",insertable=false,updatable=false)
	private DocumentType documentType;

	public DocumentTypeDocumentCategoryProcess() {
	}

	public Integer getDocumentTypeDocumentCategoryProcessId() {
		return this.documentTypeDocumentCategoryProcessId;
	}

	public void setDocumentTypeDocumentCategoryProcessId(Integer documentTypeDocumentCategoryProcessId) {
		this.documentTypeDocumentCategoryProcessId = documentTypeDocumentCategoryProcessId;
	}

	public String getDocumentTypeRole() {
		return this.documentTypeRole;
	}

	public void setDocumentTypeRole(String documentTypeRole) {
		this.documentTypeRole = documentTypeRole;
	}

	public List<DocumentInformation> getDocumentInformations() {
		return this.documentInformations;
	}

	public void setDocumentInformations(List<DocumentInformation> documentInformations) {
		this.documentInformations = documentInformations;
	}

	public DocumentInformation addDocumentInformation(DocumentInformation documentInformation) {
		getDocumentInformations().add(documentInformation);
		documentInformation.setDocumentTypeDocumentCategoryProcess(this);

		return documentInformation;
	}

	public DocumentInformation removeDocumentInformation(DocumentInformation documentInformation) {
		getDocumentInformations().remove(documentInformation);
		documentInformation.setDocumentTypeDocumentCategoryProcess(null);

		return documentInformation;
	}

	public DocumentCategoryProcess getDocumentCategoryProcess() {
		return this.documentCategoryProcess;
	}

	public void setDocumentCategoryProcess(DocumentCategoryProcess documentCategoryProcess) {
		this.documentCategoryProcess = documentCategoryProcess;
	}

	public DocumentType getDocumentType() {
		return this.documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

}