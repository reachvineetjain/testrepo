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
 * The persistent class for the SeasonCAPDetails database table.
 * 
 */
@Entity
@Table(name = "SeasonCAPDetails")
@NamedQuery(name = "SeasonCAPDetail.findAll", query = "SELECT s FROM SeasonCAPDetail s")
public class SeasonCAPDetail implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer seasonCAPDetailsId;

   @Column(nullable = false)
   private Integer createdBy;

   private Timestamp createdOn;

   @Temporal(TemporalType.TIMESTAMP)
   private Date internAppDeadlineDate;

   @Temporal(TemporalType.TIMESTAMP)
   private Date internEndDate;

   @Temporal(TemporalType.TIMESTAMP)
   private Date internStartDate;

   @Column(nullable = false)
   private Integer modifiedBy;

   @Column(nullable = false)
   private Timestamp modifiedOn;

   @Column(length = 45)
   private String programName;

   @Temporal(TemporalType.TIMESTAMP)
   private Date traineeAppDeadlineDate;

   @Temporal(TemporalType.TIMESTAMP)
   private Date traineeEndDate;

   @Temporal(TemporalType.TIMESTAMP)
   private Date traineeStartDate;

   // bi-directional many-to-one association to Season
   @ManyToOne
   @JoinColumn(name = "seasonId", nullable = false)
   private Season season;

   // bi-directional many-to-one association to SeasonStatus
   @ManyToOne
   @JoinColumn(name = "programStatusId")
   private SeasonStatus seasonStatus;

   public SeasonCAPDetail() {
   }

   public Integer getSeasonCAPDetailsId() {
      if (this.seasonCAPDetailsId != null)
         return this.seasonCAPDetailsId;
      return 0;
   }

   public void setSeasonCAPDetailsId(Integer seasonCAPDetailsId) {
      this.seasonCAPDetailsId = seasonCAPDetailsId;
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

   public Date getInternAppDeadlineDate() {
      return this.internAppDeadlineDate;
   }

   public void setInternAppDeadlineDate(Date internAppDeadlineDate) {
      this.internAppDeadlineDate = internAppDeadlineDate;
   }

   public Date getInternEndDate() {
      return this.internEndDate;
   }

   public void setInternEndDate(Date internEndDate) {
      this.internEndDate = internEndDate;
   }

   public Date getInternStartDate() {
      return this.internStartDate;
   }

   public void setInternStartDate(Date internStartDate) {
      this.internStartDate = internStartDate;
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

   public Date getTraineeAppDeadlineDate() {
      return this.traineeAppDeadlineDate;
   }

   public void setTraineeAppDeadlineDate(Date traineeAppDeadlineDate) {
      this.traineeAppDeadlineDate = traineeAppDeadlineDate;
   }

   public Date getTraineeEndDate() {
      return this.traineeEndDate;
   }

   public void setTraineeEndDate(Date traineeEndDate) {
      this.traineeEndDate = traineeEndDate;
   }

   public Date getTraineeStartDate() {
      return this.traineeStartDate;
   }

   public void setTraineeStartDate(Date traineeStartDate) {
      this.traineeStartDate = traineeStartDate;
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