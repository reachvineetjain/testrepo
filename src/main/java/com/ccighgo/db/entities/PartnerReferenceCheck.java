package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the PartnerReferenceChecks database table.
 * 
 */
@Entity
@Table(name="PartnerReferenceChecks")
@NamedQuery(name="PartnerReferenceCheck.findAll", query="SELECT p FROM PartnerReferenceCheck p")
public class PartnerReferenceCheck implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer partnerReferenceCheckId;

   @Temporal(TemporalType.TIMESTAMP)
   private Date businessLicenseExpiryDate;

   @Column(length=50)
   private String referenceApprovedBy;

   @Temporal(TemporalType.TIMESTAMP)
   private Date referenceApprovedOn;

   @Lob
   private String referenceCheckNotes;

   @Column(length=50)
   private String referenceCompletedBy;

   @Temporal(TemporalType.TIMESTAMP)
   private Date referenceCompletedOn;

   //bi-directional many-to-one association to Partner
   @ManyToOne
   @JoinColumn(name="partnerGoId")
   private Partner partner;

   public PartnerReferenceCheck() {
   }

   public Integer getPartnerReferenceCheckId() {
      return this.partnerReferenceCheckId;
   }

   public void setPartnerReferenceCheckId(Integer partnerReferenceCheckId) {
      this.partnerReferenceCheckId = partnerReferenceCheckId;
   }

   public Date getBusinessLicenseExpiryDate() {
      return this.businessLicenseExpiryDate;
   }

   public void setBusinessLicenseExpiryDate(Date businessLicenseExpiryDate) {
      this.businessLicenseExpiryDate = businessLicenseExpiryDate;
   }

   public String getReferenceApprovedBy() {
      return this.referenceApprovedBy;
   }

   public void setReferenceApprovedBy(String referenceApprovedBy) {
      this.referenceApprovedBy = referenceApprovedBy;
   }

   public Date getReferenceApprovedOn() {
      return this.referenceApprovedOn;
   }

   public void setReferenceApprovedOn(Date referenceApprovedOn) {
      this.referenceApprovedOn = referenceApprovedOn;
   }

   public String getReferenceCheckNotes() {
      return this.referenceCheckNotes;
   }

   public void setReferenceCheckNotes(String referenceCheckNotes) {
      this.referenceCheckNotes = referenceCheckNotes;
   }

   public String getReferenceCompletedBy() {
      return this.referenceCompletedBy;
   }

   public void setReferenceCompletedBy(String referenceCompletedBy) {
      this.referenceCompletedBy = referenceCompletedBy;
   }

   public Date getReferenceCompletedOn() {
      return this.referenceCompletedOn;
   }

   public void setReferenceCompletedOn(Date referenceCompletedOn) {
      this.referenceCompletedOn = referenceCompletedOn;
   }

   public Partner getPartner() {
      return this.partner;
   }

   public void setPartner(Partner partner) {
      this.partner = partner;
   }

}