//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.25 at 10:23:10 AM CST 
//


package gov.ice.xmlschema.sevisbatch.student;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import gov.ice.xmlschema.sevisbatch.common.ForeignAddrType;
import gov.ice.xmlschema.sevisbatch.common.USAddrDoctorType;
import gov.ice.xmlschema.sevisbatch.table.StudentCreationReason;


/**
 * Global personal information for Nonimmigrant student
 * 
 * <p>Java class for StudentPersonType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StudentPersonType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UserDefinedA" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}UserDefinedAType" minOccurs="0"/>
 *         &lt;element name="UserDefinedB" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}UserDefinedBType" minOccurs="0"/>
 *         &lt;element name="PersonalInfo">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{}NonImgBioTypeRequired">
 *                 &lt;sequence>
 *                   &lt;element name="Email" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}EmailType" minOccurs="0"/>
 *                   &lt;element name="Commuter" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}IndicatorYesNoType" minOccurs="0"/>
 *                   &lt;element name="VisaType" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}StudentVisaCodeType"/>
 *                 &lt;/sequence>
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="IssueReason" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}StudentCreationReason"/>
 *         &lt;element name="USAddress" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}USAddrDoctorType" minOccurs="0"/>
 *         &lt;element name="ForeignAddress" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}ForeignAddrType"/>
 *         &lt;element name="EducationalInfo" type="{}EducationalInfoType"/>
 *         &lt;element name="FinancialInfo" type="{}FinancialType"/>
 *         &lt;element name="CreateDependent" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Dependent" maxOccurs="25">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;extension base="{}NonImgBioType">
 *                           &lt;sequence>
 *                             &lt;element name="Email" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}EmailType" minOccurs="0"/>
 *                             &lt;element name="VisaType" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}DependentFMVisaType"/>
 *                             &lt;element name="Relationship" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}DependentCodeType"/>
 *                             &lt;element name="Remarks" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}RemarksType" minOccurs="0"/>
 *                             &lt;element name="UserDefinedA" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}UserDefinedAType" minOccurs="0"/>
 *                             &lt;element name="UserDefinedB" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}UserDefinedBType" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/extension>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Remarks" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}RemarksType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StudentPersonType", propOrder = {
    "userDefinedA",
    "userDefinedB",
    "personalInfo",
    "issueReason",
    "usAddress",
    "foreignAddress",
    "educationalInfo",
    "financialInfo",
    "createDependent",
    "remarks"
})
@XmlSeeAlso({
    gov.ice.xmlschema.sevisbatch.student.SEVISStudentBatchType.CreateStudent.Student.class
})
public class StudentPersonType {

    @XmlElement(name = "UserDefinedA")
    protected String userDefinedA;
    @XmlElement(name = "UserDefinedB")
    protected String userDefinedB;
    @XmlElement(name = "PersonalInfo", required = true)
    protected StudentPersonType.PersonalInfo personalInfo;
    @XmlElement(name = "IssueReason", required = true)
    @XmlSchemaType(name = "string")
    protected StudentCreationReason issueReason;
    @XmlElement(name = "USAddress")
    protected USAddrDoctorType usAddress;
    @XmlElement(name = "ForeignAddress", required = true)
    protected ForeignAddrType foreignAddress;
    @XmlElement(name = "EducationalInfo", required = true)
    protected EducationalInfoType educationalInfo;
    @XmlElement(name = "FinancialInfo", required = true)
    protected FinancialType financialInfo;
    @XmlElement(name = "CreateDependent")
    protected StudentPersonType.CreateDependent createDependent;
    @XmlElement(name = "Remarks")
    protected String remarks;

    /**
     * Gets the value of the userDefinedA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserDefinedA() {
        return userDefinedA;
    }

    /**
     * Sets the value of the userDefinedA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserDefinedA(String value) {
        this.userDefinedA = value;
    }

    /**
     * Gets the value of the userDefinedB property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserDefinedB() {
        return userDefinedB;
    }

    /**
     * Sets the value of the userDefinedB property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserDefinedB(String value) {
        this.userDefinedB = value;
    }

    /**
     * Gets the value of the personalInfo property.
     * 
     * @return
     *     possible object is
     *     {@link StudentPersonType.PersonalInfo }
     *     
     */
    public StudentPersonType.PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    /**
     * Sets the value of the personalInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link StudentPersonType.PersonalInfo }
     *     
     */
    public void setPersonalInfo(StudentPersonType.PersonalInfo value) {
        this.personalInfo = value;
    }

    /**
     * Gets the value of the issueReason property.
     * 
     * @return
     *     possible object is
     *     {@link StudentCreationReason }
     *     
     */
    public StudentCreationReason getIssueReason() {
        return issueReason;
    }

    /**
     * Sets the value of the issueReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link StudentCreationReason }
     *     
     */
    public void setIssueReason(StudentCreationReason value) {
        this.issueReason = value;
    }

    /**
     * Gets the value of the usAddress property.
     * 
     * @return
     *     possible object is
     *     {@link USAddrDoctorType }
     *     
     */
    public USAddrDoctorType getUSAddress() {
        return usAddress;
    }

    /**
     * Sets the value of the usAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link USAddrDoctorType }
     *     
     */
    public void setUSAddress(USAddrDoctorType value) {
        this.usAddress = value;
    }

    /**
     * Gets the value of the foreignAddress property.
     * 
     * @return
     *     possible object is
     *     {@link ForeignAddrType }
     *     
     */
    public ForeignAddrType getForeignAddress() {
        return foreignAddress;
    }

    /**
     * Sets the value of the foreignAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link ForeignAddrType }
     *     
     */
    public void setForeignAddress(ForeignAddrType value) {
        this.foreignAddress = value;
    }

    /**
     * Gets the value of the educationalInfo property.
     * 
     * @return
     *     possible object is
     *     {@link EducationalInfoType }
     *     
     */
    public EducationalInfoType getEducationalInfo() {
        return educationalInfo;
    }

    /**
     * Sets the value of the educationalInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link EducationalInfoType }
     *     
     */
    public void setEducationalInfo(EducationalInfoType value) {
        this.educationalInfo = value;
    }

    /**
     * Gets the value of the financialInfo property.
     * 
     * @return
     *     possible object is
     *     {@link FinancialType }
     *     
     */
    public FinancialType getFinancialInfo() {
        return financialInfo;
    }

    /**
     * Sets the value of the financialInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link FinancialType }
     *     
     */
    public void setFinancialInfo(FinancialType value) {
        this.financialInfo = value;
    }

    /**
     * Gets the value of the createDependent property.
     * 
     * @return
     *     possible object is
     *     {@link StudentPersonType.CreateDependent }
     *     
     */
    public StudentPersonType.CreateDependent getCreateDependent() {
        return createDependent;
    }

    /**
     * Sets the value of the createDependent property.
     * 
     * @param value
     *     allowed object is
     *     {@link StudentPersonType.CreateDependent }
     *     
     */
    public void setCreateDependent(StudentPersonType.CreateDependent value) {
        this.createDependent = value;
    }

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
     *         &lt;element name="Dependent" maxOccurs="25">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;extension base="{}NonImgBioType">
     *                 &lt;sequence>
     *                   &lt;element name="Email" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}EmailType" minOccurs="0"/>
     *                   &lt;element name="VisaType" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}DependentFMVisaType"/>
     *                   &lt;element name="Relationship" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}DependentCodeType"/>
     *                   &lt;element name="Remarks" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}RemarksType" minOccurs="0"/>
     *                   &lt;element name="UserDefinedA" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}UserDefinedAType" minOccurs="0"/>
     *                   &lt;element name="UserDefinedB" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}UserDefinedBType" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/extension>
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
        "dependent"
    })
    public static class CreateDependent {

        @XmlElement(name = "Dependent", required = true)
        protected List<StudentPersonType.CreateDependent.Dependent> dependent;

        /**
         * Gets the value of the dependent property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the dependent property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDependent().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link StudentPersonType.CreateDependent.Dependent }
         * 
         * 
         */
        public List<StudentPersonType.CreateDependent.Dependent> getDependent() {
            if (dependent == null) {
                dependent = new ArrayList<StudentPersonType.CreateDependent.Dependent>();
            }
            return this.dependent;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;extension base="{}NonImgBioType">
         *       &lt;sequence>
         *         &lt;element name="Email" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}EmailType" minOccurs="0"/>
         *         &lt;element name="VisaType" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}DependentFMVisaType"/>
         *         &lt;element name="Relationship" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}DependentCodeType"/>
         *         &lt;element name="Remarks" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}RemarksType" minOccurs="0"/>
         *         &lt;element name="UserDefinedA" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}UserDefinedAType" minOccurs="0"/>
         *         &lt;element name="UserDefinedB" type="{http://www.ice.gov/xmlschema/sevisbatch/Common}UserDefinedBType" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/extension>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "email",
            "visaType",
            "relationship",
            "remarks",
            "userDefinedA",
            "userDefinedB"
        })
        public static class Dependent
            extends NonImgBioType
        {

            @XmlElement(name = "Email")
            protected String email;
            @XmlElement(name = "VisaType", required = true)
            protected String visaType;
            @XmlElement(name = "Relationship", required = true)
            protected String relationship;
            @XmlElement(name = "Remarks")
            protected String remarks;
            @XmlElement(name = "UserDefinedA")
            protected String userDefinedA;
            @XmlElement(name = "UserDefinedB")
            protected String userDefinedB;

            /**
             * Gets the value of the email property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEmail() {
                return email;
            }

            /**
             * Sets the value of the email property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEmail(String value) {
                this.email = value;
            }

            /**
             * Gets the value of the visaType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getVisaType() {
                return visaType;
            }

            /**
             * Sets the value of the visaType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setVisaType(String value) {
                this.visaType = value;
            }

            /**
             * Gets the value of the relationship property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRelationship() {
                return relationship;
            }

            /**
             * Sets the value of the relationship property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRelationship(String value) {
                this.relationship = value;
            }

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

            /**
             * Gets the value of the userDefinedA property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUserDefinedA() {
                return userDefinedA;
            }

            /**
             * Sets the value of the userDefinedA property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUserDefinedA(String value) {
                this.userDefinedA = value;
            }

            /**
             * Gets the value of the userDefinedB property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUserDefinedB() {
                return userDefinedB;
            }

            /**
             * Sets the value of the userDefinedB property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUserDefinedB(String value) {
                this.userDefinedB = value;
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
     *     &lt;extension base="{}NonImgBioTypeRequired">
     *       &lt;sequence>
     *         &lt;element name="Email" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}EmailType" minOccurs="0"/>
     *         &lt;element name="Commuter" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}IndicatorYesNoType" minOccurs="0"/>
     *         &lt;element name="VisaType" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}StudentVisaCodeType"/>
     *       &lt;/sequence>
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "email",
        "commuter",
        "visaType"
    })
    public static class PersonalInfo
        extends NonImgBioTypeRequired
    {

        @XmlElement(name = "Email")
        protected String email;
        @XmlElement(name = "Commuter")
        protected Boolean commuter;
        @XmlElement(name = "VisaType", required = true)
        protected String visaType;

        /**
         * Gets the value of the email property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEmail() {
            return email;
        }

        /**
         * Sets the value of the email property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEmail(String value) {
            this.email = value;
        }

        /**
         * Gets the value of the commuter property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isCommuter() {
            return commuter;
        }

        /**
         * Sets the value of the commuter property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setCommuter(Boolean value) {
            this.commuter = value;
        }

        /**
         * Gets the value of the visaType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVisaType() {
            return visaType;
        }

        /**
         * Sets the value of the visaType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVisaType(String value) {
            this.visaType = value;
        }

    }

}
