//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.22 at 02:21:56 PM CST 
//


package com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.programstats;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for FieldStaffDashboardProgramStats complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FieldStaffDashboardProgramStats">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="programStats" type="{http://www.ccighgo.com/fieldstaffdashboardprogramstats}FieldStaffDashboardProgramStatsDetails" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FieldStaffDashboardProgramStats", propOrder = {
    "programStats"
})
public class FieldStaffDashboardProgramStats
    extends Response
{

    @XmlElement(required = true)
    protected List<FieldStaffDashboardProgramStatsDetails> programStats;

    /**
     * Gets the value of the programStats property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the programStats property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProgramStats().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FieldStaffDashboardProgramStatsDetails }
     * 
     * 
     */
    public List<FieldStaffDashboardProgramStatsDetails> getProgramStats() {
        if (programStats == null) {
            programStats = new ArrayList<FieldStaffDashboardProgramStatsDetails>();
        }
        return this.programStats;
    }

}
