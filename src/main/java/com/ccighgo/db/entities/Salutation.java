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

@Entity
@Table(name = "Salutation")
@NamedQuery(name = "Salutation.findAll", query = "SELECT l FROM Salutation l")
public class Salutation  implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
   @Column(unique = true, nullable = false) 
   private Integer salutationId;

   @Column(length = 30) 
   private String salutationName;

   @Column(length = 1) 
   private Byte active;

   public Integer getSalutationId() {
      return salutationId;
   }

   public void setSalutationId(Integer salutationId) {
      this.salutationId = salutationId;
   }

   public String getSalutationName() {
      return salutationName;
   }

   @OneToMany(mappedBy = "salutation") 
   private List<PartnerUser> partnerUser;
   
   @OneToMany(mappedBy = "salutation") private 
   List<PartnerContact> partnerContact;

   public void setSalutationName(String salutationName) {
      this.salutationName = salutationName;
   }

   public Byte getActive() {
      return active;
   }

   public void setActive(Byte active) {
      this.active = active;
   }

   public List<PartnerUser> getPartnerUser() {
      return partnerUser;
   }

   public void setPartnerUser(List<PartnerUser> partnerUser) {
      this.partnerUser = partnerUser;
   }

   public List<PartnerContact> getPartnerContact() {
      return partnerContact;
   }

   public void setPartnerContact(List<PartnerContact> partnerContact) {
      this.partnerContact = partnerContact;
   }

}
