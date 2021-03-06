//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.08 at 03:32:36 PM IST 
//


package com.ccighgo.service.transport.region.beans.stateregion;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for StateRegions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StateRegions">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="newSuperRegionId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="existingSuperRegionId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="newRegionId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="isStateORRegion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="stateRegions" type="{http://www.ccighgo.com/stateregion}StateRegion" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StateRegions", propOrder = {
    "seasonId",
    "newSuperRegionId",
    "existingSuperRegionId",
    "newRegionId",
    "isStateORRegion",
    "stateRegions"
})
public class StateRegions
    extends Response
{

    protected int seasonId;
    protected int newSuperRegionId;
    protected int existingSuperRegionId;
    protected int newRegionId;
    protected int isStateORRegion;
    @XmlElement(required = true)
    protected List<StateRegion> stateRegions;

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
     * Gets the value of the newSuperRegionId property.
     * 
     */
    public int getNewSuperRegionId() {
        return newSuperRegionId;
    }

    /**
     * Sets the value of the newSuperRegionId property.
     * 
     */
    public void setNewSuperRegionId(int value) {
        this.newSuperRegionId = value;
    }

    /**
     * Gets the value of the existingSuperRegionId property.
     * 
     */
    public int getExistingSuperRegionId() {
        return existingSuperRegionId;
    }

    /**
     * Sets the value of the existingSuperRegionId property.
     * 
     */
    public void setExistingSuperRegionId(int value) {
        this.existingSuperRegionId = value;
    }

    /**
     * Gets the value of the newRegionId property.
     * 
     */
    public int getNewRegionId() {
        return newRegionId;
    }

    /**
     * Sets the value of the newRegionId property.
     * 
     */
    public void setNewRegionId(int value) {
        this.newRegionId = value;
    }

    /**
     * Gets the value of the isStateORRegion property.
     * 
     */
    public int getIsStateORRegion() {
        return isStateORRegion;
    }

    /**
     * Sets the value of the isStateORRegion property.
     * 
     */
    public void setIsStateORRegion(int value) {
        this.isStateORRegion = value;
    }

    /**
     * Gets the value of the stateRegions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stateRegions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStateRegions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StateRegion }
     * 
     * 
     */
    public List<StateRegion> getStateRegions() {
        if (stateRegions == null) {
            stateRegions = new ArrayList<StateRegion>();
        }
        return this.stateRegions;
    }

}
