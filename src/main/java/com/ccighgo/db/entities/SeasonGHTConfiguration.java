package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the SeasonGHTConfiguration database table.
 * 
 */
@Entity
@Table(name="SeasonGHTConfiguration")
@NamedQuery(name="SeasonGHTConfiguration.findAll", query="SELECT s FROM SeasonGHTConfiguration s")
public class SeasonGHTConfiguration implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer seasonGHTConfigurationId;

   @Column(nullable=false)
   private Integer createdBy;

   @Column(nullable=false)
   private Timestamp createdOn;

   @Column(nullable=false)
   private Integer modifiedBy;

   @Column(nullable=false)
   private Timestamp modifiedOn;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(nullable=false)
   private Date seasonEndDate;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(nullable=false)
   private Date seasonStartDate;

   //bi-directional many-to-one association to Season
   @ManyToOne
   @JoinColumn(name="seasonId", nullable=false)
   private Season season;

   public SeasonGHTConfiguration() {
   }

   public Integer getSeasonGHTConfigurationId() {
      return this.seasonGHTConfigurationId;
   }

   public void setSeasonGHTConfigurationId(Integer seasonGHTConfigurationId) {
      this.seasonGHTConfigurationId = seasonGHTConfigurationId;
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