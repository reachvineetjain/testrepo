package com.ccighgo.db.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the SeasonIHPGeographyConfiguration database table.
 * 
 */
@Entity
@Table(name = "SeasonIHPGeographyConfiguration")
@NamedQuery(name = "SeasonIHPGeographyConfiguration.findAll", query = "SELECT s FROM SeasonIHPGeographyConfiguration s")
public class SeasonIHPGeographyConfiguration implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer seasonIHPGeographyConfigurationId;

   @Column(nullable = false)
   private Integer createdBy;

   private Timestamp createdOn;

   @Column(nullable = false)
   private Integer modifiedBy;

   @Column(nullable = false)
   private Timestamp modifiedOn;

   // bi-directional many-to-one association to Season
   @ManyToOne
   @JoinColumn(name = "seasonId")
   private Season season;

   // bi-directional many-to-one association to RegionIHP
   @ManyToOne
   @JoinColumn(name = "regionIHPId")
   private RegionIHP regionIhp;

   // bi-directional many-to-one association to LookupUSState
   @ManyToOne
   @JoinColumn(name = "usStatesId")
   private LookupUSState lookupUsstate;

   public SeasonIHPGeographyConfiguration() {
   }

   public Integer getSeasonIHPGeographyConfigurationId() {
      if (this.seasonIHPGeographyConfigurationId != null)
         return this.seasonIHPGeographyConfigurationId;
      return 0;
   }

   public void setSeasonIHPGeographyConfigurationId(Integer seasonIHPGeographyConfigurationId) {
      this.seasonIHPGeographyConfigurationId = seasonIHPGeographyConfigurationId;
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

   public Season getSeason() {
      return this.season;
   }

   public void setSeason(Season season) {
      this.season = season;
   }

   public RegionIHP getRegionIhp() {
      return this.regionIhp;
   }

   public void setRegionIhp(RegionIHP regionIhp) {
      this.regionIhp = regionIhp;
   }

   public LookupUSState getLookupUsstate() {
      return this.lookupUsstate;
   }

   public void setLookupUsstate(LookupUSState lookupUsstate) {
      this.lookupUsstate = lookupUsstate;
   }

}