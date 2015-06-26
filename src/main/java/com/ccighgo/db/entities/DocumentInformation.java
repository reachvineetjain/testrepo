package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the DocumentInformation database table.
 * 
 */
@Entity
@Table(name="DocumentInformation")
@NamedQuery(name="DocumentInformation.findAll", query="SELECT d FROM DocumentInformation d")
public class DocumentInformation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int documentInformationId;

	private byte active;

	private int createdBy;

	private Timestamp createdOn;

	@Column(length=50)
	private String documentName;

	@Column(length=50)
	private String fileName;

	private int modifiedBy;

	private Timestamp modifiedOn;

	@Column(length=1000)
	private String url;

	//bi-directional many-to-one association to AddendumDocumentInformation
	@OneToMany(mappedBy="documentInformation")
	private List<AddendumDocumentInformation> addendumDocumentInformations;

	//bi-directional many-to-one association to DocumentTypeDocumentCategoryProcess
	@ManyToOne
	@JoinColumn(name="documentTypeDocumentCategoryProcessId")
	private DocumentTypeDocumentCategoryProcess documentTypeDocumentCategoryProcess;

	//bi-directional many-to-one association to SeasonDepartmentDocument
	@OneToMany(mappedBy="documentInformation")
	private List<SeasonDepartmentDocument> seasonDepartmentDocuments;

	//bi-directional many-to-one association to SeasonProgramDocument
	@OneToMany(mappedBy="documentInformation")
	private List<SeasonProgramDocument> seasonProgramDocuments;

	public DocumentInformation() {
	}

	public int getDocumentInformationId() {
		return this.documentInformationId;
	}

	public void setDocumentInformationId(int documentInformationId) {
		this.documentInformationId = documentInformationId;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
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

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<AddendumDocumentInformation> getAddendumDocumentInformations() {
		return this.addendumDocumentInformations;
	}

	public void setAddendumDocumentInformations(List<AddendumDocumentInformation> addendumDocumentInformations) {
		this.addendumDocumentInformations = addendumDocumentInformations;
	}

	public AddendumDocumentInformation addAddendumDocumentInformation(AddendumDocumentInformation addendumDocumentInformation) {
		getAddendumDocumentInformations().add(addendumDocumentInformation);
		addendumDocumentInformation.setDocumentInformation(this);

		return addendumDocumentInformation;
	}

	public AddendumDocumentInformation removeAddendumDocumentInformation(AddendumDocumentInformation addendumDocumentInformation) {
		getAddendumDocumentInformations().remove(addendumDocumentInformation);
		addendumDocumentInformation.setDocumentInformation(null);

		return addendumDocumentInformation;
	}

	public DocumentTypeDocumentCategoryProcess getDocumentTypeDocumentCategoryProcess() {
		return this.documentTypeDocumentCategoryProcess;
	}

	public void setDocumentTypeDocumentCategoryProcess(DocumentTypeDocumentCategoryProcess documentTypeDocumentCategoryProcess) {
		this.documentTypeDocumentCategoryProcess = documentTypeDocumentCategoryProcess;
	}

	public List<SeasonDepartmentDocument> getSeasonDepartmentDocuments() {
		return this.seasonDepartmentDocuments;
	}

	public void setSeasonDepartmentDocuments(List<SeasonDepartmentDocument> seasonDepartmentDocuments) {
		this.seasonDepartmentDocuments = seasonDepartmentDocuments;
	}

	public SeasonDepartmentDocument addSeasonDepartmentDocument(SeasonDepartmentDocument seasonDepartmentDocument) {
		getSeasonDepartmentDocuments().add(seasonDepartmentDocument);
		seasonDepartmentDocument.setDocumentInformation(this);

		return seasonDepartmentDocument;
	}

	public SeasonDepartmentDocument removeSeasonDepartmentDocument(SeasonDepartmentDocument seasonDepartmentDocument) {
		getSeasonDepartmentDocuments().remove(seasonDepartmentDocument);
		seasonDepartmentDocument.setDocumentInformation(null);

		return seasonDepartmentDocument;
	}

	public List<SeasonProgramDocument> getSeasonProgramDocuments() {
		return this.seasonProgramDocuments;
	}

	public void setSeasonProgramDocuments(List<SeasonProgramDocument> seasonProgramDocuments) {
		this.seasonProgramDocuments = seasonProgramDocuments;
	}

	public SeasonProgramDocument addSeasonProgramDocument(SeasonProgramDocument seasonProgramDocument) {
		getSeasonProgramDocuments().add(seasonProgramDocument);
		seasonProgramDocument.setDocumentInformation(this);

		return seasonProgramDocument;
	}

	public SeasonProgramDocument removeSeasonProgramDocument(SeasonProgramDocument seasonProgramDocument) {
		getSeasonProgramDocuments().remove(seasonProgramDocument);
		seasonProgramDocument.setDocumentInformation(null);

		return seasonProgramDocument;
	}

}