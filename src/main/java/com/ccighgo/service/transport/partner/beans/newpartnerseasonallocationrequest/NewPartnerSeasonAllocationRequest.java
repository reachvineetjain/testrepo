//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.04 at 11:42:02 AM CST 
//


package com.ccighgo.service.transport.partner.beans.newpartnerseasonallocationrequest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for NewPartnerSeasonAllocationRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NewPartnerSeasonAllocationRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;sequence>
 *           &lt;element name="janAllocationId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *           &lt;element name="augustAllocationId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *           &lt;element name="augustStartRequestedMaxUnguaranteedParticipants" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *           &lt;element name="augustStartRequestedMaxguaranteedParticipants" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *           &lt;element name="januaryStartRequestedMaxUnguaranteedParticipants" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *           &lt;element name="januaryStartRequestedMaxguaranteedParticipants" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NewPartnerSeasonAllocationRequest", propOrder = {
    "janAllocationId",
    "augustAllocationId",
    "augustStartRequestedMaxUnguaranteedParticipants",
    "augustStartRequestedMaxguaranteedParticipants",
    "januaryStartRequestedMaxUnguaranteedParticipants",
    "januaryStartRequestedMaxguaranteedParticipants",
    "statusId"
})
public class NewPartnerSeasonAllocationRequest
    extends Response
{

    protected int janAllocationId;
    protected int augustAllocationId;
    protected int augustStartRequestedMaxUnguaranteedParticipants;
    protected int augustStartRequestedMaxguaranteedParticipants;
    protected int januaryStartRequestedMaxUnguaranteedParticipants;
    protected int januaryStartRequestedMaxguaranteedParticipants;
    protected int janStatusId;
    protected int augStatusId;

    /**
     * Gets the value of the janAllocationId property.
     * 
     */
    public int getJanAllocationId() {
        return janAllocationId;
    }

    /**
     * Sets the value of the janAllocationId property.
     * 
     */
    public void setJanAllocationId(int value) {
        this.janAllocationId = value;
    }

    /**
     * Gets the value of the augustAllocationId property.
     * 
     */
    public int getAugustAllocationId() {
        return augustAllocationId;
    }

    /**
     * Sets the value of the augustAllocationId property.
     * 
     */
    public void setAugustAllocationId(int value) {
        this.augustAllocationId = value;
    }

    /**
     * Gets the value of the augustStartRequestedMaxUnguaranteedParticipants property.
     * 
     */
    public int getAugustStartRequestedMaxUnguaranteedParticipants() {
        return augustStartRequestedMaxUnguaranteedParticipants;
    }

    /**
     * Sets the value of the augustStartRequestedMaxUnguaranteedParticipants property.
     * 
     */
    public void setAugustStartRequestedMaxUnguaranteedParticipants(int value) {
        this.augustStartRequestedMaxUnguaranteedParticipants = value;
    }

    /**
     * Gets the value of the augustStartRequestedMaxguaranteedParticipants property.
     * 
     */
    public int getAugustStartRequestedMaxguaranteedParticipants() {
        return augustStartRequestedMaxguaranteedParticipants;
    }

    /**
     * Sets the value of the augustStartRequestedMaxguaranteedParticipants property.
     * 
     */
    public void setAugustStartRequestedMaxguaranteedParticipants(int value) {
        this.augustStartRequestedMaxguaranteedParticipants = value;
    }

    /**
     * Gets the value of the januaryStartRequestedMaxUnguaranteedParticipants property.
     * 
     */
    public int getJanuaryStartRequestedMaxUnguaranteedParticipants() {
        return januaryStartRequestedMaxUnguaranteedParticipants;
    }

    /**
     * Sets the value of the januaryStartRequestedMaxUnguaranteedParticipants property.
     * 
     */
    public void setJanuaryStartRequestedMaxUnguaranteedParticipants(int value) {
        this.januaryStartRequestedMaxUnguaranteedParticipants = value;
    }

    /**
     * Gets the value of the januaryStartRequestedMaxguaranteedParticipants property.
     * 
     */
    public int getJanuaryStartRequestedMaxguaranteedParticipants() {
        return januaryStartRequestedMaxguaranteedParticipants;
    }

    /**
     * Sets the value of the januaryStartRequestedMaxguaranteedParticipants property.
     * 
     */
    public void setJanuaryStartRequestedMaxguaranteedParticipants(int value) {
        this.januaryStartRequestedMaxguaranteedParticipants = value;
    }

   public int getJanStatusId() {
      return janStatusId;
   }

   public void setJanStatusId(int janStatusId) {
      this.janStatusId = janStatusId;
   }

   public int getAugStatusId() {
      return augStatusId;
   }

   public void setAugStatusId(int augStatusId) {
      this.augStatusId = augStatusId;
   }


    

}
