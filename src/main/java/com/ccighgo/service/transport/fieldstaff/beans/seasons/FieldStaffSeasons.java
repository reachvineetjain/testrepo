//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.12.21 at 02:20:14 PM CST 
//


package com.ccighgo.service.transport.fieldstaff.beans.seasons;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for FieldStaffSeasons complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FieldStaffSeasons">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="count" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fieldStaffSeasons" type="{http://www.ccighgo.com/fsseasons}FieldStaffSeason" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FieldStaffSeasons", propOrder = {
    "count",
    "fieldStaffSeasons"
})
public class FieldStaffSeasons
    extends Response
{

    protected int count;
    protected List<FieldStaffSeason> fieldStaffSeasons;

    /**
     * Gets the value of the count property.
     * 
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the value of the count property.
     * 
     */
    public void setCount(int value) {
        this.count = value;
    }

    /**
     * Gets the value of the fieldStaffSeasons property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fieldStaffSeasons property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFieldStaffSeasons().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FieldStaffSeason }
     * 
     * 
     */
    public List<FieldStaffSeason> getFieldStaffSeasons() {
        if (fieldStaffSeasons == null) {
            fieldStaffSeasons = new ArrayList<FieldStaffSeason>();
        }
        return this.fieldStaffSeasons;
    }

}
