//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.25 at 10:23:10 AM CST 
//


package gov.ice.xmlschema.sevisbatch.student;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FinancialNullableType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FinancialNullableType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AcademicTerm" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="01"/>
 *               &lt;enumeration value="02"/>
 *               &lt;enumeration value="03"/>
 *               &lt;enumeration value="04"/>
 *               &lt;enumeration value="05"/>
 *               &lt;enumeration value="06"/>
 *               &lt;enumeration value="07"/>
 *               &lt;enumeration value="08"/>
 *               &lt;enumeration value="09"/>
 *               &lt;enumeration value="10"/>
 *               &lt;enumeration value="11"/>
 *               &lt;enumeration value="12"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Expense" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Tuition" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryType" minOccurs="0"/>
 *                   &lt;element name="LivingExpense" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryType" minOccurs="0"/>
 *                   &lt;element name="DependentExp" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryNullableType" minOccurs="0"/>
 *                   &lt;element name="Other" type="{}FinancialAmountNullableType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Funding" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Personal" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryType" minOccurs="0"/>
 *                   &lt;element name="School" type="{}FinancialAmountNullableType" minOccurs="0"/>
 *                   &lt;element name="Other" type="{}FinancialAmountNullableType" minOccurs="0"/>
 *                   &lt;element name="Employment" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryNullableType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="printForm" use="required" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}IndicatorYesNoType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FinancialNullableType", propOrder = {
    "academicTerm",
    "expense",
    "funding"
})
@XmlSeeAlso({
    gov.ice.xmlschema.sevisbatch.student.SEVISStudentBatchType.UpdateStudent.Student.FinancialInfo.class
})
public class FinancialNullableType {

    @XmlElement(name = "AcademicTerm")
    protected String academicTerm;
    @XmlElement(name = "Expense")
    protected FinancialNullableType.Expense expense;
    @XmlElement(name = "Funding")
    protected FinancialNullableType.Funding funding;
    @XmlAttribute(name = "printForm", required = true)
    protected boolean printForm;

    /**
     * Gets the value of the academicTerm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcademicTerm() {
        return academicTerm;
    }

    /**
     * Sets the value of the academicTerm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcademicTerm(String value) {
        this.academicTerm = value;
    }

    /**
     * Gets the value of the expense property.
     * 
     * @return
     *     possible object is
     *     {@link FinancialNullableType.Expense }
     *     
     */
    public FinancialNullableType.Expense getExpense() {
        return expense;
    }

    /**
     * Sets the value of the expense property.
     * 
     * @param value
     *     allowed object is
     *     {@link FinancialNullableType.Expense }
     *     
     */
    public void setExpense(FinancialNullableType.Expense value) {
        this.expense = value;
    }

    /**
     * Gets the value of the funding property.
     * 
     * @return
     *     possible object is
     *     {@link FinancialNullableType.Funding }
     *     
     */
    public FinancialNullableType.Funding getFunding() {
        return funding;
    }

    /**
     * Sets the value of the funding property.
     * 
     * @param value
     *     allowed object is
     *     {@link FinancialNullableType.Funding }
     *     
     */
    public void setFunding(FinancialNullableType.Funding value) {
        this.funding = value;
    }

    /**
     * Gets the value of the printForm property.
     * 
     */
    public boolean isPrintForm() {
        return printForm;
    }

    /**
     * Sets the value of the printForm property.
     * 
     */
    public void setPrintForm(boolean value) {
        this.printForm = value;
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
     *         &lt;element name="Tuition" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryType" minOccurs="0"/>
     *         &lt;element name="LivingExpense" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryType" minOccurs="0"/>
     *         &lt;element name="DependentExp" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryNullableType" minOccurs="0"/>
     *         &lt;element name="Other" type="{}FinancialAmountNullableType" minOccurs="0"/>
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
        "tuition",
        "livingExpense",
        "dependentExp",
        "other"
    })
    public static class Expense {

        @XmlElement(name = "Tuition")
        @XmlSchemaType(name = "nonNegativeInteger")
        protected Integer tuition;
        @XmlElement(name = "LivingExpense")
        @XmlSchemaType(name = "nonNegativeInteger")
        protected Integer livingExpense;
        @XmlElement(name = "DependentExp")
        @XmlSchemaType(name = "anySimpleType")
        protected String dependentExp;
        @XmlElement(name = "Other")
        protected FinancialAmountNullableType other;

        /**
         * Gets the value of the tuition property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getTuition() {
            return tuition;
        }

        /**
         * Sets the value of the tuition property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setTuition(Integer value) {
            this.tuition = value;
        }

        /**
         * Gets the value of the livingExpense property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getLivingExpense() {
            return livingExpense;
        }

        /**
         * Sets the value of the livingExpense property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setLivingExpense(Integer value) {
            this.livingExpense = value;
        }

        /**
         * Gets the value of the dependentExp property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDependentExp() {
            return dependentExp;
        }

        /**
         * Sets the value of the dependentExp property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDependentExp(String value) {
            this.dependentExp = value;
        }

        /**
         * Gets the value of the other property.
         * 
         * @return
         *     possible object is
         *     {@link FinancialAmountNullableType }
         *     
         */
        public FinancialAmountNullableType getOther() {
            return other;
        }

        /**
         * Sets the value of the other property.
         * 
         * @param value
         *     allowed object is
         *     {@link FinancialAmountNullableType }
         *     
         */
        public void setOther(FinancialAmountNullableType value) {
            this.other = value;
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
     *         &lt;element name="Personal" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryType" minOccurs="0"/>
     *         &lt;element name="School" type="{}FinancialAmountNullableType" minOccurs="0"/>
     *         &lt;element name="Other" type="{}FinancialAmountNullableType" minOccurs="0"/>
     *         &lt;element name="Employment" type="{http://www.ice.gov/xmlschema/sevisbatch/Table}MonetaryNullableType" minOccurs="0"/>
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
        "personal",
        "school",
        "other",
        "employment"
    })
    public static class Funding {

        @XmlElement(name = "Personal")
        @XmlSchemaType(name = "nonNegativeInteger")
        protected Integer personal;
        @XmlElement(name = "School")
        protected FinancialAmountNullableType school;
        @XmlElement(name = "Other")
        protected FinancialAmountNullableType other;
        @XmlElement(name = "Employment")
        @XmlSchemaType(name = "anySimpleType")
        protected String employment;

        /**
         * Gets the value of the personal property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getPersonal() {
            return personal;
        }

        /**
         * Sets the value of the personal property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setPersonal(Integer value) {
            this.personal = value;
        }

        /**
         * Gets the value of the school property.
         * 
         * @return
         *     possible object is
         *     {@link FinancialAmountNullableType }
         *     
         */
        public FinancialAmountNullableType getSchool() {
            return school;
        }

        /**
         * Sets the value of the school property.
         * 
         * @param value
         *     allowed object is
         *     {@link FinancialAmountNullableType }
         *     
         */
        public void setSchool(FinancialAmountNullableType value) {
            this.school = value;
        }

        /**
         * Gets the value of the other property.
         * 
         * @return
         *     possible object is
         *     {@link FinancialAmountNullableType }
         *     
         */
        public FinancialAmountNullableType getOther() {
            return other;
        }

        /**
         * Sets the value of the other property.
         * 
         * @param value
         *     allowed object is
         *     {@link FinancialAmountNullableType }
         *     
         */
        public void setOther(FinancialAmountNullableType value) {
            this.other = value;
        }

        /**
         * Gets the value of the employment property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEmployment() {
            return employment;
        }

        /**
         * Sets the value of the employment property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEmployment(String value) {
            this.employment = value;
        }

    }

}
