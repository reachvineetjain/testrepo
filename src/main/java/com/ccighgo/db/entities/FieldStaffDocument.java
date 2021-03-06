package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the FieldStaffDocument database table.
 * 
 */
@Entity
@Table(name="FieldStaffDocument")
@NamedQuery(name="FieldStaffDocument.findAll", query="SELECT f FROM FieldStaffDocument f")
public class FieldStaffDocument implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer fieldStaffDocumentId;

   private Byte active;

   private Integer createdBy;

   private Timestamp createdOn;

   @Column(length=1000)
   private String description;

   private Integer modifiedBy;

   private Timestamp modifiedOn;

   //bi-directional many-to-one association to DocumentInformation
   @ManyToOne
   @JoinColumn(name="documentInformationId")
   private DocumentInformation documentInformation;

   //bi-directional many-to-one association to FieldStaff
   @ManyToOne
   @JoinColumn(name="fieldStaffGoId")
   private FieldStaff fieldStaff;

   public FieldStaffDocument() {
   }

   public Integer getFieldStaffDocumentId() {
      return this.fieldStaffDocumentId;
   }

   public void setFieldStaffDocumentId(Integer fieldStaffDocumentId) {
      this.fieldStaffDocumentId = fieldStaffDocumentId;
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

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
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

   public DocumentInformation getDocumentInformation() {
      return this.documentInformation;
   }

   public void setDocumentInformation(DocumentInformation documentInformation) {
      this.documentInformation = documentInformation;
   }

   public FieldStaff getFieldStaff() {
      return this.fieldStaff;
   }

   public void setFieldStaff(FieldStaff fieldStaff) {
      this.fieldStaff = fieldStaff;
   }

}