package com.ccighgo.db.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * The persistent class for the SuperRegion database table.
 * 
 */
@Entity
@Table(name = "SuperRegion")
@NamedQuery(name = "SuperRegion.findAll", query = "SELECT s FROM SuperRegion s")
public class SuperRegion implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer superRegionId;

   private byte active;

   @Column(nullable = false)
   private Integer createdBy;

   private Timestamp createdOn;

   @Column(nullable = false)
   private Integer modifiedBy;

   @Column(nullable = false)
   private Timestamp modifiedOn;

   @Column(length = 45)
   private String superRegionName;

   // bi-directional many-to-one association to SeasonGeographyConfiguration
   @OneToMany(mappedBy = "superRegion", fetch = FetchType.EAGER)
   @Fetch(value = FetchMode.SUBSELECT)
   private List<SeasonGeographyConfiguration> seasonGeographyConfigurations;

   public SuperRegion() {
   }

   public Integer getSuperRegionId() {
      if (this.superRegionId != null)
         return this.superRegionId;
      return 0;
   }

   public void setSuperRegionId(Integer superRegionId) {
      this.superRegionId = superRegionId;
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
      if (this.modifiedBy != null)
         return this.modifiedBy;
      return 0;
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

   public String getSuperRegionName() {
      return this.superRegionName;
   }

   public void setSuperRegionName(String superRegionName) {
      this.superRegionName = superRegionName;
   }

   public List<SeasonGeographyConfiguration> getSeasonGeographyConfigurations() {
      return this.seasonGeographyConfigurations;
   }

   public void setSeasonGeographyConfigurations(List<SeasonGeographyConfiguration> seasonGeographyConfigurations) {
      this.seasonGeographyConfigurations = seasonGeographyConfigurations;
   }

   public SeasonGeographyConfiguration addSeasonGeographyConfiguration(SeasonGeographyConfiguration seasonGeographyConfiguration) {
      getSeasonGeographyConfigurations().add(seasonGeographyConfiguration);
      seasonGeographyConfiguration.setSuperRegion(this);

      return seasonGeographyConfiguration;
   }

   public SeasonGeographyConfiguration removeSeasonGeographyConfiguration(SeasonGeographyConfiguration seasonGeographyConfiguration) {
      getSeasonGeographyConfigurations().remove(seasonGeographyConfiguration);
      seasonGeographyConfiguration.setSuperRegion(null);

      return seasonGeographyConfiguration;
   }

}