package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the HostFamilyPet database table.
 * 
 */
@Entity
@Table(name="HostFamilyPet")
@NamedQuery(name="HostFamilyPet.findAll", query="SELECT h FROM HostFamilyPet h")
public class HostFamilyPet implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer hostFamilyPetId;

   @Column(length=50)
   private String additionalInformation;

   private Byte isIndoor;

   private Byte isOutdoor;

   private Integer number;

   //bi-directional many-to-one association to HostFamilyPetType
   @ManyToOne
   @JoinColumn(name="animalTypeId")
   private HostFamilyPetType hostFamilyPetType;

   //bi-directional many-to-one association to HostFamilySeason
   @ManyToOne
   @JoinColumn(name="hostFamilySeasonId")
   private HostFamilySeason hostFamilySeason;

   public HostFamilyPet() {
   }

   public Integer getHostFamilyPetId() {
      return this.hostFamilyPetId;
   }

   public void setHostFamilyPetId(Integer hostFamilyPetId) {
      this.hostFamilyPetId = hostFamilyPetId;
   }

   public String getAdditionalInformation() {
      return this.additionalInformation;
   }

   public void setAdditionalInformation(String additionalInformation) {
      this.additionalInformation = additionalInformation;
   }

   public Byte getIsIndoor() {
      return this.isIndoor;
   }

   public void setIsIndoor(Byte isIndoor) {
      this.isIndoor = isIndoor;
   }

   public Byte getIsOutdoor() {
      return this.isOutdoor;
   }

   public void setIsOutdoor(Byte isOutdoor) {
      this.isOutdoor = isOutdoor;
   }

   public Integer getNumber() {
      return this.number;
   }

   public void setNumber(Integer number) {
      this.number = number;
   }

   public HostFamilyPetType getHostFamilyPetType() {
      return this.hostFamilyPetType;
   }

   public void setHostFamilyPetType(HostFamilyPetType hostFamilyPetType) {
      this.hostFamilyPetType = hostFamilyPetType;
   }

   public HostFamilySeason getHostFamilySeason() {
      return this.hostFamilySeason;
   }

   public void setHostFamilySeason(HostFamilySeason hostFamilySeason) {
      this.hostFamilySeason = hostFamilySeason;
   }

}