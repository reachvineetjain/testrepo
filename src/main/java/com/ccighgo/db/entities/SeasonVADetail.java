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
 * The persistent class for the SeasonVADetails database table.
 * 
 */
@Entity
@Table(name = "SeasonVADetails")
@NamedQuery(name = "SeasonVADetail.findAll", query = "SELECT s FROM SeasonVADetail s")
public class SeasonVADetail implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer seasonVADetailsId;

   @Column(nullable = false)
   private Integer createdBy;

   private Timestamp createdOn;

   @Temporal(TemporalType.TIMESTAMP)
   private Date endDate;

   @Column(nullable = false)
   private Integer modifiedBy;

   @Column(nullable = false)
   private Timestamp modifiedOn;

   @Column(length = 45)
   private String programName;

   @Temporal(TemporalType.TIMESTAMP)
   private Date startDate;

   // bi-directional many-to-one association to Season
   @ManyToOne
   @JoinColumn(name = "seasonId", nullable = false)
   private Season season;

   // bi-directional many-to-one association to SeasonStatus
   @ManyToOne
   @JoinColumn(name = "programStatusId")
   private SeasonStatus seasonStatus;

   public SeasonVADetail() {
   }

   public Integer getSeasonVADetailsId() {
      if (this.seasonVADetailsId != null)
         return this.seasonVADetailsId;
      return 0;
   }

   public void setSeasonVADetailsId(Integer seasonVADetailsId) {
      this.seasonVADetailsId = seasonVADetailsId;
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

   public Date getEndDate() {
      return this.endDate;
   }

   public void setEndDate(Date endDate) {
      this.endDate = endDate;
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

   public String getProgramName() {
      return this.programName;
   }

   public void setProgramName(String programName) {
      this.programName = programName;
   }

   public Date getStartDate() {
      return this.startDate;
   }

   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }

   public Season getSeason() {
      return this.season;
   }

   public void setSeason(Season season) {
      this.season = season;
   }

   public SeasonStatus getSeasonStatus() {
      return this.seasonStatus;
   }

   public void setSeasonStatus(SeasonStatus seasonStatus) {
      this.seasonStatus = seasonStatus;
   }

}