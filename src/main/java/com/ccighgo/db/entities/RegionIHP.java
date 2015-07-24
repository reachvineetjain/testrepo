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
 * The persistent class for the RegionIHP database table.
 * 
 */
@Entity
@Table(name = "RegionIHP")
@NamedQuery(name = "RegionIHP.findAll", query = "SELECT r FROM RegionIHP r")
public class RegionIHP implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer regionIHPId;

   private byte active;

   @Column(nullable = false)
   private Integer createdBy;

   private Timestamp createdOn;

   @Column(nullable = false)
   private Integer modifiedBy;

   @Column(nullable = false)
   private Timestamp modifiedOn;

   @Column(length = 45)
   private String regionName;

   // bi-directional many-to-one association to SeasonIHPDetailsRegionApplication
   @OneToMany(mappedBy = "regionIhp")
   private List<SeasonIHPDetailsRegionApplication> seasonIhpdetailsRegionApplications;

   // bi-directional many-to-one association to SeasonIHPGeographyConfiguration
   @OneToMany(mappedBy = "regionIhp")
   private List<SeasonIHPGeographyConfiguration> seasonIhpgeographyConfigurations;

   public RegionIHP() {
   }

   public Integer getRegionIHPId() {
      if (this.regionIHPId != null)
         return this.regionIHPId;
      return 0;
   }

   public void setRegionIHPId(Integer regionIHPId) {
      this.regionIHPId = regionIHPId;
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

   public String getRegionName() {
      return this.regionName;
   }

   public void setRegionName(String regionName) {
      this.regionName = regionName;
   }

   public List<SeasonIHPDetailsRegionApplication> getSeasonIhpdetailsRegionApplications() {
      return this.seasonIhpdetailsRegionApplications;
   }

   public void setSeasonIhpdetailsRegionApplications(List<SeasonIHPDetailsRegionApplication> seasonIhpdetailsRegionApplications) {
      this.seasonIhpdetailsRegionApplications = seasonIhpdetailsRegionApplications;
   }

   public SeasonIHPDetailsRegionApplication addSeasonIhpdetailsRegionApplication(SeasonIHPDetailsRegionApplication seasonIhpdetailsRegionApplication) {
      getSeasonIhpdetailsRegionApplications().add(seasonIhpdetailsRegionApplication);
      seasonIhpdetailsRegionApplication.setRegionIhp(this);

      return seasonIhpdetailsRegionApplication;
   }

   public SeasonIHPDetailsRegionApplication removeSeasonIhpdetailsRegionApplication(SeasonIHPDetailsRegionApplication seasonIhpdetailsRegionApplication) {
      getSeasonIhpdetailsRegionApplications().remove(seasonIhpdetailsRegionApplication);
      seasonIhpdetailsRegionApplication.setRegionIhp(null);

      return seasonIhpdetailsRegionApplication;
   }

   public List<SeasonIHPGeographyConfiguration> getSeasonIhpgeographyConfigurations() {
      return this.seasonIhpgeographyConfigurations;
   }

   public void setSeasonIhpgeographyConfigurations(List<SeasonIHPGeographyConfiguration> seasonIhpgeographyConfigurations) {
      this.seasonIhpgeographyConfigurations = seasonIhpgeographyConfigurations;
   }

   public SeasonIHPGeographyConfiguration addSeasonIhpgeographyConfiguration(SeasonIHPGeographyConfiguration seasonIhpgeographyConfiguration) {
      getSeasonIhpgeographyConfigurations().add(seasonIhpgeographyConfiguration);
      seasonIhpgeographyConfiguration.setRegionIhp(this);

      return seasonIhpgeographyConfiguration;
   }

   public SeasonIHPGeographyConfiguration removeSeasonIhpgeographyConfiguration(SeasonIHPGeographyConfiguration seasonIhpgeographyConfiguration) {
      getSeasonIhpgeographyConfigurations().remove(seasonIhpgeographyConfiguration);
      seasonIhpgeographyConfiguration.setRegionIhp(null);

      return seasonIhpgeographyConfiguration;
   }

}