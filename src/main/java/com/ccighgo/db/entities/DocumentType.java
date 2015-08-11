package com.ccighgo.db.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the DocumentType database table.
 * 
 */
@Entity
@Table(name = "DocumentType")
@NamedQuery(name = "DocumentType.findAll", query = "SELECT d FROM DocumentType d")
public class DocumentType implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer documentTypeId;

   @Column(length = 50)
   private String documentTypeName;

   // bi-directional many-to-one association to DocumentTypeDocumentCategoryProcess
   @OneToMany(mappedBy = "documentType")
   private List<DocumentTypeDocumentCategoryProcess> documentTypeDocumentCategoryProcesses;

   public DocumentType() {
   }

   public Integer getDocumentTypeId() {
      if (this.documentTypeId != null)
         return this.documentTypeId;
      return 0;
   }

   public void setDocumentTypeId(Integer documentTypeId) {
      this.documentTypeId = documentTypeId;
   }

   public String getDocumentTypeName() {
      return this.documentTypeName;
   }

   public void setDocumentTypeName(String documentTypeName) {
      this.documentTypeName = documentTypeName;
   }

   public List<DocumentTypeDocumentCategoryProcess> getDocumentTypeDocumentCategoryProcesses() {
      return this.documentTypeDocumentCategoryProcesses;
   }

   public void setDocumentTypeDocumentCategoryProcesses(List<DocumentTypeDocumentCategoryProcess> documentTypeDocumentCategoryProcesses) {
      this.documentTypeDocumentCategoryProcesses = documentTypeDocumentCategoryProcesses;
   }

   public DocumentTypeDocumentCategoryProcess addDocumentTypeDocumentCategoryProcess(DocumentTypeDocumentCategoryProcess documentTypeDocumentCategoryProcess) {
      getDocumentTypeDocumentCategoryProcesses().add(documentTypeDocumentCategoryProcess);
      documentTypeDocumentCategoryProcess.setDocumentType(this);

      return documentTypeDocumentCategoryProcess;
   }

   public DocumentTypeDocumentCategoryProcess removeDocumentTypeDocumentCategoryProcess(DocumentTypeDocumentCategoryProcess documentTypeDocumentCategoryProcess) {
      getDocumentTypeDocumentCategoryProcesses().remove(documentTypeDocumentCategoryProcess);
      documentTypeDocumentCategoryProcess.setDocumentType(null);

      return documentTypeDocumentCategoryProcess;
   }

}