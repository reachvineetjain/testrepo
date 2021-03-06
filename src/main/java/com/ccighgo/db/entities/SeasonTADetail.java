package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the SeasonTADetails database table.
 * 
 */
@Entity
@Table(name="SeasonTADetails")
@NamedQuery(name="SeasonTADetail.findAll", query="SELECT s FROM SeasonTADetail s")
public class SeasonTADetail implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer seasonTADetailsId;

   @Column(nullable=false)
   private Integer createdBy;

   @Column(nullable=false)
   private Timestamp createdOn;

   @Temporal(TemporalType.TIMESTAMP)
   private Date endDate;

   @Column(nullable=false)
   private Integer modifiedBy;

   @Column(nullable=false)
   private Timestamp modifiedOn;

   @Column(length=50)
   private String programName;

   @Temporal(TemporalType.TIMESTAMP)
   private Date startDate;

   //bi-directional many-to-one association to Season
   @ManyToOne
   @JoinColumn(name="seasonId", nullable=false)
   private Season season;

   //bi-directional many-to-one association to SeasonStatus
   @ManyToOne
   @JoinColumn(name="programStatusId")
   private SeasonStatus seasonStatus;

   public SeasonTADetail() {
   }

   public Integer getSeasonTADetailsId() {
      return this.seasonTADetailsId;
   }

   public void setSeasonTADetailsId(Integer seasonTADetailsId) {
      this.seasonTADetailsId = seasonTADetailsId;
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

   public Date getEndDate() {
      return this.endDate;
   }

   public void setEndDate(Date endDate) {
      this.endDate = endDate;
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