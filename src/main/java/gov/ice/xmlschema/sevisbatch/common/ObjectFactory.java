//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.25 at 10:25:22 AM CST 
//


package gov.ice.xmlschema.sevisbatch.common;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import gov.ice.xmlschema.sevisbatch.table.StateCodeType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the gov.ice.xmlschema.sevisbatch.common package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _USAddrDoctorTypeState_QNAME = new QName("", "State");
    private final static QName _USAddrDoctorTypeExplanationCode_QNAME = new QName("", "ExplanationCode");
    private final static QName _USAddrDoctorTypeAddress2_QNAME = new QName("", "Address2");
    private final static QName _USAddrDoctorTypeCity_QNAME = new QName("", "City");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: gov.ice.xmlschema.sevisbatch.common
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link USAddressNullableType }
     * 
     */
    public USAddressNullableType createUSAddressNullableType() {
        return new USAddressNullableType();
    }

    /**
     * Create an instance of {@link NameType }
     * 
     */
    public NameType createNameType() {
        return new NameType();
    }

    /**
     * Create an instance of {@link DriverLicenseNullableType }
     * 
     */
    public DriverLicenseNullableType createDriverLicenseNullableType() {
        return new DriverLicenseNullableType();
    }

    /**
     * Create an instance of {@link ForeignAddrType }
     * 
     */
    public ForeignAddrType createForeignAddrType() {
        return new ForeignAddrType();
    }

    /**
     * Create an instance of {@link USAddressOptionalType }
     * 
     */
    public USAddressOptionalType createUSAddressOptionalType() {
        return new USAddressOptionalType();
    }

    /**
     * Create an instance of {@link NameNullableType }
     * 
     */
    public NameNullableType createNameNullableType() {
        return new NameNullableType();
    }

    /**
     * Create an instance of {@link SEVISResponse }
     * 
     */
    public SEVISResponse createSEVISResponse() {
        return new SEVISResponse();
    }

    /**
     * Create an instance of {@link USAddrDoctorType }
     * 
     */
    public USAddrDoctorType createUSAddrDoctorType() {
        return new USAddrDoctorType();
    }

    /**
     * Create an instance of {@link USAddressType }
     * 
     */
    public USAddressType createUSAddressType() {
        return new USAddressType();
    }

    /**
     * Create an instance of {@link TravelNullableType }
     * 
     */
    public TravelNullableType createTravelNullableType() {
        return new TravelNullableType();
    }

    /**
     * Create an instance of {@link EmptyType }
     * 
     */
    public EmptyType createEmptyType() {
        return new EmptyType();
    }

    /**
     * Create an instance of {@link USAddressCommuterNullableType }
     * 
     */
    public USAddressCommuterNullableType createUSAddressCommuterNullableType() {
        return new USAddressCommuterNullableType();
    }

    /**
     * Create an instance of {@link USAddrDoctorResponseType }
     * 
     */
    public USAddrDoctorResponseType createUSAddrDoctorResponseType() {
        return new USAddrDoctorResponseType();
    }

    /**
     * Create an instance of {@link TravelType }
     * 
     */
    public TravelType createTravelType() {
        return new TravelType();
    }

    /**
     * Create an instance of {@link ForeignAddrNullableType }
     * 
     */
    public ForeignAddrNullableType createForeignAddrNullableType() {
        return new ForeignAddrNullableType();
    }

    /**
     * Create an instance of {@link BatchHeaderType }
     * 
     */
    public BatchHeaderType createBatchHeaderType() {
        return new BatchHeaderType();
    }

    /**
     * Create an instance of {@link DriverLicenseType }
     * 
     */
    public DriverLicenseType createDriverLicenseType() {
        return new DriverLicenseType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StateCodeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "State", scope = USAddrDoctorType.class)
    public JAXBElement<StateCodeType> createUSAddrDoctorTypeState(StateCodeType value) {
        return new JAXBElement<StateCodeType>(_USAddrDoctorTypeState_QNAME, StateCodeType.class, USAddrDoctorType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ExplanationCode", scope = USAddrDoctorType.class)
    public JAXBElement<String> createUSAddrDoctorTypeExplanationCode(String value) {
        return new JAXBElement<String>(_USAddrDoctorTypeExplanationCode_QNAME, String.class, USAddrDoctorType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Address2", scope = USAddrDoctorType.class)
    public JAXBElement<String> createUSAddrDoctorTypeAddress2(String value) {
        return new JAXBElement<String>(_USAddrDoctorTypeAddress2_QNAME, String.class, USAddrDoctorType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "City", scope = USAddrDoctorType.class)
    public JAXBElement<String> createUSAddrDoctorTypeCity(String value) {
        return new JAXBElement<String>(_USAddrDoctorTypeCity_QNAME, String.class, USAddrDoctorType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StateCodeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "State", scope = USAddrDoctorResponseType.class)
    public JAXBElement<StateCodeType> createUSAddrDoctorResponseTypeState(StateCodeType value) {
        return new JAXBElement<StateCodeType>(_USAddrDoctorTypeState_QNAME, StateCodeType.class, USAddrDoctorResponseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Address2", scope = USAddrDoctorResponseType.class)
    public JAXBElement<String> createUSAddrDoctorResponseTypeAddress2(String value) {
        return new JAXBElement<String>(_USAddrDoctorTypeAddress2_QNAME, String.class, USAddrDoctorResponseType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "City", scope = USAddrDoctorResponseType.class)
    public JAXBElement<String> createUSAddrDoctorResponseTypeCity(String value) {
        return new JAXBElement<String>(_USAddrDoctorTypeCity_QNAME, String.class, USAddrDoctorResponseType.class, value);
    }

}
