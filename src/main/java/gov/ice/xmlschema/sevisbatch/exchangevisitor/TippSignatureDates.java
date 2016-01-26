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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Container for all TIPP signature data
 * 
 * <p>Java class for TippSignatureDates complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TippSignatureDates">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TippSite" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SiteId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="ProgramOfficial" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="UserName" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}UserNameType"/>
 *                             &lt;element name="SignatureDate" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}DateType"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="EvSignatureDate" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}DateType" minOccurs="0"/>
 *                   &lt;sequence>
 *                     &lt;element name="Supervisors" minOccurs="0">
 *                       &lt;complexType>
 *                         &lt;complexContent>
 *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                             &lt;sequence>
 *                               &lt;element name="TippPhase" maxOccurs="unbounded">
 *                                 &lt;complexType>
 *                                   &lt;complexContent>
 *                                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                       &lt;sequence>
 *                                         &lt;element name="PhaseId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                                         &lt;element name="SignatureDate" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}DateType"/>
 *                                       &lt;/sequence>
 *                                     &lt;/restriction>
 *                                   &lt;/complexContent>
 *                                 &lt;/complexType>
 *                               &lt;/element>
 *                             &lt;/sequence>
 *                           &lt;/restriction>
 *                         &lt;/complexContent>
 *                       &lt;/complexType>
 *                     &lt;/element>
 *                   &lt;/sequence>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TippSignatureDates", propOrder = {
    "tippSite"
})
public class TippSignatureDates {

