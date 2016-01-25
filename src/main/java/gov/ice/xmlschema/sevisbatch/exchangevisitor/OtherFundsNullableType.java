//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.25 at 10:21:39 AM CST 
//


package gov.ice.xmlschema.sevisbatch.exchangevisitor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OtherFundsNullableType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OtherFundsNullableType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="USGovt" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Org1" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}GovAgencyCodeNullableType" minOccurs="0"/>
 *                   &lt;element name="OtherName1" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}NameNullable" minOccurs="0"/>
 *                   &lt;element name="Amount1" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryNullableType" minOccurs="0"/>
 *                   &lt;element name="Org2" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}GovAgencyCodeNullableType" minOccurs="0"/>
 *                   &lt;element name="OtherName2" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}NameNullable" minOccurs="0"/>
 *                   &lt;element name="Amount2" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryNullableType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="International" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Org1" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}InternationalOrgCodeNullableType" minOccurs="0"/>
 *                   &lt;element name="OtherName1" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}NameNullable" minOccurs="0"/>
 *                   &lt;element name="Amount1" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryNullableType" minOccurs="0"/>
 *                   &lt;element name="Org2" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}InternationalOrgCodeNullableType" minOccurs="0"/>
 *                   &lt;element name="OtherName2" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}NameNullable" minOccurs="0"/>
 *                   &lt;element name="Amount2" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryNullableType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="EVGovt" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryNullableType" minOccurs="0"/>
 *         &lt;element name="BinationalCommission" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryNullableType" minOccurs="0"/>
 *         &lt;element name="Other" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Name" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}NameNullable"/>
 *                   &lt;element name="Amount" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryNullableType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Personal" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryNullableType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OtherFundsNullableType", propOrder = {
    "usGovt",
    "international",
    "evGovt",
    "binationalCommission",
    "other",
    "personal"
})
public class OtherFundsNullableType {

    @XmlElement(name = "USGovt")
    protected OtherFundsNullableType.USGovt usGovt;
    @XmlElement(name = "International")
    protected OtherFundsNullableType.International international;
    @XmlElement(name = "EVGovt")
    @XmlSchemaType(name = "anySimpleType")
    protected String evGovt;
    @XmlElement(name = "BinationalCommission")
    @XmlSchemaType(name = "anySimpleType")
    protected String binationalCommission;
    @XmlElement(name = "Other")
    protected OtherFundsNullableType.Other other;
    @XmlElement(name = "Personal")
    @XmlSchemaType(name = "anySimpleType")
    protected String personal;

    /**
     * Gets the value of the usGovt property.
     * 
     * @return
     *     possible object is
     *     {@link OtherFundsNullableType.USGovt }
     *     
     */
    public OtherFundsNullableType.USGovt getUSGovt() {
        return usGovt;
    }

    /**
     * Sets the value of the usGovt property.
     * 
     * @param value
     *     allowed object is
     *     {@link OtherFundsNullableType.USGovt }
     *     
     */
    public void setUSGovt(OtherFundsNullableType.USGovt value) {
        this.usGovt = value;
    }

    /**
     * Gets the value of the international property.
     * 
     * @return
     *     possible object is
     *     {@link OtherFundsNullableType.International }
     *     
     */
    public OtherFundsNullableType.International getInternational() {
        return international;
    }

    /**
     * Sets the value of the international property.
     * 
     * @param value
     *     allowed object is
     *     {@link OtherFundsNullableType.International }
     *     
     */
    public void setInternational(OtherFundsNullableType.International value) {
        this.international = value;
    }

    /**
     * Gets the value of the evGovt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEVGovt() {
        return evGovt;
    }

    /**
     * Sets the value of the evGovt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEVGovt(String value) {
        this.evGovt = value;
    }

    /**
     * Gets the value of the binationalCommission property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBinationalCommission() {
        return binationalCommission;
    }

    /**
     * Sets the value of the binationalCommission property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBinationalCommission(String value) {
        this.binationalCommission = value;
    }

    /**
     * Gets the value of the other property.
     * 
     * @return
     *     possible object is
     *     {@link OtherFundsNullableType.Other }
     *     
     */
    public OtherFundsNullableType.Other getOther() {
        return other;
    }

    /**
     * Sets the value of the other property.
     * 
     * @param value
     *     allowed object is
     *     {@link OtherFundsNullableType.Other }
     *     
     */
    public void setOther(OtherFundsNullableType.Other value) {
        this.other = value;
    }

