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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer documentInformationId;

	private Byte active;

	@Column(nullable=false)
	private Integer createdBy;

	@Column(nullable=false)
	private Timestamp createdOn;

	@Column(length=50)
	private String documentName;

	@Column(length=50)
	private String fileName;

	@Column(nullable=false)
	private Integer modifiedBy;

	@Column(nullable=false)
	private Timestamp modifiedOn;

	@Column(length=1000)
	private String url;

	//bi-directional many-to-one association to AddendumDocumentInformation
	@OneToMany(mappedBy="documentInformation")
	private List<AddendumDocumentInformation> addendumDocumentInformations;

	//bi-directional many-to-one association to DocumentTypeDocumentCategoryProcess
	@ManyToOne
	@JoinColumn(name="documentTypeDocumentCategoryProcessId", nullable=false)
	private DocumentTypeDocumentCategoryProcess documentTypeDocumentCategoryProcess;

	//bi-directional many-to-one association to FieldStaffDocument
	@OneToMany(mappedBy="documentInformation")
	private List<FieldStaffDocument> fieldStaffDocuments;

	//bi-directional many-to-one association to FieldStaffLCSeasonDocument
	@OneToMany(mappedBy="documentInformation")
	private List<FieldStaffLCSeasonDocument> fieldStaffLcseasonDocuments;

	//bi-directional many-to-one association to FieldStaffLeadershipSeasonDocument
	@OneToMany(mappedBy="documentInformation")
	private List<FieldStaffLeadershipSeasonDocument> fieldStaffLeadershipSeasonDocuments;

	//bi-directional many-to-one association to PartnerDocument
	@OneToMany(mappedBy="documentInformation")
	private List<PartnerDocument> partnerDocuments;

	//bi-directional many-to-one association to PartnerSeasonContract
	@OneToMany(mappedBy="documentInformation")
	private List<PartnerSeasonContract> partnerSeasonContracts;

	//bi-directional many-to-one association to PartnerSeasonDocument
	@OneToMany(mappedBy="documentInformation")
	private List<PartnerSeasonDocument> partnerSeasonDocuments;

	//bi-directional many-to-one association to SeasonDepartmentDocument
	@OneToMany(mappedBy="documentInformation")
	private List<SeasonDepartmentDocument> seasonDepartmentDocuments;

	//bi-directional many-to-one association to SeasonProgramDocument
	@OneToMany(mappedBy="documentInformation")
	private List<SeasonProgramDocument> seasonProgramDocuments;

	public DocumentInformation() {
	}

	public Integer getDocumentInformationId() {
		return this.documentInformationId;
	}

	public void setDocumentInformationId(Integer documentInformationId) {
		this.documentInformationId = documentInformationId;
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

	public List<FieldStaffDocument> getFieldStaffDocuments() {
		return this.fieldStaffDocuments;
	}

	public void setFieldStaffDocuments(List<FieldStaffDocument> fieldStaffDocuments) {
		this.fieldStaffDocuments = fieldStaffDocuments;
	}

	public FieldStaffDocument addFieldStaffDocument(FieldStaffDocument fieldStaffDocument) {
		getFieldStaffDocuments().add(fieldStaffDocument);
		fieldStaffDocument.setDocumentInformation(this);

		return fieldStaffDocument;
	}

	public FieldStaffDocument removeFieldStaffDocument(FieldStaffDocument fieldStaffDocument) {
		getFieldStaffDocuments().remove(fieldStaffDocument);
		fieldStaffDocument.setDocumentInformation(null);

		return fieldStaffDocument;
	}

	public List<FieldStaffLCSeasonDocument> getFieldStaffLcseasonDocuments() {
		return this.fieldStaffLcseasonDocuments;
	}

	public void setFieldStaffLcseasonDocuments(List<FieldStaffLCSeasonDocument> fieldStaffLcseasonDocuments) {
		this.fieldStaffLcseasonDocuments = fieldStaffLcseasonDocuments;
	}

	public FieldStaffLCSeasonDocument addFieldStaffLcseasonDocument(FieldStaffLCSeasonDocument fieldStaffLcseasonDocument) {
		getFieldStaffLcseasonDocuments().add(fieldStaffLcseasonDocument);
		fieldStaffLcseasonDocument.setDocumentInformation(this);

		return fieldStaffLcseasonDocument;
	}

	public FieldStaffLCSeasonDocument removeFieldStaffLcseasonDocument(FieldStaffLCSeasonDocument fieldStaffLcseasonDocument) {
		getFieldStaffLcseasonDocuments().remove(fieldStaffLcseasonDocument);
		fieldStaffLcseasonDocument.setDocumentInformation(null);

		return fieldStaffLcseasonDocument;
	}

	public List<FieldStaffLeadershipSeasonDocument> getFieldStaffLeadershipSeasonDocuments() {
		return this.fieldStaffLeadershipSeasonDocuments;
	}

	public void setFieldStaffLeadershipSeasonDocuments(List<FieldStaffLeadershipSeasonDocument> fieldStaffLeadershipSeasonDocuments) {
		this.fieldStaffLeadershipSeasonDocuments = fieldStaffLeadershipSeasonDocuments;
	}

	public FieldStaffLeadershipSeasonDocument addFieldStaffLeadershipSeasonDocument(FieldStaffLeadershipSeasonDocument fieldStaffLeadershipSeasonDocument) {
		getFieldStaffLeadershipSeasonDocuments().add(fieldStaffLeadershipSeasonDocument);
		fieldStaffLeadershipSeasonDocument.setDocumentInformation(this);

		return fieldStaffLeadershipSeasonDocument;
	}

	public FieldStaffLeadershipSeasonDocument removeFieldStaffLeadershipSeasonDocument(FieldStaffLeadershipSeasonDocument fieldStaffLeadershipSeasonDocument) {
		getFieldStaffLeadershipSeasonDocuments().remove(fieldStaffLeadershipSeasonDocument);
		fieldStaffLeadershipSeasonDocument.setDocumentInformation(null);

		return fieldStaffLeadershipSeasonDocument;
	}

	public List<PartnerDocument> getPartnerDocuments() {
		return this.partnerDocuments;
	}

	public void setPartnerDocuments(List<PartnerDocument> partnerDocuments) {
		this.partnerDocuments = partnerDocuments;
	}

	public PartnerDocument addPartnerDocument(PartnerDocument partnerDocument) {
		getPartnerDocuments().add(partnerDocument);
		partnerDocument.setDocumentInformation(this);

		return partnerDocument;
	}

	public PartnerDocument removePartnerDocument(PartnerDocument partnerDocument) {
		getPartnerDocuments().remove(partnerDocument);
		partnerDocument.setDocumentInformation(null);

		return partnerDocument;
	}

	public List<PartnerSeasonContract> getPartnerSeasonContracts() {
		return this.partnerSeasonContracts;
	}

	public void setPartnerSeasonContracts(List<PartnerSeasonContract> partnerSeasonContracts) {
		this.partnerSeasonContracts = partnerSeasonContracts;
	}

	public PartnerSeasonContract addPartnerSeasonContract(PartnerSeasonContract partnerSeasonContract) {
		getPartnerSeasonContracts().add(partnerSeasonContract);
		partnerSeasonContract.setDocumentInformation(this);

		return partnerSeasonContract;
	}

	public PartnerSeasonContract removePartnerSeasonContract(PartnerSeasonContract partnerSeasonContract) {
		getPartnerSeasonContracts().remove(partnerSeasonContract);
		partnerSeasonContract.setDocumentInformation(null);

		return partnerSeasonContract;
	}

	public List<PartnerSeasonDocument> getPartnerSeasonDocuments() {
		return this.partnerSeasonDocuments;
	}

	public void setPartnerSeasonDocuments(List<PartnerSeasonDocument> partnerSeasonDocuments) {
		this.partnerSeasonDocuments = partnerSeasonDocuments;
	}

	public PartnerSeasonDocument addPartnerSeasonDocument(PartnerSeasonDocument partnerSeasonDocument) {
		getPartnerSeasonDocuments().add(partnerSeasonDocument);
		partnerSeasonDocument.setDocumentInformation(this);

		return partnerSeasonDocument;
	}

	public PartnerSeasonDocument removePartnerSeasonDocument(PartnerSeasonDocument partnerSeasonDocument) {
		getPartnerSeasonDocuments().remove(partnerSeasonDocument);
		partnerSeasonDocument.setDocumentInformation(null);

		return partnerSeasonDocument;
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