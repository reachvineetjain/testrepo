//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.11 at 03:30:58 PM CST 
//


package gov.ice.xmlschema.sevisbatch.alpha.table;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EVInfractionCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EVInfractionCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="EXT"/>
 *     &lt;enumeration value="OTH"/>
 *     &lt;enumeration value="REC"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EVInfractionCodeType", namespace = "http://www.ice.gov/xmlschema/sevisbatch/alpha/Table")
@XmlEnum
public enum EVInfractionCodeType {

    EXT,
    OTH,
    REC;

    public String value() {
        return name();
    }

    public static EVInfractionCodeType fromValue(String v) {
        return valueOf(v);
    }

}