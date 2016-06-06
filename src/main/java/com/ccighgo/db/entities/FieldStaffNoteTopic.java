package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the FieldStaffNoteTopics database table.
 * 
 */
@Entity
@Table(name="FieldStaffNoteTopics")
@NamedQuery(name="FieldStaffNoteTopic.findAll", query="SELECT f FROM FieldStaffNoteTopic f")
public class FieldStaffNoteTopic implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer fieldStaffNoteTopicsId;

   private Integer createdBy;

   @Column(nullable=false)
   private Timestamp createdOn;

   @Column(length=50)
   private String fieldStaffNoteTopicName;

   private Byte isPublic;

   private Integer modifiedBy;

   @Column(nullable=false)
   private Timestamp modifiedOn;

   @Column(length=50)
   private String title;

   //bi-directional many-to-one association to FieldStaffNote
   @OneToMany(mappedBy="fieldStaffNoteTopic")
   private List<FieldStaffNote> fieldStaffNotes;

   //bi-directional many-to-one association to FieldStaff
   @ManyToOne
   @JoinColumn(name="fieldStaffGoId")
   private FieldStaff fieldStaff;

   public FieldStaffNoteTopic() {
   }

   public Integer getFieldStaffNoteTopicsId() {
      return this.fieldStaffNoteTopicsId;
   }

   public void setFieldStaffNoteTopicsId(Integer fieldStaffNoteTopicsId) {
      this.fieldStaffNoteTopicsId = fieldStaffNoteTopicsId;
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

   public String getFieldStaffNoteTopicName() {
      return this.fieldStaffNoteTopicName;
   }

   public void setFieldStaffNoteTopicName(String fieldStaffNoteTopicName) {
      this.fieldStaffNoteTopicName = fieldStaffNoteTopicName;
   }

   public Byte getIsPublic() {
      return this.isPublic;
   }

   public void setIsPublic(Byte isPublic) {
      this.isPublic = isPublic;
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

   public List<FieldStaffNote> getFieldStaffNotes() {
      return this.fieldStaffNotes;
   }

   public void setFieldStaffNotes(List<FieldStaffNote> fieldStaffNotes) {
      this.fieldStaffNotes = fieldStaffNotes;
   }

   public FieldStaffNote addFieldStaffNote(FieldStaffNote fieldStaffNote) {
      getFieldStaffNotes().add(fieldStaffNote);
      fieldStaffNote.setFieldStaffNoteTopic(this);

      return fieldStaffNote;
   }

   public FieldStaffNote removeFieldStaffNote(FieldStaffNote fieldStaffNote) {
      getFieldStaffNotes().remove(fieldStaffNote);
      fieldStaffNote.setFieldStaffNoteTopic(null);

      return fieldStaffNote;
   }

   public FieldStaff getFieldStaff() {
      return this.fieldStaff;
   }

   public void setFieldStaff(FieldStaff fieldStaff) {
      this.fieldStaff = fieldStaff;
   }

}