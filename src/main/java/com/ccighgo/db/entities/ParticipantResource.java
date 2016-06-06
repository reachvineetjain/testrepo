package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ParticipantResources database table.
 * 
 */
@Entity
@Table(name="ParticipantResources")
@NamedQuery(name="ParticipantResource.findAll", query="SELECT p FROM ParticipantResource p")
public class ParticipantResource implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer participantResourcesId;

   private Byte active;

   private Integer createdBy;

   @Temporal(TemporalType.TIMESTAMP)
   private Date createdOn;

   @Column(length=200)
   private String resourceURL;

   //bi-directional many-to-one association to ResourceType
   @ManyToOne
   @JoinColumn(name="resourceTypeId")
   private ResourceType resourceType;

   public ParticipantResource() {
   }

   public Integer getParticipantResourcesId() {
      return this.participantResourcesId;
   }

   public void setParticipantResourcesId(Integer participantResourcesId) {
      this.participantResourcesId = participantResourcesId;
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

   public Date getCreatedOn() {
      return this.createdOn;
   }

   public void setCreatedOn(Date createdOn) {
      this.createdOn = createdOn;
   }

   public String getResourceURL() {
      return this.resourceURL;
   }

   public void setResourceURL(String resourceURL) {
      this.resourceURL = resourceURL;
   }

   public ResourceType getResourceType() {
      return this.resourceType;
   }

   public void setResourceType(ResourceType resourceType) {
      this.resourceType = resourceType;
   }

}