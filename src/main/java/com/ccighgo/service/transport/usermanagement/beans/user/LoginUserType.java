package com.ccighgo.service.transport.usermanagement.beans.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LoginUserType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LoginUserType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="loginUserTypeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="loginId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userTypeId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LoginUserType", propOrder = {
    "loginUserTypeId",
    "loginId",
    "userTypeId"
})
public class LoginUserType {
   
   protected int loginUserTypeId;
   @XmlElement(required = true)
   protected int loginId;
   @XmlElement(required = true)
   protected int userTypeId;
   
   public int getLoginUserTypeId() {
      return loginUserTypeId;
   }
   public void setLoginUserTypeId(int loginUserTypeId) {
      this.loginUserTypeId = loginUserTypeId;
   }
   public int getLoginId() {
      return loginId;
   }
   public void setLoginId(int loginId) {
      this.loginId = loginId;
   }
   public int getUserTypeId() {
      return userTypeId;
   }
   public void setUserTypeId(int userTypeId) {
      this.userTypeId = userTypeId;
   }
  
}
