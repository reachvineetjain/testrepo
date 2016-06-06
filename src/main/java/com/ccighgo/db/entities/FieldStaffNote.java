package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the FieldStaffNote database table.
 * 
 */
@Entity
@Table(name="FieldStaffNote")
@NamedQuery(name="FieldStaffNote.findAll", query="SELECT f FROM FieldStaffNote f")
public class FieldStaffNote implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer fieldStaffNoteId;

   private Byte active;

   private Integer createdBy;

   private Timestamp createdOn;

   @Lob
   private String fieldStaffNote;

   private Byte hasRead;

   private Integer modifiedBy;

   private Timestamp modifiedOn;

   //bi-directional many-to-one association to FieldStaff
   @ManyToOne
   @JoinColumn(name="fieldStaffGoId")
   private FieldStaff fieldStaff;

   //bi-directional many-to-one association to FieldStaffNoteTopic
   @ManyToOne
   @JoinColumn(name="fieldStaffNoteTopicsId")
   private FieldStaffNoteTopic fieldStaffNoteTopic;

   public FieldStaffNote() {
   }

   public Integer getFieldStaffNoteId() {
      return this.fieldStaffNoteId;
   }

   public void setFieldStaffNoteId(Integer fieldStaffNoteId) {
      this.fieldStaffNoteId = fieldStaffNoteId;
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

   public String getFieldStaffNote() {
      return this.fieldStaffNote;
   }

   public void setFieldStaffNote(String fieldStaffNote) {
      this.fieldStaffNote = fieldStaffNote;
   }

   public Byte getHasRead() {
      return this.hasRead;
   }

   public void setHasRead(Byte hasRead) {
      this.hasRead = hasRead;
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

   public FieldStaff getFieldStaff() {
      return this.fieldStaff;
   }

   public void setFieldStaff(FieldStaff fieldStaff) {
      this.fieldStaff = fieldStaff;
   }

   public FieldStaffNoteTopic getFieldStaffNoteTopic() {
      return this.fieldStaffNoteTopic;
   }

   public void setFieldStaffNoteTopic(FieldStaffNoteTopic fieldStaffNoteTopic) {
      this.fieldStaffNoteTopic = fieldStaffNoteTopic;
   }

}