//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.23 at 04:10:07 PM CDT 
//


package com.ccighgo.service.transport.integration.thirdparty.beans.partnerAdminOverviewReferenceCheck;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for PartnerAdminOverviewReferenceCheck complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerAdminOverviewReferenceCheck">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="referenceCheck" type="{http://www.ccighgo.com/partnerAdminOverviewReferenceCheck}PartnerAdminOverviewReferenceCheckDetails" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerAdminOverviewReferenceCheck", propOrder = {
    "referenceCheck"
})
public class PartnerAdminOverviewReferenceCheck
    extends Response
{

    @XmlElement(required = true)
    protected List<PartnerAdminOverviewReferenceCheckDetails> referenceCheck;

    /**
     * Gets the value of the referenceCheck property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the referenceCheck property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReferenceCheck().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartnerAdminOverviewReferenceCheckDetails }
     * 
     * 
     */
    public List<PartnerAdminOverviewReferenceCheckDetails> getReferenceCheck() {
        if (referenceCheck == null) {
            referenceCheck = new ArrayList<PartnerAdminOverviewReferenceCheckDetails>();
        }
        return this.referenceCheck;
    }

}
