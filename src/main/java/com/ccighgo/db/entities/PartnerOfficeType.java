package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PartnerOfficeType database table.
 * 
 */
@Entity
@Table(name="PartnerOfficeType")
@NamedQuery(name="PartnerOfficeType.findAll", query="SELECT p FROM PartnerOfficeType p")
public class PartnerOfficeType implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(unique=true, nullable=false)
   private Integer partnerOfficeTypeId;

   @Column(length=50)
   private String partnerOfficeType;

   //bi-directional many-to-one association to PartnerOffice
   @OneToMany(mappedBy="partnerOfficeType")
   private List<PartnerOffice> partnerOffices;

   public PartnerOfficeType() {
   }

   public Integer getPartnerOfficeTypeId() {
      return this.partnerOfficeTypeId;
   }

   public void setPartnerOfficeTypeId(Integer partnerOfficeTypeId) {
      this.partnerOfficeTypeId = partnerOfficeTypeId;
   }

   public String getPartnerOfficeType() {
      return this.partnerOfficeType;
   }

   public void setPartnerOfficeType(String partnerOfficeType) {
      this.partnerOfficeType = partnerOfficeType;
   }

   public List<PartnerOffice> getPartnerOffices() {
      return this.partnerOffices;
   }

   public void setPartnerOffices(List<PartnerOffice> partnerOffices) {
      this.partnerOffices = partnerOffices;
   }

   public PartnerOffice addPartnerOffice(PartnerOffice partnerOffice) {
      getPartnerOffices().add(partnerOffice);
      partnerOffice.setPartnerOfficeType(this);

      return partnerOffice;
   }

   public PartnerOffice removePartnerOffice(PartnerOffice partnerOffice) {
      getPartnerOffices().remove(partnerOffice);
      partnerOffice.setPartnerOfficeType(null);

      return partnerOffice;
   }

}