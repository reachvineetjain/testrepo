//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.29 at 01:09:09 PM IST 
//


package com.ccighgo.service.transport.beans.fieldstaffdashboard.erddashboard;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ErdDashboardPoints complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ErdDashboardPoints">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="points" type="{http://www.ccighgo.com/fieldstaffdashboard}ErdDashboardPoint" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ErdDashboardPoints", propOrder = {
    "points"
})
public class ErdDashboardPoints {

    @XmlElement(required = true)
    protected List<ErdDashboardPoint> points;

    /**
     * Gets the value of the points property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the points property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPoints().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ErdDashboardPoint }
     * 
     * 
     */
    public List<ErdDashboardPoint> getPoints() {
        if (points == null) {
            points = new ArrayList<ErdDashboardPoint>();
        }
        return this.points;
    }

}
