package com.ccighgo.db.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the AddendumDocumentInformation database table.
 * 
 */
@Entity
@Table(name = "AddendumDocumentInformation")
@NamedQuery(name = "AddendumDocumentInformation.findAll", query = "SELECT a FROM AddendumDocumentInformation a")
public class AddendumDocumentInformation implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer addendumDocumentInformationId;

   private byte active;

   private Integer createdBy;

   private Timestamp createdOn;

   @Column(length = 50)
   private String documentName;

   @Column(length = 50)
   private String fileName;

   private Integer modifiedBy;

   private Timestamp modifiedOn;

   @Temporal(TemporalType.TIMESTAMP)
   private Date updateDate;

   @Column(length = 1000)
   private String url;

   // bi-directional many-to-one association to DocumentInformation
   @ManyToOne
   @JoinColumn(name = "documentInformationId", nullable = false)
   private DocumentInformation documentInformation;

   public AddendumDocumentInformation() {
   }

   public Integer getAddendumDocumentInformationId() {
      if (this.addendumDocumentInformationId != null)
         return this.addendumDocumentInformationId;
      return 0;
   }

   public void setAddendumDocumentInformationId(Integer addendumDocumentInformationId) {
      this.addendumDocumentInformationId = addendumDocumentInformationId;
   }

   public byte getActive() {
      return this.active;
   }

   public void setActive(byte active) {
      this.active = active;
   }

   public Integer getCreatedBy() {
      if (this.createdBy != null)
         return this.createdBy;
      return 0;
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
      if (this.modifiedBy != null)
         return this.modifiedBy;
      return 0;
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

   public Date getUpdateDate() {
      return this.updateDate;
   }

   public void setUpdateDate(Date updateDate) {
      this.updateDate = updateDate;
   }

   public String getUrl() {
      return this.url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public DocumentInformation getDocumentInformation() {
      return this.documentInformation;
   }

   public void setDocumentInformation(DocumentInformation documentInformation) {
      this.documentInformation = documentInformation;
   }

}