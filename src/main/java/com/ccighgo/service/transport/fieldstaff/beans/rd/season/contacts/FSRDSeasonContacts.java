//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.09 at 03:31:28 PM CST 
//


package com.ccighgo.service.transport.fieldstaff.beans.rd.season.contacts;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for FSRDSeasonContacts complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FSRDSeasonContacts">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="fSRDSeasonContacts" type="{http://www.ccighgo.com/fsrdContacts}FSRDSeasonContact" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FSRDSeasonContacts", propOrder = {
    "fsrdSeasonContacts"
})
public class FSRDSeasonContacts
    extends Response
{

    @XmlElement(name = "fSRDSeasonContacts")
    protected List<FSRDSeasonContact> fsrdSeasonContacts;

    /**
     * Gets the value of the fsrdSeasonContacts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fsrdSeasonContacts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFSRDSeasonContacts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FSRDSeasonContact }
     * 
     * 
     */
    public List<FSRDSeasonContact> getFSRDSeasonContacts() {
        if (fsrdSeasonContacts == null) {
            fsrdSeasonContacts = new ArrayList<FSRDSeasonContact>();
        }
        return this.fsrdSeasonContacts;
    }

}
