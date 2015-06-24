//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.06.24 at 03:07:09 PM CDT 
//


package com.ccighgo.service.transport.season.beans.seasonghtdetails;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SeasonGHTDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SeasonGHTDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ghtBaseDetails" type="{http://www.ccighgo.com/ght}GHTSection1Base"/>
 *         &lt;element name="ghtDates" type="{http://www.ccighgo.com/ght}GHTSection2Dates"/>
 *         &lt;element name="ghtNotes" type="{http://www.ccighgo.com/ght}GHTSection3Notes" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SeasonGHTDetails", propOrder = {
    "seasonId",
    "ghtBaseDetails",
    "ghtDates",
    "ghtNotes"
})
public class SeasonGHTDetails {

    protected int seasonId;
    @XmlElement(required = true)
    protected GHTSection1Base ghtBaseDetails;
    @XmlElement(required = true)
    protected GHTSection2Dates ghtDates;
    protected List<GHTSection3Notes> ghtNotes;

    /**
     * Gets the value of the seasonId property.
     * 
     */
    public int getSeasonId() {
        return seasonId;
    }

    /**
     * Sets the value of the seasonId property.
     * 
     */
    public void setSeasonId(int value) {
        this.seasonId = value;
    }

    /**
     * Gets the value of the ghtBaseDetails property.
     * 
     * @return
     *     possible object is
     *     {@link GHTSection1Base }
     *     
     */
    public GHTSection1Base getGhtBaseDetails() {
        return ghtBaseDetails;
    }

    /**
     * Sets the value of the ghtBaseDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link GHTSection1Base }
     *     
     */
    public void setGhtBaseDetails(GHTSection1Base value) {
        this.ghtBaseDetails = value;
    }

    /**
     * Gets the value of the ghtDates property.
     * 
     * @return
     *     possible object is
     *     {@link GHTSection2Dates }
     *     
     */
    public GHTSection2Dates getGhtDates() {
        return ghtDates;
    }

    /**
     * Sets the value of the ghtDates property.
     * 
     * @param value
     *     allowed object is
     *     {@link GHTSection2Dates }
     *     
     */
    public void setGhtDates(GHTSection2Dates value) {
        this.ghtDates = value;
    }

    /**
     * Gets the value of the ghtNotes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ghtNotes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGhtNotes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GHTSection3Notes }
     * 
     * 
     */
    public List<GHTSection3Notes> getGhtNotes() {
        if (ghtNotes == null) {
            ghtNotes = new ArrayList<GHTSection3Notes>();
        }
        return this.ghtNotes;
    }

}
