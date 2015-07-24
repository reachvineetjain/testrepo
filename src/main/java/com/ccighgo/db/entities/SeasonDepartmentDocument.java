package com.ccighgo.db.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the SeasonDepartmentDocument database table.
 * 
 */
@Entity
@Table(name = "SeasonDepartmentDocument")
@NamedQuery(name = "SeasonDepartmentDocument.findAll", query = "SELECT s FROM SeasonDepartmentDocument s")
public class SeasonDepartmentDocument implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer seasonDepartmentDocumentID;

   private byte active;

   private Integer createdBy;

   private Timestamp createdOn;

   private Integer modifiedBy;

   private Timestamp modifiedOn;

   // bi-directional many-to-one association to DocumentInformation
   @ManyToOne
   @JoinColumn(name = "documentInformationId")
   private DocumentInformation documentInformation;

   // bi-directional many-to-one association to Season
   @ManyToOne
   @JoinColumn(name = "seasonId")
   private Season season;

   public SeasonDepartmentDocument() {
   }

   public Integer getSeasonDepartmentDocumentID() {
      if (this.seasonDepartmentDocumentID != null)
         return this.seasonDepartmentDocumentID;
      return 0;
   }

   public void setSeasonDepartmentDocumentID(Integer seasonDepartmentDocumentID) {
      this.seasonDepartmentDocumentID = seasonDepartmentDocumentID;
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

   public DocumentInformation getDocumentInformation() {
      return this.documentInformation;
   }

   public void setDocumentInformation(DocumentInformation documentInformation) {
      this.documentInformation = documentInformation;
   }

   public Season getSeason() {
      return this.season;
   }

   public void setSeason(Season season) {
      this.season = season;
   }

}