    @XmlElement(name = "TippSite", required = true)
    protected List<TippSignatureDates.TippSite> tippSite;

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
     * {@link TippSignatureDates.TippSite }
     * 
     * 
     */
    public List<TippSignatureDates.TippSite> getTippSite() {
        if (tippSite == null) {
            tippSite = new ArrayList<TippSignatureDates.TippSite>();
        }
        return this.tippSite;
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
     *         &lt;element name="SiteId" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="ProgramOfficial" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="UserName" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}UserNameType"/>
     *                   &lt;element name="SignatureDate" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}DateType"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="EvSignatureDate" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}DateType" minOccurs="0"/>
     *         &lt;sequence>
     *           &lt;element name="Supervisors" minOccurs="0">
     *             &lt;complexType>
     *               &lt;complexContent>
     *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                   &lt;sequence>
     *                     &lt;element name="TippPhase" maxOccurs="unbounded">
     *                       &lt;complexType>
     *                         &lt;complexContent>
     *                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                             &lt;sequence>
     *                               &lt;element name="PhaseId" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *                               &lt;element name="SignatureDate" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}DateType"/>
     *                             &lt;/sequence>
     *                           &lt;/restriction>
     *                         &lt;/complexContent>
     *                       &lt;/complexType>
     *                     &lt;/element>
     *                   &lt;/sequence>
     *                 &lt;/restriction>
     *               &lt;/complexContent>
     *             &lt;/complexType>
     *           &lt;/element>
     *         &lt;/sequence>
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
        "siteId",
        "programOfficial",
        "evSignatureDate",
        "supervisors"
    })
    public static class TippSite {

        @XmlElement(name = "SiteId")
        protected int siteId;
        @XmlElement(name = "ProgramOfficial")
        protected TippSignatureDates.TippSite.ProgramOfficial programOfficial;
        @XmlElement(name = "EvSignatureDate")
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar evSignatureDate;
        @XmlElement(name = "Supervisors")
        protected TippSignatureDates.TippSite.Supervisors supervisors;

        /**
         * Gets the value of the siteId property.
         * 
         */
        public int getSiteId() {
            return siteId;
        }

        /**
         * Sets the value of the siteId property.
         * 
         */
        public void setSiteId(int value) {
            this.siteId = value;
        }

        /**
         * Gets the value of the programOfficial property.
         * 
         * @return
         *     possible object is
         *     {@link TippSignatureDates.TippSite.ProgramOfficial }
         *     
         */
        public TippSignatureDates.TippSite.ProgramOfficial getProgramOfficial() {
            return programOfficial;
        }

        /**
         * Sets the value of the programOfficial property.
         * 
         * @param value
         *     allowed object is
         *     {@link TippSignatureDates.TippSite.ProgramOfficial }
         *     
         */
        public void setProgramOfficial(TippSignatureDates.TippSite.ProgramOfficial value) {
            this.programOfficial = value;
        }

        /**
         * Gets the value of the evSignatureDate property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getEvSignatureDate() {
            return evSignatureDate;
        }

        /**
         * Sets the value of the evSignatureDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setEvSignatureDate(XMLGregorianCalendar value) {
            this.evSignatureDate = value;
        }

        /**
         * Gets the value of the supervisors property.
         * 
         * @return
         *     possible object is
         *     {@link TippSignatureDates.TippSite.Supervisors }
         *     
         */
        public TippSignatureDates.TippSite.Supervisors getSupervisors() {
            return supervisors;
        }

        /**
         * Sets the value of the supervisors property.
         * 
         * @param value
         *     allowed object is
         *     {@link TippSignatureDates.TippSite.Supervisors }
         *     
         */
        public void setSupervisors(TippSignatureDates.TippSite.Supervisors value) {
            this.supervisors = value;
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
         *         &lt;element name="UserName" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}UserNameType"/>
         *         &lt;element name="SignatureDate" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}DateType"/>
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
            "userName",
            "signatureDate"
        })
        public static class ProgramOfficial {

            @XmlElement(name = "UserName", required = true)
            protected String userName;
            @XmlElement(name = "SignatureDate", required = true)
            @XmlSchemaType(name = "date")
            protected XMLGregorianCalendar signatureDate;

            /**
             * Gets the value of the userName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUserName() {
                return userName;
            }

            /**
             * Sets the value of the userName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUserName(String value) {
                this.userName = value;
            }

            /**
             * Gets the value of the signatureDate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getSignatureDate() {
                return signatureDate;
            }

            /**
             * Sets the value of the signatureDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setSignatureDate(XMLGregorianCalendar value) {
                this.signatureDate = value;
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
         *         &lt;element name="TippPhase" maxOccurs="unbounded">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="PhaseId" type="{http://www.w3.org/2001/XMLSchema}int"/>
         *                   &lt;element name="SignatureDate" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}DateType"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
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
            "tippPhase"
        })
        public static class Supervisors {

            @XmlElement(name = "TippPhase", required = true)
            protected List<TippSignatureDates.TippSite.Supervisors.TippPhase> tippPhase;

            /**
             * Gets the value of the tippPhase property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the tippPhase property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getTippPhase().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link TippSignatureDates.TippSite.Supervisors.TippPhase }
             * 
             * 
             */
            public List<TippSignatureDates.TippSite.Supervisors.TippPhase> getTippPhase() {
                if (tippPhase == null) {
                    tippPhase = new ArrayList<TippSignatureDates.TippSite.Supervisors.TippPhase>();
                }
                return this.tippPhase;
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
             *         &lt;element name="PhaseId" type="{http://www.w3.org/2001/XMLSchema}int"/>
             *         &lt;element name="SignatureDate" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}DateType"/>
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
                "phaseId",
                "signatureDate"
            })
            public static class TippPhase {

                @XmlElement(name = "PhaseId")
                protected int phaseId;
                @XmlElement(name = "SignatureDate", required = true)
                @XmlSchemaType(name = "date")
                protected XMLGregorianCalendar signatureDate;

                /**
                 * Gets the value of the phaseId property.
                 * 
                 */
                public int getPhaseId() {
                    return phaseId;
                }

                /**
                 * Sets the value of the phaseId property.
                 * 
                 */
                public void setPhaseId(int value) {
                    this.phaseId = value;
                }

                /**
                 * Gets the value of the signatureDate property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link XMLGregorianCalendar }
                 *     
                 */
                public XMLGregorianCalendar getSignatureDate() {
                    return signatureDate;
                }

                /**
                 * Sets the value of the signatureDate property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link XMLGregorianCalendar }
                 *     
                 */
                public void setSignatureDate(XMLGregorianCalendar value) {
                    this.signatureDate = value;
                }

            }

        }

    }

}