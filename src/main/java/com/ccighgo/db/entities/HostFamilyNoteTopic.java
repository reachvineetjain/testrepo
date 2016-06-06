package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the HostFamilyNoteTopics database table.
 * 
 */
@Entity
@Table(name="HostFamilyNoteTopics")
@NamedQuery(name="HostFamilyNoteTopic.findAll", query="SELECT h FROM HostFamilyNoteTopic h")
public class HostFamilyNoteTopic implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer hostFamilyNoteTopicsId;

   private Integer createdBy;

   private Timestamp createdOn;

   private Byte isPublic;

   private Integer modifiedBy;

   private Timestamp modifiedOn;

   @Column(length=50)
   private String title;

   @Column(length=50)
   private String topicName;

   //bi-directional many-to-one association to HostFamilyNote
   @OneToMany(mappedBy="hostFamilyNoteTopic")
   private List<HostFamilyNote> hostFamilyNotes;

   //bi-directional many-to-one association to HostFamily
   @ManyToOne
   @JoinColumn(name="hostFamilygoId")
   private HostFamily hostFamily;

   public HostFamilyNoteTopic() {
   }

   public Integer getHostFamilyNoteTopicsId() {
      return this.hostFamilyNoteTopicsId;
   }

   public void setHostFamilyNoteTopicsId(Integer hostFamilyNoteTopicsId) {
      this.hostFamilyNoteTopicsId = hostFamilyNoteTopicsId;
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

   public String getTopicName() {
      return this.topicName;
   }

   public void setTopicName(String topicName) {
      this.topicName = topicName;
   }

   public List<HostFamilyNote> getHostFamilyNotes() {
      return this.hostFamilyNotes;
   }

   public void setHostFamilyNotes(List<HostFamilyNote> hostFamilyNotes) {
      this.hostFamilyNotes = hostFamilyNotes;
   }

   public HostFamilyNote addHostFamilyNote(HostFamilyNote hostFamilyNote) {
      getHostFamilyNotes().add(hostFamilyNote);
      hostFamilyNote.setHostFamilyNoteTopic(this);

      return hostFamilyNote;
   }

   public HostFamilyNote removeHostFamilyNote(HostFamilyNote hostFamilyNote) {
      getHostFamilyNotes().remove(hostFamilyNote);
      hostFamilyNote.setHostFamilyNoteTopic(null);

      return hostFamilyNote;
   }

   public HostFamily getHostFamily() {
      return this.hostFamily;
   }

   public void setHostFamily(HostFamily hostFamily) {
      this.hostFamily = hostFamily;
   }

}