package com.ccighgo.db.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the Region database table.
 * 
 */
@Entity
@Table(name = "Region")
@NamedQuery(name = "Region.findAll", query = "SELECT r FROM Region r")
public class Region implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer regionId;

   @Column(nullable = false)
   private byte active;

   @Column(nullable = false)
   private Integer createdBy;

   private Timestamp createdOn;

   @Column(nullable = false)
   private Integer modifiedBy;

   @Column(nullable = false)
   private Timestamp modifiedOn;

   @Column(nullable = false, length = 50)
   private String regionName;

   // bi-directional many-to-one association to SeasonGeographyConfiguration
   @OneToMany(mappedBy = "region")
   private List<SeasonGeographyConfiguration> seasonGeographyConfigurations;

   public Region() {
   }

   public Integer getRegionId() {
      if (this.regionId != null)
         return this.regionId;
      return 0;
   }

   public void setRegionId(Integer regionId) {
      this.regionId = regionId;
   }

   public byte getActive() {
      return this.active;
   }

   public void setActive(byte active) {
      this.active = active;
   }

   public Integer getCreatedBy() {
      if (this.createdBy != null)
         return this.createdBy;
      return 0;
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

   public Integer getModifiedBy() {
      if (this.modifiedBy != null) return this.modifiedBy;      return 0;
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

   public String getRegionName() {
      return this.regionName;
   }

   public void setRegionName(String regionName) {
      this.regionName = regionName;
   }

   public List<SeasonGeographyConfiguration> getSeasonGeographyConfigurations() {
      return this.seasonGeographyConfigurations;
   }

   public void setSeasonGeographyConfigurations(List<SeasonGeographyConfiguration> seasonGeographyConfigurations) {
      this.seasonGeographyConfigurations = seasonGeographyConfigurations;
   }

   public SeasonGeographyConfiguration addSeasonGeographyConfiguration(SeasonGeographyConfiguration seasonGeographyConfiguration) {
      getSeasonGeographyConfigurations().add(seasonGeographyConfiguration);
      seasonGeographyConfiguration.setRegion(this);

      return seasonGeographyConfiguration;
   }

   public SeasonGeographyConfiguration removeSeasonGeographyConfiguration(SeasonGeographyConfiguration seasonGeographyConfiguration) {
      getSeasonGeographyConfigurations().remove(seasonGeographyConfiguration);
      seasonGeographyConfiguration.setRegion(null);

      return seasonGeographyConfiguration;
   }

}