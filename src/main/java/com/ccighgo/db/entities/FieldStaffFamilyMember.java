package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the FieldStaffFamilyMember database table.
 * 
 */
@Entity
@Table(name="FieldStaffFamilyMember")
@NamedQuery(name="FieldStaffFamilyMember.findAll", query="SELECT f FROM FieldStaffFamilyMember f")
public class FieldStaffFamilyMember implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer fieldStaffFamilyMemberId;

   private Byte active;

   private Integer age;

   @Temporal(TemporalType.TIMESTAMP)
   private Date backgroundCheckDate;

   private Integer backgroundCheckPassed;

   private Integer createdBy;

   private Timestamp createdOn;

   @Column(length=100)
   private String firstName;

   @Column(length=100)
   private String lastName;

   private Byte livingAtHome;

   private Integer modifiedBy;

   private Timestamp modifiedOn;

   private Byte reasonForRejection;

   @Column(length=100)
   private String relationship;

   //bi-directional many-to-one association to FieldStaff
   @ManyToOne
   @JoinColumn(name="fieldStaffGoId")
   private FieldStaff fieldStaff;

   //bi-directional many-to-one association to LookupGender
   @ManyToOne
   @JoinColumn(name="genderId")
   private LookupGender lookupGender;

   public FieldStaffFamilyMember() {
   }

   public Integer getFieldStaffFamilyMemberId() {
      return this.fieldStaffFamilyMemberId;
   }

   public void setFieldStaffFamilyMemberId(Integer fieldStaffFamilyMemberId) {
      this.fieldStaffFamilyMemberId = fieldStaffFamilyMemberId;
   }

   public Byte getActive() {
      return this.active;
   }

   public void setActive(Byte active) {
      this.active = active;
   }

   public Integer getAge() {
      return this.age;
   }

   public void setAge(Integer age) {
      this.age = age;
   }

   public Date getBackgroundCheckDate() {
      return this.backgroundCheckDate;
   }

   public void setBackgroundCheckDate(Date backgroundCheckDate) {
      this.backgroundCheckDate = backgroundCheckDate;
   }

   public Integer getBackgroundCheckPassed() {
      return this.backgroundCheckPassed;
   }

   public void setBackgroundCheckPassed(Integer backgroundCheckPassed) {
      this.backgroundCheckPassed = backgroundCheckPassed;
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

   public String getFirstName() {
      return this.firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return this.lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public Byte getLivingAtHome() {
      return this.livingAtHome;
   }

   public void setLivingAtHome(Byte livingAtHome) {
      this.livingAtHome = livingAtHome;
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

   public Byte getReasonForRejection() {
      return this.reasonForRejection;
   }

   public void setReasonForRejection(Byte reasonForRejection) {
      this.reasonForRejection = reasonForRejection;
   }

   public String getRelationship() {
      return this.relationship;
   }

   public void setRelationship(String relationship) {
      this.relationship = relationship;
   }

   public FieldStaff getFieldStaff() {
      return this.fieldStaff;
   }

   public void setFieldStaff(FieldStaff fieldStaff) {
      this.fieldStaff = fieldStaff;
   }

   public LookupGender getLookupGender() {
      return this.lookupGender;
   }

   public void setLookupGender(LookupGender lookupGender) {
      this.lookupGender = lookupGender;
   }

}