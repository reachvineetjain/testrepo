package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the HostFamilyInterview database table.
 * 
 */
@Entity
@Table(name="HostFamilyInterview")
@NamedQuery(name="HostFamilyInterview.findAll", query="SELECT h FROM HostFamilyInterview h")
public class HostFamilyInterview implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer hostFamilyInterviewId;

   @Lob
   private String childrenFeelingsAboutHosting;

   private Integer createdBy;

   @Temporal(TemporalType.TIMESTAMP)
   private Date createdOn;

   private Byte discussedPlacement;

   @Lob
   private String financialSituation;

   private Byte hasFamilyHostedBefore;

   @Lob
   private String homeCondition;

   private Byte hostingCommitment;

   @Temporal(TemporalType.TIMESTAMP)
   private Date interviewDate;

   private Byte interviewIsAtHome;

   private Byte isCompleted;

   private Byte isStarted;

   private Integer modifiedBy;

   @Temporal(TemporalType.TIMESTAMP)
   private Date modifiedOn;

   @Lob
   private String previousHostingDescription;

   @Lob
   private String reasonsForHosting;

   @Lob
   private String religiousBackground;

   @Lob
   private String roomSharedWithDescription;

   //bi-directional many-to-one association to HostFamilySeason
   @ManyToOne
   @JoinColumn(name="hostFamilySeasonId")
   private HostFamilySeason hostFamilySeason;

   public HostFamilyInterview() {
   }

   public Integer getHostFamilyInterviewId() {
      return this.hostFamilyInterviewId;
   }

   public void setHostFamilyInterviewId(Integer hostFamilyInterviewId) {
      this.hostFamilyInterviewId = hostFamilyInterviewId;
   }

   public String getChildrenFeelingsAboutHosting() {
      return this.childrenFeelingsAboutHosting;
   }

   public void setChildrenFeelingsAboutHosting(String childrenFeelingsAboutHosting) {
      this.childrenFeelingsAboutHosting = childrenFeelingsAboutHosting;
   }

   public Integer getCreatedBy() {
      return this.createdBy;
   }

   public void setCreatedBy(Integer createdBy) {
      this.createdBy = createdBy;
   }

   public Date getCreatedOn() {
      return this.createdOn;
   }

   public void setCreatedOn(Date createdOn) {
      this.createdOn = createdOn;
   }

   public Byte getDiscussedPlacement() {
      return this.discussedPlacement;
   }

   public void setDiscussedPlacement(Byte discussedPlacement) {
      this.discussedPlacement = discussedPlacement;
   }

   public String getFinancialSituation() {
      return this.financialSituation;
   }

   public void setFinancialSituation(String financialSituation) {
      this.financialSituation = financialSituation;
   }

   public Byte getHasFamilyHostedBefore() {
      return this.hasFamilyHostedBefore;
   }

   public void setHasFamilyHostedBefore(Byte hasFamilyHostedBefore) {
      this.hasFamilyHostedBefore = hasFamilyHostedBefore;
   }

   public String getHomeCondition() {
      return this.homeCondition;
   }

   public void setHomeCondition(String homeCondition) {
      this.homeCondition = homeCondition;
   }

   public Byte getHostingCommitment() {
      return this.hostingCommitment;
   }

   public void setHostingCommitment(Byte hostingCommitment) {
      this.hostingCommitment = hostingCommitment;
   }

   public Date getInterviewDate() {
      return this.interviewDate;
   }

   public void setInterviewDate(Date interviewDate) {
      this.interviewDate = interviewDate;
   }

   public Byte getInterviewIsAtHome() {
      return this.interviewIsAtHome;
   }

   public void setInterviewIsAtHome(Byte interviewIsAtHome) {
      this.interviewIsAtHome = interviewIsAtHome;
   }

   public Byte getIsCompleted() {
      return this.isCompleted;
   }

   public void setIsCompleted(Byte isCompleted) {
      this.isCompleted = isCompleted;
   }

   public Byte getIsStarted() {
      return this.isStarted;
   }

   public void setIsStarted(Byte isStarted) {
      this.isStarted = isStarted;
   }

   public Integer getModifiedBy() {
      return this.modifiedBy;
   }

   public void setModifiedBy(Integer modifiedBy) {
      this.modifiedBy = modifiedBy;
   }

   public Date getModifiedOn() {
      return this.modifiedOn;
   }

   public void setModifiedOn(Date modifiedOn) {
      this.modifiedOn = modifiedOn;
   }

   public String getPreviousHostingDescription() {
      return this.previousHostingDescription;
   }

   public void setPreviousHostingDescription(String previousHostingDescription) {
      this.previousHostingDescription = previousHostingDescription;
   }

   public String getReasonsForHosting() {
      return this.reasonsForHosting;
   }

   public void setReasonsForHosting(String reasonsForHosting) {
      this.reasonsForHosting = reasonsForHosting;
   }

   public String getReligiousBackground() {
      return this.religiousBackground;
   }

   public void setReligiousBackground(String religiousBackground) {
      this.religiousBackground = religiousBackground;
   }

   public String getRoomSharedWithDescription() {
      return this.roomSharedWithDescription;
   }

   public void setRoomSharedWithDescription(String roomSharedWithDescription) {
      this.roomSharedWithDescription = roomSharedWithDescription;
   }

   public HostFamilySeason getHostFamilySeason() {
      return this.hostFamilySeason;
   }

   public void setHostFamilySeason(HostFamilySeason hostFamilySeason) {
      this.hostFamilySeason = hostFamilySeason;
   }

}