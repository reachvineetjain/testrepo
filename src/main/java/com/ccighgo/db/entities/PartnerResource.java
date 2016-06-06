package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the PartnerResources database table.
 * 
 */
@Entity
@Table(name="PartnerResources")
@NamedQuery(name="PartnerResource.findAll", query="SELECT p FROM PartnerResource p")
public class PartnerResource implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer partnerResourcesId;

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

   public PartnerResource() {
   }

   public Integer getPartnerResourcesId() {
      return this.partnerResourcesId;
   }

   public void setPartnerResourcesId(Integer partnerResourcesId) {
      this.partnerResourcesId = partnerResourcesId;
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