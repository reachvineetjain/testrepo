package com.ccighgo.service.transport.utility.beans.gender;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Salutation", propOrder = {
    "salutationId",
    "salutationCode"
})

public class Salutation {

   protected int salutationId;
   protected String salutationCode;
   protected Byte active;
   
   public int getSalutationId() {
      return salutationId;
   }
   public void setSalutationId(int salutationId) {
      this.salutationId = salutationId;
   }
   public String getSalutationCode() {
      return salutationCode;
   }
   public void setSalutationCode(String salutationCode) {
      this.salutationCode = salutationCode;
   }
   public Byte getActive() {
      return active;
   }
   public void setActive(Byte active) {
      this.active = active;
   }

   
}
