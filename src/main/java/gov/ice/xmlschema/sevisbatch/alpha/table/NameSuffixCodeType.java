//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.11 at 03:30:58 PM CST 
//


package gov.ice.xmlschema.sevisbatch.alpha.table;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NameSuffixCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="NameSuffixCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="I"/>
 *     &lt;enumeration value="II"/>
 *     &lt;enumeration value="III"/>
 *     &lt;enumeration value="IV"/>
 *     &lt;enumeration value="Jr."/>
 *     &lt;enumeration value="Sr."/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "NameSuffixCodeType", namespace = "http://www.ice.gov/xmlschema/sevisbatch/alpha/Table")
@XmlEnum
public enum NameSuffixCodeType {

    I("I"),
    II("II"),
    III("III"),
    IV("IV"),
    @XmlEnumValue("Jr.")
    JR("Jr."),
    @XmlEnumValue("Sr.")
    SR("Sr.");
    private final String value;

    NameSuffixCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static NameSuffixCodeType fromValue(String v) {
        for (NameSuffixCodeType c: NameSuffixCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
