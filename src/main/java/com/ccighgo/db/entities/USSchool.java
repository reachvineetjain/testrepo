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
 * The persistent class for the USSchool database table.
 * 
 */
@Entity
@Table(name = "USSchool")
@NamedQuery(name = "USSchool.findAll", query = "SELECT u FROM USSchool u")
public class USSchool implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(unique = true, nullable = false)
   private Integer usSchoolId;

   // bi-directional many-to-one association to USSchoolSeason
   @OneToMany(mappedBy = "usschool")
   private List<USSchoolSeason> usschoolSeasons;

   public USSchool() {
   }

   public Integer getUsSchoolId() {
      if (this.usSchoolId != null)
         return this.usSchoolId;
      return 0;
   }

   public void setUsSchoolId(Integer usSchoolId) {
      this.usSchoolId = usSchoolId;
   }

   public List<USSchoolSeason> getUsschoolSeasons() {
      return this.usschoolSeasons;
   }

   public void setUsschoolSeasons(List<USSchoolSeason> usschoolSeasons) {
      this.usschoolSeasons = usschoolSeasons;
   }

   public USSchoolSeason addUsschoolSeason(USSchoolSeason usschoolSeason) {
      getUsschoolSeasons().add(usschoolSeason);
      usschoolSeason.setUsschool(this);

      return usschoolSeason;
   }

   public USSchoolSeason removeUsschoolSeason(USSchoolSeason usschoolSeason) {
      getUsschoolSeasons().remove(usschoolSeason);
      usschoolSeason.setUsschool(null);

      return usschoolSeason;
   }

}