package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the HostFamilyAirport database table.
 * 
 */
@Entity
@Table(name="HostFamilyAirport")
@NamedQuery(name="HostFamilyAirport.findAll", query="SELECT h FROM HostFamilyAirport h")
public class HostFamilyAirport implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer hostFamilyAirportId;

   private Byte active;

   private Integer createdBy;

   private Timestamp createdOn;

   private Integer distanceToAirport;

   private Integer modifiedBy;

   private Timestamp modifiedOn;

   //bi-directional many-to-one association to Airport
   @ManyToOne
   @JoinColumn(name="airportId")
   private Airport airport;

   //bi-directional many-to-one association to HostFamily
   @ManyToOne
   @JoinColumn(name="hostFamilyGoId")
   private HostFamily hostFamily;

   public HostFamilyAirport() {
   }

   public Integer getHostFamilyAirportId() {
      return this.hostFamilyAirportId;
   }

   public void setHostFamilyAirportId(Integer hostFamilyAirportId) {
      this.hostFamilyAirportId = hostFamilyAirportId;
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

   public Integer getDistanceToAirport() {
      return this.distanceToAirport;
   }

   public void setDistanceToAirport(Integer distanceToAirport) {
      this.distanceToAirport = distanceToAirport;
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

   public Airport getAirport() {
      return this.airport;
   }

   public void setAirport(Airport airport) {
      this.airport = airport;
   }

   public HostFamily getHostFamily() {
      return this.hostFamily;
   }

   public void setHostFamily(HostFamily hostFamily) {
      this.hostFamily = hostFamily;
   }

}