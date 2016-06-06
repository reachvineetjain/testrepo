package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PartnerSeasonDocument database table.
 * 
 */
@Entity
@Table(name="PartnerSeasonDocument")
@NamedQuery(name="PartnerSeasonDocument.findAll", query="SELECT p FROM PartnerSeasonDocument p")
public class PartnerSeasonDocument implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer partnerSeasonDocumentId;

   @Lob
   private String description;

   //bi-directional many-to-one association to DocumentInformation
   @ManyToOne
   @JoinColumn(name="documentInformationId")
   private DocumentInformation documentInformation;

   //bi-directional many-to-one association to PartnerSeason
   @ManyToOne
   @JoinColumn(name="partnerSeasonId")
   private PartnerSeason partnerSeason;

   public PartnerSeasonDocument() {
   }

   public Integer getPartnerSeasonDocumentId() {
      return this.partnerSeasonDocumentId;
   }

   public void setPartnerSeasonDocumentId(Integer partnerSeasonDocumentId) {
      this.partnerSeasonDocumentId = partnerSeasonDocumentId;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public DocumentInformation getDocumentInformation() {
      return this.documentInformation;
   }

   public void setDocumentInformation(DocumentInformation documentInformation) {
      this.documentInformation = documentInformation;
   }

   public PartnerSeason getPartnerSeason() {
      return this.partnerSeason;
   }

   public void setPartnerSeason(PartnerSeason partnerSeason) {
      this.partnerSeason = partnerSeason;
   }

}