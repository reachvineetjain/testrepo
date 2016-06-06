package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the PartnerSeasonNotes database table.
 * 
 */
@Entity
@Table(name="PartnerSeasonNotes")
@NamedQuery(name="PartnerSeasonNote.findAll", query="SELECT p FROM PartnerSeasonNote p")
public class PartnerSeasonNote implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer partnerSeasonNotesId;

   private Integer createdBy;

   private Timestamp createdOn;

   private Byte hasRead;

   private Integer modifiedBy;

   private Timestamp modifiedOn;

   @Lob
   private String partnerNote;

   //bi-directional many-to-one association to PartnerSeason
   @ManyToOne
   @JoinColumn(name="partnerSeasonId")
   private PartnerSeason partnerSeason;

   //bi-directional many-to-one association to PartnerSeasonNoteTopic
   @ManyToOne
   @JoinColumn(name="partnerSeasonNoteTopicId")
   private PartnerSeasonNoteTopic partnerSeasonNoteTopic;

   public PartnerSeasonNote() {
   }

   public Integer getPartnerSeasonNotesId() {
      return this.partnerSeasonNotesId;
   }

   public void setPartnerSeasonNotesId(Integer partnerSeasonNotesId) {
      this.partnerSeasonNotesId = partnerSeasonNotesId;
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

   public String getPartnerNote() {
      return this.partnerNote;
   }

   public void setPartnerNote(String partnerNote) {
      this.partnerNote = partnerNote;
   }

   public PartnerSeason getPartnerSeason() {
      return this.partnerSeason;
   }

   public void setPartnerSeason(PartnerSeason partnerSeason) {
      this.partnerSeason = partnerSeason;
   }

   public PartnerSeasonNoteTopic getPartnerSeasonNoteTopic() {
      return this.partnerSeasonNoteTopic;
   }

   public void setPartnerSeasonNoteTopic(PartnerSeasonNoteTopic partnerSeasonNoteTopic) {
      this.partnerSeasonNoteTopic = partnerSeasonNoteTopic;
   }

}