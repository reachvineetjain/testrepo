//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.09 at 01:43:47 PM CDT 
//


package com.ccighgo.service.transport.seasons.beans.backgroundcheckstatus;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="BackgroundReportPackage">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ProviderReferenceId">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="IdValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="ClientReferenceId">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="IdValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="ScreeningStatus">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="DateOrderReceived" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="OrderStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="AdditionalItems" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="Text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="qualifier" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="ScreeningsSummary">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="AdditionalItems">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="Text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="qualifier" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="userId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="password" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="account" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "backgroundReportPackage"
})
@XmlRootElement(name = "BackgroundReports")
public class BackgroundReports {

    @XmlElement(name = "BackgroundReportPackage", required = true)
    protected BackgroundReports.BackgroundReportPackage backgroundReportPackage;
    @XmlAttribute(name = "userId")
    protected String userId;
    @XmlAttribute(name = "password")
    protected String password;
    @XmlAttribute(name = "account")
    protected String account;

    /**
     * Gets the value of the backgroundReportPackage property.
     * 
     * @return
     *     possible object is
     *     {@link BackgroundReports.BackgroundReportPackage }
     *     
     */
    public BackgroundReports.BackgroundReportPackage getBackgroundReportPackage() {
        return backgroundReportPackage;
    }

    /**
     * Sets the value of the backgroundReportPackage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BackgroundReports.BackgroundReportPackage }
     *     
     */
    public void setBackgroundReportPackage(BackgroundReports.BackgroundReportPackage value) {
        this.backgroundReportPackage = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the account property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccount() {
        return account;
    }

    /**
     * Sets the value of the account property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccount(String value) {
        this.account = value;
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
     *         &lt;element name="ProviderReferenceId">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="IdValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="ClientReferenceId">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="IdValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="ScreeningStatus">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="DateOrderReceived" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="OrderStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="AdditionalItems" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="Text" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                           &lt;/sequence>
     *                           &lt;attribute name="qualifier" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="ScreeningsSummary">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="AdditionalItems">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="Text" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                           &lt;/sequence>
     *                           &lt;attribute name="qualifier" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "providerReferenceId",
        "clientReferenceId",
        "screeningStatus",
        "screeningsSummary"
    })
    public static class BackgroundReportPackage {

        @XmlElement(name = "ProviderReferenceId", required = true)
        protected BackgroundReports.BackgroundReportPackage.ProviderReferenceId providerReferenceId;
        @XmlElement(name = "ClientReferenceId", required = true)
        protected BackgroundReports.BackgroundReportPackage.ClientReferenceId clientReferenceId;
        @XmlElement(name = "ScreeningStatus", required = true)
        protected BackgroundReports.BackgroundReportPackage.ScreeningStatus screeningStatus;
        @XmlElement(name = "ScreeningsSummary", required = true)
        protected BackgroundReports.BackgroundReportPackage.ScreeningsSummary screeningsSummary;
        @XmlAttribute(name = "type")
        protected String type;

        /**
         * Gets the value of the providerReferenceId property.
         * 
         * @return
         *     possible object is
         *     {@link BackgroundReports.BackgroundReportPackage.ProviderReferenceId }
         *     
         */
        public BackgroundReports.BackgroundReportPackage.ProviderReferenceId getProviderReferenceId() {
            return providerReferenceId;
        }

        /**
         * Sets the value of the providerReferenceId property.
         * 
         * @param value
         *     allowed object is
         *     {@link BackgroundReports.BackgroundReportPackage.ProviderReferenceId }
         *     
         */
        public void setProviderReferenceId(BackgroundReports.BackgroundReportPackage.ProviderReferenceId value) {
            this.providerReferenceId = value;
        }

        /**
         * Gets the value of the clientReferenceId property.
         * 
         * @return
         *     possible object is
         *     {@link BackgroundReports.BackgroundReportPackage.ClientReferenceId }
         *     
         */
        public BackgroundReports.BackgroundReportPackage.ClientReferenceId getClientReferenceId() {
            return clientReferenceId;
        }

        /**
         * Sets the value of the clientReferenceId property.
         * 
         * @param value
         *     allowed object is
         *     {@link BackgroundReports.BackgroundReportPackage.ClientReferenceId }
         *     
         */
        public void setClientReferenceId(BackgroundReports.BackgroundReportPackage.ClientReferenceId value) {
            this.clientReferenceId = value;
        }

        /**
         * Gets the value of the screeningStatus property.
         * 
         * @return
         *     possible object is
         *     {@link BackgroundReports.BackgroundReportPackage.ScreeningStatus }
         *     
         */
        public BackgroundReports.BackgroundReportPackage.ScreeningStatus getScreeningStatus() {
            return screeningStatus;
        }

        /**
         * Sets the value of the screeningStatus property.
         * 
         * @param value
         *     allowed object is
         *     {@link BackgroundReports.BackgroundReportPackage.ScreeningStatus }
         *     
         */
        public void setScreeningStatus(BackgroundReports.BackgroundReportPackage.ScreeningStatus value) {
            this.screeningStatus = value;
        }

        /**
         * Gets the value of the screeningsSummary property.
         * 
         * @return
         *     possible object is
         *     {@link BackgroundReports.BackgroundReportPackage.ScreeningsSummary }
         *     
         */
        public BackgroundReports.BackgroundReportPackage.ScreeningsSummary getScreeningsSummary() {
            return screeningsSummary;
        }

        /**
         * Sets the value of the screeningsSummary property.
         * 
         * @param value
         *     allowed object is
         *     {@link BackgroundReports.BackgroundReportPackage.ScreeningsSummary }
         *     
         */
        public void setScreeningsSummary(BackgroundReports.BackgroundReportPackage.ScreeningsSummary value) {
            this.screeningsSummary = value;
        }

        /**
         * Gets the value of the type property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getType() {
            return type;
        }

        /**
         * Sets the value of the type property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setType(String value) {
            this.type = value;
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
         *         &lt;element name="IdValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            "idValue"
        })
        public static class ClientReferenceId {

            @XmlElement(name = "IdValue", required = true)
            protected String idValue;

            /**
             * Gets the value of the idValue property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIdValue() {
                return idValue;
            }

            /**
             * Sets the value of the idValue property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIdValue(String value) {
                this.idValue = value;
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
         *         &lt;element name="IdValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            "idValue"
        })
        public static class ProviderReferenceId {

            @XmlElement(name = "IdValue", required = true)
            protected String idValue;

            /**
             * Gets the value of the idValue property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIdValue() {
                return idValue;
            }

            /**
             * Sets the value of the idValue property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIdValue(String value) {
                this.idValue = value;
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
         *         &lt;element name="DateOrderReceived" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="OrderStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="AdditionalItems" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="Text" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                 &lt;/sequence>
         *                 &lt;attribute name="qualifier" type="{http://www.w3.org/2001/XMLSchema}string" />
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
            "dateOrderReceived",
            "orderStatus",
            "additionalItems"
        })
        public static class ScreeningStatus {

            @XmlElement(name = "DateOrderReceived", required = true)
            protected String dateOrderReceived;
            @XmlElement(name = "OrderStatus", required = true)
            protected String orderStatus;
            @XmlElement(name = "AdditionalItems")
            protected List<BackgroundReports.BackgroundReportPackage.ScreeningStatus.AdditionalItems> additionalItems;

            /**
             * Gets the value of the dateOrderReceived property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDateOrderReceived() {
                return dateOrderReceived;
            }

            /**
             * Sets the value of the dateOrderReceived property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDateOrderReceived(String value) {
                this.dateOrderReceived = value;
            }

            /**
             * Gets the value of the orderStatus property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOrderStatus() {
                return orderStatus;
            }

            /**
             * Sets the value of the orderStatus property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOrderStatus(String value) {
                this.orderStatus = value;
            }

            /**
             * Gets the value of the additionalItems property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the additionalItems property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getAdditionalItems().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link BackgroundReports.BackgroundReportPackage.ScreeningStatus.AdditionalItems }
             * 
             * 
             */
            public List<BackgroundReports.BackgroundReportPackage.ScreeningStatus.AdditionalItems> getAdditionalItems() {
                if (additionalItems == null) {
                    additionalItems = new ArrayList<BackgroundReports.BackgroundReportPackage.ScreeningStatus.AdditionalItems>();
                }
                return this.additionalItems;
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
             *         &lt;element name="Text" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *       &lt;/sequence>
             *       &lt;attribute name="qualifier" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "text"
            })
            public static class AdditionalItems {

