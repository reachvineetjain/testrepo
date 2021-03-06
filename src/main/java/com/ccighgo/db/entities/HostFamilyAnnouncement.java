package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the HostFamilyAnnouncement database table.
 * 
 */
@Entity
@Table(name="HostFamilyAnnouncement")
@NamedQuery(name="HostFamilyAnnouncement.findAll", query="SELECT h FROM HostFamilyAnnouncement h")
public class HostFamilyAnnouncement implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer hostFamilyAnnouncementId;

   private Byte active;

   @Lob
   private String announcement;

   private Byte archived;

   private Integer createdBy;

   private Timestamp createdOn;

   private Byte currentlyHosting;

   private Integer modifiedBy;

   private Timestamp modifiedOn;

   @Column(length=300)
   private String title;

   private Byte unplaced;

   //bi-directional many-to-one association to DepartmentProgram
   @ManyToOne
   @JoinColumn(name="departmentProgramId")
   private DepartmentProgram departmentProgram;

   //bi-directional many-to-one association to HostFamily
   @ManyToOne
   @JoinColumn(name="hostFamilyGoId")
   private HostFamily hostFamily;

   //bi-directional many-to-one association to Season
   @ManyToOne
   @JoinColumn(name="seasonId")
   private Season season;

   public HostFamilyAnnouncement() {
   }

   public Integer getHostFamilyAnnouncementId() {
      return this.hostFamilyAnnouncementId;
   }

   public void setHostFamilyAnnouncementId(Integer hostFamilyAnnouncementId) {
      this.hostFamilyAnnouncementId = hostFamilyAnnouncementId;
   }

   public Byte getActive() {
      return this.active;
   }

   public void setActive(Byte active) {
      this.active = active;
   }

   public String getAnnouncement() {
      return this.announcement;
   }

   public void setAnnouncement(String announcement) {
      this.announcement = announcement;
   }

   public Byte getArchived() {
      return this.archived;
   }

   public void setArchived(Byte archived) {
      this.archived = archived;
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

   public Byte getCurrentlyHosting() {
      return this.currentlyHosting;
   }

   public void setCurrentlyHosting(Byte currentlyHosting) {
      this.currentlyHosting = currentlyHosting;
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

   public String getTitle() {
      return this.title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public Byte getUnplaced() {
      return this.unplaced;
   }

   public void setUnplaced(Byte unplaced) {
      this.unplaced = unplaced;
   }

   public DepartmentProgram getDepartmentProgram() {
      return this.departmentProgram;
   }

   public void setDepartmentProgram(DepartmentProgram departmentProgram) {
      this.departmentProgram = departmentProgram;
   }

   public HostFamily getHostFamily() {
      return this.hostFamily;
   }

   public void setHostFamily(HostFamily hostFamily) {
      this.hostFamily = hostFamily;
   }

   public Season getSeason() {
      return this.season;
   }

   public void setSeason(Season season) {
      this.season = season;
   }

}