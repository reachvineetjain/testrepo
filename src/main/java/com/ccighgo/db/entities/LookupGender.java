package com.ccighgo.db.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the LookupGender database table.
 * 
 */
@Entity
@Table(name = "LookupGender")
@NamedQuery(name = "LookupGender.findAll", query = "SELECT l FROM LookupGender l")
public class LookupGender implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer genderId;

   @Column(nullable = false, length = 1)
   private String genderName;

   // bi-directional many-to-one association to CCIStaffUser
   @OneToMany(mappedBy = "lookupGender")
   private List<CCIStaffUser> ccistaffUsers;

   // bi-directional many-to-one association to SeasonIHPDetail
   @OneToMany(mappedBy = "lookupGender")
   private List<SeasonIHPDetail> seasonIhpdetails;

   public LookupGender() {
   }

   public Integer getGenderId() {
      if (this.genderId != null)
         return this.genderId;
      return 0;
   }

   public void setGenderId(Integer genderId) {
      this.genderId = genderId;
   }

   public String getGenderName() {
      return this.genderName;
   }

   public void setGenderName(String genderName) {
      this.genderName = genderName;
   }

   public List<CCIStaffUser> getCcistaffUsers() {
      return this.ccistaffUsers;
   }

   public void setCcistaffUsers(List<CCIStaffUser> ccistaffUsers) {
      this.ccistaffUsers = ccistaffUsers;
   }

   public CCIStaffUser addCcistaffUser(CCIStaffUser ccistaffUser) {
      getCcistaffUsers().add(ccistaffUser);
      ccistaffUser.setLookupGender(this);

      return ccistaffUser;
   }

   public CCIStaffUser removeCcistaffUser(CCIStaffUser ccistaffUser) {
      getCcistaffUsers().remove(ccistaffUser);
      ccistaffUser.setLookupGender(null);

      return ccistaffUser;
   }

   public List<SeasonIHPDetail> getSeasonIhpdetails() {
      return this.seasonIhpdetails;
   }

   public void setSeasonIhpdetails(List<SeasonIHPDetail> seasonIhpdetails) {
      this.seasonIhpdetails = seasonIhpdetails;
   }

   public SeasonIHPDetail addSeasonIhpdetail(SeasonIHPDetail seasonIhpdetail) {
      getSeasonIhpdetails().add(seasonIhpdetail);
      seasonIhpdetail.setLookupGender(this);

      return seasonIhpdetail;
   }

   public SeasonIHPDetail removeSeasonIhpdetail(SeasonIHPDetail seasonIhpdetail) {
      getSeasonIhpdetails().remove(seasonIhpdetail);
      seasonIhpdetail.setLookupGender(null);

      return seasonIhpdetail;
   }

}