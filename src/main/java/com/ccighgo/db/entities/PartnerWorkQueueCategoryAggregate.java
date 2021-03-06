package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the PartnerWorkQueueCategoryAggregate database table.
 * 
 */
@Entity
@Table(name="PartnerWorkQueueCategoryAggregate")
@NamedQuery(name="PartnerWorkQueueCategoryAggregate.findAll", query="SELECT p FROM PartnerWorkQueueCategoryAggregate p")
public class PartnerWorkQueueCategoryAggregate implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer partnerWQCategoryAggregateId;

   @Column(nullable=false)
   private Timestamp modifiedDate;

   private Integer partnerWQCategoryAggregate;

   @Column(length=50)
   private String partnerWQCategoryName;

   //bi-directional many-to-one association to LookupDepartmentProgram
   @ManyToOne
   @JoinColumn(name="lookupDepartmentProgramId")
   private LookupDepartmentProgram lookupDepartmentProgram;

   //bi-directional many-to-one association to Partner
   @ManyToOne
   @JoinColumn(name="partnerGoId")
   private Partner partner;

   //bi-directional many-to-one association to PartnerWorkQueueCategory
   @ManyToOne
   @JoinColumn(name="partnerWQCategoryId")
   private PartnerWorkQueueCategory partnerWorkQueueCategory;

   //bi-directional many-to-one association to PartnerWorkQueueType
   @ManyToOne
   @JoinColumn(name="partnerWQTypeId")
   private PartnerWorkQueueType partnerWorkQueueType;

   public PartnerWorkQueueCategoryAggregate() {
   }

   public Integer getPartnerWQCategoryAggregateId() {
      return this.partnerWQCategoryAggregateId;
   }

   public void setPartnerWQCategoryAggregateId(Integer partnerWQCategoryAggregateId) {
      this.partnerWQCategoryAggregateId = partnerWQCategoryAggregateId;
   }

   public Timestamp getModifiedDate() {
      return this.modifiedDate;
   }

   public void setModifiedDate(Timestamp modifiedDate) {
      this.modifiedDate = modifiedDate;
   }

   public Integer getPartnerWQCategoryAggregate() {
      return this.partnerWQCategoryAggregate;
   }

   public void setPartnerWQCategoryAggregate(Integer partnerWQCategoryAggregate) {
      this.partnerWQCategoryAggregate = partnerWQCategoryAggregate;
   }

   public String getPartnerWQCategoryName() {
      return this.partnerWQCategoryName;
   }

   public void setPartnerWQCategoryName(String partnerWQCategoryName) {
      this.partnerWQCategoryName = partnerWQCategoryName;
   }

   public LookupDepartmentProgram getLookupDepartmentProgram() {
      return this.lookupDepartmentProgram;
   }

   public void setLookupDepartmentProgram(LookupDepartmentProgram lookupDepartmentProgram) {
      this.lookupDepartmentProgram = lookupDepartmentProgram;
   }

   public Partner getPartner() {
      return this.partner;
   }

   public void setPartner(Partner partner) {
      this.partner = partner;
   }

   public PartnerWorkQueueCategory getPartnerWorkQueueCategory() {
      return this.partnerWorkQueueCategory;
   }

   public void setPartnerWorkQueueCategory(PartnerWorkQueueCategory partnerWorkQueueCategory) {
      this.partnerWorkQueueCategory = partnerWorkQueueCategory;
   }

   public PartnerWorkQueueType getPartnerWorkQueueType() {
      return this.partnerWorkQueueType;
   }

   public void setPartnerWorkQueueType(PartnerWorkQueueType partnerWorkQueueType) {
      this.partnerWorkQueueType = partnerWorkQueueType;
   }

}