                @XmlElement(name = "Text", required = true)
                protected String text;
                @XmlAttribute(name = "qualifier")
                protected String qualifier;

                /**
                 * Gets the value of the text property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getText() {
                    return text;
                }

                /**
                 * Sets the value of the text property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setText(String value) {
                    this.text = value;
                }

                /**
                 * Gets the value of the qualifier property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getQualifier() {
                    return qualifier;
                }

                /**
                 * Sets the value of the qualifier property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setQualifier(String value) {
                    this.qualifier = value;
                }

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
         *         &lt;element name="AdditionalItems">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="Text" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                 &lt;/sequence>
         *                 &lt;attribute name="qualifier" type="{http://www.w3.org/2001/XMLSchema}string" />
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
            "additionalItems"
        })
        public static class ScreeningsSummary {

            @XmlElement(name = "AdditionalItems", required = true)
            protected BackgroundReports.BackgroundReportPackage.ScreeningsSummary.AdditionalItems additionalItems;

            /**
             * Gets the value of the additionalItems property.
             * 
             * @return
             *     possible object is
             *     {@link BackgroundReports.BackgroundReportPackage.ScreeningsSummary.AdditionalItems }
             *     
             */
            public BackgroundReports.BackgroundReportPackage.ScreeningsSummary.AdditionalItems getAdditionalItems() {
                return additionalItems;
            }

            /**
             * Sets the value of the additionalItems property.
             * 
             * @param value
             *     allowed object is
             *     {@link BackgroundReports.BackgroundReportPackage.ScreeningsSummary.AdditionalItems }
             *     
             */
            public void setAdditionalItems(BackgroundReports.BackgroundReportPackage.ScreeningsSummary.AdditionalItems value) {
                this.additionalItems = value;
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
             *         &lt;element name="Text" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *       &lt;/sequence>
             *       &lt;attribute name="qualifier" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "text"
            })
            public static class AdditionalItems {

                @XmlElement(name = "Text", required = true)
                protected String text;
                @XmlAttribute(name = "qualifier")
                protected String qualifier;

                /**
                 * Gets the value of the text property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getText() {
                    return text;
                }

                /**
                 * Sets the value of the text property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setText(String value) {
                    this.text = value;
                }

                /**
                 * Gets the value of the qualifier property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getQualifier() {
                    return qualifier;
                }

                /**
                 * Sets the value of the qualifier property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setQualifier(String value) {
                    this.qualifier = value;
                }

            }

        }

    }

}
