package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the PartnerHelpRequest database table.
 * 
 */
@Entity
@Table(name="PartnerHelpRequest")
@NamedQuery(name="PartnerHelpRequest.findAll", query="SELECT p FROM PartnerHelpRequest p")
public class PartnerHelpRequest implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer partnerHelpRequestId;

   private Byte active;

   @Column(nullable=false)
   private Integer createdBy;

   @Column(nullable=false)
   private Timestamp createdOn;

   @Column(nullable=false)
   private Integer modifiedBy;

   @Column(nullable=false)
   private Timestamp modifiedOn;

   @Column(length=64)
   private String partnerHelpRequestGUID;

   @Column(length=50)
   private String requestEmailAddress;

   @Column(length=1000)
   private String requestMessage;

   @Column(length=200)
   private String requestName;

   //bi-directional many-to-one association to HelpContactMode
   @ManyToOne
   @JoinColumn(name="helpContactModeId")
   private HelpContactMode helpContactMode;

   //bi-directional many-to-one association to Login
   @ManyToOne
   @JoinColumn(name="loginId")
   private Login login;

   //bi-directional many-to-one association to LookupDepartmentProgram
   @ManyToOne
   @JoinColumn(name="lookupDepartmentProgramId")
   private LookupDepartmentProgram lookupDepartmentProgram;

   //bi-directional many-to-one association to Partner
   @ManyToOne
   @JoinColumn(name="partnerGoId")
   private Partner partner1;

   //bi-directional many-to-one association to Partner
   @ManyToOne
   @JoinColumn(name="subPartnerGoId")
   private Partner partner2;

   //bi-directional many-to-one association to PartnerHelpOption
   @ManyToOne
   @JoinColumn(name="partnerHelpOptionId")
   private PartnerHelpOption partnerHelpOption;

   public PartnerHelpRequest() {
   }

   public Integer getPartnerHelpRequestId() {
      return this.partnerHelpRequestId;
   }

   public void setPartnerHelpRequestId(Integer partnerHelpRequestId) {
      this.partnerHelpRequestId = partnerHelpRequestId;
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

   public String getPartnerHelpRequestGUID() {
      return this.partnerHelpRequestGUID;
   }

   public void setPartnerHelpRequestGUID(String partnerHelpRequestGUID) {
      this.partnerHelpRequestGUID = partnerHelpRequestGUID;
   }

   public String getRequestEmailAddress() {
      return this.requestEmailAddress;
   }

   public void setRequestEmailAddress(String requestEmailAddress) {
      this.requestEmailAddress = requestEmailAddress;
   }

   public String getRequestMessage() {
      return this.requestMessage;
   }

   public void setRequestMessage(String requestMessage) {
      this.requestMessage = requestMessage;
   }

   public String getRequestName() {
      return this.requestName;
   }

   public void setRequestName(String requestName) {
      this.requestName = requestName;
   }

   public HelpContactMode getHelpContactMode() {
      return this.helpContactMode;
   }

   public void setHelpContactMode(HelpContactMode helpContactMode) {
      this.helpContactMode = helpContactMode;
   }

   public Login getLogin() {
      return this.login;
   }

   public void setLogin(Login login) {
      this.login = login;
   }

   public LookupDepartmentProgram getLookupDepartmentProgram() {
      return this.lookupDepartmentProgram;
   }

   public void setLookupDepartmentProgram(LookupDepartmentProgram lookupDepartmentProgram) {
      this.lookupDepartmentProgram = lookupDepartmentProgram;
   }

   public Partner getPartner1() {
      return this.partner1;
   }

   public void setPartner1(Partner partner1) {
      this.partner1 = partner1;
   }

   public Partner getPartner2() {
      return this.partner2;
   }

   public void setPartner2(Partner partner2) {
      this.partner2 = partner2;
   }

   public PartnerHelpOption getPartnerHelpOption() {
      return this.partnerHelpOption;
   }

   public void setPartnerHelpOption(PartnerHelpOption partnerHelpOption) {
      this.partnerHelpOption = partnerHelpOption;
   }

}