package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Airport database table.
 * 
 */
@Entity
@Table(name="Airport")
@NamedQuery(name="Airport.findAll", query="SELECT a FROM Airport a")
public class Airport implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer airportId;

   @Column(nullable=false)
   private Byte active;

   @Column(nullable=false, length=50)
   private String airportCity;

   @Column(nullable=false, length=5)
   private String airportCode;

   @Column(nullable=false, length=50)
   private String airportName;

   @Column(nullable=false)
   private Byte isInternational;

   //bi-directional many-to-one association to HostFamilyAirport
   @OneToMany(mappedBy="airport")
   private List<HostFamilyAirport> hostFamilyAirports;

   public Airport() {
   }

   public Integer getAirportId() {
      return this.airportId;
   }

   public void setAirportId(Integer airportId) {
      this.airportId = airportId;
   }

   public Byte getActive() {
      return this.active;
   }

   public void setActive(Byte active) {
      this.active = active;
   }

   public String getAirportCity() {
      return this.airportCity;
   }

   public void setAirportCity(String airportCity) {
      this.airportCity = airportCity;
   }

   public String getAirportCode() {
      return this.airportCode;
   }

   public void setAirportCode(String airportCode) {
      this.airportCode = airportCode;
   }

   public String getAirportName() {
      return this.airportName;
   }

   public void setAirportName(String airportName) {
      this.airportName = airportName;
   }

   public Byte getIsInternational() {
      return this.isInternational;
   }

   public void setIsInternational(Byte isInternational) {
      this.isInternational = isInternational;
   }

   public List<HostFamilyAirport> getHostFamilyAirports() {
      return this.hostFamilyAirports;
   }

   public void setHostFamilyAirports(List<HostFamilyAirport> hostFamilyAirports) {
      this.hostFamilyAirports = hostFamilyAirports;
   }

   public HostFamilyAirport addHostFamilyAirport(HostFamilyAirport hostFamilyAirport) {
      getHostFamilyAirports().add(hostFamilyAirport);
      hostFamilyAirport.setAirport(this);

      return hostFamilyAirport;
   }

   public HostFamilyAirport removeHostFamilyAirport(HostFamilyAirport hostFamilyAirport) {
      getHostFamilyAirports().remove(hostFamilyAirport);
      hostFamilyAirport.setAirport(null);

      return hostFamilyAirport;
   }

}