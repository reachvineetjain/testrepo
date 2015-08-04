//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.03 at 06:29:22 PM CDT 
//


package com.ccighgo.service.transport.season.beans.assignedregion;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for AssignedRegion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssignedRegion">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="seasonId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="superRegionId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="regionDetail" type="{http://www.ccighgo.com/assignedregion}RegionDetail" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssignedRegion", propOrder = {
    "seasonId",
    "superRegionId",
    "regionDetail"
})
public class AssignedRegion
    extends Response
{

    protected int seasonId;
    protected int superRegionId;
    @XmlElement(required = true)
    protected List<RegionDetail> regionDetail;

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
     * Gets the value of the superRegionId property.
     * 
     */
    public int getSuperRegionId() {
        return superRegionId;
    }

    /**
     * Sets the value of the superRegionId property.
     * 
     */
    public void setSuperRegionId(int value) {
        this.superRegionId = value;
    }

    /**
     * Gets the value of the regionDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the regionDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRegionDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RegionDetail }
     * 
     * 
     */
    public List<RegionDetail> getRegionDetail() {
        if (regionDetail == null) {
            regionDetail = new ArrayList<RegionDetail>();
        }
        return this.regionDetail;
    }

}
