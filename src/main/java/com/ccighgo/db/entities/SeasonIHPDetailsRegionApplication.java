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
 * The persistent class for the SeasonIHPDetailsRegionApplications database table.
 * 
 */
@Entity
@Table(name = "SeasonIHPDetailsRegionApplications")
@NamedQuery(name = "SeasonIHPDetailsRegionApplication.findAll", query = "SELECT s FROM SeasonIHPDetailsRegionApplication s")
public class SeasonIHPDetailsRegionApplication implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer seasonIHPDetailsRegionApplicationId;

   @Column(nullable = false)
   private Integer createdBy;

   private Timestamp createdOn;

   @Column(nullable = false)
   private Integer modifiedBy;

   @Column(nullable = false)
   private Timestamp modifiedOn;

   private byte stopAcceptingApps;

   // bi-directional many-to-one association to RegionIHP
   @ManyToOne
   @JoinColumn(name = "regionIHPId")
   private RegionIHP regionIhp;

   // bi-directional many-to-one association to SeasonIHPDetail
   @ManyToOne
   @JoinColumn(name = "seasonIHPDetailsId")
   private SeasonIHPDetail seasonIhpdetail;

   public SeasonIHPDetailsRegionApplication() {
   }

   public Integer getSeasonIHPDetailsRegionApplicationId() {
      if (this.seasonIHPDetailsRegionApplicationId != null)
         return this.seasonIHPDetailsRegionApplicationId;
      return 0;
   }

   public void setSeasonIHPDetailsRegionApplicationId(Integer seasonIHPDetailsRegionApplicationId) {
      this.seasonIHPDetailsRegionApplicationId = seasonIHPDetailsRegionApplicationId;
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

   public byte getStopAcceptingApps() {
      return this.stopAcceptingApps;
   }

   public void setStopAcceptingApps(byte stopAcceptingApps) {
      this.stopAcceptingApps = stopAcceptingApps;
   }

   public RegionIHP getRegionIhp() {
      return this.regionIhp;
   }

   public void setRegionIhp(RegionIHP regionIhp) {
      this.regionIhp = regionIhp;
   }

   public SeasonIHPDetail getSeasonIhpdetail() {
      return this.seasonIhpdetail;
   }

   public void setSeasonIhpdetail(SeasonIHPDetail seasonIhpdetail) {
      this.seasonIhpdetail = seasonIhpdetail;
   }

}