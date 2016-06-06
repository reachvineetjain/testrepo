package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PartnerReviewStatus database table.
 * 
 */
@Entity
@Table(name="PartnerReviewStatus")
@NamedQuery(name="PartnerReviewStatus.findAll", query="SELECT p FROM PartnerReviewStatus p")
public class PartnerReviewStatus implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer partnerReviewStatusId;

   @Column(length=1000)
   private String partnerStatusReason;

   //bi-directional many-to-one association to CCIStaffUser
   @ManyToOne
   @JoinColumn(name="cciStaffUserId")
   private CCIStaffUser ccistaffUser;

   //bi-directional many-to-one association to Partner
   @ManyToOne
   @JoinColumn(name="partnerGoId")
   private Partner partner;

   //bi-directional many-to-one association to PartnerStatus
   @ManyToOne
   @JoinColumn(name="partnerLeadStatusId")
   private PartnerStatus partnerStatus1;

   //bi-directional many-to-one association to PartnerStatus
   @ManyToOne
   @JoinColumn(name="partnerAgentStatusId")
   private PartnerStatus partnerStatus2;

   public PartnerReviewStatus() {
   }

   public Integer getPartnerReviewStatusId() {
      return this.partnerReviewStatusId;
   }

   public void setPartnerReviewStatusId(Integer partnerReviewStatusId) {
      this.partnerReviewStatusId = partnerReviewStatusId;
   }

   public String getPartnerStatusReason() {
      return this.partnerStatusReason;
   }

   public void setPartnerStatusReason(String partnerStatusReason) {
      this.partnerStatusReason = partnerStatusReason;
   }

   public CCIStaffUser getCcistaffUser() {
      return this.ccistaffUser;
   }

   public void setCcistaffUser(CCIStaffUser ccistaffUser) {
      this.ccistaffUser = ccistaffUser;
   }

   public Partner getPartner() {
      return this.partner;
   }

   public void setPartner(Partner partner) {
      this.partner = partner;
   }

   public PartnerStatus getPartnerStatus1() {
      return this.partnerStatus1;
   }

   public void setPartnerStatus1(PartnerStatus partnerStatus1) {
      this.partnerStatus1 = partnerStatus1;
   }

   public PartnerStatus getPartnerStatus2() {
      return this.partnerStatus2;
   }

   public void setPartnerStatus2(PartnerStatus partnerStatus2) {
      this.partnerStatus2 = partnerStatus2;
   }

}