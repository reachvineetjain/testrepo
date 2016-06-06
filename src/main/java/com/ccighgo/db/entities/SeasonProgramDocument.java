package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the SeasonProgramDocument database table.
 * 
 */
@Entity
@Table(name="SeasonProgramDocument")
@NamedQuery(name="SeasonProgramDocument.findAll", query="SELECT s FROM SeasonProgramDocument s")
public class SeasonProgramDocument implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer seasonProgramDocumentId;

   private Byte active;

   @Column(nullable=false)
   private Integer createdBy;

   @Column(nullable=false)
   private Timestamp createdOn;

   @Column(nullable=false)
   private Integer modifiedBy;

   private Timestamp modifiedOn;

   //bi-directional many-to-one association to DepartmentProgram
   @ManyToOne
   @JoinColumn(name="departmentProgramId", nullable=false)
   private DepartmentProgram departmentProgram;

   //bi-directional many-to-one association to DocumentInformation
   @ManyToOne
   @JoinColumn(name="documentInformationId", nullable=false)
   private DocumentInformation documentInformation;

   //bi-directional many-to-one association to Season
   @ManyToOne
   @JoinColumn(name="seasonId", nullable=false)
   private Season season;

   public SeasonProgramDocument() {
   }

   public Integer getSeasonProgramDocumentId() {
      return this.seasonProgramDocumentId;
   }

   public void setSeasonProgramDocumentId(Integer seasonProgramDocumentId) {
      this.seasonProgramDocumentId = seasonProgramDocumentId;
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

   public DepartmentProgram getDepartmentProgram() {
      return this.departmentProgram;
   }

   public void setDepartmentProgram(DepartmentProgram departmentProgram) {
      this.departmentProgram = departmentProgram;
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