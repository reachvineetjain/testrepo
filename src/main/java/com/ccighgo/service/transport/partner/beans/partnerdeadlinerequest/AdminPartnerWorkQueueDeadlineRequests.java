//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.12.01 at 11:31:45 AM CST 
//


package com.ccighgo.service.transport.partner.beans.partnerdeadlinerequest;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for AdminPartnerWorkQueueDeadlineRequests complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdminPartnerWorkQueueDeadlineRequests">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="deadlineRequests" type="{http://www.ccighgo.com/partnerdeadlinerequest}AdminPartnerWorkQueueDeadlineRequestsDetail" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdminPartnerWorkQueueDeadlineRequests", propOrder = {
    "deadlineRequests"
})
public class AdminPartnerWorkQueueDeadlineRequests
    extends Response
{

    @XmlElement(required = true)
    protected List<AdminPartnerWorkQueueDeadlineRequestsDetail> deadlineRequests;

    /**
     * Gets the value of the deadlineRequests property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deadlineRequests property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeadlineRequests().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdminPartnerWorkQueueDeadlineRequestsDetail }
     * 
     * 
     */
    public List<AdminPartnerWorkQueueDeadlineRequestsDetail> getDeadlineRequests() {
        if (deadlineRequests == null) {
            deadlineRequests = new ArrayList<AdminPartnerWorkQueueDeadlineRequestsDetail>();
        }
        return this.deadlineRequests;
    }

}
