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
 * The persistent class for the DocumentCategoryProcess database table.
 * 
 */
@Entity
@Table(name = "DocumentCategoryProcess")
@NamedQuery(name = "DocumentCategoryProcess.findAll", query = "SELECT d FROM DocumentCategoryProcess d")
public class DocumentCategoryProcess implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer documentCategoryProcessId;

   @Column(length = 50)
   private String documentCategoryProcessName;

   // bi-directional many-to-one association to DocumentTypeDocumentCategoryProcess
   @OneToMany(mappedBy = "documentCategoryProcess")
   private List<DocumentTypeDocumentCategoryProcess> documentTypeDocumentCategoryProcesses;

   public DocumentCategoryProcess() {
   }

   public Integer getDocumentCategoryProcessId() {
      if (this.documentCategoryProcessId != null)
         return this.documentCategoryProcessId;
      return 0;
   }

   public void setDocumentCategoryProcessId(Integer documentCategoryProcessId) {
      this.documentCategoryProcessId = documentCategoryProcessId;
   }

   public String getDocumentCategoryProcessName() {
      return this.documentCategoryProcessName;
   }

   public void setDocumentCategoryProcessName(String documentCategoryProcessName) {
      this.documentCategoryProcessName = documentCategoryProcessName;
   }

   public List<DocumentTypeDocumentCategoryProcess> getDocumentTypeDocumentCategoryProcesses() {
      return this.documentTypeDocumentCategoryProcesses;
   }

   public void setDocumentTypeDocumentCategoryProcesses(List<DocumentTypeDocumentCategoryProcess> documentTypeDocumentCategoryProcesses) {
      this.documentTypeDocumentCategoryProcesses = documentTypeDocumentCategoryProcesses;
   }

   public DocumentTypeDocumentCategoryProcess addDocumentTypeDocumentCategoryProcess(DocumentTypeDocumentCategoryProcess documentTypeDocumentCategoryProcess) {
      getDocumentTypeDocumentCategoryProcesses().add(documentTypeDocumentCategoryProcess);
      documentTypeDocumentCategoryProcess.setDocumentCategoryProcess(this);

      return documentTypeDocumentCategoryProcess;
   }

   public DocumentTypeDocumentCategoryProcess removeDocumentTypeDocumentCategoryProcess(DocumentTypeDocumentCategoryProcess documentTypeDocumentCategoryProcess) {
      getDocumentTypeDocumentCategoryProcesses().remove(documentTypeDocumentCategoryProcess);
      documentTypeDocumentCategoryProcess.setDocumentCategoryProcess(null);

      return documentTypeDocumentCategoryProcess;
   }

}