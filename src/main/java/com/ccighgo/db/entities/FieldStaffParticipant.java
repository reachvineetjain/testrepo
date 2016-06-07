package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the FieldStaffParticipant database table.
 * 
 */
@Entity
@Table(name="FieldStaffParticipant")
@NamedQuery(name="FieldStaffParticipant.findAll", query="SELECT f FROM FieldStaffParticipant f")
public class FieldStaffParticipant implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer fieldStaffParticipantId;

   private Byte active;

   @Temporal(TemporalType.TIMESTAMP)
   private Date adjustedEndDate;

   private Integer createdBy;

   private Timestamp createdOn;

   @Temporal(TemporalType.TIMESTAMP)
   private Date endDate;

   @Temporal(TemporalType.TIMESTAMP)
   private Date holdExpirationDate;

   @Temporal(TemporalType.TIMESTAMP)
   private Date holdExpirationWarningDate;

   private Byte holdRequested;

   @Temporal(TemporalType.TIMESTAMP)
   private Date holdRequestedDate;

   private Byte isDirectPlacement;

   private Integer modifiedBy;

   private Timestamp modifiedOn;

   @Column(length=1000)
   private String note;

   @Temporal(TemporalType.TIMESTAMP)
   private Date startDate;

   //bi-directional many-to-one association to FieldStaff
   @ManyToOne
   @JoinColumn(name="fieldStaffGoId")
   private FieldStaff fieldStaff;

   //bi-directional many-to-one association to Participant
   @ManyToOne
   @JoinColumn(name="participantGoId")
   private Participant participant;

   public FieldStaffParticipant() {
   }

   public Integer getFieldStaffParticipantId() {
      return this.fieldStaffParticipantId;
   }

   public void setFieldStaffParticipantId(Integer fieldStaffParticipantId) {
      this.fieldStaffParticipantId = fieldStaffParticipantId;
   }

   public Byte getActive() {
      return this.active;
   }

   public void setActive(Byte active) {
      this.active = active;
   }

   public Date getAdjustedEndDate() {
      return this.adjustedEndDate;
   }

   public void setAdjustedEndDate(Date adjustedEndDate) {
      this.adjustedEndDate = adjustedEndDate;
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

   public Date getEndDate() {
      return this.endDate;
   }

   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }

   public Date getHoldExpirationDate() {
      return this.holdExpirationDate;
   }

   public void setHoldExpirationDate(Date holdExpirationDate) {
      this.holdExpirationDate = holdExpirationDate;
   }

   public Date getHoldExpirationWarningDate() {
      return this.holdExpirationWarningDate;
   }

   public void setHoldExpirationWarningDate(Date holdExpirationWarningDate) {
      this.holdExpirationWarningDate = holdExpirationWarningDate;
   }

   public Byte getHoldRequested() {
      return this.holdRequested;
   }

   public void setHoldRequested(Byte holdRequested) {
      this.holdRequested = holdRequested;
   }

   public Date getHoldRequestedDate() {
      return this.holdRequestedDate;
   }

   public void setHoldRequestedDate(Date holdRequestedDate) {
      this.holdRequestedDate = holdRequestedDate;
   }

   public Byte getIsDirectPlacement() {
      return this.isDirectPlacement;
   }

   public void setIsDirectPlacement(Byte isDirectPlacement) {
      this.isDirectPlacement = isDirectPlacement;
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

   public String getNote() {
      return this.note;
   }

   public void setNote(String note) {
      this.note = note;
   }

   public Date getStartDate() {
      return this.startDate;
   }

   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }

   public FieldStaff getFieldStaff() {
      return this.fieldStaff;
   }

   public void setFieldStaff(FieldStaff fieldStaff) {
      this.fieldStaff = fieldStaff;
   }

   public Participant getParticipant() {
      return this.participant;
   }

   public void setParticipant(Participant participant) {
      this.participant = participant;
   }

}