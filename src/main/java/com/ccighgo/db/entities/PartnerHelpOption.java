package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the PartnerHelpOption database table.
 * 
 */
@Entity
@Table(name="PartnerHelpOption")
@NamedQuery(name="PartnerHelpOption.findAll", query="SELECT p FROM PartnerHelpOption p")
public class PartnerHelpOption implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer partnerHelpOptionId;

   private Byte active;

   private Integer createdBy;

   @Column(nullable=false)
   private Timestamp createdOn;

   private Integer modifiedBy;

   @Column(nullable=false)
   private Timestamp modifiedOn;

   @Column(length=50)
   private String partnerHelpOptionName;

   //bi-directional many-to-one association to PartnerHelpRequest
   @OneToMany(mappedBy="partnerHelpOption")
   private List<PartnerHelpRequest> partnerHelpRequests;

   public PartnerHelpOption() {
   }

   public Integer getPartnerHelpOptionId() {
      return this.partnerHelpOptionId;
   }

   public void setPartnerHelpOptionId(Integer partnerHelpOptionId) {
      this.partnerHelpOptionId = partnerHelpOptionId;
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

   public String getPartnerHelpOptionName() {
      return this.partnerHelpOptionName;
   }

   public void setPartnerHelpOptionName(String partnerHelpOptionName) {
      this.partnerHelpOptionName = partnerHelpOptionName;
   }

   public List<PartnerHelpRequest> getPartnerHelpRequests() {
      return this.partnerHelpRequests;
   }

   public void setPartnerHelpRequests(List<PartnerHelpRequest> partnerHelpRequests) {
      this.partnerHelpRequests = partnerHelpRequests;
   }

   public PartnerHelpRequest addPartnerHelpRequest(PartnerHelpRequest partnerHelpRequest) {
      getPartnerHelpRequests().add(partnerHelpRequest);
      partnerHelpRequest.setPartnerHelpOption(this);

      return partnerHelpRequest;
   }

   public PartnerHelpRequest removePartnerHelpRequest(PartnerHelpRequest partnerHelpRequest) {
      getPartnerHelpRequests().remove(partnerHelpRequest);
      partnerHelpRequest.setPartnerHelpOption(null);

      return partnerHelpRequest;
   }

}