    /**
     * Gets the value of the personal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonal() {
        return personal;
    }

    /**
     * Sets the value of the personal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonal(String value) {
        this.personal = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Org1" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}InternationalOrgCodeNullableType" minOccurs="0"/>
     *         &lt;element name="OtherName1" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}NameNullable" minOccurs="0"/>
     *         &lt;element name="Amount1" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryNullableType" minOccurs="0"/>
     *         &lt;element name="Org2" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}InternationalOrgCodeNullableType" minOccurs="0"/>
     *         &lt;element name="OtherName2" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}NameNullable" minOccurs="0"/>
     *         &lt;element name="Amount2" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryNullableType" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "org1",
        "otherName1",
        "amount1",
        "org2",
        "otherName2",
        "amount2"
    })
    public static class International {

        @XmlElement(name = "Org1")
        @XmlSchemaType(name = "anySimpleType")
        protected String org1;
        @XmlElement(name = "OtherName1")
        @XmlSchemaType(name = "anySimpleType")
        protected String otherName1;
        @XmlElement(name = "Amount1")
        @XmlSchemaType(name = "anySimpleType")
        protected String amount1;
        @XmlElement(name = "Org2")
        @XmlSchemaType(name = "anySimpleType")
        protected String org2;
        @XmlElement(name = "OtherName2")
        @XmlSchemaType(name = "anySimpleType")
        protected String otherName2;
        @XmlElement(name = "Amount2")
        @XmlSchemaType(name = "anySimpleType")
        protected String amount2;

        /**
         * Gets the value of the org1 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOrg1() {
            return org1;
        }

        /**
         * Sets the value of the org1 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOrg1(String value) {
            this.org1 = value;
        }

        /**
         * Gets the value of the otherName1 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOtherName1() {
            return otherName1;
        }

        /**
         * Sets the value of the otherName1 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOtherName1(String value) {
            this.otherName1 = value;
        }

        /**
         * Gets the value of the amount1 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAmount1() {
            return amount1;
        }

        /**
         * Sets the value of the amount1 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAmount1(String value) {
            this.amount1 = value;
        }

        /**
         * Gets the value of the org2 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOrg2() {
            return org2;
        }

        /**
         * Sets the value of the org2 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOrg2(String value) {
            this.org2 = value;
        }

        /**
         * Gets the value of the otherName2 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOtherName2() {
            return otherName2;
        }

        /**
         * Sets the value of the otherName2 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOtherName2(String value) {
            this.otherName2 = value;
        }

        /**
         * Gets the value of the amount2 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAmount2() {
            return amount2;
        }

        /**
         * Sets the value of the amount2 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAmount2(String value) {
            this.amount2 = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Name" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}NameNullable"/>
     *         &lt;element name="Amount" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryNullableType"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "name",
        "amount"
    })
    public static class Other {

        @XmlElement(name = "Name", required = true)
        @XmlSchemaType(name = "anySimpleType")
        protected String name;
        @XmlElement(name = "Amount", required = true)
        @XmlSchemaType(name = "anySimpleType")
        protected String amount;

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

        /**
         * Gets the value of the amount property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAmount() {
            return amount;
        }

        /**
         * Sets the value of the amount property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAmount(String value) {
            this.amount = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Org1" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}GovAgencyCodeNullableType" minOccurs="0"/>
     *         &lt;element name="OtherName1" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}NameNullable" minOccurs="0"/>
     *         &lt;element name="Amount1" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryNullableType" minOccurs="0"/>
     *         &lt;element name="Org2" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}GovAgencyCodeNullableType" minOccurs="0"/>
     *         &lt;element name="OtherName2" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}NameNullable" minOccurs="0"/>
     *         &lt;element name="Amount2" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryNullableType" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "org1",
        "otherName1",
        "amount1",
        "org2",
        "otherName2",
        "amount2"
    })
    public static class USGovt {

        @XmlElement(name = "Org1")
        @XmlSchemaType(name = "anySimpleType")
        protected String org1;
        @XmlElement(name = "OtherName1")
        @XmlSchemaType(name = "anySimpleType")
        protected String otherName1;
        @XmlElement(name = "Amount1")
        @XmlSchemaType(name = "anySimpleType")
        protected String amount1;
        @XmlElement(name = "Org2")
        @XmlSchemaType(name = "anySimpleType")
        protected String org2;
        @XmlElement(name = "OtherName2")
        @XmlSchemaType(name = "anySimpleType")
        protected String otherName2;
        @XmlElement(name = "Amount2")
        @XmlSchemaType(name = "anySimpleType")
        protected String amount2;

        /**
         * Gets the value of the org1 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOrg1() {
            return org1;
        }

        /**
         * Sets the value of the org1 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOrg1(String value) {
            this.org1 = value;
        }

        /**
         * Gets the value of the otherName1 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOtherName1() {
            return otherName1;
        }

        /**
         * Sets the value of the otherName1 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOtherName1(String value) {
            this.otherName1 = value;
        }

        /**
         * Gets the value of the amount1 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAmount1() {
            return amount1;
        }

        /**
         * Sets the value of the amount1 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAmount1(String value) {
            this.amount1 = value;
        }

        /**
         * Gets the value of the org2 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOrg2() {
            return org2;
        }

        /**
         * Sets the value of the org2 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOrg2(String value) {
            this.org2 = value;
        }

        /**
         * Gets the value of the otherName2 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOtherName2() {
            return otherName2;
        }

        /**
         * Sets the value of the otherName2 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOtherName2(String value) {
            this.otherName2 = value;
        }

        /**
         * Gets the value of the amount2 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAmount2() {
            return amount2;
        }

        /**
         * Sets the value of the amount2 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAmount2(String value) {
            this.amount2 = value;
        }

    }

}
