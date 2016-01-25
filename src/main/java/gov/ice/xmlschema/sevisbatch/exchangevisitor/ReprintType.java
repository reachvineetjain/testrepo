//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.25 at 10:21:39 AM CST 
//


package gov.ice.xmlschema.sevisbatch.exchangevisitor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Reprint Form
 * 
 * <p>Java class for ReprintType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReprintType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Reason" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}EVReprintRequestReasonType"/>
 *         &lt;element name="OtherRemarks" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}RemarksType" minOccurs="0"/>
 *         &lt;element name="Remarks" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}RemarksType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="printForm" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}IndicatorYesNoType" default="true" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReprintType", propOrder = {
    "reason",
    "otherRemarks",
    "remarks"
})
@XmlSeeAlso({
    gov.ice.xmlschema.sevisbatch.exchangevisitor.SEVISEVBatchType.UpdateEV.ExchangeVisitor.Dependent.Reprint.class
})
public class ReprintType {

    @XmlElement(name = "Reason", required = true)
    protected String reason;
    @XmlElement(name = "OtherRemarks")
    protected String otherRemarks;
    @XmlElement(name = "Remarks")
    protected String remarks;
    @XmlAttribute(name = "printForm")
    protected Boolean printForm;

    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReason(String value) {
        this.reason = value;
    }

    /**
     * Gets the value of the otherRemarks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherRemarks() {
        return otherRemarks;
    }

    /**
     * Sets the value of the otherRemarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherRemarks(String value) {
        this.otherRemarks = value;
    }

    /**
     * Gets the value of the remarks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * Sets the value of the remarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemarks(String value) {
        this.remarks = value;
    }

    /**
     * Gets the value of the printForm property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isPrintForm() {
        if (printForm == null) {
            return true;
        } else {
            return printForm;
        }
    }

    /**
     * Sets the value of the printForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrintForm(Boolean value) {
        this.printForm = value;
    }

}
