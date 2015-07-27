package com.ccighgo.db.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the SeasonGHTConfiguration database table.
 * 
 */
@Entity
@Table(name = "SeasonGHTConfiguration")
@NamedQuery(name = "SeasonGHTConfiguration.findAll", query = "SELECT s FROM SeasonGHTConfiguration s")
public class SeasonGHTConfiguration implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer seasonGHTConfigurationId;

   @Column(nullable = false)
   private Integer createdBy;

   private Timestamp createdOn;

   @Column(nullable = false)
   private Integer modifiedBy;

   @Column(nullable = false)
   private Timestamp modifiedOn;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(nullable = false)
   private Date seasonEndDate;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(nullable = false)
   private Date seasonStartDate;

   // bi-directional many-to-one association to Season
   @ManyToOne
   @JoinColumn(name = "seasonId", nullable = false)
   private Season season;

   public SeasonGHTConfiguration() {
   }

   public Integer getSeasonGHTConfigurationId() {
      if (this.seasonGHTConfigurationId != null)
         return this.seasonGHTConfigurationId;
      return 0;
   }

   public void setSeasonGHTConfigurationId(Integer seasonGHTConfigurationId) {
      this.seasonGHTConfigurationId = seasonGHTConfigurationId;
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

   public Date getSeasonEndDate() {
      return this.seasonEndDate;
   }

   public void setSeasonEndDate(Date seasonEndDate) {
      this.seasonEndDate = seasonEndDate;
   }

   public Date getSeasonStartDate() {
      return this.seasonStartDate;
   }

   public void setSeasonStartDate(Date seasonStartDate) {
      this.seasonStartDate = seasonStartDate;
   }

   public Season getSeason() {
      return this.season;
   }

   public void setSeason(Season season) {
      this.season = season;
   }

}