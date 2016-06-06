package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EmployerResources database table.
 * 
 */
@Entity
@Table(name="EmployerResources")
@NamedQuery(name="EmployerResource.findAll", query="SELECT e FROM EmployerResource e")
public class EmployerResource implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer employerResourcesId;

   private Byte active;

   private Integer createdBy;

   @Temporal(TemporalType.TIMESTAMP)
   private Date createdOn;

   @Column(length=200)
   private String resourceTypeURL;

   //bi-directional many-to-one association to ResourceType
   @ManyToOne
   @JoinColumn(name="resourceTypeId")
   private ResourceType resourceType;

   public EmployerResource() {
   }

   public Integer getEmployerResourcesId() {
      return this.employerResourcesId;
   }

   public void setEmployerResourcesId(Integer employerResourcesId) {
      this.employerResourcesId = employerResourcesId;
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

   public String getResourceTypeURL() {
      return this.resourceTypeURL;
   }

   public void setResourceTypeURL(String resourceTypeURL) {
      this.resourceTypeURL = resourceTypeURL;
   }

   public ResourceType getResourceType() {
      return this.resourceType;
   }

   public void setResourceType(ResourceType resourceType) {
      this.resourceType = resourceType;
   }

}