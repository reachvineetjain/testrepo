//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.25 at 11:14:24 AM CST 
//


package gov.ice.xmlschema.sevisbatch.exchangevisitor;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import gov.ice.xmlschema.sevisbatch.table.TippExemptProgram;


/**
 * <p>Java class for AddTIPP complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddTIPP">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;element name="TippExemptProgram" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}TippExemptProgram"/>
 *         &lt;/sequence>
 *         &lt;sequence>
 *           &lt;element name="ParticipantInfo" type="{}ParticipantInfo"/>
 *           &lt;element name="TippSite" type="{}TippSiteWithPhases" maxOccurs="25"/>
 *         &lt;/sequence>
 *       &lt;/choice>
 *       &lt;attribute name="print7002" use="required" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}IndicatorYesNoType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddTIPP", propOrder = {
    "tippExemptProgram",
    "participantInfo",
    "tippSite"
})
public class AddTIPP {

    @XmlElement(name = "TippExemptProgram")
    @XmlSchemaType(name = "string")
    protected TippExemptProgram tippExemptProgram;
    @XmlElement(name = "ParticipantInfo")
    protected ParticipantInfo participantInfo;
    @XmlElement(name = "TippSite")
    protected List<TippSiteWithPhases> tippSite;
    @XmlAttribute(name = "print7002", required = true)
    protected boolean print7002;

    /**
     * Gets the value of the tippExemptProgram property.
     * 
     * @return
     *     possible object is
     *     {@link TippExemptProgram }
     *     
     */
    public TippExemptProgram getTippExemptProgram() {
        return tippExemptProgram;
    }

    /**
     * Sets the value of the tippExemptProgram property.
     * 
     * @param value
     *     allowed object is
     *     {@link TippExemptProgram }
     *     
     */
    public void setTippExemptProgram(TippExemptProgram value) {
        this.tippExemptProgram = value;
    }

    /**
     * Gets the value of the participantInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ParticipantInfo }
     *     
     */
    public ParticipantInfo getParticipantInfo() {
        return participantInfo;
    }

    /**
     * Sets the value of the participantInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParticipantInfo }
     *     
     */
    public void setParticipantInfo(ParticipantInfo value) {
        this.participantInfo = value;
    }

    /**
     * Gets the value of the tippSite property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tippSite property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTippSite().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TippSiteWithPhases }
     * 
     * 
     */
    public List<TippSiteWithPhases> getTippSite() {
        if (tippSite == null) {
            tippSite = new ArrayList<TippSiteWithPhases>();
        }
        return this.tippSite;
    }

    /**
     * Gets the value of the print7002 property.
     * 
     */
    public boolean isPrint7002() {
        return print7002;
    }

    /**
     * Sets the value of the print7002 property.
     * 
     */
    public void setPrint7002(boolean value) {
        this.print7002 = value;
    }

}
