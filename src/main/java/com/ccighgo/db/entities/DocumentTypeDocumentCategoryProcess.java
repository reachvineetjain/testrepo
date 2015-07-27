package com.ccighgo.db.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the DocumentTypeDocumentCategoryProcess database table.
 * 
 */
@Entity
@Table(name = "DocumentTypeDocumentCategoryProcess")
@NamedQuery(name = "DocumentTypeDocumentCategoryProcess.findAll", query = "SELECT d FROM DocumentTypeDocumentCategoryProcess d")
public class DocumentTypeDocumentCategoryProcess implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer documentTypeDocumentCategoryProcessId;

   @Column(length = 50)
   private String documentTypeRole;

   // bi-directional many-to-one association to DocumentInformation
   @OneToMany(mappedBy = "documentTypeDocumentCategoryProcess")
   private List<DocumentInformation> documentInformations;

   // bi-directional many-to-one association to DocumentCategoryProcess
   @ManyToOne
   @JoinColumn(name = "documentCategoryProcessId")
   private DocumentCategoryProcess documentCategoryProcess;

   // bi-directional many-to-one association to DocumentType
   @ManyToOne
   @JoinColumn(name = "documentTypeId")
   private DocumentType documentType;

   public DocumentTypeDocumentCategoryProcess() {
   }

   public Integer getDocumentTypeDocumentCategoryProcessId() {
      if (this.documentTypeDocumentCategoryProcessId != null)
         return this.documentTypeDocumentCategoryProcessId;
      return 0;
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