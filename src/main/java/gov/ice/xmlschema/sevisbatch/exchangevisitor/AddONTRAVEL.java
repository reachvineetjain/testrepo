//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.11 at 03:30:17 PM CST 
//


package gov.ice.xmlschema.sevisbatch.exchangevisitor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * An on travel site of activity element
 * 
 * <p>Java class for AddONTRAVEL complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddONTRAVEL">
 *   &lt;complexContent>
 *     &lt;extension base="{}AbstractAdd">
 *       &lt;sequence>
 *         &lt;element name="Remarks" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.ice.gov/xmlschema/sevisbatch/alpha/Common}RemarksType">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddONTRAVEL", propOrder = {
    "remarks"
})
public class AddONTRAVEL
    extends AbstractAdd
{

    @XmlElement(name = "Remarks")
    protected String remarks;

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

}
