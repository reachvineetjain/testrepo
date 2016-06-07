package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ParticipantAnnouncementResults database table.
 * 
 */
@Entity
@Table(name="ParticipantAnnouncementResults")
@NamedQuery(name="ParticipantAnnouncementResult.findAll", query="SELECT p FROM ParticipantAnnouncementResult p")
public class ParticipantAnnouncementResult implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer participantAnnouncementResultsId;

   private Integer createdBy;

   @Temporal(TemporalType.TIMESTAMP)
   private Date createdOn;

   @Column(length=200)
   private String participantAnnouncementResultOption;

   //bi-directional many-to-one association to AnnouncementCreateUserType
   @ManyToOne
   @JoinColumn(name="announcementCreateTypeUserId")
   private AnnouncementCreateUserType announcementCreateUserType;

   //bi-directional many-to-one association to AnnouncementType
   @ManyToOne
   @JoinColumn(name="announcementTypeId")
   private AnnouncementType announcementType;

   //bi-directional many-to-one association to Participant
   @ManyToOne
   @JoinColumn(name="participantGoId")
   private Participant participant;

   public ParticipantAnnouncementResult() {
   }

   public Integer getParticipantAnnouncementResultsId() {
      return this.participantAnnouncementResultsId;
   }

   public void setParticipantAnnouncementResultsId(Integer participantAnnouncementResultsId) {
      this.participantAnnouncementResultsId = participantAnnouncementResultsId;
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

   public String getParticipantAnnouncementResultOption() {
      return this.participantAnnouncementResultOption;
   }

   public void setParticipantAnnouncementResultOption(String participantAnnouncementResultOption) {
      this.participantAnnouncementResultOption = participantAnnouncementResultOption;
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

   public Participant getParticipant() {
      return this.participant;
   }

   public void setParticipant(Participant participant) {
      this.participant = participant;
   }

}