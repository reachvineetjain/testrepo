//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.04 at 11:50:07 AM CST 
//


package com.ccighgo.service.transport.partner.beans.newpartnerapplicationdeadlilne;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for NewPartnerApplicationDeadLineDate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NewPartnerApplicationDeadLineDate">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;sequence>
 *           &lt;element name="partnerSeasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *           &lt;element name="augStartDeadlineDateRequested" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="janStartDeadlineDateRequested" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "NewPartnerApplicationDeadLineDate", propOrder = {
    "partnerSeasonId",
    "augStartDeadlineDateRequested",
    "janStartDeadlineDateRequested"
})
public class NewPartnerApplicationDeadLineDate
    extends Response
{

    protected int partnerSeasonId;
    @XmlElement(required = true)
    protected String augStartDeadlineDateRequested;
    @XmlElement(required = true)
    protected String janStartDeadlineDateRequested;
    protected int janStatusId;
    protected int augStatusId;


    /**
     * Gets the value of the partnerSeasonId property.
     * 
     */
    public int getPartnerSeasonId() {
        return partnerSeasonId;
    }

    /**
     * Sets the value of the partnerSeasonId property.
     * 
     */
    public void setPartnerSeasonId(int value) {
        this.partnerSeasonId = value;
    }

    /**
     * Gets the value of the augStartDeadlineDateRequested property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAugStartDeadlineDateRequested() {
        return augStartDeadlineDateRequested;
    }

    /**
     * Sets the value of the augStartDeadlineDateRequested property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAugStartDeadlineDateRequested(String value) {
        this.augStartDeadlineDateRequested = value;
    }

    /**
     * Gets the value of the janStartDeadlineDateRequested property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJanStartDeadlineDateRequested() {
        return janStartDeadlineDateRequested;
    }

    /**
     * Sets the value of the janStartDeadlineDateRequested property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJanStartDeadlineDateRequested(String value) {
        this.janStartDeadlineDateRequested = value;
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
