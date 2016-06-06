package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PartnerSeasonContract database table.
 * 
 */
@Entity
@Table(name="PartnerSeasonContract")
@NamedQuery(name="PartnerSeasonContract.findAll", query="SELECT p FROM PartnerSeasonContract p")
public class PartnerSeasonContract implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer partnerSeasonContractId;

   @Lob
   private String description;

   private Byte isSigned;

   //bi-directional many-to-one association to DocumentInformation
   @ManyToOne
   @JoinColumn(name="documentInformationId")
   private DocumentInformation documentInformation;

   //bi-directional many-to-one association to PartnerSeason
   @ManyToOne
   @JoinColumn(name="partnerSeasonId")
   private PartnerSeason partnerSeason;

   public PartnerSeasonContract() {
   }

   public Integer getPartnerSeasonContractId() {
      return this.partnerSeasonContractId;
   }

   public void setPartnerSeasonContractId(Integer partnerSeasonContractId) {
      this.partnerSeasonContractId = partnerSeasonContractId;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Byte getIsSigned() {
      return this.isSigned;
   }

   public void setIsSigned(Byte isSigned) {
      this.isSigned = isSigned;
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