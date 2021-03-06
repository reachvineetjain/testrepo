package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the LookupDepartmentPrograms database table.
 * 
 */
@Entity
@Table(name="LookupDepartmentPrograms")
@NamedQuery(name="LookupDepartmentProgram.findAll", query="SELECT l FROM LookupDepartmentProgram l")
public class LookupDepartmentProgram implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer lookupDepartmentProgramId;

   @Column(nullable=false)
   private Integer createdBy;

   @Column(nullable=false)
   private Timestamp createdOn;

   @Column(length=100)
   private String description;

   @Column(nullable=false)
   private Integer modifiedBy;

   @Column(nullable=false)
   private Timestamp modifiedOn;

   @Column(nullable=false, length=50)
   private String programName;

   //bi-directional many-to-one association to AdminQuickStatsType
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<AdminQuickStatsType> adminQuickStatsTypes;

   //bi-directional many-to-one association to AdminQuickStatsTypeAggregate
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<AdminQuickStatsTypeAggregate> adminQuickStatsTypeAggregates;

   //bi-directional many-to-one association to AdminWorkQueueCategoryAggregate
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<AdminWorkQueueCategoryAggregate> adminWorkQueueCategoryAggregates;

   //bi-directional many-to-one association to AdminWorkQueueType
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<AdminWorkQueueType> adminWorkQueueTypes;

   //bi-directional many-to-one association to AdminWorkQueueTypeAggregate
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<AdminWorkQueueTypeAggregate> adminWorkQueueTypeAggregates;

   //bi-directional many-to-one association to AnnouncementInformation
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<AnnouncementInformation> announcementInformations;

   //bi-directional many-to-one association to AnnouncementInformationHistory
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<AnnouncementInformationHistory> announcementInformationHistories;

   //bi-directional many-to-one association to CCIStaffUserProgram
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<CCIStaffUserProgram> ccistaffUserPrograms;

   //bi-directional many-to-one association to FieldStaffQuickStatsCategoryAggregate
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<FieldStaffQuickStatsCategoryAggregate> fieldStaffQuickStatsCategoryAggregates;

   //bi-directional many-to-one association to FieldStaffQuickStatsType
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<FieldStaffQuickStatsType> fieldStaffQuickStatsTypes;

   //bi-directional many-to-one association to FieldStaffQuickStatsTypeAggregate
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<FieldStaffQuickStatsTypeAggregate> fieldStaffQuickStatsTypeAggregates;

   //bi-directional many-to-one association to FieldStaffWorkQueue
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<FieldStaffWorkQueue> fieldStaffWorkQueues;

   //bi-directional many-to-one association to FieldStaffWorkQueueCategoryAggregate
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<FieldStaffWorkQueueCategoryAggregate> fieldStaffWorkQueueCategoryAggregates;

   //bi-directional many-to-one association to FieldStaffWorkQueueType
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<FieldStaffWorkQueueType> fieldStaffWorkQueueTypes;

   //bi-directional many-to-one association to FieldStaffWorkQueueTypeAggregate
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<FieldStaffWorkQueueTypeAggregate> fieldStaffWorkQueueTypeAggregates;

   //bi-directional many-to-one association to HostFamilyQuickStatsCategoryAggregate
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<HostFamilyQuickStatsCategoryAggregate> hostFamilyQuickStatsCategoryAggregates;

   //bi-directional many-to-one association to HostFamilyQuickStatsType
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<HostFamilyQuickStatsType> hostFamilyQuickStatsTypes;

   //bi-directional many-to-one association to HostFamilyQuickStatsTypeAggregate
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<HostFamilyQuickStatsTypeAggregate> hostFamilyQuickStatsTypeAggregates;

   //bi-directional many-to-one association to HostFamilyWorkQueue
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<HostFamilyWorkQueue> hostFamilyWorkQueues;

   //bi-directional many-to-one association to HostFamilyWorkQueueCategoryAggregate
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<HostFamilyWorkQueueCategoryAggregate> hostFamilyWorkQueueCategoryAggregates;

   //bi-directional many-to-one association to HostFamilyWorkQueueType
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<HostFamilyWorkQueueType> hostFamilyWorkQueueTypes;

   //bi-directional many-to-one association to HostFamilyWorkQueueTypeAggregate
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<HostFamilyWorkQueueTypeAggregate> hostFamilyWorkQueueTypeAggregates;

   //bi-directional many-to-one association to LookupDepartment
   @ManyToOne
   @JoinColumn(name="departmentId", nullable=false)
   private LookupDepartment lookupDepartment;

   //bi-directional many-to-one association to PartnerHelpOptionProgram
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<PartnerHelpOptionProgram> partnerHelpOptionPrograms;

   //bi-directional many-to-one association to PartnerHelpRequest
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<PartnerHelpRequest> partnerHelpRequests;

   //bi-directional many-to-one association to PartnerPermission
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<PartnerPermission> partnerPermissions;

   //bi-directional many-to-one association to PartnerProgram
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<PartnerProgram> partnerPrograms;

   //bi-directional many-to-one association to PartnerQuickStatsCategoryAggregate
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<PartnerQuickStatsCategoryAggregate> partnerQuickStatsCategoryAggregates;

   //bi-directional many-to-one association to PartnerQuickStatsType
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<PartnerQuickStatsType> partnerQuickStatsTypes;

   //bi-directional many-to-one association to PartnerQuickStatsTypeAggregate
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<PartnerQuickStatsTypeAggregate> partnerQuickStatsTypeAggregates;

   //bi-directional many-to-one association to PartnerWorkQueue
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<PartnerWorkQueue> partnerWorkQueues;

   //bi-directional many-to-one association to PartnerWorkQueueCategoryAggregate
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<PartnerWorkQueueCategoryAggregate> partnerWorkQueueCategoryAggregates;

   //bi-directional many-to-one association to PartnerWorkQueueType
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<PartnerWorkQueueType> partnerWorkQueueTypes;

   //bi-directional many-to-one association to PartnerWorkQueueTypeAggregate
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<PartnerWorkQueueTypeAggregate> partnerWorkQueueTypeAggregates;

   //bi-directional many-to-one association to StateTypeResourcePermission
   @OneToMany(mappedBy="lookupDepartmentProgram")
   private List<StateTypeResourcePermission> stateTypeResourcePermissions;

   public LookupDepartmentProgram() {
   }

   public Integer getLookupDepartmentProgramId() {
      return this.lookupDepartmentProgramId;
   }

   public void setLookupDepartmentProgramId(Integer lookupDepartmentProgramId) {
      this.lookupDepartmentProgramId = lookupDepartmentProgramId;
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

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
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

   public String getProgramName() {
      return this.programName;
   }

   public void setProgramName(String programName) {
      this.programName = programName;
   }

   public List<AdminQuickStatsType> getAdminQuickStatsTypes() {
      return this.adminQuickStatsTypes;
   }

   public void setAdminQuickStatsTypes(List<AdminQuickStatsType> adminQuickStatsTypes) {
      this.adminQuickStatsTypes = adminQuickStatsTypes;
   }

   public AdminQuickStatsType addAdminQuickStatsType(AdminQuickStatsType adminQuickStatsType) {
      getAdminQuickStatsTypes().add(adminQuickStatsType);
      adminQuickStatsType.setLookupDepartmentProgram(this);

      return adminQuickStatsType;
   }

   public AdminQuickStatsType removeAdminQuickStatsType(AdminQuickStatsType adminQuickStatsType) {
      getAdminQuickStatsTypes().remove(adminQuickStatsType);
      adminQuickStatsType.setLookupDepartmentProgram(null);

      return adminQuickStatsType;
   }

   public List<AdminQuickStatsTypeAggregate> getAdminQuickStatsTypeAggregates() {
      return this.adminQuickStatsTypeAggregates;
   }

   public void setAdminQuickStatsTypeAggregates(List<AdminQuickStatsTypeAggregate> adminQuickStatsTypeAggregates) {
      this.adminQuickStatsTypeAggregates = adminQuickStatsTypeAggregates;
   }

   public AdminQuickStatsTypeAggregate addAdminQuickStatsTypeAggregate(AdminQuickStatsTypeAggregate adminQuickStatsTypeAggregate) {
      getAdminQuickStatsTypeAggregates().add(adminQuickStatsTypeAggregate);
      adminQuickStatsTypeAggregate.setLookupDepartmentProgram(this);

      return adminQuickStatsTypeAggregate;
   }

   public AdminQuickStatsTypeAggregate removeAdminQuickStatsTypeAggregate(AdminQuickStatsTypeAggregate adminQuickStatsTypeAggregate) {
      getAdminQuickStatsTypeAggregates().remove(adminQuickStatsTypeAggregate);
      adminQuickStatsTypeAggregate.setLookupDepartmentProgram(null);

      return adminQuickStatsTypeAggregate;
   }

   public List<AdminWorkQueueCategoryAggregate> getAdminWorkQueueCategoryAggregates() {
      return this.adminWorkQueueCategoryAggregates;
   }

   public void setAdminWorkQueueCategoryAggregates(List<AdminWorkQueueCategoryAggregate> adminWorkQueueCategoryAggregates) {
      this.adminWorkQueueCategoryAggregates = adminWorkQueueCategoryAggregates;
   }

   public AdminWorkQueueCategoryAggregate addAdminWorkQueueCategoryAggregate(AdminWorkQueueCategoryAggregate adminWorkQueueCategoryAggregate) {
      getAdminWorkQueueCategoryAggregates().add(adminWorkQueueCategoryAggregate);
      adminWorkQueueCategoryAggregate.setLookupDepartmentProgram(this);

      return adminWorkQueueCategoryAggregate;
   }

   public AdminWorkQueueCategoryAggregate removeAdminWorkQueueCategoryAggregate(AdminWorkQueueCategoryAggregate adminWorkQueueCategoryAggregate) {
      getAdminWorkQueueCategoryAggregates().remove(adminWorkQueueCategoryAggregate);
      adminWorkQueueCategoryAggregate.setLookupDepartmentProgram(null);

      return adminWorkQueueCategoryAggregate;
   }

   public List<AdminWorkQueueType> getAdminWorkQueueTypes() {
      return this.adminWorkQueueTypes;
   }

   public void setAdminWorkQueueTypes(List<AdminWorkQueueType> adminWorkQueueTypes) {
      this.adminWorkQueueTypes = adminWorkQueueTypes;
   }

   public AdminWorkQueueType addAdminWorkQueueType(AdminWorkQueueType adminWorkQueueType) {
      getAdminWorkQueueTypes().add(adminWorkQueueType);
      adminWorkQueueType.setLookupDepartmentProgram(this);

      return adminWorkQueueType;
   }

   public AdminWorkQueueType removeAdminWorkQueueType(AdminWorkQueueType adminWorkQueueType) {
      getAdminWorkQueueTypes().remove(adminWorkQueueType);
      adminWorkQueueType.setLookupDepartmentProgram(null);

      return adminWorkQueueType;
   }

   public List<AdminWorkQueueTypeAggregate> getAdminWorkQueueTypeAggregates() {
      return this.adminWorkQueueTypeAggregates;
   }

   public void setAdminWorkQueueTypeAggregates(List<AdminWorkQueueTypeAggregate> adminWorkQueueTypeAggregates) {
      this.adminWorkQueueTypeAggregates = adminWorkQueueTypeAggregates;
   }

   public AdminWorkQueueTypeAggregate addAdminWorkQueueTypeAggregate(AdminWorkQueueTypeAggregate adminWorkQueueTypeAggregate) {
      getAdminWorkQueueTypeAggregates().add(adminWorkQueueTypeAggregate);
      adminWorkQueueTypeAggregate.setLookupDepartmentProgram(this);

      return adminWorkQueueTypeAggregate;
   }

   public AdminWorkQueueTypeAggregate removeAdminWorkQueueTypeAggregate(AdminWorkQueueTypeAggregate adminWorkQueueTypeAggregate) {
      getAdminWorkQueueTypeAggregates().remove(adminWorkQueueTypeAggregate);
      adminWorkQueueTypeAggregate.setLookupDepartmentProgram(null);

      return adminWorkQueueTypeAggregate;
   }

   public List<AnnouncementInformation> getAnnouncementInformations() {
      return this.announcementInformations;
   }

   public void setAnnouncementInformations(List<AnnouncementInformation> announcementInformations) {
      this.announcementInformations = announcementInformations;
   }

   public AnnouncementInformation addAnnouncementInformation(AnnouncementInformation announcementInformation) {
      getAnnouncementInformations().add(announcementInformation);
      announcementInformation.setLookupDepartmentProgram(this);

      return announcementInformation;
   }

   public AnnouncementInformation removeAnnouncementInformation(AnnouncementInformation announcementInformation) {
      getAnnouncementInformations().remove(announcementInformation);
      announcementInformation.setLookupDepartmentProgram(null);

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
      announcementInformationHistory.setLookupDepartmentProgram(this);

      return announcementInformationHistory;
   }

   public AnnouncementInformationHistory removeAnnouncementInformationHistory(AnnouncementInformationHistory announcementInformationHistory) {
      getAnnouncementInformationHistories().remove(announcementInformationHistory);
      announcementInformationHistory.setLookupDepartmentProgram(null);

      return announcementInformationHistory;
   }

   public List<CCIStaffUserProgram> getCcistaffUserPrograms() {
      return this.ccistaffUserPrograms;
   }

   public void setCcistaffUserPrograms(List<CCIStaffUserProgram> ccistaffUserPrograms) {
      this.ccistaffUserPrograms = ccistaffUserPrograms;
   }

   public CCIStaffUserProgram addCcistaffUserProgram(CCIStaffUserProgram ccistaffUserProgram) {
      getCcistaffUserPrograms().add(ccistaffUserProgram);
      ccistaffUserProgram.setLookupDepartmentProgram(this);

      return ccistaffUserProgram;
   }

   public CCIStaffUserProgram removeCcistaffUserProgram(CCIStaffUserProgram ccistaffUserProgram) {
      getCcistaffUserPrograms().remove(ccistaffUserProgram);
      ccistaffUserProgram.setLookupDepartmentProgram(null);

      return ccistaffUserProgram;
   }

   public List<FieldStaffQuickStatsCategoryAggregate> getFieldStaffQuickStatsCategoryAggregates() {
      return this.fieldStaffQuickStatsCategoryAggregates;
   }

   public void setFieldStaffQuickStatsCategoryAggregates(List<FieldStaffQuickStatsCategoryAggregate> fieldStaffQuickStatsCategoryAggregates) {
      this.fieldStaffQuickStatsCategoryAggregates = fieldStaffQuickStatsCategoryAggregates;
   }

   public FieldStaffQuickStatsCategoryAggregate addFieldStaffQuickStatsCategoryAggregate(FieldStaffQuickStatsCategoryAggregate fieldStaffQuickStatsCategoryAggregate) {
      getFieldStaffQuickStatsCategoryAggregates().add(fieldStaffQuickStatsCategoryAggregate);
      fieldStaffQuickStatsCategoryAggregate.setLookupDepartmentProgram(this);

      return fieldStaffQuickStatsCategoryAggregate;
   }

   public FieldStaffQuickStatsCategoryAggregate removeFieldStaffQuickStatsCategoryAggregate(FieldStaffQuickStatsCategoryAggregate fieldStaffQuickStatsCategoryAggregate) {
      getFieldStaffQuickStatsCategoryAggregates().remove(fieldStaffQuickStatsCategoryAggregate);
      fieldStaffQuickStatsCategoryAggregate.setLookupDepartmentProgram(null);

      return fieldStaffQuickStatsCategoryAggregate;
   }

   public List<FieldStaffQuickStatsType> getFieldStaffQuickStatsTypes() {
      return this.fieldStaffQuickStatsTypes;
   }

   public void setFieldStaffQuickStatsTypes(List<FieldStaffQuickStatsType> fieldStaffQuickStatsTypes) {
      this.fieldStaffQuickStatsTypes = fieldStaffQuickStatsTypes;
   }

   public FieldStaffQuickStatsType addFieldStaffQuickStatsType(FieldStaffQuickStatsType fieldStaffQuickStatsType) {
      getFieldStaffQuickStatsTypes().add(fieldStaffQuickStatsType);
      fieldStaffQuickStatsType.setLookupDepartmentProgram(this);

      return fieldStaffQuickStatsType;
   }

   public FieldStaffQuickStatsType removeFieldStaffQuickStatsType(FieldStaffQuickStatsType fieldStaffQuickStatsType) {
      getFieldStaffQuickStatsTypes().remove(fieldStaffQuickStatsType);
      fieldStaffQuickStatsType.setLookupDepartmentProgram(null);

      return fieldStaffQuickStatsType;
   }

   public List<FieldStaffQuickStatsTypeAggregate> getFieldStaffQuickStatsTypeAggregates() {
      return this.fieldStaffQuickStatsTypeAggregates;
   }

   public void setFieldStaffQuickStatsTypeAggregates(List<FieldStaffQuickStatsTypeAggregate> fieldStaffQuickStatsTypeAggregates) {
      this.fieldStaffQuickStatsTypeAggregates = fieldStaffQuickStatsTypeAggregates;
   }

   public FieldStaffQuickStatsTypeAggregate addFieldStaffQuickStatsTypeAggregate(FieldStaffQuickStatsTypeAggregate fieldStaffQuickStatsTypeAggregate) {
      getFieldStaffQuickStatsTypeAggregates().add(fieldStaffQuickStatsTypeAggregate);
      fieldStaffQuickStatsTypeAggregate.setLookupDepartmentProgram(this);

      return fieldStaffQuickStatsTypeAggregate;
   }

   public FieldStaffQuickStatsTypeAggregate removeFieldStaffQuickStatsTypeAggregate(FieldStaffQuickStatsTypeAggregate fieldStaffQuickStatsTypeAggregate) {
      getFieldStaffQuickStatsTypeAggregates().remove(fieldStaffQuickStatsTypeAggregate);
      fieldStaffQuickStatsTypeAggregate.setLookupDepartmentProgram(null);

      return fieldStaffQuickStatsTypeAggregate;
   }

   public List<FieldStaffWorkQueue> getFieldStaffWorkQueues() {
      return this.fieldStaffWorkQueues;
   }

   public void setFieldStaffWorkQueues(List<FieldStaffWorkQueue> fieldStaffWorkQueues) {
      this.fieldStaffWorkQueues = fieldStaffWorkQueues;
   }

   public FieldStaffWorkQueue addFieldStaffWorkQueue(FieldStaffWorkQueue fieldStaffWorkQueue) {
      getFieldStaffWorkQueues().add(fieldStaffWorkQueue);
      fieldStaffWorkQueue.setLookupDepartmentProgram(this);

      return fieldStaffWorkQueue;
   }

   public FieldStaffWorkQueue removeFieldStaffWorkQueue(FieldStaffWorkQueue fieldStaffWorkQueue) {
      getFieldStaffWorkQueues().remove(fieldStaffWorkQueue);
      fieldStaffWorkQueue.setLookupDepartmentProgram(null);

      return fieldStaffWorkQueue;
   }

   public List<FieldStaffWorkQueueCategoryAggregate> getFieldStaffWorkQueueCategoryAggregates() {
      return this.fieldStaffWorkQueueCategoryAggregates;
   }

   public void setFieldStaffWorkQueueCategoryAggregates(List<FieldStaffWorkQueueCategoryAggregate> fieldStaffWorkQueueCategoryAggregates) {
      this.fieldStaffWorkQueueCategoryAggregates = fieldStaffWorkQueueCategoryAggregates;
   }

   public FieldStaffWorkQueueCategoryAggregate addFieldStaffWorkQueueCategoryAggregate(FieldStaffWorkQueueCategoryAggregate fieldStaffWorkQueueCategoryAggregate) {
      getFieldStaffWorkQueueCategoryAggregates().add(fieldStaffWorkQueueCategoryAggregate);
      fieldStaffWorkQueueCategoryAggregate.setLookupDepartmentProgram(this);

      return fieldStaffWorkQueueCategoryAggregate;
   }

   public FieldStaffWorkQueueCategoryAggregate removeFieldStaffWorkQueueCategoryAggregate(FieldStaffWorkQueueCategoryAggregate fieldStaffWorkQueueCategoryAggregate) {
      getFieldStaffWorkQueueCategoryAggregates().remove(fieldStaffWorkQueueCategoryAggregate);
      fieldStaffWorkQueueCategoryAggregate.setLookupDepartmentProgram(null);

      return fieldStaffWorkQueueCategoryAggregate;
   }

   public List<FieldStaffWorkQueueType> getFieldStaffWorkQueueTypes() {
      return this.fieldStaffWorkQueueTypes;
   }

   public void setFieldStaffWorkQueueTypes(List<FieldStaffWorkQueueType> fieldStaffWorkQueueTypes) {
      this.fieldStaffWorkQueueTypes = fieldStaffWorkQueueTypes;
   }

   public FieldStaffWorkQueueType addFieldStaffWorkQueueType(FieldStaffWorkQueueType fieldStaffWorkQueueType) {
      getFieldStaffWorkQueueTypes().add(fieldStaffWorkQueueType);
      fieldStaffWorkQueueType.setLookupDepartmentProgram(this);

      return fieldStaffWorkQueueType;
   }

   public FieldStaffWorkQueueType removeFieldStaffWorkQueueType(FieldStaffWorkQueueType fieldStaffWorkQueueType) {
      getFieldStaffWorkQueueTypes().remove(fieldStaffWorkQueueType);
      fieldStaffWorkQueueType.setLookupDepartmentProgram(null);

      return fieldStaffWorkQueueType;
   }

   public List<FieldStaffWorkQueueTypeAggregate> getFieldStaffWorkQueueTypeAggregates() {
      return this.fieldStaffWorkQueueTypeAggregates;
   }

   public void setFieldStaffWorkQueueTypeAggregates(List<FieldStaffWorkQueueTypeAggregate> fieldStaffWorkQueueTypeAggregates) {
      this.fieldStaffWorkQueueTypeAggregates = fieldStaffWorkQueueTypeAggregates;
   }

   public FieldStaffWorkQueueTypeAggregate addFieldStaffWorkQueueTypeAggregate(FieldStaffWorkQueueTypeAggregate fieldStaffWorkQueueTypeAggregate) {
      getFieldStaffWorkQueueTypeAggregates().add(fieldStaffWorkQueueTypeAggregate);
      fieldStaffWorkQueueTypeAggregate.setLookupDepartmentProgram(this);

      return fieldStaffWorkQueueTypeAggregate;
   }

   public FieldStaffWorkQueueTypeAggregate removeFieldStaffWorkQueueTypeAggregate(FieldStaffWorkQueueTypeAggregate fieldStaffWorkQueueTypeAggregate) {
      getFieldStaffWorkQueueTypeAggregates().remove(fieldStaffWorkQueueTypeAggregate);
      fieldStaffWorkQueueTypeAggregate.setLookupDepartmentProgram(null);

      return fieldStaffWorkQueueTypeAggregate;
   }

   public List<HostFamilyQuickStatsCategoryAggregate> getHostFamilyQuickStatsCategoryAggregates() {
      return this.hostFamilyQuickStatsCategoryAggregates;
   }

   public void setHostFamilyQuickStatsCategoryAggregates(List<HostFamilyQuickStatsCategoryAggregate> hostFamilyQuickStatsCategoryAggregates) {
      this.hostFamilyQuickStatsCategoryAggregates = hostFamilyQuickStatsCategoryAggregates;
   }

   public HostFamilyQuickStatsCategoryAggregate addHostFamilyQuickStatsCategoryAggregate(HostFamilyQuickStatsCategoryAggregate hostFamilyQuickStatsCategoryAggregate) {
      getHostFamilyQuickStatsCategoryAggregates().add(hostFamilyQuickStatsCategoryAggregate);
      hostFamilyQuickStatsCategoryAggregate.setLookupDepartmentProgram(this);

      return hostFamilyQuickStatsCategoryAggregate;
   }

   public HostFamilyQuickStatsCategoryAggregate removeHostFamilyQuickStatsCategoryAggregate(HostFamilyQuickStatsCategoryAggregate hostFamilyQuickStatsCategoryAggregate) {
      getHostFamilyQuickStatsCategoryAggregates().remove(hostFamilyQuickStatsCategoryAggregate);
      hostFamilyQuickStatsCategoryAggregate.setLookupDepartmentProgram(null);

      return hostFamilyQuickStatsCategoryAggregate;
   }

   public List<HostFamilyQuickStatsType> getHostFamilyQuickStatsTypes() {
      return this.hostFamilyQuickStatsTypes;
   }

   public void setHostFamilyQuickStatsTypes(List<HostFamilyQuickStatsType> hostFamilyQuickStatsTypes) {
      this.hostFamilyQuickStatsTypes = hostFamilyQuickStatsTypes;
   }

   public HostFamilyQuickStatsType addHostFamilyQuickStatsType(HostFamilyQuickStatsType hostFamilyQuickStatsType) {
      getHostFamilyQuickStatsTypes().add(hostFamilyQuickStatsType);
      hostFamilyQuickStatsType.setLookupDepartmentProgram(this);

      return hostFamilyQuickStatsType;
   }

   public HostFamilyQuickStatsType removeHostFamilyQuickStatsType(HostFamilyQuickStatsType hostFamilyQuickStatsType) {
      getHostFamilyQuickStatsTypes().remove(hostFamilyQuickStatsType);
      hostFamilyQuickStatsType.setLookupDepartmentProgram(null);

      return hostFamilyQuickStatsType;
   }

   public List<HostFamilyQuickStatsTypeAggregate> getHostFamilyQuickStatsTypeAggregates() {
      return this.hostFamilyQuickStatsTypeAggregates;
   }

   public void setHostFamilyQuickStatsTypeAggregates(List<HostFamilyQuickStatsTypeAggregate> hostFamilyQuickStatsTypeAggregates) {
      this.hostFamilyQuickStatsTypeAggregates = hostFamilyQuickStatsTypeAggregates;
   }

   public HostFamilyQuickStatsTypeAggregate addHostFamilyQuickStatsTypeAggregate(HostFamilyQuickStatsTypeAggregate hostFamilyQuickStatsTypeAggregate) {
      getHostFamilyQuickStatsTypeAggregates().add(hostFamilyQuickStatsTypeAggregate);
      hostFamilyQuickStatsTypeAggregate.setLookupDepartmentProgram(this);

      return hostFamilyQuickStatsTypeAggregate;
   }

   public HostFamilyQuickStatsTypeAggregate removeHostFamilyQuickStatsTypeAggregate(HostFamilyQuickStatsTypeAggregate hostFamilyQuickStatsTypeAggregate) {
      getHostFamilyQuickStatsTypeAggregates().remove(hostFamilyQuickStatsTypeAggregate);
      hostFamilyQuickStatsTypeAggregate.setLookupDepartmentProgram(null);

      return hostFamilyQuickStatsTypeAggregate;
   }

   public List<HostFamilyWorkQueue> getHostFamilyWorkQueues() {
      return this.hostFamilyWorkQueues;
   }

   public void setHostFamilyWorkQueues(List<HostFamilyWorkQueue> hostFamilyWorkQueues) {
      this.hostFamilyWorkQueues = hostFamilyWorkQueues;
   }

   public HostFamilyWorkQueue addHostFamilyWorkQueue(HostFamilyWorkQueue hostFamilyWorkQueue) {
      getHostFamilyWorkQueues().add(hostFamilyWorkQueue);
      hostFamilyWorkQueue.setLookupDepartmentProgram(this);

      return hostFamilyWorkQueue;
   }

   public HostFamilyWorkQueue removeHostFamilyWorkQueue(HostFamilyWorkQueue hostFamilyWorkQueue) {
      getHostFamilyWorkQueues().remove(hostFamilyWorkQueue);
      hostFamilyWorkQueue.setLookupDepartmentProgram(null);

      return hostFamilyWorkQueue;
   }

   public List<HostFamilyWorkQueueCategoryAggregate> getHostFamilyWorkQueueCategoryAggregates() {
      return this.hostFamilyWorkQueueCategoryAggregates;
   }

   public void setHostFamilyWorkQueueCategoryAggregates(List<HostFamilyWorkQueueCategoryAggregate> hostFamilyWorkQueueCategoryAggregates) {
      this.hostFamilyWorkQueueCategoryAggregates = hostFamilyWorkQueueCategoryAggregates;
   }

   public HostFamilyWorkQueueCategoryAggregate addHostFamilyWorkQueueCategoryAggregate(HostFamilyWorkQueueCategoryAggregate hostFamilyWorkQueueCategoryAggregate) {
      getHostFamilyWorkQueueCategoryAggregates().add(hostFamilyWorkQueueCategoryAggregate);
      hostFamilyWorkQueueCategoryAggregate.setLookupDepartmentProgram(this);

      return hostFamilyWorkQueueCategoryAggregate;
   }

   public HostFamilyWorkQueueCategoryAggregate removeHostFamilyWorkQueueCategoryAggregate(HostFamilyWorkQueueCategoryAggregate hostFamilyWorkQueueCategoryAggregate) {
      getHostFamilyWorkQueueCategoryAggregates().remove(hostFamilyWorkQueueCategoryAggregate);
      hostFamilyWorkQueueCategoryAggregate.setLookupDepartmentProgram(null);

      return hostFamilyWorkQueueCategoryAggregate;
   }

   public List<HostFamilyWorkQueueType> getHostFamilyWorkQueueTypes() {
      return this.hostFamilyWorkQueueTypes;
   }

   public void setHostFamilyWorkQueueTypes(List<HostFamilyWorkQueueType> hostFamilyWorkQueueTypes) {
      this.hostFamilyWorkQueueTypes = hostFamilyWorkQueueTypes;
   }

   public HostFamilyWorkQueueType addHostFamilyWorkQueueType(HostFamilyWorkQueueType hostFamilyWorkQueueType) {
      getHostFamilyWorkQueueTypes().add(hostFamilyWorkQueueType);
      hostFamilyWorkQueueType.setLookupDepartmentProgram(this);

      return hostFamilyWorkQueueType;
   }

   public HostFamilyWorkQueueType removeHostFamilyWorkQueueType(HostFamilyWorkQueueType hostFamilyWorkQueueType) {
      getHostFamilyWorkQueueTypes().remove(hostFamilyWorkQueueType);
      hostFamilyWorkQueueType.setLookupDepartmentProgram(null);

      return hostFamilyWorkQueueType;
   }

   public List<HostFamilyWorkQueueTypeAggregate> getHostFamilyWorkQueueTypeAggregates() {
      return this.hostFamilyWorkQueueTypeAggregates;
   }

   public void setHostFamilyWorkQueueTypeAggregates(List<HostFamilyWorkQueueTypeAggregate> hostFamilyWorkQueueTypeAggregates) {
      this.hostFamilyWorkQueueTypeAggregates = hostFamilyWorkQueueTypeAggregates;
   }

   public HostFamilyWorkQueueTypeAggregate addHostFamilyWorkQueueTypeAggregate(HostFamilyWorkQueueTypeAggregate hostFamilyWorkQueueTypeAggregate) {
      getHostFamilyWorkQueueTypeAggregates().add(hostFamilyWorkQueueTypeAggregate);
      hostFamilyWorkQueueTypeAggregate.setLookupDepartmentProgram(this);

      return hostFamilyWorkQueueTypeAggregate;
   }

   public HostFamilyWorkQueueTypeAggregate removeHostFamilyWorkQueueTypeAggregate(HostFamilyWorkQueueTypeAggregate hostFamilyWorkQueueTypeAggregate) {
      getHostFamilyWorkQueueTypeAggregates().remove(hostFamilyWorkQueueTypeAggregate);
      hostFamilyWorkQueueTypeAggregate.setLookupDepartmentProgram(null);

      return hostFamilyWorkQueueTypeAggregate;
   }

   public LookupDepartment getLookupDepartment() {
      return this.lookupDepartment;
   }

   public void setLookupDepartment(LookupDepartment lookupDepartment) {
      this.lookupDepartment = lookupDepartment;
   }

   public List<PartnerHelpOptionProgram> getPartnerHelpOptionPrograms() {
      return this.partnerHelpOptionPrograms;
   }

   public void setPartnerHelpOptionPrograms(List<PartnerHelpOptionProgram> partnerHelpOptionPrograms) {
      this.partnerHelpOptionPrograms = partnerHelpOptionPrograms;
   }

   public PartnerHelpOptionProgram addPartnerHelpOptionProgram(PartnerHelpOptionProgram partnerHelpOptionProgram) {
      getPartnerHelpOptionPrograms().add(partnerHelpOptionProgram);
      partnerHelpOptionProgram.setLookupDepartmentProgram(this);

      return partnerHelpOptionProgram;
   }

   public PartnerHelpOptionProgram removePartnerHelpOptionProgram(PartnerHelpOptionProgram partnerHelpOptionProgram) {
      getPartnerHelpOptionPrograms().remove(partnerHelpOptionProgram);
      partnerHelpOptionProgram.setLookupDepartmentProgram(null);

      return partnerHelpOptionProgram;
   }

   public List<PartnerHelpRequest> getPartnerHelpRequests() {
      return this.partnerHelpRequests;
   }

   public void setPartnerHelpRequests(List<PartnerHelpRequest> partnerHelpRequests) {
      this.partnerHelpRequests = partnerHelpRequests;
   }

   public PartnerHelpRequest addPartnerHelpRequest(PartnerHelpRequest partnerHelpRequest) {
      getPartnerHelpRequests().add(partnerHelpRequest);
      partnerHelpRequest.setLookupDepartmentProgram(this);

      return partnerHelpRequest;
   }

   public PartnerHelpRequest removePartnerHelpRequest(PartnerHelpRequest partnerHelpRequest) {
      getPartnerHelpRequests().remove(partnerHelpRequest);
      partnerHelpRequest.setLookupDepartmentProgram(null);

      return partnerHelpRequest;
   }

   public List<PartnerPermission> getPartnerPermissions() {
      return this.partnerPermissions;
   }

   public void setPartnerPermissions(List<PartnerPermission> partnerPermissions) {
      this.partnerPermissions = partnerPermissions;
   }

   public PartnerPermission addPartnerPermission(PartnerPermission partnerPermission) {
      getPartnerPermissions().add(partnerPermission);
      partnerPermission.setLookupDepartmentProgram(this);

      return partnerPermission;
   }

   public PartnerPermission removePartnerPermission(PartnerPermission partnerPermission) {
      getPartnerPermissions().remove(partnerPermission);
      partnerPermission.setLookupDepartmentProgram(null);

      return partnerPermission;
   }

   public List<PartnerProgram> getPartnerPrograms() {
      return this.partnerPrograms;
   }

   public void setPartnerPrograms(List<PartnerProgram> partnerPrograms) {
      this.partnerPrograms = partnerPrograms;
   }

   public PartnerProgram addPartnerProgram(PartnerProgram partnerProgram) {
      getPartnerPrograms().add(partnerProgram);
      partnerProgram.setLookupDepartmentProgram(this);

      return partnerProgram;
   }

   public PartnerProgram removePartnerProgram(PartnerProgram partnerProgram) {
      getPartnerPrograms().remove(partnerProgram);
      partnerProgram.setLookupDepartmentProgram(null);

      return partnerProgram;
   }

   public List<PartnerQuickStatsCategoryAggregate> getPartnerQuickStatsCategoryAggregates() {
      return this.partnerQuickStatsCategoryAggregates;
   }

   public void setPartnerQuickStatsCategoryAggregates(List<PartnerQuickStatsCategoryAggregate> partnerQuickStatsCategoryAggregates) {
      this.partnerQuickStatsCategoryAggregates = partnerQuickStatsCategoryAggregates;
   }

   public PartnerQuickStatsCategoryAggregate addPartnerQuickStatsCategoryAggregate(PartnerQuickStatsCategoryAggregate partnerQuickStatsCategoryAggregate) {
      getPartnerQuickStatsCategoryAggregates().add(partnerQuickStatsCategoryAggregate);
      partnerQuickStatsCategoryAggregate.setLookupDepartmentProgram(this);

      return partnerQuickStatsCategoryAggregate;
   }

   public PartnerQuickStatsCategoryAggregate removePartnerQuickStatsCategoryAggregate(PartnerQuickStatsCategoryAggregate partnerQuickStatsCategoryAggregate) {
      getPartnerQuickStatsCategoryAggregates().remove(partnerQuickStatsCategoryAggregate);
      partnerQuickStatsCategoryAggregate.setLookupDepartmentProgram(null);

      return partnerQuickStatsCategoryAggregate;
   }

   public List<PartnerQuickStatsType> getPartnerQuickStatsTypes() {
      return this.partnerQuickStatsTypes;
   }

   public void setPartnerQuickStatsTypes(List<PartnerQuickStatsType> partnerQuickStatsTypes) {
      this.partnerQuickStatsTypes = partnerQuickStatsTypes;
   }

   public PartnerQuickStatsType addPartnerQuickStatsType(PartnerQuickStatsType partnerQuickStatsType) {
      getPartnerQuickStatsTypes().add(partnerQuickStatsType);
      partnerQuickStatsType.setLookupDepartmentProgram(this);

      return partnerQuickStatsType;
   }

   public PartnerQuickStatsType removePartnerQuickStatsType(PartnerQuickStatsType partnerQuickStatsType) {
      getPartnerQuickStatsTypes().remove(partnerQuickStatsType);
      partnerQuickStatsType.setLookupDepartmentProgram(null);

      return partnerQuickStatsType;
   }

   public List<PartnerQuickStatsTypeAggregate> getPartnerQuickStatsTypeAggregates() {
      return this.partnerQuickStatsTypeAggregates;
   }

   public void setPartnerQuickStatsTypeAggregates(List<PartnerQuickStatsTypeAggregate> partnerQuickStatsTypeAggregates) {
      this.partnerQuickStatsTypeAggregates = partnerQuickStatsTypeAggregates;
   }

   public PartnerQuickStatsTypeAggregate addPartnerQuickStatsTypeAggregate(PartnerQuickStatsTypeAggregate partnerQuickStatsTypeAggregate) {
      getPartnerQuickStatsTypeAggregates().add(partnerQuickStatsTypeAggregate);
      partnerQuickStatsTypeAggregate.setLookupDepartmentProgram(this);

      return partnerQuickStatsTypeAggregate;
   }

   public PartnerQuickStatsTypeAggregate removePartnerQuickStatsTypeAggregate(PartnerQuickStatsTypeAggregate partnerQuickStatsTypeAggregate) {
      getPartnerQuickStatsTypeAggregates().remove(partnerQuickStatsTypeAggregate);
      partnerQuickStatsTypeAggregate.setLookupDepartmentProgram(null);

      return partnerQuickStatsTypeAggregate;
   }

   public List<PartnerWorkQueue> getPartnerWorkQueues() {
      return this.partnerWorkQueues;
   }

   public void setPartnerWorkQueues(List<PartnerWorkQueue> partnerWorkQueues) {
      this.partnerWorkQueues = partnerWorkQueues;
   }

   public PartnerWorkQueue addPartnerWorkQueue(PartnerWorkQueue partnerWorkQueue) {
      getPartnerWorkQueues().add(partnerWorkQueue);
      partnerWorkQueue.setLookupDepartmentProgram(this);

      return partnerWorkQueue;
   }

   public PartnerWorkQueue removePartnerWorkQueue(PartnerWorkQueue partnerWorkQueue) {
      getPartnerWorkQueues().remove(partnerWorkQueue);
      partnerWorkQueue.setLookupDepartmentProgram(null);

      return partnerWorkQueue;
   }

   public List<PartnerWorkQueueCategoryAggregate> getPartnerWorkQueueCategoryAggregates() {
      return this.partnerWorkQueueCategoryAggregates;
   }

   public void setPartnerWorkQueueCategoryAggregates(List<PartnerWorkQueueCategoryAggregate> partnerWorkQueueCategoryAggregates) {
      this.partnerWorkQueueCategoryAggregates = partnerWorkQueueCategoryAggregates;
   }

   public PartnerWorkQueueCategoryAggregate addPartnerWorkQueueCategoryAggregate(PartnerWorkQueueCategoryAggregate partnerWorkQueueCategoryAggregate) {
      getPartnerWorkQueueCategoryAggregates().add(partnerWorkQueueCategoryAggregate);
      partnerWorkQueueCategoryAggregate.setLookupDepartmentProgram(this);

      return partnerWorkQueueCategoryAggregate;
   }

   public PartnerWorkQueueCategoryAggregate removePartnerWorkQueueCategoryAggregate(PartnerWorkQueueCategoryAggregate partnerWorkQueueCategoryAggregate) {
      getPartnerWorkQueueCategoryAggregates().remove(partnerWorkQueueCategoryAggregate);
      partnerWorkQueueCategoryAggregate.setLookupDepartmentProgram(null);

      return partnerWorkQueueCategoryAggregate;
   }

   public List<PartnerWorkQueueType> getPartnerWorkQueueTypes() {
      return this.partnerWorkQueueTypes;
   }

   public void setPartnerWorkQueueTypes(List<PartnerWorkQueueType> partnerWorkQueueTypes) {
      this.partnerWorkQueueTypes = partnerWorkQueueTypes;
   }

   public PartnerWorkQueueType addPartnerWorkQueueType(PartnerWorkQueueType partnerWorkQueueType) {
      getPartnerWorkQueueTypes().add(partnerWorkQueueType);
      partnerWorkQueueType.setLookupDepartmentProgram(this);

      return partnerWorkQueueType;
   }

   public PartnerWorkQueueType removePartnerWorkQueueType(PartnerWorkQueueType partnerWorkQueueType) {
      getPartnerWorkQueueTypes().remove(partnerWorkQueueType);
      partnerWorkQueueType.setLookupDepartmentProgram(null);

      return partnerWorkQueueType;
   }

   public List<PartnerWorkQueueTypeAggregate> getPartnerWorkQueueTypeAggregates() {
      return this.partnerWorkQueueTypeAggregates;
   }

   public void setPartnerWorkQueueTypeAggregates(List<PartnerWorkQueueTypeAggregate> partnerWorkQueueTypeAggregates) {
      this.partnerWorkQueueTypeAggregates = partnerWorkQueueTypeAggregates;
   }

   public PartnerWorkQueueTypeAggregate addPartnerWorkQueueTypeAggregate(PartnerWorkQueueTypeAggregate partnerWorkQueueTypeAggregate) {
      getPartnerWorkQueueTypeAggregates().add(partnerWorkQueueTypeAggregate);
      partnerWorkQueueTypeAggregate.setLookupDepartmentProgram(this);

      return partnerWorkQueueTypeAggregate;
   }

   public PartnerWorkQueueTypeAggregate removePartnerWorkQueueTypeAggregate(PartnerWorkQueueTypeAggregate partnerWorkQueueTypeAggregate) {
      getPartnerWorkQueueTypeAggregates().remove(partnerWorkQueueTypeAggregate);
      partnerWorkQueueTypeAggregate.setLookupDepartmentProgram(null);

      return partnerWorkQueueTypeAggregate;
   }

   public List<StateTypeResourcePermission> getStateTypeResourcePermissions() {
      return this.stateTypeResourcePermissions;
   }

   public void setStateTypeResourcePermissions(List<StateTypeResourcePermission> stateTypeResourcePermissions) {
      this.stateTypeResourcePermissions = stateTypeResourcePermissions;
   }

   public StateTypeResourcePermission addStateTypeResourcePermission(StateTypeResourcePermission stateTypeResourcePermission) {
      getStateTypeResourcePermissions().add(stateTypeResourcePermission);
      stateTypeResourcePermission.setLookupDepartmentProgram(this);

      return stateTypeResourcePermission;
   }

   public StateTypeResourcePermission removeStateTypeResourcePermission(StateTypeResourcePermission stateTypeResourcePermission) {
      getStateTypeResourcePermissions().remove(stateTypeResourcePermission);
      stateTypeResourcePermission.setLookupDepartmentProgram(null);

      return stateTypeResourcePermission;
   }

}