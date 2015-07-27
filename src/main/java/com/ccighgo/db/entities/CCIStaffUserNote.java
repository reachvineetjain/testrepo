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
 * The persistent class for the CCIStaffUserNotes database table.
 * 
 */
@Entity
@Table(name = "CCIStaffUserNotes")
@NamedQuery(name = "CCIStaffUserNote.findAll", query = "SELECT c FROM CCIStaffUserNote c")
public class CCIStaffUserNote implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer cciStaffUserNoteId;

   @Column(nullable = false)
   private Integer createdBy;

   private Timestamp createdOn;

   @Column(nullable = false)
   private Integer modifiedBy;

   @Column(nullable = false)
   private Timestamp modifiedOn;

   @Column(nullable = false, length = 1000)
   private String note;

   // bi-directional many-to-one association to CCIStaffUser
   @ManyToOne
   @JoinColumn(name = "ccistaffuserID", nullable = false)
   private CCIStaffUser ccistaffUser;

   public CCIStaffUserNote() {
   }

   public Integer getCciStaffUserNoteId() {
      if (this.cciStaffUserNoteId != null)
         return this.cciStaffUserNoteId;
      return 0;
   }

   public void setCciStaffUserNoteId(Integer cciStaffUserNoteId) {
      this.cciStaffUserNoteId = cciStaffUserNoteId;
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

   public String getNote() {
      return this.note;
   }

   public void setNote(String note) {
      this.note = note;
   }

   public CCIStaffUser getCcistaffUser() {
      return this.ccistaffUser;
   }

   public void setCcistaffUser(CCIStaffUser ccistaffUser) {
      this.ccistaffUser = ccistaffUser;
   }

}