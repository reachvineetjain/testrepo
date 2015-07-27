package com.ccighgo.db.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the SeasonGeographyConfiguration database table.
 * 
 */
@Entity
@Table(name = "SeasonGeographyConfiguration")
@NamedQuery(name = "SeasonGeographyConfiguration.findAll", query = "SELECT s FROM SeasonGeographyConfiguration s")
public class SeasonGeographyConfiguration implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer seasonGeographyConfigurationId;

   @Column(nullable = false)
   private Integer createdBy;

   private Timestamp createdOn;

   @Column(nullable = false)
   private Integer modifiedBy;

   @Column(nullable = false)
   private Timestamp modifiedOn;

   // bi-directional many-to-one association to FieldStaffLCSeason
   @OneToMany(mappedBy = "seasonGeographyConfiguration")
   private List<FieldStaffLCSeason> fieldStaffLcseasons;

   // bi-directional many-to-one association to FieldStaffLeadershipSeason
   @OneToMany(mappedBy = "seasonGeographyConfiguration")
   private List<FieldStaffLeadershipSeason> fieldStaffLeadershipSeasons;

   // bi-directional many-to-one association to SuperRegion
   @ManyToOne
   @JoinColumn(name = "superRegionId")
   private SuperRegion superRegion;

   // bi-directional many-to-one association to Region
   @ManyToOne
   @JoinColumn(name = "regionId")
   private Region region;

   // bi-directional many-to-one association to LookupUSState
   @ManyToOne
   @JoinColumn(name = "usStatesId")
   private LookupUSState lookupUsstate;

   // bi-directional many-to-one association to Season
   @ManyToOne
   @JoinColumn(name = "seasonId")
   private Season season;

   public SeasonGeographyConfiguration() {
   }

   public Integer getSeasonGeographyConfigurationId() {
      if (this.seasonGeographyConfigurationId != null)
         return this.seasonGeographyConfigurationId;
      return 0;
   }

   public void setSeasonGeographyConfigurationId(Integer seasonGeographyConfigurationId) {
      this.seasonGeographyConfigurationId = seasonGeographyConfigurationId;
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

   public List<FieldStaffLCSeason> getFieldStaffLcseasons() {
      return this.fieldStaffLcseasons;
   }

   public void setFieldStaffLcseasons(List<FieldStaffLCSeason> fieldStaffLcseasons) {
      this.fieldStaffLcseasons = fieldStaffLcseasons;
   }

   public FieldStaffLCSeason addFieldStaffLcseason(FieldStaffLCSeason fieldStaffLcseason) {
      getFieldStaffLcseasons().add(fieldStaffLcseason);
      fieldStaffLcseason.setSeasonGeographyConfiguration(this);

      return fieldStaffLcseason;
   }

   public FieldStaffLCSeason removeFieldStaffLcseason(FieldStaffLCSeason fieldStaffLcseason) {
      getFieldStaffLcseasons().remove(fieldStaffLcseason);
      fieldStaffLcseason.setSeasonGeographyConfiguration(null);

      return fieldStaffLcseason;
   }

   public List<FieldStaffLeadershipSeason> getFieldStaffLeadershipSeasons() {
      return this.fieldStaffLeadershipSeasons;
   }

   public void setFieldStaffLeadershipSeasons(List<FieldStaffLeadershipSeason> fieldStaffLeadershipSeasons) {
      this.fieldStaffLeadershipSeasons = fieldStaffLeadershipSeasons;
   }

   public FieldStaffLeadershipSeason addFieldStaffLeadershipSeason(FieldStaffLeadershipSeason fieldStaffLeadershipSeason) {
      getFieldStaffLeadershipSeasons().add(fieldStaffLeadershipSeason);
      fieldStaffLeadershipSeason.setSeasonGeographyConfiguration(this);

      return fieldStaffLeadershipSeason;
   }

   public FieldStaffLeadershipSeason removeFieldStaffLeadershipSeason(FieldStaffLeadershipSeason fieldStaffLeadershipSeason) {
      getFieldStaffLeadershipSeasons().remove(fieldStaffLeadershipSeason);
      fieldStaffLeadershipSeason.setSeasonGeographyConfiguration(null);

      return fieldStaffLeadershipSeason;
   }

   public SuperRegion getSuperRegion() {
      return this.superRegion;
   }

   public void setSuperRegion(SuperRegion superRegion) {
      this.superRegion = superRegion;
   }

   public Region getRegion() {
      return this.region;
   }

   public void setRegion(Region region) {
      this.region = region;
   }

   public LookupUSState getLookupUsstate() {
      return this.lookupUsstate;
   }

   public void setLookupUsstate(LookupUSState lookupUsstate) {
      this.lookupUsstate = lookupUsstate;
   }

   public Season getSeason() {
      return this.season;
   }

   public void setSeason(Season season) {
      this.season = season;
   }

}