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
 * <p>Java class for EVCreateReasonType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EVCreateReasonType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NEW"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EVCreateReasonType", namespace = "http://www.ice.gov/xmlschema/sevisbatch/Table")
@XmlEnum
public enum EVCreateReasonType {

    NEW;

    public String value() {
        return name();
    }

    public static EVCreateReasonType fromValue(String v) {
        return valueOf(v);
    }

}
