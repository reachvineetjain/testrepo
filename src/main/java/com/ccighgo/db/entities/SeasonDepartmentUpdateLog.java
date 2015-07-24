package com.ccighgo.db.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the SeasonDepartmentUpdateLog database table.
 * 
 */
@Entity
@Table(name = "SeasonDepartmentUpdateLog")
@NamedQuery(name = "SeasonDepartmentUpdateLog.findAll", query = "SELECT s FROM SeasonDepartmentUpdateLog s")
public class SeasonDepartmentUpdateLog implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer updateDepartmentLogId;

   private Integer modifiedBy;

   @Column(nullable = false)
   private Timestamp modifiedOn;

   @Lob
   private String updateLogObject;

   // bi-directional many-to-one association to Season
   @ManyToOne
   @JoinColumn(name = "seasonId")
   private Season season;

   public SeasonDepartmentUpdateLog() {
   }

   public Integer getUpdateDepartmentLogId() {
      if (this.updateDepartmentLogId != null)
         return this.updateDepartmentLogId;
      return 0;
   }

   public void setUpdateDepartmentLogId(Integer updateDepartmentLogId) {
      this.updateDepartmentLogId = updateDepartmentLogId;
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

   public String getUpdateLogObject() {
      return this.updateLogObject;
   }

   public void setUpdateLogObject(String updateLogObject) {
      this.updateLogObject = updateLogObject;
   }

   public Season getSeason() {
      return this.season;
   }

   public void setSeason(Season season) {
      this.season = season;
   }

}