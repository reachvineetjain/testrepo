//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.25 at 10:25:22 AM CST 
//


package gov.ice.xmlschema.sevisbatch.table;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EVCompletionCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EVCompletionCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="APED"/>
 *     &lt;enumeration value="CSHS"/>
 *     &lt;enumeration value="DOE"/>
 *     &lt;enumeration value="IFS"/>
 *     &lt;enumeration value="MEHE"/>
 *     &lt;enumeration value="MEHF"/>
 *     &lt;enumeration value="POCE"/>
 *     &lt;enumeration value="WFP"/>
 *     &lt;enumeration value="OTHR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EVCompletionCodeType", namespace = "http://www.ice.gov/xmlschema/sevisbatch/Table")
@XmlEnum
public enum EVCompletionCodeType {

    APED,
    CSHS,
    DOE,
    IFS,
    MEHE,
    MEHF,
    POCE,
    WFP,
    OTHR;

    public String value() {
        return name();
    }

    public static EVCompletionCodeType fromValue(String v) {
        return valueOf(v);
    }

}