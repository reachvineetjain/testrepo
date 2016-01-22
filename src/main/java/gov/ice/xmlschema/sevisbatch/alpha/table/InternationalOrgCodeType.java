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
 * <p>Java class for InternationalOrgCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="InternationalOrgCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ECA"/>
 *     &lt;enumeration value="ECE"/>
 *     &lt;enumeration value="ECLA"/>
 *     &lt;enumeration value="ECOSOC"/>
 *     &lt;enumeration value="EEC"/>
 *     &lt;enumeration value="ESCAP"/>
 *     &lt;enumeration value="FAO"/>
 *     &lt;enumeration value="IAEA"/>
 *     &lt;enumeration value="ICAO"/>
 *     &lt;enumeration value="ILO"/>
 *     &lt;enumeration value="IMF"/>
 *     &lt;enumeration value="IMO"/>
 *     &lt;enumeration value="ITU"/>
 *     &lt;enumeration value="NATO"/>
 *     &lt;enumeration value="OAS"/>
 *     &lt;enumeration value="OAU"/>
 *     &lt;enumeration value="OECD"/>
 *     &lt;enumeration value="OTHER"/>
 *     &lt;enumeration value="PAHO"/>
 *     &lt;enumeration value="UN"/>
 *     &lt;enumeration value="UNCTAD"/>
 *     &lt;enumeration value="UNDP"/>
 *     &lt;enumeration value="UNESCO"/>
 *     &lt;enumeration value="UNICEF"/>
 *     &lt;enumeration value="UNIDO"/>
 *     &lt;enumeration value="WB"/>
 *     &lt;enumeration value="WHO"/>
 *     &lt;enumeration value="WMO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "InternationalOrgCodeType", namespace = "http://www.ice.gov/xmlschema/sevisbatch/alpha/Table")
@XmlEnum
public enum InternationalOrgCodeType {

    ECA,
    ECE,
    ECLA,
    ECOSOC,
    EEC,
    ESCAP,
    FAO,
    IAEA,
    ICAO,
    ILO,
    IMF,
    IMO,
    ITU,
    NATO,
    OAS,
    OAU,
    OECD,
    OTHER,
    PAHO,
    UN,
    UNCTAD,
    UNDP,
    UNESCO,
    UNICEF,
    UNIDO,
    WB,
    WHO,
    WMO;

    public String value() {
        return name();
    }

    public static InternationalOrgCodeType fromValue(String v) {
        return valueOf(v);
    }

}