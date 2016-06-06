package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the HostFamilyResources database table.
 * 
 */
@Entity
@Table(name="HostFamilyResources")
@NamedQuery(name="HostFamilyResource.findAll", query="SELECT h FROM HostFamilyResource h")
public class HostFamilyResource implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer hostFamilyResourcesId;

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

   public HostFamilyResource() {
   }

   public Integer getHostFamilyResourcesId() {
      return this.hostFamilyResourcesId;
   }

   public void setHostFamilyResourcesId(Integer hostFamilyResourcesId) {
      this.hostFamilyResourcesId = hostFamilyResourcesId;
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