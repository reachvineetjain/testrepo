package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the AnnouncementType database table.
 * 
 */
@Entity
@Table(name="AnnouncementType")
@NamedQuery(name="AnnouncementType.findAll", query="SELECT a FROM AnnouncementType a")
public class AnnouncementType implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer announcementTypeId;

   @Column(length=50)
   private String announcementTypeName;

   //bi-directional many-to-one association to AnnouncementInformation
   @OneToMany(mappedBy="announcementType")
   private List<AnnouncementInformation> announcementInformations;

   //bi-directional many-to-one association to AnnouncementInformationHistory
   @OneToMany(mappedBy="announcementType")
   private List<AnnouncementInformationHistory> announcementInformationHistories;

   //bi-directional many-to-one association to FieldStaffAnnouncementResult
   @OneToMany(mappedBy="announcementType")
   private List<FieldStaffAnnouncementResult> fieldStaffAnnouncementResults;

   //bi-directional many-to-one association to HostFamilyAnnouncementResult
   @OneToMany(mappedBy="announcementType")
   private List<HostFamilyAnnouncementResult> hostFamilyAnnouncementResults;

   //bi-directional many-to-one association to ParticipantAnnouncementResult
   @OneToMany(mappedBy="announcementType")
   private List<ParticipantAnnouncementResult> participantAnnouncementResults;

   //bi-directional many-to-one association to PartnerAnnouncementResult
   @OneToMany(mappedBy="announcementType")
   private List<PartnerAnnouncementResult> partnerAnnouncementResults;

   public AnnouncementType() {
   }

   public Integer getAnnouncementTypeId() {
      return this.announcementTypeId;
   }

   public void setAnnouncementTypeId(Integer announcementTypeId) {
      this.announcementTypeId = announcementTypeId;
   }

   public String getAnnouncementTypeName() {
      return this.announcementTypeName;
   }

   public void setAnnouncementTypeName(String announcementTypeName) {
      this.announcementTypeName = announcementTypeName;
   }

   public List<AnnouncementInformation> getAnnouncementInformations() {
      return this.announcementInformations;
   }

   public void setAnnouncementInformations(List<AnnouncementInformation> announcementInformations) {
      this.announcementInformations = announcementInformations;
   }

   public AnnouncementInformation addAnnouncementInformation(AnnouncementInformation announcementInformation) {
      getAnnouncementInformations().add(announcementInformation);
      announcementInformation.setAnnouncementType(this);

      return announcementInformation;
   }

   public AnnouncementInformation removeAnnouncementInformation(AnnouncementInformation announcementInformation) {
      getAnnouncementInformations().remove(announcementInformation);
      announcementInformation.setAnnouncementType(null);

      return announcementInformation;
   }

   public List<AnnouncementInformationHistory> getAnnouncementInformationHistories() {
      return this.announcementInformationHistories;
   }

   public void setAnnouncementInformationHistories(List<AnnouncementInformationHistory> announcementInformationHistories) {
      this.announcementInformationHistories = announcementInformationHistories;
   }

   public AnnouncementInformationHistory addAnnouncementInformationHistory(AnnouncementInformationHistory announcementInformationHistory) {
      getAnnouncementInformationHistories().add(announcementInformationHistory);
      announcementInformationHistory.setAnnouncementType(this);

      return announcementInformationHistory;
   }

   public AnnouncementInformationHistory removeAnnouncementInformationHistory(AnnouncementInformationHistory announcementInformationHistory) {
      getAnnouncementInformationHistories().remove(announcementInformationHistory);
      announcementInformationHistory.setAnnouncementType(null);

      return announcementInformationHistory;
   }

   public List<FieldStaffAnnouncementResult> getFieldStaffAnnouncementResults() {
      return this.fieldStaffAnnouncementResults;
   }

   public void setFieldStaffAnnouncementResults(List<FieldStaffAnnouncementResult> fieldStaffAnnouncementResults) {
      this.fieldStaffAnnouncementResults = fieldStaffAnnouncementResults;
   }

   public FieldStaffAnnouncementResult addFieldStaffAnnouncementResult(FieldStaffAnnouncementResult fieldStaffAnnouncementResult) {
      getFieldStaffAnnouncementResults().add(fieldStaffAnnouncementResult);
      fieldStaffAnnouncementResult.setAnnouncementType(this);

      return fieldStaffAnnouncementResult;
   }

   public FieldStaffAnnouncementResult removeFieldStaffAnnouncementResult(FieldStaffAnnouncementResult fieldStaffAnnouncementResult) {
      getFieldStaffAnnouncementResults().remove(fieldStaffAnnouncementResult);
      fieldStaffAnnouncementResult.setAnnouncementType(null);

      return fieldStaffAnnouncementResult;
   }

   public List<HostFamilyAnnouncementResult> getHostFamilyAnnouncementResults() {
      return this.hostFamilyAnnouncementResults;
   }

   public void setHostFamilyAnnouncementResults(List<HostFamilyAnnouncementResult> hostFamilyAnnouncementResults) {
      this.hostFamilyAnnouncementResults = hostFamilyAnnouncementResults;
   }

   public HostFamilyAnnouncementResult addHostFamilyAnnouncementResult(HostFamilyAnnouncementResult hostFamilyAnnouncementResult) {
      getHostFamilyAnnouncementResults().add(hostFamilyAnnouncementResult);
      hostFamilyAnnouncementResult.setAnnouncementType(this);

      return hostFamilyAnnouncementResult;
   }

   public HostFamilyAnnouncementResult removeHostFamilyAnnouncementResult(HostFamilyAnnouncementResult hostFamilyAnnouncementResult) {
      getHostFamilyAnnouncementResults().remove(hostFamilyAnnouncementResult);
      hostFamilyAnnouncementResult.setAnnouncementType(null);

      return hostFamilyAnnouncementResult;
   }

   public List<ParticipantAnnouncementResult> getParticipantAnnouncementResults() {
      return this.participantAnnouncementResults;
   }

   public void setParticipantAnnouncementResults(List<ParticipantAnnouncementResult> participantAnnouncementResults) {
      this.participantAnnouncementResults = participantAnnouncementResults;
   }

   public ParticipantAnnouncementResult addParticipantAnnouncementResult(ParticipantAnnouncementResult participantAnnouncementResult) {
      getParticipantAnnouncementResults().add(participantAnnouncementResult);
      participantAnnouncementResult.setAnnouncementType(this);

      return participantAnnouncementResult;
   }

   public ParticipantAnnouncementResult removeParticipantAnnouncementResult(ParticipantAnnouncementResult participantAnnouncementResult) {
      getParticipantAnnouncementResults().remove(participantAnnouncementResult);
      participantAnnouncementResult.setAnnouncementType(null);

      return participantAnnouncementResult;
   }

   public List<PartnerAnnouncementResult> getPartnerAnnouncementResults() {
      return this.partnerAnnouncementResults;
   }

   public void setPartnerAnnouncementResults(List<PartnerAnnouncementResult> partnerAnnouncementResults) {
      this.partnerAnnouncementResults = partnerAnnouncementResults;
   }

   public PartnerAnnouncementResult addPartnerAnnouncementResult(PartnerAnnouncementResult partnerAnnouncementResult) {
      getPartnerAnnouncementResults().add(partnerAnnouncementResult);
      partnerAnnouncementResult.setAnnouncementType(this);

      return partnerAnnouncementResult;
   }

   public PartnerAnnouncementResult removePartnerAnnouncementResult(PartnerAnnouncementResult partnerAnnouncementResult) {
      getPartnerAnnouncementResults().remove(partnerAnnouncementResult);
      partnerAnnouncementResult.setAnnouncementType(null);

      return partnerAnnouncementResult;
   }

}