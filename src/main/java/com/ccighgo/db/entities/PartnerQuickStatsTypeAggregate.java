package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the PartnerQuickStatsTypeAggregate database table.
 * 
 */
@Entity
@Table(name="PartnerQuickStatsTypeAggregate")
@NamedQuery(name="PartnerQuickStatsTypeAggregate.findAll", query="SELECT p FROM PartnerQuickStatsTypeAggregate p")
public class PartnerQuickStatsTypeAggregate implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer partnerQSTypeAggregateId;

   @Column(nullable=false)
   private Timestamp modifiedDate;

   private Integer partnerQSTypeAggregate;

   @Column(length=50)
   private String partnerQSTypeName;

   //bi-directional many-to-one association to LookupDepartmentProgram
   @ManyToOne
   @JoinColumn(name="lookupDepartmentProgramId")
   private LookupDepartmentProgram lookupDepartmentProgram;

   //bi-directional many-to-one association to Partner
   @ManyToOne
   @JoinColumn(name="partnerGoId")
   private Partner partner;

   //bi-directional many-to-one association to PartnerQuickStatsType
   @ManyToOne
   @JoinColumn(name="partnerQSTypeId")
   private PartnerQuickStatsType partnerQuickStatsType;

   public PartnerQuickStatsTypeAggregate() {
   }

   public Integer getPartnerQSTypeAggregateId() {
      return this.partnerQSTypeAggregateId;
   }

   public void setPartnerQSTypeAggregateId(Integer partnerQSTypeAggregateId) {
      this.partnerQSTypeAggregateId = partnerQSTypeAggregateId;
   }

   public Timestamp getModifiedDate() {
      return this.modifiedDate;
   }

   public void setModifiedDate(Timestamp modifiedDate) {
      this.modifiedDate = modifiedDate;
   }

   public Integer getPartnerQSTypeAggregate() {
      return this.partnerQSTypeAggregate;
   }

   public void setPartnerQSTypeAggregate(Integer partnerQSTypeAggregate) {
      this.partnerQSTypeAggregate = partnerQSTypeAggregate;
   }

   public String getPartnerQSTypeName() {
      return this.partnerQSTypeName;
   }

   public void setPartnerQSTypeName(String partnerQSTypeName) {
      this.partnerQSTypeName = partnerQSTypeName;
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

   public PartnerQuickStatsType getPartnerQuickStatsType() {
      return this.partnerQuickStatsType;
   }

   public void setPartnerQuickStatsType(PartnerQuickStatsType partnerQuickStatsType) {
      this.partnerQuickStatsType = partnerQuickStatsType;
   }

}