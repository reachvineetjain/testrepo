//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.09 at 03:31:23 PM CST 
//


package com.ccighgo.service.transport.fieldstaff.beans.rm.season.contacts;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for FSRMSeasonContacts complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FSRMSeasonContacts">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="fSRMSeasonContacts" type="{http://www.ccighgo.com/fsrmContacts}FSRMSeasonContact" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FSRMSeasonContacts", propOrder = {
    "fsrmSeasonContacts"
})
public class FSRMSeasonContacts
    extends Response
{

    @XmlElement(name = "fSRMSeasonContacts")
    protected List<FSRMSeasonContact> fsrmSeasonContacts;

    /**
     * Gets the value of the fsrmSeasonContacts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fsrmSeasonContacts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFSRMSeasonContacts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FSRMSeasonContact }
     * 
     * 
     */
    public List<FSRMSeasonContact> getFSRMSeasonContacts() {
        if (fsrmSeasonContacts == null) {
            fsrmSeasonContacts = new ArrayList<FSRMSeasonContact>();
        }
        return this.fsrmSeasonContacts;
    }

}
