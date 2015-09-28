package com.ccighgo.service.transport.partner.beans.subpartner;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubPartnerAgency", propOrder = {
      "companyName",
      "subPartnerStatus",
      "needPartnerReview",
      "deliverDSForms",
      "payGreenheartDirectly",
      "userName",
      "password"
})
public class SubPartnerAgency {

   protected String companyName;
   protected SubPartnerStatus subPartnerStatus;
   protected Byte needPartnerReview;
   protected Byte deliverDSForms;
   protected Byte payGreenheartDirectly;
   protected String userName;
   protected String password;
   
   
   public String getCompanyName() {
      return companyName;
   }
   public void setCompanyName(String companyName) {
      this.companyName = companyName;
   }
   /**
    * Gets the value of the subPartnerStatus property.
    * 
    * @return
    *     possible object is
    *     {@link SubPartnerStatus }
    *     
    */
   public SubPartnerStatus getSubPartnerStatus() {
       return subPartnerStatus;
   }
   /**
    * Sets the value of the subPartnerStatus property.
    * 
    * @param value
    *     allowed object is
    *     {@link SubPartnerStatus }
    *     
    */
   public void setSubPartnerStatus(SubPartnerStatus value) {
       this.subPartnerStatus = value;
   }
   public Byte getNeedPartnerReview() {
      return needPartnerReview;
   }
   public void setNeedPartnerReview(Byte needPartnerReview) {
      this.needPartnerReview = needPartnerReview;
   }
   public Byte getDeliverDSForms() {
      return deliverDSForms;
   }
   public void setDeliverDSForms(Byte deliverDSForms) {
      this.deliverDSForms = deliverDSForms;
   }
   public Byte getPayGreenheartDirectly() {
      return payGreenheartDirectly;
   }
   public void setPayGreenheartDirectly(Byte payGreenheartDirectly) {
      this.payGreenheartDirectly = payGreenheartDirectly;
   }
   public String getUserName() {
      return userName;
   }
   public void setUserName(String userName) {
      this.userName = userName;
   }
   public String getPassword() {
      return password;
   }
   public void setPassword(String password) {
      this.password = password;
   }
   
}
