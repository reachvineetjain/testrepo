package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the FieldStaffAnnouncementResults database table.
 * 
 */
@Entity
@Table(name="FieldStaffAnnouncementResults")
@NamedQuery(name="FieldStaffAnnouncementResult.findAll", query="SELECT f FROM FieldStaffAnnouncementResult f")
public class FieldStaffAnnouncementResult implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer fieldStaffAnnouncementResultsId;

   private Integer createdBy;

   @Temporal(TemporalType.TIMESTAMP)
   private Date createdOn;

   @Column(length=200)
   private String fieldStaffAnnouncementResultOption;

   //bi-directional many-to-one association to AnnouncementCreateUserType
   @ManyToOne
   @JoinColumn(name="announcementCreateTypeUserId")
   private AnnouncementCreateUserType announcementCreateUserType;

   //bi-directional many-to-one association to AnnouncementType
   @ManyToOne
   @JoinColumn(name="announcementTypeId")
   private AnnouncementType announcementType;

   //bi-directional many-to-one association to FieldStaff
   @ManyToOne
   @JoinColumn(name="fieldStaffGoId")
   private FieldStaff fieldStaff1;

   //bi-directional many-to-one association to FieldStaff
   @ManyToOne
   @JoinColumn(name="fieldStaffTypeId")
   private FieldStaff fieldStaff2;

   public FieldStaffAnnouncementResult() {
   }

   public Integer getFieldStaffAnnouncementResultsId() {
      return this.fieldStaffAnnouncementResultsId;
   }

   public void setFieldStaffAnnouncementResultsId(Integer fieldStaffAnnouncementResultsId) {
      this.fieldStaffAnnouncementResultsId = fieldStaffAnnouncementResultsId;
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

   public String getFieldStaffAnnouncementResultOption() {
      return this.fieldStaffAnnouncementResultOption;
   }

   public void setFieldStaffAnnouncementResultOption(String fieldStaffAnnouncementResultOption) {
      this.fieldStaffAnnouncementResultOption = fieldStaffAnnouncementResultOption;
   }

   public AnnouncementCreateUserType getAnnouncementCreateUserType() {
      return this.announcementCreateUserType;
   }

   public void setAnnouncementCreateUserType(AnnouncementCreateUserType announcementCreateUserType) {
      this.announcementCreateUserType = announcementCreateUserType;
   }

   public AnnouncementType getAnnouncementType() {
      return this.announcementType;
   }

   public void setAnnouncementType(AnnouncementType announcementType) {
      this.announcementType = announcementType;
   }

   public FieldStaff getFieldStaff1() {
      return this.fieldStaff1;
   }

   public void setFieldStaff1(FieldStaff fieldStaff1) {
      this.fieldStaff1 = fieldStaff1;
   }

   public FieldStaff getFieldStaff2() {
      return this.fieldStaff2;
   }

   public void setFieldStaff2(FieldStaff fieldStaff2) {
      this.fieldStaff2 = fieldStaff2;
   }

}